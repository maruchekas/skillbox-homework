package Entities;

import Keys.PurchaseKey;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "purchaselist")
public class Purchase {

  @EmbeddedId
  private PurchaseKey id;

  @Column(name = "student_name", insertable = false, updatable = false)
  private String studentName;

  @Column(name = "course_name", insertable = false, updatable = false)
  private String courseName;

  private int price;

  @Column(name = "subscription_date")
  private Date subscription_Date;

}
