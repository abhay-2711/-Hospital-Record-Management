

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
class Deletion_Facilities implements InterfaceDeletion<String>{

    @Override
    public void Row_Deletion_in_csv(String x) {
        try {
            BufferedReader br=new BufferedReader(new FileReader("Facilities.csv"));
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
                array[0]="facilities";
                String req_facility;
                if(array[0]!="facilities" && array[0]!=" "){   
                req_facility=array[0];
                    if(req_facility.equals(x)){
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
            FileWriter fw = new FileWriter("Facilities.csv");
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
    public void Row_Deletion_in_mysql(String s) {
        String URL="jdbc:mysql://localhost:3306/hospital_record_management";
        try {
            Connection con= DriverManager.getConnection(URL, "root", "Finnbalor581$");
            String Query="delete from facilities where facilities=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setString(1,s);
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