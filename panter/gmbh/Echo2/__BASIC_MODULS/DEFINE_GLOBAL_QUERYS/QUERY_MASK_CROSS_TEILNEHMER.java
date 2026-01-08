package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_MASK_CROSS_TEILNEHMER extends	MyE2_DBC_CrossConnection
{

	public QUERY_MASK_CROSS_TEILNEHMER(SQLFieldMAP osqlFieldMAP) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFieldMAP.get("ID_QUERY"),	
				"JT_QUERY_TEILNEHMER", 
				"JD_USER", 
				"ID_QUERY_TEILNEHMER",
				"ID_QUERY", 
				"ID_USER",
				"ID_USER", 
//				"  NVL(JD_USER.NAME,'-')||' ('|| NVL(JD_USER.KUERZEL,'-')||')'",
				"  NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') || ' ('||  NVL(KUERZEL,'?')||')'",
				null, 
				null,
				bibALL.get_Vector("JD_USER.ID_MANDANT="+bibALL.get_ID_MANDANT()), 
//				"  NVL(JD_USER.NAME,'-')||' ('|| NVL(JD_USER.KUERZEL,'-')||')'",
				" NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') ",
				6,
				new E2_FontPlain(-2), new MyE2_String("Fehler bei der Anordnung der Teilnehmer!"), MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL);
		
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
	}

}
