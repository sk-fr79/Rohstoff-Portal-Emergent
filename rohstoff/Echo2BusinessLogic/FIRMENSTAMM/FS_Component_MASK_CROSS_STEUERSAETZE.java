package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_Component_MASK_CROSS_STEUERSAETZE extends	MyE2_DBC_CrossConnection
{

	public FS_Component_MASK_CROSS_STEUERSAETZE(SQLFieldMAP osqlFieldMAP) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFieldMAP.get("ID_ADRESSE"),
				"JT_KUNDE_MWST", 
				"JT_MWSTSCHLUESSEL", 
				"ID_KUNDE_MWST",
				"ID_ADRESSE", 
				"ID_MWSTSCHLUESSEL",
				"ID_MWSTSCHLUESSEL", 
				" JT_MWSTSCHLUESSEL.KURZBEZEICHNUNG||' ('||trim(to_char(JT_MWSTSCHLUESSEL.STEUERSATZ))||'%)'",
				null, 
				null,
				null, 
				null, 
				3,
				new E2_FontPlain(), new MyE2_String("Fehler bei der Zuordnung des Mehrwertsteuer-Schlüssels!"), MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL);
	}

}
