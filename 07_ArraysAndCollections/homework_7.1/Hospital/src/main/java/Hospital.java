public class Hospital {

  public static float[] generatePatientsTemperatures(int patientsCount) {

    float[] patientsTemperatures = new float[patientsCount];
    float min = 32.0f;
    float max = 40.0f;
    for (int i = 0; i < patientsCount; i++) {
      patientsTemperatures[i] = (float) Math.random() * (max - min) + min;
    }

    return patientsTemperatures;
  }

  public static String getReport(float[] temperatureData) {
    StringBuilder temperatureLog = new StringBuilder();
    int countHealthy = 0;
    double avgTemperature = 0;
    for (float temp : temperatureData) {
      temperatureLog.append(" ").append(Math.round(temp * 10) / 10.0);
      avgTemperature += temp;
      if (temp > 36.1 && temp < 37) {
        countHealthy++;
      }
    }
    avgTemperature = Math.round((avgTemperature / temperatureData.length) * 100) / 100.0;

    return "Температуры пациентов:" + temperatureLog +
        "\nСредняя температура: " + avgTemperature +
        "\nКоличество здоровых: " + countHealthy;

  }
}
