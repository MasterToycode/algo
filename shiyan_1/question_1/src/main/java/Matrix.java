import java.util.HashMap;
import java.util.Map;
public class Matrix {
    private int N;
    private int M;
    private int L;
    public Matrix(int n, int m, int l)
    {
        N = n;
        M = m;
        L = l;
    }
    public Matrix() {
    }
    private int[][] Array= new int[M][N];
    public void init_the_Array(int [][] ARRAY){
        Array=ARRAY;
    }
    public point answer(){
        Map<point, Integer> measurements = new HashMap<>();
        point finally_point = new point(-1, -1);
        for (int i = 0; i<=M-L; i++)
        {
            for (int j = 0; j<=N-L; j++)
            {
                int k=L-1;
                int value=0;
                while (k>=0)
                {
                    value=get_the_number(i,j,k--)+value;
                }
                measurements.put(new point(i,j),value);
            }
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<point, Integer> entry : measurements.entrySet())
        {
            int value = entry.getValue();
            if (value < min)
            {
                min = value;
                finally_point=entry.getKey();
            }
        }
        return finally_point;
    }


    private int get_the_number(int i,int j,int h)
    {
        int total=0;
        if (Array[i+L-h-1][j+L-h-1]==1) total++;
        if (h>=0)
        {
            for (int k=j+L-h;k<j+L;k++)
                if (Array[i+L-h-1][k]==1) total+=1;
            for (int k=i+L-h;k<i+L;k++)
                if (Array[k][j+L-h-1]==1) total+=1;
        }
       return total;
    }

}
