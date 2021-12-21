# Review micro-service
This micro-service deals with (almost) everything related to reviewing. Most of the information here is valuable to the other developers (that are developing other micro-services).

| **sonar**cloud                                                                                                                                                                                                                               |
|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=bugs)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)                                     |
| [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)                       |
| [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=coverage)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)                             |
| [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview) |
| [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)                           |
| [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)           |
| [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)              |
| [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)         |
| [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)               |
| [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)                    |
| [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Areview&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Areview)               |

## Events
In this section it will become clear what events the Review micro-service produces. These events will be described with the exact JSON that they deliver.

### Produces
This sections covers the few events that will be produced from the Review micro-service.

#### ReviewCreatedEvent
This very simple message only gives its `reviewId` and `reviewType`.

##### Routing key
This event has the following routing key: `events.review.created`.

##### Fields
| Key        | Value  | Extra Information              |
|------------|--------|--------------------------------|
| reviewId   | UUID   | —                              |
| reviewType | String | can be `product` or `delivery` |

##### JSON Example
The JSON that will be sent, will look like this:
```json
{
    "reviewId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
    "reviewType": "product"
}
```

#### ReviewDeletedEvent
This message will be sent when a certain review is deleted.

##### Routing key
The event has the following routing key: `events.review.deleted`.

##### Fields
| Key        | Value  | Extra Information              |
|------------|--------|--------------------------------|
| reviewId   | UUID   | —                              |
| reviewType | String | can be `product` or `delivery` |

#### JSON Example
```json
{
    "reviewId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
    "reviewType": "product"
}
```

## REST
This micro-service has a couple different endpoints, these will be shown below.

The base URL of this API is as follows: `http://localhost:8084/api/review`.

In the [postman](./postman) folder is a collection that can be used to quickly test the service.

### DELETE
This section covers the DELETE HTTP method.

#### `/{reviewId}`
Expects the following body:
```json
{
    "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943622"
}
```

### GET
This section covers the GET HTTP method.

#### `baseUrl`
This will retrieve all reviews.

When doing a get request to the base url, you are expected to provide the following query params.

`/api/review?direction={direction}&sort={sort}`

direction can be:
- `desc`
- `asc`

sort can be:
- `title`
- `description`
- `type`
- `rating`

**note**: these are not optional.

#### `/{reviewId}`
This will retrieve a single Review.

#### `/customer/{customerId}`
This will retrieve all Reviews made by a Customer.

#### `/product/{productId}`
This will retrieve all Reviews made on a Product.

#### `/delivery/{deliveryId}`
This will retrieve all Reviews made on a Delivery.

### PATCH
This section covers the PATCH HTTP method.

#### `/{reviewId}`
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

