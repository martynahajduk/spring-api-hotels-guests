package be.kdg.programming5.presentation.controller;

import be.kdg.programming5.domain.Hotel;
import be.kdg.programming5.presentation.viewmodel.HotelViewModel;
import be.kdg.programming5.service.GuestServiceInterface;
import be.kdg.programming5.service.HotelServiceInterface;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/hotels")
public class HotelController {
    private final HotelServiceInterface hotelService;
    private final GuestServiceInterface guestService;

    public HotelController(HotelServiceInterface hotelService, GuestServiceInterface guestService) {
        this.hotelService = hotelService;
        this.guestService = guestService;
    }

    @GetMapping
    public String showHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotels";
    }

    @PostMapping("/filter")
    public String filterHotels(@RequestParam String name, Model model) {
        model.addAttribute("hotels", hotelService.filterHotelsByName(name));
        return "hotels";
    }

    @GetMapping("/hotel/{id}")
    public String getHotelDetails(@PathVariable UUID id, Model model) {
        Hotel hotel = hotelService.findHotelById(id);
        model.addAttribute("hotel", hotel);
        model.addAttribute("guests", guestService.getAllGuests().stream().filter(guest -> guest.getHotel().getName().equals(hotel.getName())));
        return "showdetailshotel";
    }

    @PostMapping("/delete/{id}")
    public String deleteHotel(@PathVariable UUID id, Model model) {
        try {
            hotelService.deleteHotel(id);
            return "redirect:/hotels";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "error/generalerror";
        }
    }

    @GetMapping("/addhotel")
    public String showAddHotelForm(Model model, HttpSession session) {
        model.addAttribute("hotelViewModel", new HotelViewModel());
        return "addhotel";
    }

    @PostMapping("/addhotel")
    public String addHotel(@Valid @ModelAttribute("hotelViewModel") HotelViewModel hotelViewModel,
                           BindingResult errors, HttpSession session) {
        if (errors.hasErrors()) {
            return "addhotel";
        }
        hotelService.addHotel(hotelViewModel.getName(), hotelViewModel.getLocation());
        return "redirect:/hotels";
    }


    @GetMapping("/index")
    public String showMain(Model model, HttpSession session) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "index";
    }
}
