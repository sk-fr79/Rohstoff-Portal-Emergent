package panter.gmbh.basics4project.SANKTION_FREIGABE;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class ADR_FREIGABE_COMP_LIST_AdresseDetail_und_sprung extends MyE2_DB_PlaceHolder_NT implements MyE2IF_DB_SimpleComponent{

	private E2_Button own_sprung_2_adresselist_knopf;

	private String id_adresse = "";

	public ADR_FREIGABE_COMP_LIST_AdresseDetail_und_sprung() throws myException {
		super();

		this._bo_no();
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,
			SQLResultMAP oResultMAP) throws myException {

		E2_Grid adresse_grid = new E2_Grid()._setSize(200,16)._bo_no()._setRowHight(10);

		this.id_adresse  = oResultMAP.get_UnFormatedValue(SANKTION_PRUEFUNG.id_adresse.fn());
		
		boolean is_aktiv = oResultMAP.get_UnFormatedValue(SANKTION_PRUEFUNG.aktiv.fn()).equals("Y")?true:false;
		
		if(S.isFull(id_adresse)) {
			Rec21 rec_adresse = new Rec21(_TAB.adresse)._fill_id(oResultMAP.get_UnFormatedValue(SANKTION_PRUEFUNG.id_adresse.fn()));

			this.own_sprung_2_adresselist_knopf = new E2_Button()._image("kompass_mini.png");
			this.own_sprung_2_adresselist_knopf._ttt("Spring ins Adressmodul zu dieser Adresse");


			String name = rec_adresse.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.vorname);
			String ort = rec_adresse.getFs(ADRESSE.ort,"");
			String typ ="";


			if(is_aktiv) {
				adresse_grid._a(new RB_lab(name + " " + typ)._fsa(-1)					, new RB_gld()._ins(1)._left_top());
			}else {
				adresse_grid._a(new RB_lab(name + " " + typ)._fsa(-1)._col_fore_dgrey()	, new RB_gld()._ins(1)._left_top());
			}

			own_sprung_2_adresselist_knopf._ttt(S.ms("Sprung zur Adresse mit der ID: " + rec_adresse.get_fs_dbVal(ADRESSE.id_adresse)));

			this.own_sprung_2_adresselist_knopf._aaa(new ownActionJumpToFirma(rec_adresse.get_key_value()));
			adresse_grid._a(own_sprung_2_adresselist_knopf	, new RB_gld()._ins(1)._left_top());
			if(S.isFull(ort)) {
				if(is_aktiv) {
					adresse_grid._a(new RB_lab(ort)._fsa(-1)					, new RB_gld()._ins(1)._left_top()._span(2));
				}else {
					adresse_grid._a(new RB_lab(ort)._fsa(-1)._col_fore_dgrey()	, new RB_gld()._ins(1)._left_top()._span(2));
				}
			}

			this.add(adresse_grid);
		}
	}


	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new ADR_FREIGABE_COMP_LIST_AdresseDetail_und_sprung();
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}


	private class ownActionJumpToFirma extends XX_ActionAgentJumpToTargetList
	{

		private String id_adresse = "";
		public ownActionJumpToFirma(String ufs_id_adresse) throws myException 
		{
			super( E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,"Firmenstamm");
			this.id_adresse = ufs_id_adresse;
		}


		@Override
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			if (vTargetList.size()==0)
			{
				oMV._addAlarm(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Adresse !",true));
			}
			return oMV;
		}


		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException 
		{
			Vector<String> vRueck = new Vector<String>();

			if (S.isFull(id_adresse) && bibALL.isLong(id_adresse))
			{
				vRueck.add(id_adresse);
			}
			return vRueck;
		}
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {

	}
}
