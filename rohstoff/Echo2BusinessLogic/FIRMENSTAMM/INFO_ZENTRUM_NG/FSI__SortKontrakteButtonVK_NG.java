package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

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
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public class FSI__SortKontrakteButtonVK_NG extends MyE2_Grid
{
	public static int    SORTSTATUS_UNSORTED = 	0;
	public static int    SORTSTATUS_UP =		1;
	public static int    SORTSTATUS_DOWN = 		2;
	
	
	private MyE2_Label 							labelSortUp = new MyE2_Label(E2_ResourceIcon.get_RI("sortup.png"));
	private MyE2_Label 							labelSortDown = new MyE2_Label(E2_ResourceIcon.get_RI("sortdown.png"));
	private MyE2_Label 							labelSortEmpty = new MyE2_Label("");
	
	private MyE2_Button  						oButtonIntern = null;
	

	private int                       			iActualSortStatus = FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UNSORTED;
	
	private String                              cSortKenner = null;


	private INFO_BLOCK_Kontrakte_NG                ownInfoBlockKontrakte = null;


	/**
	 * 
	 * @param iColumnNumberInVisibleList
	 * @param GridWithSearchResultbuttons
	 * @param oSpalteDef
	 * @throws myException
	 */
	public FSI__SortKontrakteButtonVK_NG( String cText, String SortKenner, boolean bLeft, INFO_BLOCK_Kontrakte_NG own_InfoBlockKontrakte) throws myException
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.cSortKenner = SortKenner;
		this.ownInfoBlockKontrakte = own_InfoBlockKontrakte;
		
		this.oButtonIntern = new MyE2_Button(new MyE2_String(cText));
		this.oButtonIntern.setStyle(new sortButtonStyle());
		this.oButtonIntern.set_bEnabled_For_Edit(S.isFull(this.cSortKenner));                     //damit der button gleich aussieht
		
		this.setLayoutData(bLeft?MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_4_2_4_0):MyE2_Grid.LAYOUT_RIGHT_TOP(E2_INSETS.I_4_2_4_0));
		
		this.add(this.oButtonIntern);
		this.add(this.labelSortEmpty);
		if (S.isFull(this.cSortKenner))
		{
			this.oButtonIntern.add_oActionAgent(new actionSortList(this.cSortKenner));
		}
		
		
		if (S.isFull(this.cSortKenner) && this.cSortKenner.equals(S.NN(this.ownInfoBlockKontrakte.get_ActualSortKennerVK())))
		{
			//dieser button muss in den status sort versetzt werden
			if (this.ownInfoBlockKontrakte.get_bActualSort_UP_VK())
			{
				this.set_StatusUP();
			}
			else
			{
				this.set_StatusDOWN();
			}
			this.BaueSortButtonNeu();
		}

	}


	public String get_cSortKenner()
	{
		return cSortKenner;
	}

	
	
	private void SetToNextSortStatus()
	{
		int iOldSortStatus = this.iActualSortStatus;
		
		if (iOldSortStatus==FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UNSORTED)
		{
			this.iActualSortStatus = FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UP;
		}
		else if (iOldSortStatus==FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UP)
		{
			this.iActualSortStatus = FSI__SortKontrakteButtonVK_NG.SORTSTATUS_DOWN;
		}
		else
		{
			this.iActualSortStatus = FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UP;     //status UP springt nach DOWN, nicht nach unsorted
		}
	}
	
	
	public void set_StatusUP()
	{
		this.iActualSortStatus = FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UP;
	}
	public void set_StatusDOWN()
	{
		this.iActualSortStatus = FSI__SortKontrakteButtonVK_NG.SORTSTATUS_DOWN;
	}
	
	
	public boolean get_bIsUnsorted()
	{
		return this.iActualSortStatus==FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UNSORTED;
	}
	
	public boolean get_bIsSortedUP()
	{
		return this.iActualSortStatus==FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UP;
	}
	
	
	public void Reset()
	{
		this.iActualSortStatus = FSI__SortKontrakteButtonVK_NG.SORTSTATUS_UNSORTED;
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
			this.setProperty(AbstractButton.PROPERTY_FONT, new E2_FontItalic(-2));
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
		private String cSortKenner = null;
		
		public actionSortList(String  SortKenner)
		{
			super();
			this.cSortKenner = SortKenner;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FSI__SortKontrakteButtonVK_NG oThis = FSI__SortKontrakteButtonVK_NG.this;

			oThis.SetToNextSortStatus();

			//jetzt den status abspeichern
			FSI__SortKontrakteButtonVK_SaveStatus_NG oSaver = new FSI__SortKontrakteButtonVK_SaveStatus_NG();
			if (oSaver.get_bCanSaveStatus())
			{
				oSaver.saveSelectedStatus(oThis);
			}
			
			oThis.ownInfoBlockKontrakte.set_ActualSortKennerVK(this.cSortKenner);
			oThis.ownInfoBlockKontrakte.set_bActualSort_UP_VK(oThis.get_bIsSortedUP());
			oThis.ownInfoBlockKontrakte.__aufbau2();

		}

	}
	
	
	
}
