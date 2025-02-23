
package lab11;

import bridges.connect.Bridges;
/**
 * Base Code downloaded from Assignment Description
 * Modified by Ahmad Abraham, 18 February 2025
 * Lab 11: Geometry Problems
 * CMPSC 351-01
 */
public class Lab11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         //Create the BRIDGES object with credentials.
        //  If you are doing a BRIDGES assignment, use the assignment number. 
        //  Otherwise, use any number.
        //  Use your user name and API key from your BRIDGES registation.
        Bridges bridges = new Bridges(77329811, "abrahama02", "1070568054913"); //do not commit your API key!
        
        try 
        {
            //Run the assignment code in a separate .java file, 
            //  so you don't have to commit this one.
            //BridgesApp app = new BridgesApp();
            //BridgesAppUSCities app = new BridgesAppUSCities();
            BridgesAppChicago app = new BridgesAppChicago();
            app.run(bridges);

            //Activate the visualization on the server  
            bridges.visualize();
        } 
        catch (java.io.IOException | bridges.validation.RateLimitException e) 
        {
            System.err.println(e);
        }
    }
    
}
