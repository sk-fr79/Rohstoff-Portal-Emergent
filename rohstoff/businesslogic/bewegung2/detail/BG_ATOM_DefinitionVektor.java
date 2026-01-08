package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_PRUEFPROT;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;

public class BG_ATOM_DefinitionVektor extends B2_Abstract_DefinitionVektor {

	public BG_ATOM_DefinitionVektor() throws myException {
		super();

	
		this
		._a(new E2_FieldInfo_Component("ID Atom", 						BG_ATOM.id_bg_atom))
		._a(new E2_FieldInfo_Component("ID Vektor", 					BG_ATOM.id_bg_vektor))
		._a(new E2_FieldInfo_Component("ID Station Quelle", 			BG_ATOM.id_bg_station_quelle))
		._a(new E2_FieldInfo_Component("ID Station Ziel", 				BG_ATOM.id_bg_station_ziel))
		
		._a(new E2_FieldInfo_Component("ID Artikel", 					BG_ATOM.id_artikel))
		._a(new E2_FieldInfo_Component("ID Artikel Bez.", 				BG_ATOM.id_artikel_bez, 			new ownArtikelBezLbl()))
		._a(new E2_FieldInfo_Component("Anr 1", 						BG_ATOM.anr1))
		._a(new E2_FieldInfo_Component("Anr 2.", 						BG_ATOM.anr2))
		._a(new E2_FieldInfo_Component("Artikel Bez. 1", 				BG_ATOM.artbez1))
		._a(new E2_FieldInfo_Component("Artikel Bez. 2", 				BG_ATOM.artbez2))
		
		._a(new E2_FieldInfo_Component("ID EAK Code", 					BG_ATOM.id_eak_code, 				new B2_fieldInfoComp_AVVCode(BG_ATOM.id_eak_code)))
		._a(new E2_FieldInfo_Component("Kontrakt Pos Id", 				BG_ATOM.id_vpos_kon, 				new ownVposKonLbl()))
		._a(new E2_FieldInfo_Component("STD Pos Id", 					BG_ATOM.id_vpos_std,				new ownVposStdLbl()))
		
		._a(new E2_FieldInfo_Component("Tax Id", 						BG_ATOM.id_tax, 			 		new B2_fieldInfoComp_upDetail(TAX.id_tax,TAX.dropdown_text)))
		._a(new E2_FieldInfo_Component("ID Lieferbedingungen", 			BG_ATOM.id_lieferbedingungen,		new B2_fieldInfoComp_upDetail(LIEFERBEDINGUNGEN.id_lieferbedingungen, LIEFERBEDINGUNGEN.kurzbezeichnung)))
		._a(new E2_FieldInfo_Component("Kosten Produkt", 				BG_ATOM.lieferbedingungen))
		._a(new E2_FieldInfo_Component("Zaehlungsbedingung ID",			BG_ATOM.id_zahlungsbedingungen,		new B2_fieldInfoComp_upDetail(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen, ZAHLUNGSBEDINGUNGEN.kurzbezeichnung)))
		._a(new E2_FieldInfo_Component("Zaehlungsbedingung",			BG_ATOM.zahlungsbedingungen,		new B2_fieldInfoComp_upDetail(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen, ZAHLUNGSBEDINGUNGEN.kurzbezeichnung)))

		._a(new E2_FieldInfo_Component("Zolltarifnummer", 				BG_ATOM.id_zolltarifnummer,			new B2_fieldInfoComp_upDetail(ZOLLTARIFNUMMER.id_zolltarifnummer, ZOLLTARIFNUMMER.nummer)))
		
		._a(new E2_FieldInfo_Component("ID rech block", 				BG_ATOM.id_bg_rech_block))
		._a(new E2_FieldInfo_Component("ID Lager Konto", 				BG_ATOM.id_lager_konto))

		._a(new E2_FieldInfo_Component("ID Storno info", 				BG_ATOM.id_bg_storno_info, 			new ownStornoInfo()))
		._a(new E2_FieldInfo_Component("ID Del info", 					BG_ATOM.id_bg_del_info, 			new ownDelInfo()))
		
		._a(new E2_FieldInfo_Component("Manuell Preis", 				BG_ATOM.manuell_preis, 				new B2_fieldInfoComp_JaNeinLabel(BG_ATOM.manuell_preis), null))
		
		._a(new E2_FieldInfo_Component("Waehrung ID", 					BG_ATOM.id_waehrung,				new B2_fieldInfoComp_upDetail(WAEHRUNG.id_waehrung,WAEHRUNG.bezeichnung)))
		._a(new E2_FieldInfo_Component("Waehrungskurs", 				BG_ATOM.waehrungskurs))
		._a(new E2_FieldInfo_Component("E_preis_basiswaehrung", 		BG_ATOM.e_preis_basiswaehrung))
		._a(new E2_FieldInfo_Component("E_preis_fremdwaehrung", 		BG_ATOM.e_preis_fremdwaehrung))
		._a(new E2_FieldInfo_Component("E_preis_res_netto_mge_basis", 	BG_ATOM.e_preis_res_netto_mge_basis))
		._a(new E2_FieldInfo_Component("E_preis_res_netto_mge_fremd", 	BG_ATOM.e_preis_res_netto_mge_fremd))
		._a(new E2_FieldInfo_Component("G_preis_basiswaehrung", 		BG_ATOM.g_preis_basiswaehrung))
		._a(new E2_FieldInfo_Component("G_preis_fremdwaehrung", 		BG_ATOM.g_preis_fremdwaehrung))
		._a(new E2_FieldInfo_Component("G_preis_abzug_basis", 			BG_ATOM.g_preis_abzug_basis))
		._a(new E2_FieldInfo_Component("G_preis_abzug_fremd", 			BG_ATOM.g_preis_abzug_fremd))
		
		._a(new E2_FieldInfo_Component("Menge", 						BG_ATOM.menge))
		._a(new E2_FieldInfo_Component("Abrechnungsmenge", 				BG_ATOM.menge_abrech))
		._a(new E2_FieldInfo_Component("Abzugsmenge", 					BG_ATOM.menge_abzug))
		._a(new E2_FieldInfo_Component("Mettomenge", 					BG_ATOM.menge_netto))
		._a(new E2_FieldInfo_Component("Verteilungsmenge", 				BG_ATOM.menge_verteilung))
		
		._a(new E2_FieldInfo_Component("Intrastat Meldung", 			BG_ATOM.intrastat_meld, 			new B2_fieldInfoComp_JaNeinLabel(BG_ATOM.intrastat_meld), null))
		._a(new E2_FieldInfo_Component("Kontraktzwang", 				BG_ATOM.kontraktzwang, 				new B2_fieldInfoComp_JaNeinLabel(BG_ATOM.intrastat_meld), null))
		._a(new E2_FieldInfo_Component("Intrastat Meldung", 			BG_ATOM.intrastat_meld, 			new B2_fieldInfoComp_JaNeinLabel(BG_ATOM.intrastat_meld), null))
		._a(new E2_FieldInfo_Component("Transit Meldung", 				BG_ATOM.transit_meld, 				new B2_fieldInfoComp_JaNeinLabel(BG_ATOM.transit_meld), null))

		._a(new E2_FieldInfo_Component("ID_bg_pruefprot_abschluss", 	BG_ATOM.id_bg_pruefprot_abschluss, 	new ownPruefProtokollInfo(BG_ATOM.id_bg_pruefprot_abschluss)))
		._a(new E2_FieldInfo_Component("ID_bg_pruefprot_menge", 		BG_ATOM.id_bg_pruefprot_menge,  	new ownPruefProtokollInfo(BG_ATOM.id_bg_pruefprot_menge)))
		._a(new E2_FieldInfo_Component("ID_bg_pruefprot_preis",			BG_ATOM.id_bg_pruefprot_preis,  	new ownPruefProtokollInfo(BG_ATOM.id_bg_pruefprot_preis)))
		
		._a(new E2_FieldInfo_Component("Bestellnummer", 				BG_ATOM.bestellnummer))
		._a(new E2_FieldInfo_Component("Ausfuehrung Datum", 			BG_ATOM.datum_ausfuehrung))
		._a(new E2_FieldInfo_Component("EU Steuer Vermerk", 			BG_ATOM.eu_steuer_vermerk))
		._a(new E2_FieldInfo_Component("Kosten Produkt", 				BG_ATOM.kosten_produkt))
		._a(new E2_FieldInfo_Component("National Abfall Code", 			BG_ATOM.nationaler_abfall_code))
		._a(new E2_FieldInfo_Component("Notifikation Nr.", 				BG_ATOM.notifikation_nr))
		._a(new E2_FieldInfo_Component("Position in Mask", 				BG_ATOM.pos_in_mask))
		._a(new E2_FieldInfo_Component("Postennummer", 					BG_ATOM.postennummer))
		._a(new E2_FieldInfo_Component("Steuersatz", 					BG_ATOM.steuersatz))	
		._a(new E2_FieldInfo_Component("Mask Timestamp", 				BG_ATOM.timestamp_in_mask))
		._a(new E2_FieldInfo_Component("PostenNummer", 					BG_ATOM.postennummer))
		
		._a(new E2_FieldInfo_Component("Bemerkung (Intern)",			BG_ATOM.bemerkung_intern, new B2_fieldInfoComp_textArea(BG_ATOM.bemerkung_intern,200, 3), null))
		._a(new E2_FieldInfo_Component("Bemerkung (Extern)", 			BG_ATOM.bemerkung_extern, new B2_fieldInfoComp_textArea(BG_ATOM.bemerkung_extern,200, 3), null))
		;
	}
	
