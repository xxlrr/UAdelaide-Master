//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem02 - ShakingParty
//===================================

import java.util.Random;

public class ShakingParty {
    
    // number of people in the party
    private int nPeople;

    public int getnPeople() {
        return nPeople;
    }

    public void setnPeople(int nPeople) {
        this.nPeople = nPeople;
    }

    // randomly generate the number of people in the party
    public ShakingParty() {
        Random ran = new Random(1000);
        this.nPeople = ran.nextInt();
    }

    // add nTmp to the number of people in the party
    public ShakingParty(int nTmp) {
        this.nPeople = nTmp;
    }
    
    // this function should recursively count the number of hand shakes
    public int countHandShakes() {
        return countHandShakes(this.nPeople);
    }

    private int countHandShakes(int nPeople) {
        // Everyone only needs to shake hands with everyone in front of him,
        // and then add the number of handshake times of all people to get the
        // total number of handshake times
        
        if(nPeople == 1) {
            // there is no one before the first person, so return 0.
            return 0;
        }
        // the number of handshake each person equal to the number of people in front of him.
        int count = nPeople - 1;
        // total number of handshake equal to this person's count adding total number of handshake of everyone in front of him.
        return count + countHandShakes(nPeople - 1);
    }
    
}
