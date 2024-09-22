
package com.company.books.backend.controllers;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.services.ICategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


/**
 * Project: repaso-spring
 * Package: com.company.books.backend.controllers
 * <p>
 * User: LOvandoV
 * Date: 15/9/2024
 * Time: 14:15
 * <p>
 */

public class CategoriaRestControllerTest {

  @InjectMocks
  CategoriaRestController categoriaRestController;

  @Mock
  ICategoriaService categoriaService;

  @BeforeEach
  public void init() {

    MockitoAnnotations.openMocks(this);

  }

  @Test
  public void createCategoriaTest() {

    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    Categoria categoria = new Categoria(Long.valueOf(1), "Prueba 1", "Descripción Pruebas 1");
    Mockito.when(categoriaService.crear(any(Categoria.class))).thenReturn(new ResponseEntity<CategoriaResponseRest>(HttpStatus.OK));

    ResponseEntity<CategoriaResponseRest> response = categoriaRestController.crear(categoria);

    assertThat(response.getStatusCode().value()).isEqualTo(200);

  }

}
