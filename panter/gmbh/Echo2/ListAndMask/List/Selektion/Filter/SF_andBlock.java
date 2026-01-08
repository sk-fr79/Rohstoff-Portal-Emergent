package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import java.util.Vector;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class SF_andBlock {

	private Vector<SF_orLine>  	v_orLines = new Vector<>();
	private SF__Filter  		motherSelector = null;
	private MutableStyle 		style_of_grid = MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS();
	private MyE2_Grid 			grid_with_components = new MyE2_Grid(); 
	
	public SF_andBlock(SF__Filter  p_motherSelector) throws myException {
		super();
		this.motherSelector = p_motherSelector;
		this.grid_with_components.setStyle(this.style_of_grid);
		this.grid_with_components.set_Spalten(SF_Filter_CONST.get_i_breiten());
	}

	public SF_andBlock add_orLine(SF_orLine or) {
		this.v_orLines.add(or);
		
		return this;
	}

	public Vector<SF_orLine> get_orLines() {
		return v_orLines;
	}
	
	
	public SF_orLine get_last_or_line() {
		if (this.v_orLines.size()==0) {
			return null;
		} else {
			return this.v_orLines.get(this.v_orLines.size()-1);
		}
	}
	
	
	
	public void fill_grid_with_components() throws myException {
		//zuerst nachsehen, ob alle or-lines geschlossen sind, wenn ja, wird eine leere zeile angefuegt
		this.grid_with_components.removeAll();
		
		
		boolean b_add_empty_line = true;
		if (this.get_orLines().size()>0) {
			if (this.get_orLines().get(this.get_orLines().size()-1).is_saved) {
				b_add_empty_line=true;
			} else {
				b_add_empty_line=false;
			}
		}
		
		if (b_add_empty_line) {
			this.add_orLine(new SF_orLine(this.motherSelector,this));
		}
		
		boolean b_first = true;
		for (SF_orLine or_line: this.get_orLines()) {

			if (b_first) {
				this.grid_with_components.add(new MyE2_Label(new MyE2_String(""),new E2_FontBoldItalic(-2)));
				b_first = false;
			} else {
				this.grid_with_components.add(new MyE2_Label(new MyE2_String("oder"),new E2_FontBoldItalic(-2)));
			}
			this.grid_with_components.add(or_line.get_selfield(),1,E2_INSETS.I(1,1,1,1));
			this.grid_with_components.add(or_line.get_selComps(),E2_INSETS.I(1,1,1,1));
			this.grid_with_components.add(or_line.get_containerInput(),E2_INSETS.I(1,1,1,1));
			this.grid_with_components.add(or_line.get_bt_del(),E2_INSETS.I(1,1,1,1));
			
			if (or_line.is_saved) {
				or_line.get_selfield().set_bEnabled_For_Edit(false);
				or_line.get_selComps().set_bEnabled_For_Edit(false);
				or_line.get_containerInput().set_bEnabled_For_Edit(false);
				this.grid_with_components.add(or_line.get_bt_openLine());
			} else {
				or_line.get_selfield().set_bEnabled_For_Edit(true);
				or_line.get_selComps().set_bEnabled_For_Edit(true);
				or_line.get_containerInput().set_bEnabled_For_Edit(true);
				this.grid_with_components.add(or_line.get_bt_save());
			}
			
		}
		
	}

	 
	public String get_sql_where_block() {
		String or_const = " OR ";
		String c_rueck = "";
		for (SF_orLine or_line: this.v_orLines) {
			String s_where=or_line.get_sql_where_block();
			if (S.isFull(s_where.trim())) {
				c_rueck = c_rueck +" "+s_where+or_const;
			}
		}
		
		if (c_rueck.endsWith(or_const)) {
			c_rueck="("+c_rueck.substring(0, c_rueck.length()-or_const.length())+")";
		}
		
		return c_rueck;
	}
	


	public MyE2_Grid get_grid_with_and_block() {
		return grid_with_components;
	}

	public SF_andBlock get_and_block_copy() throws myException {
		SF_andBlock and_b =  new SF_andBlock(this.motherSelector);
		for (SF_orLine or_line: this.v_orLines) {
			and_b.add_orLine(or_line.get_or_Copy(and_b));
		}
		return and_b;
	}
}