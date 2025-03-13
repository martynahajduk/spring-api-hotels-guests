package be.kdg.programming5.service;

import be.kdg.programming5.repository.GuestRoomRepository;
import be.kdg.programming5.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private GuestRoomRepository guestRoomRepository;

    @Autowired
    public AuthorizationService(GuestRoomRepository guestRoomRepository) {
        this.guestRoomRepository = guestRoomRepository;
    }

    public boolean isOwner(Integer guestRoomId, CustomUserDetails userDetails) {
        return guestRoomRepository.findById(guestRoomId)
                .map(gr -> gr.getOwner().getId().equals(userDetails.getUser().getId()))
                .orElse(false);
    }
}
