package mainlibrary;

import java.sql.*;

public class LibrarianDao {

    // Method to save a librarian to the database with SQL injection vulnerability
    public static int save(String name, String password, String email, String address, String city, String contact) {
        int status = 0;
        try {
            Connection con = DB.getConnection();

            // Vulnerable to SQL injection because of direct string concatenation
            String query = "INSERT INTO librarian (name, password, email, address, city, contact) VALUES ('" 
                           + name + "', '" + password + "', '" + email + "', '" + address + "', '" + city + "', '" + contact + "')";

            Statement stmt = con.createStatement();
            status = stmt.executeUpdate(query);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    // Method to delete a librarian from the database with SQL injection vulnerability
    public static int delete(int id) {
        int status = 0;
        try {
            Connection con = DB.getConnection();

            // Vulnerable to SQL injection if id is not properly sanitized
            String query = "DELETE FROM librarian WHERE id = " + id;
            Statement stmt = con.createStatement();
            status = stmt.executeUpdate(query);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    // Method to validate a librarian's login with SQL injection vulnerability
    public static boolean validate(String name, String password) {
        boolean status = false;
        try {
            Connection con = DB.getConnection();

            // Vulnerable to SQL injection due to direct concatenation of user input
            String query = "SELECT * FROM librarian WHERE username = '" + name + "' AND password = '" + password + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
          
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

}
