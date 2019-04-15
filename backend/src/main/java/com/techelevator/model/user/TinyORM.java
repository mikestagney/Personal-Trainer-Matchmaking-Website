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

public final class TinyORM<T> {
    private Class<T> pojoClass;
    private Map<String, Field> mappedFields = new HashMap<>();

    public TinyORM(Class<T> pojo) {
    	pojoClass = pojo;
    	
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
    
    public List<T> readAll(SqlRowSet row) {
    	List<T> results = new LinkedList<T>();
        try {
        	while( row.next() ) {
        		T dto = (T) pojoClass.getConstructor().newInstance();
                for( Map.Entry<String, Field> entity: mappedFields.entrySet() ) {
                    final String dbFieldLabel = entity.getKey();
                    final Field  pojoField    = entity.getValue();
                    
                    pojoField.set( dto, pojoField.getType().cast(row.getObject(dbFieldLabel)) );
                }
                results.add(dto);
        	}
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return results.size() > 0? results: null; 
    }
    
    public T readOne(SqlRowSet row) {
        try {
        	if( row.next() ) {
        		T dto = (T) pojoClass.getConstructor().newInstance();
                for( Map.Entry<String, Field> entity: mappedFields.entrySet() ) {
                    final String dbFieldLabel = entity.getKey();
                    final Field  pojoField    = entity.getValue();
                    
                    pojoField.set( dto, pojoField.getType().cast(row.getObject(dbFieldLabel)) );
                }
                return dto;
        	} 
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    
}
