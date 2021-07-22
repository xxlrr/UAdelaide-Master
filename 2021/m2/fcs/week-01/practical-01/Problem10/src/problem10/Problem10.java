package problem10;

/**
 * @author haohongxing
 * 
 * Problem10:organize the code to expect output:
 *    Who are you?
 *    My name is, Bond...
 *    James Bond
 *    James Bond(007)
 */
public class Problem10 {
    
    public static void main(String[] args) {
        String agent_message_01282 = "Who are you?";
        System.out.println(agent_message_01282);

        agent_message_01282 = "My name is, Bond...";
        System.out.println(agent_message_01282);
        
        agent_message_01282 = "James Bond";
        System.out.println(agent_message_01282);
        
        int agent_id = 007;
        agent_message_01282 = agent_message_01282 + "(00" + agent_id + ")";
        System.out.println(agent_message_01282);
        
    }
    
}