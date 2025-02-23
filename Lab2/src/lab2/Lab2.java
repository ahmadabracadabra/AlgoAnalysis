package lab2;

import java.util.Scanner;

/**
 * Name: Ahmad Abraham
 * In this lab, I created methods that print out the Kettle Soup song and Christmas Song based on number entered for input.
 * Additionally, the exact number of lines printed by each program is calculate using a formula in terms of n.
 */

public class Lab2 {

    public static void main(String[] args) {
        //input
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a number for n: ");
        int n = scnr.nextInt();
        
        //Part 1
        KettleSoupSong(n);
        
        System.out.println();
  
        //Part 2
         String[] myGifts = {"a partridge in a pear tree", "two turtle doves" , "three French hens", "four calling birds", "five golden rings", 
            "six geese a-laying", "seven swans a-swimming", "eight maids a-milking" , "nine ladies dancing", "ten lords a-leaping", "eleven pipers piping", "twelve drummers drumming", 
          }; 
        ChristmasSong(n, myGifts); 
        
        //Part 3
        //O(n)
        System.out.println(4 * n + " lines printed in the Kettle Soup Song");
        //O(n^2)
        if (n > 12) {
            n = 12;
        } 
        System.out.println(((n * n) + (5 * n)) / 2 + " lines printed in the 12 Days of Christmas Song");
             
    } // end main
    
    public static void KettleSoupSong(int n) {
     for (int i = n; i > 0; i--) {
            String kettles;
            if (i == 1) {
                kettles = " kettle";
            } else {
                kettles = " kettles";
            }
            
            System.out.println(i + kettles + " of soup on the heat.");
            System.out.println(i + kettles + " of soup!");
            System.out.println("If one of these kettles, I happen to eat,");
            
            if (i - 1 == 1) {
                kettles = " kettle";
            } else {
                kettles = " kettles";
            }
            
            
                System.out.println((i - 1) + kettles + " of soup on the heat.\n");
            
        }     
    } // end soupy
    
   //if the number is higher than 12, it will just output the entire 12 days 
   public static void ChristmasSong(int n, String[] myGifts) {
       if (n < 1) {
        return;
       }
       
       if (n > myGifts.length) {
        n = myGifts.length;
       }

        for (int day = 1; day <= n; day++) {
          
        System.out.println("On the " + day + " day of Christmas,");
        System.out.println("my true love gave to me");


        for (int i = day - 1; i >= 0; i--) {
            if (i == 0 && day > 1) { 
                System.out.println("and " + myGifts[i]);
            } else { 
                System.out.println(myGifts[i]);
            }
        }
            System.out.println("");
    }
} // end christmas
     
    
} // end class
