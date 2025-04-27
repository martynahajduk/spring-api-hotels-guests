package be.kdg.programming5.presentation.controller;

import be.kdg.programming5.domain.Nationality;
import be.kdg.programming5.presentation.viewmodel.GuestViewModel;
import be.kdg.programming5.service.GuestServiceInterface;
import be.kdg.programming5.service.HotelServiceInterface;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final GuestServiceInterface guestService;
    private final HotelServiceInterface hotelService;


    public GuestController(GuestServiceInterface guestService, HotelServiceInterface hotelService) {
        this.guestService = guestService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public String showGuests(Model model) {
        model.addAttribute("guests", guestService.getAllGuests());
        return "guests";
    }

    @GetMapping("/guest/{id}")
    public String getGuestDetails(@PathVariable UUID id, Model model) {
        model.addAttribute("guest", guestService.findGuestById(id));
        return "showdetailsguest";
    }

    @PostMapping("/delete/{id}")
    public String deleteGuest(@PathVariable UUID id) {
        guestService.deleteGuest(id);
        return "redirect:/guests";
    }
    @PostMapping("/filter")
    public String filterGuests(@RequestParam(required = false) Nationality nationality,
                               @RequestParam(required = false) String dateOfBirth,
                               Model model) {
        LocalDate birthDateParsed = (dateOfBirth != null && !dateOfBirth.isEmpty()) ? LocalDate.parse(dateOfBirth) : null;
        model.addAttribute("guests", guestService.filterGuestsByNationalityAndBirthDate(nationality, birthDateParsed));
        return "guests";
    }

    @GetMapping("/addguest")
    public String showAddGuestForm(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("guestViewModel", new GuestViewModel());
        return "addguest";
    }


    @PostMapping("/addguest")
    public String addGuest(@Valid @ModelAttribute("guestViewModel") GuestViewModel guestViewModel,
                           BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("hotels", hotelService.getAllHotels());
            return "addguest";
        }
        guestService.addGuest(
                guestViewModel.getName(),
                guestViewModel.getBirthDate(),
                guestViewModel.getNationality(),
                guestViewModel.getHotelName(),
                guestViewModel.getRoomNumber()
        );

        return "redirect:/guests";
    }

}