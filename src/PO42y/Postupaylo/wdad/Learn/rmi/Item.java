package PO42y.Postupaylo.wdad.Learn.rmi;

import java.io.Serializable;

/**
 * Created by 1 on 13.11.2016.
 */
public class Item  implements Serializable {
    private String name;
    private double cost;

    public Item(String name, double cost)
    {
        this.name = name;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
