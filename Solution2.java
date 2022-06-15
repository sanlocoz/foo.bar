class Fraction {
    public long numerator;
    public long denominator;
    public String sign;

    private long GCD (long a, long b)
    {
        long a1 = Math.max(a, b);
        long b1 = Math.min(a, b);

        if (b1==0)
        {
            return a1;
        }
        else
        {
            return GCD(b1, a1%b1);
        }
    }

    public Fraction(long numerator, long denominator)
    {
        if(numerator*denominator<0)
        {
            this.sign = "Negative";
            this.numerator  = numerator / GCD(Math.abs(numerator), Math.abs(denominator));
            this.denominator = denominator /  GCD(Math.abs(numerator), Math.abs(denominator));
        }
        else
        {
            this.sign = "Positive";
            this.numerator  = Math.abs(numerator) / GCD(Math.abs(numerator), Math.abs(denominator));
            this.denominator = Math.abs(denominator) /  GCD(Math.abs(numerator), Math.abs(denominator));
        }

    }
    
    public static Fraction sum(Fraction a, Fraction b)
    {
        Fraction ans = new Fraction ((a.numerator*b.denominator+a.denominator*b.numerator),a.denominator*b.denominator);
        return ans;
    }
    
    public static Fraction minus(Fraction a, Fraction b)
    {
        Fraction ans = new Fraction ((a.numerator*b.denominator-a.denominator*b.numerator),a.denominator*b.denominator);
        return ans;
    }
    
    public static Fraction multiply(Fraction a, Fraction b)
    {
        Fraction ans = new Fraction (a.numerator*b.numerator, a.denominator*b.denominator);
        return ans;
    }
    
    public static Fraction divide(Fraction a, Fraction b)
    {
        Fraction ans = new Fraction (a.numerator*b.denominator, a.denominator*b.numerator);
        return ans;
    }

}

public class Solution2 {
    public static int[] solution(int[] pegs) {
        Fraction divisor;
        long sum = 0;

        for (int i=0; i<pegs.length; i++)
        {
            if(i%2==0)
            {
                sum-=(2*pegs[i]);
            }
            else
            {
                sum+=(2*pegs[i]);
            }
        }
        
        sum+=pegs[0];
        
        if(pegs.length%2==0)
        {
            divisor = new Fraction(3,2);
            sum-=pegs[pegs.length-1];
        }
        else
        {
            divisor = new Fraction(1,2);
            sum+=pegs[pegs.length-1];
        }
        
        boolean valid = true;
        
        Fraction[] ans_full = new Fraction[pegs.length];
        
        for(int i=0; i<ans_full.length; i++)
        {
            if(i==0)
            {
                ans_full[i] = Fraction.divide(new Fraction(sum, 1), divisor);   
            }
            else
            {
                ans_full[i] = Fraction.minus(new Fraction(pegs[i]-pegs[i-1],1), ans_full[i-1]);
            }
            
            if(Fraction.minus(ans_full[i], new Fraction(1,1)).sign.compareTo("Negative")==0)
            {
                valid = false;
            }
            
           //System.out.println(ans_full[i].numerator+" "+ans_full[i].denominator);
        }
        
        if(valid)
        {
            return (new int[] {(int)ans_full[0].numerator,(int)ans_full[0].denominator});
        }
        else
        {
            return (new int[] {-1,-1});
        }
    }
    public static void main(String[] args)
    {
        int[] ans = Solution2.solution(new int[] {4,30,50});
        
        for(int a:ans)
        {
            //System.out.println(a);
        }
    }
}
