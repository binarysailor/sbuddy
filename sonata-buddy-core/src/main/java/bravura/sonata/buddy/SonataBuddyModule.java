package bravura.sonata.buddy;

import bravura.sonata.buddy.ui.SonataBuddyTab;

import java.util.Collection;

/**
 * Created by tszymanski on 18/09/2015.
 */
public interface SonataBuddyModule {
    Collection<SonataBuddyTab> getTabs();
}
