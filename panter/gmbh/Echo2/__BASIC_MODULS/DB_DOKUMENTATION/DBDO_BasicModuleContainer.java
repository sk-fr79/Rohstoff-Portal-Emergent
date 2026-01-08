package panter.gmbh.Echo2.__BASIC_MODULS.DB_DOKUMENTATION;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextAreaWithEditAndSave;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.ORA_FIELD_COMMENT_RECORD;
import panter.gmbh.indep.dataTools.ORA_FIELD_QUERY;
import panter.gmbh.indep.dataTools.ORA_TABLE_QUERY;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class DBDO_BasicModuleContainer extends E2_BasicModuleContainer
{

	private static int[]       					iSpalten = 				{200,500};
	
	private MyE2_Grid  							oGridBase = 			new MyE2_Grid(DBDO_BasicModuleContainer.iSpalten, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private MyE2_SelectField  					oSelField_DB_Tables = 	null;
	private Vector<String>      				vFieldsNotToComment =   bibVECTOR.get_Vector("GEAENDERT_VON","ID_MANDANT","LETZTE_AENDERUNG","ERZEUGT_VON","ERZEUGT_AM");
	
	private Vector<ownTextAreaWithEditAndSave>  vInputFields = 			new Vector<DBDO_BasicModuleContainer.ownTextAreaWithEditAndSave>();
	
	private ORA_FIELD_COMMENT_RECORD     		recComments = null;
	
	
	public DBDO_BasicModuleContainer() throws myException
	{
		super();
		
		this.baueInputList(null);
		
		this.add(this.oGridBase, E2_INSETS.I_5_5_5_5);
		
		
	}
	
	
	private class ownActionAuswahlTabelle extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			DBDO_BasicModuleContainer.this.baueInputList(DBDO_BasicModuleContainer.this.oSelField_DB_Tables.get_ActualWert());
		}
	}

	
	private void baueInputList(String cTableName)  throws myException
	{
		this.oGridBase.removeAll();
		
		this.oGridBase.add(new MyE2_Label(new MyE2_String("Modul ist in Entwicklung ...")),2,E2_INSETS.I_5_5_5_5);
		
//		if (S.isEmpty(cTableName))
//		{
//		   // dann wird nur der selektor oSelField_DB_Tables neu aufgebaut
//		   this.oSelField_DB_Tables = new MyE2_SelectField(new ORA_TABLE_QUERY().get_ArrayTables(true), " ", false, new Extent(500));
//		   this.oSelField_DB_Tables.add_oActionAgent(new ownActionAuswahlTabelle());
//
//		   this.oGridBase.add(new MyE2_Label(new MyE2_String("Tabelle:")), new gridLayoutDarkBackground());
//		   this.oGridBase.add(this.oSelField_DB_Tables, new gridLayoutDarkBackground());
//		   
//		   this.recComments = null;
//
//		}
//		else
//		{
//		   this.oGridBase.add(new MyE2_Label(new MyE2_String("Tabelle:")), new gridLayoutDarkBackground());
//		   this.oGridBase.add(this.oSelField_DB_Tables, new gridLayoutDarkBackground());
//			
//			//field-liste aufbauen
//		   this.recComments = new ORA_FIELD_COMMENT_RECORD(cTableName);
//		   String[] cFieldList = this.recComments.get_ArrayFields(this.vFieldsNotToComment);
//		   
//		   for (int i=0;i<cFieldList.length;i++)
//		   {
//			   this.oGridBase.add(new MyE2_Label(new MyE2_String(cFieldList[i])), new gridLayoutEditRange());
//			   this.oGridBase.add(new ownTextAreaWithEditAndSave(this.recComments,cFieldList[i]));
//		   }
//		}
	}
	
	
	private class gridLayoutDarkBackground extends GridLayoutData
	{

		public gridLayoutDarkBackground()
		{
			super();
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
			this.setInsets(new Insets(5,5,5,5));
			this.setBackground(new E2_ColorDDark());
		}
		
	}
	
	
	private class gridLayoutEditRange extends GridLayoutData
	{

		public gridLayoutEditRange()
		{
			super();
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
			this.setInsets(new Insets(5,5,5,5));
			this.setBackground(new E2_ColorBase());
		}
		
	}
	
	
	
	private class ownTextAreaWithEditAndSave extends MyE2_TextAreaWithEditAndSave
	{

		private String 						cValueBeforeEdit = "";
		private String 						cFieldName = "";
		private ORA_FIELD_COMMENT_RECORD   	recComments = null;
		
		public ownTextAreaWithEditAndSave(ORA_FIELD_COMMENT_RECORD   RecComments, String FieldName) throws myException
		{
			super("", 500, -1, 3);
			
			this.recComments = RecComments;
			this.cFieldName = FieldName;
			
			this.setStatusWait4Edit();
			this.get_oButtonEdit().add_oActionAgent(new ownActionEdit());
			this.get_oButtonSave().add_oActionAgent(new ownActionSave());
			this.get_oButtonCancel().add_oActionAgent(new ownActionCancel());
		}
		
		private class ownActionEdit extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownTextAreaWithEditAndSave oThis = ownTextAreaWithEditAndSave.this;
				oThis.setStatusEdit();
				oThis.cValueBeforeEdit=oThis.getText();
			}
		}

		private class ownActionCancel extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownTextAreaWithEditAndSave oThis = ownTextAreaWithEditAndSave.this;
				oThis.setStatusWait4Edit();
				oThis.setText(oThis.cValueBeforeEdit);
			}
		}

		private class ownActionSave extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				ownTextAreaWithEditAndSave oThis = ownTextAreaWithEditAndSave.this;
				oThis.setStatusWait4Edit();
			}
		}
		
	}
	
	
}

