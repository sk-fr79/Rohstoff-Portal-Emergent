package panter.gmbh.Echo2.RB.COMP.SearchField;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField.SORTSTATUS;
import panter.gmbh.Echo2.RB.TOOLS.RB_border;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RB_ResultButtonArrays extends Vector<RB_ResultButton_IF[]> implements IF_Saveable{


	private  Integer    			actual_sort_col = null;
	private  SORTSTATUS  			actual_sort_status = null;
	
//	private RB_SearchFieldBase      calling_searchField = 		null;

	
	public RB_ResultButtonArrays() {
		super();
//		this.calling_searchField = p_calling_searchField;
	}
	
	
	/**
	 * sorts after column (0-n), sort_up=true, down=false
	 */
	public void sort() {
		if (this.actual_sort_col != null && this.actual_sort_status!=null) {
			Collections.sort(this, new comparator(this.actual_sort_col.intValue(), actual_sort_status.is_up()));
		}
	}
	
	
	
	
	private class comparator implements Comparator<RB_ResultButton_IF[]> {
		private int 		i_col = 	0;
		private boolean 	b_sort_up = true;
		
		public comparator(int p_i_col, boolean p_sort_up) {
			super();
			this.i_col = p_i_col;
			this.b_sort_up = p_sort_up;
		}

		@Override
		public int compare(RB_ResultButton_IF[] o1, RB_ResultButton_IF[] o2) {
			try {
				//sicherheitshalber, falls die vorige speicherung auf eine breitere liste zielte
				if (this.i_col>=o1.length) {
					this.i_col=0;   //dann automatisch nach spalte 0 sortieren
				}
				
				if (this.b_sort_up) {
					return o1[i_col].get_sort_string().compareTo(o2[i_col].get_sort_string());
				} else {
					return o2[i_col].get_sort_string().compareTo(o1[i_col].get_sort_string());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
	}


	public Integer get_actual_sort_col() {
		return actual_sort_col;
	}

	public void set_actual_sort_col(Integer actual_sort_row) {
		this.actual_sort_col = actual_sort_row;
	}

	public SORTSTATUS get_actual_sort_status() {
		return actual_sort_status;
	}

	public void set_actual_sort_status(SORTSTATUS actual_sort_status) {
		this.actual_sort_status = actual_sort_status;
	}

	
	
	@Override
	public String get_value_to_save() throws myException {
		if (this.actual_sort_col==null || this.actual_sort_status==null) {
			return "";
		} else {
			return ""+this.actual_sort_col.intValue()+"@"+this.actual_sort_status.name();
		}
	}

	
	@Override
	public void restore_value(String value) throws myException {
		if (S.isEmpty(value) || !value.contains("@")) {
			this.set_component_to_status_not_saved();
		} else {
			String i_col = value.substring(0,value.indexOf("@"));
			String status_name = value.substring(value.indexOf("@")+1);
			
			boolean b_fehler = false;
			
			MyInteger l = new MyInteger(i_col);
			if (l.get_bOK()) {
				this.actual_sort_col=new Integer(l.get_iValue());
			} else {
				b_fehler = true;
			}
			
			if (SORTSTATUS.get_status(status_name)!=null) {
				this.actual_sort_status=SORTSTATUS.get_status(status_name);
			} else {
				b_fehler = true;
			}
			
			if (b_fehler) {
				this.set_component_to_status_not_saved();
			}
		}
		
	}

	@Override
	public void set_component_to_status_not_saved() throws myException {
		this.actual_sort_col=null;
		this.actual_sort_status=null;
	}

	@Override
	public Component get_Comp() throws myException {
		return new MyE2_Label("");  //dummy
	}

	@Override
	public void add_action(XX_ActionAgent agent) throws myException {
	}

//	public RB_SearchFieldBase get_calling_searchField() {
//		return calling_searchField;
//	}
//
//	public void set_calling_searchField(RB_SearchField calling_searchField) {
//		this.calling_searchField = calling_searchField;
//	}
//	
	
	public void set_1st_column_focusable() {
		for  (RB_ResultButton_IF[] arr : this) {
			if (arr!=null && arr.length>0) {
				for (RB_ResultButton_IF rb: arr) {
					if (rb instanceof MyE2IF__Component) {
						((MyE2IF__Component)rb).focus_off();
					}
				}
			}
			//jetzt den ersten anschalten
			if (arr[0] instanceof MyE2IF__Component) {
				((MyE2IF__Component)arr[0]).focus_on();
				if (arr[0] instanceof Button) {
					((Button)arr[0]).setRolloverBorder(new RB_border()._col(Color.RED));
				}
			}
		}
	}

	
	/**
	 * 
	 * @return first component on first line
	 */
	public MyE2IF__Component get_focus_component() {
		MyE2IF__Component c = null;
		if (this.size()>0) {
			if (this.get(0)!=null && this.get(0).length>0) {
				if (this.get(0)[0] instanceof MyE2IF__Component) {
					c = (MyE2IF__Component)this.get(0)[0]; 
				}
			}
		}
		return c;
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 21.01.2019
	 *
	 * @return VEK with ids of member-lines
	 * @throws myException
	 */
	public VEK<Long> getIds() throws myException {
		VEK<Long> v = new VEK<>();
		
		for (RB_ResultButton_IF[] a: this) {
			if (a.length>0) {
				if (a[0].get_result_record() instanceof Rec20) {
					v._a(((Rec20)a[0]).getIdLong());
				} else {
					throw new myException(this,"Only useable with record of type Rec20");
				}
			}
		}
		return v;
	}
	
	
}
