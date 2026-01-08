package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW;

import java.util.LinkedHashMap;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.XX_BasicContainerResizeHelper;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SaveOneString;
import panter.gmbh.Echo2.UserSettings.ENUM_USER_SAVEKEY;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;

public class BEW_ModelView_DisplayContainer extends E2_BasicModuleContainer {

	private String 				idToModelize 	= "";

	private MyE2_Grid mainGrid ; 

	private MyE2_Grid leftGrid ;
	

	private MyE2_Button previousBt = null;

	private boolean leereFeldernSichtBar = true;
	
	public BEW_ModelView_DisplayContainer(String oIdToModelize,_TAB table) throws myException {
		super();
		this.idToModelize = oIdToModelize;

		int [] iBreite = {510,500};

		String loaded_value =  new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.SESSION_HASH_BEWEGUNG_INFO_EMPTY_FIELD).LOAD();
		if(S.isFull(loaded_value)){
			this.leereFeldernSichtBar = loaded_value.equals("Y")?true:false;
		}
		
		this.set_oResizeHelper(new ownResizeHelper());

		this.mainGrid = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.mainGrid._setSize(iBreite);

		this.leftGrid = new MyE2_Grid(1);
		this.leftGrid.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		mainGrid.setColumnWidth(0, new Extent(510));
		
		this.add(mainGrid);

