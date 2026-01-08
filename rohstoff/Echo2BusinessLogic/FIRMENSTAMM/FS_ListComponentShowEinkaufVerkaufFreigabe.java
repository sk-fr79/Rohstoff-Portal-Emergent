/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM
 * @author martin
 * @date 24.09.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 24.09.2020
 *
 */
public class FS_ListComponentShowEinkaufVerkaufFreigabe extends E2_UniversalListComponent {

	
	private static final String key = "KEY-FS_ListComponentShowEinkaufVerkaufFreigabe";
	
	private E2_NavigationList navilist  = null;
	
	/**
	 * @author martin
	 * @date 24.09.2020
	 *
	 */
	public FS_ListComponentShowEinkaufVerkaufFreigabe(E2_NavigationList naviList) {
		super();
		this.navilist = naviList;
		
		this.EXT().setLongString4ColumnSelection(S.ms("Status der Wareneingang/-Ausgangs-Sperre"));
		
		this.EXT().set_oCompTitleInList(new SortComponent(naviList));
	}

	@Override
	public String key() throws myException {
		return key;
	}

	@Override
	public String userText() throws myException {
		return "WA-WE";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear()._s(2);
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._s(2);

		try {
			Boolean we_sperren = (S.NN(resultMap.get_UnFormatedValue(ADRESSE.wareneingang_sperren.fieldName()),"N")).equals("Y");
			Boolean wa_sperren = (S.NN(resultMap.get_UnFormatedValue(ADRESSE.warenausgang_sperren.fieldName()),"N")).equals("Y");
			
			MyE2_String tooltipsWE = S.ms(we_sperren?"Wareneingänge sind gesperrt !":"Wareneingänge sind offen !");
			MyE2_String tooltipsWA = S.ms(wa_sperren?"Warenausgänge sind gesperrt !":"Warenausgänge sind offen !");
			
			this._a(new E2_Grid()._a(new RB_lab()._t("WE")._fs(10)._b()._ttt(tooltipsWE), new RB_gld()._col(we_sperren?Color.RED:Color.GREEN)._ins(3)), new RB_gld()._ins(2,2,4,2));
			this._a(new E2_Grid()._a(new RB_lab()._t("WA")._fs(10)._b()._ttt(tooltipsWA), new RB_gld()._col(wa_sperren?Color.RED:Color.GREEN)._ins(3)), new RB_gld()._ins(4,2,2,2));
			
			
		} catch (myException e) {
			
			this._a(new RB_lab()._t("Error"), new RB_gld()._col_back_alarm()._span(2));
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new FS_ListComponentShowEinkaufVerkaufFreigabe(this.navilist);
	}
	
	
	
	private class SortComponent extends E2_Grid {

		/**
		 * @author martin
		 * @date 23.04.2021
		 *
		 */
		public SortComponent(E2_NavigationList naviList) {
			super();
			this._setSize(40,40)._a(new OwnSorter(navilist, ADRESSE.wareneingang_sperren,"WE"))._a(new OwnSorter(navilist, ADRESSE.warenausgang_sperren,"WA"));
		}
		
	}
	
	
	//einbau sortierung nach WE/WA
	private class OwnSorter extends E2_ButtonListSorterNG {
		private E2_NavigationList navilist  = null;


		public OwnSorter(E2_NavigationList naviList, IF_Field fieldToSort, String text) {
			super();
			this.navilist = naviList;
			this._setSortTermUp("NVL("+fieldToSort.fieldName()+",'N') ASC")._setSortTermDown("NVL("+fieldToSort.fieldName()+",'N') DESC");
			this._setButtonText(S.msUt(text));
			
		}


		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return this.navilist;
		}
		
	}
	
	
}
