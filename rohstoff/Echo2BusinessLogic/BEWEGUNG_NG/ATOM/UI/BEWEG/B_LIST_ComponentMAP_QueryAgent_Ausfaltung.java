package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.UI.BEWEG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_BEWEGUNG_ATOM_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_BEWEGUNG_VEKTOR_spec;

public class B_LIST_ComponentMAP_QueryAgent_Ausfaltung extends XX_ComponentMAP_SubqueryAGENT {

	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException {
	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) 	throws myException {
		
		B_LIST_ComponentMAP oBMap = (B_LIST_ComponentMAP)oMAP;
		
		RECLIST_BEWEGUNG_VEKTOR  rlVect = oBMap.get_recBewegung().get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_bewegung(
				"NVL("+_DB.BEWEGUNG_VEKTOR$DELETED+",'N')='N'",_DB.BEWEGUNG_VEKTOR$ID_BEWEGUNG_VEKTOR,true);
		
		/*
		 * ein array nimmt fuer jeden vector die information auf, um die bewegung in der liste darzustellen
		 */
		String[][] arrTexte = new String[rlVect.get_vKeyValues().size()][3];
		
		//initialisieren
		for (int i=0;i<arrTexte.length;i++) {arrTexte[i][0]="";arrTexte[i][1]="";arrTexte[i][2]="";}
		
		for (int i=0;i<rlVect.get_vKeyValues().size();i++) {
			RECORD_BEWEGUNG_VEKTOR_spec recV = new RECORD_BEWEGUNG_VEKTOR_spec(rlVect.get(i));
			
			RECORD_BEWEGUNG_ATOM[]  recAtomStartEnde = recV.get_FirstAndLastAtom();
			
			if (recAtomStartEnde[0]!=null && recAtomStartEnde[1]!=null) {
				RECORD_BEWEGUNG_ATOM_ext  recAtomExt_first = 	new RECORD_BEWEGUNG_ATOM_ext(recAtomStartEnde[0]);
				RECORD_BEWEGUNG_ATOM_ext  recAtomExt_last = 	new RECORD_BEWEGUNG_ATOM_ext(recAtomStartEnde[1]);
				
				
				arrTexte[i][0] = recAtomExt_first.get_StationStart().get___KETTE(bibVECTOR.get_Vector(_DB.BEWEGUNG_STATION$NAME1, 
																								_DB.BEWEGUNG_STATION$STRASSE,
																								_DB.BEWEGUNG_STATION$ORT));
				
				arrTexte[i][1] = recAtomExt_first.get___KETTE(bibVECTOR.get_Vector(_DB.BEWEGUNG_ATOM$ARTBEZ1));

				
				arrTexte[i][2] = recAtomExt_last.get_StationZiel().get___KETTE(bibVECTOR.get_Vector(_DB.BEWEGUNG_STATION$NAME1, 
																								_DB.BEWEGUNG_STATION$STRASSE,
																								_DB.BEWEGUNG_STATION$ORT));

			}
		}


		//jetzt die grids innerhalb der navilist fuellen
		for (int i=0; i<arrTexte.length;i++) {
			((MyE2_Grid)oBMap.get__Comp(B__CONST.COLKEY_AUSGANGSORT)).add(new MyE2_Label(new MyE2_String(arrTexte[i][0])), E2_INSETS.I_0_0_0_0);
			((MyE2_Grid)oBMap.get__Comp(B__CONST.COLKEY_SORTE)).add(new MyE2_Label(new MyE2_String(arrTexte[i][1])), E2_INSETS.I_0_0_0_0);
			((MyE2_Grid)oBMap.get__Comp(B__CONST.COLKEY_ZIELORT)).add(new MyE2_Label(new MyE2_String(arrTexte[i][2])), E2_INSETS.I_0_0_0_0);
		}
		
	}

}
