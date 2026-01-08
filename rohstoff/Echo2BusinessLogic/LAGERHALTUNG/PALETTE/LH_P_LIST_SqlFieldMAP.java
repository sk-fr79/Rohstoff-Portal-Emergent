
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class LH_P_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {

	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;

	public LH_P_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {

		super(_TAB.lager_palette.n(), "", false);

		this.m_tpHashMap = p_tpHashMap;        
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);

		this.add_JOIN_Table(
				_TAB.vpos_tpa_fuhre.fullTableName(), 
				_TAB.vpos_tpa_fuhre.fullTableName(), 
				LEFT_OUTER, 
				":"+VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fn()+":"+VPOS_TPA_FUHRE.buchungsnr_fuhre.fn()+":"+VPOS_TPA_FUHRE.ohne_abrechnung.fn()+":", 
				true, 
				LAGER_PALETTE.id_vpos_tpa_fuhre_ein.tnfn() + "=" + VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.tnfn(), 
				"", 
				null);

		this.add_JOIN_Table(
				_TAB.artikel_bez.fullTableName(), 
				_TAB.artikel_bez.fullTableName(), 
				LEFT_OUTER, 
				":"+ARTIKEL_BEZ.anr2.tnfn()+":"+ARTIKEL_BEZ.artbez2.tnfn()+":", 
				true, 
				LAGER_PALETTE.id_artikel_bez.tnfn() + "=" + ARTIKEL_BEZ.id_artikel_bez.tnfn(), 
				"", 
				null);

		this.add_JOIN_Table(
				_TAB.artikel.fullTableName(), 
				_TAB.artikel.fullTableName(), 
				LEFT_OUTER, 
				":"+ARTIKEL.anr1.tnfn()+":"+ARTIKEL.artbez1.tnfn()+":", 
				true, 
				ARTIKEL_BEZ.id_artikel.tnfn() + "=" + ARTIKEL.id_artikel.tnfn(), 
				"", 
				null);

		this.add_SQLField(new SQLField(ARTIKEL.anr1.tnfn() + " || '-' || " + ARTIKEL_BEZ.anr2.tnfn() + " || ' ' ||  " + ARTIKEL_BEZ.artbez1.tnfn()  +  " "  , 
				LH_P_CONST.ENUM_PALETTE_LISTKEY.ARTIKEL_INFO.k(), 
				new MyE2_String("Artikel"), 
				bibE2.get_CurrSession()),true);
		
		
		this.initFields();
	}

}


