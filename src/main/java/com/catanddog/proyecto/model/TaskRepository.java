package com.catanddog.proyecto.model;

import com.catanddog.proyecto.excepciones.TaskException;
import com.catanddog.proyecto.persistence.PersistenceTask;

import java.util.*;

public class TaskRepository {
    List<Task> tasks = new ArrayList<>(); //Es un arraylist que almacena objetos del tipo Task

    public TaskRepository() {
        tasks = PersistenceTask.loadTasksPersistences();
    }

    public void save(Task task) throws TaskException {
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        tasks.add(task);
        PersistenceTask.saveTasksPersistences(tasks);
    }

    public void add(Task task) throws TaskException {
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        tasks.add(task);
    }

    public Task findById(String id){
        for (Task task:tasks){
            if(task.getId().equals(id)){
                return  task;
            }
        }
        return null;
    }

    public List<Task> findCompletedTasks() throws TaskException {

        List<Task> completedTasks = new ArrayList<>();
        for (Task task: tasks){
            if(task.getCompleted()){
                completedTasks.add(task);
            }
        }

        if(completedTasks.isEmpty()){
            throw new TaskException("No hay tareas completadas");
        }
        return completedTasks;
    }

    public List<Task> findPendingTasks() throws TaskException {

        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.getCompleted()) {
                pendingTasks.add(task);
            }
        }

        if (pendingTasks.isEmpty()) {
            throw new TaskException("No hay tareas pendientes");
        }
        return pendingTasks;
    }


    public void removeById(String id) throws TaskException {
        Task task = findById(id);
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        tasks.remove(task);
        PersistenceTask.saveTasksPersistences(tasks);
    }

    public void removeByTask(String task) throws TaskException {
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        if(!tasks.contains(task)){
            throw new TaskException("La tarea no puede ser no esta dentro del ArrayList");
        }
        tasks.remove(task);
        PersistenceTask.saveTasksPersistences(tasks);
    }

    public List<Task> findAll() throws TaskException {
        if(tasks.isEmpty()){
            throw new TaskException("La lista esta vacia");
        }
        return tasks;
    }

    public int findIndexById(String id){
        for(int i=0; i<tasks.size(); i++){
            if(tasks.get(i).getId().equals(id)){ //.get es un metodo de los arrayList , por eso tasks , la puede usar
                return i;
            }
        }
        return -1;
    }

    public void updateTask(Task updateTask) throws TaskException {
        if(updateTask==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        int index = findIndexById(updateTask.getId());
        if(index == -1){
            throw new TaskException("El indice no es valido");
        }
        tasks.set(index, updateTask);
        PersistenceTask.saveTasksPersistences(tasks);
    }

    public void updateTaskCompleted(String id, Boolean completed) throws TaskException {

        int index = findIndexById(id);
        if(index==-1){
            throw new TaskException("El índice no es válido");
        }
        tasks.get(index).setCompleted(completed);
        PersistenceTask.saveTasksPersistences(tasks);
    }
}
