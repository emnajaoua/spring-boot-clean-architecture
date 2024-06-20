package com.example.spring_boot.infrastructure.database;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class loader {

    private final JdbcTemplate jdbcTemplate;

    public loader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    @Transactional
    void loadData() throws IOException {
        InputStream resource = new ClassPathResource(
                "resources/stocks.csv").getInputStream();
        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(resource)) ) {
            reader.lines().skip(1).map(  line ->
                    Arrays.stream(line.split(",")).dropWhile(String::isEmpty)

        ).forEach( (Stream<String> fields) -> {
                            if (fields.count() >= 8) {
                    jdbcTemplate.update(
                            "INSERT INTO stocks(id, date, symbol, open, high, low, close, adj_close, volume) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                            UUID.randomUUID(),
                            Date.valueOf(fields.toList().get(0)),
                            fields.toList().get(1),
                            Double.valueOf(fields.toList().get(2)),
                            Double.valueOf(fields.toList().get(3)),
                            Double.valueOf(fields.toList().get(4)),
                            Double.valueOf(fields.toList().get(5)),
                            Double.valueOf(fields.toList().get(6)),
                            Long.valueOf(fields.toList().get(7))
                    );
                }
            });
        }

        }

}
