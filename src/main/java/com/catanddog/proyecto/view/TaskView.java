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
            System.out.println("5. Salir");
            System.out.println("Seleccina una opcion");

            String option = scanner.nextLine();
            switch (option){
                case "1":
                    addTaskView();
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

                    break;
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

    private Task getTaskInput(){

        System.out.println("Ingresar ID");
        String id = scanner.nextLine();

        System.out.println("Ingresar Titulo");
        String title = scanner.nextLine();

        System.out.println("Ingresar la descripcion");
        String description = scanner.nextLine();

        System.out.println("Esta completado? true/false");
        Boolean completed = Boolean.parseBoolean(scanner.nextLine());

        return new Task(id, title, description, completed);
    }

}
