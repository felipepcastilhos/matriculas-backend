package br.edu.ucs.matriculas.model;

import lombok.Data;

/**
 * Essa classe representa cada linha do CSV como uma entidade de domínio. 
 * Utilizamos @Data do Lombok para gerar automaticamente getters, setters, equals(), hashCode() e toString(). 
 */
@Data
public class RegistroMatricula {

    private String estado;
    private String cidade;
    private String ies;
    private String sigla;
    private String organizacao;
    private String categoriaAdministrativa;
    private String nomeCurso;
    private String nomeDetalhadoCurso;
    private String modalidade;
    private String grau;

    private int matriculas2014;
    private int matriculas2015;
    private int matriculas2016;
    private int matriculas2017;
    private int matriculas2018;
    private int matriculas2019;
    private int matriculas2020;
    private int matriculas2021;
    private int matriculas2022;
}
