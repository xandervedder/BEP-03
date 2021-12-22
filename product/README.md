# Product micro-service
This micro-microservice manages the products in our application. It handles creating and deleting products.

| **sonar**cloud                                                                                                                                                                                                                             |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=bugs)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)                                     |
| [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)                       |
| [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=coverage)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)                             |
| [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct) |
| [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)                           |
| [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)           |
| [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)              |
| [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)         |
| [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)               |
| [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)                    |
| [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=nl.softwarestrijders.waiter%3Aproduct&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=nl.softwarestrijders.waiter%3Aproduct)               |

## Events
In this section it will become clear what events the product micro-service produces. These events will be described with the exact JSON that they deliver.

### Produces
This sections covers the events that will be produced from the product micro-service.

#### ProductCreatedEvent
This event will return the new product and all of its information.

##### Routing key
This event has the following routing key: `events.product.created`.

##### Fields
| Key     | Value   | Extra Information              |
|---------|---------|--------------------------------|
| product | Product | —                              |


##### JSON Example
The JSON that will be sent, will look like this:
```json
{
    "product" : {
      "id": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
      "price": 6.50,
      "name": "Cheese",
      "description": "Old Amsterdam",
      "weight": 100,
      "nutritionalValue": {
        "kcal": 500,
        "fats": 50,
        "carbs": 30,
        "proteins": 20,
        "salts": 5
      }
    }
}
```

#### ProductDeletedEvent
This message will be sent when a product is deleted.

##### Routing key
The event has the following routing key: `events.product.deleted`.

##### Fields
| Key | Value  | Extra Information              |
|-----|--------|--------------------------------|
| id  | UUID   | —                              |

#### JSON Example
```json
{
    "id": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
}
```

## REST
This micro-service has a couple different endpoints, these will be shown below.

The base URL of this API is as follows: `http://localhost:8083/product`.

There is a postman collection in the root of this micro-service.

### DELETE
This section covers the DELETE HTTP method.

#### `/delete/{id}`
This will delete the product with given id.
Expects a product id as path variable.

### GET
This section covers the GET HTTP method.

#### `BaseUrl`
This will retrieve all the products stored in the database.

#### `/{id}`
This will retrieve a single Product.
Expects a product id as path variable.

### POST
This section covers the HTTP POST method.

#### `/create`
When posting to this endpoint, it expects all information for the new product. example:
```json
{
    "price": 6.50,
    "name": "Cheese",
    "description": "Old Amsterdam",
    "weight": 100,
    "kcal": 500,
    "fats": 50,
    "carbs": 30,
    "proteins": 20,
    "salts": 5
}
```

