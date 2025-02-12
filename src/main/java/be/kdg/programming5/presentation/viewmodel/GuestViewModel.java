package be.kdg.programming5.presentation.viewmodel;

import be.kdg.programming5.domain.Nationality;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GuestViewModel {
    @NotBlank(message = "{guest.name.required}")
    @Size(min = 2, max = 50, message = "{guest.name.size}")
    private String name;

    @NotNull(message = "{guest.birthDate.required}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "{guest.birthDate.past}")
    private LocalDate birthDate;

    @NotNull(message = "{guest.nationality.required}")
    private Nationality nationality;

    @NotBlank(message = "{guest.hotelName.required}")
    private String hotelName;

    @Min(value=1, message = "{guest.roomNumber.min}")
    private int roomNumber;

    public @NotBlank(message = "{guest.name.required}") @Size(min = 2, max = 50, message = "{guest.name.size}") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "{guest.name.required}") @Size(min = 2, max = 50, message = "{guest.name.size}") String name) {
        this.name = name;
    }

    public @NotNull(message = "{guest.birthDate.required}") @Past(message = "{guest.birthDate.past}") LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@NotNull(message = "{guest.birthDate.required}") @Past(message = "{guest.birthDate.past}") LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @NotNull(message = "{guest.nationality.required}") Nationality getNationality() {
        return nationality;
    }

    public void setNationality(@NotNull(message = "{guest.nationality.required}") Nationality nationality) {
        this.nationality = nationality;
    }

    public @NotBlank(message = "{guest.hotelName.required}") String getHotelName() {
        return hotelName;
    }

    public void setHotelName(@NotBlank(message = "{guest.hotelName.required}") String hotelName) {
        this.hotelName = hotelName;
    }

    @Min(value=1, message = "{guest.roomNumber.min}")
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(@Min(value = 1, message = "{guest.roomNumber.min}") int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
