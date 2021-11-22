public class Main {

  public static void main(String[] args) {
    Client individualBusinessman = new IndividualBusinessman();
    individualBusinessman.put(500);
    individualBusinessman.take(600);
    individualBusinessman.put(1900);
    individualBusinessman.take(100);
    System.out.println(individualBusinessman.showInfo());

    Client physicalPerson = new PhysicalPerson();
    physicalPerson.put(500);
    physicalPerson.take(100);
    physicalPerson.take(500);
    System.out.println(physicalPerson.showInfo());
  }
}
