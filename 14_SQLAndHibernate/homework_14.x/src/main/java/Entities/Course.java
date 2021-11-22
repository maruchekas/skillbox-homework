package Entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private int duration;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "enum")
  private CourseType type;

  private String description;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;

  @Column(name = "students_count")
  private int studentsCount;

  private int price;

  @Column(name = "price_per_hour")
  private float pricePerHour;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "subscriptions",
  joinColumns = {@JoinColumn(name = "course_id")},
  inverseJoinColumns = {@JoinColumn(name = "student_id")})
  private List<Student> students;

  @OneToMany(mappedBy = "courseId", fetch = FetchType.LAZY)
  private List<Subscription> subscriptions;

  @Override
  public String toString() {
    return "\nCourse{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", duration=" + duration +
        ", type=" + type +
        ", description='" + description + '\'' +
        ", teacher=" + teacher +
        ", studentsCount=" + studentsCount +
        ", price=" + price +
        ", pricePerHour=" + pricePerHour +
        '}';
  }
}
