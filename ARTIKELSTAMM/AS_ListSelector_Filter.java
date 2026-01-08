package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter.SF__Filter;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter.SF_andBlock;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter.SF_orLine;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class AS_ListSelector_Filter extends SF__Filter {

	public AS_ListSelector_Filter() throws myException {
		super();
		this.add_table(_TAB.artikel);
		this.add_table(_TAB.zolltarifnummer);
		this.add_table(_TAB.einheit);
	}



	@Override
	public void doInternalCheck() {

	}

	@Override
	public void fill_components_4_selector(	E2_Grid4MaskSimple grid4selektor, 
											MyE2_Button labelShowActualSelection,
											MyE2_Button bt_clear_anzeige, 
											MyE2_Button bt_start_filterselectdefinition) throws myException {
		
		grid4selektor.removeAll();
		
		
		String c_where = this.get_WhereBlock();
		if (S.isEmpty(c_where)) {
			labelShowActualSelection.setText("<leer>");
			bt_start_filterselectdefinition.setText("Filter definieren");
		} else {
			if (c_where.length()>100) {
				labelShowActualSelection.setText(c_where.substring(0,100));
			}else {
				labelShowActualSelection.setText(c_where);
			}
			labelShowActualSelection.setToolTipText(c_where);
			labelShowActualSelection.setLineWrap(true);
			
			int i=0;
			for (SF_andBlock and_b: this.get_ands_4_select()) {
				for (SF_orLine or_line: and_b.get_orLines()) {
					if (or_line.is_saved) {
						i++;
					}
				}
			}
			bt_start_filterselectdefinition.setText("Anzahl Bed.:"+i);
		}
		
		
		MyE2_Grid help = labelShowActualSelection.in_border(new E2_ColorDDark(), MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(0,0,0,0)), new Extent(200), new Extent(15));
		
		grid4selektor.def_(200).add_(help).def_(20).add_(bt_clear_anzeige).def_(100).add_(bt_start_filterselectdefinition);
		
	}

	
}
