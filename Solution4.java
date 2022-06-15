
public class Solution4 {
    static int[][][] memo = new int [210][210][210];
    public static void memset()
    {
        for(int i = 0; i<210; i++)
        {
            for(int j = 0; j<210; j++)
            {
                for(int k = 0; k<210; k++)
                {
                    memo[i][j][k]=-1;
                }
            }
        }
    }
    
    public static int solve(int num, int digit, int threshold)
    {
        if(memo[num][digit][threshold]!=-1)
        {
            return memo[num][digit][threshold];
        }
        
        int ans = 0;
        if(num<=0 || threshold<=0)
        {
            return 0;
        }

        if(digit==1 && num<=threshold)
        {
            return 1;
        }
        else if(digit==1 && num>threshold)
        {
            return 0;
        }
        
        for(int i=1; i<=num; i++)
        {
            int ans_temp = 1;
            if(i<=threshold)
            {
                ans_temp*= solve(num-i, digit-1, i-1);
                ans+=ans_temp;
            }
        }
        
        memo[num][digit][threshold]=ans;
        
        return ans;
    }
    
    public static int solution(int n) {
        int ans = 0;
        memset();
        
        for(int i=2;i<=n;i++)
        {
            ans += solve(n,i,n);
        }

        return ans;
    }
    
    public static void main(String[] args)
    {
        int a = Solution4.solution(200);
        System.out.println(a);
    }
}