# Event management system

Created RESTful service that manages event data based on a user's geographical location and a specified date.



## How To Run Project:


```bash
  https://github.com/ayushraj12009/apiwizAssignment.git
```
    - Clone this repository to your local machine.
    - Wait a few minutes to install all dependencies.
    - Create a database in MySQL, then paste the database name in the application.properties file. You can choose any name.
    - Run the application

    


## Here are the APIs you can test using Swagger or Postman:
![Uploading image.png…]()


## API Reference

#### Add Event In DB

```http
  POST-> http://localhost:8080/events/addEvent
```

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

```http
  GET-> http://localhost:8080/events/find?latitude=38.33354302&longitude=157.9579286&searchDate=2024-07-20
 -> Copy this url and paste in postman value automatically added then change para,s value
```

| Parameter | Type     | Temp Data                       
| :-------- | :------- | :-------------------------------- 
| `latitude`    | `Double` | 38.33354302
| `longitude`   | `Double` | 157.9579286
| `date`        | `string` | 2024-03-01



