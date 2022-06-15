import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

class dp_state {
    public int now, bunny, time_limit;

    public dp_state(int now, int bunny, int time_limit)
    {
        this.now = now;
        this.bunny = bunny;
        this.time_limit = time_limit;
    }
}

public class Solution8 {

    static int memo[][] = new int [7][32]; //[START 1 2 3 4 5 END][BITMASK 2^5]
    static int bunnies_to_be_captured = 0;
    static Queue<dp_state> QDPState = new LinkedList<dp_state>();

    public static void memset()
    {
        for(int i=0; i<7; i++)
        {
            for(int j=0; j<32; j++)
            {
                memo[i][j] = -999;
            }
        }
    }

    public static int[] representation(int num,int n)
    {
        List<Integer> ans = new ArrayList<Integer>();

        for(int i=0; i<n; i++)
        {
            if(num%2==1)
            {
                ans.add(n-i);
            }

            num>>=1;
        }

        Collections.sort(ans);

        int[] ansi = new int[ans.size()];

        for(int i=0; i<ans.size(); i++)
        {
            ansi[i]=ans.get(i)-1;
        }

        return ansi;
    }

    public static void dp(int now,int bunny, int[][] times)
    {
        int n = times.length;
        int num_bunny = n-2;
        
        for(int i=0; i<n; i++)
        {
            if(times[now][i]!=0 || i!=now)
            {
                if(i!=0 && i!=n-1) //visiting bunny
                {
                    //System.out.println(i+" "+bunny);
                    memo[i][bunny | (1<<(num_bunny-i))] = Math.max(memo[i][bunny | (1<<(num_bunny-i))], memo[now][bunny]-times[now][i]);
                    QDPState.add(new dp_state(i,(bunny | 1<<(num_bunny-i)),memo[now][bunny]-times[now][i]));
                }
                else
                {
                    memo[i][bunny] = Math.max(memo[i][bunny], memo[now][bunny]-times[now][i]);
                    QDPState.add(new dp_state(i,bunny,memo[now][bunny]-times[now][i]));
                }
            }
        }
    }

    public static int[] solution(int[][] times, int times_limit) {
        int n = times.length;
        memset();
        memo[0][0] = times_limit;

        QDPState.add(new dp_state(0,0,times_limit));

        int counter = 0;
        while(!QDPState.isEmpty() && counter<500000)
        {
            counter++;
            dp_state i = QDPState.poll();
            dp(i.now,i.bunny,times);
            //System.out.println(i.now+" "+i.bunny+" "+i.time_limit);
        }

        for(int i = 0; i < 1<<(n-2); i++)
        {
            if(memo[n-1][i]>=0)
            {
                if(Integer.bitCount(i)>Integer.bitCount(bunnies_to_be_captured))
                {
                    bunnies_to_be_captured=i;
                }
                else if(Integer.bitCount(i)==Integer.bitCount(bunnies_to_be_captured) && i>bunnies_to_be_captured)
                {
                    bunnies_to_be_captured=i;
                }
            }
        }
        int[] ans = representation(bunnies_to_be_captured,n-2);
        return ans;

    }


    public static void main(String[] args)
    {
        int[] ans=Solution8.solution(new int[][] {{0, 2, 2, 2, -1}, 
            {9, 0, 2, 2, -1}, 
            {9, 3, 0, 2, -1}, 
            {9, 3, 2, 0, -1}, 
            {9, 3, 2, 2, 0}}, 1);

        /*for (int i=0; i<ans.length; i++)
        {
            System.out.print(ans[i]+" ");
        }
        System.out.println("\n");
        System.out.println(bunnies_to_be_captured);*/
    }
}