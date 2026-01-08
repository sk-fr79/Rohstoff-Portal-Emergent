package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class UP_DOWN_ListComponent_AddOn extends MyE2_DB_PlaceHolder
{

	private AM_BasicContainer popup_For_UP_AND_DOWN_FILES = null;
	
	
	
	public UP_DOWN_ListComponent_AddOn(SQLField osqlField, AM_BasicContainer popupFor_UP_AND_DOWN_FILES) throws myException
	{
		super(osqlField);
		this.popup_For_UP_AND_DOWN_FILES = popupFor_UP_AND_DOWN_FILES;
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String id_archivmedien, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		this.removeAll();
		if (this.popup_For_UP_AND_DOWN_FILES.get_AddOn_COMPONENT_FACTORY()==null) {
			this.add(new MyE2_Label(new MyE2_String(S.NN(id_archivmedien,"-"))));
		} else {
			this.add_raw(this.popup_For_UP_AND_DOWN_FILES.get_AddOn_COMPONENT_FACTORY().generate_Component(this.EXT().get_oComponentMAP(),id_archivmedien));
		}
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		
		UP_DOWN_ListComponent_AddOn oGridCopy;
		try {
			oGridCopy = new UP_DOWN_ListComponent_AddOn(this.EXT_DB().get_oSQLField(),this.popup_For_UP_AND_DOWN_FILES);
			return oGridCopy;
		}
		catch (myException e) {
			throw new myExceptionCopy(e.getOriginalMessage());
		}
	}

	
}
