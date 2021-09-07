package model;

import java.util.Date;

public class Order {

    private Long id;
    private Long shoppingCardId;
    private Long productId;
    private Date createdDate;
    private Status status;

    enum Status{
        READY,
        FAILED
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingCardId() {
        return shoppingCardId;
    }

    public void setShoppingCardId(Long shoppingCardId) {
        this.shoppingCardId = shoppingCardId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
