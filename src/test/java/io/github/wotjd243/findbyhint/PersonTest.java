package io.github.wotjd243.findbyhint;

import io.github.wotjd243.findbyhint.treasure.domain.Person;

import java.util.Arrays;
import java.util.List;

        public class PersonTest {
            public static void main(String[] args) {
                List<Person> persons = Arrays.asList(
                        new Person("mkyong",30),
                        new Person("jack",20),
                        new Person("lawremce",40)
                );

                Person result1 = persons.stream()
                        .filter(person -> "jack".equals(person.getName()))
                        .findAny()
                        .orElse(null);

                System.out.println(result1);

                Person result2 = persons.stream()
                        .filter(person -> "ahmook".equals(person.getName()))
                        .findAny()
                        .orElse(null);

                System.out.println(result2);
    }

}
