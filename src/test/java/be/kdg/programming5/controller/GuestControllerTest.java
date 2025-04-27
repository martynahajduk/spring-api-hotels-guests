package be.kdg.programming5.controller;

import be.kdg.programming5.TestHelper;
import be.kdg.programming5.domain.*;
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

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GuestControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private TestHelper testHelper;

    @BeforeEach
    void setup() {
        testHelper.cleanUp();
        testHelper.seedUser("user_test");
        testHelper.seedAdmin("admin_test");

        testHelper.createFullGuestRoomSetup(
                "user_test",
                "Test Hotel", "Test City",
                801, RoomType.DELUXE, 500, 6,
                "Test Guest", Nationality.BELGIAN, LocalDate.of(1990, 1, 1)
        );
    }

    @Test
    @WithUserDetails(value = "admin_test", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void shouldShowGuestCreationPage() throws Exception {
        mockMvc.perform(get("/guests/addguest"))
                .andExpect(status().isOk())
                .andExpect(view().name("addguest"))
                .andExpect(model().attributeExists("guestViewModel"))
                .andExpect(model().attribute("guestViewModel", instanceOf(Object.class)));
    }

    @Test
    @WithUserDetails(value = "user_test", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void shouldShowAllGuestsPage() throws Exception {
        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(view().name("guests"))
                .andExpect(model().attributeExists("guests"));
    }

    @Test
    @WithUserDetails(value = "user_test", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void shouldNotAllowUserToAccessGuestCreationPage() throws Exception {
        mockMvc.perform(get("/guests/addguest"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldRedirectAnonymousToLoginForGuestCreationPage() throws Exception {
        mockMvc.perform(get("/guests/addguest"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void shouldRedirectAnonymousToLoginForGuestListPage() throws Exception {
        mockMvc.perform(get("/guests"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @WithUserDetails(value = "admin_test", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void adminShouldAccessGuestCreationPage() throws Exception {
        mockMvc.perform(get("/guests/addguest"))
                .andExpect(status().isOk())
                .andExpect(view().name("addguest"))
                .andExpect(model().attributeExists("guestViewModel"));
    }
}
