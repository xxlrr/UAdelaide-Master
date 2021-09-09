//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: M2
// Year: 2021
// Practical Exam Number: practical-exam-03
//===================================

public class PowerDrill extends PowerTool {

    // A constructor which takes a float parameter representing the weight of 
    // the powerdrill and a float parameter representing the value of the powerdrill
    public PowerDrill(float weight, float value) {
        // initialise the power variable of the superclass with 800 
        // because power drills have a power rating of 800 watts
        super(800, weight, value);
    }
   
    // The useTool method that takes in an integer representing the number of
    // times the powerdrill is used. The method should also print out the word 
    // "Zssh" to the terminal if the powerdrill is available.
    @Override
    public void useTool(int times) {
        // float value = this.value - (times * 0.03f);
        // if (value > 0) {
        //     this.value = value;
        //     System.out.println("Zssh");
        // }
        // else {
        //     System.out.println("The value of the powerdrill is too low to use.");
        // }
        float value = this.value - (times * 0.03f);
        this.value = value > 0 ? value : 0;
        System.out.println("Zssh!");
    }
    
}
