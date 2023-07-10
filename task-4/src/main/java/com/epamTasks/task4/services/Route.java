package com.epamTasks.task4.services;

import com.epamTasks.task4.controllers.Controller;
import com.epamTasks.task4.controllers.StoreCLIController;
import com.epamTasks.task4.controllers.StoreWebController;

public class Route {
    public static void handleRequest() {
        Controller controller = isCLI() ?
                new StoreCLIController() : new StoreWebController();
        controller.handleRequest();
    }

    private static boolean isCLI() {
        return true; //TODO needs implementation;
    }
}
