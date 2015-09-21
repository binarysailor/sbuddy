package bravura.sonata.buddy.ui;

import java.awt.Window;

/**
 * Created by tszymanski on 19/09/2015.
 */
public class WindowPosition {
    public static void centerRelativeTo(Window toBePositioned, Window reference) {
        int x = (reference.getWidth() - toBePositioned.getWidth()) / 2;
        int y = (reference.getHeight() - toBePositioned.getHeight()) / 2;
        toBePositioned.setLocation(x, y);
    }
}
