package com.blog.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {  // UserDetails : 스프링 시큐리티에서 사용자의 인증 정보를 담아두는 인터페이스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Builder
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    @Override   // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override   // 사용자의 id를 반환(고유한 값)
    public String getUsername() {
        return email;
    }

    @Override   // 사용자의 패스워드 반환
    public String getPassword() {
        return password;
    }

    @Override   // 계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직 추가
        // true : 만료되지 않았음
        return true;
    }

    @Override   // 계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        // 계정이 잠겼는지 확인하는 로직 추가
        // true : 잠금되지 않았음
        return true;
    }

    @Override   // 패스워드의 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        // true : 만료되지 않았음
        return true;
    }

    @Override   // 계정 사용 가능 여부 반환
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        // true : 사용 가능
        return true;
    }

    // 사용자 이름 변경
    public User update(String nickname) {
        this.nickname = nickname;

        return this;
    }
}
