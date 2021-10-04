/*==================================
Foundations of Computer Science
Student: Hongxing Hao
id: a1845302
Semester: M2
Year:2021
Practical Exam Number: 5
===================================*/

public class Queue {

    Node front;  // the front of the queue is where the first Node was enqueued;
    Node back;   // the back of the queue is where the last Node is enqueued;

    // accessors
    public Node getFront() {
        return front;
    }

    public Node getBack() {
        return back;
    }

    // mutator
    public void setFront(Node front) {
        this.front = front;
    }

    public void setBack(Node back) {
        this.back = back;
    }

    // add student to back of queue
    public void enqueue(Student tmpStudent) {
        Node tmpNode = new Node(tmpStudent);
        
        if (front == null) {
            this.front = tmpNode;
        }
        if (back != null) {
            this.back.setNext(tmpNode);
        }
        this.back = tmpNode;

    }

    // remove student at front of queue 
    public Student dequeue() {
        if (this.front == null) {
            return null;
        }

        Node tmpNode = this.front;
        Student std = tmpNode.getInfo();
        this.front = tmpNode.getNext();

        return std;
    }

    // return name of student at front of queue 
    public String peek() {
        if (this.front == null) {
            return "";
        }

        Node tmpNode = this.front;
        Student std = tmpNode.getInfo();

        return std.getName();
    }

    // print queue list 
    public void displayQueue() {
        if (this.front == null) {
            System.out.println("There are no students waiting for a scholarship");
            return;
        }

        Node current = this.front;
        int number = 0;
        while (current != null) {
            number++;
            Student std = current.getInfo();
            System.out.println(
                    "# " + number + " " + std.getName() + ", "
                    + std.getAge() + " years old, "
                    + getOrdinal(std.getPeriod()) + " year in Hogwarts;"
            );
            current = current.getNext();
        }

    }

    // return ordinal number
    private String getOrdinal(int num) {
        switch (num) {
            case 1:
                return "1st";
            case 2:
                return "2nd";
            case 3:
                return "3rd";
            default:
                return num + "th";
        }
    }

}
