# 📊 Matriculas Backend

Backend do projeto da disciplina **Projeto e Arquitetura de Software**, da UCS. Desenvolvido em grupo utilizando **Spring Boot**, **JDBC**, **MySQL** e o padrão **MVC**.

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.5
- Spring Web
- Spring JDBC
- MySQL
- OpenCSV (leitura de arquivos `.csv`)
- Maven
- Lombok
- DevTools

---

## 📁 Estrutura Base do Projeto

```bash
src/
├── main/
│   ├── java/
│   │   └── br/edu/ucs/matriculas/
│   │       ├── controller/
│   │       ├── dao/
│   │       ├── model/
│   │       ├── repository/
│   │       └── service/
│   └── resources/
│       ├── seed/
│       │   └── matriculas.csv
│       └── application.properties
```

---

## 🐘 Configuração do Banco

Banco de dados: **MySQL**
Nome do banco: **matriculas_db**

Execute no terminal MySQL:

```bash
CREATE DATABASE matriculas_db;
```

---

## ⚙️ Configuração de Ambiente

*Pré-requisitos necessários para rodar o projeto com sucesso.*

**Instale:**
- Java 17+
- Maven
- MySQL
- (Opcional) DBeaver para visualizar o banco

---

## 🚀 Como Executar Localmente

1.	Clone o repositório:

```bash
git clone https://github.com/felipepcastilhos/matriculas-backend.git
cd matriculas-backend
```

2.	Adicione o arquivo CSV `matriculas.csv`:

- Baixe o arquivo através do link: https://drive.google.com/drive/folders/1btHnmneCD6Yh_6_SfEtJxos47KzgIDF2?usp=sharing
- Colar o arquivo em:

```bash
src/main/resources/seed/matriculas.csv
```

Esse arquivo será utilizado para importar os dados para o banco (seed).
Ao iniciar a aplicação, os dados do CSV serão automaticamente carregados no banco de dados.
⚠️ O arquivo `matriculas.csv` é muito grande para estar no repositório (limite do GitHub), por isso é necessário adicioná-lo uma vez nesse diretório.

3.	Crie o banco de dados:

```bash
CREATE DATABASE matriculas_db;
```

4.	Compile o projeto:

```bash
mvn clean install
```

5.	Rode a aplicação:

```bash
mvn spring-boot:run
```

A API ficará disponível em: http://localhost:8080

---

## 📄 Licença

Este projeto é apenas para fins educacionais, conforme solicitado na disciplina da UCS.