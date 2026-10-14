package org.example;

import org.example.model.Book;
import org.example.model.Gender;
import org.example.model.Person;
import org.example.model.Samples;

import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {

        new BooksDistributor().distributeBooksThroughPeople();

        if (!isBooksCollectionEmpty()) {
            System.out.println("po przydzieleniu książek, kolekcja książek powinna być pusta, a nie jest :(");
            return;
        }

        if (!peopleWithoutBooksExists()) {
            System.out.println("Każda osoba posiada książkę, a przecież kolekcja książek jest mniejsza od kolekcji osób.");
            return;
        }

        printPeopleWithBooks();

        Map<Gender, List<Person>> aggregatePeopleByGander = new PeopleAggregator().aggregatePeopleByGender();
        if (malesExistsInCollection(aggregatePeopleByGander.get(Gender.FEMALE))) {
            System.out.println("W kolekcji kobiet znajdują się mężczyźni.");
            return;
        }
        if (femalesExistsInCollection(aggregatePeopleByGander.get(Gender.MALE))) {
            System.out.println("W kolekcji mężczyzn znajdują się kobiety.");
            return;
        }

        List<Person> peopleWithBooks = new PeopleCleaner().getPeopleWithBooksOnly(Samples.getSampleListOfPeople());
        if (!sizeOfPeopleWithBooksEqualsSizeOfAllBooks(peopleWithBooks)) {
            System.out.println("osób w liście powinno byc tyle samo co wszystkich książek");
            return;
        }

        System.out.println("Udało się. Wszystko działa :)");
    }

    /* KODU PONIŻSZYCH METOD NIE ZMIENIAMY ! */

    private static boolean sizeOfPeopleWithBooksEqualsSizeOfAllBooks(List<Person> peopleWithBooks) {
        return peopleWithBooks.size() == Samples.getAllBooks().size();
    }

    private static boolean isBooksCollectionEmpty() {
        return Samples.getAvailableBooks().isEmpty();
    }

    private static boolean peopleWithoutBooksExists() {
        return Samples.getSampleListOfPeople().stream().anyMatch(person -> person.getBooks().isEmpty());
    }

    private static void printPeopleWithBooks() {
        for (Book book : Samples.getAllBooks()) {
            System.out.println(
                    new StringBuilder()
                            .append(book.getOwner().getName())
                            .append(" ")
                            .append(book.getOwner().getSurname())
                            .append(" ma książkę: ")
                            .append(book.getTitle())
            );
        }
    }

    private static boolean malesExistsInCollection(List<Person> people) {
        return people.stream().anyMatch(person -> person.getGender() == Gender.MALE);
    }

    private static boolean femalesExistsInCollection(List<Person> people) {
        return people.stream().anyMatch(person -> person.getGender() == Gender.FEMALE);
    }
}
