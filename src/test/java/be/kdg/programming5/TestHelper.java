package be.kdg.programming5;

import be.kdg.programming5.domain.*;
import be.kdg.programming5.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class TestHelper {

    @Autowired private UserRepository userRepository;
    @Autowired private GuestRepository guestRepository;
    @Autowired private HotelRepository hotelRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private GuestRoomRepository guestRoomRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private ObjectMapper objectMapper;

    public void cleanUp() {
        guestRoomRepository.deleteAll();
        guestRepository.deleteAll();
        roomRepository.deleteAll();
        hotelRepository.deleteAll();
        userRepository.deleteAll();
    }

    public void seedAdmin(String username) {
        if (userRepository.findByUsername(username).isPresent()) return;
        User admin = new User();
        admin.setUsername("admin_test");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
    }

    public void seedUser(String username) {
        if (userRepository.findByUsername(username).isPresent()) return;

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode("user"));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Transactional
    public Hotel createHotel(String name, String location) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setLocation(location);
        hotel = hotelRepository.save(hotel);
        return hotel;
    }

    @Transactional
    public Room createRoom(int number, RoomType type, double price, int floor, Hotel hotel) {
        //    private Hotel hotel;
        Room room = new Room();
        room.setRoomNumber(number);
        room.setRoomType(type);
        room.setPrice(price);
        room.setFloor(floor);
        room.setHotel(hotel);
        return roomRepository.save(room);
    }

    @Transactional
    public Guest createGuest(String name, Nationality nationality, LocalDate dob, Hotel hotel) {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setNationality(nationality);
        guest.setDateOfBirth(dob);
        guest.setHotel(hotel);
        guest = guestRepository.save(guest);
        return guest;
    }

    @Transactional
    public GuestRoom createGuestRoom(Guest guest, Room room, User owner) {
        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setGuest(guest);
        guestRoom.setRoom(room);
        guestRoom.setOwner(owner);
        guestRoom = guestRoomRepository.save(guestRoom);
        return guestRoom;
    }

    public User findUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Transactional
    public GuestRoom createFullGuestRoomSetup(
            String username,
            String hotelName,
            String hotelLocation,
            int roomNumber,
            RoomType roomType,
            double price,
            int floor,
            String guestName,
            Nationality nationality,
            LocalDate dob
    ) {
        User owner = findUser(username);

        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        hotel.setLocation(hotelLocation);
        hotel = hotelRepository.save(hotel);

        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setRoomType(roomType);
        room.setPrice(price);
        room.setFloor(floor);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        Guest guest = new Guest();
        guest.setName(guestName);
        guest.setNationality(nationality);
        guest.setDateOfBirth(dob);
        guest.setHotel(hotel);
        guest = guestRepository.save(guest);

        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setGuest(guest);
        guestRoom.setRoom(room);
        guestRoom.setOwner(owner);
        return guestRoomRepository.save(guestRoom);
    }

    public String asJson(Object o) throws Exception {
        return objectMapper.writeValueAsString(o);
    }


}
