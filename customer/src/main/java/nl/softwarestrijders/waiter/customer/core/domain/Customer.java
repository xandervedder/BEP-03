package nl.softwarestrijders.waiter.customer.core.domain;

import nl.softwarestrijders.waiter.customer.core.common.Utils;
import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidEmailException;
import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidNameException;
import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidNameStartException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * Class containing {@link Customer} data like first name, last name and email.
 */
@Document(collation = "customer")
public class Customer {
	@Id
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;

	/**
	 * Constructor of the {@link Customer} class. This constructor calls
	 * the class' setter methods instead of setting the variables directly,
	 * this is because the setters contain logic to verify input data.
	 *
	 * @param firstName the Customer's first name.
	 * @param lastName  the Customer's last name.
	 */
	public Customer(String firstName, String lastName, String email, Address address) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		this.address = address;
	}

	/**
	 * Function that updates the first name of the {@link Customer}.
	 * It also checks if the name consists of letters only and starts with a capital
	 * letter and throws an {@link InvalidNameStartException invalidNameStartException}
	 * or {@link InvalidNameException invalidNameException} if not.
	 *
	 * @param firstName the to-be set first name.
	 * @throws InvalidNameStartException when the input name does not start with a capital letter,
	 *                                   it throws this exception.
	 * @throws InvalidNameException      when the input name does not consist of letters only,
	 *                                   it throws this exception.
	 */
	public void setFirstName(String firstName) {
		char[] firstNameChars = firstName.toCharArray();

		if (!Character.isUpperCase(firstNameChars[0]))
			throw new InvalidNameStartException();

		for (char c : firstNameChars)
			if (!Character.isLetter(c))
				throw new InvalidNameException(c);

		this.firstName = firstName;
	}

	/**
	 * Function that updates the last name of the {@link Customer}.
	 * It also checks if the name consists of letters only and starts with a capital
	 * letter and throws an {@link InvalidNameStartException invalidNameStartException}
	 * or {@link InvalidNameException invalidNameException} if not.
	 *
	 * @param lastName the to-be set first name.
	 * @throws InvalidNameStartException when the input name does not start with a capital letter,
	 *                                   it throws this exception.
	 */
	public void setLastName(String lastName) {
		char[] lastNameChars = lastName.toCharArray();

		if (!Character.isUpperCase(lastNameChars[0]))
			throw new InvalidNameStartException();

		for (char c : lastNameChars)
			if (!Character.isLetter(c))
				throw new InvalidNameException(c);

		this.lastName = lastName;
	}

	/**
	 * Function that checks if the character composition of the given string matches the composition of a
	 * valid email address and throws {@link InvalidEmailException InvalidEmailException} if it does not match.
	 *
	 * @param email email address
	 * @throws InvalidEmailException when the input does not have the composition of a valid email address.
	 */
	public void setEmail(String email) {
		if (Utils.isEmailValid(email))
			this.email = email;
		else
			throw new InvalidEmailException();
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UUID getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}
}
