/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.sql.Connection;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author hesham
 */
@SessionScoped
@Named(value = "loginBean")
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private Connection conn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void login() {
        FacesContext facesContect = FacesContext.getCurrentInstance();
        boolean success = true;
        if (success) {
            if (facesContect != null) {
                NavigationHandler navigationHandler = facesContect.getApplication().getNavigationHandler();
                navigationHandler.handleNavigation(facesContect, null, "/first_page?faces-redirect=true");
            }
        }
    }

    public void logout() throws Exception {
        try {
            // Release and close database resources and connections 
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }

                conn.close();
                conn = null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            setPassword(null);
            setUsername(null);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().invalidateSession();
        }
    }

}
