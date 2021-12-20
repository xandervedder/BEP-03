package nl.softwarestrijders.waiter.customer.core.domain;

import nl.softwarestrijders.waiter.customer.core.domain.exceptions.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Class containing {@link Address} data like the house number, addition, street,
 * postal code and city.
 */
@Document(collection = "address")
public class Address {
	@Id
	private UUID id;
	private int houseNumber;
	private String addition;
	private String street;
	private String postalCode;
	private String city;

	/**
	 * Constructor of the {@link Address} class. This constructor calls
	 * the class' setter methods instead of setting the variables directly,
	 * this is because the setters contain logic to verify input data.
	 *
	 * @param houseNumber house number of the address.
	 * @param addition    addition of the house number.
	 * @param street      street of the address.
	 * @param postalCode  postal code of the address.
	 * @param city        city of the address.
	 * @see #setHouseNumber(int) setHouseNumber()
	 * @see #setAddition(String) setAddition()
	 * @see #setStreet(String) setStreet()
	 * @see #setPostalCode(String) setPostalCode()
	 * @see #setCity(String) setCity()
	 */
	public Address(int houseNumber, String addition, String street, String postalCode, String city) {
		this.id = UUID.randomUUID();
		setHouseNumber(houseNumber);
		setAddition(addition);
		setStreet(street);
		setPostalCode(postalCode);
		setCity(city);
	}

	/**
	 * Function that updates the house number of the {@link Address}.
	 * It also checks if the number is a digit and throws an
	 * {@link InvalidHouseNumberException exception} if not.
	 *
	 * @param number the to-be set house number.
	 * @throws InvalidHouseNumberException when the input number is not a digit,
	 *                                     it throws this exception.
	 */
	public void setHouseNumber(int number) {
		char[] numberChars = String.valueOf(number).toCharArray();
		for (char c : numberChars)
			if (!Character.isDigit(c))
				throw new InvalidHouseNumberException();

		this.houseNumber = number;
	}

	/**
	 * Function that updates the addition of the {@link Address}. It also checks if
	 * the input characters are letters/digits and if none of them are spaces, if not
	 * then the function throws an {@link InvalidAdditionException exception}.
	 *
	 * @param addition the to-be set addition.
	 * @throws InvalidAdditionException when the input addition is not a letter/digit
	 *                                  or if it's a space, this exception is thrown.
	 */
	public void setAddition(String addition) {
		char[] additionChars = addition.toCharArray();
		for (char c : additionChars)
			if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c))
				throw new InvalidAdditionException();

		this.addition = addition;
	}

	/**
	 * Function that updates the street of the {@link Address}. It also checks if
	 * the input characters are letters/digits and if the last character is not a space,
	 * if not then the function throws an {@link InvalidStreetException exception}.
	 *
	 * @param street the to-be set addition.
	 * @throws InvalidStreetException when the input street does not consist of letters/digits
	 *                                or if the last character is a space,
	 *                                this exception is thrown.
	 */
	public void setStreet(String street) {
		char[] streetChars = street.toCharArray();
		int listSize = streetChars.length;
		for (int i = 0; i < streetChars.length; i++) {
			char c = streetChars[i];
			if (i == listSize - 1 && !Character.isLetterOrDigit(c))
				throw new InvalidStreetException("Streetname must end with a letter or a number");

			if (c != '-' && !Character.isLetterOrDigit(c) && !Character.isWhitespace(c))
				throw new InvalidStreetException("Streetname must consist of numbers, letters and dashes only");
		}

		this.street = street;
	}

	/**
	 * Function that updates the postal code of the {@link Address}. It also checks if
	 * the input is 6 characters long, the first four input characters are digits
	 * and the last two letters, if not then the function throws an
	 * {@link InvalidPostalCodeException exception}.
	 *
	 * @param postalCode the to-be set postal code.
	 * @throws InvalidPostalCodeException when the input postal code is not 6 characters long
	 *                                    and/or does not consist of numbers for the
	 *                                    first four characters and letters for the last two,
	 *                                    this exception is thrown.
	 */
	public void setPostalCode(String postalCode) {
		char[] postalCodeChars = postalCode.toCharArray();

		if (postalCodeChars.length > 6)
			throw new InvalidPostalCodeException("Invalid postalcode length");

		for (int i = 0; i < postalCodeChars.length; i++) {
			char c = postalCodeChars[i];
			if (i < 4) {
				if (!Character.isDigit(c))
					throw new InvalidPostalCodeException("First four characters of postalcode must consist of numbers only");
			} else if (!Character.isLetter(c))
				throw new InvalidPostalCodeException("Last two characters of postalcode must consist of letters only");
		}

		this.postalCode = postalCode;
	}

	/**
	 * Function that updates the city of the {@link Address}. It also checks if
	 * the input characters are letters/digits, if not
	 * then the function throws an {@link InvalidCityException exception}.
	 *
	 * @param city the to-be set city.
	 * @throws InvalidCityException when the input city is not letters/digits
	 *                              this exception is thrown.
	 */
	public void setCity(String city) {
		char[] cityChars = city.toCharArray();
		for (char c : cityChars)
			if (!Character.isLetterOrDigit(c))
				throw new InvalidCityException();

		this.city = city;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public String getAddition() {
		return addition;
	}

	public String getStreet() {
		return street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}
}
