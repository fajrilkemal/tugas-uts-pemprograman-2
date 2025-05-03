package src;

public class Patient {
    private String name;
    private int age;
    private String condition;

    public Patient(String name, int age, String condition) {
        this.name = name;
        this.age = age;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return name + " (Usia: " + age + ", Kondisi: " + condition + ")";
    }
}
