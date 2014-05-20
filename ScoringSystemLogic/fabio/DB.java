/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src.scoringsystem.fabio;

/**
 *
 * @author fabio.loreggian
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "nbuser";
    private static final String url = "jdbc:mysql://localhost:3306/users";
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String temp;

    public DB() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successful");
        } catch (SQLException ex) {
            System.out.println("Class not found: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection error: " + ex);
        }


    }

    public DB(String username, String password) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            temp = "Connection Successful";
        } catch (SQLException ex) {
            temp = "" + ex;
        } catch (ClassNotFoundException ex) {
            temp = "" + ex;
        }
    }

    public String toString() {
        return temp;
    }

    public ResultSet query(String qry) throws SQLException {
        statement = connection.prepareCall(qry);
        resultSet = statement.executeQuery();
        return resultSet;
    }

    public ArrayList<String> processResultSet(ResultSet rs) {
        ArrayList<String> temp = new ArrayList<String>();
        int i = 0;
        try {
            while (rs.next()) {
                temp.add(rs.getString(i));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public void update(String qry) throws SQLException {
        statement = connection.prepareCall(qry);
        statement.executeUpdate();
        statement.close();

    }
}
