package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_SelectAgentForCheckboxesVisible;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class TODO_MASK_CROSS_TEILNEHMER extends	MyE2_DBC_CrossConnection
{
	
	private ownCheckBoxToggleInaktivUsers oSelectActiveInactiv = new ownCheckBoxToggleInaktivUsers();
	

	public TODO_MASK_CROSS_TEILNEHMER(SQLFieldMAP osqlFieldMAP) throws myException
	{
		super((SQLFieldForPrimaryKey)osqlFieldMAP.get("ID_TODO"),	
				"JT_TODO_TEILNEHMER", 
				"JD_USER", 
				"ID_TODO_TEILNEHMER",
				"ID_TODO", 
				"ID_USER",
				"ID_USER", 
//				"  NVL(JD_USER.NAME,'-')||' ('|| NVL(JD_USER.KUERZEL,'-')||')'",
				"  NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') || ' ('||  NVL(KUERZEL,'?')||')'",
				" NVL(JD_USER.AKTIV,'N') ", 
				null,
				bibALL.get_Vector("JD_USER.ID_MANDANT="+bibALL.get_ID_MANDANT()), 
//				"  NVL(JD_USER.NAME,'-')||' ('|| NVL(JD_USER.KUERZEL,'-')||')'", 
				" NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') ",
				6,
				new E2_FontPlain(-2), new MyE2_String("Fehler bei der Anordnung der Teilnehmer!"), MyE2_DBC_CrossConnection.CROSSTYP_ALLOW_ALL);
		
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
		
		
		this.add_AddOnComponentsInFront(this.oSelectActiveInactiv);
		this.set_oAgentToSelektAnzeige(new SelectAgent());
		
		//hier muss nach dem zufuegen des SelectAgent nochmal das selector-grid gebaut werden, damit im standard alle inaktiven ausgeblendet werden
		this.build_selectorGrid();
		
	}

	
	
	private class ownCheckBoxToggleInaktivUsers extends MyE2_CheckBox
	{
	
		public ownCheckBoxToggleInaktivUsers() throws myException
		{
			super(new MyE2_String("Inaktive"));
			this.add_oActionAgent(new ownActionToogleInaktivUsers());
			GridLayoutData oGL = new GridLayoutData();
			oGL.setBackground(new E2_ColorMaskHighlight());
			this.setLayoutData(oGL);
		}

		//schalter fuer das ein/ausschalten von inaktiven benutzern
		private class ownActionToogleInaktivUsers extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				TODO_MASK_CROSS_TEILNEHMER.this.build_selectorGrid();
			}
		}

	} 

	
	private class SelectAgent extends XX_SelectAgentForCheckboxesVisible
	{
		@Override
		public boolean get_Visible(MyE2_CheckBox ocb)
		{
			//inaktive hier auch markieren
			ocb.setFont(new E2_Font(Font.PLAIN,-2));
			if (ocb.EXT().get_C_MERKMAL2().equals("N"))
			{
				ocb.setFont(new E2_Font(Font.LINE_THROUGH,-2));
			}
			
			if (TODO_MASK_CROSS_TEILNEHMER.this.oSelectActiveInactiv.isSelected())
			{
				return true;
			}
			return ocb.EXT().get_C_MERKMAL2().equals("Y");
		}

		@Override
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			return null;
		}
	
	}

	
	
}
