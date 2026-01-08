package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class FS_COMP_Checkbox_List_v2 extends E2_Grid{

	private LinkedHashMap<String, RB_cb> hm_components;
	private TreeMap<String, RB_cb> hm_selector;
	
	private E2_NavigationList navilist;
	
	public FS_COMP_Checkbox_List_v2(E2_NavigationList p_naviList) throws myException {
		super();
		this._s(1);
		E2_Grid componentGrid = new E2_Grid()._s(1)._bo_no();
		E2_Grid selectorGrid = new E2_Grid()._bo_no();
		
		this.navilist = p_naviList;
		
		ArrayList<Rec21> loadedItemList = initList();

		hm_components = new LinkedHashMap<>();
		hm_selector = new TreeMap<>();
		
		for(Rec21 recAdr : loadedItemList){			

			String anschrift_txt = recAdr.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2) + " - " + recAdr.get_ufs_kette(" ", ADRESSE.plz, ADRESSE.ort);
			
			Rec21 recLand = recAdr.get_up_Rec20(ADRESSE.id_land, LAND.id_land, false);
			
			RB_cb chkBox = new RB_cb();
			chkBox.setSelected(false);
			chkBox.EXT().set_C_MERKMAL(recLand.get_key_value());
			
			if(! hm_selector.containsKey(recLand.get_key_value())){
				RB_cb land_cb = new RB_cb()._t(recLand.getUfs(LAND.laendercode));
				land_cb.EXT().set_C_MERKMAL(recLand.get_key_value());
				land_cb._aaa(new own_select_lager_per_land());
				if(recLand.is_yes_db_val(LAND.intrastat_jn)) {
					land_cb._b();
				}
				
				hm_selector.put(recLand.get_key_value(), land_cb);
			}
			
			E2_Grid inner_grid = new E2_Grid()._setSize(25,100,400);
			E2_Grid land_grid = new E2_Grid()._setSize(90)._a(recLand.get_ufs_dbVal(LAND.laendername), new RB_gld()._center_mid());
			if(recLand.is_yes_db_val(LAND.intrastat_jn)) {
				land_grid._bo_b();
			}
			
			inner_grid
			._a(chkBox, 		new RB_gld()._ins(2)._center_mid())
			._a(land_grid, 		new RB_gld()._ins(2)._center_mid())
			._a(anschrift_txt, 	new RB_gld()._ins(2)._left_mid());
			
			hm_components.put(recAdr.get_key_value(), chkBox);
			componentGrid.add(inner_grid);
		}
		
		RB_cb cb_nur_eu_landern = new RB_cb()._t("Nur EU ländern")._b();	
		cb_nur_eu_landern._aaa(()->auto_select_intrastat_land());
		
		selectorGrid._s(hm_selector.size()+1);
		selectorGrid._a(cb_nur_eu_landern);
		for(String land_code: hm_selector.keySet()) {
			selectorGrid._a(hm_selector.get(land_code), new RB_gld()._ins(2,1,10,1));
		}
		
		
		MyE2_ContainerEx container = new MyE2_ContainerEx(componentGrid);
		if(hm_components.size()>5) {
			container.setWidth(new Extent(880));
			container.setHeight(new Extent(180));
		}
		
		this._a(selectorGrid , new RB_gld()._left_top()._ins(0,2,0,8));
		this._a(container , new RB_gld()._left_top()._ins(0,2,0,8));
		
	}

	public HashMap<String, RB_cb> getComponentMap() {
		return hm_components;
	}

	public ArrayList<Rec21> initList() throws myException{
		ArrayList<Rec21> lagerRecordList = new ArrayList<>();

		Rec21_adresse rec = new Rec21_adresse()._fill_id(this.navilist.get_vSelectedIDs_Unformated_Select_the_one_and_only().get(0));

		RecList21 lieferAdresseRecList = new RecList21(_TAB.adresse)._fill(
		new SEL(ADRESSE.values()).FROM(_TAB.adresse).INNERJOIN(_TAB.lieferadresse, ADRESSE.id_adresse, LIEFERADRESSE.id_adresse_liefer)
		.WHERE(new vgl_YN(ADRESSE.aktiv,true))
		.AND(new vgl(ADRESSE.id_adresse, COMP.GE, "1000"))
		.AND(new vgl(LIEFERADRESSE.id_adresse_basis, rec.get_key_value())).ORDERUP(ADRESSE.id_adresse).s()
		);
		
		for(Rec21 lieferAdresseRecord: lieferAdresseRecList){

				lagerRecordList.add(lieferAdresseRecord);
		}

		return lagerRecordList;
	}
	
	public VEK<String> getSelectedItems(){
		VEK<String> selectedItems = new VEK<String>();
		for(String item: hm_components.keySet()){
			if(hm_components.get(item).isSelected()){
				selectedItems.add(item);
			}
		}
		return selectedItems;
	}	

	public void auto_select_intrastat_land() throws myException{
		for(String item: hm_components.keySet()){
			Rec21 recLand = new Rec21_adresse()._fill_id(item).get_up_Rec21(ADRESSE.id_land, LAND.id_land,false);
			if(recLand.is_yes_db_val(LAND.intrastat_jn)) {
				hm_components.get(item).setSelected(true);
				hm_selector.get(recLand.get_key_value()).setSelected(true);
			}else {
				hm_components.get(item).setSelected(false);
				hm_selector.get(recLand.get_key_value()).setSelected(false);
			}
		}
	}
	
	public VEK<String> getSelectedIds(){
		VEK<String> selectedItems = new VEK<String>();
		for(String item: hm_components.keySet()){
			if(hm_components.get(item).isSelected()){
				selectedItems.add(item);
			}
		}
		return selectedItems;
	}
	
	public void setFixedSize(int width, int height){
		this.setWidth(new Extent(width-20));
		this.setHeight(new Extent(height));
	}

	private class own_select_lager_per_land extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_cb cb = (RB_cb)oExecInfo.get_MyActionEvent().getSource();
			
			boolean status = cb.isSelected();
			
			String ufs_id_land = cb.EXT().get_C_MERKMAL();
			
			for(String item: hm_components.keySet()){
				if(hm_components.get(item).EXT().get_C_MERKMAL().equals(ufs_id_land)) {
					hm_components.get(item).setSelected(status);
				}
			}
		}
	}
	
}
