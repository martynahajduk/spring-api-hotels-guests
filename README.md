# Programming 5 Project 2024-2025
## Hotel Guest Management Application

### Developer: Martyna Hajduk 
### E-mail: martyna.hajduk@student.kdg.be
### Student ID:
### Group: ACS201 

## Entities and Relationships
### Entities:
1. **Hotel**:
    - Attributes: `hotelId`, `name`, `location`
    - Relationships: A hotel can have multiple rooms and guests.

2. **Room**:
    - Attributes: `roomNumber`, `price`, `floor`, `roomType`(enum)
    - Relationships: Each room belongs to a hotel and can accommodate multiple guests.

3. **Guest**:
    - Attributes: `guestId`, `name`, `dateOfBirth`, `nationality`(enum)
    - Relationships: A guest can stay in multiple rooms and is associated with one hotel.

### Relationships:
- **Hotel ↔ Rooms**: One-to-many (A hotel can have many rooms, but a room belongs to one hotel).
- **Room ↔ Guests**: Many-to-many (A room can be rented by multiple guests over time, and a guest can stay in different rooms).
- **Hotel ↔ Guests**: One-to-many (A hotel can have many guests, but each guest is associated with one hotel).

## Build and Run Instructions
### 1. Clone the repository
First, clone the repository to your local machine:
```bash
git clone https://gitlab.com/kdg-ti/programming-5/projects-24-25/acs201/martyna.hajduk/spring-backend.git
```
### 2. Install Docker
Ensure Docker and Docker Compose are installed on your system.You can check if Docker is installed by running:
```bash
docker --version
docker-compose --version
```
If the Docker is not installed, follow the [Docker installation guide](https://docs.docker.com/get-docker/).
### 3. Set up PostgreSQL with Docker
We are using Docker Compose to run a PostgreSQL database. To start the database, use the following command:
```bash
docker-compose up -d
```
This will start the PostgreSQL container with the configuration specified in docker-compose.yml.
### 4. Build the project and run the application

# Week 2 - http requests
### Fetching all guests
GET http://localhost:8080/api/guests
<br>Accept: application/json

**Response**: `HTTP/1.1 200 - OK`

[
{
"name": "Aria Voss",
"dateOfBirth": "1993-04-11",
"nationality": "AUSTRALIAN",
"hotelName": "Steigenberger Wiltcher's",
"RoomNumbers": [
101
]
},
{
"name": "Lysander Quinn",
"dateOfBirth": "1988-08-24",
"nationality": "BELGIAN",
"hotelName": "Steigenberger Wiltcher's",
"RoomNumbers": [
201
]
},
{
"name": "Dorian Thorne",
"dateOfBirth": "1985-02-03",
"nationality": "BRAZILIAN",
"hotelName": "Hotel De Witte Lelie",
"RoomNumbers": [
401,
501
]
},
{
"name": "Felix Hawke",
"dateOfBirth": "1991-11-10",
"nationality": "CROATIAN",
"hotelName": "Hotel De Witte Lelie",
"RoomNumbers": [
501
]
},
{
"name": "Niamh Callahan",
"dateOfBirth": "1987-07-19",
"nationality": "SPANISH",
"hotelName": "Hotel 't Sandt Antwerpen",
"RoomNumbers": [
101
]
},
{
"name": "Isolde Ravenswood",
"dateOfBirth": "1994-12-25",
"nationality": "AMERICAN",
"hotelName": "Hotel 't Sandt Antwerpen",
"RoomNumbers": [
201
]
},
{
"name": "Orion Ashford",
"dateOfBirth": "1990-03-30",
"nationality": "HUNGARIAN",
"hotelName": "Hotel Julien Antwerp",
"RoomNumbers": [
301,
401
]
},
{
"name": "Selene Hawthorne",
"dateOfBirth": "1989-06-14",
"nationality": "GREEK",
"hotelName": "Hotel Julien Antwerp",
"RoomNumbers": [
401
]
},
{
"name": "Calista Evernight",
"dateOfBirth": "1995-01-18",
"nationality": "ROMANIAN",
"hotelName": "Pillows Grand Hotel Reylof",
"RoomNumbers": [
301
]
},
{
"name": "Soren Albrecht",
"dateOfBirth": "1983-09-05",
"nationality": "ROMANIAN",
"hotelName": "Pillows Grand Hotel Reylof",
"RoomNumbers": [
401
]
},
{
"name": "Alina Dimova",
"dateOfBirth": "2004-11-05",
"nationality": "MOLDOVAN",
"hotelName": "Pillows Grand Hotel Reylof",
"RoomNumbers": [
301,
401,
501
]
},
{
"name": "Carys Coetzee",
"dateOfBirth": "2004-06-08",
"nationality": "SOUTH_AFRICAN",
"hotelName": "Pillows Grand Hotel Reylof",
"RoomNumbers": [
501
]
}
]


### Searching for one guest
#### (UUID changes each time, so it doesn't work with this UUID anymore)
GET http://localhost:8080/api/guests/7c0d0d1a-9fe8-4c45-8c8a-d0ffbcd1ae79
<br>Accept: application/json

**Response**: `HTTP/1.1 200 - OK`

{
"name": "Aria Voss",
"dateOfBirth": "1993-04-11",
"nationality": "AUSTRALIAN",
"hotelName": "Steigenberger Wiltcher's",
"RoomNumbers": [
101
]
}

### Searching for one guest, but it doesn't exist
GET http://localhost:8080/api/guests/00000000-0000-0000-0000-000000000000
<br>Accept: application/json

**Response**: `HTTP/1.1 404 - Not Found`

{
"error": "Guest not found"
}

### Deleting guest
#### (UUID changes each time, so it doesn't work with this UUID anymore)
DELETE http://localhost:8080/api/guests/1242cb96-305f-4ced-a8f6-4fb096c65da7

**Response**: `HTTP/1.1 404 - Not Found`

### Deleting guest that doesn't exist
DELETE http://localhost:8080/api/guests/00000000-0000-0000-0000-000000000000

**Response**: `HTTP/1.1 404 - Not Found`

# Week 3 - http requests
### POST add guest OK
POST http://localhost:8080/api/guests
<br>Accept: application/json
<br>Content-Type: application/json

{
"name": "Alice",
"dateOfBirth": "1995-10-10",
"nationality": "BELGIAN",
"hotelName": "Hotel Julien Antwerp",
"RoomNumbers": [201]
}

**Response**: `HTTP/1.1 201 - Created`


### POST add guest - BAD REQUEST
POST http://localhost:8080/api/guests
<br>Accept: application/json
<br>Content-Type: application/json

{
"name": "A",
"dateOfBirth": "2025-10-10",
"nationality": "POLAND",
"hotelName": "Hilton",
"RoomNumbers": []
}

**Response**: `HTTP/1.1 400 - Bad Request`

{
"timestamp": "2025-03-04T21:04:48.610+00:00",
"status": 400,
"error": "Bad Request",
"path": "/api/guests"
}
