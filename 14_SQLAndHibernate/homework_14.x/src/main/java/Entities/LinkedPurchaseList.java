package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {

  @EmbeddedId()
  private LinkedPurchaseListKey id;

  @Column(name = "student_id", insertable = false, updatable = false)
  private int studentId;

  @Column(name = "course_id", insertable = false, updatable = false)
  private int courseId;



  @Data
  @Embeddable
  public static class LinkedPurchaseListKey implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public LinkedPurchaseListKey() {

    }

    public LinkedPurchaseListKey(int studentId, int courseId) {
      this.studentId = studentId;
      this.courseId = courseId;
    }
  }
}
