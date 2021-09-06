
public class LinearSearch {
    
    public Student findOneByName(Student[] students, String name) {
        for (Student student: students) {
            if (name.compareTo(student.getName()) == 0) {
                return student;
            }
        }
        return null;
    }
    
    public Student findOneByAge(Student[] students, int age) {
        for (Student student: students) {
            if (age == student.getAge()) {
                return student;
            }
        }
        return null;
    }
    
    public Student findOneByScore(Student[] students, float score) {
        for (Student student: students) {
            if (score == student.getScore()) {
                return student;
            }
        }
        return null;
    }
    
}

