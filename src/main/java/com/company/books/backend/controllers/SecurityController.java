/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.company.books.backend.controllers;

import com.company.books.backend.request.AuthRequest;
import com.company.books.backend.response.TokenResponse;
import com.company.books.backend.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.controllers
 * <p>
 * User: LOvandoV
 * Date: 15/9/2024
 * Time: 11:36
 * <p>
 */

@RestController
@RequestMapping("/api/v1")
public class SecurityController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  JwtService jwtService;

  @PostMapping("/authenticate")
  public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest authRequest) {

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsuario(), authRequest.getContrasenia()));
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsuario());
    final String token = jwtService.generateToken(userDetails);

    return ResponseEntity.ok(new TokenResponse(token));

  }

}
