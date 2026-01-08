/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author sebastien
 * @date 18.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

/**
 * @author sebastien
 * @date 18.03.2019
 *
 */
public class B2_listCompBuchungsnummernBlock extends E2_UniversalListComponent {

	private EnBgFieldList  enBgFieldList = null;
	
	private static RB_KM compKey = (RB_KM) new RB_KM()._setKeyAndName("f4014348-b5f0-4d57-9a79-3858a29491be");
	
	public B2_listCompBuchungsnummernBlock(EnBgFieldList p_enBgFieldList) throws myException {
		super();
		
		if( !(p_enBgFieldList == EnBgFieldList.ID_BG_TRANSPORT)){
			throw new myException("Error: 4e5d16a6-bce4-4fb5-8790-319b8c76dd76 : only ID_BG_TRANSPORT are allowed");
		}
		
		this.enBgFieldList = p_enBgFieldList;

		this.EXT().set_oCompTitleInList(new E2_Grid()._s(1)
				._a("TPA")._a("Buchungsnr.")._a("EK-Kontrakt")._a("VK-Kontrakt")._a("UMA"));
	}

	@Override
	public String key() throws myException {
		return compKey.get_HASHKEY();
	}

	@Override
	public String userText() throws myException {
		return "Buchungsnummern";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();

	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		Rec21 rVektor = null;
		Rec21 rKopf1 = null;
		Rec21 rKopf2 = null;
		
		B2_ListComponentMap map = ((B2_ListComponentMap)this.EXT().get_oComponentMAP());

		if (enBgFieldList==EnBgFieldList.ID_BG_TRANSPORT) {
			rVektor = map.getRec21(_TAB.bg_vektor,	BG_VEKTOR.id_bg_vektor.fn());
			rKopf1 =   map.getRec21(_TAB.vkopf_kon,EnBgFieldList.VK1_ID_VKOPF_KON.dbVal());
			rKopf2 =   map.getRec21(_TAB.vkopf_kon, EnBgFieldList.VK2_ID_VKOPF_KON.dbVal());
		} 
		
		if((rVektor != null)  ) {
			String tpa = "--";
			String buchungsnr = rVektor.getUfs(BG_VEKTOR.buchungsnummer,"--");
			String umarbeitung = "--";
			String ek_kontrakt = "--";
			String vk_kontrakt = "--";
			
			if(rKopf1 != null) {
				ek_kontrakt = rKopf1.get_ufs_dbVal(VKOPF_KON.buchungsnummer,"--");
			}
			if(rKopf2 != null) {
				vk_kontrakt = rKopf2.get_ufs_dbVal(VKOPF_KON.buchungsnummer,"--");
			}
			
			RB_gld gld = new RB_gld()._ins(1)._left_top();
			
			this._clear()._setSize(200)
			._a(tpa, gld)._a(buchungsnr, gld)._a(ek_kontrakt, gld)._a(vk_kontrakt, gld)._a(umarbeitung, gld);
		}

	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			B2_listCompBuchungsnummernBlock ret = new B2_listCompBuchungsnummernBlock(this.enBgFieldList);
			ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
			return ret;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	
}
