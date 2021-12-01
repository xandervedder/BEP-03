package nl.softwarestrijders.waiter.customer.core.domain;

import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidEmailException;
import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidNameException;
import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidNameStartException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
	private final Address address =
			new Address(1, "a", "langemansstraat", "6528GO", "KerelCity");

	@Test
	@DisplayName("Should create correctly")
	void shouldCreateCorrectly() {
		assertDoesNotThrow(() -> new Customer("Milan", "Dol", "milandol@gmail.com", address));
	}

	@Test
	@DisplayName("Should throw InvalidNameStartException if first name doesn't start with a capital letter")
	void shouldThrowWhenFirstNameDoesNotStartWithCapitalLetter() {
		assertThrows(InvalidNameStartException.class, () -> new Customer("mario", "Kart", "marioKartus@mario.com", address));
	}

	@Test
	@DisplayName("Should throw InvalidNameStartException if last name doesn't start with a capital letter")
	void shouldThrowWhenLastNameDoesNotStartWithCapitalLetter() {
		assertThrows(InvalidNameStartException.class, () -> new Customer("Guillermo", "acunsto", "g.acunsto@outlook.fr", address));
	}

	@Test
	@DisplayName("Should throw InvalidNameException if first name contains digits")
	void shouldThrowWhenFirstNameContainsDigits() {
		assertThrows(InvalidNameException.class, () -> new Customer("Gabr1el", "Mans", "gab_mans@amazon.com", address));
	}

	@Test
	@DisplayName("Should throw InvalidNameException if last name contains digits")
	void shouldThrowWhenLastNameContainsDigits() {
		assertThrows(InvalidNameException.class, () -> new Customer("Xander", "Stati0n", "xs@xs.be", address));
	}

	@Test
	@DisplayName("Should throw InvalidNameException if first name contains symbols")
	void shouldThrowWhenFirstNameContainsSymbols() {
		assertThrows(InvalidNameException.class, () -> new Customer("Qoi-iun", "Lekkel", "qoile@kereltje.nl", address));
	}

	@Test
	@DisplayName("Should throw InvalidNameException if last name contains symbols")
	void shouldThrowWhenLastNameContainsSymbols() {
		assertThrows(InvalidNameException.class, () -> new Customer("Gunter", "Gust+", "gugu@gu.co.uk", address));
	}

	@Test
	@DisplayName("Should throw InvalidEmailException if the email contains invalid characters")
	void shouldThrowInvalidEmailExceptionIfEmailContainsInvalidCharacters() {
		assertThrows(InvalidEmailException.class, () -> new Customer("Arjan", "Klonkel", "arj*n@@outlook.com", address));
	}

	@Test
	@DisplayName("Should throw InvalidEmailException if there is a double @ in the email address")
	void shouldThrowInvalidEmailExceptionIfEmailHasDoubleAt() {
		assertThrows(InvalidEmailException.class, () -> new Customer("Danial", "Gebrail", "dani@@try.com", address));
	}

	@Test
	@DisplayName("Should throw InvalidEmailException if there is a double . at the end of the email address")
	void shouldThrowInvalidEmailExceptionIfEmailHasDoubleDot() {
		assertThrows(InvalidEmailException.class, () -> new Customer("Zora", "Bult", "z.bult@twieger..com", address));
	}

	@Test
	@DisplayName("Getters and setters test for JaCoCo")
	void getterSetterTests() {
		var customer = new Customer("Test", "Test", "test@test.nl", address);
		assertDoesNotThrow(() -> customer.setId(UUID.randomUUID()));
		assertDoesNotThrow(() -> customer.setAddress(address));
		assertNotNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getLastName());
		assertNotNull(customer.getEmail());
		assertNotNull(customer.getAddress());
	}
}
