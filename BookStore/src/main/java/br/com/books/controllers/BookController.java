package br.com.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.books.data.vo.v1.BookVO;
import br.com.books.service.BookServices;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	private BookServices bookServices;

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public BookVO findById(@PathVariable(value = "id") Long id) {
		return bookServices.findById(id);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<BookVO> findAll() {
		return bookServices.findAll();
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public BookVO create(@RequestBody BookVO bookVO) {
		return bookServices.create(bookVO);
	}

	@PutMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public BookVO update(@RequestBody BookVO bookVO) {
		return bookServices.update(bookVO);
	}

	@DeleteMapping(value = "/{id}")
	public void update(@PathVariable(value = "id") Long id) {
		bookServices.delete(id);
	}

}
