package com.techelevator.authentication;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import com.techelevator.model.user.User;
import com.techelevator.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RequestAuthProvider implements the AuthProvider and has with methods for
 * Authenticating the User's Login, Registration, Logout, Changing the User's Password,
 * getting the Current User, and checking if User has Role
 * <p>
 *  Methods : boolean isLoggedIn(), User getCurrentUser(),
 *  boolean signIn(String username, String password), void logOff(),
 *  boolean changePassword(String existingPassword, String newPassword),
 *  public void register(String username, String firstName, String lastName, String password, String role),
 *  and boolean userHasRole(String[] roles)
 */
@Component
public class RequestAuthProvider implements AuthProvider {

    private HttpServletRequest request;
    private UserDao dao;
    public final static String USER_KEY = "appCurrentUser";

    @Autowired
    public RequestAuthProvider(HttpServletRequest request, UserDao dao) {
        this.request = request;
        this.dao = dao;
    }

    @Override
    public boolean isLoggedIn() {
        return (request.getAttribute(USER_KEY) != null);
    }

    @Override
    public User getCurrentUser() {
        return (User) request.getAttribute(USER_KEY);
    }

    @Override
    public boolean signIn(String username, String password) {
    	User authenticatedUser = dao.getValidUserWithPassword(username, password);
        if(authenticatedUser != null) {
            request.setAttribute(USER_KEY, authenticatedUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void logOff() {
        request.removeAttribute(USER_KEY);
    }

    @Override
    public boolean changePassword(String existingPassword, String newPassword) {
    	User userFromSession = (User) request.getAttribute(USER_KEY);
        if(userFromSession == null) {
            return false;
        }
        User userFromDb = dao.getValidUserWithPassword(userFromSession.getUsername(), existingPassword);
        if(userFromDb != null && userFromDb.getId() == userFromDb.getId()) {
            dao.changePassword(userFromSession, newPassword);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void register(String username, String firstName, String lastName, String password, String role) {
        dao.saveUser(username, firstName, lastName, password, role);
    }

    @Override
    public boolean userHasRole(String[] roles) {
    	User currentUser = getCurrentUser();
        if(currentUser != null && roles != null) {
            return Arrays.asList(roles).contains(currentUser.getRole());
        } else {
            return false;
        }
    }
}