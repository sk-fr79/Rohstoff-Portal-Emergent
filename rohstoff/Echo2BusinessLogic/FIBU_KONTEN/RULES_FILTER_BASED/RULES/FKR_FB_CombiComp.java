package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.RULES;

import java.sql.SQLException;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.filter.SQLSetString;
import panter.gmbh.indep.filter.SetString;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.FK_CONST;

public class FKR_FB_CombiComp extends MyE2_Grid_InLIST implements MyE2IF__DB_Component{
	
	private MyE2EXT__DB_Component 	oEXTDB = new MyE2EXT__DB_Component(this);
	private FKR_FB_CombiComp.Status  statusAktuell = FKR_FB_CombiComp.Status.FIELD;

	private SQLField   				sqlField = null;
	private SQLField   				dependentSqlField = null;
	private String[][] 				selectValues;
	
	private int widthInPixel = 0;
	private int rows = 0;
	
	private MyE2_TextArea 		textArea; 
	private MyE2_SelectField    selectField; 
	private MyE2_Label    		label; 
	private MyE2_Button			button; 
	private MyE2_Label    		labelResult; 


	public FKR_FB_CombiComp( SQLField  sql_Field, SQLField dep, String[][] values, int iWidthInPixel, int iRows) throws myException {
		super();
		this.sqlField = sql_Field;
		this.dependentSqlField = dep;
		this.selectValues = values;
		
		this.__define(this.sqlField);
		widthInPixel = iWidthInPixel;
		rows = iRows;
		
		textArea  = new MyE2_TextArea("",widthInPixel, 10000, rows);
		selectField  = new MyE2_SelectField(this.selectValues, "", true, new Extent(widthInPixel));
		label = new MyE2_Label("");
		labelResult = new MyE2_Label("");
		button = new MyE2_Button("Ausdruck prüfen");
		
		MyE2_Grid_InLIST g = new MyE2_Grid_InLIST(1, Border.STYLE_NONE);
		g.setInsets(new Insets(0));
		g.setOrientation(ORIENTATION_HORIZONTAL);
		
		
		button.add_oActionAgent(new ClickedValidationButton());
		
		
		g.add(textArea);
		g.add(selectField);
		g.add(label);
		g.add(button);
		g.add(labelResult);
		add(g);
		
		setVisibilityAccordingToStatus();
	}
	
	/** Fires when the CONDIION_TYPE_LEFT changes */
	private class ClickedValidationButton extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			String exp = textArea.getText();
			labelResult.setVisible(true);

			String result = "OK - Der Ausdruck evaluiert zu: ";
			if (statusAktuell == Status.SQL) {
				labelResult.setForeground(Color.BLACK);
				try {
					SQLSetString sqs = new SQLSetString(exp);
					result = result + sqs.toString();
				} catch (SQLException e) {
					result = "Fehler im Ausdruck: "+e.getMessage();
					e.printStackTrace();
					labelResult.setForeground(Color.RED);
				}
				labelResult.set_Text(result);
			}
			
			if (statusAktuell == Status.SET) {
				result = result + new SetString(exp).toString();
				labelResult.set_Text(result);
			}
			
			
		}
		
	}

	
	public FKR_FB_CombiComp setDependentField(SQLField dep) {
		dependentSqlField = dep;
		return this;
	}


	private void __define(SQLField osqlField)
	{
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
	}

	
	public FKR_FB_CombiComp.Status getStatusAktuell() {
		return statusAktuell;
	}

	public void setStatusAktuell(FKR_FB_CombiComp.Status status_Aktuell) {
		this.statusAktuell = status_Aktuell;
		setVisibilityAccordingToStatus();
	}
	
	public void setStatusAktuell(String sa) {
		if (!(sa == null || sa.equals(""))) {
			this.statusAktuell = Status.valueOf(sa);
		}
		setVisibilityAccordingToStatus();
	}
	
	private void setVisibilityAccordingToStatus() {
		textArea.setVisible(false);
		selectField.setVisible(false);
		label.setVisible(false);
		button.setVisible(false);
		labelResult.setVisible(false);

		if (statusAktuell == Status.FIELD) {
			selectField.setVisible(true);
		}
		if (statusAktuell == Status.CONST || statusAktuell == Status.SQL || statusAktuell == Status.SET) {
			textArea.setVisible(true);
			textArea.set_iRows(1);
		}
		
		if (statusAktuell == Status.SQL) {
			label.setText("Bitte einen validen SQL-Query angeben.");
			label.setVisible(true);
			textArea.set_iRows(3);
			button.setVisible(true);
		} 
		if (statusAktuell == Status.SET) {
			label.setText("Bitte Werte mit Kommata trennen (Beispiel: '1, 2, 3')");
			label.setVisible(true);
			textArea.set_iRows(3);
			button.setVisible(true);
		}
	}


	private enum Status {
		FIELD, CONST, SQL, SET
	}
	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		
	}

	@Override
	public String get_cActualMaskValue() throws myException {
		return get_cActualDBValueFormated();
	}

	/** Returns the current value for database write */
	@Override
	public String get_cActualDBValueFormated() throws myException {
		if (statusAktuell == Status.FIELD) {
			return selectField.get_ActualWert();
		}
		return textArea.getText();
	}

	@Override
	public void set_cActualMaskValue(String cText) throws myException {
		
	}

	/** Sets the current value according to the database result */
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText,String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		String contents = oResultMAP.get_FormatedValue(this.sqlField.get_cFieldName());
		String status = oResultMAP.get_FormatedValue(this.dependentSqlField.get_cFieldName());
		setStatusAktuell(status);
		if (statusAktuell == Status.FIELD) {
			selectField.set_ActiveValue_OR_FirstValue(contents);
		} else {
			textArea.setText(contents);
		}
	}

	@Override
	public void set_bIsComplexObject(boolean bisComplex) {
	}

	@Override
	public boolean get_bIsComplexObject() {
		return false;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP,
			SQLMaskInputMAP oMaskInputMap) throws myException {
		return null;
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP,SQLMaskInputMAP oMaskInputMap) throws myException {
		return null;
	}

	
	public MyE2EXT__DB_Component EXT_DB()							{		return this.oEXTDB;		}
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)			{		this.oEXTDB = oEXT_DB;	}

	

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try {
			FKR_FB_CombiComp compRueck = new FKR_FB_CombiComp(this.sqlField, this.dependentSqlField, this.selectValues, widthInPixel, rows);
			return compRueck;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
