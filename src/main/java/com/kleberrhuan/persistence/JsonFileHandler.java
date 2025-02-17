package com.kleberrhuan.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kleberrhuan.persistence.Exceptions.JsonPersistException;
import com.kleberrhuan.persistence.Exceptions.JsonReadException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonFileHandler {
    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);
    
    public static <K, T> void saveFile(Map<K, T> items, String jsonFilePath)  {
        try {
            File file = new File(jsonFilePath);
            mapper.writeValue(file, items);
        } catch (IOException e) {
            throw new JsonPersistException(e.getMessage());
        }
    }

    public static <K, T> Map<K, T> loadDataFromFile(String jsonFilePath, 
                                                    Class<K> keyClazz,
                                                    Class<T> valueClazz){
        try {
            File file = new File(jsonFilePath);
            if(!file.exists()){throw new RuntimeException("Json File not exists!");}
            if (file.length() == 0) {
                return new HashMap<>();
            }
            var mapType = mapper.getTypeFactory()
                    .constructMapType(Map.class, keyClazz, valueClazz);
            return mapper.readValue(file, mapType);
        } catch (IOException e){
            throw new JsonReadException(e.getMessage());
        }
    }
    
}
