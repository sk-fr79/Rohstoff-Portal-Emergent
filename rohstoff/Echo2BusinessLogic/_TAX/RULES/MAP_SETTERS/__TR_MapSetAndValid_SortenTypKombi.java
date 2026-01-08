package rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * validierer, soll ausschliessen, dass ein merkmal produkt, dienstleistung oder end of waste gemeinsam besetzt wird
 * @author martin
 *
 */
public class __TR_MapSetAndValid_SortenTypKombi extends XX_MAP_Set_And_Valid {

	private boolean bQuelle = false;
	
	
	
	public __TR_MapSetAndValid_SortenTypKombi(boolean quelle) {
		super();
		this.bQuelle = quelle;
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return intern_Check(oMAP, ActionType, oExecInfo, oInputMap,false);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return intern_Check(oMAP, ActionType, oExecInfo, oInputMap,false);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return intern_Check(oMAP, ActionType, oExecInfo, oInputMap,false);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return intern_Check(oMAP, ActionType, oExecInfo, oInputMap,false);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return intern_Check(oMAP, ActionType, oExecInfo, oInputMap,false);
	}

	
	private MyE2_MessageVector intern_Check(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap, boolean bNew) throws myException { 
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		Vector<String>  vFehlerMeldung = new Vector<String>();
		
		boolean bProdukt = false;
		boolean bEndOfWaste = false;
		boolean bDienstleitung = false;
		boolean bRC = false;
		
		if (this.bQuelle) {
			bProdukt = 			oMAP.get_LActualDBValue(_DB.HANDELSDEF$SORTE_PRODUKT_QUELLE,-1l,-1l).intValue()==1;
			bEndOfWaste = 		oMAP.get_LActualDBValue(_DB.HANDELSDEF$SORTE_EOW_QUELLE,-1l,-1l).intValue()==1;
			bDienstleitung = 	oMAP.get_LActualDBValue(_DB.HANDELSDEF$SORTE_DIENSTLEIST_QUELLE,-1l,-1l).intValue()==1;
			bRC = 				oMAP.get_LActualDBValue(_DB.HANDELSDEF$SORTE_RC_QUELLE,-1l,-1l).intValue()==1;
			
		} else {
			bProdukt = 			oMAP.get_LActualDBValue(_DB.HANDELSDEF$SORTE_PRODUKT_ZIEL,-1l,-1l).intValue()==1;
			bEndOfWaste = 		oMAP.get_LActualDBValue(_DB.HANDELSDEF$SORTE_EOW_ZIEL,-1l,-1l).intValue()==1;
			bDienstleitung = 	oMAP.get_LActualDBValue(_DB.HANDELSDEF$SORTE_DIENSTLEIST_ZIEL,-1l,-1l).intValue()==1;
			bRC = 				oMAP.get_LActualDBValue(_DB.HANDELSDEF$SORTE_RC_ZIEL,-1l,-1l).intValue()==1;
		}
		
		if (bProdukt && bDienstleitung) { vFehlerMeldung.add("Eine Sorte kann nur Produkt, Dienstleistung oder \"End of Waste\" sein, keine Kombination !");}
		if (bProdukt && bEndOfWaste) { vFehlerMeldung.add("Eine Sorte kann nur Produkt, Dienstleistung oder \"End of Waste\" sein, keine Kombination !");}
		if (bDienstleitung && bEndOfWaste) { vFehlerMeldung.add("Eine Sorte kann nur Produkt, Dienstleistung oder \"End of Waste\" sein, keine Kombination !");}

		//2015-09-17: die kombination rc und dienstleistung ist ausgeschlossen
		if (bDienstleitung && bRC) { vFehlerMeldung.add("Eine Sorte kann nicht gleichzeitig RC und Dienstleistung sein !");}
		

		//im maskenklick/speichern eine Fehlermeldung
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION || 
			ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_UNBOUND_KLICK_ACTION ||
			ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
			for (String cMeldung: vFehlerMeldung) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cMeldung)));
			}
		} else {
			//beim laden nur eine warnung
			for (String cMeldung: vFehlerMeldung) {
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String(cMeldung)));
			}
			
		}
		
		return oMV;
	}

	
	
	
}
