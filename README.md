# Event management system

Created RESTful service that manages event data based on a user's geographical location and a specified date.



## How To Run Project:
    - Clone this repository to your local machine.
    - Wait a few minutes to install all dependencies.
    - Create a database in MySQL, then paste the database name in the application.properties file. You can choose any name.
    - Run the application

    


## Here are the APIs you can test using Swagger or Postman:

![image](https://github.com/ayushraj12009/gyangroveassessment-/assets/51042913/c9dd917c-3c80-4a61-a0cf-0017ab68943e)


## API Reference

#### Add Event In DB


  POST-> http://localhost:8080/events/addEvent


Parameter (If you know distance or weather details then fill other wise don't fill)

| Parameter | Type     | Temp Data                       
| :-------- | :------- | :-------------------------------- 
| `latitude`    | `Double` | 38.33354302
| `longitude`   | `Double` | 157.9579286
| `event_Name`  | `string` | Between thus table
| `city_Name`   | `string` | Port Rebeccaberg
| `date`        | `string` | 2024-03-01
| `time`        | `string` | 18:00:00



#### Find Details From DB


  GET-> http://localhost:8080/events/find?latitude=38.33354302&longitude=157.9579286&searchDate=2024-07-20
  
 -> Copy this url and paste in postman value automatically added then change para,s value


| Parameter | Type     | Temp Data                       
| :-------- | :------- | :-------------------------------- 
| `latitude`    | `Double` | 38.33354302
| `longitude`   | `Double` | 157.9579286
| `date`        | `string` | 2024-03-01


### Technology Stack:
## Java Spring Boot:
- RESTful API Development: Java Spring Boot provides a robust framework for building RESTful APIs quickly and efficiently. It offers features like Spring Web MVC, which simplifies the development of API endpoints.
- Dependency Injection: Spring Boot's dependency injection mechanism allows for easy integration with external APIs and services.
- Spring Data: Spring Data provides convenient abstractions for data access, making it easier to work with databases.

## MySQL:
- Relational Database: Since the dataset provided is structured and relates to events with various attributes, a relational database like MySQL is suitable for storing and querying this data efficiently.
- ACID Compliance: MySQL ensures data integrity and consistency by adhering to ACID (Atomicity, Consistency, Isolation, Durability) properties.
- Scalability: MySQL supports horizontal and vertical scalability, allowing the system to handle increased loads and data volumes as the application grows.

### Challenges
- When I started this project, the initial challenge I faced was understanding the logic required. It took me half of the day to grasp it. Then, I began by implementing the functionality to calculate the distance and fetch weather data.
- The next challenge arose when I couldn't extract JSON data properly within the Spring Boot framework. I encountered issues with detecting dependencies, particularly with ObjectMapper. Despite numerous attempts and injecting multiple dependencies with different versions, I couldn't resolve it initially. However, eventually, I managed to overcome this hurdle by carefully installing the required dependencies and successfully utilized ObjectMapper in the project.
- The third challenge was that I inadvertently converted the date into a string, which made it difficult to show data for the next 15 days. To fix this, I had to come up with several ideas and try them out until I found a solution that worked.

### Project Demo

https://bit.ly/43G3FbS
