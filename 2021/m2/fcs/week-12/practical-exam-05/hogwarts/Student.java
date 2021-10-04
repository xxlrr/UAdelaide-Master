/*==================================
Foundations of Computer Science
Student: Hongxing Hao
id: a1845302
Semester: M2
Year:2021
Practical Exam Number: 5
===================================*/

class Student {

    // attribute;
    String name;
    int age;
    int period;

    // constructor;
    public Student() {
        this.name = "unknown";
        this.age = 0;
        this.period = 0;
    }

    // constructor with parameter;
    public Student(String tmpName, int tmpAge, int tmpPeriod) {
        this.name = tmpName;
        this.age = tmpAge;
        this.period = tmpPeriod;
    }

    // write printStudent() method 
    public void printStudent() {
        String profile = "Printing student record\n";
        profile += "Name:   " + this.name + "\n";
        profile += "Age:    " + this.age + "\n";
        profile += "Period: " + this.period + "\n";
        System.out.println(profile);
    }

    // mutators
    void setName(String name) {
        this.name = name;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setPeriod(int period) {
        this.period = period;
    }

    // accessors
    String getName() {
        return this.name;
    }

    int getAge() {
        return this.age;
    }

    int getPeriod() {
        return this.period;
    }
}