	private class ownArtikelBezLbl implements IF_FieldInfo_Component{
		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rArtikel = r.get_up_Rec21(ARTIKEL_BEZ.id_artikel_bez);
			RB_lab rueckLabel = new RB_lab();
			if(rArtikel != null) {
				rueckLabel._t(new Rec21_artikel_bez(r.get_up_Rec21(ARTIKEL_BEZ.id_artikel_bez)).__get_ANR1_ANR2_ARTBEZ1());
			}
			return rueckLabel;
		}
	}

	private class ownVposKonLbl implements IF_FieldInfo_Component{
		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rVposKon = r.get_up_Rec21(VPOS_KON.id_vpos_kon);
			RB_lab rueckLabel = new RB_lab();
			if(rVposKon != null) {
				rueckLabel._t(rVposKon.get_up_Rec21(VPOS_KON.id_vkopf_kon, VKOPF_KON.id_vkopf_kon, false).getUfs(VKOPF_KON.buchungsnummer));
			}
			return rueckLabel;
		}
	}
		
	private class ownVposStdLbl implements IF_FieldInfo_Component{
		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rVposKon = r.get_up_Rec21(VPOS_STD.id_vpos_std);
			RB_lab rueckLabel = new RB_lab();
			if(rVposKon != null) {
				rueckLabel._t(rVposKon.get_up_Rec21(VPOS_STD.id_vkopf_std, VKOPF_STD.id_vkopf_std, false).getUfs(VKOPF_STD.buchungsnummer));
			}
			return rueckLabel;
		}
	}

	private class ownStornoInfo implements IF_FieldInfo_Component{
		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rStornoInfo = r.get_up_Rec21(BG_STORNO_INFO.id_bg_storno_info);
			if(rStornoInfo !=  null) {
				String rueckText = new MyDate(rStornoInfo.getUfs(BG_STORNO_INFO.storno_datum,"")).get_cDateStandardFormat() + "-" +
				rStornoInfo.getUfs(BG_STORNO_INFO.storno_grund,"");
				return new RB_lab()._t(rueckText).c();
			}
			return new RB_lab();
		}
	}
	
	private class ownDelInfo implements IF_FieldInfo_Component{
		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rDelINfo = r.get_up_Rec21(BG_DEL_INFO.id_bg_del_info);
			if(rDelINfo !=  null) {
				String rueckText = new MyDate(rDelINfo.getUfs(BG_DEL_INFO.delete_datum,"")).get_cDateStandardFormat() + "-" +
				rDelINfo.getUfs(BG_DEL_INFO.delete_grund,"");
				return new RB_lab()._t(rueckText).c();
			}
			return new RB_lab();
		}
	}
	
	
	private class ownPruefProtokollInfo extends RB_lab implements IF_FieldInfo_Component{
		private IF_Field  field;
		
		public ownPruefProtokollInfo(IF_Field pField) {
			super();
			this.field = pField;
		}
		
		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rPruefProt = r.get_up_Rec21(field, BG_PRUEFPROT.id_bg_pruefprot, false);
			if(rPruefProt !=  null) {
				this._t( rPruefProt.getUfs(BG_PRUEFPROT.en_pruefung_typ,"") );
			}
			return this;
		}
	}


	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung2.detail.B2_Abstract_DefinitionVektor#inner_grid_size()
	 */
	@Override
	public int[] inner_grid_size() {
		return new int[]{200,210,350};
	}
}
