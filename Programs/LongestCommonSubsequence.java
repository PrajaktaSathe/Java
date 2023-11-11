package Programs;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 **/

/**
 * BugFix
 * Descriptions:
 * 1. Renamed class file and name from Solutions to LongestCommonSubsequence
 * 2. Added package name
 * 3. Optimized imports
 * 4. Added missing main method in order to test
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int mat[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            mat[i][0] = 0;
        for (int i = 0; i <= n; i++)
            mat[0][i] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                else {
                    mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]);
                }
            }
        }
        return mat[m][n];
    }

    public static void main(String[] args) {
        String text1 = "abcde", text2 = "ace";
        LongestCommonSubsequence subSeq = new LongestCommonSubsequence();
        System.out.println("The longest common subsequence length [ " + subSeq.longestCommonSubsequence(text1, text2) + " ]");
    }

}
