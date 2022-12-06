import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Node {
    int value;
    char ch;
    Node left, right;
}

// For comparing both the node values
class Comp implements Comparator<Node> {
    public int compare(Node x, Node y) {
        return x.value - y.value;
    }
}

// Implementing the huffman code
class Huffman {
    //create a static HashMap with key as character and value as integer 
    private static Map<Character, Integer> map;
    static {
        map = new HashMap<>();
    }

    // function to print the huffman code table by calling recursively
    public static void printTable(Node root, String str) {
        // prints if it is leaf node and character stored is not -
        if (root.left == null && root.right == null && root.ch != '-') {
            System.out.println(root.ch + "  :  " + str);
            map.put(root.ch, str.length()); //insert size of str into map with key as character ch
            return;
        }
        // calling recursively
        printTable(root.left, str + "0");
        printTable(root.right, str + "1");
    }

    public static void main(String[] args) {
        // Create scanner
        Scanner scanner = new Scanner(System.in);
        // Ask user for input
        System.out.println("Enter a string: ");
        String str = scanner.nextLine(); 

        // Make str lowercase
        str = str.toLowerCase();

        // Convert string to char array
        char[] characters = str.toCharArray();

        // Array of frequenciesuencies
        int[] frequencies = new int[str.length()]; 

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
        

        char[] cArray = new char[characters.length];
        int[] cFreq = new int[characters.length];
        int j = 0;
        // iterate through charactersay and assign elements that are not '0' to cArray
        // assign frequencies array to cFreq
        for (int i = 0; i < n; i++) {
            if (characters[i] != '0') {
                cArray[j] = characters[i];
                cFreq[j] = frequencies[i];
                j++;
            }
        }
        n = j; // set n to length of cArray
        // declare a priority queue with comp as comparator
        PriorityQueue<Node> que = new PriorityQueue<Node>(n, new Comp());
        // iterate through all cArray,cFreq elements and make temp node object with them
        // add node into que
        for (int i = 0; i < n; i++) {
            Node temp = new Node();
            temp.ch = cArray[i];
            temp.value = cFreq[i];
            temp.left = null;
            temp.right = null;
            que.add(temp);
        }
        // declare root node
        Node root = null;
        // loop until que size is greater than 1
        while (que.size() > 1) {
            // set a as top element of que and remove it from que
            Node a = que.peek();
            que.poll();
            // set b as next top element of que and remove it from que
            Node b = que.peek();
            que.poll();
            // create new node
            Node c = new Node();
            // add a and b values and make it as c value
            c.value = a.value + b.value;
            c.ch = '-'; // c chara as -
            c.left = a; // left node as a
            c.right = b;// right node as b
            root = c; // c as root
            que.add(c); // add it to que
        }
        // print the huffman code table
        printTable(root, "");
        //product of input string length and 8
        int old_size = str.length() * 8;
        int new_size = 0; //initialize to 0
        //iterate through string characters
        for (int i = 0; i < str.length(); i++) {
            //add the number of bits of each code to new_size
            new_size += map.get(str.charAt(i));
        }
        float cr = (float) new_size / old_size; //calculate compression_ratio
        System.out.println("Compression ratio: " + cr); //print ratio

    }
}