package bravura.sonata.buddy.ui;

import javax.swing.*;

/**
 * Created by tszymanski on 18/09/2015.
 */
public interface SonataBuddyTab {
    String getLabel();
    JPanel createUI();
}
