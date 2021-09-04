import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    // read student info from the file and create student objects.
    private static Student[] readData(String filename)
            throws FileNotFoundException {
        ArrayList<Student> students = new ArrayList<>();

        File file = new File(filename);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String name = sc.next();
            int physic = sc.nextInt();
            int chemistry = sc.nextInt();
            int math = sc.nextInt();

            Student student = new Student(name, physic, chemistry, math);
            students.add(student);
        }
        return students.toArray(new Student[students.size()]);
    }
    
    // print the student info
    public static void printStudents(Student[] students) {
        System.out.printf("%-8s%-13s%-7s%-10s%-6s\n",
                "Student", "AverageScore", "Physic", "Chemistry", "Maths");
        for(int i=0; i<students.length; i++) {
            System.out.printf("%-8s%-13s%-7s%-10s%-6s\n",
                    students[i].getName(),
                    students[i].getAverage(),
                    students[i].getPhysic(),
                    students[i].getChemistry(),
                    students[i].getMath()
                );
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        // reads students.txt
        Student[] students = readData("C:\\students.txt");
        // sort students
        students[0].sortStudents(students);        
        // print student scores
        printStudents(students);
    }
    
}
