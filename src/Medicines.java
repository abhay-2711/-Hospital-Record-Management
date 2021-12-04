

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
 * Medicines
 */
class Insertion_Medicines implements InterfaceInsertion{
    String name;
    String company;
    String expiryDate;
    int cost;
    @Override
    public void write_in_csv(){
        try {
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Medicines.csv",true)));
            String Name=this.name;
            String Company=this.company;
            String ExpiryDate=this.expiryDate;
            int Cost=this.cost;
            String comma=",";
            printWriter.println();
            printWriter.print(Name);
            printWriter.print(comma);
            printWriter.print(Company);
            printWriter.print(comma);
            printWriter.print(ExpiryDate);
            printWriter.print(comma);
            printWriter.print(Cost);
            printWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void field_inputs() {
        Scanner obj=new Scanner(System.in);
        
        System.out.println("Enter Name");
        this.name=obj.nextLine();
        System.out.println("Enter Company Name");
        this.company=obj.nextLine();
        System.out.println("Enter Expiry Date");
        this.expiryDate=obj.next();
        System.out.println("Enter Cost");
        this.cost=obj.nextInt();

        obj.close();
    }

   

    @Override
    public void insertion_jdbc() {
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="insert into medicines values(?,?,?,?)";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1, this.name);
                stmt.setString(2,this.company);
                stmt.setString(3, this.expiryDate);
                stmt.setInt(4, this.cost);
                int rowsaffected=stmt.executeUpdate();
                System.out.println(rowsaffected+"Medicines rows created");
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
class Searching_in_medicines{
    public void Search_by_name(){
        Scanner searchingname=new Scanner(System.in);
        String req_name;;
        System.out.println("Enter the name of Name of medicines");
        req_name=searchingname.nextLine();
        searchingname.close();
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="Select* from medicines where Name=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1, req_name);
                ResultSet resultSet=stmt.executeQuery();
                while(resultSet.next()){
                    System.out.println("Name:  "+resultSet.getString("Name"));
                    System.out.println("Company: "+resultSet.getString("Company"));
                    System.out.println("Expiry Date: "+resultSet.getString("Expiry_Date"));
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