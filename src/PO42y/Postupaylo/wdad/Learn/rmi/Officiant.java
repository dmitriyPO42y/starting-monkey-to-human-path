package PO42y.Postupaylo.wdad.Learn.rmi;

import java.io.Serializable;

/**
 * Created by 1 on 13.11.2016.
 */
public class Officiant  implements Serializable {
    private String firstName;
    private String secondName;

    public Officiant(String firstName, String secondName)
    {
        this.secondName = secondName;
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

}
