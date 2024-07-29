///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */


import java.sql.*;
import javax.swing.JOptionPane;
 
public class Trial1 {
      public static dbConnection con(){
        Connection con =null;
       try{ 
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/login_erp","root","");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
       return con;
}
}