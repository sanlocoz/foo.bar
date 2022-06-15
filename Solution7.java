import java.util.ArrayList;
import java.util.List;

public class Solution7 {

    static double eps=Math.pow(10,-9);

    public static boolean check(int a, int b)
    {
        int max,min;
        if(a>b)
        {
            max = a;
            min = b;
        }
        else
        {
            max = b;
            min = a;
        }

        double checkingCriteria = (double) max / (double) min + 1f;
        double log = Math.log(checkingCriteria) / Math.log(2f);

        double truncatedValue = Math.floor(log);

        //System.out.println(log+" "+truncatedValue);

        if(Math.abs(log-truncatedValue)<eps)
        {
            return false; //cannot be a pair
        }
        else
        {
            return true; // can be a pair
        }
    }

    public static int solution(int[] banana_list) {
        int n=banana_list.length;
        List<List<Integer>> edge_list = new ArrayList<List<Integer>>(n);
        
        for(int i=0; i<n; i++)
        {
            List<Integer> edge = new ArrayList<Integer>();
            for(int j=0; j<n; j++)
            {
                if(check(banana_list[i],banana_list[j]))
                {
                    edge.add(j);
                }
                //System.out.println(banana_list[i]+" "+banana_list[j]+" "+check(banana_list[i],banana_list[j]));
            }
            edge_list.add(edge);
        }
        
        /*System.out.println(edge_list.get(0).remove(edge_list.get(0).indexOf(3)));
        
        for(int i=0; i<n; i++)
        {
            System.out.println(i+": ");
            for(int j=0; j<edge_list.get(i).size(); j++)
            {
                System.out.println(edge_list.get(i).get(j));
            }
            
            System.out.println();
        }
        */
        
        boolean valid = true;
        int ans = n;
        while (valid)
        {
            int min_edge = 100, idx_min = -1;
            for(int i=0; i<n; i++)
            {
                //System.out.println(edge_list.get(i).size());
                int list_size_now = edge_list.get(i).size();
                if(min_edge>list_size_now && list_size_now>0)
                {
                    min_edge = list_size_now;
                    idx_min = i;
                }
                
                
            }
            
            if(idx_min == -1)
            {
                valid = false;
            }
            else
            {
                int neighbour_min_edge = 100, idx_neighbour_min = -1;
                for(int i=0; i<edge_list.get(idx_min).size(); i++)
                {
                    int list_size_neighbouring_edge_now = edge_list.get(edge_list.get(idx_min).get(i)).size();
                    if(neighbour_min_edge>list_size_neighbouring_edge_now && list_size_neighbouring_edge_now>0)
                    {
                        neighbour_min_edge = list_size_neighbouring_edge_now;
                        idx_neighbour_min = edge_list.get(idx_min).get(i);
                    }
                }
                //System.out.println(neighbour_min_edge+" "+idx_neighbour_min);
                
                //removing neigbour and current edge
                ans-=2;
                /*System.out.println(edge_list.get(0).remove(edge_list.get(0).indexOf(3)));*/
                
                //removing idx_min from its neighbouring node
                for(int i=0; i<edge_list.get(idx_min).size(); i++)
                {
                    //System.out.println(edge_list.get(idx_min).get(i));
                    
                    edge_list.get(edge_list.get(idx_min).get(i)).remove(edge_list.get(edge_list.get(idx_min).get(i)).indexOf(idx_min));
                }
                
                //removing idx_neighbour_min from its neighbouring node
                for(int i=0; i<edge_list.get(idx_neighbour_min).size(); i++)
                {
                    //System.out.println(edge_list.get(idx_neighbour_min).get(i));
                    
                    edge_list.get(edge_list.get(idx_neighbour_min).get(i)).remove(edge_list.get(edge_list.get(idx_neighbour_min).get(i)).indexOf(idx_neighbour_min));
                }
                
                //removing all idx_min neighbour
                edge_list.get(idx_min).clear();

                //removing all idx_neigbour_min neighbour
                edge_list.get(idx_neighbour_min).clear();
            }
        }
        
        /*for(int i=0; i<n; i++)
        {
            System.out.println(i+": ");
            for(int j=0; j<edge_list.get(i).size(); j++)
            {
                System.out.println(edge_list.get(i).get(j));
            }
            
            System.out.println();
        }*/

        return ans;
    }

    public static void main(String[] args)
    {
        System.out.println(Solution7.solution(new int[] {1,1}));
        
        //System.out.println(check(6,6));
    }
}