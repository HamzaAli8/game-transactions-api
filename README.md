Game-Transaction-API

This demo project showcases a simple RESTful API which has several endpoints that can be accessed and used to manipulate data within a provided database. 

Built with Java and Spring using Hibernate (ORM for Spring), which supports CRUD operations.

The project's main data objects/resources are items with specific attributes like name, amount, description user and transaction id. 

## Installation & Run

```
# Download this project

Firtslt navigate to github.com/HamzaAli8/game-transactions-api

Clone this repo to your local machine

For simplicity in testing this demo API, utilises an in-memory H2 database to store transactions, but can easily be adjusted to use a production database of your liking using MySQL, RDS, etc

Navigate to the project directory and execute ./mvnw spring-boot:run

Windows users can execute mvnw.cmd spring-boot:run

```



```go
func GetConfig() *Config {
	return &Config{
		DB: &DBConfig{
			Dialect:  "mysql",
			Username: "guest",
			Password: "Guest0000!",
			Name:     "todoapp",
			Charset:  "utf8",
		},
	}
}
```

```bash
# Build and Run
cd go-todo-rest-api-example
go build
./go-todo-rest-api-example

# API Endpoint : http://127.0.0.1:3000
```

## Structure
```
├── app
│   ├── app.go
│   ├── handler          // Our API core handlers
│   │   ├── common.go    // Common response functions
│   │   ├── projects.go  // APIs for Project model
│   │   └── tasks.go     // APIs for Task model
│   └── model
│       └── model.go     // Models for our application
├── config
│   └── config.go        // Configuration
└── main.go
```

## API

#### /projects
* `GET` : Get all projects
* `POST` : Create a new project

#### /projects/:title
* `GET` : Get a project
* `PUT` : Update a project
* `DELETE` : Delete a project

#### /projects/:title/archive
* `PUT` : Archive a project
* `DELETE` : Restore a project 

#### /projects/:title/tasks
* `GET` : Get all tasks of a project
* `POST` : Create a new task in a project

#### /projects/:title/tasks/:id
* `GET` : Get a task of a project
* `PUT` : Update a task of a project
* `DELETE` : Delete a task of a project

#### /projects/:title/tasks/:id/complete
* `PUT` : Complete a task of a project
* `DELETE` : Undo a task of a project
