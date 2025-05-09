package br.edu.ucs.matriculas.repository;

import java.util.List;

import br.edu.ucs.matriculas.model.RegistroMatricula;

/**
 * Interface para o repositório de matrículas.
 * Possibilitaria futuramente migrar para Spring Data JPA
 */
public interface MatriculaRepository {
    void salvarTodos(List<RegistroMatricula> registros);
}