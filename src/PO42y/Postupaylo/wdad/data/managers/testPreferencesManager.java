package PO42y.Postupaylo.wdad.data.managers;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
/**
 * Created by 1 on 10.10.2016.
 */
public class testPreferencesManager {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        PreferencesManager preferencesManager=PreferencesManager.getInstance();
        System.out.println("ДО ИЗМЕНЕНИЙ");
        if (preferencesManager.isCreateRegistry()) {
            System.out.println("Create Registry: yes");
        } else System.out.println("Create Registry: no");
        System.out.println("Registry Address: "+preferencesManager.getRegistryAddress());
        System.out.println("Registry port:"+preferencesManager.getRegistryPort());
        System.out.println("Policy Path:"+preferencesManager.getPolicyPath());
        if (preferencesManager.isUseCodeBaseOnly()){
            System.out.println("Use Code Base Only: yes");
        }else System.out.println("Use Code Base Only: no");
        System.out.println("Class Provider: "+preferencesManager.getClassProvider());
        System.out.println("ПОСЛЕ ИЗМЕННЕНИЙ");
        preferencesManager.setCreateRegistry(false);
        if (preferencesManager.isCreateRegistry()) {
            System.out.println("Create Registry: yes");
        } else System.out.println("Create Registry: no");
        preferencesManager.setRegistryAddress("126.128.0.127");
        System.out.println("Registry Address: "+preferencesManager.getRegistryAddress());
        preferencesManager.setRegistryPort("2005");
        System.out.println("Registry port:"+preferencesManager.getRegistryPort());
        preferencesManager.setPolicyPath("policy");
        System.out.println("Policy Path:"+preferencesManager.getPolicyPath());
        preferencesManager.setUseCodeBaseOnly(false);
        if (preferencesManager.isUseCodeBaseOnly()){
            System.out.println("Use Code Base Only: yes");
        }else System.out.println("Use Code Base Only: no");
        preferencesManager.setClassProvider("http://newProvider.com");
        System.out.println("Class Provider: "+preferencesManager.getClassProvider());
    }
}