//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: M2
// Year: 2021
// Practical Exam Number: practical-exam-03
//===================================

public class Chisel extends HandTool {

    // A constructor which takes a float parameter representing the weight of
    // the Chisel and a float parameter representing the value of the chisel.
    public Chisel(float weight, float value) {
        // The constructor should initialise the sharp variable
        // because chisels  are sharp!
        super(true, weight, value);
    }

    // The useTool method that takes in an integer representing the number of
    // times the Chisel is used. The method should also print out the word 
    // "Scrape" to the terminal if the hammer is available.
    @Override
    public void useTool(int times) {
//        float value = this.value - (times * 0.02f);
//        if (value > 0) {
//            this.value = value;
//            System.out.println("Scrape");
//        }
//        else {
//            System.out.println("The value of the chisel is too low to use.");
//        }
        float value = this.value - (times * 0.02f);
        this.value = value > 0 ? value : 0;
        System.out.println("Scrape");
    }
    
}
