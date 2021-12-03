

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
 * Patients
 */
class Insertion_Patients implements InterfaceInsertion{
    int id;
    String name;
    String Disease;
    String Gender;
    String AdmitStat;
    int age;
    @Override
    public void write_in_csv(){
        try {
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Patients.csv",true)));
            int ID=this.id;
            String Name=this.name;
            String DiseaseName=this.Disease;
            String PatientGender=this.Gender;
            String AdmitStatus=this.AdmitStat;
            int Age=this.age;
            String comma=",";
            printWriter.println();
            printWriter.print(ID);
            printWriter.print(comma);
            printWriter.print(Name);
            printWriter.print(comma);
            printWriter.print(DiseaseName);
            printWriter.print(comma);
            printWriter.print(PatientGender);
            printWriter.print(comma);
            printWriter.print(AdmitStatus);
            printWriter.print(comma);
            printWriter.print(Age);
            printWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void field_inputs() {
        Scanner obj=new Scanner(System.in);
        System.out.println("Enter Id of the patient");
        this.id=obj.nextInt();
        System.out.println("Enter Name");
        this.name=obj.next();
        System.out.println("Enter Disease");
        this.Disease=obj.next();
        System.out.println("Enter Gender of the patient");
        this.Gender=obj.next();
        System.out.println("Enter Admit Status of the patient");
        this.AdmitStat=obj.next();
        System.out.println("Enter age of the patient");
        this.age=obj.nextInt();

        obj.close();
    }

   

    @Override
    public void insertion_jdbc() {
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="insert into patients values(?,?,?,?,?,?)";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setInt(1, this.id);
                stmt.setString(2, this.name);
                stmt.setString(3,this.Disease);
                stmt.setString(4, this.Gender);
                stmt.setString(5, this.AdmitStat);
                stmt.setInt(6, this.age);
                int rowsaffected=stmt.executeUpdate();
                System.out.println(rowsaffected+"Patients rows created");
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}