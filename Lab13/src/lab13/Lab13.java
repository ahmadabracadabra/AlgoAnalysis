
package lab13;

/**
 * Name: Ahmad Abraham
 * Class: CMPSC 351 01
 * Lab 13: Backtracking
 */
public class Lab13 {

    public static void main(String[] args) {
        
        /* 
        RECURRENCE:
        W(1) = false
        W(2) = true
        W(3) = true
        W(4) = true
        W(5) = false
        W(n) ={ W(n-1) = false || W(n-2) = false || W(n-3) = false
        
        PATTERN: If n%4==1 then W(n) is false, otherwise its true
           "false true true true" keeps repeating
        */
        
        for (int i = 1; i <= 12; i++) {
            System.out.println("Drops: " + i + " = " + winning(i));
        }
        
    }
    
    //poison drop game
     static boolean winning(int drops) {
         if(drops == 1) {
            return false;
        }    
         if(drops == 2 ) {
            return true;
        }
          if(drops == 3) {
            return true;
        }
           if(drops == 4) {
            return true;
        }
        return !(winning(drops - 1) && winning(drops - 2) && winning(drops - 3));
    }
}
