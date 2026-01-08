/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN
 * @author martin
 * @date 05.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN.FSL_LIST_SQLFieldMap.FSL_QUERYSTRING;

/**
 * @author martin
 * @date 05.10.2020
 *
 */
public class FSL_ListCompFremdAdressen extends E2_UniversalListComponent {

	public static String key = " 5579676f-fc07-4378-8640-5f8346771c23 ";
	
	
	/**
	 * @author martin
	 * @date 05.10.2020
	 *
	 */
	public FSL_ListCompFremdAdressen() {
		super();
		
		E2_Grid gridTitle = new E2_Grid()._setSize(300);
		gridTitle	._a(new OwnSorter(FSL_QUERYSTRING.ADRESSE_FREMDWARE), new RB_gld()._left_mid()._ins(0,1,0,2))
					._a(new OwnSorter(FSL_QUERYSTRING.ADRESSE_BESITZER_WARE), new RB_gld()._left_mid()._ins(0,1,0,2))
					._a(new OwnSorter(FSL_QUERYSTRING.ADRESSE_BESITZ_LAGER), new RB_gld()._left_mid()._ins(0,1,0,2))
					;
		this.EXT().set_oCompTitleInList(gridTitle);

	}

	@Override
	public String key() throws myException {
		return FSL_ListCompFremdAdressen.key;
	}

	@Override
	public String userText() throws myException {
		return "Spalte mit Fremdadressen";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._bo_no()._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		
		String sADRESSE_FREMDWARE =     resultMap.get_FormatedValue(FSL_QUERYSTRING.ADRESSE_FREMDWARE.getLabel());
		String sADRESSE_BESITZER_WARE =  resultMap.get_FormatedValue(FSL_QUERYSTRING.ADRESSE_BESITZER_WARE.getLabel());
		String sADRESSE_BESITZ_LAGER =  resultMap.get_FormatedValue(FSL_QUERYSTRING.ADRESSE_BESITZ_LAGER.getLabel());
		
		this._clear()._setSize(300)._bo_no();
		this._a(new RB_lab()._t(S.NN(sADRESSE_FREMDWARE,"-"))._lw(), new RB_gld()._ins(2))
			._a(new RB_lab()._t(S.NN(sADRESSE_BESITZER_WARE,"-"))._lw(), new RB_gld()._ins(2))
			._a(new RB_lab()._t(S.NN(sADRESSE_BESITZ_LAGER,"-"))._lw(), new RB_gld()._ins(2))
			;
		if (S.isFull(sADRESSE_FREMDWARE) || S.isFull(sADRESSE_BESITZER_WARE) ||S.isFull(sADRESSE_BESITZ_LAGER) ) {
			this._bo_ddd();
		}
		
	}
	
	
	protected class OwnSorter extends E2_ButtonListSorterNG {
		
		public OwnSorter(FSL_QUERYSTRING defEnum) {
			super();
			
			String sortUp = defEnum.getQueryString()+" ASC";
			String sortDwn = defEnum.getQueryString()+" DESC";
			
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
			this._setButtonText(S.ms(defEnum.getTitelText()));
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return FSL_ListCompFremdAdressen.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}
		
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.E2_Grid#get_Copy(java.lang.Object)
	 */
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new FSL_ListCompFremdAdressen();
	}

	
	
	
}
