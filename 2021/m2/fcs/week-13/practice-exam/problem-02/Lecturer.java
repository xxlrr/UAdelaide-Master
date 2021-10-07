
class Lecturer extends Person {
    public String discipline;
    public int salary;

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

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

    public Lecturer(String discipline, int salary, String name, int age) {
        super(name, age);
        this.discipline = discipline;
        this.salary = salary;
    }
    
}
