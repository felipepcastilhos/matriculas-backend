package br.edu.ucs.matriculas.model;

import lombok.Data;

/**
 * Representa os critérios utilizados para filtragem nas consultas. Exibido na consulta do histórico
 * Parâmetros de entrada das consultas: útil quando quiser receber filtros como estado, ano ou modalidade nos endpoints
 */
@Data
public class ConsultaFiltro {
    private String estado;         // Ex: "RS"
    private String modalidade;     // Ex: "EaD" ou "Presencial"
    private Integer ano;           // Ex: 2022
    // private String curso;          // Ex: "PEDAGOGIA"
}