package utils;

public class Converter {

	public static int NumberConverterBy(final String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("숫자만 입력 가능합니다. value = " + value);
		}
	}
}
