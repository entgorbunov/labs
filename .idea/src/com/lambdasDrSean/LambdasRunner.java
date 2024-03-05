
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.function.Function;


public class LambdasRunner {
    public static void main(String[] args) {
        LambdasRunner lr = new LambdasRunner();
        lr.consumer();
        lr.supplier();
        lr.predicate();
        lr.function();
        var listPeople = getPeople();
        sortAge(listPeople);
        sortName(listPeople);
        sortHeight(listPeople);
    }


    public void consumer() {
        Printable<String> lambda = s -> System.out.println(s);
        lambda.print("Printable lambda");

        Consumer<String> consumerLambda = s -> System.out.println(s);
        consumerLambda.accept("Consumer lambda");

        Consumer<String> consumerMethodRef = System.out::println;
        consumerMethodRef.accept("Consumer method reference");
    }

    public void supplier() {
        Retrievable<Integer> retrievableLambda = () -> 77;
        System.out.println(retrievableLambda.retrieve());

        Supplier<Integer> supplierLambda = () -> 77;
        System.out.println(supplierLambda.get());
    }

    public void predicate() {
        Evaluate<Integer> evaluateLambda = number -> number < 0;
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
        System.out.println(check(new Person("Mike", 33, 1.8), person -> person.getAge() >= 18)); // Is adult
        System.out.println(check(new Person("Ann", 13, 1.4), person -> person.getAge() >= 18)); // Is adult
    }

    public static  <T> boolean check(T input, Predicate<T> predicate) {
        return predicate.test(input);
    }

    public void function() {
        Functionable<Integer, String > functionableLambda = number -> "Number is: " + number;
        System.out.println(functionableLambda.function(25));

        Function<Integer, String> functionLambda = number -> "Number is: " + number;
        System.out.println(functionLambda.apply(25));
    }




    // Исправленные методы сортировки
    private static void sortAge(List<Person> listPeople) {
        listPeople.sort(Comparator.comparingInt(person -> person.getAge()));
        System.out.println("Sorted by age:");
        listPeople.forEach(System.out::println);
    }

    private static void sortHeight(List<Person> listPeople) {
        listPeople.sort(Comparator.comparingDouble(person -> person.getHeight()));
        System.out.println("Sorted by height:");
        listPeople.forEach(System.out::println);
    }

    private static void sortName(List<Person> listPeople) {
        listPeople.sort(Comparator.comparing(person -> person.getName()));
        System.out.println("Sorted by name:");
        listPeople.forEach(System.out::println);
    }


}
