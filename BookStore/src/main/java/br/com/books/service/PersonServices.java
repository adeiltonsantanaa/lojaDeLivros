package br.com.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.books.exceptions.ResourceNotFoundException;
import br.com.books.model.Person;
import br.com.books.repositories.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	private PersonRepository personRepository;

	public Person findById(Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + id));
	}

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person create(Person person) {
		return personRepository.save(person);
	}

	public Person update(Person person) {
		Person person2 = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + person.getId()));
		person2.setFirstName(person.getFirstName());
		person2.setLastName(person.getLastName());
		person2.setAddress(person.getAddress());
		person2.setGender(person.getGender());
		return personRepository.save(person2);
	}

	public void delete(Long id) {
		personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + id));
		personRepository.deleteById(id);
	}

}
