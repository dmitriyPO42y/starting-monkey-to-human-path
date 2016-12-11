package PO42y.Postupaylo.wdad.Learn.rmi;

import PO42y.Postupaylo.wdad.Learn.Xml.xmlTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.rmi.Remote;

import java.rmi.RemoteException;

/**
 * Created by 1 on 13.11.2016.
 */
public class XmlDataManagerImplementation implements XmlDataManager, Serializable {
    /*возвращающий суммарную выручку заданного официанта в заданный день*/
    public XmlDataManagerImplementation()
    {
        xmlTask.openDocument("C:\\Users\\d.postupaylo\\Documents\\IdeaProjects2015\\IdeaProjects\\starting-monkey-to-human-path\\src\\PO42y\\Postupaylo\\wdad\\Learn\\Xml\\right.xml");
    }

    @Override
    public double earningsTotal(Officiant officiant, Calendar date)
    {
        return xmlTask.earningsTotal(officiant.getSecondName(), date);
    }
    @Override
    public void removeDay(Calendar date)
    {
        xmlTask.removeDay(date);
    }
    @Override
    public  void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant)
    {
        xmlTask.changeOfficiantName(oldOfficiant.getFirstName(), oldOfficiant.getSecondName(), newOfficiant.getFirstName(), newOfficiant.getSecondName());
    }
    @Override
    public List<Order> getOrders(Calendar date)
    {
        List<Order> listOrder = xmlTask.gerOrders(date);
        return listOrder;
    }
    @Override
    public Calendar lastOfficiantWorkDate(Officiant officiant)
    {
        return xmlTask.lastOfficiantWorkDate(officiant.getFirstName(), officiant.getSecondName());
    }
}

/*
public int earningsTotal(Officiant officiant, Date date) - возвращающий
суммарную выручку заданного официанта в заданный день

public void removeDay(Date date) - удаляет информацию по заданному
дню.

public void changeOfficiantName(Officiant oldOfficiant, Officiant
newOfficiant) – изменяющий имя и фамилию официанта во всем
документе

public List<Order> getOrders(Date date) – возвращающий список заказов
в заданный день

public Date lastOfficiantWorkDate(Officiant officiant) – возвращающий
последнюю дату работы заданного официанта или выбрасывающий
исключение NoSuchOfficiantException*/
