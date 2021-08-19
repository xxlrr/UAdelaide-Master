
public class Referee {
    
    // start a new round of the game.
    public void runARound(Player player1, Player player2) {
        
        // let two players choose
        String player1Choice = player1.performMove();
        String player2Choice = player2.performMove();
        
        // report players' choice.
        System.out.printf("Ok, %s's choice is %s, and %s's choice is %s.\n", 
                player1.getName(), player1Choice,
                player2.getName(), player2Choice);
        
        // find the winner according to the rules
        if(player1Choice.equals(player2Choice)){
            // draw
            System.out.println("The game is tied!");
        }
        else if(("Rock".equals(player1Choice) && "Scissors".equals(player2Choice))
                || ("Scissors".equals(player1Choice) && "Paper".equals(player2Choice))
                || ("Paper".equals(player1Choice) && "Rock".equals(player2Choice))
                ){
            // player1 is winner 
            System.out.printf("Congratulations, %s, you're the winner!\n", player1.getName());
        }
        else{
            // player2 is winner
            System.out.printf("Congratulations, %s, you're the winner!\n", player2.getName());
        }
    
    }
    
}
