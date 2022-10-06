package br.com.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.books.data.vo.v1.PersonVO;
import br.com.books.exceptions.ResourceNotFoundException;
import br.com.books.mapper.DozerMapper;
import br.com.books.model.Person;
import br.com.books.repositories.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	private PersonRepository personRepository;

	public PersonVO findById(Long id) {
		var person = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + id));
		return DozerMapper.parseObject(person, PersonVO.class);
	}

	public List<PersonVO> findAll() {
		return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		var personSave = DozerMapper.parseObject(person, Person.class);
		return DozerMapper.parseObject(personRepository.save(personSave), PersonVO.class);
	}

	public PersonVO update(PersonVO personVO) {
		var person = DozerMapper.parseObject(personVO, Person.class);
		var person2 = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + person.getId()));
		person2.setFirstName(person.getFirstName());
		person2.setLastName(person.getLastName());
		person2.setAddress(person.getAddress());
		person2.setGender(person.getGender());
		return DozerMapper.parseObject(personRepository.save(person2), PersonVO.class);
	}

	public void delete(Long id) {
		personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + id));
		personRepository.deleteById(id);
	}

}
