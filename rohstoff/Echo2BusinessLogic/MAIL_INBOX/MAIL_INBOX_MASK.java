package rohstoff.Echo2BusinessLogic.MAIL_INBOX;


import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

/**
 * 
 * @author manfred
 * @date   15.03.2013
 * 
 * @version: 1: muss von MyE2_Column abgeleitet sein, sonst funktioniert das TabedPane mit der automatischen Breite nicht. (War MyE2_Grid)
 * 
 *  
 */
public class MAIL_INBOX_MASK extends MyE2_Column  implements IF_BaseComponent4Mask
{

	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	public static RowLayoutData ownRowLayout = null;
	public static MutableStyle	ownRowStyle = null;
	
	public MAIL_INBOX_MASK(MAIL_INBOX_MASK_ComponentMAP oMap, MyE2_TabbedPane oTabbedPane) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		oTabbedPane.set_bAutoHeight(true);
		
		
		// die Grids für die Tab-Seiten
		oGridForTab		oGridPage000	= new oGridForTab(2);
		oGridForTab		oGridPage001	= new oGridForTab(2);
		
		
		oTabbedPane.add_Tabb(new MyE2_String("Email"),						oGridPage000,null);
		oTabbedPane.add_Tabb(new MyE2_String("Anhänge"),					oGridPage001,null);

//		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
		E2_MaskFiller oFiller = new E2_MaskFiller(oMap,null,null,new Insets(5,2,0,1),new Insets(5,2,0,1), new Alignment(Alignment.LEADING, Alignment.CENTER));


		//hier kommen die Felder	
		oFiller.add_Line(oGridPage000, new MyE2_String("Email-ID:"), 1, "ID_EMAIL_INBOX|#  |",3);
		oFiller.add_Line(oGridPage000, new MyE2_String("Empfänger:"), 1, "MAIL_TO|#  |",3);
		oFiller.add_Line(oGridPage000, new MyE2_String("CC:"), 1, "MAIL_CC|#  |",3);
		oFiller.add_Line(oGridPage000, new MyE2_String("Zustelldatum:"), 1, "DATE_RECEIVED|#  |#Sendedatum:|DATE_SEND|# |",3);
		oGridPage000.add_sep();// (new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(oGridPage000, new MyE2_String("Absender:"), 1, "MAIL_FROM|#  |",3);
		oFiller.add_Line(oGridPage000, new MyE2_String("Betreff:"), 1, "SUBJECT|#  |",3);
		oFiller.add_Line(oGridPage000, new MyE2_String("Text:"), 1, "MESSAGE_TEXT|#  |",3);
		oGridPage000.add_sep();
		oFiller.add_Line(oGridPage000, new MyE2_String("Zugeordnete Adresse:"), 1, "ID_ADRESSE_ZUGEORDNET|#  |"+ MAIL_INBOX_Const.BUTTON_DISCONNECT_ADRESSE+"|"+ MAIL_INBOX_Const.BUTTON_CONNECT_ADRESSE+"|",3);
		oGridPage000.add_sep();
		oFiller.add_Line(oGridPage000, new MyE2_String("Verarbeitet und bestätigt von:"), 1, "ID_USER_GELESEN|# am:|GELESEN_AM|# |"+ MAIL_INBOX_Const.BUTTON_CLEAR_CONFIRMATION +"|"+ MAIL_INBOX_Const.BUTTON_SET_CONFIRMATION +"|",3);
		oGridPage000.add_sep();
		
//		oFiller.add_Line(this, new MyE2_String("Verarbeitet: "),1,"GELESEN|#",1, " |#" + new MyE2_String("Abgeschlossen von:") +	"|ID_USER_ABGESCHLOSSEN_VON|# " + new MyE2_String("am:").toString() + "|ABGESCHLOSSEN_AM" ,6,align_lc);

		
//		oFiller.add_Line(this, new MyE2_String("MAIL_ACCOUNT:"), 1, "MAIL_ACCOUNT|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("MAIL_FOLDER:"), 1, "MAIL_FOLDER|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("MAIL_HOST:"), 1, "MAIL_HOST|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("MAIL_UID:"), 1, "MAIL_UID|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("MESSAGE_ID:"), 1, "MESSAGE_ID|#  |",3);
		
		
		oGridPage001.add(oMap.get_Comp(MAIL_INBOX_Const.DAUGHTER_PAGE_ARCHIVE_CONTENTS),  E2_INSETS.I_2_2_2_2);
		
