public class Surgeon extends Doctor {
 
    // deleting <Override> has no effect. It is used to help IDE.
    @Override
    void treatPatient() {
        //perform surgery
        System.out.println("I am a surgeon. I am treating a patient.");
    }

    void makeIncision() {
        //make incision
        System.out.println("I am a surgeon. I am making an incision.");
    }
}