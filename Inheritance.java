class Animal {
    String name;
    void walk(){
            System.out.println("Every animal walk and most of them have four legs");
    }
    void eat(){
            System.out.println("Every animal eat to gain energy");
    }
    void display(){
            System.out.println("Display function in parent class");
            walk();
            eat();
    }
}
class Dog extends Animal {
    void smell(){
        System.out.println("Dog has the ability to smell things better");
    }
    void display(){
        System.out.println("Display function in child class");
        walk();
        eat();
    }
}
public class Inheritance{
    public static void main(String[] args) {
        Dog husky = new Dog();
        husky.smell();
        husky.display();
    }
}
