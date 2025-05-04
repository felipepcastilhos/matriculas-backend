package br.edu.ucs.matriculas.service;

import br.edu.ucs.matriculas.dao.RegistroMatriculaDAO;
import br.edu.ucs.matriculas.model.ConsultaResultado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final RegistroMatriculaDAO dao;

    public List<ConsultaResultado> buscarTotalPorAno() {
        return dao.somarTotalPorAno();
    }

    public List<ConsultaResultado> consultarTotalPorAnoPorModalidade(String modalidade) {
        if (modalidade == null || modalidade.isBlank()) {
            throw new IllegalArgumentException("Modalidade não pode ser nula ou vazia");
        }

        return dao.somarTotalPorAnoPorModalidade(modalidade);
    }

    public List<ConsultaResultado> consultarTop10CursosPor2022() {
        return dao.buscarTop10CursosPorMatricula2022();
    }

    public List<ConsultaResultado> consultarTop10CursosPor2022Modalidade(String modalidade) {
        if (modalidade == null || (!modalidade.equalsIgnoreCase("EaD") && !modalidade.equalsIgnoreCase("Presencial"))) {
            throw new IllegalArgumentException("Modalidade inválida: deve ser 'EaD' ou 'Presencial'");
        }
        return dao.buscarTop10CursosPor2022ComModalidade(modalidade);
    }
}