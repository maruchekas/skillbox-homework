public class ReverseArray {

    public static String[] reverse (String[] strings){
        String keeper;
        for (int i = 0; i < strings.length / 2; i++) {
            keeper = strings[(strings.length - 1) - i];
            strings[(strings.length - 1) - i] = strings[i];
            strings[i] = keeper;
        }
        return strings;
    }
}
