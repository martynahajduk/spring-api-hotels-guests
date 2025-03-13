package be.kdg.programming5.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "app_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "owner")
    private List<GuestRoom> guestRooms;

    public List<GuestRoom> getGuestRooms() {
        return guestRooms;
    }

    public void setGuestRooms(List<GuestRoom> guestRooms) {
        this.guestRooms = guestRooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
