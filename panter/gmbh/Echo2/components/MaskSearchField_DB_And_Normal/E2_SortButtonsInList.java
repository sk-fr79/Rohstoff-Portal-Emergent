package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_ComponentExt;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SaveSortButton_IN_SearchResultWindow;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.exceptions.myException;


public class E2_SortButtonsInList extends MyE2_Grid
{
	public static int    SORTSTATUS_UNSORTED = 	0;
	public static int    SORTSTATUS_UP =		1;
	public static int    SORTSTATUS_DOWN = 		2;
	
	
	private MyE2_Label 							labelSortUp = new MyE2_Label(E2_ResourceIcon.get_RI("sortup.png"));
	private MyE2_Label 							labelSortDown = new MyE2_Label(E2_ResourceIcon.get_RI("sortdown.png"));
	private MyE2_Label 							labelSortEmpty = new MyE2_Label("");
	
	private int									ColumnNumber	= -1;

	private MyE2_Button  						oButtonIntern = null;
	
	private E2_GridWithSearchResultbuttons  	oGridWithSearchResultbuttons = null;

	private int                       			iActualSortStatus = E2_SortButtonsInList.SORTSTATUS_UNSORTED;
	
	private DefSpalteLayout_And_Else  			SpalteDef = null;
	

	
	public DefSpalteLayout_And_Else get_oSpalteDef()
	{
		return SpalteDef;
	}


	/**
	 * 
	 * @param iColumnNumberInVisibleList
	 * @param GridWithSearchResultbuttons
	 * @param oSpalteDef
	 * @throws myException
	 */
	public E2_SortButtonsInList(	int 							iColumnNumberInVisibleList, 	
									E2_GridWithSearchResultbuttons  GridWithSearchResultbuttons,
									DefSpalteLayout_And_Else   		oSpalteDef) throws myException
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.ColumnNumber = 				iColumnNumberInVisibleList;
		this.oGridWithSearchResultbuttons = GridWithSearchResultbuttons;
		
		this.SpalteDef = oSpalteDef;
		
		if (this.SpalteDef==null)
		{
			this.SpalteDef = new 	DefSpalteLayout_And_Else(	iColumnNumberInVisibleList,
																true, 
																new MyE2_String("Spalte ",true,""+(iColumnNumberInVisibleList+1),false), 
																"SPALTE"+(iColumnNumberInVisibleList+1));
		}
		
		
		this.oButtonIntern = new MyE2_Button(this.SpalteDef.get_cSpalteButton_Or_LabelText());
		this.oButtonIntern.setLineWrap(this.SpalteDef.get_bLineWrapTitel());
		this.oButtonIntern.setStyle(new sortButtonStyle());
		this.oButtonIntern.set_bEnabled_For_Edit(this.SpalteDef.get_bSpalteIsSortable());                     //damit der button gleich aussieht
		
		this.add(this.oButtonIntern);
		this.add(this.labelSortEmpty);
		
