//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: M2
// Year: 2021
// Practical Exam Number: practical-exam-03
//===================================

public abstract class PowerTool extends Tool {
    int power;  // the power rating of the tool in watts

    // A constructor that takes in a float representing the weight of the tool,
    // a float representing the value of the tool and an int value the power
    // rating of the tool
    public PowerTool(int power, float weight, float value) {
        super(weight, value);
        this.power = power;
    }

    // return the power value 
    public int getPower() {
        return power;
    }
    
}
