package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

public class B2_listCompDatumLadeAblade extends E2_UniversalListComponent {

	private static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("71ff227a-66c2-4a0c-92b7-659d142b54a0");

	private EnBgFieldList  enBgFieldList = null;

	public B2_listCompDatumLadeAblade(EnBgFieldList p_EnBgFieldList) throws myException {
		super();

		if(! (p_EnBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: 5c73bb9b-2e4b-45f0-9cc1-e5db538ef851 : only ID_BG_TRANSPORT is allowed !" );

		}

		this.enBgFieldList = p_EnBgFieldList;

		this.EXT().set_oCompTitleInList(
				new E2_Grid()._s(1)._w100()
				._a(new OwnSorter(enBgFieldList,BG_VEKTOR.datum_planung_von)._setButtonText(S.ms("Abholdat. (von)")), 		new RB_gld()._ins(1)._left_top())
				._a(new OwnSorter(enBgFieldList,BG_VEKTOR.datum_planung_bis)._setButtonText(S.ms("Abholdat. (bis)")), 	new RB_gld()._ins(1)._left_top())
				._a(new OwnSorter(enBgFieldList,BG_VEKTOR.transportkennzeichen)._setButtonText(S.ms("LKW-Kennz")), 		new RB_gld()._ins(1)._left_top())
				._a(new OwnSorter(enBgFieldList,BG_VEKTOR.anhaengerkennzeichen)._setButtonText(S.ms("Anhänger-kennz.")), 	new RB_gld()._ins(1)._left_top())
				);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Liefer/Abholdatum";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}
	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

		this._clear()._setSize(150);

		RB_gld gld 	= new RB_gld()._ins(1)._left_top();
		
		if (enBgFieldList==EnBgFieldList.ID_BG_TRANSPORT){
			Rec21 rVektor = map.getRec21(_TAB.bg_vektor, BG_VEKTOR.id_bg_vektor.fn());

			this._a(rVektor.get_fs_dbVal(BG_VEKTOR.datum_planung_von,"--")		,gld);
			this._a(rVektor.get_fs_dbVal(BG_VEKTOR.datum_planung_bis,"--")			,gld);
			this._a(rVektor.get_fs_dbVal(BG_VEKTOR.transportkennzeichen,"--")	,gld);
			this._a(rVektor.get_fs_dbVal(BG_VEKTOR.anhaengerkennzeichen, "--")	,gld);


		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompDatumLadeAblade ret = new B2_listCompDatumLadeAblade(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}


	protected class OwnSorter extends E2_ButtonListSorterNG {

		public OwnSorter(EnBgFieldList enBgFieldList, IF_Field p_field_2_sort) {
			super();
			this._w100();
			String sortUp = "NVL("+p_field_2_sort.fn()+",'') ";
			String sortDwn = "NVL("+p_field_2_sort.fn()+",'') DESC";
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);


			//			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompDatumLadeAblade.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}


}
