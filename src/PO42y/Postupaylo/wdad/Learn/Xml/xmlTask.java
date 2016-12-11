package PO42y.Postupaylo.wdad.Learn.Xml;


        import PO42y.Postupaylo.wdad.Learn.rmi.Item;
        import PO42y.Postupaylo.wdad.Learn.rmi.Officiant;
        import PO42y.Postupaylo.wdad.Learn.rmi.Order;
        import com.sun.javafx.scene.layout.region.Margins;
        import com.sun.org.apache.xpath.internal.SourceTree;
        import org.w3c.dom.*;

        import javax.xml.parsers.DocumentBuilder;
        import javax.xml.parsers.DocumentBuilderFactory;
        import javax.xml.transform.Transformer;
        import javax.xml.transform.TransformerFactory;
        import javax.xml.transform.dom.DOMSource;
        import javax.xml.transform.stream.StreamResult;
        import java.io.File;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;

/**
 * Created by 1 on 04.10.2016.
 */
public class xmlTask {
    private static Document xmlDocument;
    private static String fileName;
    //возвращающий суммарную
    //выручку заданного официанта в заданный день

    public xmlTask()
    {
        this.openDocument("C:\\Users\\d.postupaylo\\Documents\\IdeaProjects2015\\IdeaProjects\\starting-monkey-to-human-path\\src\\PO42y\\Postupaylo\\wdad\\Learn\\Xml\\right.xml");
    }

    public static double earningsTotal(String officiantSecondName,Calendar calendar)
    {
        double totalCost = 0.0;
        NodeList nodeList = xmlDocument.getElementsByTagName("date");

        for (int i =0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            Element element = (Element) node;

            Calendar nodeDate = Calendar.getInstance();

            int day = Integer.parseInt(element.getAttributes().getNamedItem("day").getNodeValue());
            int month = Integer.parseInt(element.getAttributes().getNamedItem("month").getNodeValue());
            int year = Integer.parseInt(element.getAttributes().getNamedItem("year").getNodeValue());

            nodeDate.set(year,month,day);
            if ((calendar.getTime().getDay() == nodeDate.getTime().getDay() && calendar.getTime().getMonth() == nodeDate.getTime().getMonth() && calendar.getTime().getYear() == nodeDate.getTime().getYear())){

                NodeList orders = element.getElementsByTagName("order");

                for (int key = 0; key < orders.getLength(); key++ ) {

                    Node _order = orders.item(key);
                    Element order = (Element) _order;


                    String _officiantSecondName = order.getElementsByTagName("officiant").item(0).getAttributes().getNamedItem("secondname").getNodeValue();
                    if (_officiantSecondName.equals(officiantSecondName)) {
                        double costOfItem = 0.0;
                        for (int j = 0; j < order.getElementsByTagName("item").getLength(); j++) {
                            costOfItem+= Double.parseDouble(order.getElementsByTagName("item").item(j).getAttributes().getNamedItem("cost").getNodeValue());
                            totalCost += costOfItem;
                        }

                        if (order.getElementsByTagName("totalcost").getLength() <= 0)
                        {
                            Element totalcost = xmlDocument.createElement("totalcost");
                            totalcost.setTextContent(Double.toString(costOfItem));
                            order.appendChild(totalcost);

                            rewriteDocument(fileName);
                        };

                    }
                }
            }
        }
        return totalCost;
    }

    // удаляет информацию по заданному дню.
    public static void removeDay(Calendar calendar)
    {
        NodeList nodeList = xmlDocument.getElementsByTagName("date");

        for (int i =0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            Element element = (Element) node;

            Calendar nodeDate = Calendar.getInstance();

            int day = Integer.parseInt(element.getAttributes().getNamedItem("day").getNodeValue());
            int month = Integer.parseInt(element.getAttributes().getNamedItem("month").getNodeValue());
            int year = Integer.parseInt(element.getAttributes().getNamedItem("year").getNodeValue());

            nodeDate.set(year,month,day);
            if (calendar.getTime().getDay() == nodeDate.getTime().getDay() && calendar.getTime().getMonth() == nodeDate.getTime().getMonth() && calendar.getTime().getYear() == nodeDate.getTime().getYear())
            {
                element.getParentNode().removeChild(element);
            }
        }

        rewriteDocument(fileName);
    }

