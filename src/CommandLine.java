import java.util.Scanner;

class CommandLine {

    String args;
    public CommandLine(String args) {
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
                System.out.println("\t--> <c> : Delete a column ");
                System.out.println("\t--> <r> : Delete a row");
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
                    Insertion_Doctors i=new Insertion_Doctors();
                    i.field_inputs();
                    i.write_in_csv();
                }
            }
            default:
                break;
        }
    }
    
    
}