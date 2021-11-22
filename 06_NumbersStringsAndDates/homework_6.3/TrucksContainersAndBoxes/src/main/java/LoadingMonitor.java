public class LoadingMonitor {

  private final String boxes;

  LoadingMonitor(String boxes) {
    this.boxes = boxes;
  }

  public void porter() {
    int countOfBoxes = Integer.parseInt(boxes);
    int currentTruck = 1;
    int currentContainer = 1;
    int currentBox = 0;

    for (int i = 1; i <= countOfBoxes; i++) {
      if (currentBox % 324 == 0) {
        System.out.println("Грузовик: " + currentTruck);
        currentTruck++;
      }
      if (currentBox % 27 == 0) {
        System.out.println("\t" + "Контейнер: " + currentContainer);
        currentContainer++;
      }
      System.out.println("\t\t" + "Ящик: " + i);
      currentBox++;
    }
  }

  @Override
  public String toString() {
    int countOfTrucks = (int) Math.ceil(Double.parseDouble(boxes) / (324));
    int countOfContainers = (int) Math.ceil(Double.parseDouble(boxes) / 27);
    return "Необходимо:" + System.lineSeparator() +
        "грузовиков - " + countOfTrucks + " шт." + System.lineSeparator() +
        "контейнеров - " + countOfContainers + " шт.";
  }
}
