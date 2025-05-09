package br.edu.ucs.matriculas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ucs.matriculas.service.CSVImportService;

/**
 * Classe principal da aplicação que inicializa o contexto Spring Boot e executa tarefas específicas de acordo com o ambiente configurado (dev ou prod).
 * 
 * No ambiente "dev", realiza a importação dos dados do arquivo CSV para o banco de dados.
 * No ambiente "prod", a importação é ignorada, assumindo que os dados já estão populados no banco.
 * 
 * O ambiente é definido através da propriedade "spring.profiles.active" no arquivo application.properties.
 */
@SpringBootApplication
public class MatriculasApplication implements CommandLineRunner {

    @Autowired
	private CSVImportService csvImportService;

    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(MatriculasApplication.class, args);
    }

    @Override
	public void run(String... args) {
		System.out.println("🔍 Profile ativo: " + activeProfile);
		if ("dev".equalsIgnoreCase(activeProfile.trim())) {
			System.out.println("🔁 Ambiente DEV detectado — iniciando importação do CSV...");
			csvImportService.importarCSV();
		} else {
			System.out.println("✅ Ambiente não-DEV — importação de CSV ignorada.");
		}
	}
}