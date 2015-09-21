package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.codetypeviewer.datamodel.Code;
import bravura.sonata.buddy.codetypeviewer.datamodel.CodeType;
import bravura.sonata.buddy.config.Preferences;
import bravura.sonata.buddy.delayedsearch.SearchCriteriaProducer;
import bravura.sonata.buddy.delayedsearch.SearchEngine;
import bravura.sonata.buddy.progress.ComponentInsertion;
import bravura.sonata.buddy.progress.ProgressIndicator;

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

    private ProgressIndicator indicator;
    private List<CodeType> searchResults;

    public CodesAndTypesTab(Preferences preferences, CodesAndTypesDAO dao) {
        this.codesAndTypesDAO = dao;

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
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.weighty = 0.3;
        constraints.fill = GridBagConstraints.BOTH;
        codeTypesTable = new JTable(codeTypesModel);
        codeTypesTable.getSelectionModel().addListSelectionListener(e ->
            selectCodeTypeRow(codeTypesTable.getSelectedRow())
        );

        JScrollPane codeTypesScrollPane = new JScrollPane(codeTypesTable);
        add(codeTypesScrollPane, constraints);

        codesModel = new DefaultTableModel(new Object[] {
              "Id", "Code", "Description", "Short Description", "Tags"
        }, 0);
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weighty = 0.7;
        codesTable = new JTable(codesModel);

        JScrollPane codesScrollPane = new JScrollPane(codesTable);
        add(codesScrollPane, constraints);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 1.0;
        ComponentInsertion progressBarInsertion = new ComponentInsertion(this, c);
        ComponentInsertion noResultsInsertion = new ComponentInsertion(this, c);
        indicator = new ProgressIndicator(
                progressBarInsertion, noResultsInsertion,
                new JComponent[] {codeTypesScrollPane, codesScrollPane}
        );
    }

    @Override
    public void search(CodesAndTypesSearchCriteria criteria) {
        indicator.startSearch();

        if (criteria.getCodeCode() != null) {
            searchResults = codesAndTypesDAO.findByCodeCode(criteria.getCodeCode());
        } else if (criteria.getCodeId() != null) {
            searchResults = codesAndTypesDAO.findByCodeId(criteria.getCodeId());
        } else if (criteria.getCodeTypeCode() != null) {
            searchResults = codesAndTypesDAO.findByCodeTypeCode(criteria.getCodeTypeCode());
        } else if (criteria.getCodeTypeId() != null) {
            searchResults = codesAndTypesDAO.findByCodeTypeId(criteria.getCodeTypeId());
        }

        updateResults();
    }

    @Override
    public CodesAndTypesSearchCriteria getSearchCriteria() {
        return new CodesAndTypesSearchCriteria(
                codePanel.createCodeIdPair(), codeTypePanel.createCodeIdPair());
    }

    private void groupRadioButtons() {
        ButtonGroup group = new ButtonGroup();
        codeTypePanel.addRadioButtonsToGroup(group);
        codePanel.addRadioButtonsToGroup(group);
    }

    private void updateResults() {
        if (searchResults.isEmpty()) {
            indicator.endSearchWithNoResults();
        } else {
            indicator.endSearchWithResults();
            int selectedCodeTypeIndex = repopulateCodeTypesTable();
            selectCodeTypeRow(selectedCodeTypeIndex);
        }
    }

    private int repopulateCodeTypesTable() {
        int selectedRow = -1;
        codeTypesModel.setRowCount(0);
        int i = 0;
        for (CodeType codeType : searchResults) {
            codeTypesModel.addRow(new Object[]{
                    codeType.getId(), codeType.getCode(), codeType.getDescription()
            });
            if (selectedRow < 0 || codeType.getSelectedCode() != null) {
                selectedRow = i;
            }
            i++;
        }
        return selectedRow;
    }

    private void selectCodeTypeRow(int codeTypeRowIndex) {
        if (codeTypeRowIndex >= 0) {
            codeTypesTable.setRowSelectionInterval(codeTypeRowIndex, codeTypeRowIndex);
            int selectedCodeIndex = repopulateCodesTable(codeTypeRowIndex);
            selectCodeRow(selectedCodeIndex);
        } else {
            codesModel.setRowCount(0);
        }
    }

    private int repopulateCodesTable(int codeTypeRowIndex) {
        int selectedCodeIndex = -1;
        codesModel.setRowCount(0);
        int i = 0;
        CodeType selectedCodeType = searchResults.get(codeTypeRowIndex);
        for (Code code : selectedCodeType.getCodes()) {
            codesModel.addRow(new Object[]{
                    code.getId(), code.getCode(), code.getDescription(),
                    code.getShortDescription(), code.getTags()
            });
            if (code == selectedCodeType.getSelectedCode()) {
                selectedCodeIndex = i;
            }
            i++;
        }
        return selectedCodeIndex;
    }

    private void selectCodeRow(int selectedCodeIndex) {
        if (selectedCodeIndex >= 0) {
            codesTable.setRowSelectionInterval(selectedCodeIndex, selectedCodeIndex);
        }
    }
}
