package br.com.books.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.books.model.Person;

@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}

	public Person create(Person person) {
		Person person2 = new Person();
		person2.setId(counter.incrementAndGet());
		person2.setFirstName(person.getFirstName());
		person2.setLastName(person.getLastName());
		person2.setAddress(person.getAddress());
		person2.setGender(person.getGender());
		return person2;
	}

	public Person update(Person person) {
		Person person2 = new Person();
		person2.setId(counter.incrementAndGet());
		person2.setFirstName(person.getFirstName());
		person2.setLastName(person.getLastName());
		person2.setAddress(person.getAddress());
		person2.setGender(person.getGender());
		return person2;
	}

	public void delete(String id) {
		logger.info("Person deleted");
	}

	public Person findById(String id) {
		logger.info("finding one person");
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Adeilton");
		person.setLastName("Santana");
		person.setAddress("Brasíia - DF");
		person.setGender("Male");
		return person;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Adeilton " + i);
		person.setLastName("Santana" + i);
		person.setAddress("Brasíia - DF " + i);
		person.setGender("Male" + i);
		return person;
	}
}
