package top.colayy.test;

public class Test01 {
    public static void main(String[] args) {
        String a = "1asd23";
        boolean isNum = a.matches("[0-9]+");
        System.out.println(isNum);
    }
}
