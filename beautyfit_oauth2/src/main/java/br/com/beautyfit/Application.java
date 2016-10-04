package br.com.beautyfit;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.beautyfit" })
public class Application extends SpringBootServletInitializer {
	
    private static final String CREATE_OAUTH_ACCESS_TOKEN_SQL = "create table if not exists oauth_access_token ("+
														            "token_id VARCHAR(256),"+
														            "token blob,"+
														            "authentication_id VARCHAR(256) PRIMARY KEY,"+
														            "user_name VARCHAR(256),"+
														            "client_id VARCHAR(256),"+
														            "authentication blob,"+
														            "refresh_token VARCHAR(256)"+
														            ");";
    
    private static final String DROP_TABLE_TOKEN = "DROP TABLE IF EXISTS oauth_access_token;";

    private static final String DELETE_TOKENS_SQL = "delete from oauth_access_token";
    
    private static final String DELETE_DATA_SQL = "delete from user";
    
    private static final String INSERT_DATA = "INSERT INTO user(user_id, email, password, username, user_role_id)" +
    											"VALUES (null, 'ederson@gmail.com', 'admin', 'admin', null);";
	
	@Autowired
	private DataSource dataSource;
	
   @PostConstruct
    public void setUpTokenDatasource() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(DROP_TABLE_TOKEN);
        jdbcTemplate.execute(CREATE_OAUTH_ACCESS_TOKEN_SQL);
        jdbcTemplate.execute(DELETE_TOKENS_SQL);
        jdbcTemplate.execute(DELETE_DATA_SQL);
        jdbcTemplate.execute(INSERT_DATA);
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}