package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.MASK;

import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER._SCAN_KEY;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_SETTINGS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_MASK_MaskGrid extends E2_Grid4Mask {

	public SCAN_MASK_MaskGrid(SCAN_MASK_ComponentMapCollector  mapColl) throws myException {
		super();
		this._setSize(160,600)._bo_no()._st(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		SCAN_MASK_ComponentMap  map1 = (SCAN_MASK_ComponentMap) mapColl.get(new _SCAN_KEY());
				
		this._add(new RB_lab("Name Scanner")										,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.scanner_name))			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		this._add(new RB_lab("Standort")											,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.standort))				,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		this._add(new RB_lab("Auflösung")											,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.dpi))						,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		this._add(new RB_lab("Filetype")											,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.filetype))				,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		this._add(new RB_lab("Modul Kenner")										,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.module_kenner))			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		this._add(new RB_lab("Programm Kenner")										,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.programm_kenner))			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		this._add(new RB_lab("Schreifenscan")										,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.loop_scan))				,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		this._add(new RB_lab("Schleifen-Wartezeit")									,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.loop_timeout_seconds))	,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._add(new RB_lab("Scanner-Aufruf 1")									,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.scanner_aufruf1))			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._add(new RB_lab("Scanner-Aufruf 2")									,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.scanner_aufruf2))			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
			
		this._add(new RB_lab("Scanner-Aufruf 3")									,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.scanner_aufruf3))			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._add(new RB_lab("Scanner-Aufruf 4")									,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.scanner_aufruf4))			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		this._add(new RB_lab("Beschreibung")										,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._add(map1.getComp(new RB_KF(SCANNER_SETTINGS.beschreibung))			,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
	
	}
  
	
}
