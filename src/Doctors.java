import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
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
    public void write_in_csv() {
        try {
          // using printwritter
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void field_inputs() {
        System.out.println("Enter Id, Name, Specialist, Timing, Qualification,Room");
        Scanner obj=new Scanner(System.in);
        id=obj.nextInt();
        name=obj.nextLine();
        Spec=obj.nextLine();
        timing=obj.nextLine();
        qualification=obj.nextLine();
        room=obj.nextInt();
        
    }
    
}
