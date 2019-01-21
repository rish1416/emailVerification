/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allpckage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection 
{
       
    private static Connection conn = null;
    static
    {
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginDetails", "root","");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Excetion in opening in DBConnection");
        }
        catch(SQLException ex)
        {
            System.out.println("Exception in opening in DBConnection");
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try{
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("Exception in closing in DBConnection ");
        }
        
    }
}

