package bravura.sonata.buddy.ui;

import javax.swing.*;

/**
 * Created by tszymanski on 24/09/2015.
 */
public class ComponentFactory {
    public static JTextField createDefaultTextField(ComponentDefaults.TextFieldSize size) {
        JTextField field = new JTextField();
        field.setPreferredSize(size.getDimension());

        return field;
    }
}
