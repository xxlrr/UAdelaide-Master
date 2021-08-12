public class Patient extends Person {
    private String diagnostic;
    private String inTime;
    private String prevTime;

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getPrevTime() {
        return prevTime;
    }

    public void setPrevTime(String prevTime) {
        this.prevTime = prevTime;
    }

    public Patient() {
    }

    public Patient(String diagnostic, String inTime, String prevTime, String givenName, String lastName, int age, String gender, String citizenship) {
        super(givenName, lastName, age, gender, citizenship);
        this.diagnostic = diagnostic;
        this.inTime = inTime;
        this.prevTime = prevTime;
    }
    
}
