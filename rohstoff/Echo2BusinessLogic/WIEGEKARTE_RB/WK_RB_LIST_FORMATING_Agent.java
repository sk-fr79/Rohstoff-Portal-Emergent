 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
   
import org.apache.commons.lang.StringUtils;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAGERPLATZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_LIST_BT_CONNECT_to_FUHRE;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_LIST_BT_JUMP_to_FUHRE;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecList_Lagerplatz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_Gueterkategorie;
  
public class WK_RB_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    private E2_NavigationList m_navList = null;
    
    public WK_RB_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
       this.m_tpHashMap = p_tpHashMap;
       m_navList = m_tpHashMap.getNavigationList();
       
    }
    
    
    @Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
    }
    
    @Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
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
			o = oMAP.get_hmRealComponents().get("ADDRESS_INFO_KUNDE");
			if (o instanceof MyE2_DB_Label) {
				((MyE2_DB_Label) o).setText(sAdresse_Lieferant);
			}
		}
		
		
		// Speditions-Adresse anzeigen: falls die Adresse eine frei eingegebene Adresse ist
		String sAdressInfoSped = oUsedResultMAP.get_UnFormatedValue("ADDRESS_INFO_SPED","");
		String sSpedition_Zusatz = oUsedResultMAP.get_FormatedValue("ADRESSE_SPEDITION","");
		String sSped = S.Concatenate(";", "", sAdressInfoSped,sSpedition_Zusatz);
		if (sSped != null && sSped.length() > 0){
			o = oMAP.get_hmRealComponents().get("ADDRESS_INFO_SPED");
			if (o instanceof MyE2_DB_Label_INROW) {
				((MyE2_DB_Label_INROW) o).setText(sSped);
			}
		}
		
		// die Liste der Abzüge eintragen:
		String abzuege = getInfoAbzuege(oUsedResultMAP.get_UnFormatedValue("ID_WIEGEKARTE"));
		String abzuege_kurz = abzuege;
		o = oMAP.get_hmRealComponents().get(WK_RB_CONST.WK_RB_NAMES.AUFLISTUNG_GEBINDE.db_val());
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
		String sTypDB = bibALL.null2leer(oUsedResultMAP.get_FormatedValue("TYP_WIEGEKARTE"));
		WK_RB_ENUM_WKTYP enumTyp = WK_RB_ENUM_WKTYP.getTyp(sTypDB);
		o = oMAP.get_hmRealComponents().get("TYP_WIEGEKARTE");
		if (o instanceof MyE2_DB_Label) {
			((MyE2_DB_Label) o).setText(enumTyp.user_text());
		}
		
		

		
		// Storno
		String sStorno = bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue("STORNO"));
		Color col = new E2_ColorBase();
		if (sStorno.equals("Y")){
			sStorno = "STORNO";
			col = Color.RED;
		} else {
			sStorno = "";
		}
		o = oMAP.get_hmRealComponents().get("STORNO");
		if (o instanceof MyE2_DB_Label) {
			((MyE2_DB_Label) o).setText(sStorno);
			((MyE2_DB_Label) o).setBackground(col);
		}
		
		
		// eine Eintragung der Wiegekarte hat schon das aktuelle Lager drin!!
		String sIDLager = oUsedResultMAP.get_UnFormatedValue("ID_ADRESSE_LAGER");

		
