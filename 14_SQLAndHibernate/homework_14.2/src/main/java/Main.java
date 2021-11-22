import java.util.Scanner;
import org.hibernate.PropertyAccessException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

  public static void main(String[] args) {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
    try (Session session = sessionFactory.openSession()) {
      outputStudentsCount(session);
    }
  }

  private static void outputStudentsCount(Session session) {
    Scanner scanner = new Scanner(System.in);
    for (; ; ) {
      int maxIndex = (int) session.createQuery("SELECT MAX(id) FROM Course").list().get(0);
      System.out.println("Enter the Course ID. From 1 to " + maxIndex);
      int courseId = scanner.nextInt();
      if (courseId > 0 && courseId <= maxIndex) {
        try {
        Course course = session.get(Course.class, courseId);
        System.out.println("Курс " + course.getName()
            + ". Количество студентов на курсе: " + course.getStudentsCount());
        } catch (PropertyAccessException ex){
          ex.printStackTrace();
        }
      } else {
        System.out.println("Try to do it again");
      }
    }
  }

}
