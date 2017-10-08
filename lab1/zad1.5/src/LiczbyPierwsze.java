public class LiczbyPierwsze {
    public static void main(String[] args) {
        System.out.print("Podaj liczbę: ");
        String inputNumber = JIn.getString();
        PrimeNumbers input = new PrimeNumbers(inputNumber);
    }
}
