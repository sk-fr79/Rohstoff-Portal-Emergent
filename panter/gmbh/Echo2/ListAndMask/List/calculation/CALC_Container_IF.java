package panter.gmbh.Echo2.ListAndMask.List.calculation;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2IF__BelongsToNavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public interface CALC_Container_IF extends E2IF__BelongsToNavigationList{

	public abstract Vector<CALC_Rule_ABSTRACT>		_GET_CALC_RULES()   	throws myException;
	
	/*
	 * falls popup, dann benutzt um die speicherung individueller fenstergroessen zu definieren
	 */
	public abstract String   						_GET_CALC_CLASS_MARKER();
	public abstract void   							_SET_CALC_CLASS_MARKER(String cCALC_CLASS_MARKER);
	
	public abstract void 							_SET_CALLING_CALC_BUTTON(CALC_Button oCALC_BUTTON);
	public abstract CALC_Button 					_GET_CALLING_CALC_BUTTON();
	
	public abstract MyString   						_GET_CONTAINER_TEXT4TITELBAR();
	public abstract void   							_SET_CONTAINER_TEXT4TITELBAR(MyE2_String cCONTAINER_TEXT4TITELBAR);
	public abstract MyString   						_GET_CONTAINER_HEADLINE();
	public abstract void   							_SET_CONTAINER_HEADLINE(MyE2_String cCONTAINER_HEADLINE);
	
	public abstract void 							_FILL_INTERNAL_Container() 	throws myException;
	public abstract void      						_SHOW_Container() 	throws myException;
	
	public abstract void   							_REGISTER_NAVILIST_TO_CALC_RULES(E2_NavigationList oNAVIGATIONLIST) 	throws myException;
	
}
