# Game-Transaction-API

This demo project showcases a simple RESTful API which has several endpoints that can be accessed and used to manipulate data within a provided database. 

Built with Java, Spring and Hibernate (ORM for Spring), which supports CRUD operations.


## Installation & Run

```
* Firstly navigate to (https://github.com/HamzaAli8/game-transactions-api)

* Clone this repo to your local machine

* For simplicity in testing, this demo API utilises an in-memory H2 database to store transactions. This can easily 
  be adjusted to use a production database of your liking for e.g. MySQL,Postgres, or RDS by simply making the relevant
  changes to the application.properties file contained within src.

* Navigate to the project directory and execute ./mvnw spring-boot:run

* Windows users can execute mvnw.cmd spring-boot:run

```



## Structure
```
├── java
│   ├── app
│   ├── gametransactionapi   // Our API core handlers
│   │   ├── controllers      // Common endpoints for API
│   │   ├── services         // Services to manipulate DB
│   │   └── repositories     // Repos to DB
│   └── model
│       └── transaction      // Models for our application
├── resources
│   └── application.properties  // Configuration
└── main
```

## API

#### /transactions
* `GET` : Get all transactions
* `POST` : Create a new transaction

You can then get all transations with a GET to /transactions.
This endpoint offers two options query parameters - less and greater to filter by amount.

You can POST Transactions using the following JSON structure:

```
{
    "product":{
        "name": "Sega Megadrive",
        "description":"greatest console ever!!"
        },
    "userId": 89,
    "amount": 1000
}
```

Which will return the newly created resource and it's location:

```
{
    "transactionId": 1,
    "product": {
        "productId": 1,
        "name": "Sega Megadrive",
        "description": "greatest console ever!!"
    },
    "userId": 89,
    "amount": 1000
}

```

#### More information on all the endpoints are available and can be found within the controller class.
