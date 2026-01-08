package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import echopointng.Separator;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.BasicInterfaces.IF_wrappedMulticomponentsInGrid;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.TOOLS.RB_grid4masks;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_MASK_MaskGrid extends RB_grid4masks {

	IF_wrappedMulticomponentsInGrid  wrap = (Component... comps )-> {E2_Grid g = new E2_Grid(); for (Component c: comps) {g._gld(new RB_gld()._ins(0,0,10,0))._a_cm(c);} return g._s(comps.length); };


	public MC_DES_MASK_MaskGrid(RB_ComponentMapCollector  mapColl) throws myException {
		super();
		this._setSize(160,600)._bo_no();

		MC_DES_MASK_ComponentMap  map1 = (MC_DES_MASK_ComponentMap) mapColl.get(new MC_DES_KEY());

		this._a(map1.getComp(MASK_DEF_CELL.id_mask_def)			, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP)._span(2));
		
		this._a(new Separator() 								, new RB_gld()._al(E2_ALIGN.LEFT_TOP)._span(2));		
		
		this._a(new RB_lab("Zelle Id") 							, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.id_mask_def_cell)	, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._a(new RB_lab("Komponent") 						, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.classname)			, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(new RB_lab("Feldname") 							, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._a(wrap.grid(
				map1.getComp(MASK_DEF_CELL.fieldname), 
				map1.getComp(MC_DES_CONST.KUSTOM_COMPONENT.BT_FIELD_ASSISTANT.key())
				), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._a(new RB_lab("Feld hoehe") 						, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.field_heigth)		, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(new RB_lab("Feld laenge") 						, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.field_length)		, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(new RB_lab("Spaltenabstand") 					, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.colspan)				, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(new RB_lab("Linieabstand") 						, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.rowspan)				, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._a(new RB_lab("Spalte") 							, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.start_col_in_mask)	, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(new RB_lab("Linie") 							, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.start_row_in_mask)	, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._a(new RB_lab("usertext") 							, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.usertext)			, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));

		RB_gld  left_gld = new RB_gld()._left_top()._ins(2,2,5,2);

		this._a("Ausrichtung" 									, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		this._a(map1.getComp(MASK_DEF_CELL.alignment) 			, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
		
		this._a(new E2_Grid()._setSize(160, 120, 120, 120)
				._a(new RB_lab())
				._a(new RB_lab("Italic")._i(), 					left_gld)
				._a(new RB_lab("Fett")._b(),					left_gld)
				._a(new RB_lab("Hoehe"), 						left_gld)
				._a(new RB_lab()._t("Text"), 					left_gld)
				._a(map1.getComp(MASK_DEF_CELL.text_italic), 	left_gld)
				._a(map1.getComp(MASK_DEF_CELL.text_bold), 		left_gld)
				._a(map1.getComp(MASK_DEF_CELL.text_size), 		left_gld)	
				,new RB_gld()._ins(2,2,2,10)._al(E2_ALIGN.LEFT_TOP)._span(2));

		this._a(new E2_Grid()._setSize(160, 120, 120, 120, 120)
				._a(new RB_lab())
				._a(new RB_lab("Links"), 						left_gld)
				._a(new RB_lab("Oben"), 						left_gld)
				._a(new RB_lab("Recht"), 						left_gld)
				._a(new RB_lab("Unten"), 						left_gld)
				._a(new RB_lab("Insets"), 						left_gld)
				._a(map1.getComp(MASK_DEF_CELL.left_insets), 	left_gld)
				._a(map1.getComp(MASK_DEF_CELL.top_insets), 	left_gld)
				._a(map1.getComp(MASK_DEF_CELL.right_insets), 	left_gld)
				._a(map1.getComp(MASK_DEF_CELL.bottom_insets), 	left_gld)

				,new RB_gld()._ins(2,10,2,2)._al(E2_ALIGN.LEFT_TOP)._span(2));
	}

	
}

