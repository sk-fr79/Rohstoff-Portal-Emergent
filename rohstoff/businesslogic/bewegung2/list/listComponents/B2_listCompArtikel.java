/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author sebastien
 * @date 15.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditArtikel;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.EAK_BRANCHE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_GRUPPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;


public class B2_listCompArtikel extends E2_UniversalListComponent{

	private EnBgFieldList  enBgFieldList = null;

	public B2_listCompArtikel(EnBgFieldList  p_enBgFieldList) throws myException {
		super();

		if (! (p_enBgFieldList==EnBgFieldList.AB1_ID_ARTIKEL_BEZ || p_enBgFieldList==EnBgFieldList.AB2_ID_ARTIKEL_BEZ)) {
			throw new myException("Error: f84eb871-d6bb-4b53-86f7-0d4f202b6d68 : only AB1_ID_ARTIKEL_BEZ or AB2_ID_ARTIKEL_BEZ are allowed !");	
		}

		this.enBgFieldList = p_enBgFieldList;

		this.EXT().set_oCompTitleInList(
				new E2_Grid()._s(1)._w100()
					._a(new OwnSorter()._setButtonText(S.ms(this.userText())))
					._a("AVV-Code")
				);	
	}

	@Override
	public String key() throws myException {
		return enBgFieldList.name();
	}


	@Override
	public String userText() throws myException {
		if (enBgFieldList==EnBgFieldList.AB1_ID_ARTIKEL_BEZ) {
			return "Start Artikel";
		}
		return "Ziel Artikel";
	}


	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear()._w100();
	}


	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(20,200)._w100();

		Rec21 rArtikelBez = null;
		Rec21 rAtom = null;
		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

		if (enBgFieldList==EnBgFieldList.AB1_ID_ARTIKEL_BEZ) {
			rArtikelBez = map.getRec21(_TAB.artikel_bez,EnBgFieldList.AB1_ID_ARTIKEL_BEZ.dbVal());
			rAtom = map.getRec21(_TAB.bg_atom, EnBgFieldList.A1_ID_BG_ATOM.dbVal());
		} else {
			rArtikelBez = map.getRec21(_TAB.artikel_bez,EnBgFieldList.AB2_ID_ARTIKEL_BEZ.dbVal());
			rAtom = map.getRec21(_TAB.bg_atom, EnBgFieldList.A2_ID_BG_ATOM.dbVal());
		}

		if(rArtikelBez != null) {
			
			RB_gld gld = new RB_gld()._left_top()._ins(1);
			Rec21_artikel_bez rec_artikel_bez =  new Rec21_artikel_bez(rArtikelBez);

			this
			._a(new BtEditArtikel())._a("["+rec_artikel_bez.__get_ANR1_ANR2()+"]", gld)
			._a()					._a(new RB_lab()._t(rec_artikel_bez.get_fs_dbVal(ARTIKEL_BEZ.artbez1))._fsa(-2), gld)
			;	

			String AVV_Code = "";
			String AVV_Bez = "";
			if(S.isFull(rAtom.get_ufs_dbVal(BG_ATOM.id_eak_code))) {
				Rec21 rEakCode =rAtom.get_up_Rec21(BG_ATOM.id_eak_code, EAK_CODE.id_eak_code, false);
				Rec21 rEakGruppe = rEakCode.get_up_Rec21(EAK_CODE.id_eak_gruppe, EAK_GRUPPE.id_eak_gruppe, false);
				Rec21 rEakBranche = rEakGruppe.get_up_Rec21(EAK_GRUPPE.id_eak_gruppe, EAK_BRANCHE.id_eak_branche, false);

				boolean ist_gefaehrlich = rEakCode.get_ufs_dbVal(EAK_CODE.gefaehrlich,"N").equals("Y")?true:false; 

				if(S.isFull(rEakBranche.get_ufs_dbVal(EAK_BRANCHE.key_branche,""))) {
					AVV_Code = rEakBranche.get_ufs_dbVal(EAK_BRANCHE.key_branche,"")+"-";
				}
				if(S.isFull(rEakGruppe.get_ufs_dbVal(EAK_GRUPPE.key_gruppe,""))) {
					AVV_Code = AVV_Code + rEakGruppe.get_ufs_dbVal(EAK_GRUPPE.key_gruppe,"")+"-";
				}
				
				AVV_Code = AVV_Code + rEakCode.get_ufs_dbVal(EAK_CODE.key_code,"");
				
				AVV_Code = AVV_Code + (ist_gefaehrlich?(" (*)"):"");

				AVV_Bez = rEakCode.get_ufs_dbVal(EAK_CODE.code,"<-->");
				if(AVV_Bez.length()>40) {
					AVV_Bez = AVV_Bez.substring(0, 40)+"...";
				}
				
				RB_lab avv_code_bez = new RB_lab()._lwn()._fsa(-2)._i()._t(AVV_Bez);
				avv_code_bez._setWidth(40);

				this._a()
				._a(new E2_Grid()._setSize(260)._bo_dd()
						._a(AVV_Code, gld)
						._a(avv_code_bez, gld)
						,new RB_gld()._ins(0,5,0,0)._span(2));
			}
		}


	}

	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) {
	}

	protected class BtEditArtikel extends E2_BtEditArtikel {

		public BtEditArtikel() throws myException {
			super();
		}

		@SuppressWarnings("unused")
		@Override
		public Long findIdArtikel() throws myException {
			B2_listCompArtikel oThis = B2_listCompArtikel.this;


			Long idArtikelBez = null;

			if (oThis.enBgFieldList== EnBgFieldList.AB1_ID_ARTIKEL_BEZ) {
				idArtikelBez = ((B2_ListComponentMap)oThis.EXT().get_oComponentMAP()).getRec21(_TAB.artikel_bez,EnBgFieldList.AB1_ID_ARTIKEL_BEZ.dbVal()).getId();
			} else {
				idArtikelBez = ((B2_ListComponentMap)oThis.EXT().get_oComponentMAP()).getRec21(_TAB.artikel_bez,EnBgFieldList.AB2_ID_ARTIKEL_BEZ.dbVal()).getId();
			}

			if (idArtikelBez!=null) {
				Rec21 rAB = new Rec21(_TAB.artikel_bez)._fill_id(idArtikelBez);
				return rAB.getLongDbValue(ARTIKEL_BEZ.id_artikel);
			}
			return idArtikelBez;

		}
	}
	protected class OwnSorter extends E2_ButtonListSorterNG {

		public OwnSorter() {
			super();

			String sortUp = "A1.ANR1, AB1.ARTBEZ1 ASC";
			String sortDwn =  "A1.ANR1, AB1.ARTBEZ1 DESC";
			if (enBgFieldList==EnBgFieldList.AB2_ID_ARTIKEL_BEZ) {
				sortUp = "A2.ANR1, AB2.ARTBEZ1 ASC";
				sortDwn = "A2.ANR1, AB2.ARTBEZ1 DESC";
			}

			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompArtikel.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}


	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompArtikel ret = new B2_listCompArtikel(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

}
