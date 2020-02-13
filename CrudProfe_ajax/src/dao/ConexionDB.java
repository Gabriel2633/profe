package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;





public class ConexionDB {

    private Connection connection = null;
    private static String driver;
    private static String url;
    private static String user;
    private static String passwd; 

    public ConexionDB() {
    	leerPropiedades();
        try {
            //Aunque ya no hace falta en Java             
            Class.forName(driver);        	
            connection = DriverManager.getConnection(url, user, passwd);

        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra el driver");
        } catch (SQLException e) {
            System.out.println("Excepcion SQL: " + e.getMessage());
            System.out.println("Estado SQL: " + e.getSQLState());
            System.out.println("Codigo del Error: " + e.getErrorCode());
            System.out.println("ERROR. No se puede conectar con la Bases de Datos: " + e);
            System.exit(-1);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException se) {
            System.out.println("Exception closing Connection: " + se);
        }
    }

    private static void leerPropiedades(){
        Properties props = new Properties();
        FileInputStream in = null;

        try {
        	//InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("database.properties");
    		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties");
        	System.out.println("InputStream is: " + inputStream);
            props.load(inputStream);

        } catch (FileNotFoundException ex) {

            Logger lgr = Logger.getLogger(ConexionDB.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } catch (IOException ex) {

            Logger lgr = Logger.getLogger(ConexionDB.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            
            try {
                 if (in != null) {
                     in.close();
                 }
            } catch (IOException ex) {
                Logger lgr = Logger.getLogger(ConexionDB.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        driver = props.getProperty("db.driver");
        url = props.getProperty("db.url");
        user = props.getProperty("db.user");
        passwd = props.getProperty("db.passwd");     	
    }
    
   
    public void update(String query) {
    	try (Statement stmt = connection.createStatement()) {
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error updating");
            }
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public ResultSet select(String query){
    	Statement stmt=null;
		try {
			stmt=connection.createStatement();
            return stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
}
