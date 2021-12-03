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
            }
            case "-D" :{
                System.out.println("Enter the number of table :");
                tno=myOBJScanner.nextInt();
                if(tno==1){
                    Deletion_Doctor d_doctors=new Deletion_Doctor();
                   d_doctors.Row_Deletion_in_csv();

                }
            }
            default:
                break;
        }
        myOBJScanner.close();
    }
    
    
}