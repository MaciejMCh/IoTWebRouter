/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Session;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maciej
 */
public class RemoteSQLSessionStorage extends SessionStorage {

    @Override
    protected void saveSessionPayload(String payload) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String loadSessionPayload() {
        return "";
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Connection getConnection() throws URISyntaxException, SQLException {
        System.out.println("invoke");
    URI dbUri = new URI("ec2-54-217-240-205.eu-west-1.compute.amazonaws.com");
        
    String username = "boszccqlzcomlm";
    String password = "Bk_pvbN6mhmpHYaeu9XS0ZbAzR";
    String dbUrl = "jdbc:postgresql://" + "ec2-54-217-240-205.eu-west-1.compute.amazonaws.com" + ':' + "5432" + "/d14t8amgf8a9m";
System.out.println(dbUrl);
    return DriverManager.getConnection(dbUrl, username, password);
}
    
}
