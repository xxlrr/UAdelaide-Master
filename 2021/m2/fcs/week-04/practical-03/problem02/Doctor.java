public class Doctor extends Person {
    private String speciality;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Doctor() {
    }

    public Doctor(String speciality, String givenName, String lastName, int age, String gender, String citizenship) {
        super(givenName, lastName, age, gender, citizenship);
        this.speciality = speciality;
    }
    
}
