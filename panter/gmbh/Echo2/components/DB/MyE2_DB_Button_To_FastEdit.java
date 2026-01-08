package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;



/**
 * spezieller button mit popup-fenster, fuer die schnelle eingabe von werten in einer listendarstellung (nur einige werte statt der ganzen maske)
 */
public abstract class MyE2_DB_Button_To_FastEdit extends MyE2_DB_Button implements E2_IF_Copy
{
	private MyE2_String   	cPopupTitelText = null;
	private MutableStyle  	oStyle = null; 
	private SQLField 		oSQLField = null;
	private String    		cVALIDATIONKEY = null;

	private Extent   		oExtPopupWidth = new Extent(500);
	private Extent   		oExtPopupHeight = new Extent(300);
	
	
	private E2_BasicModuleContainer   oModuleContainer4Popup = null;
	
	private boolean         bAllowEditEmptyFields = true;
	
	/**
	 * 
	 * @param osqlField
	 * @param PopupTitelText
	 * @param style
	 * @param VALIDATIONKEY
	 *        WICHTIG! E2_IF_Copy  muss implementiert werden
	 * @param PopupWidth 
	 * @param PopupHeight 
	 * @throws myException
	 */
	public MyE2_DB_Button_To_FastEdit(		SQLField 		osqlField,
											MyE2_String  	PopupTitelText, 
											MutableStyle 	style, 
											String 			VALIDATIONKEY, 
											Extent 			PopupWidth, 
											Extent 			PopupHeight) throws myException
	{
		super(osqlField, style);
		this.setIcon(E2_ResourceIcon.get_RI("edit_list.png"));
		this.cPopupTitelText = PopupTitelText;
		this.oStyle = style;
		this.oSQLField = osqlField;
		this.cVALIDATIONKEY = VALIDATIONKEY;
		this.set_cSetTextWhenEmpty(new MyE2_String("<???>",false));
		
		if (PopupWidth!=null) this.oExtPopupWidth = 	PopupWidth;
		if (PopupHeight!=null) this.oExtPopupHeight = 	PopupHeight;
		
		if (S.isFull(this.cVALIDATIONKEY))
		{
			this.add_GlobalAUTHValidator_AUTO(this.cVALIDATIONKEY);
		}
		
		this.add_oActionAgent(new ownActionAgent());
	}

	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			execInfo.get_MyActionEvent().make_ID_Validation_ADD_TO_Global_MV(bibALL.get_Vector(MyE2_DB_Button_To_FastEdit.this.get_cActualRowID()));
				
			if (bibMSG.get_bIsOK())
			{
			
				MyE2_DB_Button_To_FastEdit.this.oModuleContainer4Popup = MyE2_DB_Button_To_FastEdit.this.create_BasicContainer4Popup();
				MyE2_DB_Button_To_FastEdit.this.oModuleContainer4Popup.add_CloseActions(new ownActionRefreshNaviList_Line(MyE2_DB_Button_To_FastEdit.this.oModuleContainer4Popup));
				MyE2_DB_Button_To_FastEdit.this.oModuleContainer4Popup.CREATE_AND_SHOW_POPUPWINDOW(
											MyE2_DB_Button_To_FastEdit.this.oExtPopupWidth, 
											MyE2_DB_Button_To_FastEdit.this.oExtPopupHeight, 
											MyE2_DB_Button_To_FastEdit.this.cPopupTitelText);
			}
		}
	}
	
	/**
	 * nach jeder close-action, die mit true durchgefuehrt wurde, wird die navilist-zeile aktualisiert
	 */
	private class ownActionRefreshNaviList_Line extends XX_ActionAgentWhenCloseWindow
	{
		public ownActionRefreshNaviList_Line(E2_BasicModuleContainer container)
		{
			super(container);
		}

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			if (!this.get_bWasDone())
			{
//				if (this.get_oContainer().get_cWINDOW_CLOSE_STATUS().equals(E2_BasicModuleContainer.WINDOW_STATUS_CLOSE_WITH_SAVE))
//				{
					if (MyE2_DB_Button_To_FastEdit.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()!=null)
					{
						String cROW_ID = MyE2_DB_Button_To_FastEdit.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
						MyE2_DB_Button_To_FastEdit.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to().Refresh_ComponentMAP(cROW_ID, E2_ComponentMAP.STATUS_VIEW);
					}
//				}
			}
		}
	}
	
	
	public abstract E2_BasicModuleContainer 	create_BasicContainer4Popup() throws myException;
	
	public MyE2_String get_cPopupTitelText()
	{
		return cPopupTitelText;
	}
	
	public MutableStyle get_oStyle()
	{
		return oStyle;
	}
	
	public SQLField get_oSQLField()
	{
		return oSQLField;
	}
	
	public String get_cVALIDATIONKEY()
	{
		return cVALIDATIONKEY;
	}

	public E2_BasicModuleContainer get_oModuleContainer4Popup()
	{
		return oModuleContainer4Popup;
	}
	
	
	
}
