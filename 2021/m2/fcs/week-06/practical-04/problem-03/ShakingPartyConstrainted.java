//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem02 - ShakingParty
//===================================

import java.util.Random;

public class ShakingPartyConstrainted extends ShakingParty {
    
    // number of couples in the party
    private int nCouples;

    public int getnCouples() {
        return nCouples;
    }

    public void setnCouples(int nCouples) {
        this.nCouples = nCouples;
        this.setnPeople(2 * nCouples);
    }

    // randomly generate the number of couples in the party
    public ShakingPartyConstrainted() {
        Random ran = new Random(1000);
        this.setnCouples(ran.nextInt());
    }
    
    // add nTmp to the number of couples in the party
    public ShakingPartyConstrainted(int nTmp) {
        super(2 * nTmp);
        this.nCouples = nTmp;
    }
    
    // this function should recursively count the number of handshakes;
    public int countHandShakes() {
        return this.countHandShakes(this.nCouples);
    }
    
    private int countHandShakes(int nCouples) {
        // One person in each couple needs to shake hands with everyone of the
        // same sex before they, and then shake hands with everyone of the 
        // opposite sex except the spouse. The total number of handshake is 
        // equal to the addition of the number of handshake of each person.
        
        if(nCouples == 1) {
            // there is no one before the first couple.
            return this.nCouples - 1;
        }
        // the number of handshake each couple equal to the number of couples 
        // in front of they plus the number of opposite sexes except the spouse.
        int count = (nCouples - 1) + (this.getnCouples() - 1);
        // total number of handshake equal to this person's number adding
        // total number of handshake of everyone in front of him.
        return count + this.countHandShakes(nCouples - 1);
    }
    
}

class ShakingParty {
    
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
