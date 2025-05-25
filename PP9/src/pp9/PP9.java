package pp9;

import bridges.connect.Bridges;
/**
 * Name; Ahmad Abraham
 * PP 9: Geometry Problems: Convex Hull of US Cities
 * CMPSC-351-01
 * @author ahmadabraham
 */
public class PP9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         //Create the BRIDGES object with credentials.
        //  If you are doing a BRIDGES assignment, use the assignment number. 
        //  Otherwise, use any number.
        //  Use your user name and API key from your BRIDGES registation.
        Bridges bridges = new Bridges(9, "abrahama02", "1070568054913"); //do not commit your API key!
        
        try 
        {
            //Run the assignment code in a separate .java file, 
            //  so you don't have to commit this one.
            BridgesAppUSCities app = new BridgesAppUSCities();
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
