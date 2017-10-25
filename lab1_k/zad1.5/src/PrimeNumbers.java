import java.util.Arrays;


public class PrimeNumbers {
    int biggestNumber;
    boolean primeNumbers[] = new boolean[biggestNumber];

    /**
     *
     * @param _biggestNumber - liczba podawana przy tworzeniu obiektu,
     *                       konstruktor pełni rolę sita Erastotenesa
     */
    PrimeNumbers(String _biggestNumber) {
        biggestNumber = Integer.parseInt(_biggestNumber);
        Arrays.fill(primeNumbers, true);
        System.out.println(Math.sqrt(biggestNumber));

        int j;
        for (int i = 2; i < Math.sqrt(biggestNumber); i++) {
            if (primeNumbers[i]) {
                j = i+i;
                while (j <= biggestNumber-1) {
                    primeNumbers[j] = false;
                    j += i;
                }
            }
        }
    }

    /**
     * showPrimeNumbers() listuje liczby pierwsze mniejsze od podanej przy tworzeniu obiektu
     */
    public void showPrimeNumbers() {
        for (int i = 0; i < biggestNumber; i++) {
            if(primeNumbers[i])
                System.out.println(i);
        }
    }
}
