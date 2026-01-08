package panter.gmbh.Echo2.components.activeReport_NG;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

public class AR_GridFiller {

	private int 		i_space_cols_left = 	0;
	
	public AR_GridFiller(int p_space_cols_left, AR_Grid grid2fill, IF_AR_Component[][] comps) throws myException {
		super();
		this.i_space_cols_left = p_space_cols_left;
				
		if (comps!=null) {
			for (int i=0;i<comps.length;i++) {
				this.fill_left_space(grid2fill,comps[i]);
				for (int k=0;k<comps[i].length;k++) {
					Component comp = comps[i][k].comp();
					comp.setLayoutData(comps[i][k].get_layoutData());
					grid2fill.add_raw(comp);
				}
				this.fill_right_space(grid2fill,comps[i]);
			}
		}
	}

	
	//sammelt die colums in einer zeile
	private int collectLen(IF_AR_Component[] comps) throws myException {
		int i=0;
		
		for (IF_AR_Component c: comps) {
			
			GridLayoutData  ld = c.get_layoutData();
			if (ld==null) {
				throw new myException(this,"You MUST generate compents WITH A GRIDLAYOUTDATA !!!");
			}
			if (ld.getColumnSpan()==0) {
				i++;
			} else {
				i+=ld.getColumnSpan();
			}
		}
		
		return i;
	}
	
	
	private void fill_left_space(AR_Grid grid2fill, IF_AR_Component[] compsInLine) throws myException {
		int i_cols_of_block = this.collectLen(compsInLine);
		int i_right_fill_rows = grid2fill.getSize()-i_cols_of_block-this.i_space_cols_left;

		if (i_right_fill_rows<0) {
			throw new myException(this,"Size of your grid is TOO SMALL (1)!!");
		}
		
		//jetzt die anzahl der links liegenden elemente mit leeren labels fuellen
		if (i_space_cols_left>0) {
			grid2fill.add_raw(new MyE2_Label(""),AR_Grid.LAYOUT_LEFT(E2_INSETS.I(0,0,0,0), grid2fill.getColorBase(), i_space_cols_left));
		}
	}

	
	private void fill_right_space(AR_Grid grid2fill, IF_AR_Component[] compsInLine) throws myException {
		int i_cols_of_block = this.collectLen(compsInLine);
		int i_right_fill_rows = grid2fill.getSize()-i_cols_of_block-this.i_space_cols_left;
		
		if (i_right_fill_rows<0) {
			throw new myException(this,"Size of your grid is TOO SMALL (2)!!");
		}
		
		//jetzt die anzahl der nicht gefuellten Spalten auffuellen
		if (i_right_fill_rows>0) {
			grid2fill.add_raw(new MyE2_Label(""),AR_Grid.LAYOUT_LEFT(E2_INSETS.I(0,0,0,0), grid2fill.getColorBase(), i_right_fill_rows));
		}

	}
	
}
