
package lab18;

import bridges.connect.Bridges;

/**
 * @author ahmadabraham
 */
public class Lab18 {

    public static void main(String[] args) {
        //Create the BRIDGES object with credentials.
        //  If you are doing a BRIDGES assignment, use the assignment number. 
        //  Otherwise, use any number.
        //  Use your user name and API key from your BRIDGES registation.
        Bridges bridges = new Bridges(77329819, "abrahama02", "1070568054913"); //do not commit your API key!
        
        try 
        {
            //Run the assignment code in a separate .java file, 
            //  so you don't have to commit this one.
            //BridgesApp app = new BridgesApp();
            //GraphSearch app = new GraphSearch();
            GraphEQ app = new GraphEQ();
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
