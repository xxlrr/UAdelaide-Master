public class Student extends Person {
    private double[] marks;
    private String[] disciplines;

    public double[] getMarks() {
        return marks;
    }

    public void setMarks(double[] marks) {
        this.marks = marks;
    }

    public String[] getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(String[] disciplines) {
        this.disciplines = disciplines;
    }

    public Student() {
    }

    public Student(double[] marks, String[] disciplines, String givenName, String lastName, int age, String gender, String citizenship) {
        super(givenName, lastName, age, gender, citizenship);
        this.marks = marks;
        this.disciplines = disciplines;
    } 

    public double averageMarks() {
        double averageMark = 0;
        // to average only when you have grades
        if(this.marks.length > 0){
            double sumMark = 0;
            for(int i=0; i<this.marks.length; i++) {
                sumMark += this.marks[i];
            }
            averageMark = sumMark / this.marks.length;
        }
        
        return averageMark;
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
