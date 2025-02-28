package ru.netology.ntlg_hw_jdbc.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.ntlg_hw_jdbc.Repository.SqlRepository;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Value("${app.script.select.name}")
    private String fileName;
    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlRepository sqlRepository(DataSource dataSource) {
        SqlRepository repository = new SqlRepository(dataSource);
        repository.setSelectProductNameScript(fileName);
        return repository;
    }
}
