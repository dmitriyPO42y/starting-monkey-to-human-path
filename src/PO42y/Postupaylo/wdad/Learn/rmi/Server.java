package PO42y.Postupaylo.wdad.Learn.rmi;

import PO42y.Postupaylo.wdad.data.managers.PreferencesManager;
import PO42y.Postupaylo.wdad.utils.Constants;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Created by 1 on 13.11.2016.
 */
public class Server {
    public static void main(String[] args) {
        try {
            PreferencesManager preferencesManager = PreferencesManager.getInstance();
            String getProperty=preferencesManager.getProperty(Constants.REGISTRY_PORT);
            int port = Integer.valueOf(getProperty);
            XmlDataManager xmlDataManager = new XmlDataManagerImplementation();
            Registry registry;
            if (preferencesManager.getProperty(Constants.CREATE_REGISTRY).equals("yes")) {
                registry = LocateRegistry.getRegistry(port);
            } else {
                registry = LocateRegistry.createRegistry(port);
            }
            java.rmi.server.UnicastRemoteObject.exportObject(xmlDataManager, 20010);
            registry.bind("xmlDataManager", xmlDataManager);
            preferencesManager.addBindedObject("xmlDataManager", xmlDataManager.getClass().getName());
            System.out.println("Cервер запущен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
