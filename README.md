## Project Name
Hair Salon - Epicodus Java Project 3

### Features
This is a application for a Hair Salon/Barber shop to track their stylists/barbers and their clients in a database that tracks names, notes, and which clients belong to which barbers.  

#### Author
Eric Krause


## Setup/Installation Requirements

* Download zip or clone files to desktop.
* In PSQL:
  -CREATE DATABASE hair_salon;
  -CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, detail varchar);
  -CREATE TABLE clients (id serial PRIMARY KEY, name varchar, notes varchar, stylist_id integer);
* Open console to the project package folder and run "gradle run" .
* Go To http://localhost:4567 in a web browser.


## Specifications

|Behavior|Input|Output|
|---|---|---|
|The application will take the name of a stylist.| 'Mahesh Ankur' | 'Mahesh Ankur' |
|The application will take details of the stylist. | 'Mahesh produces great fade and high and tight hair styles' | 'Mahesh produces great fade and high and tight hair styles' |
|The application will return all stylists/barbers. | allStylists | {'Peter', 'Paul', 'John'} |
|The application will take take the name of a client. | 'SGT Smith' | 'SGT Smith' |
|The application will take notes for each client | 'SGT Smith comes every 1st and 3rd Friday, High and Tight with a zero up the sides and fingerlength on top' | 'SGT Smith comes every 1st and 3rd Friday, High and Tight with a zero up the sides and fingerlength on top'|
|The application will assign a stylistId to each client. | '2' | '2' |
|The application will return all clients. | allClients | '{SGT Smith, 1LT Clark}' |
|The application will update client and stylist information. | 'SGT Smith, SGT Smith comes every 1st and 3rd Friday, High and Tight with a zero up the sides and fingerlength on top' | 'SGT Smith, SGT Smith visits every 3rd Thursday and requests a buzz cut' |
|The application will delete a stylist or a client. | 'SGT Smith' | 'null' |

## Known Bugs

Application has issues with the links on the homepage directing to the correct pages (href in html of vtl files)

## Support and contact details

Git Username of Author: krause197

## Technologies Used

Java, jUnit, Gradle, Spark, PostgreSQL

### License

This work can be used under the MIT License.
Copyright (c) 2016 Eric Krause
