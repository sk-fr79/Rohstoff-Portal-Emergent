package rohstoff.Echo2BusinessLogic.WIEGEKARTE;




import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class WK_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{
	E2_NavigationList m_navList = null;
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
	}

	
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{

		Object o = null;
		
		m_navList = oMAP.get_oNavigationList_This_Belongs_to();
		
		// Sorte darf nicht zu lang angezeigt werden
		String sSorte = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("ART_INFO"));
		o = oMAP.get_hmRealComponents().get("ART_INFO");
		if (o instanceof MyE2_DB_Label_INROW) {
			if (sSorte.length() > 100 ){
				// tooltip auf gesamten Text setzen
				((MyE2_DB_Label_INROW) o).setToolTipText(sSorte);
				// Text selbst aber verkürzen
				sSorte = sSorte.substring(0,100) + "...";
				((MyE2_DB_Label_INROW) o).setText(sSorte);
			}
		}
		o = null;

		
		
		// W1
		String cDatum = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("W1_DATUM"));
		String cZeit = bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue("W1_ZEIT"));
		o = oMAP.get_hmRealComponents().get("W1_DATUM");
		if (o instanceof MyE2_DB_Label) {
			
			((MyE2_DB_Label) o).setText(cDatum + " "+ cZeit);
		}
		o = null;

		// W2
		cDatum = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("W2_DATUM"));
		cZeit = bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue("W2_ZEIT"));
		o = oMAP.get_hmRealComponents().get("W2_DATUM");
		if (o instanceof MyE2_DB_Label) {
			
			((MyE2_DB_Label) o).setText(cDatum + " "+ cZeit);
		}
		o = null;
		
		// W1 Handwägung ans Gewicht dranhängen
		String sHand = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("W1_HANDEINGABE"));
		String sGewicht = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("W1_GEWICHT"));
		if (sHand != null && sHand.equalsIgnoreCase("Y")){
			o = oMAP.get_hmRealComponents().get("W1_GEWICHT");
			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText("(H) " + sGewicht);
			}
		}
		
		// W2 Handwägung ans Gewicht dranhängen
		sHand = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("W2_HANDEINGABE"));
		sGewicht = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("W2_GEWICHT"));
		if (sHand != null && sHand.equalsIgnoreCase("Y")){
			o = oMAP.get_hmRealComponents().get("W2_GEWICHT");
			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText("(H) " + sGewicht);
			}
		}
		
		
		// Adresse anzeigen: falls die Adresse eine frei eingegebene Adresse ist
		String sAdresse_Lieferant = oUsedResultMAP.get_FormatedValue("ADRESSE_LIEFERANT");
		if (sAdresse_Lieferant != null && sAdresse_Lieferant.length() > 0){
			o = oMAP.get_hmRealComponents().get("ADDRESS_INFO");
			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText(sAdresse_Lieferant);
			}
		}
		
		
		// Speditions-Adresse anzeigen: falls die Adresse eine frei eingegebene Adresse ist
		String sAdresse_Spedition = oUsedResultMAP.get_FormatedValue("ADRESSE_SPEDITION");
		if (sAdresse_Spedition != null && sAdresse_Spedition.length() > 0){
			o = oMAP.get_hmRealComponents().get("ADDRESS_INFO_SPED");
			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText(sAdresse_Spedition);
			}
		}
		
		// die Liste der Abzüge eintragen:
		String abzuege = getInfoAbzuege(oUsedResultMAP.get_UnFormatedValue("ID_WIEGEKARTE"));
		String abzuege_kurz = abzuege;
		o = oMAP.get_hmRealComponents().get(WK_LIST_CONST.HASH_AUFLISTUNG_GEBINDE);
		if (o instanceof MyE2_Label) {
			if (abzuege_kurz.length() > 50){
				abzuege_kurz = abzuege.substring(0, 50) + "...";
			}
			((MyE2_Label) o).setText(abzuege_kurz);
			((MyE2_Label) o).setToolTipText(abzuege);
		}
		
		
		// Wareneingang/Ausgang
		String sWE = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("IST_LIEFERANT"));
		if (sWE.equalsIgnoreCase("Y")){
			sWE = new MyE2_String("Wareneingang").CTrans();
		} else {
			sWE = new MyE2_String("Warenausgang").CTrans();
		}
		o = oMAP.get_hmRealComponents().get("IST_LIEFERANT");
		if (o instanceof MyE2_DB_Label_INROW) {
			((MyE2_DB_Label_INROW) o).setText(sWE);
		}
		
		// anzeige, ob schon gedruckt wurde
		Long nWKGedruckt= oUsedResultMAP.get_LActualDBValue("DRUCKZAEHLER", true);
		o = oMAP.get_hmRealComponents().get("LFD_NR");
		if (o instanceof MyE2_DB_Label_INROW) {
			if (nWKGedruckt > 0){
				((MyE2_DB_Label_INROW) o).get_oErsatzButton().setIcon(E2_ResourceIcon.get_RI("print_ok.png"));
			}
		}
		
		Long nESGedruckt= oUsedResultMAP.get_LActualDBValue("DRUCKZAEHLER_EINGANGSSCHEIN",true);
		o = oMAP.get_hmRealComponents().get("ES_NR");
		if (o instanceof MyE2_DB_Label_INROW) {
			if (nESGedruckt > 0){
				((MyE2_DB_Label_INROW) o).get_oErsatzButton().setIcon(E2_ResourceIcon.get_RI("print_ok.png"));
			}
		}
		
		
		
		// WK-Typ
		String sTyp = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("TYP_WIEGEKARTE"));
		if (sTyp.equalsIgnoreCase(WK_LIST_CONST.TYP_WIEGEKARTE_WIEGESCHEIN)){
			sTyp = new MyE2_String("Wiegeschein").CTrans();
		} else if (sTyp.equalsIgnoreCase(WK_LIST_CONST.TYP_WIEGEKARTE_FREMDWIEGUNG)){
			sTyp = new MyE2_String("Fremdwiegung").CTrans();
		} else if (sTyp.equalsIgnoreCase(WK_LIST_CONST.TYP_WIEGEKARTE_STRECKE)){
			sTyp = new MyE2_String("Strecke").CTrans();
		} else if (sTyp.equalsIgnoreCase(WK_LIST_CONST.TYP_WIEGEKARTE_WIEGESCHEIN_LIEFERSCHEIN)){
			sTyp = new MyE2_String("Wiegeschein/Lieferschein").CTrans();
		} else if (sTyp.equalsIgnoreCase(WK_LIST_CONST.TYP_WIEGEKARTE_LAGER)){
			sTyp = new MyE2_String("Lagerlieferung").CTrans();
		} else if (sTyp.equalsIgnoreCase(WK_LIST_CONST.TYP_WIEGEKARTE_RETOURE)){
			sTyp = new MyE2_String("Retoure").CTrans();
		} else if (sTyp.equalsIgnoreCase(WK_LIST_CONST.TYP_WIEGEKARTE_LEERFUHRE)){
			sTyp = new MyE2_String("Leerfuhre").CTrans();
		} else if (sTyp.equalsIgnoreCase(WK_LIST_CONST.TYP_WIEGEKARTE_DOKUMENTARISCH)){
			sTyp = new MyE2_String("Dokumentarisch").CTrans();
		} ;
		
		o = oMAP.get_hmRealComponents().get("TYP_WIEGEKARTE");
		if (o instanceof MyE2_DB_Label) {
			((MyE2_DB_Label) o).setText(sTyp);
		}

		
		// Storno
		String sStorno = bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue("STORNO"));
		if (sStorno.equals("Y")){
			o = oMAP.get_hmRealComponents().get("STORNO");
			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText("STORNO");
				((MyE2_DB_Label) o).setBackground(Color.RED);
			}
		}
		
		// eine Eintragung der Wiegekarte hat schon das aktuelle Lager drin!!
		String sIDLager = oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_LAGER");

		((MyE2_Button)oMAP.get_hmRealComponents().get("KENNZEICHEN")).add_oActionAgent(new ownActionPopup(sIDLager));
		
		
		// Folgewägung 
		sGewicht= oUsedResultMAP.get_UnFormatedValue("NETTOGEWICHT");
		
		// Gesamtverwiegung - Folgewiegung - Einzelwiegung
		String sGesamtYN = oUsedResultMAP.get_UnFormatedValue("IST_GESAMTVERWIEGUNG");
		String sGesamtverwiegung = getInfoGesamtverwiegung(	oUsedResultMAP.get_UnFormatedValue("ID_WIEGEKARTE"), 
											oUsedResultMAP.get_UnFormatedValue("ID_WIEGEKARTE_PARENT"),
											sGesamtYN);
		
		
		o = oMAP.get_hmRealComponents().get("INFO_GESAMTVERWIEGUNG");
		if (o instanceof MyE2_DB_Label) {
			MyE2_DB_Label lbl =  (MyE2_DB_Label)o;
			lbl.setText(sGesamtverwiegung);
			
			if (!bibALL.isEmpty(sGesamtYN) && sGesamtYN.equals("Y")){
				lbl.setFont(new E2_FontBold());
			}
		}
		
		

		//2012-05-07: jumper in die fuhrenzentrale inaktiv, wenn keine fuhre da ist
		o = oMAP.get_hmRealComponents().get(WK_LIST_CONST.HASH_SPRUNGBUTTON_ZU_FUHRE);
		if (o!=null && o instanceof WK_LIST_BT_JUMP_to_FUHRE)
		{
			if (S.isEmpty(oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE")))
			{
				//sorgt beim anschliessenden automatischen enablen der liste fuer an- oder ausgeschaltete buttons 
				((WK_LIST_BT_JUMP_to_FUHRE)o).set_bSprungIstMoeglich(false);		
				if (ENUM_MANDANT_DECISION.USE_BUTTON_CONNECT_WK_FUHRE.is_YES()){
					((WK_LIST_BT_JUMP_to_FUHRE)o).setVisible(false);;
				}

			}
		}
		
		
		//2012-05-07: jumper in die fuhrenzentrale inaktiv, wenn keine fuhre da ist
		if (ENUM_MANDANT_DECISION.USE_BUTTON_CONNECT_WK_FUHRE.is_YES()){
			o = oMAP.get_hmRealComponents().get(WK_LIST_CONST.BUTTON_VERKNUEPFUNG_MIT_FUHRE);
			if (o!=null && o instanceof WK_LIST_BT_CONNECT_to_FUHRE)
			{
				if (S.isEmpty(oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE")))
				{
					((WK_LIST_BT_CONNECT_to_FUHRE)o).set_Connected(false);
				} else {
					((WK_LIST_BT_CONNECT_to_FUHRE)o).setVisible(false);
				}
				
			}		
		}
		
	}

	
	/**
	 * Ermittelt den Status der Wiegekarte ob 
	 * 
	 * -Normale Einzelverwiegung
	 * 
	 * -Gesmatverwiegung Parent
	 * -Gesamtverwiegung Folge
	 * 
	 * -Folgeverwiegung
	 * 
	 * @param idWiegekarte
	 * @return
	 * @throws myException 
	 */
	private String getInfoGesamtverwiegung(String idWiegekarte, String idWiegekarteParent, String sGesamtverwiegung) throws myException{
		String sRet = null;
		if (!bibALL.isEmpty(sGesamtverwiegung) && sGesamtverwiegung.equals("Y")){
				sRet = "Gesamtverwiegung";
		} else if (!bibALL.isEmpty(idWiegekarteParent) ) {
			RECORD_WIEGEKARTE rec = new RECORD_WIEGEKARTE(idWiegekarteParent);
			if (rec.get_IST_GESAMTVERWIEGUNG_cUF() != null && rec.get_IST_GESAMTVERWIEGUNG_cUF().equals("Y") ){
				sRet = "Einzelverwiegung von " + rec.get_LFD_NR_cUF_NN("-");
			} else {
				sRet = "Folgewiegung von " + rec.get_LFD_NR_cUF_NN("-");
			}
		} 
		return sRet;
	}
	
	
	
	/**
	 * Ermittelt alle Abzüge zur Wiegekarte und listet diese als Text auf in der Form:
	 * "<Bezeichnung> <menge>Stk a <gewicht>, " 
	 * @param idWiegekarte
	 * @return
	 * @throws myException 
	 */
	private String getInfoAbzuege(String idWiegekarte) throws myException{
		RECORD_WIEGEKARTE_ABZUG_GEB rec = null;
		StringBuilder sb = new StringBuilder();
		
		RECLIST_WIEGEKARTE_ABZUG_GEB list = new RECLIST_WIEGEKARTE_ABZUG_GEB("ID_WIEGEKARTE = " + idWiegekarte, "ID_WIEGEKARTE_ABZUG_GEB");
		
		for (int i = 0; i< list.size(); i++){
			if (i > 0){
				sb.append(", ");
			}
			
			rec = list.get(i);
			
			sb.append(rec.get_GEBINDE_cUF_NN(""));
			sb.append(": ");
			sb.append(rec.get_MENGE_cUF_NN(""));
			sb.append(" x ");
			sb.append(rec.get_GEWICHT_EINZEL_cUF_NN(""));
			sb.append("Kg");
		}
		return sb.toString();
	}
	
	


	/**
	 * 
	 * @author manfred
	 *
	 */
	private class ownActionPopup extends XX_ActionAgent
	{

		private String sIDAdresseLager = null;
		
		
		public ownActionPopup(String IDAdresseLager) {
			super();
			this.sIDAdresseLager = IDAdresseLager;
		}


		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			MyE2_Button oButt = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
			
			E2_ComponentMAP oMap = oButt.EXT().get_oComponentMAP();
			String cID_Zeile = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			// roten Marker in Zeile setzen
			oMap.set_Marker(true);
			
			//
			// Zustände der drüberliegenden Checkboxen prüfen, d.h. abfragen:
			// 
			// recursive nach dem E2_BasicModuleContainer suchen
//			E2_RecursiveSearchParent_BasicModuleContainer oSearch = new E2_RecursiveSearchParent_BasicModuleContainer(m_navList);
//			E2_BasicModuleContainer oContainerList = oSearch.get_First_FoundContainer();
//			E2_RecursiveSearch_Component oSearchComps = new E2_RecursiveSearch_Component(
//			oContainerList, bibALL.get_Vector(WK_SelectField_Lager.class.getName()), null);
//
//			Vector<Component> vResult = oSearchComps.get_vAllComponents();
//			String sIDLager = null;
//			if (vResult.size()== 1)
//			{
//				Component o = vResult.get(0);
//				WK_SelectField_Lager selLager = (WK_SelectField_Lager) o;
//				sIDLager = selLager.get_ActualWert();
//			}

			
//			bibMSG.add_MESSAGE(new MyE2_Info_Message(cID_Zeile));
			// TODO Auto-generated method stub
			new WK_Erfassung_Waegung(WK_LIST_FORMATING_Agent.this.m_navList , cID_Zeile, this.sIDAdresseLager, null, false,false);
		}
		
		
	}
}
