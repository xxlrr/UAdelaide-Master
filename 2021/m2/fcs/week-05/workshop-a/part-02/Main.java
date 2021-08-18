//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Animal
//===================================
public class Main {
    public static void main(String[] args) {
        // tests animal
        Animal animal;
        
        System.out.println("I am a cat!");
        animal = new Cat();
        animal.makeNoise();
        animal.eat();
        System.out.println();
        
        System.out.println("I am a kangaroo!");
        animal = new Kangaroo();
        animal.makeNoise();
        animal.eat();
        System.out.println();
        
        // tests canine
        Canine canine;
        
        System.out.println("I am a dog!");
        canine = new Dog();
        canine.makeNoise();
        canine.eat();
        canine.buryFood();
        System.out.println();
        
        System.out.println("I am a wolf!");
        canine = new Wolf();
        canine.makeNoise();
        canine.eat();
        canine.buryFood();
        System.out.println();

    }
}
