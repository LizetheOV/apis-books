/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.company.books.backend.response;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.response
 * <p>
 * User: LOvandoV
 * Date: 15/9/2024
 * Time: 11:39
 * <p>
 */

public class TokenResponse {

  private String jwtToken;

  public TokenResponse(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }

}
