package Keys;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SubscriptionKey implements Serializable {

  @Column(name = "student_id")
  private int student;

  @Column(name = "course_id")
  private int course;

}
