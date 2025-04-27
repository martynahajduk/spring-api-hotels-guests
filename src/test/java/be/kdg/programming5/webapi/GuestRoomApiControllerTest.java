package be.kdg.programming5.webapi;

import be.kdg.programming5.TestHelper;
import be.kdg.programming5.domain.*;
import be.kdg.programming5.webapi.dto.GuestRoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GuestRoomApiControllerTest {

    @Autowired private TestHelper testHelper;
    @Autowired private MockMvc mockMvc;
    private Integer guestRoomId;
    private Guest guest;
    private Room room;
    private Hotel hotel;

    @BeforeEach
    void setup() {
        testHelper.cleanUp();
        testHelper.seedAdmin("admin_test");
        testHelper.seedUser("user_test");
        testHelper.seedUser("other_user");

        GuestRoom created = testHelper.createFullGuestRoomSetup(
                "user_test",
                "Test Hotel", "Test City",
                601, RoomType.DELUXE, 500, 6,
                "Test Guest", Nationality.BELGIAN, LocalDate.of(1990, 1, 1)
        );

        guestRoomId = created.getId();
        guest = created.getGuest();
        room = created.getRoom();
        hotel = guest.getHotel();
    }

    @Test
    @WithUserDetails(value = "user_test", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void shouldPatchGuestRoomAsOwner() throws Exception {
        var dto = new GuestRoomDto(null, guest.getGuestId(), room.getRoomNumber(), hotel.getHotelId());

        mockMvc.perform(patch("/api/guest-rooms/" + guestRoomId)
                        .with(csrf())
                        .contentType("application/json")
                        .content(testHelper.asJson(dto)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = "admin_test", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void shouldDeleteGuestRoomAsAdmin() throws Exception {
        mockMvc.perform(delete("/api/guest-rooms/" + guestRoomId).with(csrf()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(value = "other_user", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void shouldNotPatchGuestRoomAsUnauthorizedUser() throws Exception {
        var dto = new GuestRoomDto(null, guest.getGuestId(), room.getRoomNumber(), hotel.getHotelId());

        mockMvc.perform(patch("/api/guest-rooms/" + guestRoomId)
                        .with(csrf())
                        .contentType("application/json")
                        .content(testHelper.asJson(dto)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
