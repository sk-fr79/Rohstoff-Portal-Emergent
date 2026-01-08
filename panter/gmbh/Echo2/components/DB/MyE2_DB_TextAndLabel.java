package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.staticStyles.Style_Row_Normal;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



/**
 * komponente mit eine einem label und einem textfeld,
 * das in listen die umschaltung con anzeige in edit-modus moeglich macht
 */
public class MyE2_DB_TextAndLabel extends MyE2_Row   implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy
{
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private MyE2_TextField			oTextField = new MyE2_TextField();
	private MyE2_Label				oLabel	= new MyE2_Label("");

	private MyE2_Button				oButtonEdit = new MyE2_Button(E2_ResourceIcon.get_RI("edit.png"));
	private MyE2_Button				oButtonSave = new MyE2_Button(E2_ResourceIcon.get_RI("save.png"));
	private MyE2_Button				oButtonCancel = new MyE2_Button(E2_ResourceIcon.get_RI("cancel.png"));
	private MyE2_Label				oLabelSpace	= new MyE2_Label(E2_ResourceIcon.get_RI("empty.png"));
	
	private boolean 				bShowOwnButtons = false;    // dann werden die buttons dieses feldes gezeigt
	
	private String 					cValueBeforeEdit	= "";			// speichert den alten wert in der maske, wenn edit
																		// aufgerufen wird
	
	/*
	 * fuer jeden der 3 buttons werden je ein actionagent definiert
	 */
	private ActionAgent_Edit_InListElement  oAgentEdit = null;
	private ActionAgent_Save_InListElement  oAgentSave = null;
	private ActionAgent_Cancel_InListElement  oAgentCancel = null;
	
	
	/*
	 * contentpane fuer evtl. popups  
	 */
	private E2_ContentPane oBasePane = null;
	
	
	public MyE2_DB_TextAndLabel(SQLField osqlField, E2_ContentPane obasePane) throws myException
	{
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);

		this.oBasePane = obasePane;
		/*
		 * standard ist erstmal inaktiv d.h. label
		 */
		this.add(this.oLabel);
		this.setStyle(new Style_Row_Normal(0,new Insets(0)));
		
		this.set_bEnabled_For_Edit(false);
		
		this.oAgentEdit = new ActionAgent_Edit_InListElement(this);
		this.oAgentSave = new ActionAgent_Save_InListElement(this);
		this.oAgentCancel = new ActionAgent_Cancel_InListElement(this);
		
		this.oButtonEdit.add_oActionAgent(this.oAgentEdit);
		this.oButtonSave.add_oActionAgent(this.oAgentSave);
		this.oButtonCancel.add_oActionAgent(this.oAgentCancel);
		
