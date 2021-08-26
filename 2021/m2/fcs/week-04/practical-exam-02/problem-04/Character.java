//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem-04
//===================================
public class Character {
    
    private String name; //this attribute storage the character name;
    private int age; // this attribute storage the character age;
    private String gender; // this attribute storage the character gender
    private String occupation; // this attribute storage the character occupation
    private String familyRole; // this attribute storage the family role
    private float rate;  // this attribute storage the character overall rate by fans;

    // default constructor
    public Character() {
    }

    // a Constructor containing all parameters
    public Character(String name, int age, String gender, String occupation, String familyRole, float rate) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.familyRole = familyRole;
        this.rate = rate;
    }

    // accessors
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getFamilyRole() {
        return familyRole;
    }

    public float getRate() {
        return rate;
    }

    // mutator
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setFamilyRole(String familyRole) {
        this.familyRole = familyRole;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
    
}
