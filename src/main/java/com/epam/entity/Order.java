package com.epam.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order extends BaseEntity {
    private Client client;
    private Driver driver;
    private Dispatcher dispatcher;
    private CarClass carClass;
    private String pickupLocation;
    private String dropOffLocation;
    private Timestamp receivedTime;
    private BigDecimal cost;

    public enum OrderStatus {
        NOT_SERVED, ACCEPTED, TAKEN_UP, CLIENT_EXPECTING, IN_PROCESS, COMPLETED;

        public Integer getId() {
            switch (this) {
                case NOT_SERVED:
                    return 1;
                case ACCEPTED:
                    return 2;
                case TAKEN_UP:
                    return 3;
                case CLIENT_EXPECTING:
                    return 4;
                case IN_PROCESS:
                    return 5;
                case COMPLETED:
                    return 6;
                default:
                    return null;
            }
        }
    }

    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = OrderStatus.valueOf(status);
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Timestamp getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Timestamp receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (carClass != order.carClass) return false;
        if (client != null ? !client.equals(order.client) : order.client != null) return false;
        if (cost != null ? !cost.equals(order.cost) : order.cost != null) return false;
        if (dispatcher != null ? !dispatcher.equals(order.dispatcher) : order.dispatcher != null) return false;
        if (driver != null ? !driver.equals(order.driver) : order.driver != null) return false;
        if (dropOffLocation != null ? !dropOffLocation.equals(order.dropOffLocation) : order.dropOffLocation != null)
            return false;
        if (pickupLocation != null ? !pickupLocation.equals(order.pickupLocation) : order.pickupLocation != null)
            return false;
        if (receivedTime != null ? !receivedTime.equals(order.receivedTime) : order.receivedTime != null) return false;
        if (status != order.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (dispatcher != null ? dispatcher.hashCode() : 0);
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        result = 31 * result + (pickupLocation != null ? pickupLocation.hashCode() : 0);
        result = 31 * result + (dropOffLocation != null ? dropOffLocation.hashCode() : 0);
        result = 31 * result + (receivedTime != null ? receivedTime.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
