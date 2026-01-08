package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWEGUNG;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class ATOM_LAG_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public ATOM_LAG_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_BEWEGUNG_ATOM", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("ABGERECHNET").set_cDefaultValueFormated("");
		this.get_("ABRECHENBAR").set_cDefaultValueFormated("");
		this.get_("ABZUG_MENGE").set_cDefaultValueFormated("");
		this.get_("ARTBEZ1").set_cDefaultValueFormated("");
		this.get_("ARTBEZ2").set_cDefaultValueFormated("");
		this.get_("BEMERKUNG").set_cDefaultValueFormated("");
		this.get_("BEMERKUNG_SACHBEARBEITER").set_cDefaultValueFormated("");

		this.get_("BUCHUNGSNUMMER").set_cDefaultValueFormated("");
		this.get_("DEL_DATE").set_cDefaultValueFormated("");
		this.get_("DELETED").set_cDefaultValueFormated("");
		this.get_("DEL_GRUND").set_cDefaultValueFormated("");
		this.get_("DEL_KUERZEL").set_cDefaultValueFormated("");
		this.get_("E_PREIS").set_cDefaultValueFormated("");
		this.get_("E_PREIS_RESULT_NETTO_MGE").set_cDefaultValueFormated("");
		this.get_("EU_STEUER_VERMERK").set_cDefaultValueFormated("");
		this.get_("ID_ARTIKEL").set_cDefaultValueFormated("");
		this.get_("ID_ARTIKEL_BEZ").set_cDefaultValueFormated("");
		this.get_("ID_BEWEGUNG").set_cDefaultValueFormated("");
		this.get_("ID_BEWEGUNG_ATOM").set_cDefaultValueFormated("");
		this.get_("ID_BEWEGUNG_VEKTOR").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_KON").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_STD").set_cDefaultValueFormated("");
		this.get_("KONTRAKTZWANG").set_cDefaultValueFormated("");
		this.get_("KONTRAKTZWANG_AUS_AM").set_cDefaultValueFormated("");
		this.get_("KONTRAKTZWANG_AUS_GRUND").set_cDefaultValueFormated("");
		this.get_("KONTRAKTZWANG_AUS_VON").set_cDefaultValueFormated("");
		this.get_("LEISTUNGSDATUM").set_cDefaultValueFormated("");
		this.get_("LIEFERINFO_TPA").set_cDefaultValueFormated("");
		this.get_("MENGE").set_cDefaultValueFormated("");
		this.get_("NATIONALER_ABFALL_CODE").set_cDefaultValueFormated("");
		this.get_("NOTIFIKATION_NR").set_cDefaultValueFormated("");
		this.get_("ORDNUNGSNUMMER_CMR").set_cDefaultValueFormated("");
		this.get_("PLANMENGE").set_cDefaultValueFormated("");
		this.get_("POSNR").set_cDefaultValueFormated("");
		this.get_("POSTENNUMMER").set_cDefaultValueFormated("");
		this.get_("PREISERMITTLUNG").set_cDefaultValueFormated("");
		this.get_("PRUEFUNG_MENGE").set_cDefaultValueFormated("");
		this.get_("PRUEFUNG_MENGE_AM").set_cDefaultValueFormated("");
		this.get_("PRUEFUNG_MENGE_VON").set_cDefaultValueFormated("");
		this.get_("SETZKASTEN_KOMPLETT").set_cDefaultValueFormated("");
		this.get_("STEUERSATZ").set_cDefaultValueFormated("");
		this.get_("STORNIERT").set_cDefaultValueFormated("");
		this.get_("STORNIERT_AM").set_cDefaultValueFormated("");
		this.get_("STORNIERT_GRUND").set_cDefaultValueFormated("");
		this.get_("STORNIERT_VON").set_cDefaultValueFormated("");
		this.get_("ZEITSTEMPEL").set_cDefaultValueFormated("");
		this.get_("ABGERECHNET").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ABRECHENBAR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ABZUG_MENGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ARTBEZ1").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ARTBEZ2").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("BEMERKUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("BEMERKUNG_SACHBEARBEITER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("BUCHUNGSNUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DEL_DATE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DEL_GRUND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("DEL_KUERZEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("E_PREIS").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("E_PREIS_RESULT_NETTO_MGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("EU_STEUER_VERMERK").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ARTIKEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_ARTIKEL_BEZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_BEWEGUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_BEWEGUNG_ATOM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_BEWEGUNG_VEKTOR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_VPOS_KON").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ID_VPOS_STD").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("KONTRAKTZWANG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("KONTRAKTZWANG_AUS_AM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("KONTRAKTZWANG_AUS_GRUND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("KONTRAKTZWANG_AUS_VON").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("LEISTUNGSDATUM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("LIEFERINFO_TPA").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("MENGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("NATIONALER_ABFALL_CODE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("NOTIFIKATION_NR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ORDNUNGSNUMMER_CMR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PLANMENGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("POSNR").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("POSTENNUMMER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PREISERMITTLUNG").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PRUEFUNG_MENGE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PRUEFUNG_MENGE_AM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("PRUEFUNG_MENGE_VON").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("SETZKASTEN_KOMPLETT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STEUERSATZ").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STORNIERT").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STORNIERT_AM").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STORNIERT_GRUND").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("STORNIERT_VON").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		this.get_("ZEITSTEMPEL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		/*
		 * beispiel fuer felder
		 *		
		this.get_("GENERIERUNGSDATUM").set_cDefaultValueFormated(bibALL.get_cDateNOW());
		this.get_("ERLEDIGT").set_cDefaultValueFormated("N");
		this.get_("ID_USER").set_cDefaultValueFormated(bibALL.get_ID_USER_FORMATTED(bibE2.get_CurrSession()));
		*/

		this.initFields();
	}

}
