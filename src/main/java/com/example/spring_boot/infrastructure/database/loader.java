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
//    @Autowired
//    IStockRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    @Transactional
    void loadData() throws IOException, RuntimeException {
        InputStream resource = new ClassPathResource("./stocks.csv").getInputStream();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreEmptyLines())) {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                jdbcTemplate.update(
                        "INSERT INTO stocks (id, date, symbol, volume, variation, spread, high, low) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ",
                        UUID.randomUUID(),
                        formatter.parse(csvRecord.get("Date")),
                        csvRecord.get("Symbol"),
                        ConvertIntoLong(csvRecord.get("Volume")),
                        ConvertIntoDouble(csvRecord.get("High")) - ConvertIntoDouble(csvRecord.get("Low")),
                        ConvertIntoDouble(csvRecord.get("Open")) - ConvertIntoDouble(csvRecord.get("Close")),
                        ConvertIntoDouble(csvRecord.get("High")),
                        ConvertIntoDouble(csvRecord.get("Low"))
                );
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }

    }

    private Long ConvertIntoLong(String xVal)
    {
        try
        {
            return Long.parseLong(xVal);
        }
        catch(Exception ex)
        {
            return 0L;
        }
    }

    private Double ConvertIntoDouble(String xVal)
    {
        try
        {
            return Double.parseDouble(xVal);
        }
        catch(Exception ex)
        {
            return 0D;
        }
    }
}