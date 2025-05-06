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
- Swagger (documentação da API)

---

## 📁 Estrutura Base do Projeto

```bash
src/
├── main/
│   ├── java/
│   │   └── br/edu/ucs/matriculas/
│   │       ├── config/
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
mysql -u root -p
CREATE DATABASE matriculas_db;
USE matriculas_db;
```

Após a importação dos dados (via seed ou dump), é possível verificar a carga com:

```bash
SELECT COUNT(*) FROM matriculas;
```

---

## ⚙️ Configuração de Ambiente

_Pré-requisitos necessários para rodar o projeto com sucesso._

**Instale:**

- Java 17+
- Maven
- MySQL
- (Opcional) DBeaver para visualizar o banco

---

## 🚀 Como Executar Localmente

1. Clone o repositório:

```bash
git clone https://github.com/felipepcastilhos/matriculas-backend.git
cd matriculas-backend
```

2. Adicione o arquivo CSV `matriculas.csv`:

- Baixe o arquivo através do link: https://drive.google.com/drive/folders/1btHnmneCD6Yh_6_SfEtJxos47KzgIDF2?usp=sharing
- Colar o arquivo em:

```bash
src/main/resources/seed/matriculas.csv
```

Esse arquivo será utilizado para importar os dados para o banco (seed).  
Ao iniciar a aplicação com o perfil **dev**, os dados do CSV serão automaticamente carregados no banco de dados.  
⚠️ O arquivo `matriculas.csv` é muito grande para estar no repositório (limite do GitHub), por isso é necessário adicioná-lo uma vez nesse diretório.  
✅ Caso não deseje utilizar o seed, mas sim importar os dados diretamente por SQL, utilize o arquivo matriculas_dump.sql e execute:

```bash
mysql -u root -p matriculas_db < ~/Downloads/matriculas_dump.sql
```

3. Compile o projeto:

```bash
mvn clean install
```

4. Rode a aplicação:

```bash
mvn spring-boot:run
```

A API ficará disponível em: http://localhost:8080

---

## 🧪 Documentação da API com Swagger

A documentação e testes da API REST estão disponíveis automaticamente ao rodar o projeto.
Acesse via navegador:

```bash
http://localhost:8080/swagger-ui/index.html
```

📌 Permite visualizar e testar as rotas disponíveis da API do sistema de matrículas.

---

## 📄 Licença

Este projeto é apenas para fins educacionais, conforme solicitado na disciplina da UCS.
