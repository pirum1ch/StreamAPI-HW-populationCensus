import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long underage = persons.stream()
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних (т.е. людей младше 18 лет): " + underage);

        List conscript = persons.stream()
                .filter(value -> value.getAge() > 18)
                .filter(value -> value.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников (т.е. мужчин от 18 и до 27 лет): " + conscript);

        List sortedListWorkablepopulation = persons.stream()
                .filter(x -> x.getEducation().compareTo(Education.HIGHER) == 0)
                .filter(value -> value.getAge() >= 18)
                .filter(value -> {
                    if (value.getSex().compareTo(Sex.MAN) == 0) {
                        return value.getAge() < 60;
                    } else {
                        return value.getAge() < 65;
                    }
                })
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Отсортированный по фамилии список потенциально работоспособных людей с высшим образованием: " + sortedListWorkablepopulation);
    }

}