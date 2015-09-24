package bravura.sonata.buddy.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tszymanski on 19/09/2015.
 */
public class ComponentDefaults {
    public enum TextFieldSize {
        SMALL(80), MEDIUM(140), LARGE(200);

        private final int HEIGHT = 24;
        private final Dimension dimension;

        public int getWidth() {
            return dimension.width;
        }

        public int getHeight() {
            return dimension.height;
        }

        public Dimension getDimension() {
            return dimension;
        }

        TextFieldSize(int width) {
            this.dimension = new Dimension(width, HEIGHT);
        }
    }

    public static void setupTab(JComponent tab) {
        tab.setPreferredSize(new Dimension(800, 600));
    }

    public static void setupTextField(JTextField field, TextFieldSize size) {
        field.setPreferredSize(size.getDimension());
    }
}
