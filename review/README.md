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
In this section it will become clear what events the Review micro-service consumes and produces. These events will be described with the exact JSON that they require or deliver. 

### Consumes
The events this micro-service consumes.

#### CreateReviewEvent
When recieving this event, this micro-service creates the Review with the given information.

##### Queue name
`create-review`.

##### Routing key
`review.create`.

##### Fields
| Key            | Value  | Extra information                               |
|----------------|--------|-------------------------------------------------|
| customerId     | UUID   | —                                               |
| conceptId      | UUID   | Id of Product or Delivery                       |
| reviewType     | String | Can be `product` or `delivery`                  |
| title          | String | Minimum length of `3`, maximum length of `32`   |
| description    | String | Minimum length of `32`, maximum length of `512` |
| rating         | int    | Between `1` or `5`                              |

##### JSON Example
For example, the JSON looks like this:
```json
{
    "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
    "conceptId": "bd72ef09-d700-4e6f-a3b3-96425cce6376",
    "reviewType": "product",
    "title": "Very good!",
    "description": "This food is the most delicious food I have ever tasted!",
    "rating": 5
}
```

#### DeleteReviewEvent
This message is simple. To delete a Review, simply provide the `reviewId`.

##### Queue name
`delete-review`.

##### Routing key
`review.delete`.

##### Fields
| Key        | Value | Extra Information |
|------------|-------|-------------------|
| customerId | UUID  | —                 |
| reviewId   | UUID  | —                 |

##### JSON Example
For example, the JSON looks like this:
```json
{
    "customerId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
    "reviewId": "62bc28c1-db80-4e4e-9bcd-8a9845943633"
}
```

#### EditReviewEvent
This message is very similar to the [CreateReviewEvent](#createreviewevent). Though this event doesn't need a `customerId` or `conceptId`, but simply a `reviewId`. There is also no need for the `reviewType` field.

##### Queue name
`edit-review`.

##### Routing key
`review.edit`.

##### Fields
| Key         | Value  | Extra information                               |
|-------------|--------|-------------------------------------------------|
| reviewId    | UUID   | —                                               |
| title       | String | Minimum length of `3`, maximum length of `32`   |
| description | String | Minimum length of `32`, maximum length of `512` |
| rating      | int    | between `1` or `5`                              |

##### JSON Example
For example the JSON looks like this:
```json
{
    "reviewId": "62bc28c1-db80-4e4e-9bcd-8a9845943633",
    "title": "Awful...",
    "description": "This food is the worst food I have ever tasted!",
    "rating": 1
}
```

### Produces
This sections covers the few events the will be produced from the Review micro-service.

#### ReviewCreatedEvent
This very simple message only gives its `reviewId` and `reviewType`.

##### Routing key
At the moment, It's not clear who will listen to what. It is also unclear if you can or can't send the same message to multiple routing keys.

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
This message will be sent when a certain review is deleted, it sends the same information as the [ReviewCreatedEvent](#reviewcreatedevent).

##### Routing key
Not clear at the moment.

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
—
