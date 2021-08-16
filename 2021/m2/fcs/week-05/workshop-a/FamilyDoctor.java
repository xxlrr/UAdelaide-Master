public class FamilyDoctor extends Doctor { //we use keyword "extends" to inherit FamilyDoctor from Doctor
    boolean makesHouseCalls;

    void giveAdvice() {
        //give homespun advice
        System.out.println("I am a family doctor. I have some advices.");
    }
}