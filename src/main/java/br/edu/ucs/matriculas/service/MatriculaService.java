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

    public List<ConsultaResultado> buscarTotalPorAno() {
        List<ConsultaResultado> resultado = dao.somarTotalPorAno();
        salvarHistorico(new ConsultaFiltro(), resultado);
        return resultado;
    }

    public List<ConsultaResultado> consultarTotalPorAnoPorModalidade(String modalidade) {
        if (modalidade == null || modalidade.isBlank()) {
            throw new IllegalArgumentException("Modalidade não pode ser nula ou vazia");
        }
    
        List<ConsultaResultado> resultado = dao.somarTotalPorAnoPorModalidade(modalidade);
    
        // Criação do filtro para salvar no histórico
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setModalidade(modalidade);
    
        salvarHistorico(filtro, resultado);
    
        return resultado;
    }

    public List<ConsultaResultado> consultarTop10CursosPor2022() {
        List<ConsultaResultado> resultado = dao.buscarTop10CursosPorMatricula2022();
    
        // Criação do filtro para salvar no histórico
        ConsultaFiltro filtro = new ConsultaFiltro();
        filtro.setAno(2022);
    
        // Salvar no histórico
        salvarHistorico(filtro, resultado);
    
        return resultado;
    }
    
    public List<ConsultaResultado> consultarTop10CursosPor2022Modalidade(String modalidade) {
        if (modalidade == null || (!modalidade.equalsIgnoreCase("EaD") && !modalidade.equalsIgnoreCase("Presencial"))) {
            throw new IllegalArgumentException("Modalidade inválida: deve ser 'EaD' ou 'Presencial'");
        }
        return dao.buscarTop10CursosPor2022ComModalidade(modalidade);
    }

    public List<ConsultaResultado> getTotaisPorAnoEstado(String estado) {
        return dao.buscarTotaisPorAnoEstado(estado);
    }
    
    public List<ConsultaResultado> getTotaisPorAnoEstadoEModalidade(String estado, String modalidade) {
        return dao.buscarTotaisPorAnoEstadoEModalidade(estado, modalidade);
    }
    
    public List<ConsultaResultado> getTop10Cursos2022PorEstado(String estado) {
        return dao.buscarTop10Cursos2022PorEstado(estado);
    }
    
    public List<ConsultaResultado> getTop10Cursos2022EstadoEModalidade(String estado, String modalidade) {
        return dao.buscarTop10Cursos2022EstadoEModalidade(estado, modalidade);
    }

    private void salvarHistorico(ConsultaFiltro filtro, List<ConsultaResultado> resultado) {
        if (historicoConsultas.size() == 2) {
            historicoConsultas.removeFirst(); // Remove o mais antigo
        }
        historicoConsultas.addLast(new HistoricoConsulta(filtro, LocalDateTime.now(), resultado));
    }

    public List<HistoricoConsulta> getHistoricoConsultas() {
        return new ArrayList<>(historicoConsultas);
    }
}