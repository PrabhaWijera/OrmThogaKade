package lk.ijse.thogakade.DTO;

/*
    @author DanujaV
    @created 11/15/22 - 11:46 PM   
*/

import java.util.ArrayList;

public class PlaceOrder {
    private String customerId;
    private String orderId;
    private ArrayList<CartDetail> orderDetails = new ArrayList<>();

    public PlaceOrder() {
    }

    public PlaceOrder(String customerId, String orderId, ArrayList<CartDetail> orderDetails) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ArrayList<CartDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<CartDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "customerId='" + customerId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
