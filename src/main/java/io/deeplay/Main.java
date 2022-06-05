package io.deeplay;

public class Main {
    public static void main(String[] args) {
        String fieldLayout = "STWSWTPPTPTTPWPP";
        String race = "Human";

        try {
            System.out.println(Solution.getResult(fieldLayout, race));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
