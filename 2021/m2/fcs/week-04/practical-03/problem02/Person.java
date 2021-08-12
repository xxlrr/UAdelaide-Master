public class Person{
    private String givenName;
    private String lastName;
    private int age;
    private String gender;
    private String citizenship;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }
    
    // Basic Constructor
    public Person() {
    }

    // Paramatic Constructor
    public Person(String givenName, String lastName, int age, String gender, String citizenship) {
        this.givenName = givenName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.citizenship = citizenship;
    }
    
}