
package com.company.books.backend.services;

import com.company.books.backend.model.Libro;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.service
 * <p>
 * User: LOvandoV
 * Date: 8/9/2024
 * Time: 12:02
 * <p>
 */

public interface ILibroService {
  ResponseEntity<List<Libro>> listarLibros();

  ResponseEntity<Libro> listarLibro(Long id);

  ResponseEntity<Libro> guardarLibro(Libro libro);

  ResponseEntity<Libro> actualizarLibro(Libro libro, Long id);

  ResponseEntity<Libro> eliminarLibro(Long id);
}
