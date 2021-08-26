//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem-02
//===================================
public class DebuggingDemo {

    public void bugMethod() {
        // runtime error: array subscript out of bounds
        // int num[] = {1, 2, 3, 4};
        // following is coreected
        int num[] = {1, 2, 3, 4, 5};
        System.out.println(num[5]);
     }

    public int bugMethod2() {
        // compile-time eror: the type of variable A does not match the return value type of the function
        // float ans = 0.0;
        // following is corrected
        int ans = 0;
        System.out.println("This method had a bug!");
        return ans;
     }


}
