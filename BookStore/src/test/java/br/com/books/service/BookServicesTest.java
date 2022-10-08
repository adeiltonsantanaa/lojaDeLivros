package br.com.books.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import br.com.books.data.vo.v1.BookVO;
import br.com.books.model.Book;
import br.com.books.repositories.BookRepository;
import br.com.books.unittests.mapper.mock.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;

	@InjectMocks
	private BookServices service;

	@Mock
	BookRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(input);
	}

	@Test
	void testFindById() {
		Book book = input.mockEntity(1);
		book.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(book));

		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/v1/book>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		equals(LocalDate.now());
		assertEquals("Title Test1", result.getTitle());

	}

	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var books = service.findAll();
		assertNotNull(books);
		assertEquals(14, books.size());

		var bookOne = books.get(1);
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());

		assertTrue(bookOne.toString().contains("links: [</api/v1/book/1>;rel=\"self\"]"));
		assertEquals("Author Test1", bookOne.getAuthor());
		equals(LocalDate.now());
		assertEquals("Title Test1", bookOne.getTitle());

		var bookTwo = books.get(2);
		assertNotNull(bookTwo);
		assertNotNull(bookTwo.getKey());
		assertNotNull(bookTwo.getLinks());

		System.out.println(bookTwo.toString());
		assertTrue(bookTwo.toString().contains("links: [</api/v1/book/2>;rel=\"self\"]"));
		assertEquals("Author Test2", bookTwo.getAuthor());
		equals(LocalDate.now());
		assertEquals("Title Test2", bookTwo.getTitle());

	}

	@Test
	void testCreate() {
		Book book = input.mockEntity(1);
		Book persisted = book;
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(book)).thenReturn(persisted);
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/v1/book/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		equals(LocalDate.now());
		assertEquals("Title Test1", result.getTitle());

	}

	@Test
	void testUpdate() {
		Book book = input.mockEntity(1);
		Book persisted = book;
		persisted.setId(1L);

		BookVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(book));
		when(repository.save(book)).thenReturn(persisted);
		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/v1/book/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		equals(LocalDate.now());
		assertEquals("Title Test1", result.getTitle());
	}

	@Test
	void testDelete() {
		Book book = input.mockEntity(1);
		book.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(book));

		service.delete(1L);
	}

}
