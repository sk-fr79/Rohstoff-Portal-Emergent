/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author martin
 * @date 07.01.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditKontraktPos;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.ENUM_VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

/**
 * @author martin
 * @date 07.01.2019
 *
 */
public class B2_listCompKontrakt extends E2_UniversalListComponent  {
	
	private EnBgFieldList  enBgFieldList = null;
	
	public B2_listCompKontrakt(EnBgFieldList  p_enBgFieldList) throws myException  {
		super();
		
		if (! (p_enBgFieldList==EnBgFieldList.VP1_ID_VPOS_KON || p_enBgFieldList==EnBgFieldList.VP2_ID_VPOS_KON)) {
			throw new myException("Error: a420d718-1352-11e9-ab14-d663bd873d93: only VP1_ID_VPOS_KON or VP3_ID_VPOS_KON are allowed !");	
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
		
		Rec21 rv = null;
		
		B2_ListComponentMap map = (B2_ListComponentMap)this.EXT().get_oComponentMAP();
		
		this._clear()._w100()._nB()._setSize(20,200);

		if (enBgFieldList==EnBgFieldList.VP1_ID_VPOS_KON) {
			rv =  map.getRec21(_TAB.vpos_kon,EnBgFieldList.VP1_ID_VPOS_KON.dbVal());
		} else {
			rv = map.getRec21(_TAB.vpos_kon,EnBgFieldList.VP2_ID_VPOS_KON.dbVal());
		}
		
		

		if (rv!=null) {

			Rec21_VPosKon rvp = new Rec21_VPosKon(rv);
			String konNr = S.NN(rvp.getBuchungsNummer());
			String sorte = " ["+rvp.get_ufs_kette(" - " , VPOS_KON.anr1,VPOS_KON.anr2)+"] "+rvp.getUfs(VPOS_KON.artbez1);
			String mengeGesamt = new MyBigDecimal(rvp.getBigDecimalDbValue(VPOS_KON.anzahl)).get_FormatedRoundedNumber(1);
			String gueltig = rvp.getGueltigkeitsZeitraum();

			this	._a(new BtEditKontraktPos())
					._a(new BtSelLine(konNr)._fsa(-2), new RB_gld()._ins(0,1,1,1));
					;
			this	._a()
					._a(new BtSelLine(sorte)._fsa(-2), new RB_gld()._ins(0,1,1,1));
					;
			this	._a()
					._a(new BtSelLine(gueltig)._fsa(-2), new RB_gld()._ins(0,1,1,1));
					;
			this	._a()
					._a(new BtSelLine(mengeGesamt)._fsa(-2), new RB_gld()._ins(0,1,1,1));
					;
		}
	}

	
	private class BtSelLine extends E2_Button {
		public BtSelLine(String label) {
			super();
			this._t(label)._aaa(()->{
					B2_listCompKontrakt.this.EXT().get_oComponentMAP()._setLineSelected();
				});
		}
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompKontrakt ret = new B2_listCompKontrakt(this.enBgFieldList);
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
	
	
	protected class OwnSorter extends E2_ButtonListSorterNG {
		
		public OwnSorter() {
			super();
			
			String sortUp = "NVL(VK1.BUCHUNGSNUMMER,'')||NVL(TO_CHAR(VP1.POSITIONSNUMMER),'') ";
			String sortDwn = "NVL(VK1.BUCHUNGSNUMMER,'')||NVL(TO_CHAR(VP1.POSITIONSNUMMER),'') DESC";
			if (enBgFieldList==EnBgFieldList.VP2_ID_VPOS_KON) {
				sortUp = "NVL(VK3.BUCHUNGSNUMMER,'')||NVL(TO_CHAR(VP3.POSITIONSNUMMER),'') ";
				sortDwn = "NVL(VK3.BUCHUNGSNUMMER,'')||NVL(TO_CHAR(VP3.POSITIONSNUMMER),'') DESC";
			}
			
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompKontrakt.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey#userText()
	 */
	@Override
	public String userText() {
		if (enBgFieldList==EnBgFieldList.VP1_ID_VPOS_KON) {
			return "EK-Kontrakt";
		} else {
			return "VK-Kontrakt";
		}
	}
	
	
	private class BtEditKontraktPos extends E2_BtEditKontraktPos {

		public BtEditKontraktPos() throws myException {
			super(B2_listCompKontrakt.this.enBgFieldList==EnBgFieldList.VP1_ID_VPOS_KON?ENUM_VORGANGSART.EK_KONTRAKT:ENUM_VORGANGSART.VK_KONTRAKT);
		}

		@Override
		public Long findIdVposKon() throws myException {
			B2_listCompKontrakt oThis = B2_listCompKontrakt.this;
			
			Rec21 recVpos = ((B2_ListComponentMap)oThis.EXT().get_oComponentMAP()).getRec21(_TAB.vpos_kon, B2_listCompKontrakt.this.enBgFieldList.dbVal());
			
			return recVpos.getId();
			
		}
		
	}

}
