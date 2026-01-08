package panter.gmbh.Echo2.ListAndMask.List;

import java.util.HashMap;
import java.util.Map.Entry;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.SelectField;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_UserSetting_naviList_column_width;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * ausgelagerte klasse zum restaurieren der Spaltenbreiten
 * @author martin
 *
 */
public class E2_NavigationList_RestoreUserSettings_ColumnWidth {
	 
	private E2_NavigationList  navilist_to_restore = null;
	
	public E2_NavigationList_RestoreUserSettings_ColumnWidth(E2_NavigationList p_navilist_to_restore) throws myException {
		super();
		this.navilist_to_restore = p_navilist_to_restore;
		
		E2_ComponentMAP map_ref = this.navilist_to_restore.get_oComponentMAP__REF();
		
		HashMap<String, String> hmKeysAndWidth= new E2_UserSetting_naviList_column_width(this.navilist_to_restore).READ_STORED_COL_WIDTHS();
		
		
		for (Entry<String, String> e: hmKeysAndWidth.entrySet()) {
			if (S.isFull(e.getValue())) {
				MyLong l = new MyLong(e.getValue());
				if (l.get_bOK()) {
					map_ref.get__Comp(e.getKey()).EXT().set_oColExtent(new Extent(l.get_iValue()));
					
					if (navilist_to_restore.isExtendWidthWithSumOfAllRowWidth()) {
						//dann auch die button-breite setzen
						map_ref.get__Comp(e.getKey())._setWidthOfTitleButton(l.get_iValue());
					}
					
					this.change_width_of_col_element_if_possible(map_ref.get_Comp(e.getKey()), l);
				}
			}
		}
		
		if (navilist_to_restore.isExtendWidthWithSumOfAllRowWidth()) {
			int lWidthGesamt = 0;
			for (Entry<String, String> e: hmKeysAndWidth.entrySet()) {
				if (S.isFull(e.getValue())) {
					MyLong l = new MyLong(e.getValue());
					if (l.get_bOK()) {
						lWidthGesamt+= l.get_iValue();
					}
				} else {
					//die standard-breiten auslesen
					Extent ext = map_ref.get__Comp(e.getKey()).EXT().get_oColExtent();
					if (ext!=null) {
						lWidthGesamt+=ext.getValue();
					}
				}
			}
			if (lWidthGesamt<1900) {
				lWidthGesamt=1900;
			}
			navilist_to_restore.getContainerForContent().setWidth(new Extent(100,Extent.PERCENT));
			navilist_to_restore.get_oDataGrid().setWidth(new Extent(lWidthGesamt));
		}
	}
	
	/*
	 * hier werden die spalteninhalte geprueft und falls definierbar, deren breite angepasst
	 */
	private void change_width_of_col_element_if_possible(Component comp,   MyLong width) {
		if(width.get_bOK()) {
		
			if (comp instanceof SelectField) {
				((SelectField) comp).setWidth(new Extent(width.get_iValue()-4));
			} 
			
			if (comp instanceof MyE2_DB_MultiComponentColumn) {
				MyE2_DB_MultiComponentColumn m_col = (MyE2_DB_MultiComponentColumn) comp;
				for (MyE2IF__Component c: m_col.get_hmComponents().values()) {
					if (c instanceof Component) {
						this.change_width_of_col_element_if_possible((Component)c, width);
					}
				}
			}
			
			if (comp instanceof Label) {
				((Label)comp).setLineWrap(true);
			}
		
			if (comp instanceof MyE2_DB_Label_INROW) {
				((MyE2_DB_Label_INROW)comp).setLineWrap(true);
			}
		}
	}
	
}
