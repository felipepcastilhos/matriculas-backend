package br.edu.ucs.matriculas.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ucs.matriculas.dao.RegistroMatriculaDAO;
import br.edu.ucs.matriculas.model.ConsultaFiltro;
import br.edu.ucs.matriculas.model.ConsultaResultado;
import br.edu.ucs.matriculas.model.HistoricoConsulta;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final RegistroMatriculaDAO dao;
    private final LinkedList<HistoricoConsulta> historicoConsultas = new LinkedList<>(); // Lista para armazenar as duas últimas consultas

    /**
     * Método para buscar o total de matrículas por ano em todo o Brasil.
     * A consulta realiza a soma total de matrículas para cada ano entre 2014 e 2022.
     * O resultado é salvo no histórico para acesso rápido.
     * 
     * @return Lista de objetos `ConsultaResultado` contendo o ano e o total de matrículas.
     */
    public List<ConsultaResultado> buscarTotalPorAno() {
        List<ConsultaResultado> resultado = dao.somarTotalPorAno();
        salvarHistorico(new ConsultaFiltro(), resultado);
        return resultado;
    }

    /**
     * Método para buscar o total de matrículas por ano filtrado por modalidade (EaD ou Presencial).
     */
    public List<ConsultaResultado> consultarTotalPorAnoPorModalidade(String modalidade) {
        if (modalidade == null || modalidade.isBlank()) {
            throw new IllegalArgumentException("Modalidade não pode ser nula ou vazia");
        }
    
        List<ConsultaResultado> resultado = dao.somarTotalPorAnoPorModalidade(modalidade);
    
        // Criação do filtro para salvar no histórico
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setModalidade(modalidade);
    
        // Salvar no histórico
        salvarHistorico(filtro, resultado);
    
        return resultado;
    }

    /**
     * Método para buscar os 10 cursos com mais matrículas no ano de 2022.
     */
    public List<ConsultaResultado> consultarTop10CursosPor2022() {
        List<ConsultaResultado> resultado = dao.buscarTop10CursosPorMatricula2022();
    
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setAno(2022);
    
        salvarHistorico(filtro, resultado);
    
        return resultado;
    }
    
    /**
     * Método para buscar os 10 cursos com mais matrículas no ano de 2022, filtrado por modalidade (EaD ou Presencial).
     */
    public List<ConsultaResultado> consultarTop10CursosPor2022Modalidade(String modalidade) {
        if (modalidade == null || (!modalidade.equalsIgnoreCase("EaD") && !modalidade.equalsIgnoreCase("Presencial"))) {
            throw new IllegalArgumentException("Modalidade inválida: deve ser 'EaD' ou 'Presencial'");
        }
    
        List<ConsultaResultado> resultado = dao.buscarTop10CursosPor2022ComModalidade(modalidade);
    
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setModalidade(modalidade);
        filtro.setAno(2022);
    
        salvarHistorico(filtro, resultado);
        return resultado;
    }    

    /**
     * Método para buscar o total de matrículas por ano em um estado específico.
     */
    public List<ConsultaResultado> getTotaisPorAnoEstado(String estado) {
        List<ConsultaResultado> resultado = dao.buscarTotaisPorAnoEstado(estado);
    
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setEstado(estado);
    
        salvarHistorico(filtro, resultado);
        return resultado;
    }
    
    /**
     * Método para buscar o total de matrículas por ano em um estado específico, filtrado por modalidade.
     */
    public List<ConsultaResultado> getTotaisPorAnoEstadoEModalidade(String estado, String modalidade) {
        List<ConsultaResultado> resultado = dao.buscarTotaisPorAnoEstadoEModalidade(estado, modalidade);
    
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setEstado(estado);
        filtro.setModalidade(modalidade);
    
        salvarHistorico(filtro, resultado);
        return resultado;
    }    
    
    /**
     * Método para buscar os 10 cursos com mais matrículas no ano de 2022, filtrado por estado.
     */
    public List<ConsultaResultado> getTop10Cursos2022PorEstado(String estado) {
        List<ConsultaResultado> resultado = dao.buscarTop10Cursos2022PorEstado(estado);
    
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setEstado(estado);
        filtro.setAno(2022); // Definido manualmente para especificar o ano da consulta
    
        salvarHistorico(filtro, resultado);
        return resultado;
    }    
    
    /**
     * Método para buscar os 10 cursos com mais matrículas no ano de 2022, filtrado por estado e modalidade.
     */
    public List<ConsultaResultado> getTop10Cursos2022EstadoEModalidade(String estado, String modalidade) {
        List<ConsultaResultado> resultado = dao.buscarTop10Cursos2022EstadoEModalidade(estado, modalidade);
    
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setEstado(estado);
        filtro.setModalidade(modalidade);
        filtro.setAno(2022); 
    
        salvarHistorico(filtro, resultado);
        return resultado;
    }

    /**
     * Método para salvar o histórico de consultas, mantendo apenas as duas mais recentes.
     */
    private void salvarHistorico(ConsultaFiltro filtro, List<ConsultaResultado> resultado) {
        if (historicoConsultas.size() == 2) {
            historicoConsultas.removeFirst(); // Remove o mais antigo
        }
        historicoConsultas.addLast(new HistoricoConsulta(filtro, LocalDateTime.now(), resultado));
    }

    /**
     * Método para obter o histórico de consultas.
     */
    public List<HistoricoConsulta> getHistoricoConsultas() {
        return new ArrayList<>(historicoConsultas);
    }
}