		this.oButtonIntern.add_oActionAgent(new actionSortList(this.ColumnNumber));
		
	}

	
	private void SetToNextSortStatus()
	{
		int iOldSortStatus = this.iActualSortStatus;
		
		if (iOldSortStatus==E2_SortButtonsInList.SORTSTATUS_UNSORTED)
		{
			this.iActualSortStatus = E2_SortButtonsInList.SORTSTATUS_UP;
		}
		else if (iOldSortStatus==E2_SortButtonsInList.SORTSTATUS_UP)
		{
			this.iActualSortStatus = E2_SortButtonsInList.SORTSTATUS_DOWN;
		}
		else
		{
			this.iActualSortStatus = E2_SortButtonsInList.SORTSTATUS_UP;     //status UP springt nach DOWN, nicht nach unsorted
		}
	}
	
	
	public void set_StatusUP()
	{
		this.iActualSortStatus = E2_SortButtonsInList.SORTSTATUS_UP;
	}
	public void set_StatusDOWN()
	{
		this.iActualSortStatus = E2_SortButtonsInList.SORTSTATUS_DOWN;
	}
	
	
	public boolean get_bIsUnsorted()
	{
		return this.iActualSortStatus==E2_SortButtonsInList.SORTSTATUS_UNSORTED;
	}
	
	public boolean get_bIsSortedUP()
	{
		return this.iActualSortStatus==E2_SortButtonsInList.SORTSTATUS_UP;
	}
	
	
	public void Reset()
	{
		this.iActualSortStatus = E2_SortButtonsInList.SORTSTATUS_UNSORTED;
		this.BaueSortButtonNeu();
	}
	
	private void BaueSortButtonNeu()
	{
		this.removeAll();
		this.add(this.oButtonIntern);
		if (this.get_bIsUnsorted())
		{
			this.add(this.labelSortEmpty);
		}
		else if (this.get_bIsSortedUP())
		{
			this.add(this.labelSortUp);
		}
		else
		{
			this.add(this.labelSortDown);
		}

		
		
	}

	
	private class sortButtonStyle extends E2_MutableStyle
	{

		public sortButtonStyle()
		{
			super();
			this.setProperty(AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
			this.setProperty(AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
			this.setProperty(AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
			this.setProperty(AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE);
			this.setProperty(AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
			this.setProperty(AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
			this.setProperty(AbstractButton.PROPERTY_FONT, new E2_FontItalic());
			this.setProperty(AbstractButton.PROPERTY_LINE_WRAP, new Boolean(false));

		}

	}

	
	/**
	 * sortieragent fuer die sort-buttons
	 * 
	 * @author martin
	 * 
	 */
	private class actionSortList extends XX_ActionAgent
	{
		public actionSortList(int columnNumber)
		{
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_SortButtonsInList oThis = E2_SortButtonsInList.this;

//			//zuerst alle sortbuttons suchen und resetten
//			Vector<E2_SortButtonsInList> vAlleKollegen = new E2_RecursiveSearch_ComponentExt(	oThis.oGridWithSearchResultbuttons,
//																								E2_SortButtonsInList.class).get_vAllComponents();
//			
//			for (E2_SortButtonsInList oTestButton: vAlleKollegen)
//			{
//				if (oTestButton != oThis)
//				{
//					oTestButton.Reset();
//				}
//			}
			oThis.SetToNextSortStatus();
//			oThis.BaueNeu();
			
//			Vector<XX_Button4SearchResultList[]> 	vButtons = oThis.oGridWithSearchResultbuttons.get_vButtons();
			
//			//sortierung durchfuehren
//			new E2_SortVector(vButtons, oThis.ColumnNumber, oThis.get_bIsSortedUP());
//			
//			//liste neu bauen
//			oThis.oGridWithSearchResultbuttons.__baue_ListeAuf();
			oThis.do_Sort(oThis.ColumnNumber);
			
			
			//jetzt den status abspeichern
			E2_UserSetting_SaveSortButton_IN_SearchResultWindow oSaver = new E2_UserSetting_SaveSortButton_IN_SearchResultWindow(oThis, oThis.oGridWithSearchResultbuttons.get_oSearchField());
			if (oSaver.get_bCanSaveStatus())
			{
				oSaver.saveSelectedStatus();
			}
			
			
		}

	}
	
	
	public void do_Sort(int iColumn) throws myException
	{
		
		//zuerst alle sortbuttons suchen und resetten
		Vector<E2_SortButtonsInList> vAlleKollegen = new E2_RecursiveSearch_ComponentExt(	this.oGridWithSearchResultbuttons,
																							E2_SortButtonsInList.class).get_vAllComponents();
		
		if (iColumn>=vAlleKollegen.size())
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sortierung unmöglich, Spaltennummer zu gross !!")));
			return;
		}

			
		for (E2_SortButtonsInList oTestButton: vAlleKollegen)
		{
			if (oTestButton != this)
			{
				oTestButton.Reset();
			}
		}

		this.BaueSortButtonNeu();
		
		//sortierung durchfuehren
		new E2_SortVector(this.oGridWithSearchResultbuttons.get_vButtons(), iColumn, this.get_bIsSortedUP());
		
		//liste neu bauen
		this.oGridWithSearchResultbuttons.__baue_ListeAuf();

	}

	
	
}
