//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: M2
// Year: 2021
// Practical Exam Number: practical-exam-03
//===================================

public abstract class HandTool extends Tool {
    
    boolean sharp;  // is true if the tool is sharp and false otherwise.

    // A constructor that takes in a float representing the weight of the tool,
    // a float representing the value of the tool and a boolean value
    // representing whether the tool is sharp.  
    public HandTool(boolean sharp, float weight, float value) {
        super(weight, value);
        this.sharp = sharp;
    }

    // returns the value of sharp
    public boolean isSharp() {
        return sharp;
    }
    
}
