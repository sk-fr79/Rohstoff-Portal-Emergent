package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_SaveSortButton_IN_SearchResultWindow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * ein Grid fuer die Buttons in den Komponenten MyE2_searchField und
 * MyE2_DB_SearchField, Uebergeben wird ein Vector mit Button-Array, pro
 * ergebniszeile ein array an buttons
 */
public class E2_GridWithSearchResultbuttons extends MyE2_Grid
{

	private Vector<XX_Button4SearchResultList[]> 	vButtons						= null;
	public Vector<XX_Button4SearchResultList[]> 	get_vButtons()
	{
		return vButtons;
	}

	private Vector<XX_ActionAgent>					vStandardAction4ClickListButton	= new Vector<XX_ActionAgent>();
	private XX_FormaterForSearchResultButtons		oFormater4Buttons				= null;

	private Vector<E2_SortButtonsInList>  			vSortButtons 					= null;  

	/*
	 * hashmap mit hashkey=integer(Spalte)  WICHTIG! die hmDefSortSpalte korrelliert mit den Abfragespalten, 
	 *                                               die Anzeige beginnt ab der Hash 1 (in der 0 steht die ID der Abfrage) 
	 */
	private HashMap<Integer,DefSpalteLayout_And_Else>  	hmDefinition4Sort = 	new HashMap<Integer, DefSpalteLayout_And_Else>();
	private Boolean                    				bResultListIsSortable = new Boolean(true);


	private Object                                  oSearchField = null;

	/**
	 * 
	 * @param v_Buttons - Vector aus array MyE2_Button[]
	 * @param oStyle  (null moeglich)
	 * @param o_StandardAction4ClickListButton - in der Regel der ActionAgent der das Merkmal mit der ID dem suchfeld uebergibt
	 * @param o_Formater4Buttons
	 * @param hm_Definition4Sort
	 * @param b_ResultListIsSortable
	 * @param objSearchField                 - das benutzte suchfeld
	 * @throws myException
	 */
	public E2_GridWithSearchResultbuttons(	Vector<XX_Button4SearchResultList[]> v_Buttons, 
											MutableStyle 						oStyle, 
											XX_ActionAgent 						o_StandardAction4ClickListButton,
											XX_FormaterForSearchResultButtons 	o_Formater4Buttons,
											HashMap<Integer,DefSpalteLayout_And_Else> 	hm_Definition4Sort,
											Boolean                    			b_ResultListIsSortable,
											Object  							objSearchField) throws myException
	{
		super(v_Buttons.get(0).length, oStyle == null ? MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS() : oStyle);

		this.vButtons = v_Buttons;
		
		this.oSearchField = objSearchField;
		
		if (o_StandardAction4ClickListButton != null)
		{
			this.vStandardAction4ClickListButton.add(o_StandardAction4ClickListButton);
		}

		this.oFormater4Buttons = o_Formater4Buttons;
		this.hmDefinition4Sort=hm_Definition4Sort;
		this.bResultListIsSortable = b_ResultListIsSortable;

		
		//die hmDefinition4Sort MUSS immer einen Member mehr haben als dargestellte spalten
		if (this.hmDefinition4Sort!=null && this.hmDefinition4Sort.size()>0)
		{
			if (this.vButtons.get(0).length != (this.hmDefinition4Sort.size()-1))
			{
				throw new myException(this,"Nummer of Columns MUST be the same as number of hmDefinitions4Sort (when this is not empty) !!");
			}
		}

		
		
		//sortbuttons einmal aufbauen, jeder sortbutton hat ein DefSpalteLayout_And_Else-objekt
		this.vSortButtons = new Vector<E2_SortButtonsInList>();
		for (int i = 0; i < this.vButtons.get(0).length; i++)
		{
			DefSpalteLayout_And_Else oDefSpalte = new 	DefSpalteLayout_And_Else(	i,
																					true, 
																					new MyE2_String("Spalte ",true,""+(i+1),false), 
																					"SPALTE"+(i+1));
			if (this.hmDefinition4Sort!=null && this.hmDefinition4Sort.containsKey(new Integer(i+1)))
			{
				oDefSpalte = this.hmDefinition4Sort.get(new Integer(i+1));
			}
			this.vSortButtons.add(new E2_SortButtonsInList(i,  this, oDefSpalte));
		}
		
		
//		//falls eine hmDefinition4Sort vorhanden ist, die XX_Button4SearchResultList mit den zusatzinfos fuettern,
//		//damit in der formatierung dann mehr Info vorhanden ist
//		if (this.hmDefinition4Sort!=null && this.hmDefinition4Sort.size()>0)
//		{
//			for (int i=0;i<this.vButtons.size();i++)
//			{
//				for (int k=0;k<this.vButtons.get(i).length;k++)
//				{
//					this.vButtons.get(i)[k].set_SpaltenName(this.hmDefinition4Sort.get(new Integer(k+1)).get_DBNameOfColumn());
//				}
//			}
//		}
		
		this.__baue_ListeAuf();
		
		
		// ------ jetzt den status laden
		E2_UserSetting_SaveSortButton_IN_SearchResultWindow oSaver = new E2_UserSetting_SaveSortButton_IN_SearchResultWindow(null, this.get_oSearchField());
		if (oSaver.get_bCanSaveStatus())
		{
			String[] Result = oSaver.get_Status_aus_Database();
			
			if (Result!=null)
			{
				int 		iSpalte = new Integer(Result[0]);
				String 		cUpDown = Result[1];
				
				if (iSpalte<this.vSortButtons.size())
				{
					if (cUpDown.equals("UP")) 
					{
						this.vSortButtons.get(iSpalte).set_StatusUP();
					}
					else
					{
						this.vSortButtons.get(iSpalte).set_StatusUP();
					}
					this.vSortButtons.get(iSpalte).do_Sort(iSpalte);
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Der zurückgeladene Sortierstatus kann nicht gesetzt werden (Spaltenzahl stimmt nicht !)"));
				}
			}
		}
		// ------  status laden ende 

		

	}

