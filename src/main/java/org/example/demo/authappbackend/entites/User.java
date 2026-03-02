package org.example.demo.authappbackend.entites;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user-id")
    private UUID id;
    private String name;
    @Column(name = "user-email" , unique = true,length = 300)
    private String email;
    private String password;
    private String image;
    private boolean enable = true;
    private Instant createdAt  = Instant.now();
    private Instant updatedAt  = Instant.now();
    private String gender;

    @Enumerated(EnumType.STRING)
    private Provider provider = Provider.LOCAL;


    @ManyToMany(fetch =  FetchType.EAGER)
    @JoinTable(name = "user-roles",
            joinColumns = @JoinColumn(name = "user-id"),
            inverseJoinColumns = @JoinColumn(name = "role-id")
    )
    private Set<Role> roles= new HashSet<>();

    @PrePersist
    protected  void onCreate(){
    Instant now = Instant.now();
    if (createdAt == null) createdAt = now;
    updatedAt= now;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt= Instant.now();
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){

        return  roles.
                stream().
                map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    ;

}
