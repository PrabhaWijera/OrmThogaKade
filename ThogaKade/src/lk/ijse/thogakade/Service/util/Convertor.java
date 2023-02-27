package lk.ijse.thogakade.Service.util;

import lk.ijse.thogakade.DTO.CustomerDTO;
import lk.ijse.thogakade.DTO.ItemDTO;
import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.entity.Item;

public class Convertor {

    public CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());
    }
    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getSalary());
    }


    public ItemDTO fromItem(Item item){
        return new ItemDTO(item.getCode(),
                item.getDescription(),
                item.getQtyOnHand(),
                (int) item.getUnitPrice());
    }

    public Item toItem(ItemDTO itemDTO){
        return new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());
    }



}
