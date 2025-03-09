package ru.netology.ntlg_hw_jdbc.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SqlRepository {

    @Value("${app.script.select.name}")
    private String scriptFileName;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String selectProductNameScript;

    public SqlRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.selectProductNameScript = read(scriptFileName);

    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSelectProductNameScript() {
        return selectProductNameScript;
    }

    public List<String> getProductName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name.toLowerCase());

        return namedParameterJdbcTemplate.queryForList(
                selectProductNameScript,
                params,
                String.class
        );
    }
}
