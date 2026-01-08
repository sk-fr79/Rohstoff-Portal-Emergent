package panter.gmbh.Echo2.__BASIC_COMPONENTS.ORDER_POPUP;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public interface OP_IF_SortObject {

	public Component   		get_SortRepresenationInList() throws myException;
	public MyE2_Button   	generate_SortUpButton() throws myException;
	public MyE2_Button   	generate_SortDownButton() throws myException;
	
	public MyE2_MessageVector store_Sort_position()  throws myException;
	
	public void             connect_with_sort_container(OP_BasicSortContainer op_basic) throws myException;
	
	public void             mark_object() throws myException;
	public void             unmark_object() throws myException;
	
}
