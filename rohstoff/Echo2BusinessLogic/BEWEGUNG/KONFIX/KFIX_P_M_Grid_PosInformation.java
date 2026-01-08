package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_KON;

public class KFIX_P_M_Grid_PosInformation extends E2_Grid implements IF_RB_Component {

	private RB_KF key;

	public KFIX_P_M_Grid_PosInformation() throws myException {
		super();
	}


	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void mark_MustField() throws myException {}

	@Override
	public void mark_Disabled() throws myException {}

	@Override
	public void mark_FalseInput() throws myException {}

	@Override
	public void set_Alignment(Alignment align) throws myException {}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		
		this._bo_ddd();
		
		//hier beschaffen des kopf-rec20
		KFIX_P_M__DataObjectCollector do_col = (KFIX_P_M__DataObjectCollector)dataObject.rb_get_belongs_to();
		Rec20_VKOPF_KON ext_rect_kopf = new Rec20_VKOPF_KON(do_col.getRecordKopf());
		boolean isFixi = ext_rect_kopf.is_yes_db_val(VKOPF_KON.ist_fixierung);
		String  c_typ = ext_rect_kopf.get_ufs_dbVal(VKOPF_KON.vorgang_typ).equals(myCONST_ENUM.VORGANGSART.EK_KONTRAKT.get_DBValue())?"EK":"VK";
		
		boolean isNew = dataObject.rec20().is_newRecordSet();
		
		RB_gld gld = new RB_gld()._left_mid()._col(new E2_ColorDDark())._ins(2, 2, 2, 2);

		this._clear();
		
		this._setSize(50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50)
						._a(new RB_lab(new MyE2_String(isFixi?"Fixierungskontrakt ":"Standardkontrakt").ut(" ("+c_typ+")")), 	gld._c()._span(3))
						._a(new RB_lab(ext_rect_kopf.getBuchungsnummerOrId())._ttt("Buchungsnummer"),							gld._c()._span(2))
						._a(new RB_lab(ext_rect_kopf.get_adresse_name())._ttt("Firma"),											gld._c()._span(9))
						;
		
		if (isFixi) {
			this
			._a(new RB_lab("Fix.Ges.Menge"), 															gld._c()._span(2)._right_mid())
			._a(new RB_lab(ext_rect_kopf.get_myBigDecimal_dbVal(
						VKOPF_KON.fix_menge_gesamt,new MyBigDecimal(0l)).get_FormatedRoundedNumber(0)), gld._c()._span(2)._right_mid())
			._a(new RB_lab(ext_rect_kopf.get_einheit()), 												gld._c()._span(1))
			._a(new RB_lab("bereits fixiert"), 															gld._c()._span(2)._right_mid())
			._a(new RB_lab(ext_rect_kopf.get_aktuelle_fixiert_menge().get_FormatedRoundedNumber(0)), 	gld._c()._span(2)._right_mid())
			._a(new RB_lab(ext_rect_kopf.get_einheit()), 												gld._c()._span(1))
			._endLine(gld)
			;
			
		} else {
			this._endLine(gld);
		}
		
		Rec20_VPOS_KON recPos = new Rec20_VPOS_KON(dataObject.rec20());
		
		String c_artikelinfo ="";
		if(! recPos.is_newRecordSet()){
			c_artikelinfo = isFixi?ext_rect_kopf.get_sorte():recPos.get_sorte();
		}
		
