package com.catanddog.proyecto.model;

import com.catanddog.proyecto.excepciones.TaskException;

import java.util.*;

public class TaskRepository {
    List<Task> tasks = new ArrayList<>(); //Es un arraylist que almacena objetos del tipo Task

    public void add(Task task){
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

    public void remove(String id){
        Task task = findById(id);
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        tasks.remove(task);
    }

    public void remove(String task){
        if(task==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        if(!tasks.contains(task)){
            throw new TaskException("La tarea no puede ser no esta dentro del ArrayList");
        }
        tasks.remove(task);
    }

    public List<Task> findAll(){
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

    public void updateTask(Task updateTask){
        if(updateTask==null){
            throw new TaskException("La tarea no puede ser nula");
        }
        int index = findIndexById(updateTask.getId());
        if(index == -1){
            throw new TaskException("El indice no es valido");
        }
        tasks.set(index, updateTask);
    }
}
