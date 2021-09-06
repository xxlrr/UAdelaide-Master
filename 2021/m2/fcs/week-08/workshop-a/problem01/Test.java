
public class Test {

    public static void main(String[] args) {
        Student[] students =  {
            new Student("A", 1, 1.0f),
            new Student("B", 2, 2.0f),
            new Student("C", 3, 3.0f),
            new Student("D", 4, 4.0f),
            new Student("E", 5, 5.0f),
            new Student("F", 6, 6.0f),
            new Student("G", 7, 7.0f),
            new Student("H", 8, 8.0f),            
        };
        
        // tests
        // LinearSearch s = new LinearSearch();
        BinarySearch s = new BinarySearch();
        // Student student = s.findOneByName(students, "E");
        // Student student = s.findOneByAge(students, 5);
        Student student = s.findOneByScore(students, 7);
        
        if (student != null) {
            System.out.print("Found: ");
            student.printInfo();
        }
        else {
            System.out.println("No Found!");
        }
    }
    
}
