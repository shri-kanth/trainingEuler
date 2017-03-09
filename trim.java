import java.io.*;
import java.util.*;

public class trim 
{

    static long N[][];
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        boolean found = false;
              
        if(n%3 != 0)
        {
            int temp = n;
            n = m;
            m = temp;
        }
        
        int max = Math.max(n,m);
        
        N = new long[max/3][max];
        
        for(int i = 0; i < max/3; i++)
           {
             N[i][0] = 1;
             for(int j = 1; j < max; j++)
                N[i][j] = 0;
           }
        
        for(int j = 1; j < max; j++)
           {  
             N[0][j] += N[0][j-1] ;
             for(int k = 1; k < j; k++)
                 N[0][j] += club(k)*N[0][j-k-1];
             N[0][j] += club(j);
           }
        
        for(int i = 1; i < max/3; i++)
        {
            for(int j = 1; j < max; j++)
            {
                N[i][j] += N[i-1][j]*N[0][j] + N[i][j-1]-(N[i-1][j-1]*N[0][j-1]);
                for(int k = 1; k < j; k++)
                    N[i][j] += cover(i,k)*N[i][j-k-1];
                N[i][j] += cover(i,j);
                
                if(i == (n/3 - 1) && j == m-1)
                {
                    found = true;
                    break;
                }
            }
            
            if(found)
                break;
        }
        
            System.out.print(N[n/3 -1][m-1] % 1000000007);
    }
    
    public static long club(int j)
    {
        if((j+1) % 3 == 1)
            return 2;
        else if((j+1) % 3 == 2)
            return 2;
        else if((j+1) % 3 == 0)
        {
            if(j == 2)
                return 5;
            else
                return 4;  
        }   
        return -1;
    }
    
    public static long cover(int i, int j)
    {
        if((j+1) % 3 == 1)
        {
            long ans  = 0;
            if(i > 1)
            {
                for(int k = 2; k < i; k++)
                    ans += 2*(N[k-1][j]-(N[0][j]*N[k-2][j]))*N[i-k-1][j];
                
                ans += 2*(N[i-1][j]-(N[0][j]*N[i-2][j]));
            }
    
            return ans;
        }
        
       if((j+1) % 3 == 2)
       {
           long ans = 0;
           if(j > 1)
           {
               for(int k = 1; k < i; k++)
                   ans += 2*(N[(j-1)/3 - 1][3*i])*N[i-k-1][j];
               
               ans += 2*(N[(j-1)/3 - 1][3*i]);
           }
           if(j == 1)
           {
               for(int k = 1; k < i; k++)
                   ans += 2*N[i-k-1][j];
               
               ans += 2;
           }
          return ans;
       }
        
       if((j+1) % 3 == 0)
       {
           long ans = 0;
              
               ans += 2*(N[(j+1)/3 - 1][3*i]-N[(j+1)/3 - 1][3*i-1]);
               ans += 2*N[(j+1)/3 - 1][3*i-2]*N[(j+1)/3 - 1][1];
           
           for(int k = 1; 3*k+2 < 3*(i+1); k++)
              {
               ans += 2*N[(j+1)/3 - 1][3*(i+1)-(3*k+2)-1]*N[k-1][j-2];
               if(3*(i+1)-(3*k+2)-2 >= 0)
                    ans += 2*N[(j+1)/3 - 1][3*(i+1)-(3*k+2)-2]*N[k-1][j-2];
               if(3*(i+1)-(3*k+2)-1 == 0)
                   ans += 2*N[k-1][j-2];
               if(3*(i+1)-(3*k+2)-3 >= 0)
                   ans += 2*N[(j+1)/3 - 1][3*(i+1)-(3*k+2)-3]*N[k-1][j-2]*N[(j+1)/3 - 1][1];
               if(3*(i+1)-(3*k+2)-2 == 0)
                   ans += 2*N[k-1][j-2]*N[(j+1)/3 - 1][1];
              }
           
           return ans;
       }
        
        return -1;
    }
}
