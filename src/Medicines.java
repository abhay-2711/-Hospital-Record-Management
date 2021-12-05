

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
    public static void Search_by_name(){
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
class update_in_medicines{
    public static void updating_cost(String x){
        Scanner idScanner=new Scanner(System.in);
        int newcost;
        System.out.println("Enter new cost of the medicine");
        newcost=idScanner.nextInt();
        idScanner.close();
        try {
            
            BufferedReader br=new BufferedReader(new FileReader("Medicines.csv"));
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
                array[0]="Name";
                String req_medicine_name;
                if(array[0]!="Name" && array[0]!=" "){   
                req_medicine_name=array[0];
                    if(req_medicine_name.equals(x)){
                        printWriter.println();
                        printWriter.print(DocLine);
                    } 
                    else{
                        printWriter.println();
                        printWriter.print(req_medicine_name);
                        printWriter.print(",");
                        printWriter.print(array[1]);
                        printWriter.print(",");
                        printWriter.print(array[2]);
                        printWriter.print(",");
                        printWriter.print(newcost);
                       
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
                PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Medicines.csv")));
                printWriter.println(DocLine);
                printWriter.close();
            }
            else{
                PrintWriter printWriter=new PrintWriter(new BufferedWriter(new FileWriter("Medicines.csv",true)));
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
            String Query="update medicines set Cost=? where Name=?";
            try(PreparedStatement stmt =con.prepareStatement(Query)) {
                stmt.setInt(1, newcost);
                stmt.setString(2, x);
                stmt.executeUpdate();
                System.out.println("Cost is updated");
            } catch (SQLException e) {
                //TODO: handle exception
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
class Deletion_Medicines implements InterfaceDeletion<String>{

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
                array[0]="Name";
                String req_facility;
                if(array[0]!="Name" && array[0]!=" "){   
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
            FileWriter fw = new FileWriter("Medicines.csv");
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
            String Query="delete from medicines where Name=?";
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