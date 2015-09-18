package bravura.sonata.buddy.config.xmlconfig;

import bravura.sonata.buddy.config.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Logger;

/**
 * Created by tszymanski on 03/07/2015.
 */
public class XmlPreferences {
    public static boolean load() {
        try {
            JAXBContext jc = JAXBContext.newInstance(
                    bravura.sonata.buddy.config.xmlconfig.Preferences.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            bravura.sonata.buddy.config.xmlconfig.Preferences xmlPreferences =
                    (bravura.sonata.buddy.config.xmlconfig.Preferences)
                            unmarshaller.unmarshal(new File("config/preferences.xml"));

            Preferences.set().searchDelay(xmlPreferences.getSearchDelay());

            return true;
        } catch (JAXBException e) {
            Logger.getLogger(XmlPreferences.class.getName()).severe("Could not load preferences");
            return false;
        }
    }
}
