package bravura.sonata.buddy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;


public class BuddyLauncher {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error while setting L&F");
            return;
        }
        final ApplicationContext ctx = new ClassPathXmlApplicationContext("/appcontext.xml");
        SwingUtilities.invokeLater(() -> {
                new MainWindow(ctx).setVisible(true);
        });
    }
}
