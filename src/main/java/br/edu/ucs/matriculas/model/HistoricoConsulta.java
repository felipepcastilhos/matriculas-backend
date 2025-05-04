package br.edu.ucs.matriculas.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Armazena informações sobre uma consulta realizada e seu momento de execução.
 * Registro das últimas consultas feitas: pode ser usado para armazenar as últimas 2 consultas e fornecer acesso rápido a elas, conforme a funcionalidade 2 do projeto.
 */
@Data
public class HistoricoConsulta {
    private ConsultaFiltro filtro;       // Critérios da consulta
    private LocalDateTime dataHora;      // Quando foi feita
}