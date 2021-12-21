# Delivery micro-service 

This micro-service deals with (almost) everything related to deliveries. Most of the information here is
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

#### Delivery

DeliveryAddressChanged: This message gives its `deliveryId` and `deliveryAddress`. 

##### Routing key

This event has the following routing key: `delivery.address`. 

##### Fields

| Key             | Value           | Extra Information              |
|-----------------|-----------------|--------------------------------|
| deliveryId      | UUID            | —                              |
| deliveryAddress | DeliveryAddress | is an address object           |

##### JSON Example

The JSON that will be sent, will look like this:

```json
{
  "deliveryId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "deliveryAddress": {
    "streetName": "Spinozaweg",
    "houseNumber": 71,
    "addition": "B",
    "postalCode": "3532SE",
    "city": "Utrecht"
  }
}
```

#### Delivery

DeliveryStatusChanged: This message gives its `deliveryId` and `deliveryStatus`.

##### Routing key

This event has the following routing key: `delivery.status`.

##### Fields

| Key            | Value  | Extra Information              |
|----------------|--------|--------------------------------|
| deliveryId     | UUID   | —                              |
| deliveryStatus | Status | is an enum                     |

#### JSON Example

```json
{
  "deliveryId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "deliveryStatus": "Status.REGISTERED"
}
```

### Consumes

This sections covers the few events that will be consumed by the delivery micro-service.

#### Order

OrderCreated: This message gives its `orderId` and `customerId`.

##### Routing key

This event has the following routing key: `order.created`.

##### Fields

| Key        | Value  | Extra Information              |
|------------|--------|--------------------------------|
| orderId    | UUID   | —                              |
| customerId | UUID   | —                              |

## REST

This micro-service has a couple different endpoints, these will be shown below.

The base URL of this API is as follows: `http://localhost:8081/delivery`.

In the [postman](./postman) folder is a collection that can be used to quickly test the service.

### GET

This section covers the GET HTTP method.

#### `baseUrl`

This will retrieve all delivery s.

When doing a get request to the base url, you'll receive a list of all the deliveries.

`/delivery`

**note**: these are not optional.

#### `/{deliveryId}`

This will retrieve a single delivery.

#### `/status/{id}`

This will retrieve the delivery status.

#### `/order/{id}`

This will retrieve the delivery that belongs to the given order(id).

#### `/delivery/{deliveryId}`

This will retrieve all delivery s made on a Delivery.

## REST-CALL

The micro-service makes a rest-call to another microservice to retrieve some information.

### GET
The delivery service calls the `@GetMapping("/order/{orderId}/retrieve-address")` from the customer service,
to get the address for registering a new delivery.
