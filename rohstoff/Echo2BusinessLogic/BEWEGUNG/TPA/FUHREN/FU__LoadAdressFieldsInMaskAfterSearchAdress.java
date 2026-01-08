package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.basics4project.ENUM_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;
import rohstoff.utils.MyAdress;

public class FU__LoadAdressFieldsInMaskAfterSearchAdress
{
	private String 									cEK_VK =	null;
	private HashMap<String, String> 				hmFieldUebersetzer = null;
	
	private boolean   								b_call_is_from_main_adress = false;
	
	/**
	 * 
	 * @param cek_vk
	 * @param FieldUebersetzer
	 * @param cArrayFieldsToClear
	 * @throws myException
	 */
	public FU__LoadAdressFieldsInMaskAfterSearchAdress(		String 					cek_vk, 
															HashMap<String, String> FieldUebersetzer,
															boolean    				p_call_is_from_main_adress) throws myException
	{
		super();
		this.cEK_VK 			= cek_vk;
		this.hmFieldUebersetzer = FieldUebersetzer;
		
		this.b_call_is_from_main_adress = p_call_is_from_main_adress;
		
		//pruefung, ob alle uebersetzer vorhanden sind
		if (!this.hmFieldUebersetzer.containsKey("NAME1")) throw new myException(this,"Fieldname for NAME1 is missing !");
		if (!this.hmFieldUebersetzer.containsKey("NAME2")) throw new myException(this,"Fieldname for NAME2 is missing !");
		if (!this.hmFieldUebersetzer.containsKey("NAME3")) throw new myException(this,"Fieldname for NAME3 is missing !");
		if (!this.hmFieldUebersetzer.containsKey("STRASSE")) throw new myException(this,"Fieldname for STRASSE is missing !");
		if (!this.hmFieldUebersetzer.containsKey("HAUSNUMMER")) throw new myException(this,"Fieldname for HAUSNUMMER is missing !");
		if (!this.hmFieldUebersetzer.containsKey("PLZ")) throw new myException(this,"Fieldname for PLZ is missing !");
		if (!this.hmFieldUebersetzer.containsKey("ORT")) throw new myException(this,"Fieldname for ORT is missing !");
		if (!this.hmFieldUebersetzer.containsKey("ORTZUSATZ")) throw new myException(this,"Fieldname for ORTZUSATZ is missing !");
		if (!this.hmFieldUebersetzer.containsKey("TEL")) throw new myException(this,"Fieldname for TEL is missing !");
		if (!this.hmFieldUebersetzer.containsKey("FAX")) throw new myException(this,"Fieldname for FAX is missing !");
		if (!this.hmFieldUebersetzer.containsKey("OEFFNUNGSZEITEN")) throw new myException(this,"Fieldname for OEFFNUNGSZEITEN is missing !");
		if (!this.hmFieldUebersetzer.containsKey("LAENDERCODE")) throw new myException(this,"Fieldname for LAENDERCODE is missing !");
		if (!this.hmFieldUebersetzer.containsKey("ID_ADRESSE_LAGER")) throw new myException(this,"Fieldname for ID_ADRESSE_LAGER is missing !");
		if (!this.hmFieldUebersetzer.containsKey("LADEMENGE_GUTSCHRIFT")) throw new myException(this,"Fieldname for LADEMENGE_GUTSCHRIFT is missing !");
		if (!this.hmFieldUebersetzer.containsKey("ABLADEMENGE_RECHNUNG")) throw new myException(this,"Fieldname for ABLADEMENGE_RECHNUNG is missing !");
	}
	
	
	public void set_MaskValues(HashMap<String,MyE2IF__Component> hmFields, MyAdress  oAdress) throws myException
	{
		
		if (oAdress.get_bIS_FirmenAdresse())
		{
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("NAME1"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_NAME1()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("NAME2"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_NAME2()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("NAME3"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_NAME3()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("STRASSE"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_STRASSE()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("HAUSNUMMER"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_HAUSNUMMER()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("PLZ"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_PLZ()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("ORT"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_ORT()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("ORTZUSATZ"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_ORTZUSATZ()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("TEL"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_StandardTelefonNumber()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("FAX"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_StandardFaxNumber()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("LAENDERCODE"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_LAENDERCODE()));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("OEFFNUNGSZEITEN"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_cFI_OEFFNUNGSZEITEN()));
			if (this.hmFieldUebersetzer.containsKey("BEMERKUNG_FAHRPLAN"))
			{
				((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("BEMERKUNG_FAHRPLAN"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("BEMERKUNG_FAHRPLAN")));
			}
		}
		else if (oAdress.get_bIS_LieferAdresse())
		{
			//dann wird die adresse selbst eingetragen
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("NAME1"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("NAME1")));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("NAME2"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("NAME2")));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("NAME3"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("NAME3")));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("STRASSE"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("STRASSE")));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("HAUSNUMMER"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("HAUSNUMMER")));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("PLZ"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("PLZ")));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("ORT"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("ORT")));
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("ORTZUSATZ"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("ORTZUSATZ")));
			
			//bei der lieferadresse stehen die oeffnungszeiten in der zuordnungstabelle
			RECORD_ADRESSE oADHelp = new RECORD_ADRESSE(oAdress.get_cID_ADRESSE_orig());
			
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("LAENDERCODE"))).set_cActualMaskValue("");
			if (oADHelp.get_UP_RECORD_LAND_id_land()!=null)
			{
				((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("LAENDERCODE"))).set_cActualMaskValue(oADHelp.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""));
			}
			
