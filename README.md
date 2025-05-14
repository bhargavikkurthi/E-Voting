## Overview

The E-Voting System is designed to facilitate online elections by allowing users to create elections, cast votes, and monitor election results. Built using Spring Boot, Hibernate, and MySQL, the system provides a secure and efficient way to manage elections with CRUD operations.


---


## Features

* Election Management: Create elections with unique titles.

* Choice Management: Add multiple choices for each election.

* User Management: Register users with unique usernames.

* Voting System: Allow users to cast votes for elections.

* Result Calculation: Retrieve election results, including total votes and the winning candidate.

* Data Persistence: Uses MySQL for data storage.

* REST API Endpoints for seamless integration.


---


## Tech Stack

* Java 21

* Spring Boot 3.2.5

* Hibernate (JPA for ORM)

* MySQL (Database)

* Postman (For API testing)


---


## Flowchart

1. Create Election → 2. Add Choices → 3. Register Voters → 4. Cast Votes → 5. Monitor Results


---


## API Endpoints

### Election Endpoints

| Method       | Endpoint    | Description                         |
| ------------ |:------------|:------------------------------------|
| GET          | /elections  | Retrieves the list of all elections |
| POST         | /elections  | Creates a new election              |


### ElectionChoice Endpoints

| Method       | Endpoint                | Description                                |
| ------------ |:------------------------|:-------------------------------------------|
| POST         | /election-choices       | Adds a new election choice                 |
| GET          | /election-choices       | Fetches the list of all election choices   |
| POST         | /election-choices/count | Retrieves all choices for a given election |


### User Endpoints

| Method       | Endpoint | Description                        |
| ------------ |:---------|:-----------------------------------|
| POST         | /users   | Creates a new user in the database |
| GET          | /users   | Fetches the list of all users      |


### Vote Endpoints

| Method       | Endpoint              | Description                                      |
| ------------ |:----------------------|:-------------------------------------------------|
| GET          | /votes                | Fetches the list of all votes                    |
| POST         | /votes                | Registers a new vote in the database             |
| GET          | /votes/count          | Fetches the total count of votes                 |
| POST         | /votes/count/election | Returns the count of total votes for an election |


### Results Endpoints

| Method       | Endpoint        | Description                                  |
| ------------ |:----------------|:---------------------------------------------|
| POST         | /results/winner | Retrieves the winning choice for an election |



### 📄 Example Request: Get Users (Paginated)

Fetch a paginated list of users from the system.

### 🔹 HTTP Request

GET /get/users?page=0&size=5

### 🔹 Description

Returns the first 5 users.  
Pagination starts from page index `0`.

### 🔹 Query Parameters

| Parameter | Type   | Description                          |
| --------- |:-------|:-------------------------------------|
| `page`    | int    | Page number (e.g., `0` = first page) |
| `size`    | int    | Number of users per page             |
