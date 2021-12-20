package nl.softwarestrijders.waiter.customer.core.domain;

import nl.softwarestrijders.waiter.customer.core.common.Utils;
import nl.softwarestrijders.waiter.customer.core.domain.event.CustomerDomainEvent;
import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidEmailException;
import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidNameException;
import nl.softwarestrijders.waiter.customer.core.domain.exceptions.InvalidNameStartException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * Class containing {@link Customer} data like first name, last name and email.
 */
@Document(collection = "customer")
public class Customer {
	@Id
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private List<UUID> orders;
	private HashMap<UUID, String> reviews; // String = type of review
	@Transient
	private List<CustomerDomainEvent> events = new ArrayList<>();

	/**
	 * Constructor of the {@link Customer} class. This constructor calls
	 * the class' setter methods instead of setting the variables directly,
	 * this is because the setters contain logic to verify input data.
	 *
	 * @param firstName the Customer's first name.
	 * @param lastName  the Customer's last name.
	 * @param email     the Customer's email address.
	 * @param address   the Customer's address.
	 */
	public Customer(String firstName, String lastName, String email, Address address) {
		this.id = UUID.randomUUID();
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		this.address = address;
		this.orders = new ArrayList<>();
		this.reviews = new HashMap<>();
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

	/**
	 * Function that adds the given orderId to the list of orderId's.
	 *
	 * @param orderId orderId
	 */
	public void addOrder(UUID orderId) {
		this.orders.add(orderId);
	}

	/**
	 * Function that removes the given orderId to the list of orderId's.
	 *
	 * @param orderId orderId
	 */
	public void removeOrder(UUID orderId) {
		this.orders.remove(orderId);
	}

	/**
	 * Function that adds the given orderIds to the list of orderId's.
	 *
	 * @param orderIds orderIds
	 */
	public void addOrders(List<UUID> orderIds) {
		this.orders.addAll(orderIds);
	}

	/**
	 * Function that adds the given orderId to the list of orderId's.
	 *
	 * @param reviewId reviewId
	 */
	public void addReview(UUID reviewId, String type) {
		this.reviews.put(reviewId, type);
	}

	/**
	 * Function that removes the given orderId to the list of orderId's.
	 *
	 * @param reviewId reviewId
	 */
	public void removeReview(UUID reviewId) {
		this.reviews.remove(reviewId);
	}

	/**
	 * Function that lists all {@link CustomerDomainEvent CustomerEvents}
	 *
	 * @return list of {@link CustomerDomainEvent CustomerEvents}
	 */
	public List<CustomerDomainEvent> listEvents() {
		return Collections.unmodifiableList(events);
	}

	/**
	 * Function that clears all {@link CustomerDomainEvent CustomerEvents}
	 */
	public void clearEvents() {
		this.events.clear();
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

	public List<UUID> getOrders() {
		return Collections.unmodifiableList(orders);
	}

	public Map<UUID, String> getReviews() {
		return Collections.unmodifiableMap(reviews);
	}
}