		// das Tabbed-Pane einbinden
		this.add(oTabbedPane,E2_INSETS.I_0_0_0_0);
		

	}



	
	/**
	 * Grid für die Anordnung der Objekte in den einzelnen Tabs 
	 * @author manfred
	 * @date   15.03.2013
	 */
	private class oGridForTab extends MyE2_Grid
	{

		public oGridForTab(int iNumCols) 
		{
			super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			
			this.setSize(iNumCols);
			this.setWidth(new Extent(100,Extent.PERCENT));
			
			this.add(new MyE2_Label(" "),iNumCols, E2_INSETS.I_2_10_2_0);   //luft vom oberen rand
		}

		public oGridForTab(int[] iSpalten)
		{
			super(iSpalten,0);
			//this.setWidth(new Extent(100,Extent.PERCENT));
			this.add(new MyE2_Label(" "),iSpalten.length, E2_INSETS.I_2_10_2_0);   //luft vom oberen rand
		}
		
		
		public void add_sep()
		{
			this.NewLine();
			this.add(new Separator(),this.getSize(),E2_INSETS.I_2_0_2_0);
		}
		
		
	}

	
	/**
	 * actionAgents fuer die tab, die die Complexen tochter-felder aktivieren
	 */
	private class tabbActionAgent extends XX_ActionAgent
	{
		private MAIL_INBOX_MASK_ComponentMAP 		oE2_MAP = null;
		private XX_FULL_DAUGHTER			oDaughterToActivate = null;
		
		public tabbActionAgent(XX_FULL_DAUGHTER activate, MAIL_INBOX_MASK_ComponentMAP oe2_map)
		{
			super();
			this.oDaughterToActivate = activate;
			this.oE2_MAP = oe2_map;
		}

		
		public void executeAgentCode(ExecINFO oExecInfo)
		{
//			try
//			{
				//NEU_09
//				((FS_Component_MASK_DAUGHTER_ARTIKELBEZ) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_EK)).set_bIsActive(false);
//				((FS_Component_MASK_DAUGHTER_ARTIKELBEZ) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ARTBEZ_VK)).set_bIsActive(false);
//				
//				((FS_Component_MASK_DAUGHTER_MITARBEITER) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MITARBEITER)).set_bIsActive(false);
//				((FS_Component_MASK_DAUGHTER_ZUSATZINFOS) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_ZUSATZINFOS)).set_bIsActive(false);
//				((FS_Component_MASK_DAUGHTER_ZUSATZINFOS) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MESSAGES)).set_bIsActive(false);
//				((FS_Component_MASK_DAUGHTER_LIEFERADRESSEN) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_LIEFERADRESSEN)).set_bIsActive(false);
//				((FS_Component_MASK_DAUGHTER_KONTEN) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_KONTEN)).set_bIsActive(false);
//				((FS_Component_MASK_DAUGHTER_MATSPEZ) this.oE2_MAP_ADRESSE.get(FS_CONST.MASK_FIELD_COMPLETE_DAUGHTER_MATSPEZ)).set_bIsActive(false);
//				
//				if (this.oDaughterToActivate!=null) this.oDaughterToActivate.set_bIsActive(true);
//			}
//			catch (myException ex)
//			{
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("FS_Mask:tabbActionAgent:doAction:Error setting Complex-Objects!",false));
//			}
			
		}
		
	}



	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	

	
	
}
