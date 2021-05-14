package seminar3.setup;

import seminar3.controller.Controller;
import seminar3.integration.*;
import seminar3.view.View;

/**
 * Starts the entire application, contains the main method used to start the application.
 */
public class Main {
    /**
     * The main method used to start the entire application.
     *
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        EAShandler eas = new EAShandler();
        EIShandler eis = new EIShandler();
        DChandler dc = new DChandler();
        Printer printer = new Printer();

        Controller contr = new Controller(eas,eis,dc,printer);
        View view = new View(contr);

        view.runFakeExecution();
    }
}
