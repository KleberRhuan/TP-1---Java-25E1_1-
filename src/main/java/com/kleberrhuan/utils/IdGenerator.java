package com.kleberrhuan.utils;

import com.kleberrhuan.utils.Exceptions.IdAutoConterLoadException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final String FILE_PATH = "data/id_counter.txt";
    private static AtomicLong counter = new AtomicLong(LoadLastId());

    public static synchronized long getNextId() {
        long id = counter.incrementAndGet();
        saveLastId(id);
        return id;
    }
    
    private static long LoadLastId(){
        Path path = Paths.get(FILE_PATH);
        if(!Files.exists(path)) { return 1;}
        
        try {
            List<String> lines = Files
                    .readAllLines(path, StandardCharsets.UTF_8);
            if (lines.isEmpty()) {
                return 1;
            }
            return Long.parseLong(lines.get(0));
        } catch (IOException | NumberFormatException e){
            System.out.println(e.getMessage());
            throw new IdAutoConterLoadException("Error Loading id counter");
        }
    }

    private static void saveLastId(long id) {
        Path path = Paths.get(FILE_PATH);
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            
            Files.writeString(
                    path,
                    String.valueOf(id),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to save ID counter", e);
        }
    }
}
