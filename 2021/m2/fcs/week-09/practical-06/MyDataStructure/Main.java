
public class Main {

    // The bracketsMatching method takes a String of lower case letters as input.
    // The method should return TRUE when the brackets/parenthesis are matching,
    // and return FALSE if not.
    public static boolean bracketsMatching(String input) {
        MyStack bracketsStack = new MyStack();

        for (char c : input.toCharArray()) {
            Node top;
            switch (c) {
                case '(':
                case '{':
                    bracketsStack.push(new Node(String.valueOf(c)));
                    break;
                case ')':
                    top = bracketsStack.pop();
                    if (!(top != null && "(".equals(top.getData()))) {
                        return false;
                    }
                    break;
                case '}':
                    top = bracketsStack.pop();
                    if (!(top != null && "{".equals(top.getData()))) {
                        return false;
                    }
                    break;
            }
        }

        return bracketsStack.isEmpty();
    }

    // takes a Myqueue object as input, and return a Myqueue object
    // which saves a reversed queue.
    public static MyQueue reverseQueue(MyQueue queue) {
        // dequeue nodes and push to a temp stack.
        MyStack ms = new MyStack();
        while (!queue.isEmpty()) {
            String data = queue.dequeue();
            Node node = new Node(data);
            ms.push(node);
        }
        // pop nodes from stack and enqueue nodes to a new queue.
        MyQueue mq = new MyQueue();
        while (!ms.isEmpty()) {
            Node node = ms.pop();
            mq.enqueue(node);
        }
        // return the new queue.
        return mq;
    }

    public static void main(String[] args) {
        // test bracketsMatching
        System.out.println(bracketsMatching("(1+2) * { (2+3)*(3+4) }"));
        System.out.println();

        // test MyQueue
        MyQueue mq = new MyQueue();
        mq.enqueue(new Node("1"));
        mq.enqueue(new Node("2"));
        mq.enqueue(new Node("3"));
        mq.enqueue(new Node("4"));
        mq.enqueue(new Node("5"));
        System.out.println("Current Queue: ");
        mq.displayQueue();
        System.out.println();

        System.out.println("Dequeue: " + mq.dequeue());
        System.out.println();

        System.out.println("Current Queue: ");
        mq.displayQueue();
        System.out.println();

        // test reverseQueue  
        MyQueue newQ = reverseQueue(mq);
        System.out.println("Reverse: ");
        newQ.displayQueue();
        System.out.println();
    }
}
