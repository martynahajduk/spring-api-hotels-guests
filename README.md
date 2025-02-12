# Programming 5 Project
## Hotel Guest Management Application

### Developer: Martyna Hajduk, ACS201 2024-2025

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
