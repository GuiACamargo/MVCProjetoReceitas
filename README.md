# Palmirinha Drinks MVC

Um projeto criado para o desafio de estudo em MVC (**_Model-View-Controller_**), um padrão para a
criação/arquitetura de software, que busca a reutilização do código e a separação dos conceitos
em três camadas diferentes:

- **Model** (Onde se localiza a lógica dos tratamentos de dados e as regras de negócio);
- **View** (Onde os dados solicitados do model são apresentados);
- **Controller** (Fica entre o *Model* e o *View*, coordenando as mudanças em ambos).

## Informações para dar o "*run*" no projeto

Ao baixar o projeto, primeiro confira se as versões e as tecnologias utilizadas no projeto estão disponíveis
em seu computador:

```
- Spring Tools Suite 4 (v4.14.1)
- MySQL Workbench 8
- Java SE 17
```
**_ATENÇÃO_**: Antes de dar o "*run*" no projeto localize o arquivo **application.properties** localizado em 
**src > main > resources > application.properties**. Ao abrir o arquivo localize as seguintes linhas:
```
spring.datasource.url=jdbc:mysql://localhost:3306/gft_drinks?createDatabaseIfNotExist=True
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
```
No lugar de ambos os "**root**" substituia o primeiro pelo seu username e o segundo pela senha do banco de dados
(*e outros que julgue necessário*)
do MySQL Workbench 8 para a utilização e integração correta do banco de dados. Para iniciar o projeto, aperte com o botão direito no projeto, e então, **Run As > Spring Boot App**, 
abra então o seu navegador de preferência o Google Chrome, que foi utilizado para o desenvolvimento e acesse localhost:8080.
**Usuário e Admins Iniciais**:
```
USUÁRIO
Usuário: usuario@gft.com
Senha: Gft@1234
---------------------------
ADMIN:
Usuário: admin@gft.com
Senha: Gft@1234
```
**_ATENÇÃO_**: O botão (verde) para popular o banco com as receitas se localiza no superior direito da página, no canto da navbar.
  





## Tecnologias Utilizadas

- [Java 17](https://docs.oracle.com/en/java/);
- [HTML5](https://devdocs.io/html/);
- [CSS3](https://devdocs.io/css/);
- [Spring Boot](https://spring.io/projects/spring-boot);
- [Spring Security](https://spring.io/projects/spring-security);
- [Spring Data](https://spring.io/projects/spring-data);
- [BootStrap](https://getbootstrap.com/);
- [MySQL Workbench](https://dev.mysql.com/doc/workbench/en/);
- [Thymeleaf](https://www.thymeleaf.org/);
- Outras.






## Redes Sociais
[![github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white
)](https://github.com/GuiACamargo)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/guilhermecamargodev/)