		this.oButtonEdit.setLayoutData(new RowLayout_For_MultiComponentRows());
		this.oButtonSave.setLayoutData(new RowLayout_For_MultiComponentRows());
		this.oButtonCancel.setLayoutData(new RowLayout_For_MultiComponentRows());
		this.oLabel.setLayoutData(new RowLayout_For_MultiComponentRows());
		this.oTextField.setLayoutData(new RowLayout_For_MultiComponentRows());
	}

	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		String cText = "";
		
		if (!bSetDefault)
		{
			this.oLabel.setText("");
			this.oTextField.setText("");
			this.EXT_DB().set_cLASTActualDBValueFormated(cText);
			this.EXT_DB().set_cLASTActualMaskValue(cText);
			
			
			return;
		}
		
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();
		
		this.oLabel.setText(cText);
		this.oTextField.setText(cText);

		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);
	}

	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bSuperEnabled = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();

		/*
		 * neueingabe wird anders behandelt: nur label
		 */
		if (this.EXT().get_oComponentMAP() != null)
			if (this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null)
			{
				this.removeAll();
				this.add(this.oLabel);
				return;
			}
		
		
		if (bSuperEnabled)
		{
			this.cValueBeforeEdit=this.oTextField.getText();
			
			this.removeAll();
			if (this.bShowOwnButtons)
			{
				this.add(this.oButtonSave);
				this.add(this.oButtonCancel);
				this.add(this.oTextField);
			}
			else
			{
				this.add(this.oTextField);
			}
		}
		else 
		{
			this.removeAll();
			if (this.bShowOwnButtons)
			{
				this.add(this.oButtonEdit);
				this.add(this.oLabelSpace);
				this.add(this.oLabel);
			}
			else
			{
				this.add(this.oLabel);
			}
		}
		
		
		/*
		 * jetzt das textfeld noch formatieren
		 */
		if (bSuperEnabled)
		{
			this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(bSuperEnabled,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
		}
	}

	
	public String get_cActualMaskValue() throws myException
	{
		return this.oTextField.getText();
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return this.oTextField.getText();
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		this.oLabel.setText(cText);
		this.oTextField.setText(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);
	}

	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_TextAndLabel:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");


		this.oLabel.setText(cText);
		this.oTextField.setText(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
	}

	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	public boolean get_bIsComplexObject()
	{
		return false;
	}

	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP) throws myException
	{
		return null;
	}

	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public MyE2EXT__DB_Component 			EXT_DB()									{	return this.oEXTDB;	}
	public void 							set_EXT_DB(MyE2EXT__DB_Component oeXT_DB)	{	this.oEXTDB=oeXT_DB;	}
	public boolean 							get_bShowOwnButtons()						{	return bShowOwnButtons;	}
	public MyE2_Button 						get_oButtonCancel()							{	return oButtonCancel;}
	public MyE2_Button 						get_oButtonEdit()							{	return oButtonEdit;	}
	public MyE2_Button 						get_oButtonSave()							{	return oButtonSave;	}
	public MyE2_Label 						get_oLabel()								{	return oLabel;	}
	public MyE2_TextField 					get_oTextField()							{	return oTextField;	}
	public void 							set_oButtonCancel(MyE2_Button buttonCancel)	{	oButtonCancel = buttonCancel;	}
	public void 							set_oButtonEdit(MyE2_Button buttonEdit)		{	oButtonEdit = buttonEdit;}
	public void 							set_oButtonSave(MyE2_Button buttonSave)		{	oButtonSave = buttonSave;	}
	public void 							set_oLabel(MyE2_Label label)				{	oLabel = label;	}
	public void 							set_oTextField(MyE2_TextField textField)	{	oTextField = textField;	}

	public void 							set_bShowOwnButtons(boolean showOwnButtons) throws myException
	{	
		bShowOwnButtons = showOwnButtons;
		this.set_bEnabled_For_Edit(this.isEnabled());
	}
	

	public String get_cValueBeforeEdit()
	{
		return cValueBeforeEdit;
	}

	
	
	public ActionAgent_Cancel_InListElement 	get_oAgentCancel()		{		return oAgentCancel;	}
	public ActionAgent_Edit_InListElement 		get_oAgentEdit()		{		return oAgentEdit;	}
	public ActionAgent_Save_InListElement 		get_oAgentSave()		{		return oAgentSave;	}
	public E2_ContentPane 		get_oBasePane()			{		return this.oBasePane; }
	
	
	public void show_InputStatus(boolean bInputIsOK)
	{
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(this.oTextField.isEnabled(),this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),!bInputIsOK));
	}

	
	
	
	
	
	
	
	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_TextAndLabel oRueck = null;
		
		try
		{
			oRueck = new MyE2_DB_TextAndLabel(this.oEXTDB.get_oSQLField(),this.oBasePane);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_TextAndLabel:get_Copy:copy-error!");
		}

		
		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		oRueck.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oRueck));
		
		oRueck.get_oLabel().setStyle(this.get_oLabel().getStyle());
		oRueck.get_oTextField().setStyle(this.get_oTextField().getStyle());
		oRueck.get_oLabel().setFont(this.get_oLabel().getFont());
		oRueck.get_oTextField().setFont(this.get_oTextField().getFont());
		
		oRueck.setStyle(this.getStyle());
		try
		{
			oRueck.set_bShowOwnButtons(this.get_bShowOwnButtons());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
		
		return oRueck;
	}


	
	
	
	
	/*
	 * standard-actionagent fuer die buttons edit/cancel/save innerhalb der liste
	 */
	private class ActionAgent_Edit_InListElement extends XX_ActionAgent
	{
		private MyE2_DB_TextAndLabel oDB_LabelAndText = null;

		public ActionAgent_Edit_InListElement(MyE2_DB_TextAndLabel oLabeltext)
		{
			super();
			oDB_LabelAndText = oLabeltext;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				this.oDB_LabelAndText.set_bEnabled_For_Edit(true);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("MyE2_DB_TextAndLabel:ActionAgent_Edit_InListElement:doAction: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		}
		
	}
	
	

	/*
	 * standard-actionagent fuer die buttons edit/cancel/save innerhalb der liste
	 */
	private class ActionAgent_Save_InListElement extends XX_ActionAgent
	{
		private MyE2_DB_TextAndLabel oDB_LabelAndText = null;

		public ActionAgent_Save_InListElement(MyE2_DB_TextAndLabel oLabeltext)
		{
			super();
			oDB_LabelAndText = oLabeltext;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			/*
			 * hier wird gespeichert
			 */
			E2_ComponentMAP 	oMap = this.oDB_LabelAndText.EXT().get_oComponentMAP();
			SQLFieldMAP			oFieldMAP = oMap.get_oSQLFieldMAP();
			SQLResultMAP 		oResultMAP = oMap.get_oInternalSQLResultMAP();
			SQLField			oField = this.oDB_LabelAndText.EXT_DB().get_oSQLField();
			SQLField			oPKFieldOfTable = (SQLField) oFieldMAP.get_hmPrimaryKeys().get(oField.get_cTableName());
			String 				cPKFieldNAME = oPKFieldOfTable.get_cFieldName();
			
			if (cPKFieldNAME == null)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("Table has nor primary key !!!"));
			}
			else
			{
				if (!oField.get_bWriteable())
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Das Feld "+oField.get_cFieldLabel()+" ist nicht zum Schreibzugriff zugelassen"));
				}
				else
				{
					bibMSG.add_MESSAGE(oField.get_vCheckNewValue(this.oDB_LabelAndText.get_oTextField().getText()));
					if (bibMSG.get_bIsOK())
					{
						try
						{
							String 	cPKFieldUnformatedValue =  oResultMAP.get_UnFormatedValue(oPKFieldOfTable.get_cFieldLabel());

							if (bibALL.isEmpty(cPKFieldUnformatedValue))
							{
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("MyE2_DB_TextAndLabel:ActionAgent_Save_InListElement:doAction:Error: Primary key has no value !!!"));
							}
							else
							{
								oField.set_cNewValueFormated(this.oDB_LabelAndText.get_oTextField().getText());
								String cSQL = "UPDATE "+bibALL.get_TABLEOWNER()+"."+oField.get_cTableName()+" SET "+
											 oField.get_cFieldName()+"="+oField.get_cInsertValuePart()+ " WHERE "+cPKFieldNAME+"="+cPKFieldUnformatedValue;
								
								if (!bibDB.ExecSQL(cSQL,true))
								{
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("MyE2_DB_TextAndLabel:ActionAgent_Save_InListElement:doAction: SQL-Error:"));
									bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(cSQL));
								}
								else
								{
									bibMSG.add_MESSAGE(new MyE2_Info_Message("Datensatz gespeichert !"));
									oMap._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,false,null);
									oMap.set_Marker(true);
									this.oDB_LabelAndText.set_bEnabled_For_Edit(false);
								}
							}
							
						}
						catch (myException ex)
						{
							ex.printStackTrace();
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("MyE2_DB_TextAndLabel:ActionAgent_Save_InListElement:doAction: Error: "));
							bibMSG.add_MESSAGE(ex.get_ErrorMessage());
						}
					}
				}
			}
		}
	}
	

	/*
	 * standard-actionagent fuer die buttons edit/cancel/save innerhalb der liste
	 */
	private class ActionAgent_Cancel_InListElement extends XX_ActionAgent
	{
		private MyE2_DB_TextAndLabel oDB_LabelAndText = null;

		public ActionAgent_Cancel_InListElement(MyE2_DB_TextAndLabel oLabeltext)
		{
			super();
			oDB_LabelAndText = oLabeltext;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			try
			{
				this.oDB_LabelAndText.set_bEnabled_For_Edit(false);
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("MyE2_DB_TextAndLabel:ActionAgent_Edit_InListElement:doAction: "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			this.oDB_LabelAndText.get_oLabel().setText(this.oDB_LabelAndText.get_cValueBeforeEdit());
			this.oDB_LabelAndText.get_oTextField().setText(this.oDB_LabelAndText.get_cValueBeforeEdit());
		}
		
	}



	
	private class RowLayout_For_MultiComponentRows extends RowLayoutData
	{

		public RowLayout_For_MultiComponentRows()
		{
			super();
			this.setInsets(new Insets(0,0,2,0));
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		}

	}

	
	
	
	
	
	
}
