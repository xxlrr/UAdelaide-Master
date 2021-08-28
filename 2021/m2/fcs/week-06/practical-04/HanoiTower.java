//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem 01 - Tower of Hanoi (TOH)
//===================================
public class HanoiTower {
    
    private int disks;

    public HanoiTower() {
        this.disks = 3;
    }

    public HanoiTower(int dTmp) {
        this.disks = dTmp;
    }
    
    // use recursion to move disks
    private void hanoi(int topN, String from, String inter, String to) {
        if (topN == 1){
            System.out.println("Move the disk " + topN + " from " + from + " to " + to);
        }else {
            hanoi(topN - 1, from, to, inter);
            System.out.println("Move the disk " + topN + " from " + from + " to " + to);
            hanoi(topN - 1, inter, from, to);
        }
    }
    
    public void solve() {
        hanoi(this.disks, "Pole1", "Pole2", "Pole3");
    }
    
}
