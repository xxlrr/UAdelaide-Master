//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: M2
// Year: 2021
// Practical Exam Number: practical-exam-03
//===================================

public class Hammer extends HandTool {

    // A constructor which takes a float parameter representing the weight
    // of the Hammer and a float parameter representing the value of the hammer
    public Hammer(float weight, float value) {
        // initialise the sharp variable of the superclass with false
        // because hammer's are not sharp.
        super(false, weight, value);
    }

    // The useTool method that takes in an integer representing the number of
    // times the Hammer is used. The method should also print out the word 
    // "Bang!" to the terminal if the hammer is available.
    @Override
    public void useTool(int times) {
        // float value = this.value - (times * 0.01f);
        // if (value > 0) {
        //     this.value = value;
        //     System.out.println("Bang!");
        // }
        // else {
        //     System.out.println("The value of the hammer is too low to use.");
        // }
        float value = this.value - (times * 0.01f);
        this.value = value > 0 ? value : 0;
        System.out.println("Bang!");
    }
    
}
