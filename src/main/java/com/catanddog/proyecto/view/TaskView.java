package com.catanddog.proyecto.view;

import com.catanddog.proyecto.controller.TaskController;
import com.catanddog.proyecto.excepciones.TaskException;
import com.catanddog.proyecto.excepciones.TaskValidationException;
import com.catanddog.proyecto.model.Task;

import java.util.Scanner;

public class TaskView {
    private  final TaskController taskController;
    private  final Scanner scanner;

    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu(){
        while(true){
            System.out.println("\n Gestion de tareas");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Eliminar");
            System.out.println("3. Actualizar");
            System.out.println("4. Mostrar Tareas");
            System.out.println("5. Actualizar estado de la tarea");
            System.out.println("6. Mostrar Tareas completadas");
            System.out.println("7. Mostrar Tareas pendientes");
            System.out.println("8. Salir");
            System.out.println("Seleccina una opcion");

            String option = scanner.nextLine();
            switch (option){
                case "1":
                    addTaskView();
                    break;
                case "2":
                    removeTaskView();
                    break;
                case "3":
                    updateTaskView();
                    break;
                case "4":
                    showTasksView();
                    break;
                case "5":
                    System.out.println("Saliendo del Sistema");
                    break;
                case "6":
                    showCompletedTasksView();
                    break;
                case "7":
                    showPendingTasksView();
                    break;
                case "8":
                    System.out.println("Saliendo del sistema");
                    return;
                default:
                    System.out.println("Opcion invalida, Intentelo");
            }
        }
    }

    public void addTaskView(){
        try {
            Task task = getTaskInput();
            taskController.addTask(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted());
            System.out.println("Tarea agregada correctamente");
        } catch (TaskException | TaskValidationException e) {
            System.out.println(" Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void removeTaskView(){
        try {
            System.out.println("Ingrese el Id a eliminar");
            String id = scanner.nextLine();
            this.taskController.removeTask(id);
            System.out.println("Tarea eliminada correctamente");
        }  catch (TaskException | TaskValidationException e) {
            System.out.println(" Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void showTasksView(){
        try {
            System.out.println("\n Lista de Tareas");

            this.taskController.showsTasks();

        }  catch (TaskException | TaskValidationException e) {
            System.out.println(" Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado, Contacte con soporte");
            e.printStackTrace();
        }
    }

    public void updateTaskView(){
        try {
            Task task = getTaskInput();
            taskController.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted());
            System.out.println("Tarea actualizada correctamente");
        } catch (TaskException | TaskValidationException e) {
            System.out.println(" Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado");
            e.printStackTrace();
        }
    }

    public void updateTaskCompletedView(){
        try {
            System.out.println("Ingrese el ID de la Tarea");
            String id = scanner.nextLine();

            Boolean completed=null;
            while (completed==null) {
                System.out.println("¿Está completada? true/false");
                String input = scanner.nextLine().trim().toLowerCase();
                if(input.equals("true")){
                    completed = true;
                }else if(input.equals("false")){
                    completed = false;
                }else{
                    System.out.println("El valor ingresado no es correcto, ingrese: 'true' o 'false'");
                }
            }
            taskController.updateTaskCompleted(id, completed);
            System.out.println("Estado de la tarea actualizado correctamente");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e){
            System.out.println("Error inesperado, Contacte con el soporte");
            e.printStackTrace();
        }
    }

    public void showCompletedTasksView(){
        try{
            System.out.println("Tareas Completadas");
            taskController.showCompletedTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public void showPendingTasksView(){
        try{
            System.out.println("Tareas Pendientes");
            taskController.showPendingTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private Task getTaskInput(){
        String id ;
        do{
            System.out.println("Ingresar ID");
            id = scanner.nextLine();
            if(id.isEmpty()){
                System.out.println("ID no puede estar vacio");
            }
        }while (id.isEmpty());

        String title;
        do{
            System.out.println("Ingresar Titulo");
            title = scanner.nextLine();
            if(title.isEmpty()){
                System.out.println("El titulo no puede estar vacio");
            }
        }while (title.isEmpty());

        String description;
        do{
            System.out.println("Ingresar la descripcion");
            description = scanner.nextLine();
            if(description.isEmpty()){
                System.out.println("La descripcion no puede estar vacio");
            }
        }while (description.isEmpty());

        Boolean completed = null;
        do{
            System.out.println("Esta completado? true/false");
            String input = scanner.nextLine().trim().toLowerCase();
            if(input.equals("true")){
                completed = true;
            } else if (input.equals("false")) {
                completed = false;
            } else {
                System.out.println("El valor ingresado no es correcto, ingrese: 'true' o 'false' ");
            }

        }while (completed==null);



        return new Task(id, title, description, completed);
    }

}
