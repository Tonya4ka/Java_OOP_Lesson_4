import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Box<String> stringBox = new Box<>(); // won't compile

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange(1));
        orangeBox.add(new Orange(2));
        // orangeBox.add(new Apple(2));       // won't compile
        // orangeBox.add(new GoldenApple(2)); // won't compile

        System.out.println(orangeBox.getWeight()); // 3

        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple(1));
        appleBox.add(new GoldenApple(2)); // acceptable
        System.out.println(appleBox.getWeight()); // 3

        Box<GoldenApple> goldenAppleBox = new Box<>();
        goldenAppleBox.add(new GoldenApple(5)); // acceptable
        // goldenAppleBox.add(new Apple(5));       // won't compile
        System.out.println(goldenAppleBox.getWeight()); // 5

        goldenAppleBox.moveTo(appleBox); // acceptable
        // appleBox.moveTo(goldenAppleBox); // won't compile
        // orangeBox.moveTo(appleBox);     // won't compile

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox.moveTo(orangeBox2);
        System.out.println(orangeBox.getWeight()); // 0
        System.out.println(orangeBox2.getWeight()); // 3
    }

    static class Box<T extends Fruit> {
        private final List<T> contents;

        public Box() {
            contents = new ArrayList<>();
        }

        public void add(T fruit) {
            contents.add(fruit);
        }

        public int getWeight() {
            return contents.stream().mapToInt(Fruit::getWeight).sum();
        }

        public void moveTo(Box<? super T> other) {
            other.contents.addAll(this.contents);
            this.contents.clear();
        }
    }

    static class Fruit {
        private final int weight;

        public Fruit(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

    static class Orange extends Fruit {
        public Orange(int weight) {
            super(weight);
        }
    }

    static class Apple extends Fruit {
        public Apple(int weight) {
            super(weight);
        }
    }

    static class GoldenApple extends Apple {
        public GoldenApple(int weight) {
            super(weight);
        }
    }
}