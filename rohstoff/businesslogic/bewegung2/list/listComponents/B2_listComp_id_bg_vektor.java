/**
 * rohstoff.businesslogic.bewegung2.list.listComponents
 * @author martin
 * @date 07.01.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.list.listComponents;

import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.businesslogic.bewegung2.detail.B2_listBt_RecordDetail;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.B2_ListComponentMap;

/**
 * @author martin
 * @date 07.01.2019
 *
 */
public class B2_listComp_id_bg_vektor extends E2_UniversalListComponent {

	public String key() { return "ID_BG_TRANSPORT2"; }

	public B2_listComp_id_bg_vektor()  {
		super();
		this._clear();
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	/**
		//jetzt die ids der joinblock dazu
		this.add_SQLField(new SQLField(BG_VEKTOR.id_bg_vektor.fn("VE"), 	"VE_ID_BG_VEKTOR", S.ms("Vektor-ID")), false);

		this.add_SQLField(new SQLField(BG_STATION.id_bg_station.fn("S1"), "S1_ID_BG_STATION", S.ms("Start-Station-ID")), false);
		this.add_SQLField(new SQLField(BG_STATION.id_bg_station.fn("S2"), "S2_ID_BG_STATION", S.ms("Mittel-Station-ID")), false);
		this.add_SQLField(new SQLField(BG_STATION.id_bg_station.fn("S3"), "S3_ID_BG_STATION", S.ms("Ziel-Station-ID")), false);

		this.add_SQLField(new SQLField(BG_LADUNG.id_bg_ladung.fn("L1"), 	"L1_ID_BG_LADUNG", S.ms("Start-Ladung-ID")), false);
		this.add_SQLField(new SQLField(BG_LADUNG.id_bg_ladung.fn("L2A"), 	"L2A_ID_BG_LADUNG", S.ms("Zwischen(A)-Ladung-ID")), false);
		this.add_SQLField(new SQLField(BG_LADUNG.id_bg_ladung.fn("L2B"), 	"L2B_ID_BG_LADUNG", S.ms("Zwischen(B)-Ladung-ID")), false);
		this.add_SQLField(new SQLField(BG_LADUNG.id_bg_ladung.fn("L3"), 	"L3_ID_BG_LADUNG", S.ms("Ziel-Ladung-ID")), false);

		this.add_SQLField(new SQLField(BG_ATOM.id_bg_atom.fn("A1"), 		"A1_ID_BG_ATOM", S.ms("Start-Atom-ID")), false);
		this.add_SQLField(new SQLField(BG_ATOM.id_bg_atom.fn("A2"), 		"A2_ID_BG_ATOM", S.ms("Ziel-Atom-ID")), false);
	 * 
	 */


	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear()._setSize(75,75)._w100()._bo_dd();

		B2_ListComponentMap map = (B2_ListComponentMap)this.EXT().get_oComponentMAP();

		Rec21 rVektor = map.getRec21(_TAB.bg_vektor,BG_VEKTOR.id_bg_vektor.fn());

		Rec21 rAtomQ =  map.getRec21(_TAB.bg_atom,"A1_ID_BG_ATOM");
		Rec21 rAtomZ =  map.getRec21(_TAB.bg_atom,"A2_ID_BG_ATOM");

		Rec21 rStationQ =  	map.getRec21(_TAB.bg_station,EnBgFieldList.S1_ID_BG_STATION.dbVal());
		Rec21 rStationS =	map.getRec21(_TAB.bg_station,EnBgFieldList.S2_ID_BG_STATION.dbVal());
		Rec21 rStationZ = 	 map.getRec21(_TAB.bg_station,EnBgFieldList.S3_ID_BG_STATION.dbVal());


		RB_gld gld = new RB_gld()._left_mid()._ins(1,2,1,2);
		this
		._a(new B2_listBt_RecordDetail(rVektor,"ID-Vektor"), 		gld)._a(new B2_listBt_RecordDetail(rVektor, rVektor.getFs(BG_VEKTOR.id_bg_vektor))		,gld)
		._a(new B2_listBt_RecordDetail(rStationQ, "ID-Stat. (Q)"), 	gld)._a(new B2_listBt_RecordDetail(rStationQ, rStationQ.getFs(BG_STATION.id_bg_station)),gld)
		._a(new B2_listBt_RecordDetail(rAtomQ,"ID-Atom (Q)"),		gld)._a(new B2_listBt_RecordDetail(rAtomQ,rAtomQ.getFs(BG_ATOM.id_bg_atom))				,gld)
		._a(new B2_listBt_RecordDetail(rStationS, "ID-Stat. (S)"),	gld)._a(new B2_listBt_RecordDetail(rStationS, rStationS.getFs(BG_STATION.id_bg_station)),gld)
		._a(new B2_listBt_RecordDetail(rAtomZ, "ID-Atom (Z)"), 		gld)._a(new B2_listBt_RecordDetail(rAtomZ, rAtomZ.getFs(BG_ATOM.id_bg_atom))			,gld)
		._a(new B2_listBt_RecordDetail(rStationZ, "ID-Stat. (Z)"), 	gld)._a(new B2_listBt_RecordDetail(rStationZ, rStationZ.getFs(BG_STATION.id_bg_station)),gld)
		;
	}


//	private class BtSelLine extends E2_Button {
//		public BtSelLine(String label) {
//			super();
//			this._t(label)._fsa(-2)._i()._aaa(()->{
//				B2_listComp_id_bg_vektor.this.EXT().get_oComponentMAP()._setLineSelected();
//			});
//		}
//	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		B2_listComp_id_bg_vektor ret = new B2_listComp_id_bg_vektor();
		ret.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(ret));
		return new B2_listComp_id_bg_vektor();
	}

	/**
	 * der button ist immer aktiv
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean bEnabled) {
	}

	@Override
	public String userText() throws myException {
		return "IDs";
	}

}
