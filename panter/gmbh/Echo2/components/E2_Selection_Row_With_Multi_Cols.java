package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingPreselection_MultiSelectRow;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * komponente zur selektions-staffette, in N angezeigten Spalten werden jeweils max N vorherige selectionColumns angezeigt
 * wird als abtract definiert, damit man gezwungen ist, eine ableitung zur erzeugen, was wiederum zur moeglichkeit fuehrt,
 * den status speicherbar zu machen
 */
public abstract class E2_Selection_Row_With_Multi_Cols extends MyE2_Row
{
	public static E2_ResourceIcon ICON_FOR_SEARCHBUTTON = E2_ResourceIcon.get_RI("suchkaskade.png");
	
	
	private Vector<SelectBlockGrid> vSelectCols = new Vector<SelectBlockGrid>(); 
	private String    			 cWertFuerAusstieg = null;
	private XX_ActionAgent       oActionAgentFuerLastSelection = null;

	private    	int  			 iSpalteHoeheInPixel = 400;
	
	private boolean 			bShowSaveButton = false;

	
	private boolean    			bAutoSizeHight = true;
	

	/**
	 * 
	 * @param style
	 * @param SpalteHoeheInPixel
	 * @param ShowSaveButton
	 */
	public E2_Selection_Row_With_Multi_Cols(MutableStyle style,int SpalteHoeheInPixel, boolean ShowSaveButton)
	{
		super(style);
		
		this.iSpalteHoeheInPixel = 		SpalteHoeheInPixel;
		this.bShowSaveButton = 			ShowSaveButton;
	}

	public void set_ActionAgentFuerLastSelection(XX_ActionAgent actionAgentFuerLastSelection)
	{
		oActionAgentFuerLastSelection = actionAgentFuerLastSelection;
	}

	
	public void START(String cStartWert, boolean lookForStoredValues)  throws myException
	{
		if (!lookForStoredValues)
		{
			this.vSelectCols.get(0).init_Column(cStartWert);
		}
		else
		{
			boolean bLookWasDone = false;
			
			/*
			 * nachsehen, ob eine startreihenfolge gepeichert wurde
			 */
			try
			{
				if (new E2_UserSettingPreselection_MultiSelectRow().prepareSelector(this))
				{
					bLookWasDone = true;
				}
			} 
			catch (myException e)
			{
				e.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler beim Laden der Selektionsvorgaben !")));
				bLookWasDone = false;
			}
			
			if (!bLookWasDone)
			{
				this.vSelectCols.get(0).init_Column(cStartWert);
			}
		}
	}
	
	public void START(Vector<String> vValues)  throws myException
	{
		for (int i=0;i<vValues.size();i++)
		{
			this.vSelectCols.get(i).init_Column(vValues.get(i));
			//Falls eine preselection eine leere spalte generiert, dann abbruch !
			if (this.vSelectCols.get(i).get_iActualButtonCount()==0)
			{
				break;
			}
		}
	}
	
	
	
	public Vector<SelectBlockGrid> get_vSelectCols()
	{
		return this.vSelectCols;
	}
	
	
	/**
	 * 
	 * @param queryStringMitPlatzhalter
	 * @param StringTitel
	 * @param iSpaltenBreite (pixelbreite fuer den auswahlblock)
	 * @param iGridSpaltenZahl (std. 1)
	 * @param ButtonBuilder (wenn null, wird ein standardbuttonbuilder gezogen)
	 */
	public void add_QueryStringForSteps(String queryStringMitPlatzhalter,MyE2_String StringTitel, int iSpaltenBreite, int iGridSpaltenZahl, XX_ButtonBuilder ButtonBuilder) throws myException
	{
		
		//die erste suche darf ohne #WERT# kommen
		if (this.vSelectCols.size()>0)
		{
			if (queryStringMitPlatzhalter.indexOf("#WERT")==-1)
			{
				throw new myException(this,"Definition MUST contain #WERT1 ... n !");
			}
		}

		SelectBlockGrid oSelCol = new SelectBlockGrid(queryStringMitPlatzhalter,StringTitel,iSpaltenBreite, iGridSpaltenZahl, ButtonBuilder);
		oSelCol.set_iNumberInVector(this.vSelectCols.size());
		this.vSelectCols.add(oSelCol);
	}
	

	
	
	public String get_cWertFuerAusstieg()
	{
		return cWertFuerAusstieg;
	}
	

	public void set_cWertFuerAusstieg(String cWert)
	{
		this.cWertFuerAusstieg=cWert;
	}
	

	
	
	
	public class SelectBlockGrid extends MyE2_Grid
	{
		private 	String 					cQueryStringMitPlatzhalter = null;
		
		private     String 					cInitValue = null;
		
