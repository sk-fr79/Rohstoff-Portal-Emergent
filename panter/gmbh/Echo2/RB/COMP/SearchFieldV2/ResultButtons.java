package panter.gmbh.Echo2.RB.COMP.SearchFieldV2;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.RB.TOOLS.RB_border;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SIMPLE;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class ResultButtons extends VEK<ResultButton[]> {

	private  Integer    				actualSortCol = null;
	private  EnSortStatusButtonGrid 	actualSortStatus = null;
	
	
	private  String   					saveSortSettingsKey = null;
//	private IfRenderPopupGridForSearchResults           renderer = null;
	
//	//in dieses grid wird der resultbutton geaendert
//	private E2_Grid    					grid4popup = new E2_Grid();
	
	
	public ResultButtons(String p_saveSortSettingsKey) {
		super();
		this.saveSortSettingsKey = p_saveSortSettingsKey;
	}
	
	
	/**
	 * sorts after column (0-n), sort_up=true, down=false
	 */
	public void sort() {
		if (this.actualSortCol != null && this.actualSortStatus!=null) {
			Collections.sort(this, new comparator(this.actualSortCol.intValue(), actualSortStatus.isUp()));
		}
	}
	
	
	public ResultButtons _readStoredSortStatus() {
		
		try {
			E2_UserSetting_SIMPLE setting = new E2_UserSetting_SIMPLE(saveSortSettingsKey);
			String val = S.NN((String) setting.get_Settings(saveSortSettingsKey));
			
			Vector<String> vec  = bibALL.TrenneZeile(val, "@");
			
			if (vec!=null && vec.size()==2) {
				MyLong l = new MyLong(vec.get(0)) ;
				String sortString = vec.get(1);
				
				EnSortStatusButtonGrid status = EnSortStatusButtonGrid.getStatus(sortString);
				
				if (status != null && l.isOK()) {
					this.actualSortStatus = status;
					this.actualSortCol = l.get_iValue();
				}
			}
			
			
		} catch (myException e) {
			e.printStackTrace();
			actualSortCol = null;
			actualSortStatus = null;
		}
		
		
		return this;
	}
	
	
	
	private class comparator implements Comparator<ResultButton[]> {
		private int 		i_col = 	0;
		private boolean 	b_sort_up = true;
		
		public comparator(int p_i_col, boolean p_sort_up) {
			super();
			this.i_col = p_i_col;
			this.b_sort_up = p_sort_up;
		}

		@Override
		public int compare(ResultButton[] o1, ResultButton[] o2) {
			try {
				//sicherheitshalber, falls die vorige speicherung auf eine breitere liste zielte
				if (this.i_col>=o1.length) {
					this.i_col=0;   //dann automatisch nach spalte 0 sortieren
				}
				
				if (this.b_sort_up) {
					return o1[i_col].getSortString().toUpperCase().compareTo(o2[i_col].getSortString().toUpperCase());
				} else {
					return o2[i_col].getSortString().toUpperCase().compareTo(o1[i_col].getSortString().toUpperCase());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		
	}


	public Integer getActualSortCol() {
		return actualSortCol;
	}

	public void setActualSortCol(Integer actual_sort_row) {
		this.actualSortCol = actual_sort_row;
	}

	public EnSortStatusButtonGrid getActualSortStatus() {
		return actualSortStatus;
	}

	public void setActualSortStatus(EnSortStatusButtonGrid actual_sort_status) {
		this.actualSortStatus = actual_sort_status;
	}

	
	


	public void setFirstColumnFocusable() {
		for  (ResultButton[] arr : this) {
			if (arr!=null && arr.length>0) {
				for (ResultButton rb: arr) {
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
	public MyE2IF__Component getFocusComponent() {
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
		for (ResultButton[] a: this) {
			v._a(a[0].getRec21().getIdLong());
		}
		return v;
	}


	public String getSaveSortSettingsKey() {
		return saveSortSettingsKey;
	}
	
}
