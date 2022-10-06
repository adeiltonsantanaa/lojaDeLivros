package br.com.books.service;

import org.springframework.stereotype.Service;

import br.com.books.converters.NumberConverter;
import br.com.books.exceptions.UnsupportedMathOperationException;

@Service
public class MathService {

	public Double somar(String num1, String num2) throws Exception {
		if(!NumberConverter.isNumeric(num1) || (!NumberConverter.isNumeric(num2))) {
			throw new UnsupportedMathOperationException("Setar só valores númericos");
		}
		return NumberConverter.convertToDouble(num1) + NumberConverter.convertToDouble(num2);
	}
	
	
	public Double subtrair(String num1, String num2) throws Exception {
		if(!NumberConverter.isNumeric(num1) || (!NumberConverter.isNumeric(num2))) {
			throw new UnsupportedMathOperationException("Setar só valores númericos");
		}
		return NumberConverter.convertToDouble(num1) - NumberConverter.convertToDouble(num2);
	}
	
	
	public Double dividir(String num1, String num2) throws Exception {
		if(!NumberConverter.isNumeric(num1) || (!NumberConverter.isNumeric(num2))) {
			throw new UnsupportedMathOperationException("Setar só valores númericos");
		}
		return NumberConverter.convertToDouble(num1) / NumberConverter.convertToDouble(num2);
	}
	
	
	public Double multiplicar(String num1, String num2) throws Exception {
		if(!NumberConverter.isNumeric(num1) || (!NumberConverter.isNumeric(num2))) {
			throw new UnsupportedMathOperationException("Setar só valores númericos");
		}
		return NumberConverter.convertToDouble(num1) * NumberConverter.convertToDouble(num2);
	}
	
	
	public Double med(String num1, String num2) throws Exception {
		
		if(!NumberConverter.isNumeric(num1) || (!NumberConverter.isNumeric(num2))) {
			throw new UnsupportedMathOperationException("Setar só valores númericos");
		}
		return (NumberConverter.convertToDouble(num1) + NumberConverter.convertToDouble(num2))/2;
	}
	
	public Double raiz(String num1) throws Exception {
		
		if(!NumberConverter.isNumeric(num1)) {
			throw new UnsupportedMathOperationException("Setar só valores númericos");
		}
		Double n1 = NumberConverter.convertToDouble(num1);
		return Math.sqrt(n1);
	}
	

}
