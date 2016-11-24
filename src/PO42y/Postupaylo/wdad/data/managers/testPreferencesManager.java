package PO42y.Postupaylo.wdad.data.managers;

import PO42y.Postupaylo.wdad.utils.Constants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by 1 on 10.10.2016.
 */
public class testPreferencesManager {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        PreferencesManager preferencesManager = PreferencesManager.getInstance();
        //System.out.println(preferencesManager.getProperty(Constants.CREATE_REGYSTRY));
        //preferencesManager.setProperty(Constants.CREATE_REGYSTRY, "no");
        System.out.println(preferencesManager.getProperty(Constants.CREATE_REGYSTRY));
        try {
            Properties properties = preferencesManager.getProperties();
            properties.list(System.out);
        } catch (XPathExpressionException xPathExpressionException) {
            xPathExpressionException.getStackTrace();
        }

        //preferencesManager.addBindedObject("name","class");
        //preferencesManager.removeBindedObject("name");
    }
}