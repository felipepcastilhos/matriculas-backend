package br.edu.ucs.matriculas.dao;

import br.edu.ucs.matriculas.model.RegistroMatricula;
import br.edu.ucs.matriculas.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RegistroMatriculaDAO implements MatriculaRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void salvarTodos(List<RegistroMatricula> registros) {
        String sql = "INSERT INTO matriculas (" +
                "estado, cidade, ies, sigla, organizacao, categoria, nomeCurso, nomeDetalhadoCurso, " +
                "modalidade, grau, " +
                "ano2014, ano2015, ano2016, ano2017, ano2018, ano2019, ano2020, ano2021, ano2022" +
                ") VALUES (" +
                ":estado, :cidade, :ies, :sigla, :organizacao, :categoria, :nomeCurso, :nomeDetalhadoCurso, " +
                ":modalidade, :grau, " +
                ":ano2014, :ano2015, :ano2016, :ano2017, :ano2018, :ano2019, :ano2020, :ano2021, :ano2022" +
                ")";

        for (RegistroMatricula m : registros) {
            Map<String, Object> params = new HashMap<>();
            params.put("estado", m.getEstado());
            params.put("cidade", m.getCidade());
            params.put("ies", m.getIes());
            params.put("sigla", m.getSigla());
            params.put("organizacao", m.getOrganizacao());
            params.put("categoria", m.getCategoriaAdministrativa());
            params.put("nomeCurso", m.getNomeCurso());
            params.put("nomeDetalhadoCurso", m.getNomeDetalhadoCurso());
            params.put("modalidade", m.getModalidade());
            params.put("grau", m.getGrau());
            params.put("ano2014", m.getMatriculas2014());
            params.put("ano2015", m.getMatriculas2015());
            params.put("ano2016", m.getMatriculas2016());
            params.put("ano2017", m.getMatriculas2017());
            params.put("ano2018", m.getMatriculas2018());
            params.put("ano2019", m.getMatriculas2019());
            params.put("ano2020", m.getMatriculas2020());
            params.put("ano2021", m.getMatriculas2021());
            params.put("ano2022", m.getMatriculas2022());

            jdbcTemplate.update(sql, params);
        }
    }
}