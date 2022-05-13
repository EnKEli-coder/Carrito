
package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    Connection con;
    String url="jdbc:mysql://node8531-env-0242047.sp.skdrive.net:3306/bdcarritocompras";
    String user="root";
    String pass="oVyCJBziLU";

    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
            System.out.println("auevoperrin");
        } catch (Exception e){
            System.out.println("error de coneccion localhost");
            e.toString();
        } 
        return con;
    } 

    
}
