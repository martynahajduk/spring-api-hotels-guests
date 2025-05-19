package be.kdg.programming5.webapi;

import be.kdg.programming5.domain.GuestRoom;
import be.kdg.programming5.domain.User;
import be.kdg.programming5.security.CustomUserDetails;
import be.kdg.programming5.service.AuthorizationService;
import be.kdg.programming5.service.GuestRoomService;
import be.kdg.programming5.webapi.dto.BookingDto;
import be.kdg.programming5.webapi.dto.GuestRoomDto;
import be.kdg.programming5.webapi.dto.GuestRoomMapper;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guest-rooms")
public class GuestRoomApiController {

    private final GuestRoomService guestRoomService;
    private final GuestRoomMapper guestRoomMapper;
    private final AuthorizationService authorizationService;

    public GuestRoomApiController(GuestRoomService guestRoomService,
                                  GuestRoomMapper guestRoomMapper,
                                  AuthorizationService authorizationService) {
        this.guestRoomService = guestRoomService;
        this.guestRoomMapper = guestRoomMapper;
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public List<GuestRoomDto> getAll() {
        return guestRoomService.getAll().stream()
                .map(guestRoomMapper::toDto)
                .toList();
    }

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN') or @authorizationService.isOwner(#id, principal)") TODO i needed to put it in comment for week 11 assignment
    public ResponseEntity<GuestRoomDto> add(@RequestBody @Valid GuestRoomDto dto,
                                            @AuthenticationPrincipal CustomUserDetails user) {
        User owner = (user != null) ? user.getUser() : null;
        GuestRoom guestRoom = guestRoomService.add(dto, owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(guestRoomMapper.toDto(guestRoom));
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @authorizationService.isOwner(#id, principal)")
    public ResponseEntity<GuestRoomDto> patch(@PathVariable Integer id,
                                              @RequestBody @Valid GuestRoomDto dto, @AuthenticationPrincipal CustomUserDetails user) {
        GuestRoom updated = guestRoomService.update(id, dto);
        return ResponseEntity.ok(guestRoomMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or @authorizationService.isOwner(#id, principal)")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        guestRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<BookingDto> search(@RequestParam String guestName) {
        return guestRoomService.searchByGuestName(guestName);
    }



}
