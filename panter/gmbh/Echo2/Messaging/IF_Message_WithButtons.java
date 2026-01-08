package panter.gmbh.Echo2.Messaging;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;

public interface IF_Message_WithButtons {
	
	public Component    						get_ComponentWithMessageAndButtons(MyE2_Message oMessage);
	public Insets  		 						get_Insets_4_MessageInMessageGrid(MyE2_Message oMessage);

	public Color        						get_Color_4_MessageBackground(MyE2_Message oMessage);
	
	public Vector<E2_IF_Handles_ActionAgents>   get_vActiveComponents(MyE2_Message oMessage);
	public void         						disable_ActiveComponents(MyE2_Message oMessage) throws myException;
	public void         						enable_ActiveComponents(MyE2_Message oMessage) throws myException;

}
