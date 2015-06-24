package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.BuddyTabBuilder;
import bravura.sonata.buddy.common.delayedsearch.SearchEngine;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class CodesAndTypesTab extends JPanel implements SearchEngine<CodesAndTypesSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private final CodeOrIdPanel codeTypePanel;
    private final CodeOrIdPanel codePanel;


    public CodesAndTypesTab() {
        new BuddyTabBuilder().setupTab(this);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 300;
        constraints.weightx = 0.3;
        codePanel = new CodeOrIdPanel("Search by code");
        add(codePanel, constraints);

        constraints.gridx = 1;
        codeTypePanel = new CodeOrIdPanel("Search by code type");
        add(codeTypePanel, constraints);

        groupRadioButtons();
    }

    private void groupRadioButtons() {
        ButtonGroup group = new ButtonGroup();
        codeTypePanel.addRadioButtonsToGroup(group);
        codePanel.addRadioButtonsToGroup(group);
    }

    @Override
    public void search(CodesAndTypesSearchCriteria criteria) {

    }
}
