package be.kdg.programming5.webapi;

import be.kdg.programming5.domain.GuestRoom;
import be.kdg.programming5.domain.Role;
import be.kdg.programming5.domain.User;
import be.kdg.programming5.security.CustomUserDetails;
import be.kdg.programming5.service.AuthorizationService;
import be.kdg.programming5.service.GuestRoomService;
import be.kdg.programming5.webapi.dto.GuestRoomDto;
import be.kdg.programming5.webapi.dto.GuestRoomMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.security.authorization.AuthorizationDeniedException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class GuestRoomApiControllerUnitTest {
    @Autowired
    private GuestRoomApiController guestRoomApiController;

    @MockitoBean
    private GuestRoomService guestRoomService;

    @MockitoBean
    private GuestRoomMapper guestRoomMapper;

    @MockitoBean
    private AuthorizationService authorizationService;

    @Test
    void shouldUpdateGuestRoomIfAuthorized() {
        Integer id = 17645;
        UUID guestId = UUID.randomUUID();
        UUID hotelId = UUID.randomUUID();
        GuestRoomDto inputDto = new GuestRoomDto(id, guestId, 101, hotelId);
        GuestRoom updatedGuestRoom = new GuestRoom();

        User mockUser = new User();
        mockUser.setUsername("admin");
        mockUser.setPassword("password");
        mockUser.setRole(Role.ADMIN);

        CustomUserDetails user = new CustomUserDetails(mockUser);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
        SecurityContextHolder.setContext(context);

        when(authorizationService.isOwner(id, user)).thenReturn(true);
        when(guestRoomService.update(id, inputDto)).thenReturn(updatedGuestRoom);
        when(guestRoomMapper.toDto(updatedGuestRoom)).thenReturn(inputDto);

        var response = guestRoomApiController.patch(id, inputDto, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(inputDto, response.getBody());
    }

    @Test
    void shouldThrowAccessDeniedExceptionIfUserNotAuthorized() {
        Integer id = 2137;
        GuestRoomDto dto = new GuestRoomDto(id, UUID.randomUUID(), 101, UUID.randomUUID());
        User mockUser = new User();
        mockUser.setUsername("user");
        mockUser.setPassword("kremowka2137");
        mockUser.setRole(Role.USER);

        CustomUserDetails user = new CustomUserDetails(mockUser);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
        SecurityContextHolder.setContext(context);

        when(authorizationService.isOwner(id, user)).thenReturn(false);

        assertThrows(AuthorizationDeniedException.class,
                () -> guestRoomApiController.patch(id, dto, user));

    }
}

