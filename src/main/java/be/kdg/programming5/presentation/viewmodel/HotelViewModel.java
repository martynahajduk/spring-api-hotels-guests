package be.kdg.programming5.presentation.viewmodel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class HotelViewModel {
    @NotBlank(message = "{hotel.name.required}")
    @Size(min = 3, max = 50, message = "{hotel.name.size}")
    private String name;

    @NotBlank(message = "{hotel.location.required}")
    private String location;

    public @NotBlank(message = "{hotel.name.required}") @Size(min = 3, max = 50, message = "{hotel.name.size}") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "{hotel.name.required}") @Size(min = 3, max = 50, message = "{hotel.name.size}") String name) {
        this.name = name;
    }

    public @NotBlank(message = "{hotel.location.required}") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "{hotel.location.required}") String location) {
        this.location = location;
    }
}
