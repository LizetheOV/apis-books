
package com.company.books.backend.model.dao;

import com.company.books.backend.model.Libro;
import org.springframework.data.repository.CrudRepository;

/**
 * Project: repaso-spring
 * Package: com.company.books.backend.model.dao
 * <p>
 * User: LOvandoV
 * Date: 8/9/2024
 * Time: 12:06
 * <p>
 */

public interface ILibroDao extends CrudRepository<Libro, Long> {
}
