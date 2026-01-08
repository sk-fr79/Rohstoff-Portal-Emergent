package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class BSK_P_MASK__ComponentMAP_builder_AddOnSQL_Protokoll_Mengenaenderung implements E2_ComponentMAP.builder_AddOnSQL_STATEMENTS
{

	@Override
	public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(	E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP, MyE2_MessageVector oMV)	throws myException
	{
		return null;
	}


	@Override
	public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(	E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP, MyE2_MessageVector oMV)	throws myException
	{
		
		Vector<String> vRueck = new Vector<String>();
		
		if (componentMAP.get__DBComp("ANZAHL").EXT_DB().get_bActualMaskValueHasChanged() || 
			componentMAP.get__DBComp("EINZELPREIS").EXT_DB().get_bActualMaskValueHasChanged())
		{
			String cANZAHL = "NULL";
			Double dAnzahl =componentMAP.get__DBComp("ANZAHL").EXT_DB().get_D_LAST_ActualMaskValue(null,null);
			if (dAnzahl != null)
			{
				cANZAHL = MyNumberFormater.formatDezForDATABASE(bibALL.Round(dAnzahl,3), 3);
			}
			
			String cEPREIS = "NULL";
			Double dEPREIS = componentMAP.get__DBComp("EINZELPREIS").EXT_DB().get_D_LAST_ActualMaskValue(null,null);
			if (dEPREIS != null)
			{
				cEPREIS = MyNumberFormater.formatDezForDATABASE(bibALL.Round(dEPREIS,3), 3);
			}
		
			MySqlStatementBuilder  stmtAddChange = new MySqlStatementBuilder();
			stmtAddChange.addSQL_Paar("ID_VPOS_KON_AENDERUNGEN", 	"SEQ_VPOS_KON_AENDERUNGEN.NEXTVAL", false);
			stmtAddChange.addSQL_Paar("ID_VPOS_KON", 				componentMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID(), false);
			stmtAddChange.addSQL_Paar("ANZAHL", 					cANZAHL , false);
			stmtAddChange.addSQL_Paar("EINZELPREIS", 				cEPREIS , false);
			stmtAddChange.addSQL_Paar("DATUM_AENDERUNG", 			"SYSDATE" , false);
			stmtAddChange.addSQL_Paar("UHRZEIT_AENDERUNG", 			"TO_CHAR(SYSDATE,'HH24:MI')" , false);
			stmtAddChange.addSQL_Paar("KUERZEL_AENDERUNG", 			bibALL.get_KUERZEL() , true);

			vRueck.add(stmtAddChange.get_CompleteInsertString("JT_VPOS_KON_AENDERUNGEN", bibE2.cTO()));
		}
		
		return vRueck;
	}

}
