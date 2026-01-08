
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

public class B2_listCompLieferbedingung extends E2_UniversalListComponent {

	private static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("a6711468-1e49-4a7d-ae01-2dfcbfdc2916");

	private EnBgFieldList  enBgFieldList = null;

	public B2_listCompLieferbedingung(EnBgFieldList p_enBgFieldList) throws myException {
		super();

		if( !(p_enBgFieldList == EnBgFieldList.A1_ID_BG_ATOM || p_enBgFieldList == EnBgFieldList.A2_ID_BG_ATOM)) {
			throw new myException("Error : 12f33504-f85e-4edf-a0e1-97ef734b055c : only A1_ID_BG_ATOM or A2_ID_BG_ATOM are allowed !");
		}

		this.enBgFieldList = p_enBgFieldList;

		this.EXT().set_oCompTitleInList(
				new E2_Grid()._s(1)._w100()
				._a("Lfbed(EK)", new RB_gld()._left_top())
				._a("Lfbed(VK)", new RB_gld()._left_top())
				._a("Passnummer(LIEF)", new RB_gld()._left_top())
				._a("Passnummer(ABN)", new RB_gld()._left_top())
				);
		
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Lieferbedingung";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}


	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._w100()._setSize(100);
		
		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());
		
		Rec21 rAtom1 = null;
		Rec21 rAtom2 = null;
		
		if((this.enBgFieldList != EnBgFieldList.A1_ID_BG_ATOM) || (this.enBgFieldList != EnBgFieldList.A2_ID_BG_ATOM)) {
			rAtom1 = map.getRec21(_TAB.bg_atom, EnBgFieldList.A1_ID_BG_ATOM.dbVal());
			rAtom2 = map.getRec21(_TAB.bg_atom, EnBgFieldList.A2_ID_BG_ATOM.dbVal());
			
			Rec21 rEkLiefBed = null;
			Rec21 rVkLiefBed = null;
			
			String ekLiefBed = "--";
			String vkLiefBed = "--";
			String passnr_lief = "--";
			String passnr_abn = "--";
			
			RB_gld gld 	= new RB_gld()._ins(1)._left_top();
			
			
			// Manfred: es gibt beim Fuhren-Import die Möglichkeit dass Lieferbedinungen keinen Eintrag in der Stammdaten-Tabelle haben. 
			// deshalb wird er Eintrag manipuliert.
			if(S.isFull(rAtom1.getFs(BG_ATOM.lieferbedingungen))) {
				 rEkLiefBed = rAtom1.get_up_Rec21(LIEFERBEDINGUNGEN.id_lieferbedingungen);
				 if (rEkLiefBed != null) {
					 ekLiefBed = rEkLiefBed.getUfs(LIEFERBEDINGUNGEN.kurzbezeichnung,"--");
				 } else {
					 ekLiefBed = rAtom1.getFs(BG_ATOM.id_lieferbedingungen) + " (Fehler im Stammstatz)";
				 }
			}
			
			if(S.isFull(rAtom2.getFs(BG_ATOM.lieferbedingungen))) {
				 rVkLiefBed = rAtom2.get_up_Rec21(LIEFERBEDINGUNGEN.id_lieferbedingungen);
				 if (rVkLiefBed != null) {
					 vkLiefBed = rVkLiefBed.getUfs(LIEFERBEDINGUNGEN.kurzbezeichnung,"--");
				 } else {
					 vkLiefBed = rAtom2.getFs(BG_ATOM.lieferbedingungen) +  " (Fehler im Stammsatz)";
				 }
			}
			
			this
			._a(ekLiefBed, gld)
			._a(vkLiefBed, gld);
			
			this
			._a(passnr_lief, gld)
			._a(passnr_abn, gld);
		}
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompLieferbedingung ret = new B2_listCompLieferbedingung(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

}
