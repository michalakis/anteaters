/*

instructions for an ant's brain to follow

note(stan): for now, Instruction is the only public class here,
this is just to get around Java's rule about only having 1 per file,
the other classes are so simple it makes sense to have them here for now.
I think this means they are package-private which for our purposes should be ok.

note(stan): at the moment, the instructions classes themselves do not check for valid values for their components:
e.g. the constructor for Mark does not check that the int is between 0 and 5.
This is instead handled by the ant brain loader code which I think is fine because
we'll never create an ant brain any other way (the Brain constructor takes a file)
the reason I chose this is because we need to check check all states exist in the file as a whole
so it would be inconsistent to do some of the checking there and some of it here.

*/

public abstract class Instruction  {}

class Sense extends Instruction {
    // Go to thenState if cond holds in sensedir;
    // and to elseState otherwise
    
    private final SenseDirection sensedir;
    private final int thenState, elseState;
    private final Condition condition;
    
    public Sense (SenseDirection _sensedir, int _thenState, int _elseState, Condition _condition)
    { sensedir = _sensedir; thenState = _thenState; elseState = _elseState; condition = _condition; }
    
    // getters
    public SenseDirection sensedir()     { return sensedir;  }
    public int thenState()               { return thenState; }
    public int elseState()               { return elseState; }
    public Condition condition()         { return condition; }
}

class Mark extends Instruction {
    // Set mark i in current cell and go to nextState
    
    private final int marker;
    private final int nextState;
    
    public Mark (int _marker, int _nextState)
    { marker = _marker; nextState = _nextState; }
    
    // getters
    public int marker()                  { return marker;    }
    public int nextState()               { return nextState; }
}

class Unmark extends Instruction {
    // Clear mark i in current cell and go to nextState
    
    private final int marker;
    private final int nextState;
    
    public Unmark (int _marker, int _nextState)
    { marker = _marker; nextState = _nextState; }
    
    // getters
    public int marker()                  { return marker;    }
    public int nextState()               { return nextState; }
}

class PickUp extends Instruction {
    // Pick up food from current cell and go to thenState;
    // go to elseState if there is no food in the current cell

    private final int thenState, elseState;
    
    public PickUp (int _thenState, int _elseState)
    { thenState = _thenState; elseState = _elseState; }
    
    // getters
    public int thenState()               { return thenState; }
    public int elseState()               { return elseState; }
}

class Drop extends Instruction {
    // Drop food in current cell and go to nextState
    
    private final int nextState;
    
    public Drop (int _nextState)
    { nextState = _nextState; }
    
    // getters
    public int nextState()               { return nextState; }
}

class Turn extends Instruction {
    // Turn left or right and go to nextState
    
    private final LeftOrRight leftOrRight;
    private final int nextState;
    
    public Turn (LeftOrRight _leftOrRight, int _nextState)
    { leftOrRight = _leftOrRight; nextState = _nextState; }
    
    // getters
    public LeftOrRight leftOrRight()     { return leftOrRight; }
    public int nextState()               { return nextState; }
}

class Move extends Instruction {
    // Move forward and go to thenState;
    // go to elseState if the cell ahead is blocked

    private final int thenState, elseState;
    
    public Move (int _thenState, int _elseState)
    { thenState = _thenState; elseState = _elseState; }
    
    // getters
    public int thenState()               { return thenState; }
    public int elseState()               { return elseState; }
}

class Flip extends Instruction {
    // Choose a random number x from 0 to p-1;
    // go to _thenState if x=0 and _elseState otherwise
    private final int p, thenState, elseState;
    
    public Flip (int _p, int _thenState, int _elseState)
    { p = _p; thenState = _thenState; elseState = _elseState; }
    
    // getters
    public int p()                       { return p; }
    public int thenState()               { return thenState; }
    public int elseState()               { return elseState; }
}