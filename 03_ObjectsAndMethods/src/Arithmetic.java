public class Arithmetic {
    private int a = 0;
    private int b = 0;

    Arithmetic(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void getSumOfNums() {                         // сумма чисел
        System.out.println("Сумма чисел: " + (a + b));
        ;
    }

    public void getDiffOfNums() {                        //разность чисел
        System.out.println("Разность чисел: " + (a - b));
        ;
    }

    public void getMultiplOfNums() {                    // произведение чисел
        System.out.println("Произведение чисел: " + (a * b));
        ;
    }

    public void getDivOfNums() {                      // деление чисел
        if (b == 0) {
            System.out.println("На ноль делить нельзя!");
            return;

        }
        System.out.println("Частное чисел: " + ((double) a / b));
    }

    public void getAgvOfNums() {                       // среднее значение чисел
        System.out.println("Среднее значение чисел: " + ((double) (a + b) / 2));
    }

    public void getMaxOfNums() {                      // максимальное из двух
        int max;
        if (a == b) {
            System.out.println("Заданные числа равны.");
            return;
        } else if (a > b) {
            max = a;
        } else max = b;
        System.out.println("Максимальное число: " + max);
    }

    public void getMinOfNums() {                    // минимальное из двух
        int min = 0;
        if (a == b) {
            System.out.println("Заданные числа равны.");
            return;
        } else if (a < b) {
            min = a;
        } min = b;
        System.out.println("Минимальное число: " + min);
    }

}
