# delivery

micro-service This micro-service deals with (almost) everything related to deliveries. Most of the information here is
valuable to the other developers (that are developing other micro-services).

| **sonar**cloud                                                                                                                                                                                                                                   |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=bugs)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)                                     |
| [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)                       |
| [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=coverage)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)                             |
| [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery) | 
| [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)                           |
| [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)           |
| [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)              |
| [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)         |
| [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)               |
| [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)                    |
| [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Adelivery&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Adelivery)               |

## Events

In this section it will become clear what events the delivery micro-service produces. These events will be described
with the exact JSON that they deliver.

### Produces

This sections covers the few events that will be produced from the delivery micro-service.

#### delivery

CreatedEvent This very simple message only gives its `delivery Id` and `delivery Type`.

##### Routing key

This event has the following routing key: `events.delivery .created`.

##### Fields

| Key        | Value  | Extra Information              |
|------------|--------|--------------------------------|
| deliveryId   | UUID   | —                              |
| deliveryType | String | can be `product` or `delivery` |

##### JSON Example

The JSON that will be sent, will look like this:

```json
{
  "delivery
  Id
  ": "
  62bc28c1-db80-4e4e-9bcd-8a9845943633
  ",
  "delivery
  Type
  ": "
  product
  "
}
```

#### delivery

DeletedEvent This message will be sent when a certain delivery is deleted.

##### Routing key

The event has the following routing key: `events.delivery .deleted`.

##### Fields

| Key        | Value  | Extra Information              |
|------------|--------|--------------------------------|
| deliveryId   | UUID   | —                              |
| deliveryType | String | can be `product` or `delivery` |

#### JSON Example

```json
{
  "delivery
  Id
  ": "
  62bc28c1-db80-4e4e-9bcd-8a9845943633
  ",
  "delivery
  Type
  ": "
  product
  "
}
```

## REST

This micro-service has a couple different endpoints, these will be shown below.

The base URL of this API is as follows: `http://localhost:8084/api/delivery
`.

In the [postman](./postman) folder is a collection that can be used to quickly test the service.

### DELETE

This section covers the DELETE HTTP method.

#### `/{delivery

Id}`
Expects the following body:

```json
{
  "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943622"
}
```

### GET

This section covers the GET HTTP method.

#### `baseUrl`

This will retrieve all delivery s.

When doing a get request to the base url, you are expected to provide the following query params.

`/api/delivery ?direction={direction}&sort={sort}`

direction can be:

- `desc`
- `asc`

sort can be:

- `title`
- `description`
- `type`
- `rating`

**note**: these are not optional.

#### `/{deliveryId}`
This will retrieve a single delivery .

#### `/customer/{customerId}`

This will retrieve all delivery s made by a Customer.

#### `/product/{productId}`

This will retrieve all delivery s made on a Product.

#### `/delivery/{deliveryId}`

This will retrieve all delivery s made on a Delivery.

### PATCH

This section covers the PATCH HTTP method.

#### `/{deliveryId}`
This endpoint expects the following body:

```json
{
  "title": "<title here>",
  "description": "<description here>",
  "rating": 5
}
```

### POST

This section covers the HTTP POST method.

#### `baseUrl`

When POST'ing to this endpoint, the following body is expected:

```json
{
  "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943622",
  "conceptId": "62bc28c1-db80-4e4e-9bcd-8a9845943332",
  "type": "product",
  "title": "title",
  "description": "description",
  "rating": 5
}
```
