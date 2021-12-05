

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



/**
 * Doctors
 */
class Insertion_Doctors implements InterfaceInsertion{
    private int id;
    private String name;
    private String Spec;
    private String timing;
    private String qualification;
    private int room;
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
            printWriter.println();
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
class Deletion_Doctor implements InterfaceDeletion<Integer>{

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
            FileWriter fw = new FileWriter("Doctors.csv");
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
            String Query="delete from doctors where id=?";
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
class Searching_in_doctors {

    public static void searching_by_id(){
        Scanner searchingid=new Scanner(System.in);
        int x;
        System.out.println("Enter the id");
        x=searchingid.nextInt();
        searchingid.close();
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="Select* from doctors where id=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setInt(1, x);
                ResultSet resultSet=stmt.executeQuery();
                while(resultSet.next()){
                    System.out.println("Name: "+resultSet.getString("name"));
                    System.out.println("Specialist: "+resultSet.getString("Specialist"));
                    System.out.println("Timing: "+resultSet.getString("Timing"));
                    System.out.println("Qualification: "+resultSet.getString("Qualification"));
                    System.out.println("Room no: "+resultSet.getString("Room"));
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
        Scanner searchingid=new Scanner(System.in);
        String req_name;;
        System.out.println("Enter the name");
        req_name=searchingid.next();
        searchingid.close();
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="Select* from doctors where Name=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1, req_name);
                ResultSet resultSet=stmt.executeQuery();
                while(resultSet.next()){
                    System.out.println("ID: "+resultSet.getString("id"));
                    System.out.println("Name: "+resultSet.getString("Name"));
                    System.out.println("Specialist: "+resultSet.getString("Specialist"));
                    System.out.println("Timing: "+resultSet.getString("Timing"));
                    System.out.println("Qualification: "+resultSet.getString("Qualification"));
                    System.out.println("Room no: "+resultSet.getString("Room"));
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
class updation_in_doctors{
    public static void updating_timing(Integer x){
        Scanner idScanner=new Scanner(System.in);
        String newtiming;
        System.out.println("Enter new timing of the doctor");
        newtiming=idScanner.next();
        idScanner.close();
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
                    else{
                        printWriter.println();
                        printWriter.print(req_id);
                        printWriter.print(",");
                        printWriter.print(array[1]);
                        printWriter.print(",");
                        printWriter.print(array[2]);
                        printWriter.print(",");
                        printWriter.print(newtiming);
                        printWriter.print(",");
                        printWriter.print(array[4]);
                        printWriter.print(",");
                        printWriter.print(Integer.parseInt(array[5]));
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
                PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Doctors.csv")));
                printWriter.println(DocLine);
                printWriter.close();
            }
            else{
                PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Doctors.csv",true)));
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
            String Query="update doctors set Timing=? where id=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1, newtiming);
                stmt.setInt(2, x);
                stmt.executeUpdate();
                System.out.println("Timing is updated");
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}