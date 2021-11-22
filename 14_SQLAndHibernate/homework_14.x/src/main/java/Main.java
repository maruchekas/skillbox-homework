import Entities.Course;
import Entities.LinkedPurchaseList;
import Entities.LinkedPurchaseList.LinkedPurchaseListKey;
import Entities.Purchase;
import Entities.Student;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

  public static void main(String[] args) {
    try (Session session = new Configuration().configure("hibernate.cfg.xml")
        .buildSessionFactory().getCurrentSession()) {
      Transaction transaction = session.beginTransaction();

      String hql = "from " + Purchase.class.getSimpleName();
      List<Purchase> purchases = session.createQuery(hql).getResultList();

      for (Purchase purchase : purchases
      ) {
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Student> studentCriteria = builder.createQuery(Student.class);
        Root<Student> studentRoot = studentCriteria.from(Student.class);
        studentCriteria.select(studentRoot)
            .where(builder.equal(studentRoot.get("name"), purchase.getStudentName()));
        Student student = session.createQuery(studentCriteria).getSingleResult();

        CriteriaQuery<Course> courseCriteria = builder.createQuery(Course.class);
        Root<Course> courseRoot = courseCriteria.from(Course.class);
        courseCriteria.select(courseRoot)
            .where(builder.equal(courseRoot.get("name"), purchase.getCourseName()));
        Course course = session.createQuery(courseCriteria).getSingleResult();

        LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
        linkedPurchaseList
            .setId(new LinkedPurchaseListKey(student.getId(), course.getId()));
        linkedPurchaseList.setStudentId(student.getId());
        linkedPurchaseList.setCourseId(course.getId());
        session.save(linkedPurchaseList);

      }
      transaction.commit();
    }
  }

}
