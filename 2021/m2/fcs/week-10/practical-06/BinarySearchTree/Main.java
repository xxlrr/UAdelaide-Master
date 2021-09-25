//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem
//===================================
public class Main {
    
    public static void temp(Integer i) {
        i = i + 1;
    }

    public static void main(String[] args) {        
        MyBST bst = new MyBST();
        
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);
        bst.insert(6);
        bst.insert(7);
        
        System.out.println(bst.search(1));
    }

}
