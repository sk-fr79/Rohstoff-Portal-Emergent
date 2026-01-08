/**
 * @author sebastien
 * @date 21.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;


import java.util.LinkedHashMap;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;


public class B2_containerRecordDetail extends E2_BasicModuleContainer {

	private MyE2_TabbedPane tab = null;

	private Rec21 sourceRecord = null;

	public B2_containerRecordDetail(Rec21 p_sourceRecord) throws myException {
		super();

//		int[] iStandardSize = {200,210,350};
		
		_TAB table = p_sourceRecord.get_tab();

		if(  !(table == _TAB.bg_vektor) && !(table == _TAB.bg_atom) && !(table == _TAB.bg_station)  ){
			throw new myException("Error: 965b0b0b-d5bd-436f-ba30-23509ec913a5 : only BG_VEKTOR, BG_ATOM or BG_STATION records are allowed");
		}

		this.sourceRecord 		= p_sourceRecord;
		this.tab 				= new MyE2_TabbedPane(800);
		
		HMAP<RB_KM, Rec21> rec_hm = null;
		if(p_sourceRecord.get_tab() == _TAB.bg_vektor) {
			 rec_hm = new Rec21_bgVector(p_sourceRecord).getCompleteStackOfRecords();
		}else if(p_sourceRecord.get_tab() == _TAB.bg_atom) {
			rec_hm= new Rec21_bgVector(p_sourceRecord.get_up_Rec21(BG_VEKTOR.id_bg_vektor)).getCompleteStackOfRecords();
		}else if(p_sourceRecord.get_tab() == _TAB.bg_station) {
			rec_hm= new Rec21_bgVector(p_sourceRecord.get_up_Rec21(BG_VEKTOR.id_bg_vektor)).getCompleteStackOfRecords();
		}
		
		LinkedHashMap<String, E2_Grid> bewegungGrid_hm = new LinkedHashMap<>();

		bewegungGrid_hm.put("BG_VEKTOR@"+rec_hm.get(RecV.key).get_key_value(), 
				new E2_Grid4Visualize()._setRec21(rec_hm.get(RecV.key))
				._setDefinition(new BG_VEKTOR_DefinitionVektor())._render());
		
		bewegungGrid_hm.put("BG_STATION@"+rec_hm.get(RecS1.key).get_key_value(),
			new E2_Grid4Visualize()._setRec21(rec_hm.get(RecS1.key))
				._setDefinition(new BG_STATION_DefinitionVektor())._render());
		
		bewegungGrid_hm.put("BG_ATOM@"+rec_hm.get(RecA1.key).get_key_value(),
				new E2_Grid4Visualize()._setRec21(rec_hm.get(RecA1.key))
				._setDefinition(new BG_ATOM_DefinitionVektor())._render());
		
		bewegungGrid_hm.put("BG_STATION@"+rec_hm.get(RecS2.key).get_key_value(),
			new E2_Grid4Visualize()._setRec21(rec_hm.get(RecS2.key))
				._setDefinition(new BG_STATION_DefinitionVektor())._render());	
		
		bewegungGrid_hm.put("BG_ATOM@"+rec_hm.get(RecA2.key).get_key_value(),
			new E2_Grid4Visualize()._setRec21(rec_hm.get(RecA2.key))
				._setDefinition(new BG_ATOM_DefinitionVektor())._render());	
		
		bewegungGrid_hm.put("BG_STATION@"+rec_hm.get(RecS3.key).get_key_value(),
				new E2_Grid4Visualize()._setRec21(rec_hm.get(RecS3.key))
				._setDefinition(new BG_STATION_DefinitionVektor())._render());
		
		int selectidIdx=0;
		int i = 0;
		
		for(String key:bewegungGrid_hm.keySet()) {
			String[] oEntry = key.split("@");
			E2_Grid grd = bewegungGrid_hm.get(key);
			tab.add_Tabb(S.ms(oEntry[0] + " (Id: " + oEntry[1] +")"), 	grd);
			
			if(key.equals(sourceRecord.get_tab().baseTableName()+"@"+sourceRecord.get_key_value())){
				selectidIdx = i;
			}
			i++;

		}

		this.tab.setSelectedIndex(selectidIdx);

		this.add(tab);

	}
}
