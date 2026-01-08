package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.VERSION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.exceptions.myException;

public class HAD__GridBedienPanelSorter extends MyE2_SelectField  implements IF_Saveable {
	
	//hashmap definiert den zusammenhang zwischen sichtbarem text und key
	private HashMap<String, String> hm_key_to_SQL_WhereBlock = new HashMap<String, String>();
	
	//hashmap definiert den zusammenhang zwischen key und sortier-statement
	private HashMap<String, String> hm_text_to_value = new HashMap<String, String>();

	public HAD__GridBedienPanelSorter() throws myException {
		super();
		
		hm_text_to_value.put("1", new MyE2_String("Modul/Titel").CTrans());
		hm_text_to_value.put("2", new MyE2_String("Modul/Version").CTrans());
		hm_text_to_value.put("3", new MyE2_String("Modul/Version/Titel").CTrans());
		hm_text_to_value.put("4", new MyE2_String("Titel").CTrans());
		hm_text_to_value.put("5", new MyE2_String("Version/Ticket/Titel").CTrans());
		
		hm_key_to_SQL_WhereBlock.put("1", HILFETEXT.modulkenner.tnfn()+","+HILFETEXT.titel.tnfn());
		hm_key_to_SQL_WhereBlock.put("2", HILFETEXT.modulkenner.tnfn()+","+VERSION.version_code.tnfn());
		hm_key_to_SQL_WhereBlock.put("3", HILFETEXT.modulkenner.tnfn()+","+VERSION.version_code.tnfn()+","+HILFETEXT.titel.tnfn());
		hm_key_to_SQL_WhereBlock.put("4", HILFETEXT.titel.tnfn());
		hm_key_to_SQL_WhereBlock.put("5", VERSION.version_code.tnfn()+","+HILFETEXT.ticketnummer.tnfn()+","+HILFETEXT.titel.tnfn());

		String[][] array4dd = bibARR.get_Array_ValueKey(hm_text_to_value, null);
		
		this.set_ListenInhalt(array4dd, false);
		this.set_ActiveValue("1");
		
		this.setToolTipText(new MyE2_String("Sortierung der Liste festlegen ...").CTrans());
	}
	
	@Override
	public String get_value_to_save() throws myException {
		return S.NN(this.get_ActualWert());
	}

	@Override
	public void restore_value(String value) throws myException {
		this.set_ActiveValue_OR_FirstValue(value);
		
	}

	@Override
	public void set_component_to_status_not_saved() throws myException {
		this.set_ActiveValue_OR_FirstValue("");
	}

	@Override
	public Component get_Comp() throws myException {
		return this;
	}

	@Override
	public void add_action(XX_ActionAgent agent) throws myException {
		this.add_oActionAgent(agent);
	}

	
	public String get_SortString() throws myException {
		String s_sort = "";
		String key = this.get_ActualWert();
	
		if (S.isFull(key)) {
			String sort = this.hm_key_to_SQL_WhereBlock.get(key);
			if (S.isFull(sort)) {
				s_sort = sort;
			}
		}
		return s_sort;
	}
	
}
