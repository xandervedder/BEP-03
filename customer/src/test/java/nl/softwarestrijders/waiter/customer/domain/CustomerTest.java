package nl.softwarestrijders.waiter.customer.domain;

import nl.softwarestrijders.waiter.customer.domain.exceptions.InvalidEmailException;
import nl.softwarestrijders.waiter.customer.domain.exceptions.InvalidNameException;
import nl.softwarestrijders.waiter.customer.domain.exceptions.InvalidNameStartException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {
	@Test
	@DisplayName("Should create correctly")
	void shouldCreateCorrectly() {
		assertDoesNotThrow(() -> new Customer("Milan", "Dol", "milandol@gmail.com"));
	}

	@Test
	@DisplayName("Should throw InvalidNameStartException if first name doesn't start with a capital letter")
	void shouldThrowWhenFirstNameDoesNotStartWithCapitalLetter() {
		assertThrows(InvalidNameStartException.class, () -> new Customer("mario", "Kart", "marioKartus@mario.com"));
	}

	@Test
	@DisplayName("Should throw InvalidNameStartException if last name doesn't start with a capital letter")
	void shouldThrowWhenLastNameDoesNotStartWithCapitalLetter() {
		assertThrows(InvalidNameStartException.class, () -> new Customer("Guillermo", "acunsto", "g.acunsto@outlook.fr"));
	}

	@Test
	@DisplayName("Should throw InvalidNameException if first name contains digits")
	void shouldThrowWhenFirstNameContainsDigits() {
		assertThrows(InvalidNameException.class, () -> new Customer("Gabr1el", "Mans", "gab_mans@amazon.com"));
	}

	@Test
	@DisplayName("Should throw InvalidNameException if last name contains digits")
	void shouldThrowWhenLastNameContainsDigits() {
		assertThrows(InvalidNameException.class, () -> new Customer("Xander", "Stati0n", "xs@xs.be"));
	}

	@Test
	@DisplayName("Should throw InvalidNameException if first name contains symbols")
	void shouldThrowWhenFirstNameContainsSymbols() {
		assertThrows(InvalidNameException.class, () -> new Customer("Qoi-iun", "Lekkel", "qoile@kereltje.nl"));
	}

	@Test
	@DisplayName("Should throw InvalidNameException if last name contains symbols")
	void shouldThrowWhenLastNameContainsSymbols() {
		assertThrows(InvalidNameException.class, () -> new Customer("Gunter", "Gust+", "gugu@gu.co.uk"));
	}

	@Test
	@DisplayName("Should throw InvalidEmailException if the email contains invalid characters")
	void shouldThrowInvalidEmailExceptionIfEmailContainsInvalidCharacters() {
		assertThrows(InvalidEmailException.class, () -> new Customer("Arjan", "Klonkel", "arj*n@@outlook.com"));
	}

	@Test
	@DisplayName("Should throw InvalidEmailException if there is a double @ in the email address")
	void shouldTrowInvalidEmailExceptionIfEmailHasDoubleAt() {
		assertThrows(InvalidEmailException.class, () -> new Customer("Danial", "Gebrail", "dani@@try.com"));
	}

	@Test
	@DisplayName("Should throw InvalidEmailException if there is a double . at the end of the email address")
	void shouldTrowInvalidEmailExceptionIfEmailHasDoubleDot() {
		assertThrows(InvalidEmailException.class, () -> new Customer("Zora", "Bult", "z.bult@twieger..com"));
	}
}
