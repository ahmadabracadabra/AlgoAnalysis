package lab9;

import bridges.connect.Bridges;
import bridges.base.SLelement;
import java.util.Iterator;

//
// This tutorial creates a set of singly linked list elements, links them
// and displays them
//
// Reference: SLelement, Bridges classes
//

public class Lab9 {

	public static void main(String[] args) throws Exception {

		// Create Bridges object,  fill in credentials
		Bridges bridges = new Bridges(9, "abrahama02",
			"1070568054913");
		// set title
		bridges.setTitle("A Single Linked List Example");

		// set  description
		bridges.setDescription("A singly linked list of 5 nodes with names; the nodes in this example use string as the generic type");

		// create the linked list elements with student names
		SLelement<String>  st0 = new SLelement<String> ("Gretel Chaney");
		SLelement<String>  st1 = new SLelement<String> ("Lamont Kyler");
		SLelement<String>  st2 = new SLelement<String> ("Gladys Serino");
		SLelement<String>  st3 = new SLelement<String> ("Karol Soderman");
		SLelement<String>  st4 = new SLelement<String> ("Starr McGinn");
                SLelement<String>  st5 = new SLelement<String> ("Ahmad Abraham");

		//  link the elements
		st0.setNext(st1);
		st1.setNext(st2);
		st2.setNext(st3);
		st3.setNext(st4);
                st4.setNext(st5);

		// we want to see these names in the visualization so we will set them as
		// the nodes' labels. We will retrieve the nodes' generic data for the label
		st0.setLabel(st0.getValue());
		st1.setLabel(st1.getValue());
		st2.setLabel(st2.getValue());
		st3.setLabel(st3.getValue());
		st4.setLabel(st4.getValue());
                st5.setLabel(st5.getValue());

		// tell Bridges the head of the list
		bridges.setDataStructure(st0);

		// visualize the linked list (hit the key 'l' on the visualzation to see all labels
		bridges.visualize();
	}
}