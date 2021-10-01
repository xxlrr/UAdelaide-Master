
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    
    static LinkedList<Bus> busses = new LinkedList();
    
    public static void mainMenu() {
        String menu = "\n\n";
        menu += "===============================================================================" + "\n";
        menu += "::    Bus Reservation System                                                 ::" + "\n";
        menu += "===============================================================================" + "\n";
        menu += "Welcome to bus reservation system\n";
        menu += "1. Add a bus\n";
        menu += "2. Reservation\n";
        menu += "3. Show buses information\n";
        menu += "4. Exit\n";
        menu += "Enter your choice (a number):";
        System.out.println(menu);
    }
    
    public static void printSubHeader(String subHeader) {
        String ans = "\n\n";
        ans += "===============================================================================" + "\n";
        ans += "::    Bus Reservation System                                                 ::" + "\n";
        ans += "===============================================================================" + "\n";
        ans += ":: " + subHeader + "\n";
        System.out.println(ans);
    }
    
    
    public static void waitInput() {
        String ans = "\n\n";
        ans += "                        Press any key to continue . . .      " + "\n";
        ans += "===============================================================================" + "\n";
        System.out.println(ans);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    public static void addBus() {
        printSubHeader("Add A New Bus");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the capacity of the bus: ");
        int capacity = scanner.nextInt();
        int busId = busses.size() + 1;
        Bus bus = new Bus(busId, capacity);
        busses.add(bus);
    }
    
    public static void reservation() {
        printSubHeader("Reserve A Seat");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the bus id: ");
        int busId = scanner.nextInt();
        
        System.out.println("Please enter the seat id: ");
        int seatId = scanner.nextInt();
        
        System.out.println("Please enter the passenger name: ");
        String passengerName = scanner.next();
        
        Bus bus = busses.get(busId - 1);
        bus.makeReservation(seatId, passengerName);
    }
    
    public static void showBusesInfo() {
        printSubHeader("Show Buses Info");
        
        for(int i=0; i<busses.size(); i++) {
            busses.get(i).printBusInfo();
        }
    }
    
    public static boolean userInputIsValid(String input) {
        try{
            int option = Integer.parseInt(input);
            if(option < 1 || option > 4) {
                throw new Exception("The input is invalid. ");
            }
        }
        catch (Exception e) {
            System.out.println("Error! The input is invalid, please try again. ");
            return false;
        }
        return true;
    }

    public static boolean performAction(String option) {

        switch (option) {
            case "1":
                addBus();
                break;
            case "2":
                reservation();
                break;
            case "3":
                showBusesInfo();
                break;
            case "4":
                return false;
        }
        return true;
        
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String option;
        while(true) {
            mainMenu();
            option = scanner.next();
            if(userInputIsValid(option)) {
                if(!performAction(option)) {
                    return;
                }
            }
            waitInput();
        }

    }
    
}
