public class Main {

    public static void main(String[] args) {
        Basket b1 = new Basket();
        Basket b2 = new Basket();
        Basket b3 = new Basket();

        System.out.println("Всего корзин " + Basket.getCountBaskets());
        System.out.println("Средняя стоимость позиции во всех корзинах: " + Basket.averagePriceItem());
        System.out.println("Средний чек корзины:  " + Basket.averagePriceBasket());

        b1.add("Banana", 18, 4, 0.35);
        b1.add("Lemon", 16, 3, 0.3);
        b1.add("Orange", 17, 6, 0.4);
        b2.add("Banana", 18, 6, 0.35);
        b3.add("Orange", 17, 4, 0.4);
        Basket b4 = new Basket();
        b4.add("Lemon", 16, 1, 0.3);

        System.out.println("Всего корзин " + Basket.getCountBaskets());
        System.out.println("Средняя стоимость позиции во всех корзинах: " + Basket.averagePriceItem());
        System.out.println("Средний чек корзины:  " + Basket.averagePriceBasket());

    }
}
