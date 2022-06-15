import java.math.BigInteger;

public class Solution3 {
    public static String solution(int[] xs) {
        BigInteger[] val = new BigInteger[xs.length];
        int[] counter = new int[] {0,0,0};
        BigInteger[] answer = {BigInteger.ONE,BigInteger.ZERO,BigInteger.ONE};
        BigInteger minNegative = BigInteger.ONE;
        
        for(int i=0; i<xs.length; i++)
        {
            val[i] = new BigInteger(Long.toString(xs[i]));
            
            if(val[i].compareTo(BigInteger.ZERO)<0)
            {
                counter[0]++;
                answer[0] = answer[0].multiply(val[i]);
                
                if(minNegative.compareTo(BigInteger.ONE)==0)
                {
                    minNegative = val[i];
                }
                else if(val[i].compareTo(minNegative)>0)
                {
                    minNegative = val[i];
                }   
            }
            else if(val[i].compareTo(BigInteger.ZERO)==0)
            {
                counter[1]++;
            }
            else if(val[i].compareTo(BigInteger.ZERO)>0)
            {
                counter[2]++;
                answer[2] = answer[2].multiply(val[i]);
            }
        }
        
        //System.out.println(minNegative);
        
        BigInteger retBigInteger = BigInteger.ZERO;
        String ret="";
        
        if(counter[2]>0)
        {
            //System.out.println("There is at least 1 positive integer");
            if(answer[0].compareTo(BigInteger.ZERO)>0)
            {
                retBigInteger = answer[0].multiply(answer[2]);
            }
            else
            {
                retBigInteger = answer[0].multiply(answer[2]).divide(minNegative);
            }
            ret = retBigInteger.toString();
            
        }
        else if(counter[0]>0) // no positive integer
        {
            //System.out.println("No positive integer in testcase");
            if(counter[0]==1 && counter[1]==0)
            {
                retBigInteger = answer[0];
            }
            else if(counter[0]==1 && counter[1]>0)
            {
                retBigInteger = BigInteger.ZERO;
            }
            else
            {
                retBigInteger = answer[0];
                
                if(answer[0].compareTo(BigInteger.ZERO)<0)
                {
                    retBigInteger = answer[0].divide(minNegative);
                }
            }
            
            ret = retBigInteger.toString();
        }
        else //all zero
        {
            //System.out.println("All zero");
            ret="0";
        }
        
        return ret;
    }
    public static void main(String[] args)
    {
        System.out.println(Solution3.solution(new int[] {5,6}));
    }
}