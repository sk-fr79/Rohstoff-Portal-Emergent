package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.MASK_VALID;

import java.util.Vector;

import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MaskSearchField.MyE2_MaskSearchField;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EINHEITEN_KOMBINATIONEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.ARTIKELSTAMM.AS___CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EAK_CODE_ext;


/**
 * prueft, ob die schalterstellungen der einzelne Maskeschalter sinnvolle kombinationen ergeben
 * @author martin
 *
 */
public class ARTIKEL_MAP_SET_AND_VALID_TYPEN extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		this.MaskSetting_EinheitenSperre(oMAP, true);

		return intern_Check(oMAP, ActionType, oExecInfo, oInputMap,false);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		this.MaskSetting_EinheitenSperre(oMAP, true);
		
		return intern_Check(oMAP, ActionType, oExecInfo, oInputMap,true);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		this.MaskSetting_EinheitenSperre(oMAP, false);
		
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
		
		boolean bGefahr = oMAP.get_bActualDBValue(_DB.ARTIKEL$GEFAHRGUT);
		
		boolean bProdukt = oMAP.get_bActualDBValue(_DB.ARTIKEL$IST_PRODUKT);
		boolean bDienstleitung = oMAP.get_bActualDBValue(_DB.ARTIKEL$DIENSTLEISTUNG);
		boolean bEndOfWaste = oMAP.get_bActualDBValue(_DB.ARTIKEL$END_OF_WASTE);
		
		boolean bLeergut = oMAP.get_bActualDBValue(_DB.ARTIKEL$IST_LEERGUT);
		
		this.FillMask_Info_Block(oMAP);

		
		//sinnlose kombinationen durchgehen
		if (bGefahr && bLeergut) { vFehlerMeldung.add("Leergut kann kein Gefahrgut sein !");}
		if (bGefahr && bDienstleitung) { vFehlerMeldung.add("Eine Dienstleistung kann kein Gefahrgut sein !");}
		
		if (bProdukt && bDienstleitung) { vFehlerMeldung.add("Eine Sorte kann nur Produkt, Dienstleistung oder \"End of Waste\" sein, keine Kombination !");}
		if (bProdukt && bEndOfWaste) { vFehlerMeldung.add("Eine Sorte kann nur Produkt, Dienstleistung oder \"End of Waste\" sein, keine Kombination !");}
		if (bDienstleitung && bEndOfWaste) { vFehlerMeldung.add("Eine Sorte kann nur Produkt, Dienstleistung oder \"End of Waste\" sein, keine Kombination !");}

		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {    //in maskenload wird nur gewarnt
			if (!bNew) {
				this.pruefe_EinheitenKombi(oMAP, oMV,true);
				this.pruefe_NummernCodes(oMAP, oMV,true);
			}
			for (String cMeldung: vFehlerMeldung) {
				oMV.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String(cMeldung)));
			}
			
		} else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION || ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_UNBOUND_KLICK_ACTION) {
			this.pruefe_EinheitenKombi(oMAP, oMV,false);
			this.pruefe_NummernCodes(oMAP, oMV,false);

			
			for (String cMeldung: vFehlerMeldung) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cMeldung)));
			}
		} else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION) {
			//bei speichern noch die einheitenkombis checken
			this.pruefe_EinheitenKombi(oMAP, oMV,false);
			this.pruefe_NummernCodes(oMAP, oMV,false);

			
			for (String cMeldung: vFehlerMeldung) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(cMeldung)));
			}
			
		}
		
		return oMV;
	}
	

	private void pruefe_EinheitenKombi(E2_ComponentMAP oMAP, MyE2_MessageVector oMV, boolean bWarn) throws myException {
		
	
		int iMessageTyp = bWarn?MyE2_Message.TYP_WARNING:MyE2_Message.TYP_ALARM;
		
		long lEinheit = oMAP.get_LActualDBValue(_DB.ARTIKEL$ID_EINHEIT, -1l, -1l);
		long lEinheit_preis = oMAP.get_LActualDBValue(_DB.ARTIKEL$ID_EINHEIT_PREIS, -1l, -1l);
		
		if (lEinheit_preis==-1) {
			lEinheit_preis = lEinheit;
		}
		
		long lMengenDivisor = oMAP.get_LActualDBValue(_DB.ARTIKEL$MENGENDIVISOR, -1l, -1l);
		
		//pruefung nur bei gefuellten feldern, da sonst keine abfrage moeglich 
		if (lEinheit>0 && lEinheit_preis>0 && lMengenDivisor>0) {
		
			//hier pruefen, ob die einheiten-kombination zugelassen ist
			RECLIST_EINHEITEN_KOMBINATIONEN  recList = new RECLIST_EINHEITEN_KOMBINATIONEN("SELECT * FROM "+bibE2.cTO()+
					".JT_EINHEITEN_KOMBINATIONEN " +
					" WHERE" +
					" ID_EINHEIT="+lEinheit+" AND "+
					" ID_EINHEIT_PREIS="+lEinheit_preis);
		
			if (recList.get_vKeyValues().size()==0) {
				oMV.add_MESSAGE(new MyE2_Message(new MyE2_String("Die Einheitenkombination aus Mengen- und Preiseinheit ist nicht zugelassen !!!"),iMessageTyp,false), false);
			} else {
				if (lEinheit == lEinheit_preis && lMengenDivisor!=1)	{
					oMV.add_MESSAGE(new MyE2_Message(new MyE2_String("Wenn die Einheit = Preiseinheit ist, dann MUSS der Divisor 1 sein !"),iMessageTyp,false), false);
				} else	if (lEinheit != lEinheit_preis && lMengenDivisor==1) {
					oMV.add_MESSAGE(new MyE2_Message(new MyE2_String("Wenn die Einheit <> Preiseinheit ist, dann DARF der Divisor NICHT 1 sein !"),iMessageTyp,false), false);
				}
			}
		}
	}
	
	
	private void pruefe_NummernCodes(E2_ComponentMAP oMAP, MyE2_MessageVector oMV, boolean bWarn) throws myException {
		
		int iMessageTyp = bWarn?MyE2_Message.TYP_WARNING:MyE2_Message.TYP_ALARM;

		
		boolean bIstProdukt = oMAP.get_bActualDBValue(_DB.ARTIKEL$IST_PRODUKT);
		boolean bIstDienstleistung = oMAP.get_bActualDBValue(_DB.ARTIKEL$DIENSTLEISTUNG);
		boolean bEndOfWaste = oMAP.get_bActualDBValue(_DB.ARTIKEL$END_OF_WASTE);
		
		String cZolltarifnummer = S.NN(oMAP.get_cActualDBValueFormated(_DB.ARTIKEL$ZOLLTARIFNR)).trim();
		String cBaselCode = S.NN(oMAP.get_cActualDBValueFormated(_DB.ARTIKEL$BASEL_CODE)).trim();
		String cBaselNotiz = S.NN(oMAP.get_cActualDBValueFormated(_DB.ARTIKEL$BASEL_NOTIZ)).trim();
		String cOECDCode = S.NN(oMAP.get_cActualDBValueFormated(_DB.ARTIKEL$EUCODE)).trim();
		String cOECDNotiz = S.NN(oMAP.get_cActualDBValueFormated(_DB.ARTIKEL$EUNOTIZ)).trim();
		
		long lID_AVV_IN = oMAP.get_LActualDBValue(_DB.ARTIKEL$ID_EAK_CODE, -1l, -1l);
		long lID_AVV_EX_MANDANT = oMAP.get_LActualDBValue(_DB.ARTIKEL$ID_EAK_CODE_EX_MANDANT, -1l, -1l);
		
		if (S.isEmpty(cZolltarifnummer)) {
			if ( !(bIstProdukt || bIstDienstleistung || bEndOfWaste)) {
				oMV.add_MESSAGE(
						new MyE2_Message(new MyE2_String("Ein Sorte darf nur ohne Zolltarifnummer sein, " +
								"wenn er ein Produkt, \"End of Waste\" oder eine Dienstleistung ist !!"),iMessageTyp,false));
			}
		}
		
		if (bIstProdukt || bIstDienstleistung || bEndOfWaste) {
			if (S.isFull(cBaselCode) || S.isFull(cBaselNotiz) || S.isFull(cOECDCode) || S.isFull(cOECDNotiz) || lID_AVV_IN>0 || lID_AVV_EX_MANDANT>0) {
				oMV.add_MESSAGE(
						new MyE2_Message(new MyE2_String("Ein Artikel mit einem der Merkmale: Produkt oder Dienstleistung" +
								" oder \"End of Waste\" darf keinen OECD, Basel oder AVV-Code tragen !!"),iMessageTyp,false));
			}
		}
		
	}
	
	
	
	/**
	 * sieht nach, ob der artikel schon verwendet wird, wenn ja werden die einheitenfelder gesperrt 
	 * (wird nur im Edit-Modus angewandt)
	 * @throws myException
	 */
	private void MaskSetting_EinheitenSperre(E2_ComponentMAP oMAP, boolean bOnlyReset) throws myException {
		
		Vector<String> vFelder = bibVECTOR.get_Vector(_DB.ARTIKEL$ID_EINHEIT, _DB.ARTIKEL$ID_EINHEIT_PREIS, _DB.ARTIKEL$MENGENDIVISOR);
		oMAP.set_ActiveADHOC(vFelder, true, false);

		if (bOnlyReset) {
			return;
		}
		
		String cID_ARTIKEL = oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		
		// jetzt nachsehen, ob der artikel bereits in einer fuhre oder einer rechnung auftaucht. wenn ja, 
		// die felder sperren
		// bei bearbeitung der maske sind diese felder nicht mehr bearbeitbar
		if (  (!bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU  WHERE FU.ID_ARTIKEL="+cID_ARTIKEL).trim().equals("0"))  ||
			  (!bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU INNER JOIN JT_ARTIKEL_BEZ AB ON(FU.ID_ARTIKEL_BEZ_EK=AB.ID_ARTIKEL_BEZ) WHERE AB.ID_ARTIKEL="+cID_ARTIKEL).trim().equals("0")) ||
			  (!bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE FU INNER JOIN JT_ARTIKEL_BEZ AB ON(FU.ID_ARTIKEL_BEZ_EK=AB.ID_ARTIKEL_BEZ) WHERE AB.ID_ARTIKEL="+cID_ARTIKEL).trim().equals("0")) ||
			  (!bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_VPOS_RG RG WHERE RG.ID_ARTIKEL="+cID_ARTIKEL).trim().equals("0"))
		   )
		{				
			oMAP.set_ActiveADHOC(vFelder, false, false);
		}
	
		//das oecd-suchfeld muss immer leer sein (ist kein datenbank-suchfeld)
		((MyE2_MaskSearchField)oMAP.get__Comp(AS___CONST.HASH_KEY_MASK_SEARCH_OECD_CODE)).clean();
	}

	
	
	
	private void FillMask_Info_Block(E2_ComponentMAP oMAP) throws myException {

		//anzeige: anr1,artbez1,avv,typ(prod/eow/diens), Gefahrgutanzeige
		int[] iBreite = {40,200,120,250,100};
		
		MyE2_Grid oGridInfo = (MyE2_Grid) oMAP.get__Comp(AS___CONST.HASH_KEY_MASK_INFO_FIELD);
		
		oGridInfo.set_Spalten(iBreite);
		oGridInfo.removeAll();
		
		boolean bAktiv = oMAP.get_bActualDBValue(_DB.ARTIKEL$AKTIV);

		//werte fuer die anzeige feststellen
		//wenn die Sorte inaktiv, dann den herader grau machen
		String cANR1 = oMAP.get_cActualDBValueFormated(_DB.ARTIKEL$ANR1);
		String cARTBEZ1 = oMAP.get_cActualDBValueFormated(_DB.ARTIKEL$ARTBEZ1);
		
		if (S.isEmpty(cANR1)) 		{	cANR1=		"<anr1>";}
		if (S.isEmpty(cARTBEZ1)) 	{	cARTBEZ1=	"<artbez1>";}
		
		String cAVV_CODE = 		"<avv-code>";
		RECORD_EAK_CODE_ext  	recCode  = null;
		
		String cID_EAK_CODE_EX_MANDANT=oMAP.get_cActualDBValueFormated("ID_EAK_CODE_EX_MANDANT", "");
		
		if (!S.isEmpty(cID_EAK_CODE_EX_MANDANT)) {
			cID_EAK_CODE_EX_MANDANT = bibALL.ReplaceTeilString(cID_EAK_CODE_EX_MANDANT, ".", "");
			recCode = new RECORD_EAK_CODE_ext(cID_EAK_CODE_EX_MANDANT);
			cAVV_CODE = recCode.get_complete_Code();
		}

		String cTyp = "ROHSTOFF";
		boolean bProdukt = oMAP.get_bActualDBValue(_DB.ARTIKEL$IST_PRODUKT);
		boolean bDienstleitung = oMAP.get_bActualDBValue(_DB.ARTIKEL$DIENSTLEISTUNG);
		boolean bEndOfWaste = oMAP.get_bActualDBValue(_DB.ARTIKEL$END_OF_WASTE);

		boolean bGefahrgut =  oMAP.get_bActualDBValue(_DB.ARTIKEL$GEFAHRGUT);
		
		if ((bProdukt && bDienstleitung) || (bProdukt && bEndOfWaste) || (bDienstleitung && bEndOfWaste)) {
			cTyp = "<Fehler>";
		} else if (bProdukt) {
			cTyp = "PRODUKT";
		} else if (bDienstleitung) {
			cTyp = "DIENSTLEISTUNG";
		} else if (bEndOfWaste) {
			cTyp = "\"End of Waste\"";
		}
		
		String cGEFAHRGUT = bGefahrgut?"GEFAHRGUT":"< - >";
		
		//jetzt die labels bauen
		ownLabel labelANR1 = 		new ownLabel(new MyE2_String(cANR1,false),!bAktiv,false);
		ownLabel labelARTBEZ1 = 	new ownLabel(new MyE2_String(cARTBEZ1,false),!bAktiv,false);

		ownLabel labelTYP = 	new ownLabel(new MyE2_String(cTyp,true),!bAktiv,false);

		ownLabel labelAVV = 	new ownLabel(new MyE2_String(cAVV_CODE,false),!bAktiv,false);
		if (recCode != null && recCode.is_GEFAEHRLICH_YES()) {
			labelAVV = 	new ownLabel(new MyE2_String(cAVV_CODE,false),!bAktiv,true);
		}

		ownLabel labelGefahr = new ownLabel(new MyE2_String(cGEFAHRGUT,true),!bAktiv,bGefahrgut);
		
		oGridInfo.add_raw(labelANR1);
		oGridInfo.add_raw(labelARTBEZ1);
		oGridInfo.add_raw(labelTYP);
		oGridInfo.add_raw(labelAVV);
		oGridInfo.add_raw(labelGefahr);

	}
	
	
	private class ownLabel extends MyE2_Label {
		private MutableStyle  oStyleLabelAktiv =	MyE2_Label.STYLE_NORMAL_BOLD();
		private MutableStyle  oStyleLabelInaktiv = 	MyE2_Label.STYLE_NORMAL_ITALIC_GREY();

		public ownLabel(MyE2_String cText, boolean bGrau, boolean bWarn) {
			super(cText);

			GridLayoutData  oGL_RED = new GridLayoutData();
			oGL_RED.setInsets(E2_INSETS.I(4,2,10,2));
			oGL_RED.setBackground(new E2_ColorAlarm());
			
			GridLayoutData   oGL_Normal = new GridLayoutData();
			oGL_Normal.setInsets(E2_INSETS.I(4,2,10,2));
			oGL_Normal.setBackground(new E2_ColorDark());

			
			//immer wenn es mit < beginnt, dann platzhalter
			if (cText.COrig().trim().startsWith("<") || bGrau) {
				this.setStyle(this.oStyleLabelInaktiv);
			} else {
				this.setStyle(this.oStyleLabelAktiv);
			}
			
			
			if (bWarn) {
				this.setLayoutData(oGL_RED);
			} else {
				this.setLayoutData(oGL_Normal);
			}
			
		}
		
	}

}
