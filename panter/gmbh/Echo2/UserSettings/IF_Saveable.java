package panter.gmbh.Echo2.UserSettings;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.exceptions.myException;

public interface IF_Saveable {

	//interface um beliebige komponenten einfach speicherbar zu machen
	public String 		get_value_to_save() throws myException;
	public void   		restore_value(String value) throws myException;
	public void   		set_component_to_status_not_saved() throws myException;
	public Component  	get_Comp() throws myException;
	public void     	add_action(XX_ActionAgent agent) throws myException;
	
	
}
