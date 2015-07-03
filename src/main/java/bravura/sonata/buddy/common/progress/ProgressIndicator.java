package bravura.sonata.buddy.common.progress;

import javax.swing.*;
import java.awt.Dimension;

/**
 * Created by tszymanski on 29/06/2015.
 */
public class ProgressIndicator {

    private static final long FAKE_SEARCH_DELAY_MS = 100;

    private final JComponent[] componentsToHide;
    private final ComponentInsertion progressBarInsertion;
    private final ComponentInsertion noResultsInsertion;
    private final JProgressBar progressBar;
    private final JLabel noResults;

    public ProgressIndicator(ComponentInsertion progressBarInsertion, ComponentInsertion noResultsInsertion, JComponent[] componentsToHide) {
        this.componentsToHide = componentsToHide;
        this.progressBarInsertion = new ComponentInsertion(progressBarInsertion); // copy
        this.noResultsInsertion = new ComponentInsertion(noResultsInsertion); // copy

        this.progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setPreferredSize(new Dimension(300, 30));

        this.noResults = new JLabel("Sorry, I haven't found anything");
        this.noResults.setHorizontalAlignment(JLabel.CENTER);
    }

    public void startSearch() {
        SwingUtilities.invokeLater(() -> {
            hideResultComponents();
            noResultsInsertion.remove();
            progressBarInsertion.insert(progressBar);
        });

        // to assure the user that his typing does anything, show the progress bar at least for a noticeable while
        fakeDelay();
    }

    private void fakeDelay() {
        try {
            Thread.sleep(FAKE_SEARCH_DELAY_MS);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void endSearchWithNoResults() {
        SwingUtilities.invokeLater(() -> {
            progressBarInsertion.remove();
            noResultsInsertion.insert(noResults);
        });
    }

    public void endSearchWithResults() {
        SwingUtilities.invokeLater(() -> {
            progressBarInsertion.remove();
            showResultComponents();
        });
    }

    private void hideResultComponents() {
        for (JComponent componentToHide : componentsToHide) {
            componentToHide.setVisible(false);
        }
    }

    private void showResultComponents() {
        for (JComponent componentToHide : componentsToHide) {
            componentToHide.setVisible(true);
        }
    }
}
