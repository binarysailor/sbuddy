package bravura.sonata.buddy.codetypeviewer;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by tszymanski on 24/06/2015.
 */
class CodeOrIdPanel extends JPanel {

    private static final DocumentFilter CODE_FILTER = new CodeDocumentFilter();
    private static final DocumentFilter ID_FILTER = new IdDocumentFilter();

    private JRadioButton codeRadio;
    private JTextField codeTextField;
    private JRadioButton idRadio;
    private JTextField idTextField;

    CodeOrIdPanel(String title) {
        setBorder(BorderFactory.createTitledBorder(title));
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.LINE_START;

        codeRadio = new JRadioButton("Code");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        add(codeRadio, constraints);

        codeTextField = createCodeTextField();
        constraints.gridx = 1;
        constraints.weightx = 0.7;
        add(codeTextField, constraints);

        idRadio = new JRadioButton("Id");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 0.3;
        add(idRadio, constraints);

        idTextField = createIdTextField();
        constraints.gridx = 1;
        constraints.weightx = 0.7;
        add(idTextField, constraints);

        ButtonGroup group = new ButtonGroup();
        group.add(codeRadio);
        group.add(idRadio);
    }

    void addRadioButtonsToGroup(ButtonGroup group) {
        group.add(codeRadio);
        group.add(idRadio);
    }

    private JTextField createCodeTextField() {
        return createTextFieldWithFilter(CODE_FILTER);
    }

    private JTextField createIdTextField() {
        return  createTextFieldWithFilter(ID_FILTER);
    }

    private JTextField createTextFieldWithFilter(DocumentFilter filter) {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(80, 24));
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(filter);
        return textField;
    }
}
