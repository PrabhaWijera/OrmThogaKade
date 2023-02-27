package lk.ijse.thogakade.DTO;


import java.time.LocalDate;

public class OrderDTO {
    private String orderId;
    private LocalDate date;
    private String customerId;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, LocalDate date, String customerId) {
        this.orderId = orderId;
        this.date = date;
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
