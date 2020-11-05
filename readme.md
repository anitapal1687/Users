
Tested with
* Java 8
* Maven
* H2 Database

## How to run this?
$ git clone https://github.com/anitapal1687/Users.git

$ spring-boot:run

Endpoints:
1) /upload- POST- to upload csv file and save the data to database.
2) /users- GET- to get all the user details. Different filters has been add like maximum salary, minimum salary, sorting.
3) /users/{id}- GET - to get the details of a particular user.
4) /users/create - POST - to create a new user. 
5) /users- PUT- to update user details
6) /users/{id} - DELETE - to delete mapping of a user

