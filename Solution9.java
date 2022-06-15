public class Solution9 {

    static int memo[][][]= new int [(1<<11)][10][51]; //BITMASK ROW COL

    public static void memset()
    {
        for(int i=0; i<(1<<11); i++)
        {
            for(int j=0; j<10; j++)
            {
                for(int k=0; k<51; k++)
                {
                    memo[i][j][k]=-1;
                }
            }
        }
    }

    public static int turnoff(int mask, int offset)
    {
        int all_one_bit = (1<<(offset+1))-1;
        all_one_bit ^= (1<<(offset));
        int ans = mask & all_one_bit;
        return ans;
    }

    public static int dp(int bitmask, int row, int col, boolean[][] checker)
    {
        if(memo[bitmask][row][col]!=-1)
        {
            return memo[bitmask][row][col];
        }

        int row_max = checker.length+1, col_max = checker[0].length+1;

        if(col==col_max) return 1;

        int ans=0;
        int bit[] = new int[4];

        bit[0] = (bitmask & (1<<row_max)) > 0 ? 1 : 0; //upper left bit
        bit[1] = (bitmask & (1<<0)) > 0 ? 1 : 0; //upper bit
        bit[2] = (bitmask & (1<<(row_max-1))) > 0 ? 1 : 0; //left bit
        bit[3] = (bitmask & (1<<(row_max-2))) > 0 ? 1 : 0; //left-bottom bit

        int mask = turnoff(bitmask, row_max);
        mask = (mask<<1);

        int new_row,new_col;

        if(row+1 >= row_max)
        {
            new_row = 0;
            new_col = col+1;
        }
        else
        {
            new_row = row+1;
            new_col = col;
        }

        if(col==0 && row==0) //case 0
        {
            ans += (dp(mask|1, new_row, new_col, checker));
            ans += (dp(mask, new_row, new_col, checker));
        }
        else if(col==0 && row>0) // case 1
        {
            int sum1 = bit[1];
            boolean checker1 = checker[row-1][col];

            if(checker1)// sum1+now_bit should equal to 0 or 1
            {
                if(sum1==0)// now could be 1 or 0
                {
                    ans += (dp((mask|1), new_row, new_col, checker));
                    ans += (dp((mask), new_row, new_col, checker));
                }
                else if(sum1==1)
                {
                    ans += (dp((mask), new_row, new_col, checker));
                }
            }
            else //sum1 could be 0, 2, 3, 4
            {
                ans += (dp((mask|1), new_row, new_col, checker));
                ans += (dp((mask), new_row, new_col, checker));;
            }
        }
        else if(col>0 && row==0) // case 2
        {
            int sum1 = bit[2]+bit[3];
            boolean checker1 = checker[row][col-1];

            if(checker1)// sum1+now_bit should equal to 0 or 1
            {
                if(sum1==0)// now could be 1 or 0
                {
                    ans += (dp((mask|1), new_row, new_col, checker));
                    ans += (dp((mask), new_row, new_col, checker));
                }
                else if(sum1==1)
                {
                    ans += (dp((mask), new_row, new_col, checker));
                }
            }
            else //sum1 could be 0, 2, 3, 4
            {
                ans += (dp((mask|1), new_row, new_col, checker));
                ans += (dp((mask), new_row, new_col, checker));;
            }
        }
        else if(col>0 && row==row_max-1) //case 3
        {
            int sum1 = bit[0]+bit[1]+bit[2];
            boolean checker1 = checker[row-1][col-1];

            if(checker1)// sum1+now_bit should equal to 0 or 1
            {
                if(sum1==0)// now should be 1
                {
                    ans += (dp((mask|1), new_row, new_col, checker));
                }
                else if(sum1==1)
                {
                    ans += (dp((mask), new_row, new_col, checker));
                }
            }
            else //sum1 could be 0, 2, 3, 4
            {
                if(sum1==0)// now can not be 1
                {
                    ans += (dp((mask), new_row, new_col, checker));
                }
                else if(sum1==1)// now should be 1
                {
                    ans += (dp((mask|1), new_row, new_col, checker));
                }
                else //current sum = 2/3
                {
                    ans += (dp((mask), new_row, new_col, checker));
                    ans += (dp((mask|1), new_row, new_col, checker));
                }

            }
        }
        else //case 4
        {
            int sum1 = bit[0] + bit[1] + bit[2];
            int sum2 = bit[2] + bit[3];

            boolean checker1 = checker[row-1][col-1];
            boolean checker2 = checker[row][col-1];

            if(checker1) // sum1+now_bit must be 1
            {
                if(checker2) //sum2+now_bit can be 0 or 1
                {
                    if(sum1==1)
                    {
                        ans += (dp((mask), new_row, new_col, checker));
                    }
                    else if(sum1==0 && sum2==0)
                    {
                        ans += (dp((mask|1), new_row, new_col, checker));
                    }
                }
                else //sum2+now bit can be 0 1 2 3
                {
                    if(sum1==1)
                    {
                        ans += (dp((mask), new_row, new_col, checker));
                    }
                    else if(sum1==0)
                    {
                        ans += (dp((mask|1), new_row, new_col, checker));
                    }     
                }
            }
            else //sum1 must not be 1
            {
                if(checker2) //sum2+now_bit can be 0 or 1
                {
                    if(sum1==0)//now_bit must be 0
                    {
                        ans += (dp((mask), new_row, new_col, checker));
                    }
                    else if(sum1==1)//now_bit must be 1
                    {
                        ans += (dp((mask|1), new_row, new_col, checker));
                    }
                    else
                    {
                        if(sum2==1)//now bit must be 0
                        {
                            ans += (dp((mask), new_row, new_col, checker));
                        }
                        else if(sum2==0)
                        {
                            ans += (dp((mask), new_row, new_col, checker));
                            ans += (dp((mask|1), new_row, new_col, checker));
                        }
                    }
                }
                else //sum2+now bit free
                {
                    if(sum1==0)//now_bit must be 0
                    {
                        ans += (dp((mask), new_row, new_col, checker));
                    }
                    else if(sum1==1)//now_bit must be 1
                    {
                        ans += (dp((mask|1), new_row, new_col, checker));
                    }
                    else
                    {
                        ans += (dp((mask), new_row, new_col, checker));
                        ans += (dp((mask|1), new_row, new_col, checker));

                    }
                }
            }
        }

        //System.out.println(row_max+" "+ col_max+" "+ans);
        //System.out.println(bitmask+" "+ row+" "+col+" "+ans);

        return memo[bitmask][row][col]=ans;
    }

    public static int solution(boolean[][] g) {
        memset();
        int ans = dp(0,0,0,g);
        return ans;
    }

    public static void main(String[] args)
    {
        boolean[][] checker = new boolean[][] {{true, false, true, false, false, true, true, true}, 
                                               {true, true, true, false, false, false, true, false}, 
                                               {true, true, true, false, false, false, true, false}, 
                                               {true, false, true, false, false, false, true, false}, 
                                               {true, false, true, false, false, true, true, true}};
        //Solution9.solution(new boolean[][] {{true, false, true}, {false, true, false}, {true, false, true}});

        System.out.println(Solution9.solution(checker));

        //System.out.println(turnoff(Integer.parseInt("111100",2),4));
    }
}