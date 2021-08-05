//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem03
//===================================
package problem03;

public class Problem {

    // print graphics as required
    public static void main(String[] args) {
        for(int i=1; i<=40; i++) {
            switch(i/10) {
                case 0:
                    System.out.println(Problem.repeat('*', i));
                    break;
                case 1:
                    System.out.println(Problem.repeat('=', i));
                    break;
                case 2:
                    System.out.println(Problem.repeat('x', i));
                    break;
                case 3:
                case 4:
                    System.out.println(Problem.repeat('o', i));
                    break;
            }
        }
    }
    
    // returns a string of characters that are repeatedly spelled some times
    public static String repeat(char c, int repeats) {
        String str = "";
        for(int i=0; i<repeats; i++){
            str += c;
        }
        return str;
    }
}
