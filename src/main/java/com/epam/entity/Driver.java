package com.epam.entity;

import java.util.List;

public class Driver extends Employee {
    /**
     * order which did not complete currently for a given client. Basically Driver can have many orders, but currently
     * only one. Order is considered as 'currently' when its status equals to 'not completed'
     */
    private Order currentOrder;
    private List<Order> orders;
    private boolean available;
    private String currentLocation;
    private String govNumber;
    private String carModel;
    private CarClass carClass;

    public String getGovNumber() {
        return govNumber;
    }

    public void setGovNumber(String govNumber) {
        this.govNumber = govNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = CarClass.valueOf(carClass);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        if (!super.equals(o)) return false;

        Driver driver = (Driver) o;

        if (available != driver.available) return false;
        if (carClass != driver.carClass) return false;
        if (carModel != null ? !carModel.equals(driver.carModel) : driver.carModel != null) return false;
        if (currentLocation != null ? !currentLocation.equals(driver.currentLocation) : driver.currentLocation != null)
            return false;
        if (currentOrder != null ? !currentOrder.equals(driver.currentOrder) : driver.currentOrder != null)
            return false;
        if (govNumber != null ? !govNumber.equals(driver.govNumber) : driver.govNumber != null) return false;
        if (orders != null ? !orders.equals(driver.orders) : driver.orders != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (currentOrder != null ? currentOrder.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (currentLocation != null ? currentLocation.hashCode() : 0);
        result = 31 * result + (govNumber != null ? govNumber.hashCode() : 0);
        result = 31 * result + (carModel != null ? carModel.hashCode() : 0);
        result = 31 * result + (carClass != null ? carClass.hashCode() : 0);
        return result;
    }
}
