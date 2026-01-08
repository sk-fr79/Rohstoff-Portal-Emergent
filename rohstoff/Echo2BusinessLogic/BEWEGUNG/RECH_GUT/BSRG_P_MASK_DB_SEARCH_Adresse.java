package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_MWST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_K_MASK_COMP_SEARCH_ADRESS_AND_SETMASK_FIELDS;
import rohstoff.utils.AdressPopUP_FOR_DB_SEARCH_Adresse;
import rohstoff.utils.MyAdressLIGHT;
import rohstoff.utils.ECHO2.DB_SEARCH_Adress;

public class BSRG_P_MASK_DB_SEARCH_Adresse extends DB_SEARCH_Adress

{
	/*
	 * steuert die anzeige der warnmeldung. Wenn ownMaskSettings.doMaskSettingsAfterValueWrittenInMaskField() nicht von dem
	 * Element selbst, sondern von einem externen aufruf gestartet wird, dann kann dieser Schalter fuer einen durchlauf deaktiviert
	 * werden. beim naechsten mal ist er automatisch wieder aktiv
	 */


	public BSRG_P_MASK_DB_SEARCH_Adresse(SQLField osqlField) throws myException
	{
		super( osqlField);
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskSettings());

		this.set_AddOnComponent(new AdressPopUP_FOR_DB_SEARCH_Adresse());
		this.get_oTextFieldForSearchInput().set_iWidthPixel(75);
		this.get_oTextForAnzeige().setWidth(new Extent(350));
		this.get_oTextForAnzeige().setFont(new E2_FontPlain());
	}


	private class ownMaskSettings extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) throws myException
		{
			if (bibALL.isEmpty(cMaskValue))
				return;
			
			if (!bAfterAction)
				return;
			
			String cID_ADRESSE_UF = bibALL.ReplaceTeilString(cMaskValue, ".", "");
			
			//felder fuellen 
			MyAdressLIGHT   oAdress = new MyAdressLIGHT(cID_ADRESSE_UF);
//			RECORD_ADRESSE  recAdress = new RECORD_ADRESSE(cID_ADRESSE_UF);
			
			
			// maske füellen
			E2_ComponentMAP oMap = oSearchField.EXT().get_oComponentMAP();

			//zahlungsbedingungen
			String cID_UF_Zahlungsbed = oAdress.get_UnFormatedValue("ID_ZAHLUNGSBEDINGUNGEN");
			if (!bibALL.isEmpty(cID_UF_Zahlungsbed))
			{
				RECORD_ZAHLUNGSBEDINGUNGEN oMZ = new RECORD_ZAHLUNGSBEDINGUNGEN(cID_UF_Zahlungsbed);
				
				((MyE2_DB_SelectField)oMap.get__Comp("ID_ZAHLUNGSBEDINGUNGEN")).set_ActiveValue_OR_FirstValue(oMZ.get_ID_ZAHLUNGSBEDINGUNGEN_cF());
				((MyE2IF__DB_Component)oMap.get__Comp("ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(oMZ.get_BEZEICHNUNG_cF_NN(""));
				((MyE2IF__DB_Component)oMap.get__Comp("ZAHLTAGE")).set_cActualMaskValue(oMZ.get_ZAHLTAGE_cF_NN(""));
				((MyE2IF__DB_Component)oMap.get__Comp("FIXMONAT")).set_cActualMaskValue(oMZ.get_FIXMONAT_cF_NN(""));
				((MyE2IF__DB_Component)oMap.get__Comp("FIXTAG")).set_cActualMaskValue(oMZ.get_FIXTAG_cF_NN(""));
				((MyE2IF__DB_Component)oMap.get__Comp("SKONTO_PROZENT")).set_cActualMaskValue(oMZ.get_SKONTO_PROZENT_cF_NN(""));
			}
			else
			{
				((MyE2_DB_SelectField)oMap.get__Comp("ID_ZAHLUNGSBEDINGUNGEN")).set_ActiveValue_OR_FirstValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("ZAHLTAGE")).set_cActualMaskValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("FIXMONAT")).set_cActualMaskValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("FIXTAG")).set_cActualMaskValue("");
				((MyE2IF__DB_Component)oMap.get__Comp("SKONTO_PROZENT")).set_cActualMaskValue("");
			}
			
			((MyE2IF__DB_Component)oMap.get__Comp("LIEFERBEDINGUNGEN")).set_cActualMaskValue(oAdress.get_LIEFERBEDINGUNGEN_KLARTEXT());
			((MyE2IF__DB_Component)oMap.get__Comp("STEUERSATZ")).set_cActualMaskValue("");
			((BS_ComboBox_MWST)oMap.get__Comp("STEUERSATZ")).set_Varianten(oAdress.get_MWST_SAETZE(true),"-",null, false);
			((MyE2_SelectField)oMap.get__Comp("ID_WAEHRUNG_FREMD")).set_ActiveValue(oAdress.get_FormatedValue("ID_WAEHRUNG"));
			
			if (!(S.NN(oAdress.get_UnFormatedValue("ID_WAEHRUNG")).equals(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF_NN(""))))
			{
				((MyE2_DB_TextField) oMap.get__Comp("WAEHRUNGSKURS")).set_cActualMaskValue("");
			}

			// der kontrakt wird zwingend geloescht
			((MyE2_DB_MaskSearchField) oMap.get__Comp("ID_VPOS_KON_ZUGEORD")).set_cActualMaskValue("");
			((MyE2_DB_MaskSearchField) oMap.get__Comp("ID_VPOS_KON_ZUGEORD")).FillLabelWithDBQuery("");
			
			
			
			
			String cLagerVorzeichen=((MyE2_DB_SelectField)oMap.get__Comp("LAGER_VORZEICHEN")).get_ActualWert();
			
			if (cLagerVorzeichen.equals("1"))
			{
				((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_STEUER")).set_cActualMaskValue(oAdress.get_b_OHNE_SteuerBeiWarenEingang()?"Y":"N");
			}
			if (cLagerVorzeichen.equals("-1"))
			{
				((MyE2_DB_CheckBox)oMap.get__Comp("OHNE_STEUER")).set_cActualMaskValue(oAdress.get_b_OHNE_SteuerBeiWarenAusgang()?"Y":"N");
			}
			
			//2012-05-16: meldungen einblenden
			new __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN(cID_ADRESSE_UF, bibALL.get_Vector(myCONST.VORGANGSART_GUTSCHRIFT,myCONST.VORGANGSART_RECHNUNG)).ACTIVATE_MESSAGES();
		}
	}
	

	/*
	 *  ueberschriebene methode, sorgt dafuer, dass der masken-lade-button fuer die adresse immer an ist  
	 */
	public void set_bEnabled_For_Edit(boolean Enabled) throws myException
	{
		boolean enabled = Enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		this.get_oTextFieldForSearchInput().set_bEnabled_For_Edit(enabled);
		this.get_buttonStartSearch().set_bEnabled_For_Edit(enabled);
		this.get_oTextFieldForSearchInput().setStyle(this.get_oTextFieldForSearchInput().EXT().get_STYLE_FACTORY().get_Style(enabled,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
	}

}
