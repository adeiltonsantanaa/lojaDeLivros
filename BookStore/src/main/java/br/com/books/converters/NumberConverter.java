package br.com.books.converters;

public class NumberConverter {

	public static Double convertToDouble(String number) {
		if (number == null)
			return 0D;
		String StNumber = number.replaceAll(",", ".");
		if (isNumeric(StNumber))
			return Double.parseDouble(StNumber);
		return Double.parseDouble(StNumber);
	}

	public static boolean isNumeric(String number) {
		if (number == null)
			return false;
		String stNumber = number.replaceAll(",", ".");
		return stNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
