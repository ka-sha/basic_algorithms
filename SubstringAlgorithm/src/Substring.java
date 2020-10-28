import java.util.ArrayList;
import java.util.Arrays;

public class Substring {

    private static final int d = 26;

    public static ArrayList<Integer> BoyerMoore(String T, String P)
    {
        if (incorrectData(T, P))
            return new ArrayList<>();

        int[] suffixTable = suffixTable(P);
        int[] stopTable = stopTable(P);
        ArrayList<Integer> result = new ArrayList<>();
        int deltaStop, deltaSuff;

        for (int i = 0; i < T.length() - P.length() + 1; i += Math.max(deltaStop, deltaSuff))
        {
            int j = P.length() - 1;

            while (j >= 0 && P.charAt(j) == T.charAt(i + j))
                j--;

            if (j == -1)
            {
                result.add(i);
                deltaStop = 1;
            }
            else
                deltaStop = j - stopTable[T.charAt(i + j) - 'a'];

            deltaSuff = suffixTable[j + 1];
        }

        return result;
    }

    public static ArrayList<Integer> RobinKarp(String T, String P)
    {
        if (incorrectData(T, P))
            return new ArrayList<>();

        int q = 227;
        int n = T.length(), m = P.length();
        int d_m = ((int)Math.pow(d, m - 1)) % q;
        int h = 0, h1 = 0;
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < m; i++)
        {
            h = (h*d + P.charAt(i)) % q;
            h1 = (h1*d + T.charAt(i)) % q;
        }

        for (int s = 0; s < n - m + 1; s++)
        {
            if (h == h1 && P.equals(T.substring(s, s + m)))
                result.add(s);
            if (s < n - m)
                h1 = (d*(h1 - d_m*T.charAt(s)) + T.charAt(s + m)) % q;
            if (h1 < 0)
                h1 += q;
        }

        return result;
    }

    public static ArrayList<Integer> KnuthMorrisPratt(String T, String P)
    {
        if (incorrectData(T, P))
            return new ArrayList<>();

        int[] pi = prefix(P);
        int k = 0;
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < T.length(); i++)
        {
            while (k > 0 && P.charAt(k) != T.charAt(i))
                k = pi[k - 1];

            if (P.charAt(k) == T.charAt(i))
                k++;

            if (k == P.length())
            {
                result.add(i - P.length() +1);
                k = pi[k - 1];
            }
        }

        return result;
    }

    public static ArrayList<Integer> automa(String T, String P)
    {
        if (incorrectData(T, P))
            return new ArrayList<>();

        int m = P.length();
        int n = T.length();
        int[][] table = new int[m + 1][26];
        ArrayList<Integer> result = new ArrayList<>();

        for (int s = 0; s < m; s++)
            for (int c = 'a'; c <= 'z'; c++)
                table[s][c - 'a'] = suffix(P.substring(0, s + 1), P.substring(0, s) + (char)c);
        for (int c = 'a'; c <= 'z'; c++)
            table[m][c - 'a'] = suffix(P.substring(0, m), P.substring(0, m) + (char)c);

        for (int i = 0, j = 0; i < n; i++)
        {
            j = table[j][T.charAt(i) - 'a'];
            if (j == m)
                result.add(i - j + 1);
        }

        return result;
    }

    private static boolean incorrectData(String T, String P)
    {
        return (T.isEmpty() || P.isEmpty() || P.length() > T.length());
    }

    private static int[] suffixTable(String P)
    {
        int m = P.length();
        int[] table = new int[m + 1];
        int[] pi = prefix(P);
        int[] pi1 = prefix(new String(new StringBuilder(P).reverse()));

        Arrays.fill(table, m - pi[m - 1]);

        for (int i = 0; i < m; i++)
        {
            int ind = m - pi1[i];
            int shift = i - pi1[i] + 1;

            if (table[ind] > shift)
                table[ind] = shift;
        }

        return table;
    }

    private static int[] stopTable(String P)
    {
        int[] stop = new int[d];

        for (int i = 'a'; i <= 'z'; i++)
        {
            int j = P.length() - 2;

            while (j >= 0 && i != P.charAt(j))
                j--;

            stop[i - 'a'] = j;
        }

        return stop;
    }

    private static int[] prefix(String P)
    {
        int n = P.length();
        int[] pi = new int[n];
        int k = 0;
        pi[0] = 0;

        for (int i = 1; i < n; i++)
        {
            while (k > 0 && P.charAt(k) != P.charAt(i))
                k = pi[k - 1];

            if (P.charAt(k) == P.charAt(i))
                k++;
            pi[i] = k;
        }

        return pi;
    }

    private static int suffix(String partOfP, String partOfPWithC)
    {
        int max = 0;
        int n = partOfP.length();
        int m = partOfPWithC.length();

        for (int i = 0; i < n; i++)
            if (partOfP.substring(0, i + 1).equals(partOfPWithC.substring(m - i - 1, m)))
                max = i + 1;

        return max;
    }
}