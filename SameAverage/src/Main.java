import java.util.*;

public class Main {
    private static boolean checkIfPossibleToSplit(int n, int arraySum) {
        boolean isPossible = false;
        for (int i = 1; i < n; i++) {
            if (i * arraySum % n == 0) isPossible = true;
        }
        return isPossible;
    }
    private static boolean findValueInSet(Set<Integer> integerSet, int value)
    {
        for(var element:integerSet)
        {
            if(element==value) return true;
        }
        return false;
    }

    private static boolean checkIfSplittingFound(List<Integer> arrayInteger) {
        int arraySize = arrayInteger.size();
        int m = arraySize / 2;
        int arraySum = arrayInteger.stream().mapToInt(Integer::intValue).sum();
        if (!checkIfPossibleToSplit(arraySize, arraySum)) return false;
        List<Set<Integer>> sublistSum = new ArrayList<>();
       for (int i=0; i<=m; i++) sublistSum.add(new HashSet<>());
       sublistSum.get(0).add(0);
        for (int number : arrayInteger) {
            for (int i = m; i >= 1; i--) {
                for (int element : sublistSum.get(i - 1)) {
                    sublistSum.get(i).add(element + number);
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            int divisionResult = arraySum*i/arraySize;
            int rest = arraySum*i%arraySize;
            if (rest == 0 && findValueInSet(sublistSum.get(i), divisionResult)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> arrayInteger = new ArrayList<>(); //va fi citita de la tastatura
        Scanner scanner = new Scanner(System.in);
        System.out.println("Read the number of elements in the list:");
        int n = scanner.nextInt();
        System.out.println("Read the elements in the list:");
        for(int i=0; i<n; i++)
        {
            int x = scanner.nextInt();
            arrayInteger.add(x);
        }
        System.out.print("The result is : " + checkIfSplittingFound(arrayInteger));
    }
}