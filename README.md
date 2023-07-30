# Java_OOP_Lesson_4
Урок 4. ООП: Обобщения. ч1
public class Homework {

    /**
     * Есть класс Фрукта. У фрукта есть вес. 
     * Есть 3 наследника: апельсин, яблоко и золотое яблоко.
     * 
     * Нужно создать класс "Коробка с фруктами", в которую можно класть фрукты какого-то типа. 
     * После создания коробка пустая. В нее можно добавлять фрукты с помощью метода add.
     * У коробки можно узнать сумарный вес с помощью метода #getWeight. 
     * Содержимое коробки можно пересыпать в другую коробку. При этом исходная коробка очищается, а вторая наполняется.
     */
    public static void main(String[] args) {
        Box<String> stringBox = new Box<>(); // не должно компилироваться

        Box<Orange> orangeBox = new Box<>();
        orangeBox.add(new Orange(1));
        orangeBox.add(new Orange(2));
        orangeBox.add(new Apple(2));       // не должно компилироваться
        orangeBox.add(new GoldenApple(2)); // не должно компилироваться

        System.out.println(orangeBox.getWeight()); // 3

        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple(1));
        appleBox.add(new GoldenApple(2)); // допустимый вариант
        System.out.println(appleBox.getWeight()); // 3

        Box<GoldenApple> goldenAppleBox = new Box<>();
        goldenAppleBox.add(new GoldenApple(5)); // допустимый вариант
        goldenAppleBox.add(new Apple(5));       // не должно компилироваться
        System.out.println(goldenAppleBox.getWeight()); // 5

        goldenAppleBox.moveTo(appleBox); // допустимый вариант 
        appleBox.moveTo(goldenAppleBox); // не должно компилироваться
        orangeBox.moveTo(appleBox);      // не должно компилироваться

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox.moveTo(orangeBox2);
        System.out.println(orangeBox.getWeight()); // 0
        System.out.println(orangeBox2.getWeight()); // 3
    }

    // FIXME: 06.07.2023 Добавить нужный дженерик.
    static class Box {

        // FIXME: 06.07.2023 Реализовать методы согласно заданию.

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
