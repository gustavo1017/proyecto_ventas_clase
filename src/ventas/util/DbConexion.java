/*
 *Clase de conexion a una base de datos Mysql 
 */
package ventas.util;

import com.mysql.jdbc.Driver;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Gustavo
 * @version 1.0
 * 
 */
public class DbConexion {
    /**
     * Declaracion de variables para conexion 
    */
    private static String JDBC_DRIVER;
    private static String JDBC_URL;
    private static String JDBC_USER;
    private static String JDBC_PASS;
    private static Driver driver = null;
    private static String JDBC_FILE_NAME="jdbc";
    
    /**
     * Funcion para almacenar los datos del archivo propiedades en nuestas variables de conexion
     */
    public static  Properties loadProperties(String file){
        Properties prop = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration e = bundle.getKeys();
        String key = null;
        while(e.hasMoreElements()){
            key = (String)e.nextElement();
            prop.put(key, bundle.getObject(key));
            
        }
        JDBC_DRIVER = prop.getProperty("driver");
        JDBC_URL = prop.getProperty("url");
        JDBC_USER = prop.getProperty("user");
        JDBC_PASS = prop.getProperty("pass");
        
        
        return prop;
        
    }
    
    
    public static Connection getConnection()throws SQLException{
        if(driver == null){
            try{
                loadProperties(JDBC_FILE_NAME);
                Class jdbcClassDriver = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcClassDriver.newInstance();
                DriverManager.registerDriver(driver);
            }catch(Exception ex){
                System.out.println("Fallo en cargar el driver");
                ex.printStackTrace();
            }
        }
        
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_URL);
    }
    
    
    
    
}
