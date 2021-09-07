package model;

import java.util.Date;

public class CartItem {

    private Long id;
    private Integer quantity;
    private Long shoppingCardId;
    private Long productId;
    private Date createdDate;

    public CartItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

}
