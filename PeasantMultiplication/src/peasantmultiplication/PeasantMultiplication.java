
package peasantmultiplication;

import java.util.Scanner;

/**
 * @author ahmadabraham
 * PeasantMultiply(n, y):
prod ← 0
while n > 0
if n is odd
prod ← prod + m
n ← n/2
m ← m + m
return prod
 */
public class PeasantMultiplication {
        
    public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
        int n = scnr.nextInt();
        int m = scnr.nextInt();    

        System.out.println(PeasantMultiply(n,m));
    }//main
    
    
    public static int PeasantMultiply(int n, int m) {
         int prod = 0;
        
        while (n > 0) { 
            if ((n & 1) == 1) { 
                prod = prod + m;
            }
            
            n >>= 1; 
            m = m + m; 
        }
        
        return prod;
    } //end peasant
        
} //end class  
    
    
    

