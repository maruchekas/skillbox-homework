public class Printer {
    private String queue = "";
    private int docsCount = 0;
    private int totalPagesCount = 0;
    private int allTimePagesCount = 0;
    private int allTimeDocsCount = 0;


    public void append(String text) {
        append("", text, 1);
    }

    public void append(String name, String text) {
        append(name, text, 1);
    }

    public void append(String name, String text, int count) {
        queue = queue + "\n" + name + "\n" + text + "\n" + count + " стр.";
        docsCount++;
        allTimeDocsCount++;
        totalPagesCount = totalPagesCount + count;
        allTimePagesCount = allTimePagesCount + totalPagesCount;
    }

    public void print() {
        System.out.println("Отправляю документы на печать");
        if (queue.isEmpty()) {
            System.out.println("Список документов пока пуст");
        } else {
            System.out.println(queue);
        }
        queue = "";
        docsCount = 0;
        totalPagesCount = 0;
    }

    public void clear() {
        queue = "";
    }

    public int getPagesCount() {
        return totalPagesCount;
    }

    public int getDocsCount() {
        return docsCount;
    }

    public String getAllTimeDocsPagesCount() {
        return "За все время напечатано документов: " + allTimeDocsCount + "\n" +
                "За все время напечатано страниц: " + allTimePagesCount;
    }
}
