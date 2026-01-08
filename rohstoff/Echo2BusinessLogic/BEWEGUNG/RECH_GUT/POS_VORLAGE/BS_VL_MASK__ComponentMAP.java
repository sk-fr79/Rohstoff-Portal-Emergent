package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComboBox_EINHEIT_PREIS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_ComponentMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG_P_MASK_COMP_LAGERVORZEICHEN;

public class BS_VL_MASK__ComponentMAP extends BS_ComponentMAP {

	private E2_BasicModuleContainer_MASK  oMaskContainer = null;
	
	/*
	 * benutzung durch: Rechnung-Gutschrift-Modul, 	oBS_Setting != null
	 * freie positionen:							oBS_Setting == null 
	 */
	public BS_VL_MASK__ComponentMAP(E2_BasicModuleContainer_MASK  MaskContainer ) throws myException 
	{
		super(new BS_VL_MASK_SQLFieldMAP());
		
		this.oMaskContainer = MaskContainer;
		
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_VPOS_RG_VL"),true,60),	new MyE2_String("ID Pos"));
		this.add_Component(new BS_VL_MASK_DB_SEARCH_ArtikelBez(oFM.get_("ID_ARTIKEL_BEZ"),	null),	new MyE2_String("ID-Art-Bez."));
		
		
		/*
		 * 	
		 * haengt folgende felder an:
		 * "ANZAHL"
		 * "ARTBEZ1"
		 * "ARTBEZ2"
		 * "EINZELPREIS"
		 * "GESAMTPREIS"
		 * "EINZELPREIS_ABZUG"
		 * "EINZELPREIS_RESULT"
		 * "GESAMTPREIS_ABZUG"
		 * "EINZELPREIS_FW"
		 * "GESAMTPREIS_FW"
		 * "EINZELPREIS_ABZUG_FW"
		 * "EINZELPREIS_RESULT_FW"
		 * "GESAMTPREIS_ABZUG_FW"
		 * "WAEHRUNGSKURS"
		 * ANR1
		 * ANR2
		 * ID_ARTIKEL
		 * POSITIONSNUMMER
		 * MWST
		 * LIEFERBEDINGUNGEN
		 * ZAHLUNGSBEDINGUNGEN
		 * ID_ZAHLUNGSBEDINGUNGEN
		 * ID_WAEHRUNG_FREMD
		 * ZAHLTAGE
		 * FIXMONAT
		 * FIXTAG
		 * SKONTO_PROZENT
		 */ 
		BS__CompMap_FieldMAP_Gemeinsamkeiten.add_Basic_POSITION_Fields_To_ComponentMap(this, oFM, null,"RG");

		this.add_Component(new MyE2_DB_TextField(oFM.get_("AUSFUEHRUNGSDATUM"),true,120),	new MyE2_String("Leistungdatum"));

		this.add_Component(new BS_ComboBox_EINHEIT(oFM),new MyE2_String("Mg.-EH"));
		this.add_Component(new BS_ComboBox_EINHEIT_PREIS(oFM),new MyE2_String("Pr.-EH"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("MENGENDIVISOR"),true,120),new MyE2_String("Divisor"));
		
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ZAHLUNGSBED_CALC_DATUM"),true,120),new MyE2_String("Zahldatum"));

		// deaktivierte Anzeigen, was die abzuege betrifft
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ANZAHL_ABZUG"),true,120),			new MyE2_String("Abzug Menge"));

		//Lagervorzeichen
		this.add_Component(new BSRG_P_MASK_COMP_LAGERVORZEICHEN(oFM, false),	new MyE2_String("Warenein-/Ausgang"));


		
		//festlegungen
		this.get__Comp("ANR1").EXT().set_bDisabledFromBasic(true);
		this.get__Comp("ANR2").EXT().set_bDisabledFromBasic(true);
		this.get__Comp("ID_ARTIKEL").EXT().set_bDisabledFromBasic(true);
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("OHNE_STEUER")), new MyE2_String("Beleg ohne Steuer"));
		
		
		//preis-felder formatieren
		BS__CompMap_FieldMAP_Gemeinsamkeiten.format_BasicPositionFields(this);

		//preisfelder sperren 
		BS__CompMap_FieldMAP_Gemeinsamkeiten.set_READ_ONLY_Fields(this);

		this.add_Component(new MyE2_DB_TextField(oFM.get_("EINTRAG_FUER_DROPDOWN"),true,300),new MyE2_String("Anzeige in der Auswahl"));
		this.add_Component(new BS_VL_DropDown_Verteiler(oFM.get_("VERTEILER")),new MyE2_String("Verteiler"));

		// hier werden jetzt die masken-labels erzeugt, die ein waehrungssymbol bekommen sollen.
		// damit sie wiedergefunden werden, muessen sie in der E2_ComponentMAP vorhanden sein
		this.add_Component(BS__CONST.HASH_WS_EPREIS_ABZUG, new MyE2_Label(new MyE2_String("Abzug EPreis #FW# "),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		this.add_Component(BS__CONST.HASH_WS_GPREIS_ABZUG, new MyE2_Label(new MyE2_String("Abzug GPreis #FW#"),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		this.add_Component(BS__CONST.HASH_WS_EPREIS_RESULT, new MyE2_Label(new MyE2_String("Res. EPreis #FW#"),MyE2_Label.STYLE_SMALL_ITALIC()), new MyE2_String(""));
		
		this.add_oMAPValidator(new BL_VL_MASK_MapValidator());
		
		this.set_oMAPSettingAgent(new BS_VL_MASK__ComponentMAP_MapSettingAgent());
	}



	public E2_BasicModuleContainer_MASK get_oMaskContainer()
	{
		return oMaskContainer;
	}


	
	
}
