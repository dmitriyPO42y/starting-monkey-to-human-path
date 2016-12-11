package PO42y.Postupaylo.wdad.Learn.Xml;

import java.util.Calendar;

/**
 * Created by 1 on 09.10.2016.
 */
public class testXmlTask {

    public static void main(String[] args) {
        XmlTask.openDocument("C:\\Users\\d.postupaylo\\Documents\\IdeaProjects2015\\IdeaProjects\\starting-monkey-to-human-path\\src\\PO42y\\Postupaylo\\wdad\\Learn\\Xml\\right.xml");

        Calendar date = Calendar.getInstance();

        date.set(2011, 01, 03);
        double total = XmlTask.earningsTotal("ivanov", date);
        XmlTask.changeOfficiantName("Andrey","sidorov","", "ivanov");
        XmlTask.removeDay(date);

        System.out.println(total);
    }
}
