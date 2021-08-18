
class Wolf extends Canine {
    
    @Override
    void eat(){
        //the animal's preferred food source, meat or grass
        System.out.println("I am eating meet ...");
    }
    
    @Override
    void makeNoise(){
        //sound of noise
        System.out.println("awoo awoo ...");
    }
    
    @Override
    void buryFood(){
        System.out.println("I buried some meet!");
    }
    
}
