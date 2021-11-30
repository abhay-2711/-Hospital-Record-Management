

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
    public void write_in_csv(){
        try {
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Doctors.csv",true)));
            int ID=this.id;
            String Name=this.name;
            String Specialization=this.Spec;
            String Time=this.timing;
            String Qual=this.qualification;
            int Room=this.room;
            String comma=",";
            printWriter.print(ID);
            printWriter.print(comma);
            printWriter.print(Name);
            printWriter.print(comma);
            printWriter.print(Specialization);
            printWriter.print(comma);
            printWriter.print(Time);
            printWriter.print(comma);
            printWriter.print(Qual);
            printWriter.print(comma);
            printWriter.print(Room);
            printWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void field_inputs() {
        Scanner obj=new Scanner(System.in);
        System.out.println("Enter Id of the doctor");
        this.id=obj.nextInt();
        System.out.println("Enter Name");
        this.name=obj.next();
        System.out.println("Enter Specialization");
        this.Spec=obj.next();
        System.out.println("Enter Timing of the doctor");
        this.timing=obj.next();
        System.out.println("Enter Qualification of the doctor");
        this.qualification=obj.next();
        System.out.println("Enter Room of the doctor");
        this.room=obj.nextInt();

        obj.close();
    }

    @Override
    public void read_csv() {
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader("Doctors.csv"));
            String DocLine;
            while ((DocLine = bufferedReader.readLine()) !=null) {
                this.insertion_jdbc();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertion_jdbc() {
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="insert into doctors values(?,?,?,?,?,?)";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setInt(1, this.id);
                stmt.setString(2, this.name);
                stmt.setString(3,this.Spec);
                stmt.setString(4, this.timing);
                stmt.setString(5, this.qualification);
                stmt.setInt(6, this.room);
                int rowsaffected=stmt.executeUpdate();
                System.out.println(rowsaffected+"Doctors rows created");
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
