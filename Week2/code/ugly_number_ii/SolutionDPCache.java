package code.ugly_number_ii;

public class SolutionDPCache {
    static int[] dp = new int[1690];
    static int a = 0;
    static int b = 0;
    static int c = 0;
    static int count = 1;
    public int nthUglyNumber(int n) {
        if( n<= count){
            return dp[n-1];
        }
        for(int i = count; i < n; i++){
            dp[i] = Math.min(Math.min(dp[a]*2, dp[b]*3),dp[c]*5);
            if(dp[i]==dp[a]*2) a++;
            if(dp[i]==dp[b]*3) b++;
            if(dp[i]==dp[c]*5) c++;
            count++;
        }
        return dp[n-1];
    }
    static{
        dp[0] = 1;
    }
}
