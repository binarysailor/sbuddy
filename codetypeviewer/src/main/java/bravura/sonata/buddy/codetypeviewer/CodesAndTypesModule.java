package bravura.sonata.buddy.codetypeviewer;

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
public class CodesAndTypesModule implements SonataBuddyModule, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private SonataBuddyTab tab = new SonataBuddyTab() {
        @Override
        public String getLabel() {
            return "Codes & Types";
        }

        @Override
        public JPanel createUI() {
            return (CodesAndTypesTab)applicationContext.getBean("ui.tabs.codesAndTypes");
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