		//positionszeile
		if (isNew) {
			this
			._a(new RB_lab("Position"), 													gld._c()._span(3))
			._a(new RB_lab(""),			 													gld._c()._span(2))
			._a(new RB_lab(c_artikelinfo)._ttt("Sorte"),									gld._c()._span(9))
			._endLine(gld);
		} else {
			String ePreis = recPos.get_fs_dbVal(VPOS_KON.einzelpreis);
			String pMge = recPos.get_fs_dbVal(VPOS_KON.anzahl);
			
			this
			._a(new RB_lab("Position"), 													gld._c()._span(3))
			._a(new RB_lab(recPos.get_fs_dbVal(VPOS_KON.positionsnummer))._ttt("Position"),	gld._c()._span(2))
			._a(new RB_lab(c_artikelinfo)._ttt("Sorte"),			 						gld._c()._span(9))
			
			._a(S.isEmpty(pMge)?new RB_lab():new RB_lab("Menge"), 												gld._c()._span(2)._right_mid())
			._a(S.isEmpty(pMge)?new RB_lab():new RB_lab(recPos.get_fs_dbVal(VPOS_KON.anzahl))._ttt("Menge"),	gld._c()._span(2)._right_mid())
			._a(S.isEmpty(pMge)?new RB_lab():new RB_lab(recPos.get_fs_dbVal(VPOS_KON.einheitkurz, "")),			gld._c()._span(1))
			
			._a(S.isEmpty(ePreis)?new RB_lab():new RB_lab("Preis"), 						gld._c()._span(2)._right_mid())
			._a(S.isEmpty(ePreis)?new RB_lab():new RB_lab(ePreis)._ttt("Preis"),			gld._c()._span(2)._right_mid())
			._a(S.isEmpty(ePreis)?new RB_lab():new RB_lab(recPos.get_waehrung_fremd()),		gld._c()._span(1))
			
			._endLine(gld);
		}
		
	}
	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		valueFormated.length();
		
		Rec20_VKOPF_KON ext_rect_kopf = new Rec20_VKOPF_KON(new Rec20(_TAB.vkopf_kon)._fill_id(bibALL.convertID2UnformattedID(valueFormated)));
		
		boolean isFixi = ext_rect_kopf.is_yes_db_val(VKOPF_KON.ist_fixierung);
		String  c_typ = ext_rect_kopf.get_ufs_dbVal(VKOPF_KON.vorgang_typ).equals(myCONST_ENUM.VORGANGSART.EK_KONTRAKT.get_DBValue())?"EK":"VK";
	
		boolean isNew = this.rb_ComponentMap_this_belongsTo().getRbDataObjectActual().rb_MASK_STATUS().isStatusNew();
		
		RB_gld gld = new RB_gld()._left_mid()._col(new E2_ColorDDark())._ins(2, 2, 2, 2);

		this._clear();
		
		this._setSize(50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50)
						._a(new RB_lab(new MyE2_String(isFixi?"Fixierungskontrakt ":"Standardkontrakt").ut(" ("+c_typ+")")), 	gld._c()._span(3))
						._a(new RB_lab(ext_rect_kopf.getBuchungsnummerOrId())._ttt("Buchungsnummer"),							gld._c()._span(2))
						._a(new RB_lab(ext_rect_kopf.get_adresse_name())._ttt("Firma"),											gld._c()._span(9))
						;
		
		if (isFixi) {
			this
			._a(new RB_lab("Fix.Ges.Menge"), 															gld._c()._span(2))
			._a(new RB_lab(ext_rect_kopf.get_myBigDecimal_dbVal(
						VKOPF_KON.fix_menge_gesamt,new MyBigDecimal(0l)).get_FormatedRoundedNumber(0)), gld._c()._span(2)._right_mid())
			._a(new RB_lab(ext_rect_kopf.get_einheit()), 												gld._c()._span(1))
			._a(new RB_lab("bereits fixiert"), 															gld._c()._span(2))
			._a(new RB_lab(ext_rect_kopf.get_aktuelle_fixiert_menge().get_FormatedRoundedNumber(0)), 	gld._c()._span(2)._right_mid())
			._a(new RB_lab(ext_rect_kopf.get_einheit()), 												gld._c()._span(1))
			._endLine(gld)
			;
			
		} else {
			this._endLine(gld);
		}
		
		

		//positionszeile
		if (isNew) {
			this
			._a(new RB_lab("Position"), 													gld._c()._span(3))
			._a(new RB_lab(""),			 													gld._c()._span(2))
			._endLine(gld);
		
		} else {
			
			Rec20_VPOS_KON recPos = new Rec20_VPOS_KON(new Rec20(_TAB.vpos_kon)._fill_id(this._find_component_in_neighborhood(VPOS_KON.id_vpos_kon).get__actual_maskstring_in_view()));
		
			String c_artikelinfo = isFixi?ext_rect_kopf.get_sorte():recPos.get_sorte();
			
			String ePreis = this._find_component_in_neighborhood(VPOS_KON.einzelpreis).get__actual_maskstring_in_view();
			String pMge = 	this._find_component_in_neighborhood(VPOS_KON.anzahl).get__actual_maskstring_in_view();
			
			this
			._a(new RB_lab("Position"), 													gld._c()._span(3))
			._a(new RB_lab(recPos.get_fs_dbVal(VPOS_KON.positionsnummer))._ttt("Position"),	gld._c()._span(2))
			._a(new RB_lab(c_artikelinfo)._ttt("Sorte"),			 						gld._c()._span(9))
			
			._a(S.isEmpty(pMge)?new RB_lab():new RB_lab("Menge"), 							gld._c()._span(2)._right_mid())
			._a(S.isEmpty(pMge)?new RB_lab():new RB_lab(pMge)._ttt("Menge"),				gld._c()._span(2)._right_mid())
			._a(S.isEmpty(pMge)?new RB_lab():new RB_lab(recPos.get_fs_dbVal(VPOS_KON.einheitkurz, "")),			gld._c()._span(1))
			
			._a(S.isEmpty(ePreis)?new RB_lab():new RB_lab("Preis"), 						gld._c()._span(2)._right_mid())
			._a(S.isEmpty(ePreis)?new RB_lab():new RB_lab(ePreis)._ttt("Preis"),			gld._c()._span(2)._right_mid())
			._a(S.isEmpty(ePreis)?new RB_lab():new RB_lab(recPos.get_waehrung_fremd()),		gld._c()._span(1))
			
			._endLine(gld);
		}
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key=key;

	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return new Vector<RB_Validator_Component>();
	}
}
