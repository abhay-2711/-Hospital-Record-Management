import java.sql.SQLException;
import java.util.Scanner;

class CommandLine {

    String args;
    public CommandLine(String args) throws SQLException {
        Scanner myOBJScanner= new Scanner(System.in);
        int tno;
        switch (args) {
            case "-h":{
                System.out.println("This management system works on tables:");
                System.out.println();
                System.out.println("1 --> Doctors Table");
                System.out.println("2 --> Facilities Table");
                System.out.println("3 --> Laboratories Table");
                System.out.println("4 --> Medicines Table");
                System.out.println("5 --> Patients Table");
                System.out.println();
                System.out.println("<h> : Help");
                System.out.println();
                System.out.println("<I> : Insert a new row");
                System.out.println();
                System.out.println("<D> : Delete a row");
                System.out.println();
                System.out.println("<S> : Searching");
                System.out.println("\t--> <n> : By name");
                System.out.println("\t--> <i> : By Id");
                System.out.println("<U> :Updation");
                System.out.println();
                break;
            }
            case "-I" :{
                System.out.println("Enter the number of table :");
                tno=myOBJScanner.nextInt();
                if(tno==1){
                    Insertion_Doctors i_doctors=new Insertion_Doctors();
                    i_doctors.field_inputs();
                    i_doctors.write_in_csv();
                    i_doctors.insertion_jdbc();
                }
                else if(tno==2){
                    Insertion_Facilities i_Facilities=new Insertion_Facilities();
                    i_Facilities.field_inputs();
                    i_Facilities.write_in_csv();
                    i_Facilities.insertion_jdbc();
                }
                else if(tno==3){
                    Insertion_Laboratories i_lanoratories=new Insertion_Laboratories();
                    i_lanoratories.field_inputs();
                    i_lanoratories.write_in_csv();
                    i_lanoratories.insertion_jdbc();
                }
                else if(tno==4){
                    Insertion_Medicines i_medicines=new Insertion_Medicines();
                    i_medicines.field_inputs();
                    i_medicines.write_in_csv();
                    i_medicines.insertion_jdbc();
                }
                else if(tno==5){
                    Insertion_Patients i_patients=new Insertion_Patients();
                    i_patients.field_inputs();
                    i_patients.write_in_csv();
                    i_patients.insertion_jdbc();
                }
                break;
            }
            case "-D" :{
                System.out.println("Enter the number of table :");
                tno=myOBJScanner.nextInt();
                if(tno==1){
                    Scanner obj=new Scanner(System.in);
                    int x;
                    System.out.println("Enter ID to delete the row");
                    x=obj.nextInt();
                    obj.close();
                    Deletion_Doctor d_doctors=new Deletion_Doctor();
                    d_doctors.Row_Deletion_in_mysql(x);
                    d_doctors.Row_Deletion_in_csv(x);
                }
                else if(tno==2){
                    Scanner obj=new Scanner(System.in);
                    String x;
                    System.out.println("Enter facility name");
                    x=obj.nextLine();
                    obj.close();
                    Deletion_Facilities d_facilities=new Deletion_Facilities();
                    d_facilities.Row_Deletion_in_mysql(x);
                    d_facilities.Row_Deletion_in_csv(x);
                }
                else if(tno==3){
                    Scanner obj=new Scanner(System.in);
                    String x;
                    System.out.println("Enter facility name of laboratories table");
                    x=obj.nextLine();
                    obj.close();
                    Deletion_Laboratories d_facilities=new Deletion_Laboratories();
                    d_facilities.Row_Deletion_in_mysql(x);
                    d_facilities.Row_Deletion_in_csv(x);
                }
                else if(tno==4){
                    Scanner obj=new Scanner(System.in);
                    String x;
                    System.out.println("Enter name of medicines");
                    x=obj.nextLine();
                    obj.close();
                    Deletion_Medicines d_facilities=new Deletion_Medicines();
                    d_facilities.Row_Deletion_in_mysql(x);
                    d_facilities.Row_Deletion_in_csv(x);
                }
                else{
                    Scanner obj=new Scanner(System.in);
                    int x;
                    System.out.println("Enter ID to delete the row");
                    x=obj.nextInt();
                    obj.close();
                    Deletion_Patients d_Patient=new Deletion_Patients();
                    d_Patient.Row_Deletion_in_mysql(x);
                    d_Patient.Row_Deletion_in_csv(x);
                }
                break;
            }
            case "-S" :{
                System.out.println("Enter the number of table :");
                tno=myOBJScanner.nextInt();
                if(tno==1){
                    Scanner searching=new Scanner(System.in);
                    String x;
                    System.out.println("Enter name or id");
                    x=searching.next();
                    String id="i";
                    String name="n";
                    if(x.equals(id)){
                       Searching_in_doctors.searching_by_id();
                    }
                    else{
                        Searching_in_doctors.searching_by_name();
                    }
                    searching.close();
                }
                else if(tno==2){
                    System.out.println("Facilities table have only one attribute");
                }
                else if(tno==3){
                    Searching_in_Laboratories.search_by_name();
                }
                else if(tno==4){
                    Searching_in_medicines.Search_by_name();
                }
                else{
                    Scanner searching=new Scanner(System.in);
                    String x;
                    System.out.println("Enter name or id");
                    x=searching.next();
                    String id="i";
                    String name="n";
                    if(x.equals(id)){
                        Searching_in_patients.searching_by_id();
                    }
                    else{
                        Searching_in_patients.searching_by_name();
                    }
                    searching.close();
                }
                break;
            }
            case "-U":{
                System.out.println("Enter the number of table :");
                tno=myOBJScanner.nextInt();
                if(tno==1){
                    Scanner timeScanner=new Scanner(System.in);
                    System.out.println("Enter ID of doctor");
                    int x=timeScanner.nextInt();
                    updation_in_doctors.updating_timing(x);
                    timeScanner.close();

                }
                else if(tno==2){
                    System.out.println("Facilities table has nothing to update");
                }
                else if(tno==3){
                    Scanner nameScanner=new Scanner(System.in);
                    System.out.println("Enter name of facility in laboratories table");
                    String x=nameScanner.nextLine();
                    update_in_laboratories.updating_cost(x);
                    nameScanner.close();
                }
                else if(tno==4){
                    Scanner nameScanner=new Scanner(System.in);
                    System.out.println("Enter name of medicines");
                    String x=nameScanner.nextLine();
                    update_in_medicines.updating_cost(x);
                    nameScanner.close();
                }
                else{
                    Scanner idScanner=new Scanner(System.in);
                    System.out.println("Enter ID of Patients");
                    int x=idScanner.nextInt();
                    update_in_patients.updating_admit(x);
                    idScanner.close();
                }
            }
            default:
                break;
        }
        myOBJScanner.close();
    }
    
    
}