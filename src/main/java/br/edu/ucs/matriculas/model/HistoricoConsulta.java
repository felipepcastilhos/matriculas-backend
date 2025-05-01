package br.edu.ucs.matriculas.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Armazena informações sobre uma consulta realizada e seu momento de execução.
 */
@Data
public class HistoricoConsulta {
    private ConsultaFiltro filtro;       // Critérios da consulta
    private LocalDateTime dataHora;      // Quando foi feita
}