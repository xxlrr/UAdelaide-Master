//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: M2
// Year: 2021
// Practical Exam Number: practical-exam-03
//===================================

public abstract class Tool implements Asset {
    
    float weight;   // stores the weight of the Tool in grams.
    float value;    // stores the current value of the Tool in cents.

    // A constructor. Initialises the weight and value of this Tool object. 
    public Tool(float weight, float value) {
        this.weight = weight;
        this.value = value;
    }

    // return the value of the tool.
    @Override
    public float getValue() {
        return 0;
    }
    
    //return the weight of the tool.
    public float getWeight() {
        return 0;
    }
    
    // an abstract void method called useTool which takes in an integer
    // parameter representing the number of times the tool is used. 
    public abstract void useTool(int times);

}
