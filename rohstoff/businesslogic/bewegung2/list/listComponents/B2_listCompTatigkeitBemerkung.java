package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

public class B2_listCompTatigkeitBemerkung extends E2_UniversalListComponent {

	private EnBgFieldList enBgFieldList;

	public static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("c4a80f41-6f74-430c-af85-3cd458ed38f5");
	
	public B2_listCompTatigkeitBemerkung(EnBgFieldList p_EnBgFieldList) throws myException {
		super();
		
		if(! (p_EnBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: 7f13ca3e-0997-4260-ac9e-689d31180b8d : only ID_BG_TRANSPORT is allowed !");
		}
		
		this.enBgFieldList = p_EnBgFieldList;
		
		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)
				._a(new OwnSorter()._setButtonText(S.ms("Tätigkeit")))
				._a(new OwnSorter()._setButtonText(S.ms("Bemerkung")))	
			);

	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Tätigkeit";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._w100()._setSize(210);
		
		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

		RB_gld gld = new RB_gld()._ins(1)._left_top();
		
		if(this.enBgFieldList == EnBgFieldList.ID_BG_TRANSPORT) {
			Rec21 rVektor = map.getRec21(_TAB.bg_vektor , BG_VEKTOR.id_bg_vektor.fn());
			
			RB_TextArea bemerkung_text_area = new RB_TextArea(200, 6);
			bemerkung_text_area.set_bEnabled_For_Edit(false);
			bemerkung_text_area._fo_s_plus(-2);
			
			bemerkung_text_area._t(rVektor.get_ufs_dbVal(BG_VEKTOR.bemerkung,""));
			
			this._a("-", 					gld);
			this._a(bemerkung_text_area, 	gld);
			
		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompTatigkeitBemerkung ret = new B2_listCompTatigkeitBemerkung(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	
	protected class OwnSorter extends E2_ButtonListSorterNG {

		public OwnSorter() {
			super();
//			String sortUp  ="";
//			String sortDwn = "";
//			
//			if(enBgFieldList == EnBgFieldList.AB1_ID_ARTIKEL_BEZ) {
//				sortUp = "A1.ANR1, AB1.ARTBEZ1 ASC";
//				sortDwn =  "A1.ANR1, AB1.ARTBEZ1 DESC";
//			}else {
//				sortUp = "A2.ANR1, AB2.ARTBEZ1 ASC";
//				sortDwn = "A2.ANR1, AB2.ARTBEZ1 DESC";
//			}
//			
//			if(S.isAllFull(sortUp, sortDwn)) {
//				this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
//			}
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompTatigkeitBemerkung.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}
}
