import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.function.Function;

interface Printable {
    void print(String message);

}
interface Retrievable {
    Integer retrieve();

}
interface Evaluate {
    boolean evaluate(Integer number);

}
interface Functionable {
    String function(Integer number);

}

public class FunctionalInterfacesDemo {

    private static void accept(Person person) {
        System.out.println(person.name + " - " + person.age);
    }

    private static int compareInt(Person person) {
        return person.age;
    }

    private static double applyAsDouble(Person person) {
        return person.height;
    }

    private static void accept2(Person person) {
        System.out.println(person.name + " - " + person.height);
    }

    private static String apply(Person person) {
        return person.name;
    }

    private static void accept3(Person person) {
        System.out.println(person.name);
    }

    static class Person {
        String name;
        int age;
        double height;

        public Person(String name, int age, double height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + ", height=" + height + '}';
        }
    }



    public static void main(String[] args) {
        consumer();
        supplier();
        predicate();
        function();
        var listPeople = getPeople();
        sortAge();
        sortName();
        sortHeight();
    }

    public static void consumer() {
        Printable printableLambda = message -> System.out.println(message);
        printableLambda.print("Printable lambda");

        Consumer<String> consumerLambda = message -> System.out.println(message);
        consumerLambda.accept("Consumer lambda");

        Consumer<String> consumerMethodRef = System.out::println;
        consumerMethodRef.accept("Consumer method reference");
    }

    public static void supplier() {
        Retrievable retrievableLambda = () -> 77;
        System.out.println(retrievableLambda.retrieve());

        Supplier<Integer> supplierLambda = () -> 77;
        System.out.println(supplierLambda.get());
    }

    public static void predicate() {
        Evaluate evaluateLambda = number -> number < 0;
        System.out.println(evaluateLambda.evaluate(-1));
        System.out.println(evaluateLambda.evaluate(1));

        Predicate<Integer> predicateLambda = number -> number < 0;
        System.out.println(predicateLambda.test(-1));
        System.out.println(predicateLambda.test(1));

        System.out.println(check(4, num -> num % 2 == 0)); // Is number even
        System.out.println(check(7, num -> num % 2 == 0)); // Is number even

        System.out.println(check("Mr. Joe Bloggs", str -> str.startsWith("Mr."))); // String begins with "Mr."
        System.out.println(check("Ms. Ann Bloggs", str -> str.startsWith("Mr."))); // String begins with "Mr."

        // Assuming a simple Person class with age and height for the adult check
        // This part is illustrative and may require actual implementation of Person class and attributes
        System.out.println(check(new Person("Mike", 33, 1.8), person -> person.age >= 18)); // Is adult
        System.out.println(check(new Person("Ann", 13, 1.4), person -> person.age >= 18)); // Is adult
    }

    public static <T> boolean check(T input, Predicate<T> predicate) {
        return predicate.test(input);
    }

    public static void function() {
        Functionable functionableLambda = number -> "Number is: " + number;
        System.out.println(functionableLambda.function(25));

        Function<Integer, String> functionLambda = number -> "Number is: " + number;
        System.out.println(functionLambda.apply(25));
    }



    public static List<Person> getPeople() {

        List<Person> result = new ArrayList<>();

        result.add(new Person("Mike", 33, 1.8));

        result.add(new Person("Mary", 25, 1.4));

        result.add(new Person("Alan", 34, 1.7));

        result.add(new Person("Zoe", 30, 1.5));

        return result;

    }

    // Исправленные методы сортировки
    private static void sortAge() {
        List<Person> list = getPeople();
        list.sort(Comparator.comparingInt(FunctionalInterfacesDemo::compareInt));
        System.out.println("Sorted by age:");
        list.forEach(FunctionalInterfacesDemo::accept);
    }

    private static void sortHeight() {
        List<Person> list = getPeople();
        list.sort(Comparator.comparingDouble(FunctionalInterfacesDemo::applyAsDouble));
        System.out.println("Sorted by height:");
        list.forEach(FunctionalInterfacesDemo::accept2);
    }

    private static void sortName() {
        List<Person> list = getPeople();
        list.sort(Comparator.comparing(FunctionalInterfacesDemo::apply));
        System.out.println("Sorted by name:");
        list.forEach(FunctionalInterfacesDemo::accept3);
    }


}
