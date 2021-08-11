
import java.util.ArrayList;
import java.util.Scanner;

class Animal {
    private String name;
    private int age;
    private String favoriteFood;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }    
    
    Animal(String name, int age, String favoriteFood){
        this.name = name;
        this.age = age;
        this.favoriteFood = favoriteFood;
    }
    
    // to make a self-introduction
    public void sayHello() {
        System.out.printf(
                "Hello, I am %s. I am %d years old. My favorite food is %s.\n",
                this.name, this.age, this.favoriteFood);
    }
}
 
class Panda extends Animal{
 
    Panda(){
        this("Panda", 2, "Bamboos");
    }
    
    Panda(String name, int age, String favoriteFood) {
        super(name, age, favoriteFood);
    }
    
    public void play(){
        System.out.println("I am playing with my friends.");
    }
}
 
class Elephant extends Animal{
     
    Elephant(){
        this("Elephant", 5, "Bananas");
    }
    
    Elephant(String name, int age, String favoriteFood) {
        super(name, age, favoriteFood);
    }
    
    public void eat() {
        System.out.println("I am eating.");
    }
}

class Koala extends Animal{
 
    Koala(){
        this("Koala", 1, "Leaves");
    }
    
    Koala(String name, int age, String favoriteFood) {
        super(name, age, favoriteFood);
    }
    
    public void sleep() {
        System.out.println("I am sleeping.");
    }
}

class Lion extends Animal{
 
    Lion(){
        this("Lion", 3, "Meet");
    }
    
    Lion(String name, int age, String favoriteFood) {
        super(name, age, favoriteFood);
    }
}

class Zebra extends Animal{
    Zebra(){
        this("Zebra", 4, "Grass");
    }
    
    Zebra(String name, int age, String favoriteFood) {
        super(name, age, favoriteFood);
    }
}

class Zoo {
    Panda panda = new Panda();
    Elephant elephant = new Elephant();
    Koala koala = new Koala();
    Lion lion = new Lion();
    Zebra zebra = new Zebra();
}

public class ZooBuilder {
    private ArrayList<Panda> pandaSpace = new ArrayList<Panda>();
    private ArrayList<Elephant> elephantSpace = new ArrayList<Elephant>();
    private ArrayList<Koala> koalaSpace = new ArrayList<Koala>();
    private ArrayList<Lion> lionSpace = new ArrayList<Lion>();
    private ArrayList<Zebra> zebraSpace = new ArrayList<Zebra>();
    
    public void addAnimal(String animalType, String name, int age, String favoriteFood){
        switch(animalType) {
            case "panda":
                Panda newPanda = new Panda(name, age, favoriteFood);
                this.pandaSpace.add(newPanda);
                break;
            case "elephant":
                Elephant newElephant = new Elephant(name, age, favoriteFood);
                this.elephantSpace.add(newElephant);
                break;
            case "koala":
                Koala newKoala = new Koala(name, age, favoriteFood);
                this.koalaSpace.add(newKoala);
                break;
            case "lion":
                Lion newLion = new Lion(name, age, favoriteFood);
                this.lionSpace.add(newLion);
                break;
            case "zebra":
                Zebra newZebra = new Zebra(name, age, favoriteFood);
                this.zebraSpace.add(newZebra);
                break;
            default:
                System.out.println("Sorry, there is no space for this animal!");
        }
    }
    
    
    public static void main(String[] args){
        // tests Zoo class and some animal classes.
        Zoo zoo = new Zoo();
        zoo.panda.sayHello();
        zoo.panda.play();
        zoo.elephant.sayHello();
        zoo.elephant.eat();
        zoo.koala.sayHello();
        zoo.lion.sayHello();
        zoo.zebra.sayHello();
        
        System.out.println("========================================");
        
        // tests ZooBuilder class.
        ZooBuilder zooBuilder = new ZooBuilder();
        System.out.println("Hello, I built a zoo, and can you add some animals?");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter the type of the animal: ");
            String animalType = scanner.next();
            System.out.print("Please enter its name: ");
            String animalName = scanner.next();
            System.out.print("Please enter its age: ");
            int animalAge = scanner.nextInt();
            System.out.print("Please enter its favorite food: ");
            String favoriteFood = scanner.next();
            zooBuilder.addAnimal(animalType, animalName, animalAge, favoriteFood);
            
            System.out.print("Do you want to continue? (y/n): ");
            String isContinue = scanner.next();
            if(!"y".equals(isContinue)){
                break;
            }
        }
    }
    
}