			((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("OEFFNUNGSZEITEN"))).set_cActualMaskValue(bibALL.null2leer(
					oADHelp.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_OEFFNUNGSZEITEN_cUF_NN("")
					));
			
			
			//dann Tel und Fax neu eintragen, wenn es eintraegen bei der lieferadresse gibt
			if (S.isFull(oAdress.get_StandardTelefonNumber()))
			{
				((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("TEL"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_StandardTelefonNumber()));
			}
			if (S.isFull(oAdress.get_StandardFaxNumber()))
			{
				((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("FAX"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_StandardFaxNumber()));
			}
			
			if (this.hmFieldUebersetzer.containsKey("BEMERKUNG_FAHRPLAN"))
			{
				((MyE2IF__DB_Component)hmFields.get(this.hmFieldUebersetzer.get("BEMERKUNG_FAHRPLAN"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_UnFormatedValue("BEMERKUNG_FAHRPLAN")));
			}

		}
		
		//resetten
		((MyE2_DB_MaskSearchField)hmFields.get(this.hmFieldUebersetzer.get("ID_ADRESSE_LAGER"))).get_oTextFieldForSearchInput().setBackground(Color.WHITE);
		

		if (oAdress.get_bIS_FirmenAdresse())
		{
			//2015-09-21: den anwender zwingen, eine lageradresse auszuwaehlen
			boolean bFillID_ADRESSE_LAGER = true;         //altes verhalten, lageradresse=hauptadresse
			int iAnzahlLager = 0;
			if (this.b_call_is_from_main_adress) {
				if (bib_Settigs_Mandant.get_StoredValue(ENUM_MANDANT_ZUSATZ_FELDNAMEN.ZWINGE_SUCHE_NACH_LIEFERADRESSE_IN_FUHRE)) {
					//nur dann, wenn es lieferadressen gibt
					iAnzahlLager = new RECORD_ADRESSE_extend(oAdress.get_cID_ADRESSE_orig()).get_RECLIST_ADRESSEN_LAGER(true).get_vKeyValues().size();
					if (iAnzahlLager>0) {
						bFillID_ADRESSE_LAGER = false;    //heisst: es wird nicht die hauptadresse vorgeschlagen !
					}
				}
			}
			if (bFillID_ADRESSE_LAGER) {
				((MyE2_DB_MaskSearchField)hmFields.get(this.hmFieldUebersetzer.get("ID_ADRESSE_LAGER"))).get_oTextFieldForSearchInput().setText(bibALL.null2leer(oAdress.get_c_STD_L_ID_ADRESSE()));
				((MyE2_DB_MaskSearchField)hmFields.get(this.hmFieldUebersetzer.get("ID_ADRESSE_LAGER"))).FillLabelWithDBQuery(bibALL.null2leer(oAdress.get_c_STD_L_ID_ADRESSE()));
				((MyE2_DB_MaskSearchField)hmFields.get(this.hmFieldUebersetzer.get("ID_ADRESSE_LAGER"))).set_cActualMaskValue(bibALL.null2leer(oAdress.get_c_STD_L_ID_ADRESSE()));
			} else {
				((MyE2_DB_MaskSearchField)hmFields.get(this.hmFieldUebersetzer.get("ID_ADRESSE_LAGER"))).prepare_ContentForNew(false);
				((MyE2_DB_MaskSearchField)hmFields.get(this.hmFieldUebersetzer.get("ID_ADRESSE_LAGER"))).get_oTextFieldForSearchInput().setBackground(new E2_ColorHelpBackground());
				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die gewählte Firma besitzt ",true," "+iAnzahlLager+" ",false," zusätzliche aktive Lieferadressen/Lager! ",true,"Bitte geben Sie eine Lieferadresse/Lager an",true)));
			}
			if (this.cEK_VK.equals("EK"))
			{
				MyE2_DB_CheckBox oCB = (MyE2_DB_CheckBox)hmFields.get(this.hmFieldUebersetzer.get("LADEMENGE_GUTSCHRIFT"));
				oCB.setSelected(!oAdress.get_bABLADEMENGE_FUER_GUTSCHRIFT());
				//die aktion ausloessen, die die faerbung des abrechnungsrelevanten gewichts anzeigt
				if (oCB.get_vActionAgents().size()>0)
				{
					oCB.get_vActionAgents().get(0).executeAgentCode(new ExecINFO(new MyActionEvent(new ActionEvent(oCB,"")),true));
				}
			}
			else
			{
				MyE2_DB_CheckBox oCB = (MyE2_DB_CheckBox)hmFields.get(this.hmFieldUebersetzer.get("ABLADEMENGE_RECHNUNG"));
				oCB.setSelected(!oAdress.get_bLADEMENGE_FUER_RECHNUNG());
				//die aktion ausloessen, die die faerbung des abrechnungsrelevanten gewichts anzeigt
				if (oCB.get_vActionAgents().size()>0)
				{
					oCB.get_vActionAgents().get(0).executeAgentCode(new ExecINFO(new MyActionEvent(new ActionEvent(oCB,"")),true));
				}
			}
		}
		
	}
	
	
	
	
	//vorgaben fuer die zu fuellenden felder (EK-Seite)
	public static HashMap<String, String> get_UebersetzerMapFuer_EK_Seite()
	{
		HashMap<String, String> oHMRueck = new HashMap<String, String>();
		oHMRueck.put("NAME1", "L_NAME1");
		oHMRueck.put("NAME2", "L_NAME2");
		oHMRueck.put("NAME3", "L_NAME3");
		oHMRueck.put("STRASSE", "L_STRASSE");
		oHMRueck.put("HAUSNUMMER", "L_HAUSNUMMER");
		oHMRueck.put("PLZ", "L_PLZ");
		oHMRueck.put("ORT", "L_ORT");
		oHMRueck.put("ORTZUSATZ", "L_ORTZUSATZ");
		oHMRueck.put("TEL", "TEL_LIEFERANT");
		oHMRueck.put("FAX", "FAX_LIEFERANT");
		oHMRueck.put("OEFFNUNGSZEITEN", "OEFFNUNGSZEITEN_LIEF");
		oHMRueck.put("LAENDERCODE", "L_LAENDERCODE");
		oHMRueck.put("ID_ADRESSE_LAGER", "ID_ADRESSE_LAGER_START");
		oHMRueck.put("LADEMENGE_GUTSCHRIFT", "LADEMENGE_GUTSCHRIFT");
		oHMRueck.put("ABLADEMENGE_RECHNUNG", "ABLADEMENGE_RECHNUNG");
		oHMRueck.put("ABLADEMENGE_RECHNUNG", "ABLADEMENGE_RECHNUNG");
		oHMRueck.put("BEMERKUNG_FAHRPLAN","BEMERKUNG_START_FP");
		
		return oHMRueck;
	}
	
	//vorgaben fuer die zu fuellenden felder (VK-Seite)
	public static HashMap<String, String> get_UebersetzerMapFuer_VK_Seite()
	{
		HashMap<String, String> oHMRueck = new HashMap<String, String>();
		oHMRueck.put("NAME1", "A_NAME1");
		oHMRueck.put("NAME2", "A_NAME2");
		oHMRueck.put("NAME3", "A_NAME3");
		oHMRueck.put("STRASSE", "A_STRASSE");
		oHMRueck.put("HAUSNUMMER", "A_HAUSNUMMER");
		oHMRueck.put("PLZ", "A_PLZ");
		oHMRueck.put("ORT", "A_ORT");
		oHMRueck.put("ORTZUSATZ", "A_ORTZUSATZ");
		oHMRueck.put("TEL", "TEL_ABNEHMER");
		oHMRueck.put("FAX", "FAX_ABNEHMER");
		oHMRueck.put("OEFFNUNGSZEITEN", "OEFFNUNGSZEITEN_ABN");
		oHMRueck.put("LAENDERCODE", "A_LAENDERCODE");
		oHMRueck.put("ID_ADRESSE_LAGER", "ID_ADRESSE_LAGER_ZIEL");
		oHMRueck.put("LADEMENGE_GUTSCHRIFT", "LADEMENGE_GUTSCHRIFT");
		oHMRueck.put("ABLADEMENGE_RECHNUNG", "ABLADEMENGE_RECHNUNG");
		oHMRueck.put("BEMERKUNG_FAHRPLAN","BEMERKUNG_ZIEL_FP");
		
		return oHMRueck;
	}
	

	//vorgaben fuer die zu fuellenden felder (VK-Seite)
	public static HashMap<String, String> get_UebersetzerMapFuer_Zusatzort_Seite()
	{
		HashMap<String, String> oHMRueck = new HashMap<String, String>();
		oHMRueck.put("NAME1", "NAME1");
		oHMRueck.put("NAME2", "NAME2");
		oHMRueck.put("NAME3", "NAME3");
		oHMRueck.put("STRASSE", "STRASSE");
		oHMRueck.put("HAUSNUMMER", "HAUSNUMMER");
		oHMRueck.put("PLZ", "PLZ");
		oHMRueck.put("ORT", "ORT");
		oHMRueck.put("ORTZUSATZ", "ORTZUSATZ");
		oHMRueck.put("TEL", "TEL");
		oHMRueck.put("FAX", "FAX");
		oHMRueck.put("OEFFNUNGSZEITEN", "OEFFNUNGSZEITEN");
		oHMRueck.put("LAENDERCODE", "LAENDERCODE");
		oHMRueck.put("ID_ADRESSE_LAGER", "ID_ADRESSE_LAGER");
		oHMRueck.put("LADEMENGE_GUTSCHRIFT", "LADEMENGE_GUTSCHRIFT");
		oHMRueck.put("ABLADEMENGE_RECHNUNG", "ABLADEMENGE_RECHNUNG");
		
		return oHMRueck;
	}
	


}
