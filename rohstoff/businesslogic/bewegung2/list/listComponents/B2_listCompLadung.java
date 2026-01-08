/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author martin
 * @date 07.01.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorterNG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.HIGHLEVEL.E2_BtEditArtikel;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

/**
 * @author martin
 * @date 07.01.2019
 *
 */
public class B2_listCompLadung extends E2_UniversalListComponent{
	
	private EnBgFieldList  enBgFieldList = null;
	
	public B2_listCompLadung(EnBgFieldList  p_enBgFieldList) throws myException  {
		super();
		
		if (! (p_enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM || p_enBgFieldList==EnBgFieldList.A2_ID_BG_ATOM)) {
			throw new myException("Error: bda70a6a-141d-11e9-ab14-d663bd873d93: only A1_ID_BG_ATOM or A2_ID_BG_ATOM are allowed !");	
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
		
//		Rec21 adresseBasis = null;
//		Rec21 vposKon = null;
		Rec21 raArtikelBez = null;
		Rec21 raArtikel = null;
		Rec21 raAtom  = null;
		this._clear()._w100()._nB()._setSize(20,200);

		if (enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM) {
//			adresseBasis =	((B2_ListComponentMap)this.EXT().get_oComponentMAP()).getRec21(_TAB.adresse,	EnBgFieldList.ADB1_ID_ADRESSE_BASIS.dbVal());
			raArtikelBez =  ((B2_ListComponentMap)this.EXT().get_oComponentMAP()).getRec21(_TAB.artikel_bez,EnBgFieldList.AB1_ID_ARTIKEL_BEZ.dbVal());
			raArtikel =  	((B2_ListComponentMap)this.EXT().get_oComponentMAP()).getRec21(_TAB.artikel,	EnBgFieldList.AR1_ID_ARTIKEL.dbVal());
			raAtom = 		((B2_ListComponentMap)this.EXT().get_oComponentMAP()).getRec21(_TAB.bg_atom,	EnBgFieldList.A1_ID_BG_ATOM.dbVal());
		} else {
//			adresseBasis =	((B2_ListComponentMap)this.EXT().get_oComponentMAP()).getRec21(_TAB.adresse,	EnBgFieldList.ADB3_ID_ADRESSE_BASIS.dbVal());
			raArtikelBez =  ((B2_ListComponentMap)this.EXT().get_oComponentMAP()).getRec21(_TAB.artikel_bez,EnBgFieldList.AB2_ID_ARTIKEL_BEZ.dbVal());
			raArtikel =  	((B2_ListComponentMap)this.EXT().get_oComponentMAP()).getRec21(_TAB.artikel,	EnBgFieldList.AR2_ID_ARTIKEL.dbVal());
			raAtom = 		((B2_ListComponentMap)this.EXT().get_oComponentMAP()).getRec21(_TAB.bg_atom,	EnBgFieldList.A2_ID_BG_ATOM.dbVal());
		}

		
		
		Rec21 einheit = null;
		if (raArtikel!=null) {
			einheit = raArtikel.get_up_Rec21(EINHEIT.id_einheit);
		}

		
		if (raAtom!=null) {
			String s = raAtom.getFs(BG_ATOM.menge, " ");
			if (einheit!=null && S.isFull(s)) {
				s = s+" "+einheit.getUfs(EINHEIT.einheitkurz,"");
			}
			this._a(new RB_lab()._icon("empty14.png"))
				._a(new BtSelLine(s))
				;
		}
		
		if (raArtikel!=null && raArtikelBez!=null) {
			this._a(new OwnBtEditArtikel())
			._a(new BtSelLine("["+raArtikel.getUfs(ARTIKEL.anr1)+" - "+raArtikelBez.getUfs(ARTIKEL_BEZ.anr2)+"] "+raArtikelBez.getUfs(ARTIKEL_BEZ.artbez1)));
		} else {
			this._a(new RB_lab()._icon("empty14.png"))
			._a();
			
		}
		
		if (raAtom!=null) {
			String s = raAtom.getUfs(BG_ATOM.e_preis_basiswaehrung,"");
			Rec21 waehrung = raAtom.get_up_Rec21(WAEHRUNG.id_waehrung);
			if (waehrung!=null && waehrung.is_ExistingRecord() && S.isFull(s)) {
				s = s+" "+waehrung.getUfs(WAEHRUNG.waehrungssymbol);
			}
			this._a(new RB_lab()._icon("empty14.png"))
				._a(new BtSelLine(s))
				;
		}
	}

	
	private class BtSelLine extends E2_Button {
		public BtSelLine(String label) {
			super();
			this._t(label)._aaa(()->{
					B2_listCompLadung.this.EXT().get_oComponentMAP()._setLineSelected();
				});
		}
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompLadung ret = new B2_listCompLadung(this.enBgFieldList);
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
	
	
	private class OwnBtEditArtikel extends E2_BtEditArtikel  {
		public OwnBtEditArtikel() throws myException {
			super();
		}

		@Override
		public Long findIdArtikel() throws myException {
			B2_listCompLadung oThis = B2_listCompLadung.this;
			
			EnBgFieldList fieldArtikel = EnBgFieldList.AR1_ID_ARTIKEL;
			if (oThis.enBgFieldList==EnBgFieldList.A2_ID_BG_ATOM) {
				fieldArtikel = EnBgFieldList.AR2_ID_ARTIKEL;
			}
			
			Rec21 raArtikel =  	((B2_ListComponentMap)oThis.EXT().get_oComponentMAP()).getRec21(_TAB.artikel,fieldArtikel.dbVal());
			if (raArtikel!=null) {
				return raArtikel.getIdLong();
			}
			return null;
		}
		
		
	}
	
	
	protected class OwnSorter extends E2_ButtonListSorterNG {
		
		public OwnSorter() {
			super();
			
			String sortUp = "A1.E_PREIS";
			String sortDwn = "A1.E_PREIS DESC";
			if (enBgFieldList==EnBgFieldList.A2_ID_BG_ATOM) {
				sortUp = "A2.E_PREIS";
				sortDwn = "A2.E_PREIS DESC";
			}
			
			this._setSortTermUp(sortUp)._setSortTermDown(sortDwn);
		}

		@Override
		public E2_NavigationList getNavigationList() throws myException {
			return B2_listCompLadung.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
		}
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ListAndMask.If_ComponentWithOwnKey#userText()
	 */
	@Override
	public String userText() {
		if (enBgFieldList==EnBgFieldList.A1_ID_BG_ATOM) {
			return "Start-Ladung";
		} else {
			return "Ziel-Ladung";
		}
	}
	
}
