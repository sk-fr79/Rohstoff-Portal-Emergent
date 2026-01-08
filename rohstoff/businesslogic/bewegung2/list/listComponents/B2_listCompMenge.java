/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author sebastien
 * @date 15.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;


public class B2_listCompMenge extends E2_UniversalListComponent{

	private EnBgFieldList  enBgFieldList = null;

	public static RB_KM compKey = (RB_KM) new RB_KM();

	public B2_listCompMenge(EnBgFieldList p_enBgFieldList) throws myException {
		super();

		if (! (p_enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM || p_enBgFieldList==EnBgFieldList.A2_ID_BG_ATOM)) {
			throw new myException("Error: c34ff88d-722b-4b47-a4a9-8f4368cdb2be : only A1_ID_BG_ATOM or A2_ID_BG_ATOM are allowed !");	
		}

		this.enBgFieldList =  p_enBgFieldList;
		String table_prefix = "";
		String prefix = "";

		if(p_enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM) {
			B2_listCompMenge.compKey._setKeyAndName("d547389c-57fa-46f6-bbbf-79e0e3bc95dc-EK");
			table_prefix = "A1.";
			prefix = "EK";
		}else {
			B2_listCompMenge.compKey._setKeyAndName("d547389c-57fa-46f6-bbbf-79e0e3bc95dc-VK");
			table_prefix = "A2.";
			prefix = "VK";
		}


		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)._w100()
				._a(new OwnSorter(enBgFieldList, BG_VEKTOR.planmenge.tnfn())			._setButtonText(S.ms("PlanMge.("+prefix+")")), 	new RB_gld()._ins(1)._right_top())
				._a(new OwnSorter(enBgFieldList, table_prefix+BG_ATOM.menge.fn())		._setButtonText(S.ms("LadeMge.("+prefix+")")), 		new RB_gld()._ins(1)._right_top())
				._a(new OwnSorter(enBgFieldList, table_prefix+BG_ATOM.menge_netto.fn())	._setButtonText(S.ms("AbladeMge. ("+prefix+")")), 	new RB_gld()._ins(1)._right_top())
				);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		if(enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM) {
			return "EK-Mengen";
		}
		return "VK-Menge";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) {
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompMenge ret = new B2_listCompMenge(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	public void populate(SQLResultMAP resultMap) throws myException {
		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

		this._clear()._setSize(200)._w100();

		Rec21 rAtom 	= 	null;
		Rec21 rArtikel 	= 	null;
		Rec21 rEinheit = 	null;

		if (enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM) {
			rAtom = 	map.getRec21(_TAB.bg_atom,	EnBgFieldList.A1_ID_BG_ATOM.dbVal());
			rArtikel =  map.getRec21(_TAB.artikel,	EnBgFieldList.AR1_ID_ARTIKEL.dbVal());
		} else {
			rAtom = 	map.getRec21(_TAB.bg_atom,	EnBgFieldList.A2_ID_BG_ATOM.dbVal());
			rArtikel =  map.getRec21(_TAB.artikel,	EnBgFieldList.AR1_ID_ARTIKEL.dbVal());
		}

		Rec21 rVektor 	= map.getRec21(_TAB.bg_vektor,	BG_VEKTOR.id_bg_vektor.fn());

		if(rArtikel != null) {
			rEinheit = rArtikel.get_up_Rec21(EINHEIT.id_einheit);
		}

		RB_gld gld = new RB_gld()._ins(1)._right_top();

		if (rAtom != null && rArtikel != null) {
			if(S.isFull(rVektor.get_fs_dbVal(BG_VEKTOR.planmenge))) {
				this._a(rVektor.get_fs_dbVal(BG_VEKTOR.planmenge) 	+ " " + rEinheit.get_fs_dbVal(EINHEIT.einheitkurz)		, gld);
			}else {this._a("--", gld);}
			
			if(S.isFull(rAtom.get_fs_dbVal(BG_ATOM.menge))) {
				this._a(rAtom.get_fs_dbVal(BG_ATOM.menge,"--") 	+ " " + rEinheit.get_fs_dbVal(EINHEIT.einheitkurz,"")	, gld);
			}else {this._a("--", gld);}
			
			if(S.isFull(rAtom.get_fs_dbVal(BG_ATOM.menge_netto))) {
				this._a(rAtom.get_fs_dbVal(BG_ATOM.menge_netto,"--")+ " " + rEinheit.get_fs_dbVal(EINHEIT.einheitkurz,"")	, gld);
			
			}else {this._a("--", gld);}
		}

	}


	protected class OwnSorter extends E2_ButtonListSorterNG {
		public OwnSorter(EnBgFieldList p_enBgFieldList, String p_sort_field) {
			super();

			String sortUp =		"NVL("+p_sort_field+",'')";
			String sortDwn = 	"NVL("+p_sort_field+",'') DESC";

			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}


		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompMenge.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}
}
