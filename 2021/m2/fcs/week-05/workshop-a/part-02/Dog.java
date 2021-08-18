
class Dog extends Canine {
    
    @Override
    void eat(){
        //the animal's preferred food source, meat or grass
        System.out.println("I am eating a bone ...");
    }
    
    @Override
    void makeNoise(){
        //sound of noise
        System.out.println("woof woof ...");
    }
      
    @Override
    void buryFood(){
        System.out.println("I buried some bones!");
    }
    
}
