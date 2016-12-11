package PO42y.Postupaylo.wdad.Learn.rmi;

import PO42y.Postupaylo.wdad.data.managers.PreferencesManager;
import PO42y.Postupaylo.wdad.utils.Constants;
import jdk.internal.org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 1 on 13.11.2016.
 */
public class Client {
    public static void main(String[] args){
        try {
            PreferencesManager preferencesManager = PreferencesManager.getInstance();

            String getProperty=preferencesManager.getProperty(Constants.REGISTRY_PORT);
            int port = Integer.valueOf(getProperty);
            String address = preferencesManager.getProperty(Constants.REGISTRY_ADDRESS);
            Registry registry = LocateRegistry.getRegistry(address, port);
            XmlDataManager xmlDataManager = ((XmlDataManager)registry.lookup("xmlDataManager"));

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            calendar.set(2011, 01, 03);

            Officiant officiant1 = new Officiant("ivan", "petrov");

            Officiant officiant2 = new Officiant("", "petrov");

            xmlDataManager.changeOfficiantName(officiant1, officiant2);
            System.out.println(xmlDataManager.earningsTotal(officiant2, calendar));
            calendar.set(2011, 02, 03);
            xmlDataManager.removeDay(calendar);
            System.out.println(xmlDataManager.lastOfficiantWorkDate(officiant2).toString());
            calendar.set(2011, 04, 03);
            xmlDataManager.getOrders(calendar);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());

            ex.printStackTrace();
        }
    }
}
