package br.com.books.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.books.controllers.BookController;
import br.com.books.data.vo.v1.BookVO;
import br.com.books.exceptions.RequiredObjectNullException;
import br.com.books.exceptions.ResourceNotFoundException;
import br.com.books.mapper.DozerMapper;
import br.com.books.model.Book;
import br.com.books.repositories.BookRepository;

@Service
public class BookServices {

	@Autowired
	private BookRepository bookRepository;

	public BookVO findById(Long id) {
		var book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado. " + id));
		BookVO vo = DozerMapper.parseObject(book, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findAll()).withSelfRel());
		return vo;
	}

	public List<BookVO> findAll() {
		var books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
		books.stream().forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		return books;
	}

	public BookVO create(BookVO bookVO) {
		if (bookVO == null) {
			throw new RequiredObjectNullException("Não é possível persistir um livro nulo!");
		}
		var bookSave = DozerMapper.parseObject(bookVO, Book.class);
		BookVO vo = DozerMapper.parseObject(bookRepository.save(bookSave), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public BookVO update(BookVO bookVO) {
		var book = DozerMapper.parseObject(bookVO, Book.class);
		var book2 = bookRepository.findById(book.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + book.getId()));
		book2.setAuthor(book.getAuthor());
		book2.setLaunchDate(book.getLaunchDate());
		book2.setPrice(book.getPrice());
		book2.setTitle(book.getTitle());
		BookVO vo = DozerMapper.parseObject(bookRepository.save(book2), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada. " + id));
		bookRepository.deleteById(id);
	}

}
