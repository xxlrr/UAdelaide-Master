//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: MarksOfClass
//===================================

public class Student {
 
    private String name;
    private int physic;
    private int chemistry;
    private int math;
    private int average;

    //constructor
    public Student(String name, int physic, int chemistry, int math) {
        this.name = name;
        this.physic = physic;
        this.chemistry = chemistry;
        this.math = math;
        this.average = (physic + chemistry + math) / 3;
    }
    
    // accessor
    public String getName() {
        return name;
    }

    public int getPhysic() {
        return physic;
    }

    public int getChemistry() {
        return chemistry;
    }

    public int getMath() {
        return math;
    }

    public int getAverage() {
        return average;
    }
    
    // mutator
    public void setName(String name) {
        this.name = name;
    }

    public void setPhysic(int physic) {
        this.physic = physic;
        this.average = (physic + chemistry + math) / 3;
    }

    public void setChemistry(int chemistry) {
        this.chemistry = chemistry;
        this.average = (physic + chemistry + math) / 3;
    }

    public void setMath(int math) {
        this.math = math;
        this.average = (physic + chemistry + math) / 3;
    }
    
    // the input of this method should be an array of students and the output should be an array of sorted students
    public Student[] sortStudents(Student[] students) {
        int n = students.length;        
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (compare(students[j], students[j+1]) < 0)
                {
                    Student temp = students[j];
                    students[j] = students[j+1];
                    students[j+1] = temp;
                }
            }
        }
        return students;
    }
    
    // compare the ranking of the tow students.
    private int compare(Student stu1, Student stu2) {
        // first, sort according to the average masks
        if (stu1.getAverage() > stu2.getAverage()) {
            return 1;
        }
        else if (stu2.getAverage() > stu1.getAverage()) {
            return -1;
        }
        // then, sort according to the physic masks
        if (stu1.getPhysic()> stu2.getPhysic()) {
            return 1;
        }
        else if (stu2.getPhysic() > stu1.getPhysic()) {
            return -1;
        }
        // then, sort according to the chemistry masks
        if (stu1.getChemistry()> stu2.getChemistry()) {
            return 1;
        }
        else if (stu2.getChemistry() > stu1.getChemistry()) {
            return -1;
        }
        // then, sort according to the math masks
        if (stu1.getMath()> stu2.getMath()) {
            return 1;
        }
        else if (stu2.getMath() > stu1.getMath()) {
            return -1;
        }
        // finally, reverse sort according to the names.
        return -1 * stu1.getName().compareTo(stu2.getName());
    }
    
}
