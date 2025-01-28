package com.ms.shared.util.util.bl;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.ms.shared.api.generic.GenericDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
/*
 *Company:mithlaSoftech Creation Date:2024
 *@author sumit kumar
 *@version 1.0
 */
@Service
public class DataReaderUtil {


    public static <T extends GenericDTO> List<T> fetchDataFromUrl(Class<T> clazz, String urlString, String header) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        URLConnection connection = null;
        try {
            connection = url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Using a BufferedReader to read the content
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder result = new StringBuilder();
            String line;
            if(header!=null){
                result.append(header);
            }
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
            System.out.println("result.toString()"  + result.toString());
            return parseCsvData(result.toString(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends GenericDTO> List<T> parseCsvData(String csvData, Class<T> clazz) {

        try (StringReader reader = new StringReader(csvData)) {
            StringReader stringReader = new StringReader(csvData);
            CSVReader csvReader = new CSVReaderBuilder(stringReader).build();
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(csvReader)
                    .withType(clazz)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .withQuoteChar('"')
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
