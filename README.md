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