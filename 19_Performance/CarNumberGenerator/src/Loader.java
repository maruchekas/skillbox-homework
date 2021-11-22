import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Loader {
    public static final int CORES = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(CORES);

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            executorService.submit(new Thread(() -> {
                int limitRegionCode = finalI * 10;
                try {
                    generateCarNumbers(limitRegionCode);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Общее время выполнения: " + (System.currentTimeMillis() - start) + " ms");

    }

    private static void generateCarNumbers(int limitRegionCode) throws FileNotFoundException {
        String path = "res/numbers" + limitRegionCode + ".txt";
        PrintWriter writer = new PrintWriter(path);
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int regionCode = limitRegionCode - 9; regionCode <= limitRegionCode; regionCode++) {
            StringBuilder carNumberBuilder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            if (regionCode < 10 || number < 100) {
                                appendPartsWithPad(regionCode, carNumberBuilder, number, firstLetter, secondLetter,
                                    thirdLetter);
                            } else appendPartsWithOutPad(regionCode, carNumberBuilder, number, firstLetter,
                                secondLetter, thirdLetter);
                        }
                    }
                }
            }
            writer.write(carNumberBuilder.toString());
        }
        writer.flush();
        writer.close();
        System.out.println("Сгенерированы номера и записаны в файл" + path);
    }

    private static void appendPartsWithPad(int regionCode, StringBuilder carNumberBuilder, int number,
        char firstLetter, char secondLetter, char thirdLetter) {
        carNumberBuilder.append(firstLetter)
            .append(padNumber(number, 3))
            .append(secondLetter)
            .append(thirdLetter)
            .append(padNumber(regionCode, 2))
            .append("\n");
    }

    private static void appendPartsWithOutPad(int regionCode, StringBuilder carNumberBuilder, int number,
        char firstLetter, char secondLetter, char thirdLetter) {
        carNumberBuilder.append(firstLetter)
            .append(number)
            .append(secondLetter)
            .append(thirdLetter)
            .append(regionCode)
            .append("\n");
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }

        return numberStr;
    }
}
