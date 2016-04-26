/*

note(stan):
Here I've used the same idea as in Instruction where these classes can be used polymorphically
use instanceof to figure out which one you're looking at elsewhere in the code.
Did it this way mainly because markers need to store an int.

*/

public abstract class Condition {}


class Friend extends Condition {}
class Foe extends Condition {}
class FriendWithFood extends Condition {}
class FoeWithFood extends Condition {}
class Food extends Condition {}
class Rock extends Condition {}
class Marker extends Condition { // use .i() to get the number from a marker
    private int i;
    
    public Marker(int _i)
    {
        if(i < 0 || i>5)
        {
            System.out.println("Markers must be between 0 and 5");
            System.exit(1);
        }
        
        i = _i;
    }
    
    public int i() { return i; }
}
class FoeMarker extends Condition {}
class Home extends Condition {}
class FoeHome extends Condition {}