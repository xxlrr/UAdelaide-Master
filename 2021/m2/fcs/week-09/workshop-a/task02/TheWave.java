/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author haohongxing
 */
class Node {

    int forwardHeight = 0;
    int backwardHeight = 0;
    int xPos;
    int yPos;
    int oldForwardHeight = 0;
    int oldBackwardHeight = 0;

    Node prev = null;
    Node next = null;

    Node() {
        this.xPos = 0;
        this.yPos = 0;
    }

    Node(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    void update() {
        if (next != null) {
            next.forwardHeight = oldForwardHeight;
        } else {
            backwardHeight = oldForwardHeight;
        }
        if (prev != null) {
            prev.backwardHeight = oldBackwardHeight;
        } else {
            forwardHeight = oldBackwardHeight;
        }
    }

    void draw() {
        //rect(xPos, yPos+backwardHeight+forwardHeight,10,10);
        System.out.printf("%d, %d, %d, %d\n",
                xPos, yPos + backwardHeight + forwardHeight, 10, 10);

        oldForwardHeight = forwardHeight;
        oldBackwardHeight = backwardHeight;
    }

}


class TheWave {

    Node head;

    TheWave() {
        int numNodes = 20;
        int xInc = 15;
        int xPos = 10;
        head = new Node(xPos, 0);
        xPos = xPos + xInc;
        Node current = head;
        for (int i = 1; i < numNodes; i++) {
            Node nextNode = new Node(xPos, 0);
            xPos = xPos + xInc;
            nextNode.prev = current;
            current.next = nextNode;
            current = current.next;
        }
    }

    void pulse() {
        head.oldForwardHeight = 40;
    }

    void draw() {
        Node current = head;
        while (current != null) {
            current.update();
            current = current.next;
        }
        current = head;
        while (current != null) {
            current.draw();
            current = current.next;
        }
    }

}

