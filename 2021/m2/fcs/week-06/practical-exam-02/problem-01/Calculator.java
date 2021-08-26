//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Exam Number: Problem-01
//===================================
public class Calculator {
    
    public int sum(int numA, int numB) {
        return numA + numB;
    }

    public int sub(int numA, int numB) {
        return numA - numB;
    }

    public float multiply(float numA, float numB) {
        return numA * numB;
    }

    public float division(float numA, float numB) {
        if(numB == 0) {
            return -99.0f;
        }
        return numA / numB;
    }
    
}
