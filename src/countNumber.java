import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

class countNumber {
    public static void main(String[] args) {

        int from = 10000;
        int to = 99999;

        List<Long> primeFiveDigitNumbers = new ArrayList<>();

        int testNumber, primeFinder;
        for (testNumber = from; testNumber <= to; testNumber++) {
            primeFinder = 0;
            for (int i = 2; i < testNumber; i++) {
                if ((testNumber % i) == 0) {
                    primeFinder = 1;
                }
            }
            if (primeFinder == 0 && testNumber >= 2) {
                primeFiveDigitNumbers.add((long) testNumber);
            }
        }

        countData maxPalindrome = palindrome.getMaxPalindrome(primeFiveDigitNumbers);

        System.out.println("    Сомножитель первый:     " + maxPalindrome.getPrimeNumberFirst());
        System.out.println("    Сомножитель второй:     " + maxPalindrome.getPrimeNumberSecond());
        System.out.println("Наибольшее число палиндром: " + maxPalindrome.getPalindrome());
    }
}

class palindrome {

    private static TreeSet<countData> palindromes = new TreeSet<>();

    private static void palindromeSearch(long primeNumberFirst, long primeNumberSecond) {

        long result = primeNumberFirst * primeNumberSecond;
        String strResult = String.valueOf(result);
        if (strResult.equals(new StringBuilder(strResult).reverse().toString())) {
            palindromes.add(new countData(result, primeNumberFirst, primeNumberSecond));
        }
    }

    static countData getMaxPalindrome(List<Long> primeFiveDigitNumbers) {
        for (int i = 0; i < primeFiveDigitNumbers.size(); i++) {
            for (Long primeFiveDigitNumber : primeFiveDigitNumbers) {
                long primeFiveDigitNumberFirst = primeFiveDigitNumbers.get(i);
                long primeFiveDigitNumberSecond = primeFiveDigitNumber;
                palindrome.palindromeSearch(primeFiveDigitNumberFirst, primeFiveDigitNumberSecond);
            }
        }
        return palindromes.last();
    }
}

class countData implements Comparable<countData> {

    private long palindrome;
    private long primeNumberFirst;
    private long primeNumberSecond;

    countData(final long palindrome, final long primeNumberFirst, final long primeNumberSecond) {
        this.palindrome = palindrome;
        this.primeNumberFirst = primeNumberFirst;
        this.primeNumberSecond = primeNumberSecond;
    }

    long getPalindrome() {
        return palindrome;
    }

    long getPrimeNumberFirst() {
        return primeNumberFirst;
    }

    long getPrimeNumberSecond() {
        return primeNumberSecond;
    }

    @Override
    public int compareTo(countData calculatedData) {
        return Long.compare(this.palindrome, calculatedData.getPalindrome());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof countData)) return false;
        countData calculatedData = (countData) o;
        return getPalindrome() == calculatedData.getPalindrome() &&
                getPrimeNumberFirst() == calculatedData.getPrimeNumberFirst() &&
                getPrimeNumberSecond() == calculatedData.getPrimeNumberSecond();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPalindrome(), getPrimeNumberFirst(), getPrimeNumberSecond());
    }

}