package PO42y.Postupaylo.wdad.data.managers;

import com.oracle.webservices.internal.api.message.PropertySet;
import org.omg.CORBA.SystemException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.rmi.Remote;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by 1 on 10.10.2016.
 */
public class PreferencesManager {
    private static PreferencesManager instance;
    private String path = "C:\\Users\\d.postupaylo\\Documents\\IdeaProjects2015\\IdeaProjects\\starting-monkey-to-human-path\\src\\PO42y\\Postupaylo\\wdad\\resourses\\configuration\\appconfig.xml";
    Document document;

    PreferencesManager() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(path);
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    public static PreferencesManager getInstance() throws IOException, SAXException, ParserConfigurationException {
        if (instance == null)
            instance = new PreferencesManager();
        return instance;
    }

    public void setProperty(String key, String value) throws TransformerException
    {
        String[] tags = key.split("\\.");
        int lastIndex = tags.length - 1;
        document.getElementsByTagName(tags[lastIndex]).item(0).setTextContent(value);
        writeDoc();
    }
    public String getProperty(String key)
    {
        String[] tags = key.split("\\.");
        int lastIndex = tags.length - 1;
        NodeList nodes = document.getElementsByTagName(tags[lastIndex]);
        return nodes.item(0).getTextContent();
    }
    public void setProperties(Properties prop) throws TransformerException
    {
        for (String key: prop.stringPropertyNames())
        {
            setProperty(key, getProperty(key));
        }
    }
    public Properties getProperties() throws XPathExpressionException {
        Properties properties = new Properties();
        String key, value;
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//*[not(*)]";
        try {
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                key = nodeList.item(i).getNodeName();
                value = getProperty(key);
                properties.put(key, value);
            }
        } catch (XPathExpressionException xPathExpressionException) {
            xPathExpressionException.getStackTrace();
        }
        return properties;
    }


    public void addBindedObject(String name, String className) throws TransformerException
    {
        Element bindedObject = document.createElement("bindedobject");
        bindedObject.setAttribute("name", name);
        bindedObject.setAttribute("class", className);
        document.getElementsByTagName("server").item(0).appendChild(bindedObject);
        writeDoc();
    }
    public void removeBindedObject(String name) throws TransformerException
    {
        NodeList nodeList = document.getElementsByTagName("bindedobject");
        for (int i =0; i < nodeList.getLength(); i++)
        {
            String removeName = nodeList.item(i).getAttributes().getNamedItem("name").getNodeValue();
            if (removeName.equals(name))
            {
                nodeList.item(i).getParentNode().removeChild(nodeList.item(i));
            }
        }
        writeDoc();
    }

    private void writeDoc() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));
        transformer.transform(domSource, streamResult);
    }

    @Deprecated
    public boolean isUseCodeBaseOnly() {
        String tegUseCodebaseOnly = "usecodebaseonly";
        if (document.getElementsByTagName(tegUseCodebaseOnly).item(0).getTextContent().equals("yes"))
            return true;
        return false;
    }
    @Deprecated
    public String getRegistryAddress() {
        String tegRegistryAddress = "registryaddress";
        return document.getElementsByTagName(tegRegistryAddress).item(0).getTextContent();
    }
    @Deprecated
    public boolean getCreateRegistry() {
        NodeList nodeList = document.getElementsByTagName("createregistry");
        return nodeList.item(0).getTextContent().equals("yes");
    }
    @Deprecated
    public void setCreateRegistry(boolean createRegistry) throws TransformerException {
        String textContent;
        String tegCreateRegistry = "createregistry";
        if (createRegistry) {
            textContent = "yes";
        } else textContent = "no";
        document.getElementsByTagName(tegCreateRegistry).item(0).setTextContent(textContent);
        writeDoc();
    }
    @Deprecated
    public String getRegistryPort() {
        String tegRegistryPort = "registryport";
        return document.getElementsByTagName(tegRegistryPort).item(0).getTextContent();
    }
    @Deprecated
    public void setRegistryAddress(String newTextContent) throws TransformerException {
        String tegRegistryAddress = "registryaddress";
        document.getElementsByTagName(tegRegistryAddress).item(0).setTextContent(newTextContent);
        writeDoc();
    }
    @Deprecated
    public String getPolicyPath() {
        String tegPolicyPath = "policypath";
        return document.getElementsByTagName(tegPolicyPath).item(0).getTextContent();
    }
    @Deprecated
    public void setRegistryPort(String newRegistryPort) throws TransformerException {
        String tegRegistryPort = "registryport";
        document.getElementsByTagName(tegRegistryPort).item(0).setTextContent(newRegistryPort);
        writeDoc();
    }
    @Deprecated
    public void setPolicyPath(String newPolicyPath) {
        String tegPolicyPath = "policypath";
        document.getElementsByTagName(tegPolicyPath).item(0).setTextContent(newPolicyPath);
    }
    @Deprecated
    public void setUseCodeBaseOnly(boolean newUseCodeBaseOnly) throws TransformerException {
        String tegUseCodebaseOnly = "usecodebaseonly";
        String textContent;
        if (newUseCodeBaseOnly){
            textContent="yes";
        } else textContent="no";
        document.getElementsByTagName(tegUseCodebaseOnly).item(0).setTextContent(textContent);
        writeDoc();
    }
    @Deprecated
    public String getClassProvider(){
        String tegClassProvider="classprovider";
        return document.getElementsByTagName(tegClassProvider).item(0).getTextContent();
    }
    @Deprecated
    public void setClassProvider(String newClassProvider) throws TransformerException {
        String tegClassProvider="classprovider";
        document.getElementsByTagName(tegClassProvider).item(0).setTextContent(newClassProvider);
        writeDoc();
    }
    @Deprecated
    public boolean isCreateRegistry() {
        String tegCreateRegistry = "createregistry";
        if (document.getElementsByTagName(tegCreateRegistry).item(0).getTextContent().equals("yes"))
            return true;
        return false;
    }
}
