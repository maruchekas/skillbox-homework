package Keys;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PurchaseKey implements Serializable {

  @Column(name = "student_name")
  private String studentName;

  @Column(name = "course_name")
  private String courseName;

  public PurchaseKey() {
  }

  public PurchaseKey(String studentName, String courseName) {
    this.studentName = studentName;
    this.courseName = courseName;
  }
}
