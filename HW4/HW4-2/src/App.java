import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Create scanner
        Scanner scanner = new Scanner(System.in);
        String str1, str2;

        // Ask user for string and store it
        System.out.print("Enter a string: ");
        str1 = scanner.nextLine();

        // Ask user for another and to compare and store it
        System.out.print("Enter another string: ");
        str2 = scanner.nextLine();

        // Convert both str1 and str2 to lowercase
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // Find the Longest Common Subsequence between str1 and str2
        lcs(str1, str2);
    }

    private static void lcs(String str1, String str2) {
        // Find the Longest Common Subsequence between str1 and str2
        String lcs = "";
        int[][] lcsTable = new int[str1.length() + 1][str2.length() + 1];

        // Fill lcsTable[][]
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0 || j == 0)
                    lcsTable[i][j] = 0;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    lcsTable[i][j] = lcsTable[i - 1][j - 1] + 1;
                else
                    lcsTable[i][j] = max(lcsTable[i - 1][j], lcsTable[i][j - 1]);
            }
        }

        // Set the index and temp location to index
        int index = lcsTable[str1.length()][str2.length()];
        int temp = index;

        // Create a character array to store the lcs string
        char[] lcsChar = new char[index + 1];

        // Start from the right-most-bottom-most corner and
        // one by one store characters in lcs[]
        int i = str1.length(), j = str2.length();
        while (i > 0 && j > 0) {
            // If current character in str1 and str2 are same,
            // then current character is part of LCS
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                // Put current character in result
                lcsChar[index - 1] = str1.charAt(i - 1);

                // reduce values of i, j and index
                i--;
                j--;
                index--;
            }

            // If not same, then find the larger of two and
            // go in the direction of larger value
            else if (lcsTable[i - 1][j] > lcsTable[i][j - 1])
                i--;
            else
                j--;
        }

        // Print the lcs
        System.out.print("LCS of " + str1 + " and " + str2 + " is ");
        for (int k = 0; k <= temp; k++) {
            System.out.print(lcsChar[k]);
        }
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }
}
