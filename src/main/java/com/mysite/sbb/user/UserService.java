package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.sbb.exception.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
  private final UserRepository userRepository;
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

  public SiteUser getUser(String username) {
    Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
    if(siteUser.isPresent()) {
      return siteUser.get();
    } else {
      throw new DataNotFoundException("siteuser not found");
    }
  }
}
