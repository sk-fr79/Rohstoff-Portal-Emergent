package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

public class B2_listCompContainerSorte extends E2_UniversalListComponent {

	private static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("8fcdefbd-1bda-488e-aa3f-efa78a3ed2a0");

	private EnBgFieldList  enBgFieldList = null;

	public B2_listCompContainerSorte(EnBgFieldList p_enBgFieldList ) throws myException {
		super();

		if(! (p_enBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: 6a709b19-0ad8-4381-80bd-8dace15a6229 : ");
		}

		this.enBgFieldList = p_enBgFieldList;

		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)
				._a("Container")
				._a(new OwnSorter(EnBgFieldList.AB1_ID_ARTIKEL_BEZ)._setButtonText(S.ms("EK_Sorte")))
				._a(new OwnSorter(EnBgFieldList.AB2_ID_ARTIKEL_BEZ)._setButtonText(S.ms("VK_Sorte")))	
			);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Container/Sorte";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(150);

		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

		RB_gld gld 	= new RB_gld()._ins(1)._left_top();
		RB_gld gld2 = new RB_gld()._ins(1,2,1,2)._left_top();
		
		RB_lab default_lbl = new RB_lab("<-->")._fsa(-2);
		if(this.enBgFieldList == EnBgFieldList.ID_BG_TRANSPORT) {
			
			Rec21 rArtBezEk = map.getRec21(_TAB.artikel_bez, EnBgFieldList.AB1_ID_ARTIKEL_BEZ.dbVal() );
			Rec21 rArtBezVk = map.getRec21(_TAB.artikel_bez, EnBgFieldList.AB2_ID_ARTIKEL_BEZ.dbVal() );
			Rec21_artikel_bez rArtBezEk_ext = null;
			Rec21_artikel_bez rArtBezVk_ext = null;
			
			if(rArtBezEk != null) {
				 rArtBezEk_ext = new Rec21_artikel_bez(rArtBezEk);
			}
			if(rArtBezVk != null) {
				rArtBezVk_ext = new Rec21_artikel_bez(rArtBezVk); 
			}
			
			this._a()._a("-", gld);
			if(rArtBezEk != null) {
				this._a(new RB_lab()._t(rArtBezEk_ext.__get_ANR1_ANR2_ARTBEZ1())._fsa(-2),gld2);
			}else {
				this._a(default_lbl, gld);
			}
			
			if(rArtBezVk != null) {
				this._a(new RB_lab()._t(rArtBezVk_ext.__get_ANR1_ANR2_ARTBEZ1())._fsa(-2),gld2);
			}else {
				this._a(default_lbl, gld);
			}
		}
	
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompContainerSorte ret = new B2_listCompContainerSorte(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	protected class OwnSorter extends E2_ButtonListSorterNG {

		public OwnSorter(EnBgFieldList p_enBgFieldList) {
			super();
			String sortUp  ="";
			String sortDwn = "";
			
			if(enBgFieldList == EnBgFieldList.AB1_ID_ARTIKEL_BEZ) {
				sortUp = "A1.ANR1, AB1.ARTBEZ1 ASC";
				sortDwn =  "A1.ANR1, AB1.ARTBEZ1 DESC";
			}else {
				sortUp = "A2.ANR1, AB2.ARTBEZ1 ASC";
				sortDwn = "A2.ANR1, AB2.ARTBEZ1 DESC";
			}
			
			if(S.isAllFull(sortUp, sortDwn)) {
				this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
			}
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompContainerSorte.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}

}
