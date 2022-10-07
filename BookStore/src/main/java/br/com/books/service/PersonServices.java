package br.com.books.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.books.controllers.PersonController;
import br.com.books.data.vo.v1.PersonVO;
import br.com.books.exceptions.RequiredObjectNullException;
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
		PersonVO vo = DozerMapper.parseObject(person, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findAll()).withSelfRel());
		return vo;
	}

	public List<PersonVO> findAll() {
		var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO create(PersonVO person) {
		if (person == null) {
			throw new RequiredObjectNullException("Não é possível persistir uma pessoa nula!");
		}
		var personSave = DozerMapper.parseObject(person, Person.class);
		PersonVO vo = DozerMapper.parseObject(personRepository.save(personSave), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public PersonVO update(PersonVO personVO) {
		var person = DozerMapper.parseObject(personVO, Person.class);
		var person2 = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + person.getId()));
		person2.setFirstName(person.getFirstName());
		person2.setLastName(person.getLastName());
		person2.setAddress(person.getAddress());
		person2.setGender(person.getGender());
		PersonVO vo = DozerMapper.parseObject(personRepository.save(person2), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + id));
		personRepository.deleteById(id);
	}

}
