package ru.netology.ntlg_hw_jdbc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.ntlg_hw_jdbc.Repository.SqlRepository;

@RestController
public class SqlController {

    SqlRepository sqlRepository;

    @Autowired
    public SqlController(SqlRepository sqlRepository) {
        this.sqlRepository = sqlRepository;
    }

    @GetMapping("/products/fetch-product")
    public String fetchProduct(@RequestParam("name") String name) {
        return sqlRepository.getProductName(name);
    }
}