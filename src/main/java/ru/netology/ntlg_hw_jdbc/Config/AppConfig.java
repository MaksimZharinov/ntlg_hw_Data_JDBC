package ru.netology.ntlg_hw_jdbc.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.netology.ntlg_hw_jdbc.Repository.SqlRepository;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Value("${app.script.select.name}")
    private String fileName;
    private final NamedParameterJdbcTemplate jdbcTemplate =
            new NamedParameterJdbcTemplate(new JdbcTemplate());

    @Bean
    public SqlRepository sqlRepository() {
        SqlRepository repository = new SqlRepository();
        repository.setNamedParameterJdbcTemplate(jdbcTemplate);
        repository.setSelectProductNameScript(fileName);
        return repository;
    }
}
