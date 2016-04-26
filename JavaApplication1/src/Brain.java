import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Brain {
    private ArrayList<Instruction> instructions;
    private int state;
    
    public Brain(File f)
    {
        state = 0;
        loadFromFile(f);
    }
    
    private void loadFromFile(File f)
    {
        Scanner scanner = null;
        int highestStateSoFar = 0;
        
        try {
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read file.");
            System.exit(0);
        }
       
        while (scanner.hasNextLine()) {
            scanner = new Scanner(scanner.nextLine());
            while (scanner.hasNext()) {
                String s = scanner.next();
                System.out.println(s);
            }
        }
    }
}