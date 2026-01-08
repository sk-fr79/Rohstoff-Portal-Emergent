package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class AS_MASK_BEZ_LIST_CROSS_STEUERSAETZE extends	MyE2_DBC_CrossConnection
{

	public AS_MASK_BEZ_LIST_CROSS_STEUERSAETZE(SQLFieldMAP osqlFieldMAP) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFieldMAP.get("ID_ARTIKEL_BEZ"),
				"JT_ARTIKEL_BEZ_MWST", 
				"JT_MWSTSCHLUESSEL", 
				"ID_ARTIKEL_BEZ_MWST",
				"ID_ARTIKEL_BEZ", 
				"ID_MWSTSCHLUESSEL",
				"ID_MWSTSCHLUESSEL", 
				"trim(to_char(JT_MWSTSCHLUESSEL.STEUERSATZ))||'%'",
				null, 
				null,
				null, 
				null, 
				3,
				new E2_FontPlain(-2), new MyE2_String("Fehler bei der Zuordnung des Mehrwertsteuer-Schlüssels!"), MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL);
	}

}
