//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem02
//===================================

public class Problem {

    // on the screen print [101, 100, 99, 98, 97, 96, 95, 94, 93, 92, 91, 90, 89]
    public static void main(String[] args) {
        System.out.print("[");
        for(int i=101; i>=89; i--) {
            System.out.print(i);
            if(i != 89){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
}
