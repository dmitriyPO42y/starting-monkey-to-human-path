package PO42y.Postupaylo.wdad.Learn.rmi;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 13.11.2016.
 */
public interface XmlDataManager extends Remote {
    double earningsTotal(Officiant officiant, Calendar date) throws RemoteException;
    void removeDay(Calendar date) throws IOException, RemoteException;
    void changeOfficiantName(Officiant oldOfficiant, Officiant newOfficiant)  throws IOException, RemoteException;
    List<Order> getOrders(Calendar date) throws RemoteException;
    Calendar lastOfficiantWorkDate(Officiant officiant)  throws RemoteException;
}

/*public int earningsTotal(Officiant officiant, Date date) - возвращающий
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
