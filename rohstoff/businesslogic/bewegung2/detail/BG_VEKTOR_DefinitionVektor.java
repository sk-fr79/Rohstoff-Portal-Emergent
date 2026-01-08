package rohstoff.businesslogic.bewegung2.detail;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_DEL_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_PRUEFPROT;
import panter.gmbh.basics4project.DB_ENUMS.BG_STORNO_INFO;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.TRANSPORTMITTEL;
import panter.gmbh.basics4project.DB_ENUMS.VERPACKUNGSART;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;

public class BG_VEKTOR_DefinitionVektor extends B2_Abstract_DefinitionVektor {

	public BG_VEKTOR_DefinitionVektor() throws myException {
		super();

		this
		._a(new E2_FieldInfo_Component("ID:", 								BG_VEKTOR.id_bg_vektor, 		null))
		._a(new E2_FieldInfo_Component("ID Vektor base:", 					BG_VEKTOR.id_bg_vektor_base,	null))
		._a(new E2_FieldInfo_Component("ID Vektor sub:",					BG_VEKTOR.id_bg_vektor_sub,		null))
		
		._a(new E2_FieldInfo_Component("ID Transit Land 1:", 				BG_VEKTOR.id_land_transit1, 	new ownLandInfo(BG_VEKTOR.id_land_transit1)))
		._a(new E2_FieldInfo_Component("ID Transit Land 2:", 				BG_VEKTOR.id_land_transit2, 	new ownLandInfo(BG_VEKTOR.id_land_transit2)))
		._a(new E2_FieldInfo_Component("ID Transit Land 3:", 				BG_VEKTOR.id_land_transit3, 	new ownLandInfo(BG_VEKTOR.id_land_transit3)))

		._a(new E2_FieldInfo_Component("ID Logi start:", 					BG_VEKTOR.id_adresse_logi_start,null))
		._a(new E2_FieldInfo_Component("ID logi ziel:", 					BG_VEKTOR.id_adresse_logi_ziel, null))

		._a(new E2_FieldInfo_Component("ID Adresse Fremdware:",				BG_VEKTOR.id_adresse_fremdware,	new ownAdresse(BG_VEKTOR.id_adresse_fremdware)))

		._a(new E2_FieldInfo_Component("ID Adresse Spedition:",				BG_VEKTOR.id_adresse_spedition,	new ownAdresse(BG_VEKTOR.id_adresse_spedition)))
		._a(new E2_FieldInfo_Component("ID Transportmittel:", 				BG_VEKTOR.id_transportmittel,	new B2_fieldInfoComp_upDetail(TRANSPORTMITTEL.id_transportmittel, TRANSPORTMITTEL.beschreibung)))

		._a(new E2_FieldInfo_Component("ID Handelsdef.:",					BG_VEKTOR.id_handelsdef,		null))//(r)->{return new RB_lab()._t(r.get_up_Rec21(HANDELSDEF.id_handelsdef).get_fs_dbVal(HANDELSDEF.,"")).c();}))

		._a(new E2_FieldInfo_Component("Print Anhang 7:",					BG_VEKTOR.print_anhang7,		new B2_fieldInfoComp_JaNeinLabel(BG_VEKTOR.print_anhang7), 		null))
		._a(new E2_FieldInfo_Component("Ek_vk_zuord_zwang:",				BG_VEKTOR.ek_vk_zuord_zwang, 	new B2_fieldInfoComp_JaNeinLabel(BG_VEKTOR.ek_vk_zuord_zwang), 	null))

		._a(new E2_FieldInfo_Component("ID storno info",					BG_VEKTOR.id_bg_storno_info ,  	new ownStornoInfo() ))
		._a(new E2_FieldInfo_Component("ID del info",						BG_VEKTOR.id_bg_del_info ,  	new ownDelInfo() ))
		._a(new E2_FieldInfo_Component("ID Verpackungsart",					BG_VEKTOR.id_bg_del_info ,  	new ownVerpackungsart()))

		._a(new E2_FieldInfo_Component("ID_uma_kontrakt:", 					BG_VEKTOR.id_uma_kontrakt,		null))
		
		._a(new E2_FieldInfo_Component("Transport typ:", 					BG_VEKTOR.en_transport_typ,		new ownTransportTyp(), null))
		
		._a(new E2_FieldInfo_Component("Vektor Status:",					BG_VEKTOR.en_vektor_status		))
		._a(new E2_FieldInfo_Component("Vektor Quelle:",					BG_VEKTOR.en_vektor_quelle		))
		._a(new E2_FieldInfo_Component("Position:",							BG_VEKTOR.posnr					))
		
		._a(new E2_FieldInfo_Component("Transport verantwortung:", 			BG_VEKTOR.transportverantwortung))
		._a(new E2_FieldInfo_Component("Planung Datum von:", 				BG_VEKTOR.datum_planung_von		))
		._a(new E2_FieldInfo_Component("Planung Datum bis:", 				BG_VEKTOR.datum_planung_bis		))
		._a(new E2_FieldInfo_Component("Buchungsnr.:", 						BG_VEKTOR.buchungsnummer		))
		._a(new E2_FieldInfo_Component("Planmenge:", 						BG_VEKTOR.planmenge))
		._a(new E2_FieldInfo_Component("Transportmittel:", 				BG_VEKTOR.transportmittel))
		._a(new E2_FieldInfo_Component("Transportkennzeichen:", 			BG_VEKTOR.transportkennzeichen))
		._a(new E2_FieldInfo_Component("Anhaengerkennzeichen:", 			BG_VEKTOR.anhaengerkennzeichen))
		._a(new E2_FieldInfo_Component("TPA Lieferinfo:", 					BG_VEKTOR.lieferinfo_tpa))
		._a(new E2_FieldInfo_Component("Ordnungsnr.:", 						BG_VEKTOR.ordnungsnummer_cmr))
		._a(new E2_FieldInfo_Component("Kosten Transport :", 				BG_VEKTOR.kosten_transport))
		._a(new E2_FieldInfo_Component("Menge (EU Blatt):", 				BG_VEKTOR.ah7_menge))
		._a(new E2_FieldInfo_Component("Volume (EU Blatt):", 				BG_VEKTOR.ah7_volumen))
		
		._a(new E2_FieldInfo_Component("AVV Austellung Datum:", 			BG_VEKTOR.ah7_ausstellung_datum))
		._a(new E2_FieldInfo_Component("ID_bg_pruefprot_abschluss:", 		BG_VEKTOR.id_bg_pruefprot_abschluss	,		new ownPruefProtokollInfo(BG_VEKTOR.id_bg_pruefprot_abschluss)))
		
//		._a(new E2_FieldInfo_Component("AVV Austellung Datum:", 			BG_VEKTOR.avv_ausstellung_datum))
//		._a(new E2_FieldInfo_Component("Id_bg_pruefport_gelangensbest:", 	BG_VEKTOR.id_bg_pruefport_gelangensbest))
//		._a(new E2_FieldInfo_Component("id_bg_pruefprot_abschluss:", 		BG_VEKTOR.id_bg_pruefprot_abschluss))
		
		;

		
	}

