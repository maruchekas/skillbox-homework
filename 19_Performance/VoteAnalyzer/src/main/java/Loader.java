import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Loader {

  public static void main(String[] args) throws Exception {
    String fileName = "res/data-1572M.xml";
    long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    long mainStart = System.currentTimeMillis();

    System.out.println("Начался процесс парсинга XML файла...");

    SAXParserFactory parserFactory = SAXParserFactory.newInstance();
    SAXParser parser = parserFactory.newSAXParser();
    XMLHandler handler = new XMLHandler();
    parser.parse(new File(fileName), handler);

    usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;

    System.out.format("""
        Использовано памяти при парсинге XML файла: %d
         Создано строк: %d
        """, usage, handler.getAllVoters().size());
    long middle = System.currentTimeMillis();
    System.out.println("\tПарсинг файла занял " + (middle - mainStart) + " ms");

    System.out.println("Процесс записи данных в базу данных начался...");

    DBConnection.executePrepareStatementInsert(handler.getAllVoters());

    System.out.println("\tВремя записи данных в базу заняло: "
        + (System.currentTimeMillis() - middle) + " ms\n");

    System.out.println("Список избирателей, проголосовавших более одного раза: ");
    DBConnection.printDuplicatedVoters();
    DBConnection.closeConnection();
  }
}