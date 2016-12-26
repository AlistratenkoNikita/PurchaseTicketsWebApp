/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.alistratenko.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Alistratenko Nikita
 */
@WebListener
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce); //To change body of generated methods, choose Tools | Templates.
        Properties prop = new Properties();
        InputStream input = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
            input = classLoader.getResourceAsStream("ua/com/alistratenko/database/config.properties"); 
            prop.load(input);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            ConnectionFactory.connection = DriverManager.getConnection(prop.getProperty("dburl"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
            if (ConnectionFactory.connection != null) {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Connection is open");
                System.out.println("--------------------------------------------------------------------------");
            }
        } catch (Exception ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce
    ) {
        ServletContextListener.super.contextDestroyed(sce); //To change body of generated methods, choose Tools | Templates.
        try {
            ConnectionFactory.connection.close();
            if (ConnectionFactory.connection == null) {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Connection is closed");
                System.out.println("--------------------------------------------------------------------------");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
