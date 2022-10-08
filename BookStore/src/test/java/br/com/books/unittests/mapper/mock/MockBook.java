package br.com.books.unittests.mapper.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.books.data.vo.v1.BookVO;
import br.com.books.model.Book;

public class MockBook {

	public Book mockEntity() {
		return mockEntity(0);
	}

	public BookVO mockVO() {
		return mockVO(0);
	}

	public List<Book> mockEntityList() {
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 14; i++) {
			books.add(mockEntity(i));
		}
		return books;
	}

	public List<BookVO> mockVOList() {
		List<BookVO> books = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			books.add(mockVO(i));
		}
		return books;
	}

	public Book mockEntity(Integer number) {
		Book book = new Book();
		book.setAuthor("Author Test" + number);
		book.setLaunchDate(LocalDate.now());
		book.setPrice(20.0F);
		book.setId(number.longValue());
		book.setTitle("Title Test" + number);
		return book;
	}

	public BookVO mockVO(Integer number) {
		BookVO bookVO = new BookVO();
		bookVO.setAuthor("Author Test" + number);
		bookVO.setLaunchDate(LocalDate.now());
		bookVO.setPrice(20.0F);
		bookVO.setKey(number.longValue());
		bookVO.setTitle("Title Test" + number);
		return bookVO;
	}

}
