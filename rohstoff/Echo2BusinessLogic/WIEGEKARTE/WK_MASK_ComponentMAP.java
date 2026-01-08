package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextArea;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class WK_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public WK_MASK_ComponentMAP() throws myException
	{
		super(new WK_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		WK_SearchFieldFuhre			oSearchFuhre = null;
		
		// Fuhre/ Fuhrenort
		 MyE2_DB_TextField				tfFuhre			= null;
		 MyE2_DB_TextField				tfFuhreOrt		= null;
		 
		
		// Wiegekarte_typ
		 MyE2_DB_CheckBox					cbWiegekarte_Wiegeschein = null;
		 MyE2_DB_CheckBox					cbWiegekarte_Strecke = null;
		 MyE2_DB_CheckBox					cbWiegekarte_Fremdwiegung = null;
		 MyE2_DB_CheckBox					cbWiegekarte_Wiegeschein_Lieferschein = null;
		 MyE2_DB_CheckBox					cbWiegekarte_Retoure = null;
		 MyE2_DB_CheckBox					cbWiegekarte_Lager = null;
		 ActionAgent_RadioFunction_CheckBoxList 	agentWatchdogWiegekartenTyp = null;
		
		
		 MyE2_DB_CheckBox					cbGesamtverwiegung = null;
		
		
		 WK_UB_SearchFieldAdresse		oSearchAdresseAbnehmerStrecke = null;
		
		 MyE2_DB_TextField					tfBemerkung1 = null;
		 MyE2_DB_TextField					tfBemerkung2 = null;
		 MyE2_DB_TextField					tfBemerkungIntern = null;

		

		 MyE2_DB_TextField					tfGewichtAbzug = null;
		 MyE2_DB_TextField					tfGrundAbzug = null;
		
		 MyE2_DB_TextField					tfAnzahlPaletten = null;
		 MyE2_DB_TextField					tfAnzahlBigBags = null;
		 MyE2_DB_TextField					tfAnzahlGitterboxen = null;
		 MyE2_DB_TextField					tfAnzahlBehaelter = null;
		 MyE2_DB_TextField					tfAnzahlFrei = null;
		 MyE2_DB_TextField					tfBezeichnungFrei = null;
		
		
		
		// allgemeine Angaben Wiegekarte
		 WK_UB_SearchFieldAdresse		oSearchAdresse  = null;
		 WK_UB_SearchFieldAdresse		oSearchAdresseSpedition = null;
		
		
		
		 MyE2_DB_CheckBox						cbAdresseNeu	= null;
		 UB_TextArea						tfAdresse		= null;

//		private UB_TextField					tfKennzeichen 		= null;
		
		 MyE2_Column						colCB_WE = null;
		 MyE2_Column						colCB_WA = null;
		
		 MyE2_DB_CheckBox					cbWarenEingang			= null;
		 MyE2_DB_CheckBox					cbWarenAusgang 		= null;
		 ActionAgent_RadioFunction_CheckBoxList 	agentWatchdogWEWA = null;
		
		 MyE2_DB_TextArea						tfBefund			= null;
		
		 WK_UB_SearchFieldSorte			oSearchSorte 	= null;
		 MyE2_SelectFieldWithParameters  cmbSorteKunde		= null;
		 
		 MyE2_DB_CheckBox						cbSorteHand			= null;
		

		
		/*
		 * beispiele fuer felder in der map
		 *
		*/
//		String[] cFieldsStandard = {"BEFUND","ERZEUGT_AM","ERZEUGT_VON","ID_ADRESSE_LAGER","ID_ADRESSE_LIEFERANT","ID_ARTIKEL_SORTE","ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE_ORT","ID_WIEGEKARTE","IST_LIEFERANT","KENNZEICHEN","LFD_NR","NETTOGEWICHT"}; 
//		String[] cFieldsInfolabs = {"BEFUND","ERZEUGT_AM","ERZEUGT_VON","ID_ADRESSE_LAGER","ID_ADRESSE_LIEFERANT","ID_ARTIKEL_SORTE","ID_VPOS_TPA_FUHRE","ID_VPOS_TPA_FUHRE_ORT","ID_WIEGEKARTE","IST_LIEFERANT","KENNZEICHEN","LFD_NR","NETTOGEWICHT"}; 

		//E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_WIEGEKARTE_WICHTIGKEIT","BESCHREIBUNG","ID_WK_WICHTIGKEIT","ISTSTANDARD",true);
		
//		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextArea(oFM.get_("BEFUND"),500,5), new MyE2_String("BEFUND"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ADRESSE_LAGER"),true,200,10,false), new MyE2_String("ID_ADRESSE_LAGER"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ADRESSE_LIEFERANT"),true,200,10,false), new MyE2_String("ID_ADRESSE_LIEFERANT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_ARTIKEL_SORTE"),true,200,10,false), new MyE2_String("ID_ARTIKEL_SORTE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE"),true,200,10,false), new MyE2_String("ID_VPOS_TPA_FUHRE"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_TPA_FUHRE_ORT"),true,200,10,false), new MyE2_String("ID_VPOS_TPA_FUHRE_ORT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_WIEGEKARTE"),true,200,10,false), new MyE2_String("ID_WIEGEKARTE"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("IST_LIEFERANT")), new MyE2_String("IST_LIEFERANT"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("KENNZEICHEN"),true,200,20,false), new MyE2_String("KENNZEICHEN"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("LFD_NR"),true,100,10,false), new MyE2_String("LFD_NR"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("NETTOGEWICHT"),true,380,19,false), new MyE2_String("NETTOGEWICHT"));
		

		/*
		 * beispiele fuer beliebige felder
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_WK_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_WK")), new MyE2_String("ID"));

		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iRows(4);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iRows(4);
		
		((MyE2_DB_TextField)this.get__Comp("AUFGABEKURZ")).set_iWidthPixel(600);
		((MyE2_DB_TextField)this.get__Comp("ANTWORTKURZ")).set_iWidthPixel(600);

		((MyE2_DB_CheckBox)this.get__Comp("ERLEDIGT")).add_oActionAgent(new cbActionAgent());
		*/	

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new WK_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new WK_MASK_FORMATING_Agent());
	}
	
}
