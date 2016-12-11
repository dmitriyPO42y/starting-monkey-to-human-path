package PO42y.Postupaylo.wdad.Learn.rmi;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 1 on 13.11.2016.
 */
public class Order  implements Serializable {
    private Officiant officiant;
    private List<Item> items;

    public Order(Officiant officiant, List<Item> items)
    {
        this.officiant = officiant;
        this.items = items;
    }

    public Officiant getOfficiant() {
        return officiant;
    }

    public List<Item> getItems() {
        return items;
    }
}
