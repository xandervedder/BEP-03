# Order micro-service

This micro-service deals with everything related to managing orders. Most of the information here is valuable to the
other developers (that are developing other micro-services).

| Sonarcloud                                                                                                                                                                                                                               |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=bugs)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)                                     |
| [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)                       |
| [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=coverage)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)                             |
| [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder) |
| [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)                           |
| [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)           |
| [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)              |
| [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)         |
| [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)               |
| [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)                    |
| [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aorder&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aorder)               |

## Events

In this section it will become clear what events the Order micro-service produces. These events will be described with
the exact JSON that they deliver.

### Produces

This sections covers the few events that will be produced from the Order micro-service.

#### OrderCreated

This event produces a message with a `orderId` and `customerId`.

##### Routing key

This event has the following routing key: `order.created`.

##### Fields

| Key           | Value
|------------   |--------
| orderId       | UUID
| customerId    | UUID

##### JSON Example

The JSON that will be sent, will look like this:

```json
{
  "orderId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "customerId": "53bc28c1-db65-235e-9bcd-8a9845943622"
}
```

#### OrderDeleted

This message will be sent when an order is deleted.

##### Routing key

The event has the following routing key: `order.deleted`.

##### Fields

| Key        | Value  |
|------------|--------|
| orderId    | UUID   |

#### JSON Example

```json
{
  "orderId": "62bc28c1-db80-4e4e-9bcd-8a9845943633"
}
```

#### ProductAddedToOrder

This message will be sent when a new product or amount of existing product is added to a certain order.

##### Routing key

The event has the following routing key: `order.product.added`.

##### Fields

| Key        | Value  |
|------------|--------|
| orderId    | UUID   |
| productId    | UUID   |
| amount    | integer   |

#### JSON Example

```json
{
  "orderId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "productId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "amount": 5
}
```

#### ProductRemovedFromOrder

This message will be sent when a complete product or amount of existing product is removed from a certain order.

##### Routing key

The event has the following routing key: `order.product.removed`.

##### Fields

| Key        | Value  |
|------------|--------|
| orderId    | UUID   |
| productId    | UUID   |
| amount    | integer   |

#### JSON Example

```json
{
  "orderId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "productId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
  "amount": 5
}
```

## REST

This micro-service has a couple different endpoints, these will be shown below.

The base URL of this API is as follows: `http://localhost:8082/api/order`.

In the [postman](./postman) folder is a collection that can be used to quickly test the service.

### DELETE

This section covers the DELETE HTTP method.

#### `/{orderId}`

Deletes an entire order.

Expects the following body:

```json
{
  "orderId": "62bc28c1-db80-4e4e-9bcd-8a9845943622"
}
```

#### `/{orderId}/product/{productId}`

Removes amount of a product in a specified order.

**Please note**: When the specified amount is greater or equal to the amount in the order the complete product will be
removed.

Expects the following body:

```json
{
  "amount": 1
}
```

### GET

This section covers the GET HTTP method.

#### `baseUrl`

This will find all orders.

When doing a get request to the base url, you have the option to specify a filter as a query parameter.

These could be:

- `customer`
- `product`

**Please note**: these are not stackable. Only one filter can be applied at the same time.

**Returns**:

```json
[
  {
    "id": "4173a58e-2cb9-4618-a2d4-cc3aa3b05e19",
    "receipt": {
      "items": [
        {
          "productId": "17214ef5-4331-481d-a904-8416ed9bfa58",
          "amount": 3
        }
      ]
    },
    "customerId": "07e5d42d-a2c7-4c23-be8d-d7dfb811c139",
    "price": {
      "subTotal": 5.0,
      "total": 6.0,
      "vat": 0.21
    }
  },
  {
    "id": "7674e266-026d-40b2-9692-6662c6abd474",
    "receipt": {
      "items": []
    },
    "customerId": "07e5d42d-a2c7-4c23-be8d-d7dfb811c139",
    "price": {
      "subTotal": 0.0,
      "total": 0.0,
      "vat": 0.21
    }
  }
]
```

#### `/{orderId}`

This will retrieve a single order.

**Returns:**

```json
{
  "id": "4173a58e-2cb9-4618-a2d4-cc3aa3b05e19",
  "receipt": {
    "items": [
      {
        "productId": "17214ef5-4331-481d-a904-8416ed9bfa58",
        "amount": 3
      }
    ]
  },
  "customerId": "07e5d42d-a2c7-4c23-be8d-d7dfb811c139",
  "price": {
    "subTotal": 5.0,
    "total": 6.0,
    "vat": 0.21
  }
}
```

### POST

This section covers the HTTP POST method.

#### `baseUrl`
This command creates a new order.

When POSTing to this endpoint, the following body is expected:

```json
{
  "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943622"
}
```

#### `/{orderId}/product/{productId}`
This command adds a new product adds an amount to an order.

When POSTing to this endpoint, the following body is expected:

```json
{
  "amount": 2
}
```
