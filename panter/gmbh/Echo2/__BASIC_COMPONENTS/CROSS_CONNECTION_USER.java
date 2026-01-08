package panter.gmbh.Echo2.__BASIC_COMPONENTS;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_FormatCheckBox;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_SelectAgentForCheckboxesVisible;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class CROSS_CONNECTION_USER extends MyE2_DBC_CrossConnection
{
	
//	private SQLFieldForPrimaryKey oFieldPrimaryKeyFromTopTable = 	null;      //Primaerschluesselfeld der Relevanten Tabelle, a die Benutzer angehaengt werden
//	private String                cNameMittlerTabelle = 		null;      //Name der Vermittlertabelle
//	private String                cIDMittlerTabelle = 			null;      //Name des Primarykey der mittlertabelle
//	private int                   iCrossType = 					0;
//	private String                cNameID_TopTable_IN_MittlerTabelle = null;   //Name der ID der Toptabelle in der mittlertabelle
//	private String                cNameID_USER_IN_MittlerTabelle = null;   //Name der id_user in der mittlertabelle
	
	private    ownCheckBoxToggleInaktivUsers   			oCB_Inaktiv = null;
	private    ownCheckBoxToggle_NOT_VerwaltungUsers   	oCB_NichtVerwaltung = null;
	
	private    Font     								oFont4List = null;
	
	private    GridLayoutData 							GLBase = LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_1_1_1_1);
	private    GridLayoutData 							GLSelected = LayoutDataFactory.get_GridLayout(E2_INSETS.I_1_1_1_1, new E2_ColorLLight(),new Alignment(Alignment.LEFT,Alignment.TOP),1);

	
	public CROSS_CONNECTION_USER(	SQLFieldForPrimaryKey 	o_FieldPrimaryKeyFromTopTable,
									String 					c_NameMittlerTabelle, 
									String 					c_IDMittlerTabelle,
									String 					c_NameID_TopTable_IN_MittlerTabelle,
									String 					c_NameID_USER_IN_MittlerTabelle,
									int 					iAnordnungSpaltenZahl,
									Font                    oFontForList,
									int 					i_CrossType 
									)
			throws myException
	{
		super(			o_FieldPrimaryKeyFromTopTable, 
						c_NameMittlerTabelle, 
						"JD_USER", 
						c_IDMittlerTabelle,
						c_NameID_TopTable_IN_MittlerTabelle, 
						c_NameID_USER_IN_MittlerTabelle,
						"ID_USER", 
//						"  NVL(NAME,'-')||' ('||  NVL(VORNAME,'<vorname>')||' '|| NVL(NAME1,'<name1>')||')'",
						"  NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') || ' ('||  NVL(KUERZEL,'?')||')'",
						" NVL(JD_USER.AKTIV,'N') ", 
						" NVL(JD_USER.IST_VERWALTUNG,'N') ", 
						 bibALL.get_Vector("JD_USER.ID_MANDANT="+bibALL.get_ID_MANDANT()),
//						"  NVL(NAME,'-')||' ('||  NVL(VORNAME,'<vorname>')||' '|| NVL(NAME1,'<name1>')||')'",
						 " NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') ", 
						iAnordnungSpaltenZahl,
						oFontForList==null?new E2_FontPlain(-2):oFontForList,  
						new MyE2_String("Fehler beim Aufbau der Benutzerliste"), 
						i_CrossType);

		this.oCB_Inaktiv = 			new ownCheckBoxToggleInaktivUsers(this);
		this.oCB_NichtVerwaltung = 	new ownCheckBoxToggle_NOT_VerwaltungUsers(this);
		
		this.oFont4List = oFontForList==null?new E2_FontPlain(-2):oFontForList;
		
		
		this.add_AddOnComponentsInFront(new E2_ComponentGroupHorizontal_NG(this.oCB_Inaktiv,this.oCB_NichtVerwaltung));
		this.set_oAgentToSelektAnzeige(new SelectAgent());
		this.add_ActionAgentToCheckboxes(new ownActionAgent());
		this.set_oFormatCheckBoxes(new CheckBoxFormater());
		this.build_selectorGrid();
		
	}
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			if (CROSS_CONNECTION_USER.this.get_oFormatCheckBoxes()!=null)
			{
				CROSS_CONNECTION_USER.this.get_oFormatCheckBoxes().doFormat( (MyE2_CheckBox)oExecInfo.get_MyActionEvent().getSource());
			}
		}
	}
	
	
	
	
	//checkboxen fuer ein/ausblenden der inaktiven benutzer
	private class ownCheckBoxToggleInaktivUsers extends MyE2_CheckBox
	{
		MyE2_DBC_CrossConnection oCCTeilnehmer = null;
		
		public ownCheckBoxToggleInaktivUsers(MyE2_DBC_CrossConnection CCTeilnehmer) throws myException
		{
			super(new MyE2_String("Inaktive"));
			this.oCCTeilnehmer = CCTeilnehmer;
			this.add_oActionAgent(new ownActionToogleInaktivUsers());
			GridLayoutData oGL = new GridLayoutData();
			oGL.setBackground(new E2_ColorDDDark());
			this.setLayoutData(oGL);
			
			this.setToolTipText(new MyE2_String("Zeige auch inaktive Benutzer an!" ).CTrans());
			
		}
		
		//schalter fuer das ein/ausschalten von inaktiven benutzern
		private class ownActionToogleInaktivUsers extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				ownCheckBoxToggleInaktivUsers.this.oCCTeilnehmer.build_selectorGrid();
			}
		}

	}
	
	//checkboxen fuer ein/ausblenden der inaktiven benutzer
	private class ownCheckBoxToggle_NOT_VerwaltungUsers extends MyE2_CheckBox
	{
		MyE2_DBC_CrossConnection oCCTeilnehmer = null;
		
		public ownCheckBoxToggle_NOT_VerwaltungUsers(MyE2_DBC_CrossConnection CCTeilnehmer) throws myException
		{
			super(new MyE2_String("Nicht-Verwaltung"));
			this.oCCTeilnehmer = CCTeilnehmer;
			this.add_oActionAgent(new ownActionToogle_NOT_VerwaltungUsers());
			GridLayoutData oGL = new GridLayoutData();
			oGL.setBackground(new E2_ColorDDDark());
			this.setLayoutData(oGL);
			
			this.setToolTipText(new MyE2_String("Zeige auch Nicht-Verwaltungs-Benutzer an!" ).CTrans());

			
		}
		
		//schalter fuer das ein/ausschalten von inaktiven benutzern
		private class ownActionToogle_NOT_VerwaltungUsers extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				ownCheckBoxToggle_NOT_VerwaltungUsers.this.oCCTeilnehmer.build_selectorGrid();
			}
		}

	}

	
	private class CheckBoxFormater extends XX_FormatCheckBox
	{

		@Override
		public void doFormat(MyE2_CheckBox oCB) throws myException
		{
			if (oCB.isSelected())
			{
				oCB.setLayoutData(CROSS_CONNECTION_USER.this.GLSelected);
			}
			else
			{
				oCB.setLayoutData(CROSS_CONNECTION_USER.this.GLBase);
			}
			
		}
		
	}
	
	
	private class SelectAgent extends XX_SelectAgentForCheckboxesVisible
	{
		
		@Override
		public boolean get_Visible(MyE2_CheckBox ocb)
		{
			CROSS_CONNECTION_USER oThis = CROSS_CONNECTION_USER.this; 
			
			//inaktive hier auch markieren
			ocb.setFont(oThis.oFont4List);
			if (ocb.EXT().get_C_MERKMAL2().equals("N"))
			{
				ocb.setFont(new E2_Font(Font.LINE_THROUGH,-2));
			}
			
			boolean bVisible = true;
			if (ocb.EXT().get_C_MERKMAL2().equals("N") && (!oThis.oCB_Inaktiv.isSelected()))   //aktiv ?
			{
				bVisible = false;
			}
			
			
			if (ocb.EXT().get_C_MERKMAL3().equals("N") && (!oThis.oCB_NichtVerwaltung.isSelected()))    //Verwaltung
			{
				bVisible = false;
			}
			
			
			return bVisible;
		}
		
		@Override
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			return null;
		}
		
	}
	
	
	
}
