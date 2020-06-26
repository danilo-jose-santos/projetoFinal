package br.com.isidrocorp.projeto.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.isidrocorp.projeto.model.Departamento;

public interface DepartamentoDAO extends CrudRepository<Departamento, Integer> {

}
