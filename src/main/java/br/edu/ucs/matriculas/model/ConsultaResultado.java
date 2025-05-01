package br.edu.ucs.matriculas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Representa o retorno de uma consulta agregada, como total de alunos por curso ou estado.
 */
@Data
@AllArgsConstructor
public class ConsultaResultado {
    private String chave;   // Pode ser o nome do curso, estado, etc.
    private int total;      // Quantidade de alunos
}