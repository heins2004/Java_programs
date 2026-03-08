abstract class Animal {
    public abstract void makesound();
    void walk(){
            System.out.println("Every animal walk ");
    }
    void eat(){
            System.out.println("Every animal eat ");
    }
}
class Dog extends Animal {
    public void makesound(){
        System.out.println("Dog Barks");
    }
}
public class Abstract{
    public static void main(String[] args) {
        Dog husky = new Dog();
        husky.makesound();
        husky.walk();
        husky.eat();
    }
}
