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
        Animal[] animals = new Animal[4];
        
        // create some animals
        animals[0] = new Dog();
        animals[1] = new Cat();
        animals[2] = new Wolf();
        animals[3] = new Kangaroo();
        
        // test every animal's <eat> and <makeNoise>.
        for(int i=0; i<animals.length; i++) {
            animals[i].eat();
            animals[i].makeNoise();
        }
        
        System.out.println("==============================");
        
        // give every animal a shot.
        Vet vet = new Vet();
        for(int i=0; i<animals.length; i++) {
            vet.giveShot(animals[i]);
        }
    }

}
