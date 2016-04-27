import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Brain {

    private ArrayList<Instruction> instructions;
    private int state;

    public Brain(File f) throws Exception {
        instructions = new ArrayList<>();

        state = 0;

        loadFromFile(f);
        
    }
    
    private void loadFromFile(File f) throws Exception {
        // Does not check if too many arguments were supplied at the moment since this won't cause a problem
        // todo(stan): could add that later
        
        
        int highestStateSoFar = -1; // used so that at the end we can check if the highest state seen exceeds the number loaded

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int instructionNo = -1;
            while ((line = br.readLine()) != null) {
                instructionNo++;
                if(instructionNo > 9999) throw new Exception("Too many instructions. A brain can have up to 9999 instructions.");
                        
                // we know we have a line string at this point
                try {
                    String word[] = line.split(" "); // the line is split into words (seperated by spaces)
                    switch (word[0]) {               // Switching on the type of instruction to create
                        case "Sense": {
                            if (!(word.length == 5 || word.length == 6)) {
                                throw new Exception();
                            }

                            Condition c = null;
                            switch (word[4]) // switching on the type of condition to create
                            {
                                case "Friend":
                                    c = new Friend();
                                    break;
                                case "Foe":
                                    c = new Foe();
                                    break;
                                case "FriendWithFood":
                                    c = new FriendWithFood();
                                    break;
                                case "FoeWithFood":
                                    c = new FoeWithFood();
                                    break;
                                case "Food":
                                    c = new Food();
                                    break;
                                case "Rock":
                                    c = new Rock();
                                    break;
                                case "Marker":
                                    c = new Marker(Integer.parseInt(word[5]));
                                    break;
                                case "FoeMarker":
                                    c = new FoeMarker();
                                    break;
                                case "Home":
                                    c = new Home();
                                    break;
                                case "FoeHome":
                                    c = new FoeHome();
                                    break;
                                default:
                                    throw new Exception();
                            }

                            instructions.add(new Sense(
                                    SenseDirection.valueOf(word[1]),
                                    Integer.parseInt(word[2]),
                                    Integer.parseInt(word[3]),
                                    c
                                )
                            );

                            { // check if any of these states are the new highest seen so far
                                if (Integer.parseInt(word[2]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[2]);
                                }

                                if (Integer.parseInt(word[3]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[3]);
                                }
                            }
                        }break;

                        case "Mark": {
                            instructions.add(new Mark(
                                    Integer.parseInt(word[1]),
                                    Integer.parseInt(word[2])
                                )
                            );

                            { // // check if this state is the new highest seen so far
                                if (Integer.parseInt(word[2]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[2]);
                                }
                            }
                        }break;

                        case "Unmark": {
                            instructions.add(new Unmark(
                                    Integer.parseInt(word[1]),
                                    Integer.parseInt(word[2])
                                )
                            );

                            { // check if this state is the new highest seen so far
                                if (Integer.parseInt(word[2]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[2]);
                                }
                            }
                        }break;

                        case "PickUp": {
                            instructions.add(new Unmark(
                                    Integer.parseInt(word[1]),
                                    Integer.parseInt(word[2])
                                )
                            );

                            { // check if any of these states are the new highest seen so far
                                if (Integer.parseInt(word[1]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[1]);
                                }

                                if (Integer.parseInt(word[2]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[2]);
                                }
                            }
                        }break;

                        case "Drop": {
                            instructions.add(new Drop(
                                    Integer.parseInt(word[1])
                                )
                            );

                            { // check if this state is the new highest seen so far
                                if (Integer.parseInt(word[1]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[1]);
                                }
                            }
                        }break;

                        case "Turn": {
                            instructions.add(new Turn(
                                    LeftOrRight.valueOf(word[1]),
                                    Integer.parseInt(word[2])
                                )
                            );
                            
                            { // check if this state is the new highest seen so far
                                if (Integer.parseInt(word[2]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[2]);
                                }
                            }
                        }break;

                        case "Move": {
                            instructions.add(new Move(
                                    Integer.parseInt(word[1]),
                                    Integer.parseInt(word[2])
                                )
                            );

                            { // check if any of these states are the new highest seen so far
                                if (Integer.parseInt(word[1]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[1]);
                                }

                                if (Integer.parseInt(word[2]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[2]);
                                }
                            }
                        }break;

                        case "Flip": {
                            instructions.add(new Flip(
                                    Integer.parseInt(word[1]),
                                    Integer.parseInt(word[2]),
                                    Integer.parseInt(word[3])
                                )
                            );

                            { // check if any of these states are the new highest seen so far
                                if (Integer.parseInt(word[2]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[2]);
                                }

                                if (Integer.parseInt(word[3]) > highestStateSoFar) {
                                    highestStateSoFar = Integer.parseInt(word[3]);
                                }
                            }
                        }break;
                    }
                } catch (Exception e) {
                    throw new Exception("Syntax error at instruction " + instructionNo + "  (starting from 0)");
                }
            }
            
            if(instructionNo == -1) throw new Exception ("File contains no instructions!");
            if(highestStateSoFar > instructionNo) throw new Exception("State " + highestStateSoFar + " is referenced but there aren't that many instructions!");
        } catch (FileNotFoundException ex) {
            throw new Exception("Couldn't read file.");
        } catch (IOException ex) {
            throw new Exception("Couldn't read file.");
        }
    }
}
