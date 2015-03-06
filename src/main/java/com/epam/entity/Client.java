package com.epam.entity;

import java.math.BigDecimal;
import java.util.List;

public class Client extends User {
    /**
     * client's orders
     */
    private List<Order> orders;
    /**
     * client has bonus, which will increase per trip
     */
    private BigDecimal bonus;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (bonus != null ? !bonus.equals(client.bonus) : client.bonus != null) return false;
        if (orders != null ? !orders.equals(client.orders) : client.orders != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (bonus != null ? bonus.hashCode() : 0);
        return result;
    }
}
