package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class WF_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3938671254149025283L;

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
//		String cID_USER_BESITZER = oUsedResultMAP.get_UnFormatedValue("ID_USER_BESITZER");
//		
//		MAP_USER oUser = new MAP_USER(cID_USER_BESITZER);
//		
//		String cAnzeige = S.NN(oUser.get_cUF_NAME()) + " (" + S.NN(oUser.get_cUF_VORNAME())+" "+S.NN(oUser.get_cUF_NAME1()) + ")";
//		Object o = oMAP.get__Comp("ID_USER_BESITZER");
//		if (o instanceof MyE2_DB_Label)
//		{
//			((MyE2_DB_Label)oMAP.get__Comp("ID_USER_BESITZER")).setText(cAnzeige);
//		} 
		
		String 		cIDColumn = null;
		RECORD_USER 	oUser = null;
		String 		cAnzeige = "";
		Object 		o = null;
		
		
		// BESITZER
		cIDColumn = oUsedResultMAP.get_UnFormatedValue("ID_USER_BESITZER");
		if (cIDColumn != null && !cIDColumn.trim().equals(""))
		{
			oUser = new RECORD_USER(cIDColumn);
			cAnzeige = S.NN(oUser.get_VORNAME_cUF()) + " "	+ S.NN(oUser.get_NAME1_cUF()) + " ("+ S.NN(oUser.get_NAME_cUF()) + ")";
			o = oMAP.get_hmRealComponents().get("ID_USER_BESITZER");

			if (o instanceof MyE2_DB_Label)
			{
				((MyE2_DB_Label) o).setText(cAnzeige);
			}
		}
		
		// Abgeschlossen von
		cAnzeige = "";
		cIDColumn = oUsedResultMAP.get_UnFormatedValue("ID_USER_BEARBEITER");
		if (cIDColumn != null && !cIDColumn.trim().equals(""))
		{
			oUser = new RECORD_USER(cIDColumn);
			cAnzeige = S.NN(oUser.get_VORNAME_cUF()) + " "
					+ S.NN(oUser.get_NAME1_cUF()) + " ("
					+ S.NN(oUser.get_NAME_cUF()) + ")";
			o = oMAP.get_hmRealComponents().get("ID_USER_BEARBEITER");

			if (o instanceof MyE2_DB_Label)
			{
				((MyE2_DB_Label) o).setText(cAnzeige);

			}
		}
		
		

		// Abgeschlossen von
		cAnzeige = "";
		cIDColumn = oUsedResultMAP.get_UnFormatedValue("ID_USER_ABGESCHLOSSEN_VON");
		if (cIDColumn != null && !cIDColumn.trim().equals(""))
		{
			oUser = new RECORD_USER(cIDColumn);
			cAnzeige = S.NN(oUser.get_VORNAME_cUF()) + " "
					+ S.NN(oUser.get_NAME1_cUF()) + " ("
					+ S.NN(oUser.get_NAME_cUF()) + ")";
			o = oMAP.get_hmRealComponents().get("ID_USER_ABGESCHLOSSEN_VON");

			if (o instanceof MyE2_DB_Label)
			{
				((MyE2_DB_Label) o).setText(cAnzeige);
			}
		}

		
		// Status
		cAnzeige = "";
		cIDColumn = oUsedResultMAP.get_UnFormatedValue("ID_LAUFZETTEL_STATUS");
		if (cIDColumn != null && !cIDColumn.trim().equals(""))
		{
			RECORD_LAUFZETTEL_STATUS oStatus = new RECORD_LAUFZETTEL_STATUS(cIDColumn);
			cAnzeige = S.NN(oStatus.get_STATUS_cF());
			o = oMAP.get_hmRealComponents().get("ID_LAUFZETTEL_STATUS");
			
			// anzeigen
			if (o instanceof MyE2_DB_Label)
			{
				((MyE2_DB_Label) o).setText(cAnzeige);

				// todo: eventuell mit einem farbigen Hintergrund belegen, für
				// eine deutlichere Sicht der Dinge
				// ((MyE2_DB_Label)o).setBackground(new Color(0xFFFFCC));
			}
		}
		
		
		// Prio
		cAnzeige = "";
		cIDColumn = oUsedResultMAP.get_UnFormatedValue("ID_LAUFZETTEL_PRIO");
		if (cIDColumn != null && !cIDColumn.trim().equals(""))
		{
			RECORD_LAUFZETTEL_PRIO oPrio = new RECORD_LAUFZETTEL_PRIO(cIDColumn);
			cAnzeige = S.NN(oPrio.get_PRIO_cF());
			o = oMAP.get_hmRealComponents().get("ID_LAUFZETTEL_PRIO");

			// anzeigen
			if (o instanceof MyE2_DB_Label)
			{
				((MyE2_DB_Label) o).setText(cAnzeige);
			}
			
		}

		
		
		// 2 Möglichkeiten: Prüfen, ob man Standalone ist
		// oder schauen, ob es den Button gibt...
		
		MyE2_ButtonInLIST oButtonDel = (MyE2_ButtonInLIST) oMAP
				.get_hmRealComponents().get(WF_CONST.HASH_DEL_BUTTON_SUBLIST);
		if (oButtonDel != null)
		{
			// dem loeschbutton die id mitteilen
			String cID_LAUFZETTEL_EINTRAG = oUsedResultMAP.get_cUNFormatedROW_ID();
			oButtonDel.EXT().set_C_MERKMAL(cID_LAUFZETTEL_EINTRAG);
		}
		
		
	}

}
