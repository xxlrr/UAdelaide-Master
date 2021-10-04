/*==================================
Foundations of Computer Science
Student: Hongxing Hao
id: a1845302
Semester: M2
Year:2021
Practical Exam Number: 5
===================================*/

public class Node {
    Node next;
    Student info;

    // Basic constructor
    public Node() {
        this.info = null;
        this.next = null;
    }

    // Constructor with parameter for student
    public Node(Student tmpStudent) {
        this.info = tmpStudent;
        this.next = null;
    }

    // accessors
    public Node getNext() {
        return next;
    }
    
    public Student getInfo() {
        return info;
    }

    // mutator
    public void setNext(Node next) {
        this.next = next;
    }

    public void setInfo(Student info) {
        this.info = info;
    }

}
