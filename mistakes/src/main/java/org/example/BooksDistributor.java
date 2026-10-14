package org.example;

import org.example.model.Book;
import org.example.model.Person;
import org.example.model.Samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BooksDistributor {

    public void distributeBooksThroughPeople() {
        List<Person> people = new ArrayList<>(Samples.getSampleListOfPeople());
        Collections.shuffle(people);

        List<Book> availableBooks = Samples.getAvailableBooks();
        for (int i = 0; i < availableBooks.size(); i++) {
            Book book = availableBooks.get(i);
            Person person = people.get(i);

            person.getBooks().add(book);
            book.setOwner(person);
        }

        availableBooks.clear();
    }
}




