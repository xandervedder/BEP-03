# Customer micro-service

| **sonar**cloud |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=bugs)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)                                      |
| [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)                        |
| [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=coverage)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)                              |
| [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)  |
| [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)                            |
| [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)            |
| [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)               |
| [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)          |
| [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)                |
| [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Acustomer&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Acustomer)                     |

## Events

In this section it will become clear what events the Customer micro-service consumes and produces. These events will be
described with the exact JSON that they require or deliver.

### Consumes

#### Review created event

##### Routing key

This event has the following routing key: review.created.

##### Fields

| Key           | Value  | Extra information  |
|---------------|--------|--------------------|
| customerId    | UUID   |      —             |
| reviewId      | UUID   |      —             |
| type          | String | delivery or product |

##### JSON Example

```json
{
  "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "reviewId": "62ba8c89-d1r0-84rt-3ds4-6y984gf4d785",
  "type": "Delivery"
}
```

#### Review deleted event

#### Routing key

This event has the following routing key: review.deleted.

#### Fields

| Key           | Value  | Extra information  |
|---------------|--------|--------------------|
| customerId    | UUID   |      —             |
| reviewId      | UUID   |      —             |

##### JSON Example

or example, the JSON looks like this:

```json
{
  "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "reviewId": "62ba8c89-d1r0-84rt-3ds4-6y984gf4d785"
}
```

#### order created event

##### Routing key

This event has the following routing key: order.created.

##### Fields

| Key           | Value  | Extra information  |
|---------------|--------|--------------------|
| customerId    | UUID   |      —             |
| orderId       | UUID   |      —             |

###### JSON Example

or example, the JSON looks like this:

```json
{
  "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "orderId": "62ba8c89-d1r0-84rt-3ds4-6y984gf4d785"
}
```

#### order deleted event

##### Routing key

This event has the following routing key: order.deleted.

##### Fields

| Key           | Value  | Extra information  |
|---------------|--------|--------------------|
| customerId    | UUID   |      —             |
| orderId       | UUID   |      —             |

###### JSON Example

or example, the JSON looks like this:

```json
{
  "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "orderId": "62ba8c89-d1r0-84rt-3ds4-6y984gf4d785"
}
```

#### order deleted event

##### Routing key

This event has the following routing key: review.created.

##### Fields

| Key           | Value  | Extra information  |
|---------------|--------|--------------------|
| customerId    | UUID   |      —             |
| reviewId      | UUID   |      —             |
| type          | String |      —             |

#### Review deleted event

##### Routing key

This event has the following routing key: review.deleted.

##### Fields

| Key           | Value  | Extra information  |
|---------------|--------|--------------------|
| customerId    | UUID   |      —             |
| reviewId      | UUID   |      —             |

##### JSON Example

or example, the JSON looks like this:

```json
{
  "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "orderId": "62ba8c89-d1r0-84rt-3ds4-6y984gf4d785"
}
```

## REST

This micro-service has a couple different endpoints, these will be shown below.

The base URL of this API is as follows: http://localhost:8080/customer.

In the postman folder is a collection that can be used to quickly test the service.

### GET

This section covers the GET HTTP method.

#### `/{customerId}/reviews`

This will retrieve all reviews from the given customer.

#### `/{customerId}/orders`

This will retrieve all orders from the given customer.

#### `/{customerId}/deliveries`

This will retrieve all deliveries from the given customer.

#### `/{customerId}/retrieve-address`

This will retrieve the address from the given customer.

### POST

#### `baseUrl`

This will register a customer.

##### Fields

| Key           | Value  | Extra information  |
|---------------|--------|--------------------|
| firstname     | String |                    |
| lastname      | String |                    |
| email         | String |                    |
| housenumber   | int    |                    |
| addition      | String |                    |
| street        | String |                    |
| postalCode    | String |                    |
| city          | String |                    |
