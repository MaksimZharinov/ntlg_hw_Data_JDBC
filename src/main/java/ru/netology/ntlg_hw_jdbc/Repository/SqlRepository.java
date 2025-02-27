package ru.netology.ntlg_hw_jdbc.Repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Collectors;

@Repository
public class SqlRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String selectProductNameScript;

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSelectProductNameScript(String scriptFileName) {
        this.selectProductNameScript = read(scriptFileName);
    }

    public String getSelectProductNameScript() {
        return selectProductNameScript;
    }

    public String getProductName(String name) {

        return namedParameterJdbcTemplate.query(
                selectProductNameScript,
                new HashMap<String, Object>() {{
                    put("name", name);
                }},
                (rs, rowNum) -> rs.getString("name")
        ).toString();
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
