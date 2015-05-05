package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.BuddyTabBuilder;

import javax.swing.JScrollPane;


public class CheetSheetTab extends JScrollPane {

    private static final long serialVersionUID = 1L;


    public CheetSheetTab() {
        new BuddyTabBuilder().setupTab(this);
    }

}
