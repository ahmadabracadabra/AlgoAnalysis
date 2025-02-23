package divideandconquer;

/**
 *
 * @author ahmadabraham
 */
public class DivideAndConquer {

    public static void main(String[] args) {
        // TODO code application logic here
        int[] data = {12,31,24,43,10,56,45,78,22,89,66,44,22,11,45,23,19,18,102};
        int[] easyCheck = {2,1,4};
        int[] easyCheckEven = {2,1,4,3};
        int[] testOne = {13};
        int[] testZero = {};
        
        System.out.println(sum(data));
         System.out.println(sum(easyCheck));
          System.out.println(sum(easyCheckEven));
          System.out.println(sum(testOne));
          
          try 
          {
         sum(testZero);
          }
          catch (Exception e) {
              System.out.println(e);
          }
    }
    public static int sum(int[] array) {
        return sdSum(array,0,array.length-1);
    }
    
    
    private static int sdSum(int[] array, int start, int end) {
        if (end < start) {
            throw new IllegalArgumentException("Array is EMPTY");
        }
        if(start==end) {
            return array[end]; 
        }
          return sdSum(array,start,end-1) + array[end];
    }
    
    private static int dcSum(int[] array, int start, int end) {
          if (end < start) {
            throw new IllegalArgumentException("Array is EMPTY");
        }
        if(start==end) {
            return array[end]; 
        }
        int mid = (start+end)/2;
        return dcSum(array,start,mid) + dcSum(array,mid+1,end);
    }
    
    
}
