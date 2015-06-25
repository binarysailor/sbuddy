package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.common.delayedsearch.SearchCriteriaProducer;
import bravura.sonata.buddy.common.delayedsearch.SearchEngine;
import bravura.sonata.buddy.common.delayedsearch.SearchTextListener;
import bravura.sonata.buddy.config.Preferences;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Optional;

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

    private SearchEngine<CodesAndTypesSearchCriteria> searchEngine;
    private SearchCriteriaProducer<CodesAndTypesSearchCriteria> criteriaProducer;
    private Preferences preferences;

    CodeOrIdPanel(
            String title,
            Preferences preferences,
            SearchEngine<CodesAndTypesSearchCriteria> searchEngine,
            SearchCriteriaProducer<CodesAndTypesSearchCriteria> criteriaProducer) {

        this.searchEngine = searchEngine;
        this.criteriaProducer = criteriaProducer;
        this.preferences = preferences;

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

    Optional<CodeIdPair> createCodeIdPair() {
        CodeIdPair pair = new CodeIdPair();
        if (codeRadio.isSelected() && !StringUtils.isEmpty(codeTextField.getText())) {
            pair.setCode(codeTextField.getText());
        }
        if (idRadio.isSelected() && !StringUtils.isEmpty(idTextField.getText())) {
            pair.setId(Long.valueOf(idTextField.getText()));
        }
        return pair.asOptional();
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
        textField.addKeyListener(new SearchTextListener<>(
                searchEngine, criteriaProducer, preferences.getSearchDelay()));
        return textField;
    }
}