//		((MyE2_Button)oMAP.get_hmRealComponents().get("KENNZEICHEN")).add_oActionAgent(new ownActionPopup(sIDLager));
		WK_RB_LIST_bt_ListToMaskInListRowKENNZEICHEN btnEdit = ((WK_RB_LIST_bt_ListToMaskInListRowKENNZEICHEN)oMAP.get_hmRealComponents().get(WK_RB_CONST.WK_RB_NAMES.MEHRZEILER_KENNZEICHEN.db_val()));
		String sKfzKennz = bibALL.null2leer(oUsedResultMAP.get_UnFormatedValue(WIEGEKARTE.kennzeichen.fn()));
		btnEdit.setText(sKfzKennz);
		btnEdit.setFont(new E2_FontBold(4));
		btnEdit._setBorder(new E2_ColorDDDark());
		btnEdit.setBackground(new E2_ColorDDark());
		// wenn Storno, dann nicht editieren
		if (sStorno.equals("Y")){
			btnEdit.setIsUsedToEdit(false);
		}
	
		
		
		
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
		
		
		
		String idContainerEigen = oUsedResultMAP.get_UnFormatedValue("WK_CONT_ID_CONTAINER");
		if(S.isFull(idContainerEigen)) {
			MyE2_DB_Label_INROW lblID = (MyE2_DB_Label_INROW) oMAP.get__Comp_From_RealComponents(WIEGEKARTE.container_nr.fieldName());
			String containernr_eigen = oUsedResultMAP.get_UnFormatedValue("WK_CONT_CONTAINER_NR");
			lblID.set_Text(containernr_eigen);
			
				
		}
			
		String id_lagerplatz_absetz = oUsedResultMAP.get_UnFormatedValue("ID_LAGERPLATZ_ABSETZ");
		String id_lagerplatz_schuett = oUsedResultMAP.get_UnFormatedValue("ID_LAGERPLATZ_SCHUETT");
		String id_lagerplatz = S.isFull(id_lagerplatz_absetz) ? id_lagerplatz_absetz : id_lagerplatz_schuett;
		
		if (S.isFull(id_lagerplatz)){
			MyE2_Label lblLagerpfad = (MyE2_Label) oMAP.get__Comp_From_RealComponents(WK_RB_CONST.WK_RB_NAMES.LAGERPLATZ_PFAD.db_val());
			
			// pfad des Lagerplatzes lesen
			RecList_Lagerplatz rl = new RecList_Lagerplatz()._fill(RecList_Lagerplatz.getSql_Lagerplatz_by_ID(id_lagerplatz));
			if (rl != null && rl.size() > 0) {
				Rec22 r =rl.get(0); 
				String lager = r.getDbValueUnFormated(RecList_Lagerplatz.FIELD_PATH);
				lblLagerpfad.set_Text(lager);
			}
		}
		
		// Güterkategorie
		String sGueterkategorie = bibALL.null2leer(oUsedResultMAP.get_FormatedValue(WIEGEKARTE.gueterkategorie.fieldName()));
		String sGueterkat = StringUtils.capitalize(sGueterkategorie.toLowerCase());
		
		o = oMAP.get_hmRealComponents().get(WIEGEKARTE.gueterkategorie.fieldName());
		if (o instanceof MyE2_DB_Label_INROW) {
			((MyE2_DB_Label_INROW) o).setText(sGueterkat);
		}
		
		
		//2012-05-07: jumper in die fuhrenzentrale inaktiv, wenn keine fuhre da ist
		o = oMAP.get_hmRealComponents().get(WK_RB_CONST.WK_RB_NAMES.SPRUNGBUTTON_ZU_FUHRE.db_val());
		if (o!=null && o instanceof WK_RB_LIST_BT_JUMP_to_FUHRE)
		{
			if (S.isEmpty(oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE")))
			{
				//sorgt beim anschliessenden automatischen enablen der liste fuer an- oder ausgeschaltete buttons 
				((WK_RB_LIST_BT_JUMP_to_FUHRE)o).set_bSprungIstMoeglich(false);		
				if (ENUM_MANDANT_DECISION.USE_BUTTON_CONNECT_WK_FUHRE.is_YES_FromSession()){
					((WK_RB_LIST_BT_JUMP_to_FUHRE)o).setVisible(false);;
				}

			}
		}
		
		
		//2012-05-07: jumper in die fuhrenzentrale inaktiv, wenn keine fuhre da ist
		if (ENUM_MANDANT_DECISION.USE_BUTTON_CONNECT_WK_FUHRE.is_YES_FromSession()){
			o = oMAP.get_hmRealComponents().get(WK_RB_CONST.WK_RB_NAMES.BUTTON_VERKNUEPFUNG_MIT_FUHRE.db_val());
			if (o!=null && o instanceof WK_RB_LIST_BT_CONNECT_to_FUHRE)
			{
				if (S.isEmpty(oUsedResultMAP.get_UnFormatedValue("ID_VPOS_TPA_FUHRE")))
				{
					((WK_RB_LIST_BT_CONNECT_to_FUHRE)o).set_Connected(false);
				} else {
					((WK_RB_LIST_BT_CONNECT_to_FUHRE)o).setVisible(false);
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
			RecDOWiegekarte rec = new RecDOWiegekarte(m_tpHashMap.getLastMaskLoadStatus())._fill_id(idWiegekarteParent);
//			Rec21_wiegekarte rec = new Rec21_wiegekarte()._fill_id(idWiegekarteParent);
			String sGes = rec.get_fs_dbVal(WIEGEKARTE.ist_gesamtverwiegung, "N");
			String sLfnd = rec.get_fs_dbVal(WIEGEKARTE.lfd_nr);
			
			if (sGes.equals("Y") ){
				sRet = "Einzelverwiegung von " + sLfnd;
			} else {
				sRet = "Folgewiegung von " + sLfnd;
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
		Rec22 rec = null;
		StringBuilder sb = new StringBuilder();
		
		SEL sel = new SEL("*")
					.FROM(WIEGEKARTE_ABZUG_GEB._tab())
					.WHERE(new vglParam(WIEGEKARTE_ABZUG_GEB.id_wiegekarte))
					.ORDERUP(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb);
		
		SqlStringExtended  sqlExt = new SqlStringExtended(sel.s())
						._addParameters(new VEK<ParamDataObject>()
						._a(new Param_Long("",Long.parseLong(idWiegekarte))) );
				
		RecList22 rl = new RecList22(WIEGEKARTE_ABZUG_GEB._tab())._fill(sqlExt);
		
		for (int i = 0; i< rl.size(); i++){
			if (i > 0){
				sb.append(", ");
			}
			
			rec = rl.get(i);
			;
			sb.append(rec.get_ufs_dbVal(WIEGEKARTE_ABZUG_GEB.gebinde, ""));
			sb.append(": ");
			sb.append(rec.get_fs_dbVal(WIEGEKARTE_ABZUG_GEB.menge, ""));
			sb.append(" x ");
			sb.append(rec.get_ufs_dbVal(WIEGEKARTE_ABZUG_GEB.gewicht_einzel, ""));
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
			

			// TODO Auto-generated method stub
//			new WK_Erfassung_Waegung(WK_LIST_FORMATING_Agent.this.m_navList , cID_Zeile, this.sIDAdresseLager, null, false,false);
			// SPRUNG IN DIE MASKE ....
			
		}
		
		
	}
	
    
}
 
 
