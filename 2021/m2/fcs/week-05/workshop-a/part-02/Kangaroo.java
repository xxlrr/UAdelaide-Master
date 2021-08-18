
class Kangaroo extends Animal {
    
    @Override
    void eat(){
        //the animal's preferred food source, meat or grass
        System.out.println("I am eating grass ...");
    }
    
    @Override
    void makeNoise(){
        //sound of noise
        System.out.println("tui tui ...");
    }
    
}
