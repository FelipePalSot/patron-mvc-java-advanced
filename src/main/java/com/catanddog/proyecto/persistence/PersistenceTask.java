package com.catanddog.proyecto.persistence;

import com.catanddog.proyecto.model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenceTask {
    private static final String PATH_ARCHIVO = "tasks.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();;

//    public ProductoRepository() {
//        // Usamos GsonBuilder para que el archivo JSON quede ordenado y legible (pretty printing)
//        this.gson = new GsonBuilder().setPrettyPrinting().create();
//    }
    /*No se puede inicializar aca porque como los metodos son estaticos sus
    propiedades tambien tienen que se estaticas , lo cual a fuerza a inicializar cuando
    se declara  */

    // Guardar la lista completa en el archivo JSON
    public static void saveTasksPersistences(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(PATH_ARCHIVO)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar la lista desde el archivo JSON
    public static List<Task> loadTasksPersistences() {
        File jsonCreated = new File(PATH_ARCHIVO);
        if (!jsonCreated.exists()) {
            return new ArrayList<>(); // Si no existe el archivo, devolvemos una lista vacía
        }

        try (FileReader reader = new FileReader(PATH_ARCHIVO)) {
            // Definimos el tipo de dato que esperamos (List<Producto>)
            Type tipoLista = new TypeToken<ArrayList<Task>>(){}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
