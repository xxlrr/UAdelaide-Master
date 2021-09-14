/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author haohongxing
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TaxiRank tr = new TaxiRank();
        tr.add(new Taxi(1));
        tr.add(new Taxi(2));
        tr.add(new Taxi(3));
        tr.add(new Taxi(4));
        tr.add(new Taxi(5));
        
        tr.list();
        
        Taxi taxi = tr.get();
        System.out.println("Get TaxiID: " + taxi.taxiID);
        
        tr.list();
    }
    
}
