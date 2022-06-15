public class Solution6 {

    static int[][][] memo = new int [25][25][2]; // n m power
    static int[] x = {0,-1,0,1};
    static int[] y = {-1,0,1,0};

    final static int MAX = 99999999;

    public static void memset()
    {
        for(int i = 0; i<25; i++)
        {
            for(int j = 0; j<25; j++)
            {
                for(int k = 0; k<2; k++)
                {
                    memo[i][j][k]=MAX;
                }
            }
        }
    }

    public static void fill(int[][] map) {

        int row=map.length, col=map[0].length;

        //System.out.println(row+" "+col);

        memo[row-1][col-1][0]=1;
        memo[row-1][col-1][1]=1;


        for(int j=row-1;j>=0;j--)
        {
            for(int k=col-1;k>=0;k--)
            {
                if(j!=row-1 || k!=col-1)
                {
                    for(int l=0;l<4;l++)
                    {
                        int new_j= j+x[l];
                        int new_k= k+y[l];

                        if(new_j>=0 && new_j<row && new_k>=0 && new_k<col)
                        {
                            if(map[j][k]==1)
                            {
                                memo[j][k][0]=Math.min(memo[j][k][0], 1+memo[new_j][new_k][1]);
                                //memo[j][k][1]=  not available     
                            }
                            else
                            {
                                memo[j][k][0]=Math.min(Math.min(memo[j][k][0], 1+memo[new_j][new_k][0]),1+memo[new_j][new_k][1]);
                                memo[j][k][1]=Math.min(memo[j][k][1],1+memo[new_j][new_k][1]);
                            }
                        }
                    }
                }
            }
        }
    }

    public static int solution(int[][]map)
    {
        memset();
        for(int i=0;i<=50;i++)
        {
            fill(map);
        }


        return Math.min(memo[0][0][0],memo[0][0][1]);

    }

    public static void main(String[] args)
    {
        /*System.out.println(Solution6.solution(new int[][] {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}}));*/

        /*for(int i = 0; i<20; i++)
        {
            for(int j = 0; j<20; j++)
            {
                System.out.print(memo[i][j][1]+" ");
            }
            System.out.println();
        }*/

        //System.out.println(Solution6.solution(new int[][] {{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}}));

    }
}