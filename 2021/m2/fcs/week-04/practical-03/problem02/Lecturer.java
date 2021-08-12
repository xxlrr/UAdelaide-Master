public class Lecturer extends Person {
    private double salary;
    private String[] disciplines;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String[] getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String[] disciplines) {
        this.disciplines = disciplines;
    }

    public Lecturer() {
    }

    public Lecturer(double salary, String[] disciplines, String givenName, String lastName, int age, String gender, String citizenship) {
        super(givenName, lastName, age, gender, citizenship);
        this.salary = salary;
        this.disciplines = disciplines;
    }
    
    public double annualSalary() {
        return this.salary * 12;
    }
    
    public void displayDisciplines() {
        if(this.disciplines.length == 0){
            System.out.println("There is no discipline information.");
            return;
        }
        for (String discipline : this.disciplines) {
            System.out.println(discipline);
        }
    }
}
