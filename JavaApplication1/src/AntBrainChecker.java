import java.io.File;

public class AntBrainChecker {
    public static void main(String [] args)
    {
        if(args.length == 0) {
            System.out.println("Pass a .ant file as the first commandline argument.");
        }else {
            File f = new File(args[0]);
            if (f.exists()) {
                try{
                    Brain testBrain = new Brain(f);
                    System.out.println("Ant brain is valid.");
                }catch(Exception e)
                {
                    System.out.println("Problem loading ant brain:");
                    System.out.println(e.getMessage());
                }
            }else
            {
                System.out.println("Couldn't load .ant file.");
            }
        }
    }
}
