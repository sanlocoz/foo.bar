import java.math.BigInteger;

public class Solution5 {
    public static BigInteger solve(BigInteger x, BigInteger y)
    {
        //System.out.println(x+ " "+y);
        
        BigInteger ans=BigInteger.ZERO;
        if(x.compareTo(BigInteger.ONE)==0 && y.compareTo(BigInteger.ONE)==0)
        {
            return BigInteger.ZERO;
        }
        else if(y.compareTo(BigInteger.ONE)== 0 || x.compareTo(BigInteger.ONE) == 0) 
        {
            return x.multiply(y).subtract(BigInteger.ONE);
        }
        else if(x.compareTo(BigInteger.ZERO)<=0 || y.compareTo(BigInteger.ZERO)<=0)
        {
            return new BigInteger("-1");
        }
        else
        {
            BigInteger maxi,mini;
            if(x.compareTo(y)>=0)
            {
                maxi=x;
                mini=y;
            }
            else
            {
                maxi=y;
                mini=x;
            }
            
            BigInteger multiply = maxi.divide(mini);
            //System.out.println(maxi+" "+mini+" "+multiply);
            BigInteger ret = solve(maxi.subtract(mini.multiply(multiply)),mini);
            
            if(ret.compareTo(new BigInteger("-1"))!=0)
            {
                ans = multiply.add(ret);
            }
            else
            {
                ans = new BigInteger("-1");
            }
        }
        
        return ans;
    }
    
    public static String solution(String x, String y) {
        BigInteger b1 = new BigInteger(x),b2=new BigInteger(y);
        BigInteger ret = solve(b1,b2);
        //System.out.println("Halo");
        if(ret.compareTo(BigInteger.ZERO)>=0)
        {
            return ret.toString();
        }
        else
        {
            return "impossible";
        }
    }
    
    public static void main(String[] args)
    {
        /*for(int i=1;i<=100;i++)
        {
            for(int j=1;j<=100;j++)
            {
                System.out.print(Solution5.solution(Integer.toString(i), Integer.toString(j))+" ");
            }
            System.out.println();
        }
        BigInteger a = new BigInteger("5");
        BigInteger b = new BigInteger("2");
        System.out.println(a.divide(b));*/
        
        System.out.println(Solution5.solution("1000000000000000000000000000", "1"));
        
    }
}