		baue_bewegung_struktur_2(table);

	}


	private void baue_bewegung_struktur_2(_TAB table) throws myException{
		switch(table){
		case bewegung:
			baue_neue_ui(new RECORD_BEWEGUNG(idToModelize));
			break;
		case bewegung_vektor:
			baue_neue_ui(getBewegungRecordFromBewegungVektorId(idToModelize));
			break;
		case bewegung_vektor_pos:
			baue_neue_ui(getBewegungRecordFromBewegungVektorPositionId(idToModelize));
			break;
		case bewegung_atom:
			baue_neue_ui(getBewegungRecordFromInBewegungAtomId(idToModelize));
			break;
		case bewegung_station:
			baue_neue_ui(getBewegungRecordFromStationId(idToModelize));
			break;
		default:
			break;
		}
	}


	private void baue_neue_ui(RECORD_BEWEGUNG bewegungRecord)throws myException{

		LinkedHashMap<String, Integer> mapping = new LinkedHashMap<String, Integer>();

		mapping.put(bewegungRecord.get_ID_BEWEGUNG_cUF_NN(""), new Integer(0));

		RECLIST_BEWEGUNG_VEKTOR bewVektorRecList = bewegungRecord.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_id_bewegung();

		for(RECORD_BEWEGUNG_VEKTOR bewVektorRecord: bewVektorRecList.get_v_records()){

			mapping.put(bewVektorRecord.get_ID_BEWEGUNG_VEKTOR_cUF_NN(""), new Integer(1));

			RECLIST_BEWEGUNG_VEKTOR_POS bewVektorPosRecList = bewVektorRecord.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor(null, BEWEGUNG_VEKTOR_POS.posnr.fieldName(), false);
			
			for (RECORD_BEWEGUNG_VEKTOR_POS bewVektorPosRecord: bewVektorPosRecList.get_v_records()){

				mapping.put(bewVektorPosRecord.get_ID_BEWEGUNG_VEKTOR_POS_cUF_NN(""), new Integer(2));

				RECLIST_BEWEGUNG_ATOM bewAtomRecList = bewVektorPosRecord.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos(null, BEWEGUNG_ATOM.posnr.fieldName()+ " ASC", false);

				for(RECORD_BEWEGUNG_ATOM bewAtomRecord: bewAtomRecList.get_v_records()){

					mapping.put(bewAtomRecord.get_ID_BEWEGUNG_ATOM_cUF_NN(""), new Integer(3));

					RECLIST_BEWEGUNG_STATION bewStationList = bewAtomRecord.get_DOWN_RECORD_LIST_BEWEGUNG_STATION_id_bewegung_atom(null, BEWEGUNG_STATION.mengenvorzeichen.fieldName(), false);

					for(RECORD_BEWEGUNG_STATION bewStation : bewStationList.get_v_records()){
						mapping.put(bewStation.get_ID_BEWEGUNG_STATION_cUF_NN(""), new Integer(4));
					}
				}

			}

		}

		int[] iBreite ={100,100,100,100,100};

		E2_Grid headerGrd = new E2_Grid()._setSize(iBreite)
				._bc(new E2_ColorDDark())
				._bo(new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID))
				._a_cm(new RB_lab("Bewegung")._b())
				._a_cm(new RB_lab("Vektor")._b())
				._a_cm(new RB_lab("Vektor pos")._b())
				._a_cm(new RB_lab("Atom")._b())
				._a_cm(new RB_lab("Station")._b())
				._endLine(new RB_gld()._col(new E2_ColorBase())._span(5));

		E2_Grid ergebnisGrd = new E2_Grid()._setSize(iBreite);

		for(String id:mapping.keySet()){

			String addOnInfo = "";
			
			if(mapping.get(id) == 4){
				String mv = new RECORD_BEWEGUNG_STATION(id).get_MENGENVORZEICHEN_cUF_NN("");
				if(mv.equals("1")){
					addOnInfo = "(A)";
				}else if(mv.equals("-1")){
					addOnInfo = "(L)";
				}
			}
			
			MyE2_Button bt = new MyE2_Button(bibALL.convertID2FormattedID(id) + " " + addOnInfo);
			bt.EXT().set_C_MERKMAL(id);
			bt.EXT().set_O_PLACE_FOR_EVERYTHING(mapping.get(id));
			bt.add_oActionAgent(new ShowDetailFromId_actionAgent());
			if(id.equals(idToModelize)){
				bt.setForeground(Color.RED);
			}

			ergebnisGrd
			._startLine(new RB_gld()._col(new E2_ColorBase())._span(mapping.get(id)))
			._a_cm(bt)
			._bo(new Border(1, new E2_ColorDDark(), Border.STYLE_SOLID));

		}

		MyE2_ContainerEx ergebnisContainer = new MyE2_ContainerEx(ergebnisGrd);
		ergebnisContainer.setHeight(new Extent(700));

		leftGrid.add(headerGrd);
		leftGrid.add(ergebnisContainer);	

		mainGrid.add(leftGrid);
	}


	private RECORD_BEWEGUNG getBewegungRecordFromBewegungVektorId(String bewegungVektorId) throws myException{
		return new RECORD_BEWEGUNG_VEKTOR(bewegungVektorId).get_UP_RECORD_BEWEGUNG_id_bewegung();
	}


	private RECORD_BEWEGUNG getBewegungRecordFromBewegungVektorPositionId(String bewegungVektorPosId) throws myException{
		return new RECORD_BEWEGUNG_VEKTOR_POS(bewegungVektorPosId)
				.get_UP_RECORD_BEWEGUNG_VEKTOR_id_bewegung_vektor()
				.get_UP_RECORD_BEWEGUNG_id_bewegung();
	}


	private RECORD_BEWEGUNG getBewegungRecordFromInBewegungAtomId(String bewegungAtomId)throws myException{
		return new RECORD_BEWEGUNG_ATOM(bewegungAtomId)
				.get_UP_RECORD_BEWEGUNG_VEKTOR_id_bewegung_vektor()
				.get_UP_RECORD_BEWEGUNG_id_bewegung();
	}


	private RECORD_BEWEGUNG getBewegungRecordFromStationId(String bewegungStationId)throws myException{
		return new RECORD_BEWEGUNG_STATION(bewegungStationId)
				.get_UP_RECORD_BEWEGUNG_ATOM_id_bewegung_atom()
				.get_UP_RECORD_BEWEGUNG_VEKTOR_id_bewegung_vektor()
				.get_UP_RECORD_BEWEGUNG_id_bewegung();
	}	


	private class ShowDetailFromId_actionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			MyE2_Button bt_ = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();

			bt_.setFont(new E2_FontBold());
			bt_.setBackground(new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT());

			if(! (previousBt== null)){
				previousBt.setFont(new E2_FontPlain());
				previousBt.setBackground(new E2_ColorDark());
			} 

			if(previousBt == bt_){
				mainGrid.removeAll();

				mainGrid.add(leftGrid, 1,1, E2_INSETS.I_0_0_0_0, E2_ALIGN.LEFT_TOP);

				previousBt = null;
			}else{

				previousBt = bt_;

				String id = bt_.EXT().get_C_MERKMAL();
				Integer levelOf = (Integer) bt_.EXT().get_O_PLACE_FOR_EVERYTHING();

				mainGrid.removeAll();

				mainGrid.add(leftGrid, 1,1, E2_INSETS.I_0_0_0_0, E2_ALIGN.LEFT_TOP);

				switch(levelOf.intValue()){
				case 0:
					mainGrid.add(detailContainer(new BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer.this, new RECORD_BEWEGUNG(id))), 1,1, E2_INSETS.I_0_0_0_0, E2_ALIGN.LEFT_TOP);
					break;
				case 1:
					mainGrid.add(detailContainer(new BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer.this, new RECORD_BEWEGUNG_VEKTOR(id))), 1,1, E2_INSETS.I_0_0_0_0, E2_ALIGN.LEFT_TOP);
					break;
				case 2:
					mainGrid.add(detailContainer(new BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer.this, new RECORD_BEWEGUNG_VEKTOR_POS(id))), 1,1, E2_INSETS.I_0_0_0_0, E2_ALIGN.LEFT_TOP);
					break;
				case 3:
					mainGrid.add(detailContainer(new BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer.this, new RECORD_BEWEGUNG_ATOM(id))), 1,1, E2_INSETS.I_0_0_0_0, E2_ALIGN.LEFT_TOP);
					break;
				case 4:
					mainGrid.add(detailContainer(new BEW_ModelView_Generic_detail_grid(BEW_ModelView_DisplayContainer.this, new RECORD_BEWEGUNG_STATION(id))), 1,1, E2_INSETS.I_0_0_0_0, E2_ALIGN.LEFT_TOP);
					break;
				}
			}
		}

	}
	
	private MyE2_ContainerEx detailContainer(BEW_ModelView_Generic_detail_grid detailGrid) throws myException{
		MyE2_ContainerEx det = new MyE2_ContainerEx(detailGrid);
		det.setBackground(new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT());
		det.setWidth(new Extent(this.get_oExtWidth().getValue()-525));
		det.setHeight(new Extent(this.get_oExtHeight().getValue()-100));		
		return det;
	}

	public boolean isLeereFeldernSichtBar() {
		return leereFeldernSichtBar;
	}


	public void setLeereFeldernSichtBar(boolean leereFeldernSichtBar) throws myException {
		new E2_UserSetting_SaveOneString(ENUM_USER_SAVEKEY.SESSION_HASH_BEWEGUNG_INFO_EMPTY_FIELD).SAVE(leereFeldernSichtBar?"Y":"N");
		this.leereFeldernSichtBar = leereFeldernSichtBar;
	}


	private class ownResizeHelper extends XX_BasicContainerResizeHelper{

		@Override
		public void do_actionAfterResizeWindow(E2_BasicModuleContainer ownContainer) throws myException {

			mainGrid.setColumnWidth(0, new Extent(510));
		}
	}

}
