package com.mysite.sbb.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public SiteUser create(String username, String email, String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    SiteUser user = SiteUser.builder()
                            .username(username)
                            .email(email)
                            .password(passwordEncoder.encode(password))
                            .build();
    this.userRepository.save(user);
    return user;
  }
}
