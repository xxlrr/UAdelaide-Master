//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: WeatherReposrting
//===================================

public class Main {

    public static void main(String[] args) {
        // the maximum temperature for each day of December 2019
        float degrees[] = {
            18.2f, 19.5f, 21.7f,   25f, 
            26.1f, 22.9f, 27.7f, 39.6f, 
            27.2f, 26.7f, 26.6f, 24.5f, 
            21.6f, 25.6f, 31.3f, 36.5f, 
            41.8f,   45f, 45.2f,   45f, 
            24.7f, 27.5f, 34.7f, 33.4f, 
            35.7f, 35.4f, 42.7f, 40.1f, 
            39.8f, 41.5f, 24.7f,
        };
        
        // used to count the number of days
        int degreeCounters[] = new int[5];
        // used to count the exceptions
        int elseDegreeCounter = 0;
        // start to count the number of days with the different temperatures.
        for(int i=0; i<degrees.length; i++) {
            // 0-10 degrees
            if(0 < degrees[i] && degrees[i] <= 10) {
                degreeCounters[0]++;
            }
            // 11-20 degrees
            else if(10 < degrees[i] && degrees[i] <= 20) {
                degreeCounters[1]++;
            }
            // 21-30 degrees
            else if(20 < degrees[i] && degrees[i] <= 30) {
                degreeCounters[2]++;
            }
            // 31-40 degrees
            else if(30 < degrees[i] && degrees[i] <= 40) {
                degreeCounters[3]++;
            }
            // 41-50 degrees
            else if(40 < degrees[i] && degrees[i] <= 50.0) {
                degreeCounters[4]++;
            }
            // else degress
            else {
                elseDegreeCounter++;
            }
        }
        
        System.out.println("The number of count the days from 0 to 10 degrees: "  + degreeCounters[0]);
        System.out.println("The number of count the days from 11 to 20 degrees: " + degreeCounters[1]);
        System.out.println("The number of count the days from 21 to 30 degrees: " + degreeCounters[2]);
        System.out.println("The number of count the days from 31 to 40 degrees: " + degreeCounters[3]);
        System.out.println("The number of count the days from 41 to 50 degrees: " + degreeCounters[4]);
        if(elseDegreeCounter > 0) {
            System.out.println("There are " + elseDegreeCounter + " exceptions to the degrees"); 
        }
    }
    
}
