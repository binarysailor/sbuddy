package bravura.sonata.buddy.navigsearch;

import bravura.sonata.buddy.SonataBuddyModule;
import bravura.sonata.buddy.ui.SonataBuddyTab;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by tszymanski on 18/09/2015.
 */
public class NavigSearchModule implements SonataBuddyModule, ApplicationContextAware {

    private ApplicationContext applicationContext;

    SonataBuddyTab tab = new SonataBuddyTab() {
        @Override
        public String getLabel() {
            return "Navigator Search";
        }

        @Override
        public JPanel createUI() {
            return (NavigSearchTab)applicationContext.getBean("ui.tabs.navigSearch");
        }
    };

    @Override
    public Collection<SonataBuddyTab> getTabs() {
        return Collections.singleton(tab);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
