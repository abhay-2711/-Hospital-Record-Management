import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

/**
 * Doctors
 */
class Insertion_Doctors implements InterfaceInsertion{
    int id;
    String name;
    String Spec;
    String timing;
    String qualification;
    int room;
    
    @Override
    public void write_in_csv() {
      Properties connProperties=new Properties();
      connProperties.put("user","root");
      connProperties.put("password","Finnbalor$") ; 
      String URL="jdbc:mysql://localhost:3306/hospital_record_management";
      Connection con= DriverManager.getConnection(URL,connProperties);
      String Sql= "select* from Doctors";
      try (PreparedStatement stmt= con.prepareStatement(Sql)){
          Resultset rs=(Resultset)stmt.executeQuery(); 
      } catch (Exception e) {
         e.printStackTrace();
      }
    }

    @Override
    public void field_inputs() {
        System.out.println("Enter Id, Name, Specialist, Timing, Qualification,Room");
        Scanner obj=new Scanner(System.in);
        id=obj.nextInt();
        name=obj.nextLine();
        Spec=obj.nextLine();
        timing=obj.nextLine();
        qualification=obj.nextLine();
        room=obj.nextInt();
        
    }
    
}