	private class ownLandInfo implements IF_FieldInfo_Component{

		private IF_Field field;
		
		public ownLandInfo(IF_Field pField) {
			super();
			field = pField;
		}

		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rLand = r.get_up_Rec21( field, LAND.id_land,false);
			if(rLand !=  null) {
				return new RB_lab()._t(rLand.get_ufs_dbVal(LAND.beschreibung,"")).c();
			}
			return new RB_lab();
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
	
	private class ownVerpackungsart implements IF_FieldInfo_Component{
		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rVerpackungsart = r.get_up_Rec21(VERPACKUNGSART.id_verpackungsart);
			if(rVerpackungsart !=  null) {
				return new RB_lab()._t(rVerpackungsart.getUfs(VERPACKUNGSART.verpackungsart,"")).c();
			}
			return new RB_lab();
		}
	}
	
	private class ownAdresse implements IF_FieldInfo_Component{
		
		private IF_Field field;
		
		public ownAdresse(IF_Field pField) {
			super();
			field = pField;
		}
		
		@Override
		public Component get_component(Rec21 r) throws myException {
			Rec21 rtmp = r.get_up_Rec21(field, ADRESSE.id_adresse, false);
			RB_lab rueckC = new RB_lab();
			if(rtmp !=  null) {
				Rec21_adresse rAdresse = new Rec21_adresse(rtmp);
				rueckC._t(rAdresse.__get_name1_ort()).c();
			}
			return rueckC;
		}
	}
	
	private class ownTransportTyp implements IF_FieldInfo_Component{

		@Override
		public Component get_component(Rec21 r) throws myException {
			String trpTyp = r.getUfs(BG_VEKTOR.en_transport_typ,"");
			if(S.isFull(trpTyp)) {
				EnTransportTyp oTyp = EnTransportTyp.valueOf(trpTyp);
				return new RB_lab(oTyp.userText());
			}else return new RB_lab();
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
