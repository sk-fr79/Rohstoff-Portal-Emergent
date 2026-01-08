package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_LabelButton4ListLineSelection;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_K_L_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	private MyDBToolBox oDB = bibALL.get_myDBToolBox();


	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(this.oDB);
	}


	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 


		String cID_VKOPF = oUsedResultMAP.get_cUNFormatedROW_ID();

		Rec20_VKOPF_KON recKopf = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(cID_VKOPF);

		KFIX_K_L_BT_Schliessen_Kontrakte schliessen_knopf =   (KFIX_K_L_BT_Schliessen_Kontrakte)oMAP.get__Comp(KFIX___CONST.HASH_KEY_ANZEIGE_LOCKED);

		schliessen_knopf._fill(recKopf);


		MyE2_LabelButton4ListLineSelection oLabFixierung =   (MyE2_LabelButton4ListLineSelection)oMAP.get__Comp(KFIX___CONST.HASH_KEY_ANZEIGE_FIX);
		oLabFixierung._set_gld(3, 1, 3, 1, null, null);
		this.set_fix_status(recKopf, oLabFixierung);
		
		
		MyE2_LabelButton4ListLineSelection oLabFixierungAktuell = (MyE2_LabelButton4ListLineSelection)oMAP.get__Comp(KFIX___CONST.HASH_KEY_ANZEIGE_FIX_AKTUELL_MENGE);
		oLabFixierungAktuell._fo_s(bibALL.get_FONT_SIZE()-2)._fo_italic()._set_gld(1, 5, 5, 1, new Alignment(Alignment.RIGHT, Alignment.TOP), null);
		if (oUsedResultMAP.get_FormatedValue(VKOPF_KON.ist_fixierung.fieldName()).equals("Y")){
			oLabFixierungAktuell.set_Text( updateFixierungStatus(recKopf));
		}else{
			oLabFixierungAktuell.set_Text("-");
		}

		MyE2_LabelButton4ListLineSelection oLabFixierungGesamt = (MyE2_LabelButton4ListLineSelection)oMAP.get__Comp(KFIX___CONST.HASH_KEY_ANZEIGE_FIX_GESAMT_MENGE);
		oLabFixierungGesamt._fo_s(bibALL.get_FONT_SIZE()-2)._fo_italic()._set_gld(1, 5, 5, 1, new Alignment(Alignment.RIGHT, Alignment.TOP), null);
		if (oUsedResultMAP.get_FormatedValue(VKOPF_KON.ist_fixierung.fieldName()).equals("Y")){
			oLabFixierungGesamt.set_Text( oUsedResultMAP.get_FormatedValue(VKOPF_KON.fix_menge_gesamt.fieldName(),"-"));
		}else{
			oLabFixierungGesamt.set_Text("-");
		}


		
		MyE2_LabelButton4ListLineSelection labelPos = (MyE2_LabelButton4ListLineSelection)oMAP.get__Comp(KFIX___CONST.HASH_KEY_ANZEIGE_POSITIONS);
		String label = "??";   //anzeige fuer fehler
		Color  back = null;

		String cQuery1 =   "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE   NVL(DELETED,'N')='N' AND   ID_VKOPF_KON="+cID_VKOPF+" AND POSITION_TYP<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"'";
		String cQuery2 =   "SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_KON WHERE   NVL(DELETED,'N')='N' AND   ID_VKOPF_KON="+cID_VKOPF+" AND POSITION_TYP<>'"+myCONST.VG_POSITION_TYP_ZUSATZTEXT+"' AND EINZELPREIS IS NOT NULL";

		String cWert1 = this.oDB.EinzelAbfrage(cQuery1,"@@@","@@@","@@@");
		String cWert2 = this.oDB.EinzelAbfrage(cQuery2,"@@@","@@@","@@@");
		
		if (cWert1.equals("@@@") || cWert2.equals("@@@")) {
			back = new E2_ColorAlarm();
		} else {
			if (cWert1.equals(cWert2)) {
				back = new E2_ColorBase();
				label = cWert1+"/"+cWert2;
			} else {
				back = new E2_ColorAlarm();
				label = cWert1+"/"+cWert2;
			}
		}
		labelPos._txt(label)._fo_s(bibALL.get_FONT_SIZE()-2)._fo_italic()._set_gld(2, 5, 2, 1, new Alignment(Alignment.CENTER, Alignment.TOP), back);
		
		
		boolean bDeleted = oUsedResultMAP.get_FormatedValue("DELETED").equals("Y");

		if (bDeleted)
		{
			oMAP.set_AllComponentsAsDeleted();
		}	
	}

	private String updateFixierungStatus(Rec20_VKOPF_KON oRecord) throws myException{
		
		if(oRecord.get_aktuelle_fixiert_menge().get_bOK()){
			return oRecord.get_aktuelle_fixiert_menge().get_FormatedRoundedNumber(0);
		}
		
		return "-";

	}



	
	private void set_fix_status(Rec20_VKOPF_KON recKopf, MyE2_LabelButton4ListLineSelection lab_fix) throws myException{
		
		if (recKopf.is_fixierungsKontrakte()) {
			
			Long fix = recKopf.get_aktuelle_fixiert_menge().get_longValue();
			Long gesamt = recKopf.get_fixierung_gesamt_menge().get_longValue();
			
			boolean ist_end_fixierung_vor_aktuelle_datum = myDateHelper.get_Date1_Greater_Date2(bibALL.get_cDateNOW(), recKopf.get_fix_datum_bis());

			if(fix<gesamt || fix==0){
				lab_fix._set_icon(KFIX___CONST.LABEL_KOPF_FIXIERT)._ttt("Fixierungs-Kontrakt");
			}else if(fix==gesamt){
				lab_fix._set_icon(KFIX___CONST.LABEL_KOPF_FIXIERT_TOTAL)._ttt("Fixierungs-Kontrakt, Status Fixierung abgeschlossen !");
			}else if(fix>gesamt){
				lab_fix._set_icon(KFIX___CONST.LABEL_KOPF_FIXIERT_PROBLEM)._ttt("Fixierungs-Kontrakt, Status Fixierungsmenge überschritten !");
			}else if (ist_end_fixierung_vor_aktuelle_datum){
				lab_fix._set_icon(KFIX___CONST.LABEL_KOPF_FIXIERT_PROBLEM)._ttt("Fixierungs-Kontrakt, Status Fixierungzeitraum überschritten !");
			}
		} else 	{
			lab_fix._set_icon(KFIX___CONST.LABEL_KOPF_NORMAL)._ttt("Standard-Kontrakt");
		}
	}

	
}
