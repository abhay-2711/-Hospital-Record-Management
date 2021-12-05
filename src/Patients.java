

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
class Searching_in_patients{
    public static void searching_by_id(){
        Scanner searchingid=new Scanner(System.in);
        int x;
        System.out.println("Enter the id");
        x=searchingid.nextInt();
        searchingid.close();
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="Select* from patients where id=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setInt(1, x);
                ResultSet resultSet=stmt.executeQuery();
                while(resultSet.next()){
                    System.out.println("Name: "+resultSet.getString("name"));
                    System.out.println("Disease: "+resultSet.getString("Disease"));
                    System.out.println("Gender: "+resultSet.getString("Gender"));
                    System.out.println("Admit_Status: "+resultSet.getString("Admit_Status"));
                    System.out.println("Age: "+resultSet.getString("Age"));
                }
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 
    public static void searching_by_name(){
        Scanner searchingname=new Scanner(System.in);
        String req_name;;
        System.out.println("Enter the name");
        req_name=searchingname.next();
        searchingname.close();
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="Select* from Patients where Name=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1, req_name);
                ResultSet resultSet=stmt.executeQuery();
                while(resultSet.next()){
                    System.out.println("ID: "+resultSet.getString("id"));
                    System.out.println("Name: "+resultSet.getString("Name"));
                    System.out.println("Disease: "+resultSet.getString("Disease"));
                    System.out.println("Gender: "+resultSet.getString("Gender"));
                    System.out.println("Admit_Status: "+resultSet.getString("Admit_Status"));
                    System.out.println("Age: "+resultSet.getString("Age"));
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
class update_in_patients{
    public static void updating_admit(Integer x){
        Scanner idScanner=new Scanner(System.in);
        String newadmitstatus;
        System.out.println("Enter new admit status of the patient");
        newadmitstatus=idScanner.next();
        idScanner.close();
        try {
            
            BufferedReader br=new BufferedReader(new FileReader("Patients.csv"));
            PrintWriter printWriter=new PrintWriter(new FileWriter("Using_in_deletion.csv",true));
            String DocLine;
            DocLine = br.readLine();
            printWriter.print(DocLine);
            while (DocLine !=null) {
                DocLine = br.readLine();
                String array[]=new String[6];
                if (DocLine!=null){
                array=DocLine.split(",");
                }
                else
                array[0]="id";
                int req_id;
                if(array[0]!="id" && array[0]!=" "){   
                req_id=Integer.parseInt(array[0]);
                    if(req_id!=x){
                        printWriter.println();
                        printWriter.print(DocLine);
                    } 
                    else{
                        printWriter.println();
                        printWriter.print(req_id);
                        printWriter.print(",");
                        printWriter.print(array[1]);
                        printWriter.print(",");
                        printWriter.print(array[2]);
                        printWriter.print(",");
                        printWriter.print(array[3]);
                        printWriter.print(",");
                        printWriter.print(newadmitstatus);
                        printWriter.print(",");
                        printWriter.print(array[5]);
                       
                    }   
                }
            }
            br.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
        BufferedReader bufferedReader=new BufferedReader(new FileReader("Using_in_deletion.csv"));
        String DocLine;
        int i=0;
        while((DocLine=bufferedReader.readLine())!=null){
            if(i==0){
                PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Patients.csv")));
                printWriter.println(DocLine);
                printWriter.close();
            }
            else{
                PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Patients.csv",true)));
                printWriter.println(DocLine);
                printWriter.close();
            }
            i++;
        }
        bufferedReader.close();
       
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Using_in_deletion.csv")));
            String useless=" ";
            printWriter.println(useless);
            printWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="update patients set Admit_Status=? where id=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1, newadmitstatus);
                stmt.setInt(2, x);
                stmt.executeUpdate();
                System.out.println("Admit status is updated");
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
class Deletion_Patients implements InterfaceDeletion<Integer>{

    @Override
    public void Row_Deletion_in_csv(Integer x) {
        try {
            BufferedReader br=new BufferedReader(new FileReader("Doctors.csv"));
            PrintWriter printWriter=new PrintWriter(new FileWriter("Using_in_deletion.csv",true));
            String DocLine;
            DocLine = br.readLine();
            printWriter.print(DocLine);
            while (DocLine !=null) {
                DocLine = br.readLine();
                String array[]=new String[6];
                if (DocLine!=null){
                array=DocLine.split(",");
                }
                else
                array[0]="id";
                int req_id;
                if(array[0]!="id" && array[0]!=" "){   
                req_id=Integer.parseInt(array[0]);
                    if(req_id!=x){
                        printWriter.println();
                        printWriter.print(DocLine);
                    }    
                }
            }
            br.close();
            printWriter.close();
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileReader fr = new FileReader("Using_in_deletion.csv");
            FileWriter fw = new FileWriter("Patients.csv");
            String str = "";
            int i;
            while ((i = fr.read()) != -1) {
                str += (char)i;
            }
            fw.write(str);
            fr.close();
            fw.close();
        }
        catch (IOException e) {
            System.out.println(
                "There are some IOException");
        }
        try {
            PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Using_in_deletion.csv")));
            String useless=" ";
            printWriter.println(useless);
            printWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
    }

    @Override
    public void Row_Deletion_in_mysql(Integer s) {
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="delete from patients where id=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setInt(1,s);
                stmt.executeUpdate();
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}