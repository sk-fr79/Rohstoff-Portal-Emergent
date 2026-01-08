package panter.gmbh.basics4project.SANKTION_FREIGABE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class ADR_FREIGABE_COMP_Label_adresse_detail extends E2_Grid implements IF_RB_Component{

	private RB_KF key;
	
	public ADR_FREIGABE_COMP_Label_adresse_detail() {
		super();
		this._s(1);
	}

	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void set_Alignment(Alignment align) throws myException {}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(! (dataObject.rec20()==null)) {
			
			this._clear();
			Rec21_adresse rec_addr = new Rec21_adresse()._fill_id(dataObject.rec20().get_myLong_dbVal(SANKTION_PRUEFUNG.id_adresse).get_lValue());
			
			String name_vorname = rec_addr.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.vorname);
			String strasse_ort = rec_addr.get_ufs_kette(" - ", ADRESSE.strasse, ADRESSE.ort);
			
			String hauptadresse = "";

			if(rec_addr.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_MITARBEITER)) {
				name_vorname = name_vorname + " (Mit.)";
				hauptadresse = rec_addr._getMainAdresse().__get_name1_ort();
			}else if(rec_addr.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_LIEFERADRESSE)) {
				name_vorname = name_vorname + " (Lag.)";
				hauptadresse = rec_addr._getMainAdresse().__get_name1_ort();
			}else {
				name_vorname = name_vorname + " (*)";
			}
			this
			._a(new RB_lab(name_vorname)._fsa(1))
			._a(new RB_lab(strasse_ort)._fsa(1))
			._a(new RB_lab(hauptadresse));
		}

	}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {}

	@Override
	public RB_KF rb_KF() throws myException {return key;}

	@Override
	public void set_rb_RB_K(RB_KF p_key) throws myException {
		this.key = p_key;

	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return new Vector<RB_Validator_Component>();
	}

}
