package bravura.sonata.buddy;

import javax.swing.*;

/**
 * Created by tszymanski on 02/11/2015.
 */
public class ErrorReporter {

    private static boolean interactive = true;
    private static JFrame mainFrame;

    public static void setInteractive(boolean interactive) {
        ErrorReporter.interactive = interactive;
    }

    public static void setMainFrame(JFrame mainFrame) {
        ErrorReporter.mainFrame = mainFrame;
    }

    public static void report(Exception ex) {
        String message = "Sorry, an unexpected error has occurred: " + ex.getMessage();
        if (isInteractive()) {
            JOptionPane.showMessageDialog(mainFrame, message, "Unexpected error", JOptionPane.ERROR_MESSAGE);
        } else {
            System.err.println(message);
        }
    }

    public static boolean isInteractive() {
        return interactive && mainFrame != null;
    }
}
