package org.example.storage;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.example.model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public class TaskStorage {
    private static final String FILE_PATH = "src/main/resources/tasks.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>)
                    (json, type, context) -> LocalDateTime.parse(json.getAsString()))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>)
                    (src, type, context) -> new JsonPrimitive(src.toString()))
            .setPrettyPrinting()
            .create();

    public static void saveTasks(List<Task> tasks) throws IOException {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer);
        }
    }

    public static List<Task> loadTasks() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new java.util.ArrayList<>();
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(reader, listType);
        }
    }
}
