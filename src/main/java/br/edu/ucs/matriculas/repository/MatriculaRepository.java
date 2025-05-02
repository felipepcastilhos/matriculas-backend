package br.edu.ucs.matriculas.repository;

import br.edu.ucs.matriculas.model.RegistroMatricula;

import java.util.List;

public interface MatriculaRepository {
    void salvarTodos(List<RegistroMatricula> registros);
}