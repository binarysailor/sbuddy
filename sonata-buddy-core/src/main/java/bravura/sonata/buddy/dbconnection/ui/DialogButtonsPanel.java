package bravura.sonata.buddy.dbconnection.ui;

import javax.swing.*;

/**
 * Created by tszymanski on 20/09/2015.
 */
class DialogButtonsPanel extends JPanel {

    interface DialogButtonsListener {
        void onSave();

        void onCancel();
    }

    private DialogButtonsListener listener;

    DialogButtonsPanel(DialogButtonsListener listener) {
        this.listener = listener;

        JButton cancelButton = new JButton(("Cancel"));
        cancelButton.addActionListener(e -> {
            listener.onCancel();
        });
        add(cancelButton);

        JButton saveButton = new JButton(("Save"));
        saveButton.addActionListener(e -> {
            listener.onSave();
        });
        add(saveButton);
    }
}
