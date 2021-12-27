package br.com.registre.of.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.registre.of.partner.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
