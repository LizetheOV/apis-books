/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.company.books.backend.request;

import java.io.Serializable;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.request
 * <p>
 * User: LOvandoV
 * Date: 15/9/2024
 * Time: 11:40
 * <p>
 */

public class AuthRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  private String usuario;
  private String contrasenia;

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

}
