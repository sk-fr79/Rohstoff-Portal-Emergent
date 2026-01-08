package panter.gmbh.Echo2.components.DB;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;


public interface MyE2IF__ComponentContainer
{
	public Vector<MyE2IF__Component> get_vComponents();
	
	
	/**
	 * @return s Vector with all components of typ MyE2IF_DB_Component
	 */
	public Vector<MyE2IF__DB_Component>			get_vDB_Components();
	public HashMap<String,MyE2IF__Component> 	get_hmComponents();
	public MyE2IF__Component   					add_Component(MyE2IF__Component oComp, MyString cTitleForMaskOrList, String cHASH_KEY_FOR_NON_DB_FIELDS) throws myException;
	public MyE2IF__Component  					get_ReducedComponent(); 
	
	public MyE2IF__Component                    get_ListHeaderComponent(E2_NavigationList oList) throws myException;
}
