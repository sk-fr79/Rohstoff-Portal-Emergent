package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_Component_MASK_CROSS_ADRESSAUSSTATTUNG extends	MyE2_DBC_CrossConnection
{

	public FS_Component_MASK_CROSS_ADRESSAUSSTATTUNG(SQLFieldMAP osqlFieldMAP) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFieldMAP.get("ID_ADRESSE"),
				"JT_ADRESSAUSSTATT", 
				"JT_ADRESSAUSSTATT_DEF", 
				"ID_ADRESSAUSSTATT",
				"ID_ADRESSE", 
				"ID_ADRESSAUSSTATT_DEF",
				"ID_ADRESSAUSSTATT_DEF", 
				"JT_ADRESSAUSSTATT_DEF.KURZBEZEICHNUNG",
				null, 
				null,
				null, 
				"JT_ADRESSAUSSTATT_DEF.KURZBEZEICHNUNG", 
				4,
				new E2_FontPlain(), new MyE2_String("Fehler bei der Zuordnung der Adress-Ausstattung!"), MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL);
	}

}
