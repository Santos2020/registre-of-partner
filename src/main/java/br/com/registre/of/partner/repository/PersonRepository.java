package br.com.registre.of.partner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.registre.of.partner.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
