package nl.softwarestrijders.waiter.customer.domain;

import nl.softwarestrijders.waiter.customer.domain.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
	private int houseNumber;
	private String addition;
	private String street;
	private String postalCode;
	private String city;

	@BeforeEach
	public void initialize() {
		this.houseNumber = 1;
		this.addition = "A";
		this.street = "TestStreet";
		this.postalCode = "1010AB";
		this.city = "testCity";
	}

	@Test
	@DisplayName("Should create Address successfully")
	void shouldCreateAddressSuccessfully() {
		assertDoesNotThrow(() -> new Address(houseNumber, addition, street, postalCode, city));
	}

	@Test
	@DisplayName("Should create Address successfully when street contains whitespaces")
	void shouldCreateAddressSuccessfullyWithSpaceInStreet() {
		assertDoesNotThrow(() -> new Address(houseNumber, addition, "Test street", postalCode, city));
	}

	@Test
	@DisplayName("Should throw InvalidHouseNumberException if housenumber is invalid")
	void shouldThrowWhenInvalidHouseNumber() {
		assertThrows(InvalidHouseNumberException.class, () -> new Address(-1, addition, street, postalCode, city));
	}

	@Test
	@DisplayName("Should throw if addition is invalid")
	void shouldThrowWhenInvalidAddition() {
		assertThrows(InvalidAdditionException.class, () -> new Address(houseNumber, "-", street, postalCode, city));
	}

	@Test
	@DisplayName("Should not throw if addition contains whitespaces")
	void shouldNotThrowWhenAdditionContainsWhiteSpace() {
		assertDoesNotThrow(() -> new Address(houseNumber, "A 4", street, postalCode, city));
	}

	@Test
	@DisplayName("Should not throw if street name contains a hyphen")
	void shouldNotThrowWhenHyphenInStreet() {
		assertDoesNotThrow(() -> new Address(houseNumber, addition, "Test-Street", postalCode, city));
	}

	@Test
	@DisplayName("Should Throw if streetname ends with something else than a number or a letter")
	void shouldThrowWhenInvalidStreet() {
		assertThrows(InvalidStreetException.class, () -> new Address(houseNumber, addition, "Test-Street-", postalCode, city));
	}

	@Test
	@DisplayName("Should throw when street contains white space")
	void shouldThrowWhenStreetContainsWhitespace() {
		assertDoesNotThrow(() -> new Address(houseNumber, addition, "Test Street", postalCode, city));
	}

	@Test
	@DisplayName("Should throw when street name contains invalid characters")
	void shouldThrowWhenStreetContainsInvalidCharacter() {
		assertThrows(InvalidStreetException.class, () -> new Address(houseNumber, addition, "Test*Street", postalCode, city));
	}

	@Test
	@DisplayName("Should throw when Postal code contains too many numbers")
	void shouldThrowWhenPostalcodeContainsTooManyNumbers() {
		assertThrows(InvalidPostalCodeException.class, () -> new Address(houseNumber, addition, street, "2211AGA", city));
	}

	@Test
	@DisplayName("Should throw when Postal code contains letters in the digit part")
	void shouldThrowWhenPostalcodeContainsLettersInDigitPart() {
		assertThrows(InvalidPostalCodeException.class, () -> new Address(houseNumber, addition, street, "2A11AA", city));
	}

	@Test
	@DisplayName("Should throw when postal code contains to many letters")
	void shouldThrowWhenPostalcodeContainsTooManyLetters() {
		assertThrows(InvalidPostalCodeException.class, () -> new Address(houseNumber, addition, street, "22111A", city));
	}

	@Test
	@DisplayName("Should throw if postal code contains invalid characters")
	void shouldThrowWhenPostalcodeContainsinvalidCharacters() {
		assertThrows(InvalidPostalCodeException.class, () -> new Address(houseNumber, addition, street, "2211*A", city));
	}

	@Test
	@DisplayName("Should throw when city contains invalid characters")
	void shouldThrowWhenCityContainsInvalidCharacter() {
		assertThrows(InvalidCityException.class, () -> new Address(houseNumber, addition, street, postalCode, "TestC*ty"));
	}

	@Test
	@DisplayName("Should throw when city contains numbers")
	void shouldNotThrowWhenCityContainsNumbers() {
		assertDoesNotThrow(() -> new Address(houseNumber, addition, street, postalCode, "TestC1ty"));
	}

	@Test
	@DisplayName("Getters and setters test for JaCoCo")
	void getterSetterTests() {
		var address = new Address(houseNumber, addition, street, postalCode, city);
		assertDoesNotThrow(() -> address.setId("1"));
		assertDoesNotThrow(address::getHouseNumber); //int cannot be null
		assertNotNull(address.getId());
		assertNotNull(address.getAddition());
		assertNotNull(address.getStreet());
		assertNotNull(address.getPostalCode());
		assertNotNull(address.getCity());

	}
}
