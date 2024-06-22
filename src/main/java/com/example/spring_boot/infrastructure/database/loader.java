package com.example.spring_boot.infrastructure.database;

import com.example.spring_boot.infrastructure.spec.IStockRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
public class loader {
    @Autowired
    IStockRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Transactional
    void loadData() throws IOException, RuntimeException {
        InputStream resource = new ClassPathResource("./stocks.csv").getInputStream();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withIgnoreEmptyLines())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                jdbcTemplate.update(
                        "INSERT INTO STOCKS (id, date, symbol, volume, variation, spread, high, low) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ",
                        UUID.randomUUID(),
                        formatter.parse(csvRecord.get("Date")),
                        csvRecord.get("Symbol"),
                        Long.parseLong(csvRecord.get("Volume")),
                        Double.parseDouble(csvRecord.get("High")) - Double.parseDouble(csvRecord.get("Low")),
                        Double.parseDouble(csvRecord.get("Open")) - Double.parseDouble(csvRecord.get("Close")),
                        Double.parseDouble(csvRecord.get("High")),
                        Double.parseDouble(csvRecord.get("Low"))
                );
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

    }
}