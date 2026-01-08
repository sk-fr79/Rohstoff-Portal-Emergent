package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

public class B2_listCompPreis extends E2_UniversalListComponent {


	private EnBgFieldList enBgFieldList = null;

	public static RB_KM compKey = (RB_KM) new RB_KM();

	public B2_listCompPreis(EnBgFieldList p_enBgFieldList) throws myException {
		super();

		if (! (p_enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM || p_enBgFieldList==EnBgFieldList.A2_ID_BG_ATOM)) {
			throw new myException("Error: 77f8a952-65a7-430f-a817-7c8155e3d654 : only A1_ID_BG_ATOM or A2_ID_BG_ATOM are allowed !");	
		}

		this.enBgFieldList =  p_enBgFieldList;

		String table_prefix = "";
		String prefix ="";
		if(this.enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM) {
			B2_listCompPreis.compKey._setKeyAndName("984f9def-6084-44cd-a08f-349a0bee1d0e-EK");
			table_prefix = "A1.";
			prefix = "EK";
		}else {
			B2_listCompPreis.compKey._setKeyAndName("984f9def-6084-44cd-a08f-349a0bee1d0e-VK");
			table_prefix = "A2.";
			prefix = "VK";
		}

		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)._w100()
				._a(new OwnSorter(enBgFieldList, table_prefix+BG_ATOM.e_preis_basiswaehrung.fn())._setButtonText(S.ms("Preis (" + prefix+")")), 		new RB_gld()._ins(1)._right_top())
				._a(new OwnSorter(enBgFieldList, table_prefix+BG_ATOM.eu_steuer_vermerk.fn())._setButtonText(S.ms("EU-Verm. (" + prefix+")")), 	new RB_gld()._ins(1)._right_top())
				);
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		if(enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM) {
			return "EK-Preis";
		}
		return "VK-Preis";
	}
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(200);

		Rec21 rAtom 	= null;

		E2_ComponentMAP_IF_Rec21 map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

		if (enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM) {
			rAtom = map.getRec21(_TAB.bg_atom, EnBgFieldList.A1_ID_BG_ATOM.dbVal());
		}else {
			rAtom = map.getRec21(_TAB.bg_atom, EnBgFieldList.A2_ID_BG_ATOM.dbVal());
		}

		if(rAtom != null) {
			String preis = rAtom.get_fs_dbVal(BG_ATOM.e_preis_basiswaehrung, "");
			String waehrungSymbol = "";
			
			if(S.isFull(preis)) {
			 waehrungSymbol = rAtom.get_up_Rec21(BG_ATOM.id_waehrung, WAEHRUNG.id_waehrung, false).get_fs_dbVal(WAEHRUNG.waehrungssymbol);
			}else {
				preis = "--";
			}
			this
			._a(new RB_lab()._t(preis+waehrungSymbol),new RB_gld()._ins(1)._right_top())
			._a(new RB_lab()._t(rAtom.get_fs_dbVal(BG_ATOM.eu_steuer_vermerk, "--")),new RB_gld()._ins(1)._right_top());

		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompPreis ret = new B2_listCompPreis(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
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
			return B2_listCompPreis.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}

	}

}
