package com.lambdasDrSean;

import java.util.ArrayList;
import java.util.List;

import com.lambdasDrSean.Person;

public class Utility {

    public List<Person> getPeople() {

        com.lambdasDrSean.mike = new Person("Mike", 33, 1.8);
        List<Person> result = new ArrayList<>();

        result.add(mike);

        result.add(new Person("Mary", 25, 1.4));

        result.add(new Person("Alan", 34, 1.7));

        result.add(new Person("Zoe", 30, 1.5));

        return result;

    };

}
