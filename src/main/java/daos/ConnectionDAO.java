/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;

/**
 *
 * @author hesham
 */
public interface ConnectionDAO {
    
    public Connection getConnection();
    public void closeConnection();
    public Connection openSessionConnection();
    
}