	public void __baue_ListeAuf() throws myException
	{
		this.removeAll();
		
		if (this.bResultListIsSortable)
		{
			for (int i = 0; i < this.vButtons.get(0).length; i++)
			{
				this.add(this.vSortButtons.get(i),this.vSortButtons.get(i).get_oSpalteDef().get_glTitelButtons());
			}
		}


		for (int i = 0; i < this.vButtons.size(); i++)
		{
			if (this.oFormater4Buttons != null)
			{
				this.oFormater4Buttons.RESET_ROW(vButtons.get(i));
			}

			
			String cToolTipText = new MyE2_String("Ergebnis für das Suchfeld (ID): ",true,S.NN(this.vButtons.get(i)[0].EXT().get_C_MERKMAL()),false).CTrans();
			
			for (int k = 0; k < this.vButtons.get(i).length; k++)
			{
				
				//zuerst DefSpalteLayout_And_Else info-objekt aus den sortbuttons holen und jedem button das seine uebergeben
				DefSpalteLayout_And_Else  oDefSpalte = this.vSortButtons.get(k).get_oSpalteDef();
				this.vButtons.get(i)[k].set_DefSpalteLayout(oDefSpalte);
				this.vButtons.get(i)[k].setLineWrap(oDefSpalte.get_bLineWrapList());
				
				this.vButtons.get(i)[k].remove_AllActionAgents();
				
				for (int l=0;l<this.vStandardAction4ClickListButton.size();l++)
				{
					this.vButtons.get(i)[k].add_oActionAgent(this.vStandardAction4ClickListButton.get(l));
				}

				this.add(vButtons.get(i)[k], oDefSpalte.get_glListButtons());

				if (oFormater4Buttons != null)
				{
					oFormater4Buttons.DO_Format(vButtons.get(i)[k]);
				}
				
				//falls hier noch kein tooltip an dem button haengt, einen anfuegen, der die zugehoerige id anzeige
				if (S.isEmpty(this.vButtons.get(i)[k].getToolTipText()))
				{
					this.vButtons.get(i)[k].setToolTipText(cToolTipText);;
				}
				
				
			}
		}

	}
	
	
	
	public HashMap<Integer,DefSpalteLayout_And_Else>  get_hmSortierInfo() throws myException	{	return this.hmDefinition4Sort;		}
	public Boolean get_bResultListIsSortable() 											{	return this.bResultListIsSortable;	}

	public Object get_oSearchField()
	{
		return oSearchField;
	}

	public void set_oSearchField(Object oSearchField)
	{
		this.oSearchField = oSearchField;
	}



}