		private     MyE2_String	 			cStringTitel = null;
		private     SelectBlockGrid  		oColumnLeft = null;
		private     SelectBlockGrid  		oColumnRight = null;
		
		private     MyE2_Column  			oColDrumRum = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		private     MyE2_ContainerEx        oContainerEx = new MyE2_ContainerEx();
		



		private 	Color 					oColorButtonUnselected = new E2_ColorBase();
		private 	Color 					oColorButtonSelected = new E2_ColorDDark();

		private     XX_ButtonBuilder     	oButtonBuilder = null;
		
		private    	int 					iNumberInVector = 0;          //welche position hat das element im vector
		private     int       				iActualButtonCount = 0;       //wieviele buttons gibt es gerade 
		
		private    	int  					iSpalteBreiteInPixel = 400;
		
		private  ButtonToSaveActualSelectionValues   oButtonToSaveStatus = new ButtonToSaveActualSelectionValues();

		/**
		 * 
		 * @param queryStringMitPlatzhalter
		 * @param StringTitel
		 * @param iSpaltenBreite (pixelbreite fuer den auswahlblock)
		 * @param iGridSpaltenZahl (std. 1)
		 * @param ButtonBuilder (wenn null, wird ein standardbuttonbuilder gezogen)
		 */
		public SelectBlockGrid(String queryStringMitPlatzhalter,MyE2_String StringTitel, int iSpaltenBreite, int iGridSpaltenZahl, XX_ButtonBuilder ButtonBuilder)
		{
			super(iGridSpaltenZahl,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());

//			this.setBorder(new Border(1,Color.RED,Border.STYLE_SOLID));
			this.setWidth(new Extent(98,Extent.PERCENT));                      //muss den ganzen platz in der ContainerEX benutzen
			
			this.oButtonBuilder = ButtonBuilder;

			if (this.oButtonBuilder==null)
			{
				this.oButtonBuilder = new standardButtonBuilder();
				this.setSize(1);             //dann hat das Grid immer nur eine spalte, da nur ein button automatisch erzeugt wird
			}
			
			
			this.iSpalteBreiteInPixel = iSpaltenBreite;
			
			E2_Selection_Row_With_Multi_Cols oThis = E2_Selection_Row_With_Multi_Cols.this;
			
			this.cQueryStringMitPlatzhalter = queryStringMitPlatzhalter;
			this.cStringTitel = StringTitel;
			
			this.oContainerEx.setWidth(new Extent(this.iSpalteBreiteInPixel));
			this.oContainerEx.setHeight(new Extent(oThis.iSpalteHoeheInPixel));
			
			this.oContainerEx.add(this);
			
//			this.oContainerEx.setBackground(Color.YELLOW);
			
			MyE2_Row oRowTitel = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
			oRowTitel.add(new MyE2_Label(this.cStringTitel,MyE2_Label.STYLE_SMALL_BOLD_ITALIC()));
			if (oThis.bShowSaveButton)
			{
				oRowTitel.add(this.oButtonToSaveStatus, E2_INSETS.I_10_0_0_0);
			}
			
			this.oColDrumRum.add(oRowTitel,E2_INSETS.I_4_4_4_4);
			this.oColDrumRum.add(this.oContainerEx,E2_INSETS.I_1_1_1_1);
			
			//wenn es der erste ist, dann ist der linke = null;
			if (oThis.vSelectCols.size()>0)
			{
				this.oColumnLeft=oThis.vSelectCols.get(oThis.vSelectCols.size()-1);
				oThis.vSelectCols.get(oThis.vSelectCols.size()-1).set_oColumnRight(this);
			}
		}

		public MyE2_ContainerEx get_oContainerEx() 
		{
			return oContainerEx;
		}
		

		public void clear()
		{
			this.removeAll();
			this.cInitValue=null;
			this.iActualButtonCount=0;
		}
		
		/**
		 * 
		 * @param cWert
		 * Baut die Column und die Buttons neu auf
		 * @throws myException
		 */
		public void init_Column(String cWert) throws myException
		{
			E2_Selection_Row_With_Multi_Cols oThis = E2_Selection_Row_With_Multi_Cols.this;
			
			this.cInitValue = cWert; 

			String cQueryMitValues = this.cQueryStringMitPlatzhalter;
			
			//jetzt alle vorgaengerwerte austauschen #WERT4#,#WERT3#,#WERT2#,#WERT1# und #WERT0# austauschen, wenn der platzhalter vorhanden ist
			
			for (int i=this.iNumberInVector;i>=0;i--)
			{
				cQueryMitValues = bibALL.ReplaceTeilString(cQueryMitValues, "#WERT"+i+"#", oThis.get_vSelectCols().get(i).get_cInitValue());
			}
			
			String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cQueryMitValues, "");
			
