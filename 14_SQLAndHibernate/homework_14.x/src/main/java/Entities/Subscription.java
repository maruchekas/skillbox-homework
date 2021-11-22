package Entities;

import Keys.SubscriptionKey;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

  @EmbeddedId
  private SubscriptionKey id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id", insertable = false, updatable = false)
  private Student studentId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Course courseId;

  @Column(name = "subscription_date")
  private Date subscriptionDate;

  @Override
  public String toString() {
    return "\nEntities.Subscription{" +
        "id=" + id +
        ", studentId=" + studentId +
        ", courseId=" + courseId +
        ", subscriptionDate=" + subscriptionDate +
        '}';
  }
}
