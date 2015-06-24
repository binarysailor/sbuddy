package bravura.sonata.buddy.codetypeviewer;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Created by tszymanski on 24/06/2015.
 */
class CodeDocumentFilter extends DocumentFilter {

    private static final int MAX_LENGTH = 5;

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (fb.getDocument().getLength() + string.length() <= MAX_LENGTH) {
            super.insertString(fb, offset, string.toUpperCase(), attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (fb.getDocument().getLength() - length + text.length() <= MAX_LENGTH) {
            super.replace(fb, offset, length, text.toUpperCase(), attrs);
        }
    }
}