			if (cErgebnis == null)
				throw new myException(this,"Error building Row: ("+bibALL.ReplaceTeilString(cQueryMitValues, "#WERT#", S.NN(cWert))+")");
			
			//jetzt den Vector mit den Buttons aufbauen
			this.removeAll();
			
			this.iActualButtonCount = cErgebnis.length;
			
			for (int i=0;i<cErgebnis.length;i++)
			{
				this.oButtonBuilder.build_Buttons_In_Grid(E2_Selection_Row_With_Multi_Cols.this, cErgebnis[i], this);
			}
			
			//die selectionRow neu aufbauen, je nach situation
			oThis.removeAll();
			if (this.get_iNumberInVector()==0)
			{
				oThis.add(this.get_oColDrumRum());
			}
			else if (this.get_iNumberInVector()==1)
			{
				oThis.add(this.get_oColumnLeft().get_oColDrumRum());
				oThis.add(this.get_oColDrumRum());
			}
			else if (this.get_iNumberInVector()>1)
			{
				oThis.add(this.get_oColumnLeft().get_oColumnLeft().get_oColDrumRum());
				oThis.add(this.get_oColumnLeft().get_oColDrumRum());
				oThis.add(this.get_oColDrumRum());
			}

			//alle folgenden SelectColumn's leermachen
			
			for (int i=this.get_iNumberInVector()+1;i<oThis.get_vSelectCols().size();i++)
			{
				oThis.get_vSelectCols().get(i).clear();
			}
			
			
			//jetzt den button in der vorgaengerliste markieren
			if (this.iNumberInVector>0)
			{
				SelectBlockGrid oSelVorgaenger = this.oColumnLeft;
				
				//alle buttons auf normalen hintergrund setzen
				Component[]  arrayComponents = oSelVorgaenger.getComponents();
				for (int i=0;i<arrayComponents.length;i++)
				{
					if (arrayComponents[i] instanceof MyE2_Button)
					{
						MyE2_Button oButt = (MyE2_Button)arrayComponents[i];
						
						if (oButt.EXT().get_C_MERKMAL().equals(this.cInitValue))
						{
							((MyE2_Button)arrayComponents[i]).setBackground(SelectBlockGrid.this.oColorButtonSelected);
						}
						else
						{
							((MyE2_Button)arrayComponents[i]).setBackground(SelectBlockGrid.this.oColorButtonUnselected);
						}
					}
				}
			}
		}
		
		
		public SelectBlockGrid get_oColumnLeft()
		{
			return oColumnLeft;
		}


		public void set_oColumnLeft(SelectBlockGrid columnLeft)
		{
			oColumnLeft = columnLeft;
		}


		public SelectBlockGrid get_oColumnRight()
		{
			return oColumnRight;
		}


		public void set_oColumnRight(SelectBlockGrid columnRight)
		{
			oColumnRight = columnRight;
		}
		
		public boolean get_bIsFirstCol()
		{
			return (this.oColumnLeft==null);
		}

		
		public boolean get_bIsSecondCol()
		{
			boolean bRueck = false;
			if (this.oColumnLeft != null && this.oColumnLeft.get_bIsFirstCol())
			{
				bRueck = true;
			}
			
			return bRueck;
		}

		
		public boolean get_bIsLastCol()
		{
			return (this.oColumnRight==null);
		}
		
	

		public MyE2_Column get_oColDrumRum()
		{
			return oColDrumRum;
		}
		
		


		public String get_cInitValue()
		{
			return cInitValue;
		}

		public int get_iNumberInVector()
		{
			return this.iNumberInVector;
		}

		public void set_iNumberInVector(int numberInVetor)
		{
			this.iNumberInVector = numberInVetor;
		}


		public int get_iActualButtonCount()
		{
			return iActualButtonCount;
		}
		
		
		
		/*
		 * speicherbuttons, um den status bis zu dieser spalte vorzudefinieren
		 */
		private class ButtonToSaveActualSelectionValues extends MyE2_Button
		{

			public ButtonToSaveActualSelectionValues()
			{
				super(E2_ResourceIcon.get_RI("save_status.png"));
				this.add_oActionAgent(new actionSaveStatus());
				this.setToolTipText(new MyE2_String("Auswahl bis zu dieser Spalte speichern !").CTrans());
			}
			
		}

		
		
		private class actionSaveStatus extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				Vector<String> vValuesToThisRow = new Vector<String>();
				
				SelectBlockGrid 						oThis = SelectBlockGrid.this;    //column, in der wir uns befinden
				E2_Selection_Row_With_Multi_Cols 	OTHIS = E2_Selection_Row_With_Multi_Cols.this;   //der ganze selector
				
