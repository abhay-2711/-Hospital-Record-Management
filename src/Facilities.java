

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Facilities
 */
class Insertion_Facilities implements InterfaceInsertion{
    
	String facilities;
   
    @Override
    public void write_in_csv(){
        try {
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Facilities.csv",true)));
           
            String Facilities=this.facilities;
            
            printWriter.println();
            printWriter.print(Facilities);
            printWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void field_inputs() {
        Scanner obj=new Scanner(System.in);
       
        System.out.println("Enter Facilities");
        this.facilities=obj.next();
             obj.close();
    }

    

    @Override
    public void insertion_jdbc() {
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="insert into Facilities values(?)";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1, this.facilities);
                
                int rowsaffected=stmt.executeUpdate();
                System.out.println(rowsaffected+"Facilities rows created");
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}