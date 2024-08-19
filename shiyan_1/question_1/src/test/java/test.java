import org.junit.Test;

public class test {
    @Test
    public void test_array(){
                int [][]ARRAY =new int [16][16];
                int i=0;
        for (int j = 0; j < 16; j++)
        {
            for (int k = 0; k < 16; k++)
            {
                ARRAY[j][k]=0;
            }
        }

        while (true)
        {
            ARRAY[i][0] = 1;
            ARRAY[i][2] = 1;
            ARRAY[i][3] = 1;
            ARRAY[i][9] = 1;
            ARRAY[i][8] = 1;
            ARRAY[i][11] = 1;
            break;
        }
                i++;
                while (true){
                    ARRAY[i][2]=1;
                    ARRAY[i][5]=1;
                    ARRAY[i][14]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][6]=1;
                    ARRAY[i][8]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][1]=1;
                    ARRAY[i][3]=1;
                    ARRAY[i][5]=1;
                    ARRAY[i][10]=1;
                    ARRAY[i][12]=1;
                    ARRAY[i][14]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][4]=1;
                    ARRAY[i][7]=1;
                    ARRAY[i][12]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][0]=1;
                    ARRAY[i][8]=1;
                    ARRAY[i][12]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][3]=1;
                    ARRAY[i][6]=1;
                    ARRAY[i][10]=1;
                    ARRAY[i][14]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][0]=1;
                    ARRAY[i][5]=1;
                    ARRAY[i][8]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][2]=1;
                    ARRAY[i][4]=1;
                    ARRAY[i][6]=1;
                    ARRAY[i][12]=1;
                    ARRAY[i][15]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][6]=1;
                    ARRAY[i][8]=1;
                    ARRAY[i][10]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][0]=1;
                    ARRAY[i][4]=1;
                    ARRAY[i][14]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][2]=1;
                    ARRAY[i][8]=1;
                    ARRAY[i][11]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][1]=1;
                    ARRAY[i][4]=1;
                    ARRAY[i][6]=1;
                    ARRAY[i][10]=1;
                    ARRAY[i][13]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][3]=1;
                    ARRAY[i][4]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][2]=1;
                    ARRAY[i][10]=1;
                    ARRAY[i][12]=1;
                    break;
                }
                i++;
                while (true){
                    ARRAY[i][5]=1;
                    ARRAY[i][14]=1;
                    break;
                }
        Matrix matrix = new Matrix(16, 16, 4);
                matrix.init_the_Array(ARRAY);
        point answer = matrix.answer();
        System.out.println(answer);

    }
}
