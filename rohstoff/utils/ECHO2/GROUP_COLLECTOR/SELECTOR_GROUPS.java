package rohstoff.utils.ECHO2.GROUP_COLLECTOR;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class SELECTOR_GROUPS extends XX_ListSelektor
{
	private String cNAME_OF_TABLE = null;
	private String cPRIMARY_KEY_OF_TABLE = null;
	private String cMODULE_KENNER = null;
	
	private MyE2_SelectField	oSelectField = null;
	
	
	/*
	 * selektor-kompoente fuer die gruppen
	 */
	public SELECTOR_GROUPS(String NAME_OF_TABLE ,String PRIMARY_KEY_OF_TABLE, String MODULE_KENNER) throws myException
	{
		super();
		this.cNAME_OF_TABLE = 			NAME_OF_TABLE;
		this.cPRIMARY_KEY_OF_TABLE = 	PRIMARY_KEY_OF_TABLE;
		this.cMODULE_KENNER = 			MODULE_KENNER;
		
		this.oSelectField = new MyE2_SelectField("SELECT MENUETEXT,ID_COLLECTION_DEF FROM "+bibE2.cTO()+".JT_COLLECTION_DEF WHERE MODULE_KENNER="+bibALL.MakeSql(this.cMODULE_KENNER),
												false,true,false,true);
		
		this.oSelectField.setToolTipText(new MyE2_String("Auswahl der benutzerdefinierten Gruppen").CTrans());
		
	}

	public String get_WhereBlock() throws myException
	{
		String cRueck = "";
		if ( !this.oSelectField.get_ActualWert().trim().equals(""))
		{
			cRueck = this.cNAME_OF_TABLE+"."+this.cPRIMARY_KEY_OF_TABLE+" IN (SELECT ID_TABLE FROM "+bibE2.cTO()+".JT_COLLECTIONS WHERE ID_COLLECTION_DEF="+this.oSelectField.get_ActualWert().trim()+")";
		}
		return " "+cRueck+" ";
	}

	public Component get_oComponentForSelection()
	{
		return this.oSelectField;
	}

	
	public Component get_oComponentWithoutText()
	{
		return this.oSelectField;
	}


	public void doInternalCheck()
	{
	}

	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) 
	{
		this.oSelectField.add_oActionAgent(oAgent);
	}

}
