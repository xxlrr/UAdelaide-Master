//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Doctor
//===================================
public class Main {

    public static void main(String[] args) {
        Doctor doctor;
        
        doctor = new Doctor();
        doctor.treatPatient();
        
        doctor = new FamilyDoctor();
        // because <treatPatient> is not overridden in FamilyDoctor, <treatPatient> in the superclass will be called
        doctor.treatPatient();
        // not accessible because it's not int the same package (Package is not used here).
        // doctor.giveAdvice();
        
        doctor = new Surgeon();
        doctor.treatPatient();
        
    }

}
