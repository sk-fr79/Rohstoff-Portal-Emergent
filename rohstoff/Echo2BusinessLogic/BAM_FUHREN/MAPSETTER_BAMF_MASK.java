/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyAdress;

public class MAPSETTER_BAMF_MASK extends XX_MAP_SettingAgent
{

	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{

		// grundstellung
		oMap.set_DisabledFromInteractiv_ALL(false);

		//2016-12-20
		((MyE2_DBC_CrossConnection)oMap.get__Comp(BAMF__CONST.FIELDNAME_CROSSREFERENCE_BAM_USER)).EXT().set_bDisabledFromBasic(false);

		
		BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW recFuhreAusView = ((BAMF_MASK_ComponentMAP) oMap).get_recFuhreAusView();

		// neueingabe ist nur aus der fuhre moeglich, dort stehen die Fuhren/-
		// Fuhrenort-IDs in der SQLFieldMAP
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
		{

			/*
			 * dann wird einiges an content gefuellt
			 */
			String cID_ADRESSE_EK = recFuhreAusView.get_ID_ADRESSE_START_cUF();

			if (!bibALL.isEmpty(cID_ADRESSE_EK))
			{
				MyAdress oAdressEK = new MyAdress(cID_ADRESSE_EK, false);
//				((MyE2IF__DB_Component) oMap.get("WM_ANSPRECHPARTNER_LIEFERANT")).set_cActual_Formated_DBContent_To_Mask(oAdressEK.get_StandardAnsprechpartner(true, false, true, true, false, false), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
				((MyE2IF__DB_Component) oMap.get("WM_FAXNUMMER")).set_cActual_Formated_DBContent_To_Mask(oAdressEK.get_StandardFaxNumber(), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
			}
			((MyE2IF__DB_Component) oMap.get("WM_ANLIEFERDATUM")).set_cActual_Formated_DBContent_To_Mask(recFuhreAusView.get_DATUM_ABLADEN_cF_NN(""), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
			((MyE2IF__DB_Component) oMap.get("WM_ABHOLDATUM")).set_cActual_Formated_DBContent_To_Mask(recFuhreAusView.get_DATUM_AUFLADEN_cF_NN(""), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
			((MyE2IF__DB_Component) oMap.get("WM_ARTBEZ1")).set_cActual_Formated_DBContent_To_Mask(recFuhreAusView.get_ARTBEZ1_EK_cF_NN(""), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
			// ((MyE2IF__DB_Component)oMap.get("WM_MENGE_ABLADEN")).set_cActual_Formated_DBContent_To_Mask(recFuhreAusView.get_ANTEIL_ABLADEMENGE_ABN_cF_NN(""),E2_ComponentMAP.STATUS_NEW_EMPTY,
			// null);
			// 2012-10-30: gewicht muss sein: relevantes abrechnungsgewicht des
			// lieferanten
			// falls es sich um die hauptfuhre oder einen EK-fuhrenort handelt,
			// dann wird die relevante abrechnungsmenge genommen
			String cID_VPOS_TPA_FUHRE_ORT = recFuhreAusView.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("");
			if (S.isFull(cID_VPOS_TPA_FUHRE_ORT))
			{
				RECORD_VPOS_TPA_FUHRE_ORT recOrt = new RECORD_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT);
				if (recOrt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
				{
					if (recOrt.is_LADEMENGE_GUTSCHRIFT_YES())
					{
						((MyE2IF__DB_Component)oMap.get("WM_MENGE_ABLADEN")).set_cActual_Formated_DBContent_To_Mask(recOrt.get_ANTEIL_LADEMENGE_cF_NN(""),E2_ComponentMAP.STATUS_NEW_EMPTY,null);
					}
					else
					{
						((MyE2IF__DB_Component)oMap.get("WM_MENGE_ABLADEN")).set_cActual_Formated_DBContent_To_Mask(recOrt.get_ANTEIL_ABLADEMENGE_cF_NN(""),E2_ComponentMAP.STATUS_NEW_EMPTY,null);
					}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Bei Zusatzabladeorten MUSS die Menge auf dem Reiter <Weigermeldung> manuell gesetzt werden !!")));
				}
			}
			else
			{
				if (recFuhreAusView.is_LADEMENGE_GUTSCHRIFT_YES())
				{
					((MyE2IF__DB_Component)oMap.get("WM_MENGE_ABLADEN")).set_cActual_Formated_DBContent_To_Mask(recFuhreAusView.get_ANTEIL_LADEMENGE_LIEF_cF_NN(""),E2_ComponentMAP.STATUS_NEW_EMPTY, null);
				}
				else
				{
					((MyE2IF__DB_Component)oMap.get("WM_MENGE_ABLADEN")).set_cActual_Formated_DBContent_To_Mask(recFuhreAusView.get_ANTEIL_ABLADEMENGE_LIEF_cF_NN(""),E2_ComponentMAP.STATUS_NEW_EMPTY, null);
				}
			}

			((MyE2IF__DB_Component) oMap.get("WM_ORT")).set_cActual_Formated_DBContent_To_Mask(bibALL.get_RECORD_MANDANT().get_ORT_cF_NN(""), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
			((MyE2IF__DB_Component) oMap.get("WM_DATUM")).set_cActual_Formated_DBContent_To_Mask(bibALL.get_cDateNOW(), E2_ComponentMAP.STATUS_NEW_EMPTY, null);

			((MyE2IF__DB_Component) oMap.get("BAM_DATUM")).set_cActual_Formated_DBContent_To_Mask(recFuhreAusView.get_DATUM_ABLADEN_cF_NN(""), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
			((MyE2IF__DB_Component) oMap.get("BAM_ORT")).set_cActual_Formated_DBContent_To_Mask(recFuhreAusView.get_A_ORT_cF_NN(""), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
			((MyE2IF__DB_Component) oMap.get("DATUM_AUSSTELLUNG")).set_cActual_Formated_DBContent_To_Mask(bibALL.get_cDateNOW(), E2_ComponentMAP.STATUS_NEW_EMPTY, null);
		}

		// beim aendern zieht er alles aus der ComponentMAP
		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{

			boolean bAllesSperren_wegen_RG_Positionen = !recFuhreAusView.get_b_FBAM_CAN_BE_SAVED();

			if (bAllesSperren_wegen_RG_Positionen)
			{
				oMap.set_All_DB_ComponentsAsDisableFromInteractive();
				oMap.get__Comp(BAMF__CONST.FIELDNAME_BUTTON_COMPOSE_FEHLERBESCHREIBUNG).EXT().set_bDisabledFromInteractive(true);
				oMap.get__Comp(BAMF__CONST.FIELDNAME_FBAM_RESET_WEIGERMELDUNGSZAEHLER).EXT().set_bDisabledFromInteractive(true);
			}
			else
			{
				if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ABGESCHLOSSEN_BEHEBUNG").equals("Y"))
				{
					((MyE2_DB_SelectField) oMap.get("ID_USER_BEHEBUNG")).EXT().set_bDisabledFromInteractive(true);
					((MyE2_DB_CheckBox) oMap.get("ABGESCHLOSSEN_BEHEBUNG")).EXT().set_bDisabledFromInteractive(true);
				}
				if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ABGESCHLOSSEN_KONTROLLE").equals("Y")) {
					oMap.set_DisabledFromInteractive(BAMF__CONST.FBAM_LOCKLIST_BAM, ":", true);
				}

				if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("WM_GESPERRT").equals("Y")) {
					oMap.set_DisabledFromInteractive(BAMF__CONST.FBAM_LOCKLIST_WEIGERMELDUNG, ":", true);
				}
				
				//2016-12-20: wenn der anwender nicht der ist, der die bam gemacht hat, koennen verteiler nicht geaendert werden
				boolean sperren_verteilerliste = true;
				try {
					RECORD_FBAM recFBAM = new RECORD_FBAM(oMap.get_oInternalSQLResultMAP().get_LActualDBValue(FBAM.id_fbam.fn(), false));
					String user_erzeuger = recFBAM.ufs(FBAM.erzeugt_von,"");
					String user_aktiv = bibALL.get_RECORD_USER().ufs(USER.kuerzel,"#");
					boolean b_geschaeftsfuehrer = bibALL.get_RECORD_USER().is_DEVELOPER_YES()||bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES();
					if (user_erzeuger.equals(user_aktiv) || b_geschaeftsfuehrer) {
						sperren_verteilerliste=false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (sperren_verteilerliste) {
					((MyE2_DBC_CrossConnection)oMap.get__Comp(BAMF__CONST.FIELDNAME_CROSSREFERENCE_BAM_USER)).EXT().set_bDisabledFromBasic(true);
				}
				//2016-12-20: ende
				
			}
		}
	}

	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}

}