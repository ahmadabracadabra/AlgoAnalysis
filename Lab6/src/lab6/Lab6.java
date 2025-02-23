
package lab6;

import java.util.Scanner;

/**
 *
 * Name: Ahmad Abraham
 * Class: CMPSC-351-01
 * Lab 6: Recursion
 * In this program, I am writing recursive methods to the problems in the Lab which are the number of Occurrences in an array,
 * the minimum value in an array, and the amount of chocolate Little Bobby can eat
 */
public class Lab6 {

    public static void main(String[] args) {
       Scanner scnr = new Scanner(System.in);
       Double[] myList = {5.5,7.8,37.2,15.8,9.2,7.89,21.2,5.5,37.2,9.2,9.2,0.1828};
        Double[] EmptyList = {};
        System.out.println("Enter a number for the target(only one problem uses this):");
       Double target = scnr.nextDouble();
        System.out.println();
       //TAIL RECURSION OCCURRENCES
        System.out.println(target + " appears " + numOccurrence(myList,target,0, 0) + " times");  
        //MINIMUM VALUE RECURSION
        System.out.println("The minimum value in the list is: " + minVal(myList,0));
        System.out.println("The minimum value in the empty list is: " + minVal(EmptyList,0));
        //CHOCOLATE FEAST
        System.out.println("Little Bobby ate " + chocolateFeast(4,1,2) + " pieces of chocolate");
       
        
    }
    
    //TAIL RECURSION OCCURRENCE NUMBER
     public static int numOccurrence(Double[] myList, double target, int index, int accumulator)
    {
// Did it without recursion first (ignore this part)       
//       for (int i = 0; i < myList.length; i++) {
//           if (myList[i] == target )
//               accumulator++;
//       }
//     return accumulator;
        if (index == myList.length) {
            return accumulator;
        }

        if (myList[index] == target) {
            return numOccurrence(myList, target, index + 1, accumulator + 1);
        } 
        else {
            return numOccurrence(myList, target, index + 1, accumulator);
        }
    }
    
     //MINIMUM VALUE
       public static Double minVal(Double[] myList, int index) {
          if (myList == null || myList.length == 0) {
            return null;
        }
          
        if (index == myList.length - 1) {
            return myList[index];
        }
        
        return Math.min(myList[index], minVal(myList, index + 1));
    }
     
     
     //CHOCOLATE FEAST
     public static int chocolateFeast(int n, int d, int w) {
        if (n < d) {
            return 0;
        }

        int chocoBought = 0;
        while (n >= d) {
            n = n - d; 
            chocoBought = chocoBought + 1; 
        }
        int wrappers = chocoBought;
      
        int chocoTraded = 0;
        while (wrappers >= w) {
            wrappers = wrappers - w;
            chocoTraded = chocoTraded + 1; 
            wrappers = wrappers + 1; 
        }

        return chocoBought + chocoTraded + chocolateFeast(n, d, w);
    }
     
}