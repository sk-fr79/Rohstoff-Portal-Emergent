/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author martin
 * @date 07.01.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditAdress;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 * @date 07.01.2019
 *
 */
public class BSAAL_listCompLieferant extends E2_UniversalListComponent {
	
	private E2_NavigationList naviList = null;
	
	public BSAAL_listCompLieferant(E2_NavigationList p_naviList) throws myException  {
		super();

		this.naviList = p_naviList;
		
		this.EXT().set_oCompTitleInList(new OwnSorter()._setButtonText(S.ms(this.userText())));
	}

	@Override
	public String key() {
		return "b3d5039e-5c5b-11e9-8647-d663bd873d93";
	}
	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(20,180)._w100();
		
		Long l = resultMap.getLongDBValue(BSAAL__CONST.SONDERSPALTEN.A_ID_ADRESSE.name());

		
		if (l!=null) {
			Rec21_adresse rad = new Rec21_adresse()._fill_id(l);
			Rec21_adresse radM = rad._getMainAdresse();
			this._a(new ownAdressEdit()._ttt(S.ms("Adresse-ID: "+radM.getIdLong().toString())), new RB_gld()._ins(1, 2, 3, 1))
				._a(new BtSelLine(radM.get_ufs_kette(" ",ADRESSE.name1,ADRESSE.name2)));
		}
	
	}

	
	private class BtSelLine extends E2_Button {
		public BtSelLine(String label) {
			super();
			this._t(label)._aaa(()->{
					BSAAL_listCompLieferant.this.EXT().get_oComponentMAP()._setLineSelected();
				});
		}
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			BSAAL_listCompLieferant ret = new BSAAL_listCompLieferant(this.naviList);
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
			
			this.getvActionAfterSave().add(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					naviList._REBUILD_ACTUAL_SITE(null);
				}
			});
			
		}
		@Override
		public MyLong find_id_adress() {
			try {
				
				Long l = BSAAL_listCompLieferant.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().getLongDBValue(BSAAL__CONST.SONDERSPALTEN.A_ID_ADRESSE.name());
				return new MyLong(l);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	
	protected class OwnSorter extends E2_ButtonListSorterNG {
		
		public OwnSorter() {
			super();
			
			String sortUp = "JT_ADRESSE.NAME1, JT_ADRESSE.NAME2";
			String sortDwn = "JT_ADRESSE.NAME1 DESC, JT_ADRESSE.NAME2 DESC";
			
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return BSAAL_listCompLieferant.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey#userText()
	 */
	@Override
	public String userText() {
		return "Lieferant";
	}
	
}
