/*
 *    Banco Bisa
 *    http://bisa.com
 *
 *    (C) 2020, Grupo Financiero Bisa
 *
 */

package com.company.books.backend.services;

import com.company.books.backend.model.Libro;
import com.company.books.backend.model.dao.ILibroDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.service
 * <p>
 * User: LOvandoV
 * Date: 8/9/2024
 * Time: 12:04
 * <p>
 */

@Service
public class LibroServiceImpl implements ILibroService {

  private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

  @Autowired
  private ILibroDao iLibroDao;

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<List<Libro>> listarLibros() {
    ResponseEntity response;
    try {
      log.info("Iniciando consulta de libros");
      List<Libro> libros = (List<Libro>) iLibroDao.findAll();
      response = new ResponseEntity<>(libros, HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Ocurrio un error al obtener libros", e.getMessage());
      e.getStackTrace();
    }

    return response;

  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<Libro> listarLibro(Long id) {
    ResponseEntity response;
    try {
      log.info("Iniciando consulta de libro");
      Optional<Libro> libro = iLibroDao.findById(id);

      if (libro.isEmpty()) {
        log.info("La consulta de libro por id: %s no retorno datos", id);
        response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
        response = new ResponseEntity<>(libro, HttpStatus.OK);
      }
    } catch (Exception e) {
      response = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Ocurrio un error al obtener libros", e.getMessage());
      e.getStackTrace();
    }

    return response;
  }

  @Override
  @Transactional
  public ResponseEntity<Libro> guardarLibro(Libro libro) {
    ResponseEntity response;
    try {
      log.info("Iniciando creación de libro");
      Libro libroGuardado = iLibroDao.save(libro);

      if (Objects.isNull(libroGuardado)) {
        log.info("Ocurrio un error al guardar libro");
        response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      } else {
        response = new ResponseEntity<>(libro, HttpStatus.OK);
      }
    } catch (Exception e) {
      response = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Ocurrio un error al obtener libros", e.getMessage());
      e.getStackTrace();
    }

    return response;
  }

  @Override
  public ResponseEntity<Libro> actualizarLibro(Libro libro, Long id) {
    ResponseEntity response;
    try {
      log.info("Iniciando actualizanción de libro");
      Optional<Libro> libroObtenido = iLibroDao.findById(id);

      if (libroObtenido.isEmpty()) {
        log.info("No se encontro el libro con id", id);
        response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      } else {
        libroObtenido.get().setNombre(libro.getNombre());
        libroObtenido.get().setDescripcion(libro.getDescripcion());
        libroObtenido.get().setCategoria(libro.getCategoria());
        Libro libroGuardado = iLibroDao.save(libroObtenido.get());
        response = new ResponseEntity<>(libroGuardado, HttpStatus.OK);
      }
    } catch (Exception e) {
      response = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Ocurrio un error al obtener libros", e.getMessage());
      e.getStackTrace();
    }

    return response;
  }

  @Override
  public ResponseEntity<Libro> eliminarLibro(Long id) {
    ResponseEntity response;
    try {
      log.info("Iniciando eliminado de libro");
      iLibroDao.deleteById(id);

      response = new ResponseEntity<>(HttpStatus.OK);

    } catch (Exception e) {
      response = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Ocurrio un error al obtener libros", e.getMessage());
      e.getStackTrace();
    }

    return response;
  }
}
