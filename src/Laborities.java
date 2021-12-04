

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Laboratories
 */
class Insertion_Laboratories implements InterfaceInsertion{
    
    String facilities;
    int cost;
    @Override
    public void write_in_csv(){
        try {
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Laboratories.csv",true)));
            
            String Facilities=this.facilities;
            int Cost=this.cost;
            String comma=",";
            printWriter.println();
            printWriter.print(Facilities);
            printWriter.print(comma);
            printWriter.print(Cost);
            printWriter.print(comma);
            
            printWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void field_inputs() {
        Scanner obj=new Scanner(System.in);
       
        System.out.println("Facilities in Laboratory");
        this.facilities=obj.nextLine();
        System.out.println("Cost of Facilities");
        this.cost=obj.nextInt();

        obj.close();
    }

   

    @Override
    public void insertion_jdbc() {
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="insert into laboratories values(?,?)";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
               
                stmt.setString(1, this.facilities);
                stmt.setInt(2, this.cost);
                int rowsaffected=stmt.executeUpdate();
                System.out.println(rowsaffected+"Laboratories rows created");
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
class Searching_in_Laboratories{
    public void search_by_name(){
        Scanner searchingname=new Scanner(System.in);
        String req_name;;
        System.out.println("Enter the name of Laboratory facility");
        req_name=searchingname.nextLine();
        searchingname.close();
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="Select* from laboratories where Facilities=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1, req_name);
                ResultSet resultSet=stmt.executeQuery();
                while(resultSet.next()){
                    System.out.println("Facilities: "+resultSet.getString("Facilities"));
                    System.out.println("Cost: "+resultSet.getString("Cost"));
                }
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
