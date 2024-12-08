package org.group5.connectionSQL;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;

public class MyConnection {
    public static final String username = "root";
    public static final String password = "Truc12345678@";
    public static final String url = "jdbc:mysql://localhost:3306/test";
    public static Connection con = null;
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ""+ex,"", JOptionPane.WARNING_MESSAGE);
        }
        return con;
    }
    
    
}
