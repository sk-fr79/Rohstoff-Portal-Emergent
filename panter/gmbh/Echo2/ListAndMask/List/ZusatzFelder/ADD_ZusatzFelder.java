package panter.gmbh.Echo2.ListAndMask.List.ZusatzFelder;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LISTEN_ZUSATZFELDER;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField_OnlyInQueryWhenVisible;
import panter.gmbh.indep.exceptions.myException;

public class ADD_ZusatzFelder
{
	private E2_ComponentMAP  oComponentMapToExtent = null;
	private String           cMODUL_KENNER = null;
	
	
	public ADD_ZusatzFelder(E2_ComponentMAP o_ComponentMapToExtent, String c_MODUL_KENNER) throws myException
	{
		super();
		this.oComponentMapToExtent = 	o_ComponentMapToExtent;
		this.cMODUL_KENNER = 			c_MODUL_KENNER;
		
		RECLIST_LISTENZUSATZFELDER_SPEC rlZUSATZ = new RECLIST_LISTENZUSATZFELDER_SPEC(this.cMODUL_KENNER,true);
		
		SQLFieldMAP  oFieldMap = this.oComponentMapToExtent.get_oSQLFieldMAP();
		
		for (int i=0;i<rlZUSATZ.get_vKeyValues().size();i++)
		{
			//zuerst die fieldmap bedienen
			RECORD_LISTEN_ZUSATZFELDER recLF = rlZUSATZ.get(i);
			oFieldMap.add_SQLField(new SQLField_OnlyInQueryWhenVisible("("+recLF.get_SUBQUERY_cUF()+")", recLF.get_FIELDNAME_cUF(), new MyE2_String(recLF.get_FIELDNAME_cUF(),false), o_ComponentMapToExtent), false);
		}

		//die liste muss neu initialisiert werden
		oFieldMap.initFields();
		
		
		//dann die ComponentMAP
		for (int i=0;i<rlZUSATZ.get_vKeyValues().size();i++)
		{
			RECORD_LISTEN_ZUSATZFELDER recLF = rlZUSATZ.get(i);
			MyE2_DB_Label_INROW  oLabel = new MyE2_DB_Label_INROW(oFieldMap.get_(recLF.get_FIELDNAME_cUF()));
			oLabel.EXT().set_ToolTipStringForListHeader(new MyE2_String(recLF.get_TOOLTIP_cUF_NN("")));
			o_ComponentMapToExtent.add_Component(oLabel,new MyE2_String(recLF.get_BESCHRIFTUNG_LISTE_cUF_NN("")));
		}
		
		//dann sortieren
		for (int i=0;i<rlZUSATZ.get_vKeyValues().size();i++)
		{
			RECORD_LISTEN_ZUSATZFELDER recLF = rlZUSATZ.get(i);
			o_ComponentMapToExtent.move_Row(recLF.get_FIELDNAME_cUF(), recLF.get_SPALTENPOSITION_lValue(new Long(100)));
		}
		
	}
	
	
	
}
