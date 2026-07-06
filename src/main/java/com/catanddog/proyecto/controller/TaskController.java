package com.catanddog.proyecto.controller;

import com.catanddog.proyecto.excepciones.TaskException;
import com.catanddog.proyecto.excepciones.TaskValidationException;
import com.catanddog.proyecto.model.Task;
import com.catanddog.proyecto.model.TaskRepository;

import java.util.List;

public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData( id,  title,  description,  completed);
        Task task = new Task(id, title, description, completed);
        this.taskRepository.save(task);
        System.out.println("La tarea fue agregada existosamente");
    }

    public void removeTask(String id) throws TaskException, TaskValidationException {
        if(id==null || id.trim().isEmpty()){
            throw new TaskException("El id es inválido");
        }
            this.taskRepository.removeById(id);
    }

    public void showsTasks() throws TaskException, TaskValidationException {
        List<Task> tasks = this.taskRepository.findAll();
        if(tasks.isEmpty()){
            throw new TaskException("La lista no puede estar vacia");
        }
    }

    public void updateTask(String id, String title, String description, Boolean completed) throws TaskException, TaskValidationException {
        validateTaskData( id,  title,  description,  completed);
        Task updateTask = new Task(id, title, description, completed);
        this.taskRepository.updateTask(updateTask);
    }

    private void validateTaskData(String id, String title, String description, Boolean completed) throws TaskValidationException {
        if(id==null || id.trim().isEmpty()){
            throw new TaskValidationException("El id es inválido");
        }

        if(title==null || title.trim().isEmpty()){
            throw new TaskValidationException("El titulo es inválido");
        }

        if(description==null || description.trim().isEmpty()){
            throw new TaskValidationException("La descripcion es inválida");
        }

        if(completed==null){
            throw new TaskValidationException("El estado de la tarea es inválido");
        }

    }
}




