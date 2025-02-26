package be.kdg.programming5.webapi;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.service.GuestServiceInterface;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {
    private final GuestServiceInterface guestService;

    public GuestApiController(final GuestServiceInterface guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<GuestDto> getAllGuests() {
        return guestService.getAllGuests().stream()
                .map(GuestDto::fromGuest)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> getGuestById(@PathVariable("id") UUID id) {
        Guest guest = guestService.findGuestById(id);
        if (guest != null) {
            return ResponseEntity.ok(GuestDto.fromGuest(guest));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable("id") UUID id) {
        boolean deleted = guestService.deleteGuest(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GuestDto> addGuest(@Valid @RequestBody GuestDto guestDto) {
        Guest newGuest = guestService.addGuest(
                guestDto.name(),
                guestDto.dateOfBirth(),
                guestDto.nationality(),
                guestDto.hotelName(),
                guestDto.RoomNumbers()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(GuestDto.fromGuest(newGuest));
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<GuestDto> updateGuest(@PathVariable UUID id, @Valid @RequestBody GuestDto guestDto) {
//        Guest updatedGuest = guestService.updateGuest(id, guestDto.name(), guestDto.dateOfBirth(), guestDto.nationality()); //TODO update guest
//        return ResponseEntity.ok(GuestDto.fromGuest(updatedGuest));
//    }
}

