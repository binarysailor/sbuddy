package bravura.sonata.buddy.common.progress;

import javax.swing.*;

/**
 * Created by tszymanski on 29/06/2015.
 */
public class ComponentInsertion {
    private final JComponent parent;
    private final Object constraints;

    private JComponent insertedComponent;

    public ComponentInsertion(JComponent parent, Object constraints) {
        this.constraints = constraints;
        this.parent = parent;
    }

    ComponentInsertion(ComponentInsertion other) {
        this.parent = other.parent;
        this.constraints = other.constraints;
    }

    void insert(JComponent component) {
        parent.add(component, constraints);
        this.insertedComponent = component;
        repaint();
    }

    void remove() {
        if (insertedComponent != null) {
            this.parent.remove(insertedComponent);
            this.insertedComponent = null;
            repaint();
        }
    }

    private void repaint() {
        JRootPane rootPane = parent.getRootPane();
        if (rootPane != null) {
            rootPane.invalidate();
            rootPane.repaint();
        }
    }
}
