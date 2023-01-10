/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KHP2T
 */
public class ConnectSQL {
    public static String databaseName = "DoAnCoSoQL";
    public static Connection getConnection() {
    String url = "net.sourceforge.jtds.jdbc.Driver";
        try {
            Class.forName(url);
            String dbUrl = "jdbc:jtds:sqlserver://localhost:1433/DoAnCoSoQL;instance=LAPTOP-QCD6MUNN";
            return DriverManager.getConnection(dbUrl,"sa","1234567");
            
        } catch (Exception ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static void main(String[] args) {
        ConnectSQL.getConnection();
        System.out.println("successfull");
    }
}