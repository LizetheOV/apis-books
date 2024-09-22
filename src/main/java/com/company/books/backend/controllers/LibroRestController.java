
package com.company.books.backend.controllers;

import com.company.books.backend.model.Libro;
import com.company.books.backend.services.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.controllers
 * <p>
 * User: LOvandoV
 * Date: 8/9/2024
 * Time: 12:01
 * <p>
 */

@RestController
@RequestMapping("/api/v1")
public class LibroRestController {

  @Autowired
  private ILibroService libroService;

  @GetMapping("/libros")
  public ResponseEntity<List<Libro>> obtenerLibros() {
    return libroService.listarLibros();
  }

  @GetMapping("/libros/{id}")
  public ResponseEntity<Libro> obtenerLibro(@PathVariable Long id) {
    return libroService.listarLibro(id);
  }


  @PostMapping("/libros")
  public ResponseEntity<Libro> guardarLibro(@RequestBody Libro libro) {
    return libroService.guardarLibro(libro);
  }

  @PutMapping("/libros/{id}")
  public ResponseEntity<Libro> actualizarLibro(@RequestBody Libro libro, @PathVariable Long id) {
    return libroService.actualizarLibro(libro, id);
  }

  @DeleteMapping("/libros/{id}")
  public ResponseEntity<Libro> eliminarLibro(@PathVariable Long id) {
    return libroService.eliminarLibro(id);
  }

}
