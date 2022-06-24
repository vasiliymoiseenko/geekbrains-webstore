package ru.geekbrains.webstore.auth.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import ru.geekbrains.webstore.auth.service.UserService;
import ru.geekbrains.webstore.auth.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.api.auth.AuthRequest;
import ru.geekbrains.webstore.api.auth.AuthResponse;
import ru.geekbrains.webstore.api.exception.response.ErrorResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;
  private final AuthenticationManager authenticationManager;

  @PostMapping("api/v1/auth")
  public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
    } catch (BadCredentialsException e) {
      return new ResponseEntity<>(new ErrorResponse("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
    }
    UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
    String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthResponse(token));
  }
}
