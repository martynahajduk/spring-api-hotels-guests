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
"roomNumbers": [
101
]
},
{
"name": "Lysander Quinn",
"dateOfBirth": "1988-08-24",
"nationality": "BELGIAN",
"hotelName": "Steigenberger Wiltcher's",
"roomNumbers": [
201
]
},
{
"name": "Dorian Thorne",
"dateOfBirth": "1985-02-03",
"nationality": "BRAZILIAN",
"hotelName": "Hotel De Witte Lelie",
"roomNumbers": [
401,
501
]
},
{
"name": "Felix Hawke",
"dateOfBirth": "1991-11-10",
"nationality": "CROATIAN",
"hotelName": "Hotel De Witte Lelie",
"roomNumbers": [
501
]
},
{
"name": "Niamh Callahan",
"dateOfBirth": "1987-07-19",
"nationality": "SPANISH",
"hotelName": "Hotel 't Sandt Antwerpen",
"roomNumbers": [
101
]
},
{
"name": "Isolde Ravenswood",
"dateOfBirth": "1994-12-25",
"nationality": "AMERICAN",
"hotelName": "Hotel 't Sandt Antwerpen",
"roomNumbers": [
201
]
},
{
"name": "Orion Ashford",
"dateOfBirth": "1990-03-30",
"nationality": "HUNGARIAN",
"hotelName": "Hotel Julien Antwerp",
"roomNumbers": [
301,
401
]
},
{
"name": "Selene Hawthorne",
"dateOfBirth": "1989-06-14",
"nationality": "GREEK",
"hotelName": "Hotel Julien Antwerp",
"roomNumbers": [
401
]
},
{
"name": "Calista Evernight",
"dateOfBirth": "1995-01-18",
"nationality": "ROMANIAN",
"hotelName": "Pillows Grand Hotel Reylof",
"roomNumbers": [
301
]
},
{
"name": "Soren Albrecht",
"dateOfBirth": "1983-09-05",
"nationality": "ROMANIAN",
"hotelName": "Pillows Grand Hotel Reylof",
"roomNumbers": [
401
]
},
{
"name": "Alina Dimova",
"dateOfBirth": "2004-11-05",
"nationality": "MOLDOVAN",
"hotelName": "Pillows Grand Hotel Reylof",
"roomNumbers": [
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
"roomNumbers": [
501
]
}
]

### Searching for one guest

#### UUID changes each time when using create-drop, so it doesn't work with this UUID anymore, it works, just try with actual uuid from database :)

GET http://localhost:8080/api/guests/7c0d0d1a-9fe8-4c45-8c8a-d0ffbcd1ae79
<br>Accept: application/json

**Response**: `HTTP/1.1 200 - OK`

