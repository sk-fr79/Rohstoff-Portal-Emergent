package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingleMyString;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;


public class FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort extends E2_BasicModuleContainer_MASK
{
	private DB_SEARCH_Adress 		oSearchAdress = null;
	private MyE2_Button             oButtonAbbruch = new MyE2_Button(new MyE2_String("Abbrechen"));
	private MyE2_Button             oButtonUebernehmen = new MyE2_Button(new MyE2_String("Trotzdem übernehmen"));
	private boolean      			bLeerenWennSchliessknopf = true;
	private MyE2_DB_CheckBox 		oCBOhneAVV_VertragsCheck = null;
	
	private boolean    				bIsPrimaryCall = false;

	
	/**
	 * 
	 * @param SearchAdress
	 * @param cEK_VK
	 * @param cID_ADRESSE_Unformated
	 * @param CBOhneAVV_VertragsCheck
	 * @param bIsFromPrimaryCall
	 * @param CallAtAdressSearch
	 * @throws myException
	 */
	public FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort(	DB_SEARCH_Adress 	SearchAdress, 
																String 				cEK_VK, 		
																String  			cID_ADRESSE_Unformated, 
																MyE2_DB_CheckBox 	CBOhneAVV_VertragsCheck,
																boolean            	bIsFromPrimaryCall, 
																boolean 			CallAtAdressSearch) throws myException
	{
		super();
		this.oSearchAdress = SearchAdress;
		this.oCBOhneAVV_VertragsCheck = CBOhneAVV_VertragsCheck;
		this.bIsPrimaryCall = bIsFromPrimaryCall;
		
		
		//2011-12-16: optionales verhindern einer fuhre, wenn es keinen gueltigen handelsvertrag gibt
		boolean bVerbieteUeberstimmen = __RECORD_MANDANT_ZUSATZ.IS__Value("VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG", "N", "N");
		this.oButtonUebernehmen.setVisible(!bVerbieteUeberstimmen);
		
		this.set_bVisible_Row_For_Messages(true);
		
		//zuerst pruefen, ob der popup-warner ueberhaupt noetig/gewuenscht ist
		if (! cID_ADRESSE_Unformated.equals(bibALL.get_ID_ADRESS_MANDANT()))
		{
			
			RECORD_ADRESSE recADRESSE_Lager = new RECORD_ADRESSE(cID_ADRESSE_Unformated);
			
			//2014-08-04: umstellung auf die neue pruefmimik
			FU__CHECK_MustWarn_AVV_Kontrakt  oPruefeVertrag = new FU__CHECK_MustWarn_AVV_Kontrakt();

			VectorSingleMyString vMeldungen = new VectorSingleMyString();
			vMeldungen.addAll(oPruefeVertrag.pruefe_Adresse_und_ReturnFehlerVector(recADRESSE_Lager, cEK_VK, bibALL.get_cDateNOW(), false));
			
			boolean bShowWarning = (vMeldungen.size()>0);
				
			//2012-03-27: das pruefen auf fehlenden handelsvertrag ist bei gesetztem Schalter <VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG>
			//            in den daemon FUHREN_PRUEFUNG_FEHLENDER_HANDELSVERTRAG gewandert, somit ist das hide des ueberstimm-buttons wirkungslos 
			bShowWarning = bShowWarning && (!bVerbieteUeberstimmen);
			
			//wenn in der maske der schalter OHNE_AVV_VERTRAG_CHECK gesetzt ist, dann kein popup anzeigen
			boolean bMustCheck = !oCBOhneAVV_VertragsCheck.isSelected();

			if (bMustCheck && bShowWarning && this.bIsPrimaryCall)
			{
				// den uebergeordneten E2_BasicContainer suchen
				E2_BasicModuleContainer oMask = new E2_RecursiveSearchParent_BasicModuleContainer(this.oSearchAdress).get_First_FoundContainer();
				
				String cMASK_KENNER = oMask.get_MODUL_IDENTIFIER();
				this.oButtonUebernehmen.add_GlobalValidator(new E2_ButtonAUTHValidator(cMASK_KENNER,FU___CONST.HASH_UEBERSTIMMEN_FEHLENDEN_KONTRAKT));
				
				MyE2_Row   oRowHelp = new MyE2_Row();
				oRowHelp.add(oPruefeVertrag.get_ColumnWithMeldungen(vMeldungen), E2_INSETS.I_0_0_0_0);
				
				this.add(oRowHelp, E2_INSETS.I_10_10_10_10);
				this.add(this.oButtonAbbruch,  E2_INSETS.I_10_10_10_10);
				this.add(this.oButtonUebernehmen,  E2_INSETS.I_10_10_10_10);
				
				this.oButtonAbbruch.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				});
		
				this.oButtonUebernehmen.add_oActionAgent(new XX_ActionAgent()
				{
					public void executeAgentCode(ExecINFO oExecInfo)  throws myException
					{
						FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort.this.bLeerenWennSchliessknopf = false;     // inhalt bleibt stehen
						FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					}
				});
				
				this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
				{
					public void executeAgentCode(ExecINFO oExecInfo) throws myException 
					{
						if (FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort.this.bLeerenWennSchliessknopf)
						{
							// adresse unbrauchbar machen, neu suchen erzwingen
							FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort.this.oSearchAdress.get_oTextForAnzeige().setText("");
							FU__Popup_Warne_kein_Handelsvertrag_Bei_Lagerort.this.oSearchAdress.get_oTextFieldForSearchInput().setText("");
						}
					}
				});
	
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(300),new MyE2_String("WARNUNG ! Kein gültiger EU-Vertrag-Vertrag vorhanden !!!"));
			}
			
		}
	}
}
