// C:\Program Files\MySQL\MySQL Server 8.0\bin

import java.sql.*;
public class Conn {

    public Connection c;
    public Statement st;
    public Conn(){

        try {

            c = DriverManager.getConnection("jdbc:mysql://localhost/bank", "root", "Aman@9924");
            st =c.createStatement();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}