{
"name": "Aria Voss",
"dateOfBirth": "1993-04-11",
"nationality": "AUSTRALIAN",
"hotelName": "Steigenberger Wiltcher's",
"roomNumbers": [
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

#### UUID changes each time when using create-drop, so it doesn't work with this UUID anymore, it works, just try with actual uuid from database :)

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

# Week 4 & 5

## ROLES

#### ADMIN

username: admin_test
<br>password: admin123

#### USER

username: user_test
<br>password: guest123

## Access Control

### Publicly Accessible Pages

- [Landing Page](http://localhost:8080/)
- [Login Page](http://localhost:8080/login)
- [Hotels List](http://localhost:8080/hotels)

### Pages that Require Basic Authentication (USER)

- [Main Menu](http://localhost:8080/mainmenu)
- [Guests List](http://localhost:8080/guests)
- [Guest Details](http://localhost:8080/guests/guest/{id})
- [Hotel Details](http://localhost:8080/hotels/hotel/{id})

### Pages that Require Admin Authentication (ADMIN)

- [Add Hotel](http://localhost:8080/hotels/addhotel)
- [Add Guest](http://localhost:8080/guests/addguest)

## Permissions

### Unauthenticated Users

Allowed:

- See [Landing Page](http://localhost:8080/), [Login Page](http://localhost:8080/login) and [Hotels List](http://localhost:8080/hotels) but without hotel ID and location.

Not Allowed:

- See [Main Menu](http://localhost:8080/mainmenu), [Guests List](http://localhost:8080/guests), [Guest Details](http://localhost:8080/guests/guest/{id}) and [Hotel Details](http://localhost:8080/hotels/hotel/{id}).
- See [Add Hotel](http://localhost:8080/hotels/addhotel) and [Add Guest](http://localhost:8080/guests/addguest).
- Add or modify any data.

### USER authentication

Allowed:

- All pages listed under unauthenticated users
- [Main Menu](http://localhost:8080/mainmenu) — _with links to Guests and Hotels_
- [Guests List](http://localhost:8080/guests) — _limited view (no guest ID, no hotel or room info)_
- [Guest Details](http://localhost:8080/guests/guest/{id}) — _limited view (no hotel/room info, no edit options)_
- [Hotel Details](http://localhost:8080/hotels/hotel/{id}) — _limited view (no hotel ID, no guest list)_
- Modify or delete GuestRoom entries **only if they are the owner**, for now User has permissions for guests **Alina Dimova** and **Carys Coetzee**.

Not Allowed:

- Add new hotels ([Add Hotel](http://localhost:8080/hotels/addhotel)) or guests ([Add Guest](http://localhost:8080/guests/addguest)).
- Modify data of other guests.
- Access guest or hotel internal IDs.
- See full guest ([Guest Details](http://localhost:8080/guests/guest/{id})) or hotel ([Hotel Details](http://localhost:8080/hotels/hotel/{id})) details.

### ADMIN authentication

Allowed:

- All user-level permissions
- Full view and control over [Guest List](http://localhost:8080/guests), [Guest Details](http://localhost:8080/guests/guest/{id}) and [Hotel Details](http://localhost:8080/hotels/hotel/{id}).
- Add new hotels ([Add Hotel](http://localhost:8080/hotels/addhotel)).
- Add new guests ([Add Guest](http://localhost:8080/guests/addguest)).
- Edit/delete **any** GuestRoom entry.
- See all entity IDs, guests in hotels, and room assignments.

# Testing

## Week 6 & 8 & 10

### Spring Test Profile

- Profile: `test`
- File: `application-test.properties`
- Usage: `./gradlew test -Dspring.profiles.active=test`

### Test Database Credentials (H2 in-memory)

username=sa<br>
password=_(no password)_

### Test Classes

- API tests: `GuestRoomApiControllerTest`
- MVC tests: `GuestControllerTest`
- Role verification: `GuestRoomApiControllerTest`GuestControllerTest``
- Repository: `HotelRepositoryTest`
- Service: `GuestRoomServiceTest`
- Mocking tests: `GuestRoomApiControllerUnitTest`, `GuestRoomServiceUnitTest`
- Tests with `verify(...)`: `GuestRoomApiControllerUnitTest`, `GuestRoomServiceUnitTest`

### Code Coverage Screenshots

![Screenshot 2025-04-26 2006279.png](src/main/resources/static/images/Screenshot%202025-04-26%202006279.png)
![Screenshot 2025-04-26 2006280.png](src/main/resources/static/images/Screenshot%202025-04-26%202006280.png)

### CI/CD: GitLab pipeline

You can find successful test report [here](https://gitlab.com/kdg-ti/programming-5/projects-24-25/acs201/martyna.hajduk/spring-backend/-/pipelines/1819884789/test_report?job_name=test).

## Week 12
### Bootstrap Icon

**Icons used:**  
`bi-building`, `bi-building-add`, `bi-person`, `bi-person-plus`

**Used in pages:**
- [/](http://localhost:8080/mainmenu)
- [/mainmenu](http://localhost:8080/)
- **Usage:** Appears in cards in `index.html` and `mainmenu.html`.

### Custom Client-Side Validation

- **Form validated:** Guest Add Form
- **Where to find it:**
   - **URL:** [Add Guest](http://localhost:8080/guests/addguest)
   - **Source file:** `js/guest-validation.js`,`js/add-guest.js`
- **Description:**  
  The form validates that all required fields (guestId, roomNumber, hotelId) are filled in before allowing submission. If any are missing or invalid, submission is blocked and user feedback is shown.

### Extra JavaScript Dependencies
#### 1. `dayjs.js`

- **Where to find it:**
   - **URL:** [Add Guest](http://localhost:8080/guests/addguest)
   - **Source file:** `js/age.js`,`js/add-guest.js`
- **Purpose:**  
Checks if added guest is above 18, if not - gives a warning that guest is below 18 and says the actual age.
- **How to use:**  
Just try to add the guest below 18.


#### 2. `hotkeys.js`

- **Where to find it:**
   - **URL:** [Add Guest](http://localhost:8080/guests/addguest)
   - **Source file:** `js/hotkey-setup.js`,`js/add-guest.js`
- **How to use:**
   - `Ctrl+Enter` you can submit form automatically using this custom shortcut.

