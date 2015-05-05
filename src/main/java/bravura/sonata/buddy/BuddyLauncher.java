package bravura.sonata.buddy;

import bravura.sonata.buddy.MainWindow;

import javax.swing.SwingUtilities;


public class BuddyLauncher {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                new MainWindow().setVisible(true);
        });
    }
}
