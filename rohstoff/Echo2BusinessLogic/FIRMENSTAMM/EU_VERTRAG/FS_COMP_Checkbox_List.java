package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class FS_COMP_Checkbox_List extends MyE2_ContainerEx{

	private LinkedHashMap<String, RB_cb> hm_components;

	private E2_NavigationList navilist;

	public FS_COMP_Checkbox_List(E2_NavigationList p_naviList) throws myException {
		super();

		E2_Grid componentGrid = new E2_Grid()._bo_ddd()._setSize(new Extent(1, Extent.PC),new Extent(9, Extent.PC),new Extent(90, Extent.PC) )
				//25,110,390)
				._bo_no();

		this.navilist = p_naviList;

		ArrayList<Rec21> loadedItemList = initList();

		if(loadedItemList.size()>6) {
			componentGrid._setSize(
					new Extent(1, Extent.PC),new Extent(4, Extent.PC),new Extent(45, Extent.PC),
					new Extent(1, Extent.PC),new Extent(4, Extent.PC),new Extent(45, Extent.PC)
//					25,110,390, 25,110,390
					);
		}
		
		hm_components = new LinkedHashMap<>();

		for(Rec21 recAdr : loadedItemList){			
			RB_cb chkBox = new RB_cb();
			chkBox.setSelected(false);

			String anschrift_txt = recAdr.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2) + " - " + recAdr.get_ufs_kette(" ", ADRESSE.plz, ADRESSE.ort);

			Rec21 recLand = recAdr.get_up_Rec20(ADRESSE.id_land, LAND.id_land, false);

			//20181015: bei alten adressen kann es vorkommen, dass kein laendereintrag gemacht wurde. dies wird mit einer myException abgefangen
			if (recLand==null) {
				throw new myExceptionForUser(S.ms("Fehler! Es gibt in der Haupt- oder einer Lageradresse mind. einen leeren Eintrag im Land. Bitte vorher korrigieren !"));
			}
			
			
//			E2_Grid inner_grid = new E2_Grid()._setSize(25,110,390);
			E2_Grid land_grid = new E2_Grid()
					._setSize(90)
					._a(new RB_lab(recLand.get_ufs_dbVal(LAND.laendername))._fsa(-1), new RB_gld()._center_mid());
			
			if(recLand.is_yes_db_val(LAND.intrastat_jn)) {
				land_grid._bo_b();
			}

			componentGrid
			._a(chkBox, 							new RB_gld()._ins(10,2,2,2)._left_top())
			._a(land_grid, 							new RB_gld()._ins(10,2,2,2)._left_top())
			._a(new RB_lab(anschrift_txt)._fsa(-1), new RB_gld()._ins(10,2,2,2)._left_top());

			hm_components.put(recAdr.get_key_value(), chkBox);
//			componentGrid._a(inner_grid, new RB_gld()._ins(5,0,0,0)._left_top());
		}
		
		this.add(componentGrid);

		if(loadedItemList.size()>6) {
			this.setHeight(new Extent(150));
		}else {
			this.setHeight(new Extent(loadedItemList.size()*25));
		}
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
				.AND(new vgl(LIEFERADRESSE.id_adresse_basis, rec.get_key_value())).ORDERUP(ADRESSE.name1).s()
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
			}else {
				hm_components.get(item).setSelected(false);
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

}
