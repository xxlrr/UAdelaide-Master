//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Practical-05
//===================================

// linear srach class
public class LinearSearch extends MySearchAlg {

    // find the num and return the index of that number
    @Override
    int search(int[] array, int num) {
        for (int i=0; i<array.length; i++) {
            if (num == array[i]) {
                return i;
            }
        }
        return -1;
    }
    
}