    //изменяющий имя и фамилию
    //официанта во всех днях и записывающий результат в
    //этот же xml-файл
    public static void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName)
    {
        NodeList nodeList = xmlDocument.getElementsByTagName("order");

        for (int i =0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            Element element = (Element) node;

            String _oldSecondName =  element.getElementsByTagName("officiant").item(0).getAttributes().getNamedItem("secondname").getNodeValue();
            String _oldFirstName =  element.getElementsByTagName("officiant").item(0).getAttributes().getNamedItem("firstname").getNodeValue();

            if ( _oldFirstName.equals(oldFirstName) &&  _oldSecondName.equals(oldSecondName)){

                for (int j = 0; j < element.getElementsByTagName("item").getLength(); j++) {
                    element.getElementsByTagName("officiant").item(0).getAttributes().getNamedItem("firstname").setNodeValue(newFirstName);
                    element.getElementsByTagName("officiant").item(0).getAttributes().getNamedItem("secondname").setNodeValue(newSecondName);
                }
            }
        }

        rewriteDocument(fileName);
    }

    public static List<Order> gerOrders(Calendar calendar)
    {
        List<Order> listOrder = new ArrayList<Order>();
        NodeList nodeList = xmlDocument.getElementsByTagName("order");

        for (int i =0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);

            Calendar nodeDate = Calendar.getInstance();

            int day = Integer.parseInt(node.getAttributes().getNamedItem("day").getNodeValue());
            int month = Integer.parseInt(node.getAttributes().getNamedItem("month").getNodeValue());
            int year = Integer.parseInt(node.getAttributes().getNamedItem("year").getNodeValue());

            nodeDate.set(year,month,day);
            if (calendar.getTime().getDay() == nodeDate.getTime().getDay() && calendar.getTime().getMonth() == nodeDate.getTime().getMonth() && calendar.getTime().getYear() == nodeDate.getTime().getYear())
            {
                Officiant officiant;
                List<Item> items = new ArrayList<Item>();
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++)
                {
                    if (childNodes.item(j).getNodeName().equals("officiant"))
                    {
                        String firstName =  childNodes.item(j).getAttributes().getNamedItem("firstname").getNodeValue();
                        String secondName =  childNodes.item(j).getAttributes().getNamedItem("secondname").getNodeValue();
                        officiant = new Officiant(firstName, secondName);
                        Order order = new Order(officiant, items);
                        listOrder.add(order);
                    }
                    if (childNodes.item(j).getNodeName().equals("item"))
                    {
                        String name =childNodes.item(j).getAttributes().getNamedItem("name").getNodeValue();
                        double cost = Double.parseDouble(childNodes.item(j).getAttributes().getNamedItem("cost").getNodeValue());
                        Item item = new Item(name, cost);
                        items.add(item);
                    }
                }
            }

        }
        return listOrder;
    }

    public static Calendar lastOfficiantWorkDate(String firstName, String secondName) {
        Calendar calendar = Calendar.getInstance();
        NodeList officiantList = xmlDocument.getElementsByTagName("officiant");
        NamedNodeMap officiantAttributes;

        for (int i = officiantList.getLength() - 1; i >= 0; i--) {
            officiantAttributes = officiantList.item(i).getAttributes();
            if ((officiantAttributes.getNamedItem("firstname").getNodeValue().equals(firstName)) &&
                    (officiantAttributes.getNamedItem("secondname").getNodeValue().equals(secondName))) {
                Node date = officiantList.item(i).getParentNode().getParentNode();
                NamedNodeMap dateAttributes = date.getAttributes();
                calendar.set(
                        Integer.parseInt(dateAttributes.getNamedItem("year").getTextContent()),
                        Integer.parseInt(dateAttributes.getNamedItem("month").getTextContent()),
                        Integer.parseInt(dateAttributes.getNamedItem("day").getTextContent())
                );
                return calendar;
            }
        }
        return null;
    }

    public static void openDocument(String name)
    {
        try
        {
            fileName = name;
            File xmlFile = new File(name);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            xmlDocument = documentBuilder.parse(xmlFile);
            xmlDocument.getDocumentElement().normalize();
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();

        }
    }

    public static void rewriteDocument(String name)
    {
        try {
            File xmlFile = new File(name);
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(new DOMSource(xmlDocument), new StreamResult(xmlFile));
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();

        }
    }
}


/*
    Интерфейс класса (публичные методы):
public int earningsTotal(String officiantSecondName,Calendar calendar) -
public void removeDay(Calendar calendar) -
public void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName) –
    Если при парсинге документа, тег <totalcost>
    отсутствует или имеет не корректное значение –
    необходимо его добавить или исправить значение
    соответственно.*/
