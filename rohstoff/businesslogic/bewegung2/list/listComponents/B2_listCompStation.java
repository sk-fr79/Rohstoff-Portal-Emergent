/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author martin
 * @date 07.01.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditAdress;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

/**
 * @author martin
 * @date 07.01.2019
 *
 */
public class B2_listCompStation extends E2_UniversalListComponent {
	
	private EnBgFieldList  enBgFieldList = null;
	
	public B2_listCompStation(EnBgFieldList  p_enBgFieldList) throws myException  {
		super();
		
		if (! (p_enBgFieldList==EnBgFieldList.S1_ID_BG_STATION || p_enBgFieldList==EnBgFieldList.S3_ID_BG_STATION || p_enBgFieldList==EnBgFieldList.ID_BG_TRANSPORT)) {
			throw new myException("Error: 802da66a-1352-11e9-ab14-d663bd873d93: only S1_ID_BG_STATION, ID_BG_TRANSPORT or S3_ID_BG_STATION are allowed !");	
		}
		
		this.enBgFieldList =  p_enBgFieldList;
		this.EXT().set_oCompTitleInList(new OwnSorter()._setButtonText(S.ms(this.userText())));
	}

	@Override
	public String key() {
		return enBgFieldList.name();
	}
	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(20,180)._w100();
		
		Rec21 ra = null;

		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());
		
		if (enBgFieldList==EnBgFieldList.S1_ID_BG_STATION) {
			ra = map.getRec21(_TAB.adresse,EnBgFieldList.AD1_ID_ADRESSE.dbVal());
		} else {
			ra = map.getRec21(_TAB.adresse,EnBgFieldList.AD3_ID_ADRESSE.dbVal());
		}

		Rec21_adresse rad = new Rec21_adresse(ra);
		Rec21_adresse radM = rad._getMainAdresse();
		
		this	._a(new ownAdressEdit()._ttt(S.ms("Adresse-ID: "+ra.getIdLong().toString())), new RB_gld()._ins(1, 2, 3, 1))
				._a(new BtSelLine(ra.getFs(ADRESSE.name1)));
		this	._a()   												
				._a(new BtSelLine(ra.getFs(ADRESSE.name2)));
		
		this	._a()
				._a(ra.get_fs_dbVal(ADRESSE.ort))
				
				._a()
				._a(radM.get_StandardTelefonNumber());
		
		if (rad.getId()==radM.getId()) {
			this	._a()   												
					._a(new BtSelLine("Hauptadresse")._fsa(-2)._i());
		} else {
			this	._a()   												
					._a(new BtSelLine("Lager von: "+radM.__get_name1_ort())._fsa(-2)._i());
		}
		
		
		
	}

	
	private class BtSelLine extends E2_Button {
		public BtSelLine(String label) {
			super();
			this._t(label)._aaa(()->{
					B2_listCompStation.this.EXT().get_oComponentMAP()._setLineSelected();
				});
		}
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompStation ret = new B2_listCompStation(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

	/**
	 * der button ist immer aktiv
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) {
	}
	
	
	private class ownAdressEdit extends E2_BtEditAdress  {
		public ownAdressEdit() throws myException {
			super();
		}
		@Override
		public MyLong find_id_adress() throws myException {
			B2_listCompStation oThis = B2_listCompStation.this;
			String useLabel =  EnBgFieldList.AD1_ID_ADRESSE.dbVal();
			if (enBgFieldList==EnBgFieldList.S3_ID_BG_STATION) {
				useLabel = EnBgFieldList.AD3_ID_ADRESSE.dbVal();
			}

			return new MyLong(((B2_ListComponentMap)oThis.EXT().get_oComponentMAP()).getRec21(_TAB.adresse,useLabel).getIdLong());
		}
	}
	
	
	protected class OwnSorter extends E2_ButtonListSorterNG {
		
		public OwnSorter() {
			super();
			
			String sortUp = "AD1.NAME1, AD1.NAME2";
			String sortDwn = "AD1.NAME1 DESC, AD1.NAME2 DESC";
			if (enBgFieldList==EnBgFieldList.S3_ID_BG_STATION) {
				sortUp = "AD3.NAME1, AD3.NAME2";
				sortDwn = "AD3.NAME1 DESC, AD3.NAME2 DESC";
			}
			
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompStation.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey#userText()
	 */
	@Override
	public String userText() {
		if (enBgFieldList==EnBgFieldList.S1_ID_BG_STATION) {
			return "Start-Station";
		} else {
			return "Ziel-Station";
		}
	}
	
}
