package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.BuddyTabBuilder;
import bravura.sonata.buddy.codetypeviewer.datamodel.CodeType;
import bravura.sonata.buddy.common.delayedsearch.SearchCriteriaProducer;
import bravura.sonata.buddy.common.delayedsearch.SearchEngine;
import bravura.sonata.buddy.config.Preferences;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;


public class CodesAndTypesTab extends JPanel
        implements SearchEngine<CodesAndTypesSearchCriteria>, SearchCriteriaProducer<CodesAndTypesSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private CodeOrIdPanel codeTypePanel;
    private CodeOrIdPanel codePanel;
    private JTable codeTypesTable;
    private DefaultTableModel codeTypesModel;
    private JTable codesTable;
    private DefaultTableModel codesModel;
    private CodesAndTypesDAO codesAndTypesDAO;


    public CodesAndTypesTab(Preferences preferences, CodesAndTypesDAO dao) {
        this.codesAndTypesDAO = dao;
        new BuddyTabBuilder().setupTab(this);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 300;
        constraints.weightx = 0.3;
        codePanel = new CodeOrIdPanel("Search by code", preferences, this, this);
        add(codePanel, constraints);

        constraints.gridx = 1;
        codeTypePanel = new CodeOrIdPanel("Search by code type", preferences, this, this);
        add(codeTypePanel, constraints);

        groupRadioButtons();

        codeTypesModel = new DefaultTableModel(new Object[] {
                "Id", "Code", "Description"
        }, 0);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.weighty = 0.3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        codeTypesTable = new JTable(codeTypesModel);
        codeTypesTable.setBorder(BorderFactory.createTitledBorder("Code Types"));
        JScrollPane scrollPane = new JScrollPane(codeTypesTable);
        add(scrollPane, constraints);

        codesModel = new DefaultTableModel(new Object[] {
              "Id", "Code", "Description", "Short Description", "Tags"
        }, 0);
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.weighty = 0.7;
        codesTable = new JTable(codesModel);
        codesTable.setBorder(BorderFactory.createTitledBorder("Codes"));
        scrollPane = new JScrollPane(codesTable);
        add(scrollPane, constraints);
    }

    private void groupRadioButtons() {
        ButtonGroup group = new ButtonGroup();
        codeTypePanel.addRadioButtonsToGroup(group);
        codePanel.addRadioButtonsToGroup(group);
    }

    @Override
    public void search(CodesAndTypesSearchCriteria criteria) {
        List<CodeType> results = null;
        if (criteria.getCodeCode() != null) {
            results = codesAndTypesDAO.findByCodeCode(criteria.getCodeCode());
        } else if (criteria.getCodeId() != null) {
            results = codesAndTypesDAO.findByCodeId(criteria.getCodeId());
        } else if (criteria.getCodeTypeCode() != null) {
            results = codesAndTypesDAO.findByCodeTypeCode(criteria.getCodeTypeCode());
        } else if (criteria.getCodeTypeId() != null) {
            results = codesAndTypesDAO.findByCodeTypeId(criteria.getCodeTypeId());
        }

        if (results != null) {
            displayResults(results);
        }
    }

    @Override
    public CodesAndTypesSearchCriteria getSearchCriteria() {
        return new CodesAndTypesSearchCriteria(
                codePanel.createCodeIdPair(), codeTypePanel.createCodeIdPair());
    }

    private void displayResults(List<CodeType> results) {
        codeTypesModel.setRowCount(0);
        for (CodeType codeType : results) {
            
        }
    }
}
