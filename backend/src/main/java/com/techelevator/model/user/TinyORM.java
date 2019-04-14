package com.techelevator.model.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

// annotated variable is mapped to a RecordSet field
@Retention(RetentionPolicy.RUNTIME)
@interface MapToDB {
	String value() default "";
}

public abstract class TinyORM<T> {
    protected Class<T> pojoClass;
    protected Map<String, Field> mappedFields = new HashMap<>();

    public TinyORM(Class<T> pojo) {
        // extract names of all class variables mapped to db fields
        for( Field pojoField: allPOJO_Fields(new LinkedList<Field>(), pojo) ) {
        	MapToDB dbFieldLabel = pojoField.getAnnotation(MapToDB.class);
            if( dbFieldLabel != null ) {
            	pojoField.setAccessible(true);
                String dbLabel = dbFieldLabel.value();
                if( dbLabel.isEmpty() ) {
                	dbLabel = pojoField.getName();
                }
                mappedFields.put(dbLabel, pojoField);
            }
        }
    }
    
    private static List<Field> allPOJO_Fields(List<Field> fields, Class<?> type) {
        if (type.getSuperclass() != null) {
            allPOJO_Fields(fields, type.getSuperclass());
        }
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        return fields;
    }

    protected void load(SqlRowSet row) {
        try {
        	while( row.next() ) {
                for( Map.Entry<String, Field> entity: mappedFields.entrySet() ) {
                    final String dbFieldLabel = entity.getKey();
                    final Field  pojoField    = entity.getValue();
                    System.out.println(dbFieldLabel + ": " + row.getObject(dbFieldLabel));System.out.flush();
                    pojoField.set( this, pojoField.getType().cast(row.getObject(dbFieldLabel)) );
                }
        	}
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
}
