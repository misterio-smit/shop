package matrox;


import matrox.domain.Order;
import matrox.domain.Thing;
import matrox.ejb.OrdersManagerBean;
import matrox.ejb.ThingsManagerBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class OrderBean implements Serializable {
    private Order order;
    private String name;
    private int quantity;


    @EJB
    private OrdersManagerBean ordersManagerBean;

    @EJB
    private ThingsManagerBean thingsManagerBean;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void createOrder() {
        if(order == null) {
            order = ordersManagerBean.createOrder();
        }
    }

    public void createThing() {
        thingsManagerBean.createThing(name, quantity);
    }

    public List<Thing> getThings() {
        return thingsManagerBean.getThings();
    }

    public void addThing(Thing thing) {
        if (order == null) {
            return;
        }
        ordersManagerBean.addToOrder(thing.getId(), order.getId(), 1);
    }

    public List<Thing> getThingInOrder() {
        if (order == null) {
            return Collections.emptyList();

        }
        return ordersManagerBean.getThincsInOrder(order.getId());


    }
}
