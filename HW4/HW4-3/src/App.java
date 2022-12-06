import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Node of tree
class Node {
    // Frequency of character
    int value;
    // Character
    char character;
    // Left and right children
    Node left, right;
}

// Class for comparing the value of nodes
class Compare implements Comparator<Node> {
    public int compare(Node x, Node y) {
        return x.value - y.value;
    }
}

class App {

    // Hashmap for storing the characters
    static {
        map = new HashMap<>();
    }
    private static Map<Character, Integer> map;

    // Print the character and huffman code assigned to it
    public static void printCode(Node root, String character) {
        // NOTE: Left is 0 and right is 1
        // Make sure left and right has no children before printing
        if (root.left == null && root.right == null && root.character != '-') {
            System.out.println(root.character + "  :  " + character);
            map.put(root.character, character.length());
            return;
        }
        // Traverse other nodes because everyone loves recursion/dynamic programming
        // :sigh:
        printCode(root.left, character + "0");
        printCode(root.right, character + "1");
    }

    public static void main(String[] args) {
        // Create scanner
        Scanner scanner = new Scanner(System.in);
        // Ask user for input
        System.out.print("Enter a string: ");
        String str = scanner.nextLine();

        // Make str lowercase
        str = str.toLowerCase();

        // Array of frequenciesuencies
        int[] frequencies = new int[str.length()];

        // Convert string to char array
        char[] characters = str.toCharArray();

        // Loop through all characters in string
        for (int i = 0; i < str.length(); i++) {
            // Set frequencies of each character to 1 for now
            frequencies[i] = 1;
            for (int j = i + 1; j < str.length(); j++) {
                if (characters[i] == characters[j]) {
                    frequencies[i]++;
                    characters[j] = '0';
                }
            }
        }

        // Create array of each character and frequency
        char[] charactersArr = new char[characters.length];
        int[] frequenciesArr = new int[characters.length];
        // Secondary counter
        int index = 0;

        for (int i = 0; i < characters.length; i++) {
            if (characters[i] != '0') {
                charactersArr[index] = characters[i];
                frequenciesArr[index] = frequencies[i];
                index++;
            }
        }

        // Create a queue
        PriorityQueue<Node> queue = new PriorityQueue<Node>(index, new Compare());

        for (int i = 0; i < index; i++) {
            Node temp = new Node();
            temp.character = charactersArr[i];
            temp.value = frequenciesArr[i];
            temp.left = null;
            temp.right = null;
            queue.add(temp);
        }
        // Set root as null
        Node root = null;

        while (queue.size() > 1) {
            // Set as the first node in queue
            Node firstChar = queue.peek();
            // Remove the first node from the queue
            queue.poll();
            // Set as the first node in queue
            Node secondChar = queue.peek();
            // Remove the first node from the queue
            queue.poll();
            // Create an empty node
            Node c = new Node();
            // Add the values together
            c.value = firstChar.value + secondChar.value;
            c.character = '-';
            c.left = firstChar;
            c.right = secondChar;
            root = c;
            // Add item to the queue
            queue.add(c);
        }
        // Using recursion, starting at the root, print all the huffman codes
        printCode(root, "");

        // Create the compression ratio using the before and after sizes of the string
        int beforeSize = str.length() * 8; // Change from bits to bytes
        int afterSize = 0;

        for (int i = 0; i < str.length(); i++) {
            // Increment afterSize by the length of the huffman code
            afterSize += map.get(str.charAt(i));
        }

        // Print the compression ratio
        float compressionRatio = (float) afterSize / beforeSize;
        System.out.println("Compression Ratio: " + compressionRatio);

    }
}