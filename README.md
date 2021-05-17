# GEORGE HIKING TOURS

# Description and Tech Stack
This project has been created for a tour guide whose name ise George. He will manage the bookings that the hikers that want to join.

You should be able to start the example application by executing com.hiking.GeorgeTourApplication,
which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

The project is based on a web service which uses the following technologies:

* Java 1.8
* Spring Boot
* Database H2 (In-Memory)
* Maven


To solve demands of George this;

4 REST API has been designed.

#REST APIs

* /booking/create
* /booking/cancel
* /booking/view_booking
* /booking/view_bookings_with_date


## /booking/create
method: POST
request: {
"hikerSet": [
{
"age": 35,
"hikerName": "john",
"hikerSurname": "brown"
},
{
"age": 32,
"hikerName": "julie",
"hikerSurname": "parker"
}
],
"tourId": 1
}

Hikers can make a booking for one or more hikers with them.

## /booking/cancel
method: POST

{
"bookingNumber": "12345678",
"surname": "brown"
}

bookingNumber and surname matching is mandatory to cancel the booking. 

## /booking/view_booking
method: GET

bookingNumber: {bookingNumber that you obtained after your booking had been created}

Hikers can view their bookings. They can view how many hikers will join and which tour and when is the date


## /booking/view_bookings_with_date
method: GET

localDate: The parameter has a format: dd/MM/yyyy

If you don't give a parameter with right format, you will encounter "Please give date parameter with this format: dd/MM/yyyy" error message.

The Guide George can view booking for a specific date. He can view the bookings with hikers and which tour and when it will start and end



# Build and Run It
After you download the project you can maven clean install and run GeorgeTourApplication.java class

OR

you can also run it in a docker container with the commands below:

* docker build -t george_hiking_tours .
* docker run -p 8080:8080 --name george_tours george_hiking_tours


