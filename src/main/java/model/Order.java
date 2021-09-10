package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(name = "user_id")
    private Long userId;
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private ShoppingCart shoppingCart;
    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;
    @Column(name = "created_date")
    private Date createdDate;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status{
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", userId=" + userId +
                ", shoppingCart=" + shoppingCart +
                ", shoppingCartId=" + shoppingCartId +
                ", createdDate=" + createdDate +
                ", status=" + status +
                '}';
    }

}
