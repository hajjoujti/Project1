package fr.eql.ai109.projet1;

import javafx.util.StringConverter;
/**
 * <p>{@link StringConverter} implementation for {@link Integer}
 * (and int primitive) values.</p>
 * @since JavaFX 2.1
 */
public class IntegerStringConverterCustomized extends StringConverter<Integer> {

	/** {@inheritDoc} */
	public Integer fromString(String value) {
		// If the specified value is null or zero-length, return null
		if (value == null) {
			return null;
		}

		value = value.trim();

		if (value.length() < 1) {
			return null;
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/** {@inheritDoc} */
	public String toString(Integer value) {
		// If the specified value is null, return a zero-length String
		if (value == null) {
			return "";
		}
		 return (Integer.toString(((Integer)value).intValue()));
	}
}