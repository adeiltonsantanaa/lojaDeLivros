package br.com.books.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.books.data.vo.v1.PersonVO;
import br.com.books.exceptions.RequiredObjectNullException;
import br.com.books.model.Person;
import br.com.books.repositories.PersonRepository;
import br.com.books.unittests.mapper.mock.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;

	@InjectMocks
	private PersonServices service;

	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(input);
	}

	@Test
	void testFindById() {
		Person person = input.mockEntity(1);
		person.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(person));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/v1/person>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());

	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var people = service.findAll();
		assertNotNull(people);
		assertEquals(14, people.size());

		var personOne = people.get(1);
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());
		System.out.println(personOne.toString());
		assertTrue(personOne.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", personOne.getAddress());
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Female", personOne.getGender());
		
		var personOTwo = people.get(2);
		assertNotNull(personOTwo);
		assertNotNull(personOTwo.getKey());
		assertNotNull(personOTwo.getLinks());
		System.out.println(personOTwo.toString());
		assertTrue(personOTwo.toString().contains("links: [</api/v1/person/2>;rel=\"self\"]"));
		assertEquals("Addres Test2", personOTwo.getAddress());
		assertEquals("First Name Test2", personOTwo.getFirstName());
		assertEquals("Last Name Test2", personOTwo.getLastName());
		assertEquals("Male", personOTwo.getGender());
	}

	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreateException() {
		Exception exception = assertThrows(RequiredObjectNullException.class, () -> {
			service.create(null);
		});

		String expectedMessage = "Não é possível persistir uma pessoa nula!";
		String atualMessage = exception.getMessage();

		assertTrue(atualMessage.contains(expectedMessage));

	}

	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/v1/person/1>;rel=\"self\"]"));
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

}
