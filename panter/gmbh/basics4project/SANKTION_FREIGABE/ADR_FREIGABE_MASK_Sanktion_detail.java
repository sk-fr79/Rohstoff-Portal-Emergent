package panter.gmbh.basics4project.SANKTION_FREIGABE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG_POS;
import panter.gmbh.basics4project.SANKTION.ENUM_SANKTION_Ergebnis_typ;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class ADR_FREIGABE_MASK_Sanktion_detail  extends RB_MaskDaughterSimple { 

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {}

	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void mark_MustField() throws myException {}

	@Override
	public void mark_Disabled() throws myException {}

	@Override
	public void mark_FalseInput() throws myException {

	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		int[] col_width = {120,280,150,150,250,250};
		this._clear();

		if(! (dataObject == null)) {
			RecList20 paramRecList = dataObject.rec20().get_down_reclist20(SANKTION_PRUEFUNG_POS.id_sanktion_pruefung, "", SANKTION_PRUEFUNG_POS.id_sanktion_pruefung_pos.fn());
			if(paramRecList == null) {
				this._a("<Fehler beim Aufbau der Liste>");
			}else {
				RB_gld title_gld 	= new RB_gld()._ins(2)._left_top()._col_back_d();
				RB_gld data_gld 	= new RB_gld()._ins(2)._left_top()._col(new E2_ColorBase());

				E2_Grid in_grid1 	= new E2_Grid()._bo_dd()
						._setSize(col_width)
						._a(new RB_lab("ID adresse")._fsa(-1), 					title_gld)
						._a(new RB_lab("Name")._fsa(-1), 						title_gld)
						._a(new RB_lab("Typ")._fsa(-1), 						title_gld)
						._a(new RB_lab("Treffer in ...")._fsa(-1), 				title_gld)
						._a(new RB_lab("Schlüsselwort (in unserer Adresse)")._fsa(-1), 				title_gld)
						._a(new RB_lab("Schlüsselwort (in Sanktionstabelle)")._fsa(-1), 	title_gld)
				;

				if(paramRecList.size()==0) {
					in_grid1._a(new RB_lab("<Kein Ergebnisse>")._col_fore_lgrey()._b(), new RB_gld()._span(col_width.length)._ins(2)._center_mid());
				}else {
					for(Rec20 rec : paramRecList) {
						Rec21_adresse rec_adresse = new Rec21_adresse()._fill_id(rec.get_ufs_dbVal(SANKTION_PRUEFUNG_POS.id_adresse, ""));
						String adresse_typ = "";
						if(rec_adresse.get_myLong_dbVal(ADRESSE.adresstyp).get_iValue()==myCONST.ADRESSTYP_LIEFERADRESSE) {
							adresse_typ = "Lieferadresse";
						}else if(rec_adresse.get_myLong_dbVal(ADRESSE.adresstyp).get_iValue()==myCONST.ADRESSTYP_MITARBEITER) {
							adresse_typ = "Mitarbeiter";
						}else if(rec_adresse.get_myLong_dbVal(ADRESSE.adresstyp).get_iValue()==myCONST.ADRESSTYP_FIRMENINFO) {
							adresse_typ = "Hauptadresse";
						}
						
						String weg = ENUM_SANKTION_Ergebnis_typ.valueOf(rec.get_fs_dbVal(SANKTION_PRUEFUNG_POS.sanktion_weg, "")).user_text();
						
						E2_Grid adresse_detail = new E2_Grid()._s(1)
						._a(new RB_lab()._t(rec_adresse.get_ufs_kette(" ", true, ADRESSE.vorname, ADRESSE.name1, ADRESSE.name2, ADRESSE.name3 ))._fsa(-1))
						._a(new RB_lab()._t(rec_adresse.get_ufs_kette(" ", true, ADRESSE.strasse, ADRESSE.hausnummer))._fsa(-1))
						._a(new RB_lab()._t(rec_adresse.get_ufs_kette(" ", true, ADRESSE.ort, ADRESSE.plz))._fsa(-1))
						;
						
						in_grid1
						._a(new RB_lab()._t(rec_adresse.get_fs_dbVal(ADRESSE.id_adresse))._fsa(-1)						, data_gld)
						._a(adresse_detail																				, data_gld)
						._a(new RB_lab()._t(adresse_typ)._fsa(-1)														, data_gld)
						._a(new RB_lab()._t(weg)._fsa(-1)																, data_gld)
						._a(new RB_lab()._t(rec.get_ufs_dbVal(SANKTION_PRUEFUNG_POS.adresse_schluesselwort))._line_wrap(true)._fsa(-1)	, data_gld)
						._a(new RB_lab()._t(rec.get_ufs_dbVal(SANKTION_PRUEFUNG_POS.sanktion_schluesselwort))._line_wrap(true)._fsa(-1)	, data_gld)
						;
					}
				}
				MyE2_ContainerEx dataContainer = new MyE2_ContainerEx(in_grid1);
				dataContainer.setHeight(new Extent(300));

				this._a(in_grid1, 		new RB_gld()._left_top()._span(2));
				this._a(dataContainer , new RB_gld()._left_top()._span(2));
			}

		}
	}

	@Override
	public void rb_set_db_value_manual(String id_of_mothertable) throws myException {

	}


}
