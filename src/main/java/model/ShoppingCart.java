package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shopping_cart", schema = "public")
public class ShoppingCart extends AbstractIdentifiableObject {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "created_date")
    private Date createdDate;

    public ShoppingCart() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Long getUserId() {
//        return userId;
//    }

//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

//    @Override
//    public String toString() {
//        return "ShoppingCart{" +
//                "user=" + user +
//                ", userId=" + userId +
//                ", createdDate=" + createdDate +
//                '}';
//    }

}
