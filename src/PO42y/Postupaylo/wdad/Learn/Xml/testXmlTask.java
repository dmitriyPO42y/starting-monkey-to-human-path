package PO42y.Postupaylo.wdad.Learn.Xml;

import java.util.Calendar;

/**
 * Created by 1 on 09.10.2016.
 */
public class testXmlTask {

    public static void main(String[] args) {
        xmlTask.openDocument("C:\\Users\\1\\IdeaProjects\\starting-monkey-to-human-path\\src\\PO42y\\Postupaylo\\wdad\\Learn\\Xml\\right.xml");

        Calendar date = Calendar.getInstance();

        date.set(2011, 01, 03);
        double total = xmlTask.earningsTotal("ivanov", date);
        xmlTask.changeOfficiantName("Andrey","sidorov","", "ivanov");
        xmlTask.removeDay(date);

        System.out.println(total);
    }
}
