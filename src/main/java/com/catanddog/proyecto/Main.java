package com.catanddog.proyecto;

import com.catanddog.proyecto.controller.TaskController;
import com.catanddog.proyecto.model.TaskRepository;
import com.catanddog.proyecto.view.TaskView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskController controller = new TaskController(repository);
        TaskView view = new TaskView(controller);

        view.showMenu();
    }
}
