public class MatrixChainMultiplication {
    private int[] p;
    private int[][] m;
    private int[][] s;

    public MatrixChainMultiplication(int[] p) {
        this.p = p;
        int n = p.length - 1;
        m = new int[n + 1][n + 1];
        s = new int[n + 1][n + 1];
    }

    public void printM() {
        System.out.println("M:");
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printS() {
        System.out.println("S:");
        for (int i = 1; i < s.length; i++) {
            for (int j = 1; j < s[i].length; j++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solve() {
        int n = p.length - 1;

        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
    }

    public void printOptimalParens() {
        printOptimalParens(1, p.length - 1);
    }

    private void printOptimalParens(int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOptimalParens(i, s[i][j]);
            printOptimalParens(s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        int[] arr = { 30, 35, 15, 5, 10, 20, 25 };
        MatrixChainMultiplication mcm = new MatrixChainMultiplication(arr);
        mcm.solve();
        mcm.printM();
        mcm.printS();
        mcm.printOptimalParens();
    }
}
