package br.com.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequiredObjectNullException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequiredObjectNullException(String msg) {
		super(msg);
	}

}
