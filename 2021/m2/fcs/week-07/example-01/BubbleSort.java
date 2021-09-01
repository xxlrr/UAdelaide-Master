
import java.util.Random;


public class BubbleSort {
    
    // controls weather to sort in descending order.
    private boolean descending = false;

    public boolean isDescending() {
        return descending;
    }

    public void setDescending(boolean descending) {
        this.descending = descending;
    }
    
    public int[] sortInt(int [] arr) {        
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if ((!this.isDescending() && arr[j] > arr[j+1])
                        || (this.isDescending() && arr[j] < arr[j+1]))
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    
    public float[] sortFloat(float [] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if ((!this.isDescending() && arr[j] > arr[j+1])
                        || (this.isDescending() && arr[j] < arr[j+1]))
                {
                    // swap arr[j+1] and arr[j]
                    float temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    
    public String[] sortString(String [] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {       
                if ((!this.isDescending() && arr[j].compareTo(arr[j+1]) > 0)
                        || (this.isDescending() && arr[j].compareTo(arr[j+1]) < 0))
                {
                    // swap arr[j+1] and arr[j]
                    String temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }
    
    
    public static void main(String[] args) {
        int count = 10;
        
        Student[] students = new Student[count];
        
        
        for(int i=0; i<students.length; i++) {
            students[i] = new Student();
            students[i].printInfo();
        }       
    }
    
}


class Student{
    
    private String name;
    private float score;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // randomly generate student information
    public Student() {
        Random ran = new Random();
        int id = ran.nextInt(10000);
        this.name = "Student_" + id;
        this.score = ran.nextFloat() * 100;
        this.age = ran.nextInt(80);
    }

    public Student(String name, float score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }
    
    // output the student information
    public void printInfo() {
        System.out.println("Student name: " + this.getName() + "; "
                + "Age: " + this.getAge() + "; "
                + "Score: " + this.getScore());
    }
}
