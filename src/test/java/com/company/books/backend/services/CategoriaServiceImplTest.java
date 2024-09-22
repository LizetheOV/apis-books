/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.company.books.backend.services;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.services
 * <p>
 * User: LOvandoV
 * Date: 15/9/2024
 * Time: 13:54
 * <p>
 */

public class CategoriaServiceImplTest {


  @InjectMocks
  CategoriaServiceImpl categoriaService;

  @Mock
  ICategoriaDao categoriaDao;

  List<Categoria> categorias = new ArrayList<>();

  @BeforeEach
  public void init() {

    MockitoAnnotations.openMocks(this);
    cargarCategorias();

  }

  @Test
  public void buscarCategoria() {

    Mockito.when(categoriaDao.findAll()).thenReturn(categorias);
    ResponseEntity<CategoriaResponseRest> response = categoriaService.buscarCategorias();
    assertEquals(5, response.getBody().getCategoriaResponse().getCategoria().size());
    verify(categoriaDao, times(1)).findAll();

  }

  public void cargarCategorias() {

    categorias = List.of(
        new Categoria(Long.valueOf(1), "Prueba 1", "Descripción Pruebas 1"),
        new Categoria(Long.valueOf(2), "Prueba 2", "Descripción Pruebas 2"),
        new Categoria(Long.valueOf(3), "Prueba 3", "Descripción Pruebas 3"),
        new Categoria(Long.valueOf(4), "Prueba 4", "Descripción Pruebas 4"),
        new Categoria(Long.valueOf(5), "Prueba 5", "Descripción Pruebas 5")
    );

  }

}