				for (int i=0;i<OTHIS.get_vSelectCols().size();i++)
				{
					vValuesToThisRow.add(OTHIS.get_vSelectCols().get(i).get_cInitValue());
					if (OTHIS.get_vSelectCols().get(i)==oThis)
					{
						//dann wird hier geendet
						break;
					}
				}
				
				if (new E2_UserSettingPreselection_MultiSelectRow().storeSelector(OTHIS,vValuesToThisRow)>0)
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Selektionskette gespeichert: ",true,bibALL.Concatenate(vValuesToThisRow, " - ", "-"),false)));
				}
				
			}
			
		}


		
	}


	
	/*
	 * statisch abstracte klasse, die benutzt werden kann, um die buttons / komponenten fuer eine 
	 * auswahlzeile zu bauen
	 */
	public static abstract class XX_ButtonBuilder
	{
		public abstract Vector<MyE2_Button> build_Buttons_In_Grid(E2_Selection_Row_With_Multi_Cols oMultiSel, String[] ergebnisZeile, SelectBlockGrid ownSelectGrid) throws myException;
	}

	
	public class standardButtonBuilder extends XX_ButtonBuilder
	{
		private 	Color 					oColorButtonUnselected = new E2_ColorBase();

		@Override
		public Vector<MyE2_Button> build_Buttons_In_Grid(E2_Selection_Row_With_Multi_Cols oMultiSel, String[] ergebnisZeile, SelectBlockGrid ownSelectGrid)	throws myException
		{
			String cNextValue = ergebnisZeile[0];
			String cButtonText = ergebnisZeile.length>1?ergebnisZeile[1]:cNextValue;
			String cButtonHelpText = ergebnisZeile.length>2?ergebnisZeile[2]:cButtonText;
			
			MyE2_Button oBut = new MyE2_Button(new MyE2_String(cButtonText,false));
			oBut.setBackground(oColorButtonUnselected);
			oBut.EXT().set_C_MERKMAL(cNextValue);
			oBut.setToolTipText(cButtonHelpText+" ("+cNextValue+")");
			oBut.EXT().set_O_PLACE_FOR_EVERYTHING(ownSelectGrid);
			oBut.add_oActionAgent(new ActionToSelectNextColumn(oMultiSel));
			ownSelectGrid.add(oBut, E2_INSETS.I_1_1_1_1);
			
			Vector<MyE2_Button> vRueck = new Vector<MyE2_Button>();
			vRueck.add(oBut);

			
			return vRueck;
		}
	}
	

	
	/**
	 * 
	 * @author martin
	 * ActionAgent fuer die Selektionsbuttons in den grids, die den jeweils rechts liegenden Block neu fuellen oder
	 * aussteigen (wenn ganz rechts)
	 *
	 */
	public static class ActionToSelectNextColumn extends XX_ActionAgent
	{
		private E2_Selection_Row_With_Multi_Cols  oMultiSelColumns = null;
		
		
		public ActionToSelectNextColumn(E2_Selection_Row_With_Multi_Cols multiSelColumns)
		{
			super();
			this.oMultiSelColumns = multiSelColumns;
		}


		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
				
			//zuerst den ausstiegspunkt auf null setzen
			this.oMultiSelColumns.set_cWertFuerAusstieg(null);
			
			MyE2_Button oButton = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
			
			SelectBlockGrid oSelCol = (SelectBlockGrid)oButton.EXT().get_O_PLACE_FOR_EVERYTHING();
			
			String cValueForNextCol = oButton.EXT().get_C_MERKMAL();
			
			if (oSelCol.get_bIsLastCol())
			{
				this.oMultiSelColumns.set_cWertFuerAusstieg(cValueForNextCol);
				
				//dann den actionAgent starten
				if (this.oMultiSelColumns.oActionAgentFuerLastSelection != null)
				{
					this.oMultiSelColumns.oActionAgentFuerLastSelection.executeAgentCode(execInfo);
				}
			}
			else
			{
				oSelCol.get_oColumnRight().init_Column(cValueForNextCol);
			}
		}
		
	}

	
	
	public boolean 				get_bAutoHeight() 
	{
		return bAutoSizeHight;
	}

	public void set_bAutoHeight(boolean bAutoSizeHight) 
	{
		this.bAutoSizeHight = bAutoSizeHight;
	}

	
	public void set_iHeight_in_Pixel(int iHeight)
	{
		this.iSpalteHoeheInPixel = iHeight;
		for (int i=0;i<this.vSelectCols.size();i++)
		{
			this.vSelectCols.get(i).get_oContainerEx().setHeight(new Extent(this.iSpalteHoeheInPixel));
		}
		
	}
	
	
}
