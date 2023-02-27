package lk.ijse.thogakade.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class Order {
    @Id
    private String orderId;
    @Column
    private LocalDate date;
    @Column
    private String customerId;

    public Order() {
    }

    public Order(String orderId, LocalDate date, String customerId) {
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
