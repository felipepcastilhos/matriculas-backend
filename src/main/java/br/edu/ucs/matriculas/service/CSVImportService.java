package br.edu.ucs.matriculas.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import br.edu.ucs.matriculas.dao.RegistroMatriculaDAO;
import br.edu.ucs.matriculas.model.RegistroMatricula;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CSVImportService {

    private final RegistroMatriculaDAO matriculaDAO;

    /**
     * Método responsável por importar os dados do arquivo CSV e persistir no banco de dados.
     * O método lê o arquivo `matriculas.csv` localizado em `/seed`, valida o número de colunas
     * de cada linha e registra as linhas inválidas em um arquivo de log (`log-importacao.csv`). 
     * Linhas válidas são mapeadas para objetos `RegistroMatricula` e persistidas no banco de dados.
     * 
     * Fluxo do método:
     * 1. Leitura do cabeçalho e contagem das colunas esperadas.
     * 2. Para cada linha:
     *    - Verifica se o número de colunas está correto.
     *    - Caso esteja correto, tenta mapear para um objeto `RegistroMatricula`.
     *    - Se houver erro no mapeamento ou quantidade de colunas, registra no log.
     * 3. Registra no log o número de linhas válidas, inválidas e a quantidade total lida.
     * 4. Salva os registros válidos no banco utilizando o DAO.
     */
    public void importarCSV() {
        List<RegistroMatricula> registrosValidos = new ArrayList<>();
        int totalLidas = 0;
        int totalInvalidas = 0;

        try (
            Reader reader = new InputStreamReader(
                    getClass().getResourceAsStream("/seed/matriculas.csv"), StandardCharsets.UTF_8);
            CSVReader csvReader = new CSVReader(reader);
            PrintWriter logWriter = new PrintWriter(
                    new FileWriter("src/main/resources/logs/log-importacao.csv", false)) // sobrescreve
        ) {
            String[] header = csvReader.readNext();
            int expectedColumns = header.length;

            logWriter.println("Linha;Erro;Conteúdo");

            String[] linha;
            int linhaAtual = 2;

            while ((linha = csvReader.readNext()) != null) {
                totalLidas++;
                if (linha.length != expectedColumns) {
                    logWriter.println(linhaAtual + ";Colunas incorretas (" + linha.length + " de " + expectedColumns + ");" + String.join(";", linha));
                    totalInvalidas++;
                } else {
                    try {
                        RegistroMatricula registro = parseLinha(header, linha);
                        registrosValidos.add(registro);
                    } catch (Exception e) {
                        logWriter.println(linhaAtual + ";Erro de mapeamento;" + String.join(";", linha));
                        totalInvalidas++;
                    }
                }
                linhaAtual++;
            }

            matriculaDAO.salvarTodos(registrosValidos);

            System.out.println("✅ Total de registros lidos: " + totalLidas);
            System.out.println("✅ Registros válidos: " + registrosValidos.size());
            System.out.println("⚠️ Linhas inválidas: " + totalInvalidas);
            System.out.println("📄 Log de erros: src/main/resources/logs/log-importacao.csv");

        } catch (Exception e) {
            System.err.println("❌ Erro ao importar CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método responsável por mapear uma linha do CSV para um objeto `RegistroMatricula`.
     * Este método utiliza a estratégia de mapeamento `HeaderColumnNameMappingStrategy` 
     * da biblioteca OpenCSV para associar os valores do CSV aos atributos da entidade.
     * 
     * Fluxo do método:
     * 1. Recebe o cabeçalho e os valores da linha como arrays de `String`.
     * 2. Constrói um CSV temporário em memória para ser lido pelo `CsvToBean`.
     * 3. Mapeia os valores para a classe `RegistroMatricula`.
     * 4. Retorna o objeto populado.
     * 
     * @param header Array contendo os nomes das colunas do CSV.
     * @param linha Array contendo os valores da linha do CSV.
     * @return Objeto `RegistroMatricula` populado com os valores da linha.
     */
    private RegistroMatricula parseLinha(String[] header, String[] linha) throws IOException {
        HeaderColumnNameMappingStrategy<RegistroMatricula> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(RegistroMatricula.class);

        StringBuilder csvLinha = new StringBuilder();
        csvLinha.append(String.join(";", header)).append("\n");
        csvLinha.append(String.join(";", linha)).append("\n");

        try (
            Reader reader = new StringReader(csvLinha.toString())
        ) {
            CsvToBean<RegistroMatricula> csvToBean = new CsvToBeanBuilder<RegistroMatricula>(reader)
                    .withMappingStrategy(strategy)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<RegistroMatricula> list = csvToBean.parse();
            if (list.isEmpty()) throw new IOException("Linha não pôde ser convertida");
            return list.get(0);
        }
    }
}