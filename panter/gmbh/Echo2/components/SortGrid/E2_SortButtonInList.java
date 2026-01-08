package panter.gmbh.Echo2.components.SortGrid;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.MutableStyle;
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


public class E2_SortButtonInList extends MyE2_Grid
{
	
	private MyE2_Label 							labelSortUp = new MyE2_Label(E2_ResourceIcon.get_RI("sortup.png"));
	private MyE2_Label 							labelSortDown = new MyE2_Label(E2_ResourceIcon.get_RI("sortdown.png"));
	private MyE2_Label 							labelSortEmpty = new MyE2_Label("");
	
	private int									ColumnNumber	= -1;

	private MyE2_Button  						oButtonIntern = null;
	
	private E2_SortGrid  					  	ownSortGrid = null;

	private String                     			cActualSortStatus = E2_SortGrid.SORTED_NOT;
	
	private boolean    							bLineWrap = true;
	private boolean    							bSortable = true;
	
	private MyE2_String   						cButtonText = null;

	private MutableStyle                      	oStyleButton = E2_SortButtonInList.SORT_BUTTON_STYLE_STD();

	private String   							MODULE_KENNER_TO_STORE = null;  
	
	/**
	 * 
	 * @param iColumnNumberInVisibleList
	 * @param SortGrid
	 * @param ButtonText (can be null)
	 * @param LineWrap (can be null)
	 * @param Sortable (can be null)
	 * @param StyleButton (can be null)
	 * @param ModuleKenner4StoreSortstatus   MUST BE SET !!!!
	 * @throws myException
	 */
	public E2_SortButtonInList(		int 		   iColumnNumberInVisibleList, 	
									E2_SortGrid    SortGrid,
									MyE2_String    ButtonText,
									Boolean        LineWrap,
									Boolean        Sortable,
									MutableStyle   StyleButton,
									String         ModuleKenner4StoreSortstatus) throws myException
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.__baue(iColumnNumberInVisibleList, SortGrid, ButtonText, LineWrap, Sortable, StyleButton, ModuleKenner4StoreSortstatus, null, null);
	}

	
	
	/**
	 * 
	 * @param iColumnNumberInVisibleList
	 * @param SortGrid
	 * @param ButtonText (can be null)
	 * @param LineWrap (can be null)
	 * @param Sortable (can be null)
	 * @param StyleButton (can be null)
	 * @param ModuleKenner4StoreSortstatus   MUST BE SET !!!!
	 * @throws myException
	 */
	public E2_SortButtonInList(		int 		   iColumnNumberInVisibleList, 	
									E2_SortGrid    SortGrid,
									MyE2_String    ButtonText,
									Boolean        LineWrap,
									Boolean        Sortable,
									MutableStyle   StyleButton,
									String         ModuleKenner4StoreSortstatus,
									Integer        iLastSortRow,
									String         cLastSortStatus) throws myException
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.__baue(iColumnNumberInVisibleList, SortGrid, ButtonText, LineWrap, Sortable, StyleButton, ModuleKenner4StoreSortstatus, iLastSortRow, cLastSortStatus);
	}

	
	
	
	private void __baue(		int 		   iColumnNumberInVisibleList, 	
								E2_SortGrid    SortGrid,
								MyE2_String    ButtonText,
								Boolean        LineWrap,
								Boolean        Sortable,
								MutableStyle   StyleButton,
								String         ModuleKenner4StoreSortstatus,
								Integer        iLastSortRow,
								String         cLastSortStatus) throws myException
	{
		
		this.ColumnNumber = 	iColumnNumberInVisibleList;
		this.ownSortGrid = 		SortGrid;
		
		this.MODULE_KENNER_TO_STORE = ModuleKenner4StoreSortstatus;
		
		if (S.isEmpty(this.MODULE_KENNER_TO_STORE))
		{
			throw new myException(this,"Empty modulkenner4store is not allowed");
		}
		
		if (ButtonText != null)
		{
			this.cButtonText = ButtonText;
		}
		else
		{
			this.cButtonText = new MyE2_String("Spalte ",true,""+(iColumnNumberInVisibleList+1),false);
		}
		
		if (LineWrap!=null)
		{
			this.bLineWrap=LineWrap.booleanValue();
		}
		
		if (Sortable!=null)
		{
			this.bSortable=Sortable.booleanValue();
		}


		if (StyleButton != null)
		{
			this.oStyleButton = StyleButton;
		}
		
		this.oButtonIntern = new MyE2_Button(this.cButtonText);
		this.oButtonIntern.setLineWrap(this.bLineWrap);
		this.oButtonIntern.setStyle(this.oStyleButton);
		this.oButtonIntern.set_bEnabled_For_Edit(this.bSortable);                     //damit der button gleich aussieht
		
		this.BaueSortButton();
		
		this.oButtonIntern.add_oActionAgent(new actionSortList(this.ColumnNumber));

		if (iLastSortRow != null && S.isFull(cLastSortStatus))
		{
			if (iLastSortRow==this.ColumnNumber)
			{
				if (cLastSortStatus.equals(E2_SortGrid.SORTED_UP))
				{
					this.set_StatusUP();
				}
				else
				{
					this.set_StatusDOWN();
				}
			}

		}
		
	}
	
	
	
	private void SetToNextSortStatus()
	{
		String  cOldSortStatus = this.cActualSortStatus;
		
		if (cOldSortStatus.equals(E2_SortGrid.SORTED_NOT))
		{
			this.cActualSortStatus = E2_SortGrid.SORTED_UP;
		}
		else if (cOldSortStatus.equals(E2_SortGrid.SORTED_UP))
		{
			this.cActualSortStatus = E2_SortGrid.SORTED_DOWN;
		}
		else
		{
			this.cActualSortStatus = E2_SortGrid.SORTED_UP;     //status down springt nach up, nicht nach unsorted
		}
		
		this.ownSortGrid.set_iLastSortedColumn(ColumnNumber);
		this.ownSortGrid.set_cLastSortStatus(this.cActualSortStatus);
	}
	
	
	public void set_StatusUP()
	{
		this.cActualSortStatus = E2_SortGrid.SORTED_UP;
		this.ownSortGrid.set_iLastSortedColumn(ColumnNumber);
		this.ownSortGrid.set_cLastSortStatus(this.cActualSortStatus);

		this.BaueSortButton();
	}
	public void set_StatusDOWN()
	{
		this.cActualSortStatus = E2_SortGrid.SORTED_DOWN;
		this.ownSortGrid.set_iLastSortedColumn(ColumnNumber);
		this.ownSortGrid.set_cLastSortStatus(this.cActualSortStatus);

		this.BaueSortButton();
	}
	
	
	public boolean get_bIsUnsorted()
	{
		return this.cActualSortStatus.equals(E2_SortGrid.SORTED_NOT);
	}
	
	public boolean get_bIsSortedUP()
	{
		return this.cActualSortStatus.equals(E2_SortGrid.SORTED_UP);
	}
	
	public boolean get_bIsSortedDOWN()
	{
		return this.cActualSortStatus.equals(E2_SortGrid.SORTED_DOWN);
	}
	
	public void Reset()
	{
		this.cActualSortStatus = E2_SortGrid.SORTED_NOT;
		this.BaueSortButton();
	}
	
	private void BaueSortButton()
	{
		this.removeAll();
		this.add(this.oButtonIntern, E2_INSETS.I_0_0_2_0);
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

	
	public static E2_MutableStyle  SORT_BUTTON_STYLE_STD()
	{
		E2_MutableStyle oRueck = new E2_MutableStyle();
		oRueck.setProperty(AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		oRueck.setProperty(AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
		oRueck.setProperty(AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		oRueck.setProperty(AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE);
		oRueck.setProperty(AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		oRueck.setProperty(AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oRueck.setProperty(AbstractButton.PROPERTY_FONT, new E2_FontItalic());
		oRueck.setProperty(AbstractButton.PROPERTY_LINE_WRAP, new Boolean(false));
		return oRueck;
	}
	

	public static E2_MutableStyle  SORT_BUTTON_STYLE_SMALL()
	{
		E2_MutableStyle oRueck = new E2_MutableStyle();
		oRueck.setProperty(AbstractButton.PROPERTY_PRESSED_ENABLED, new Boolean(true));
		oRueck.setProperty(AbstractButton.PROPERTY_PRESSED_BORDER, new Border(1, new E2_ColorLLight(), Border.STYLE_SOLID));
		oRueck.setProperty(AbstractButton.PROPERTY_BORDER, new Border(1, new E2_ColorDDDark(), Border.STYLE_SOLID));
		oRueck.setProperty(AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.FALSE);
		oRueck.setProperty(AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);
		oRueck.setProperty(AbstractButton.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
		oRueck.setProperty(AbstractButton.PROPERTY_FONT, new E2_FontItalic(-2));
		oRueck.setProperty(AbstractButton.PROPERTY_LINE_WRAP, new Boolean(false));
		return oRueck;
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
			E2_SortButtonInList oThis = E2_SortButtonInList.this;

			if (oThis.bSortable)
			{
				oThis.SetToNextSortStatus();
				
				oThis.ownSortGrid.SortListe(oThis.ColumnNumber, oThis.get_bIsSortedUP());
				oThis.ownSortGrid.BaueListeAuf();
			
				//jetzt den status abspeichern
				E2_SortGrid_USERSETTING_LastSort oSaver = new E2_SortGrid_USERSETTING_LastSort(oThis, oThis.ownSortGrid);
	
				if (oSaver.get_bCanSaveStatus())
				{
					oSaver.saveSelectedStatus(E2_SortButtonInList.this.MODULE_KENNER_TO_STORE);
				}
			}
		}

	}
	


	public E2_SortGrid get_ownSortGrid()
	{
		return ownSortGrid;
	}


	public int get_iColumnNumber()
	{
		return ColumnNumber;
	}


	public boolean get_bLineWrap()
	{
		return bLineWrap;
	}


	public boolean get_bSortable()
	{
		return bSortable;
	}


	public void set_bLineWrap(boolean bLineWrap)
	{
		this.bLineWrap = bLineWrap;
	}


	public void set_bSortable(boolean bSortable)
	{
		this.bSortable = bSortable;
	}

	
	
}
