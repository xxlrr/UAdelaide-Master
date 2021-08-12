//==================================
// Foundations of Computer Science
// Student: Hongxing Hao
// id: a1845302
// Semester: m2
// Year: 2021
// Practical Number: Problem01
//===================================
public class Problem01 {

    public static void main(String[] args) {
        // TODO code application logic here
    }

}

class Person{
    private String givenName;
    private String lastName;
    private int age;
    private String gender;
    private String citizenship;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }
    
    // Basic Constructor
    public Person() {
    }

    // Paramatic Constructor
    public Person(String givenName, String lastName, int age, String gender, String citizenship) {
        this.givenName = givenName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.citizenship = citizenship;
    }
    
}

class Shape {

    private double width;
    private double height;
    private double radius;
    private double length;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // Basic Constructor
    public Shape() {
    }
    
    // Paramatic Constructor
    public Shape(double width, double height, double radius, double length) {
        this.width = width;
        this.height = height;
        this.radius = radius;
        this.length = length;
    }
    
}

class Book{
    private String title;
    private int year;
    private String publish;
    private int pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    // Basic Constructor
    public Book() {
    }

    // Paramatic Constructor
    public Book(String title, int year, String publish, int pages) {
        this.title = title;
        this.year = year;
        this.publish = publish;
        this.pages = pages;
    }
        
}

class Animal{
    private String name;
    private double weight;
    private String favouriteFood;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getFavouriteFood() {
        return favouriteFood;
    }

    public void setFavouriteFood(String favouriteFood) {
        this.favouriteFood = favouriteFood;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Basic Constructor
    public Animal() {
    }

    // Paramatic Constructor
    public Animal(String name, double weight, String favouriteFood, int age) {
        this.name = name;
        this.weight = weight;
        this.favouriteFood = favouriteFood;
        this.age = age;
    }
    
}