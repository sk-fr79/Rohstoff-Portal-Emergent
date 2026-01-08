/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.ExportSql;

import java.text.SimpleDateFormat;
import java.util.Date;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class EXP_portDef {
	
//	private EXP_ENUM_EVALTYP evalTyp = null;
	private IF_Field         field = null;
//	private String           textToInterpret =null;
	
	private RB_cb  			cbAktiv = new RB_cb()._check();
	private RB_lab 			label  = null; 
	private RB_selField     selType = null; 
	private RB_TextArea   	defText = null;
	
	
	/**
	 * @param evalTyp
	 * @param field
	 * @param textToInterpret
	 * @throws myException 
	 */
	public EXP_portDef(EXP_ENUM_EVALTYP evalTyp, IF_Field field, String textToInterpret) throws myException {
		super();
		this.field = field;
		
		this.label = new RB_lab()._t(this.getField().fn());
		this.selType = new RB_selField()._populate(EXP_ENUM_EVALTYP.FIELDVAL.get_dd_Array(false))._setActiveDBVal(EXP_ENUM_EVALTYP.FIELDVAL.db_val())._aaa(new ownAction());
		this.defText = new RB_TextArea()._width(590)._height(25)._txt("#"+this.getField().fn()+"#")._fo_s_plus(-2);
		
		//jetzt die werte laden
		EXP_UserSetting_SaveDropDown 	saveDd = new EXP_UserSetting_SaveDropDown(this.field);
		EXP_UserSetting_SaveTextField 	saveText = new EXP_UserSetting_SaveTextField(this.field);
		
		String oldDD = saveDd.LOAD();
		String oldText = saveText.LOAD();
		
		if (S.isFull(oldDD)) {
			this.selType._setActiveDBVal(oldDD);
		}
		
		if (S.isFull(oldText)) {
			this.defText.setText(oldText);
		}
	}
	
	
	public EXP_ENUM_EVALTYP getEvalTyp() {
		return EXP_ENUM_EVALTYP.FIELDVAL.getEnum(this.selType.getActualDbVal());
	}
	
	public IF_Field getField() {
		return field;
	}
	
	
	public String getTextToInterpret() {
		return S.NN(this.defText.getText());
	}
	
	public EXP_portDef _setEvalTyp(EXP_ENUM_EVALTYP evalTyp) throws myException {
		this.selType._setActiveDBVal(evalTyp.db_val());
		return this;
	}
	
	public EXP_portDef _setField(IF_Field field) {
		this.field = field;
		return this;
	}
	
	public EXP_portDef _setTextToInterpret(String textToInterpret) {
		this.defText.setText(textToInterpret);
		return this;
	}
	
	
	public boolean isAktiv() {
		return this.cbAktiv.isSelected();
	}
	
	public EXP_portDef _setActive(boolean activ) {
		this.cbAktiv.setSelected(activ);
		return this;
	}

	public RB_cb getCbAktiv() {
		return cbAktiv;
	}

	public RB_lab getFieldLabel() {
		return label;
	}

	public RB_selField getSelType() {
		return selType;
	}

	public RB_TextArea getDefText() {
		return defText;
	}
	
	
	private class ownAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			EXP_portDef.this.defText.setText("");
			
			if (EXP_ENUM_EVALTYP.FIELDVAL.getEnum(EXP_portDef.this.selType.getActualDbVal())==EXP_ENUM_EVALTYP.FIELDVAL) {
				EXP_portDef.this.defText.setText("#"+EXP_portDef.this.field.fn()+"#");
			}
			
		}
	}
	
	
	public String interpretText(Rec20 r) throws myException {
		
		String cRet = "NULL";
		if  (this.getEvalTyp()==EXP_ENUM_EVALTYP.KONSTANTE) {
			if (S.isFull(this.getTextToInterpret().trim())) {
				cRet =  this.getTextToInterpret().trim();
				_TAB tab = this.field._t();

				//jetzt nachsehen, was in dem statement fuer textplatzhalter stehen
				VEK<IF_Field> placeholders = new VEK<IF_Field>();
				
				for (IF_Field f: tab.get_fields()) {
					if (cRet.contains("#"+f.fieldName()+"#")) {
						placeholders.add(f);
					}
				}
				
				for (IF_Field f: placeholders) {
					cRet = bibALL.ReplaceTeilString(cRet,"#"+f.fieldName()+"#" , r.get_value4sql_statements(f));
				}
			}
		} else if (this.getEvalTyp()==EXP_ENUM_EVALTYP.FIELDVAL) {
			String text = this.getTextToInterpret().trim();
			
			if (text.length()<4 || (!text.startsWith("#")) || (!text.endsWith("#")) ) {
				throw new myException("Fieldval-Type cannot be "+text);
			} else {
				text = text.substring(1);
				text = text.substring(0,text.length()-1);    //jetzt sind ## weg
				
				//jetzt das feld des zu interpretierenden textes suchen
				IF_Field f = _TAB.find_field(this.field._t(), text);
				
				cRet = r.get_value4sql_statements(f);
				
				Object ob = r.getValue(f);
				
				if (f.generate_MetaFieldDef().get_bIsDateType() && ob instanceof Date) {
					//dann in umwandlungsformat stecken
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String dateString = format.format((Date)ob);
					
					cRet = "TO_DATE('"+dateString+"','YYYY-MM-DD HH24:MI:SS')";
				}
			}
		} else if (this.getEvalTyp()==EXP_ENUM_EVALTYP.SQLQUERY) {
			_TAB tab = this.field._t();
			String sqlText = this.getTextToInterpret().trim();
			
			//jetzt nachsehen, was in dem statement fuer textplatzhalter stehen
			VEK<IF_Field> placeholders = new VEK<IF_Field>();
			
			for (IF_Field f: tab.get_fields()) {
				if (sqlText.contains("#"+f.fieldName()+"#")) {
					placeholders.add(f);
				}
			}
			
			for (IF_Field f: placeholders) {
				sqlText = bibALL.ReplaceTeilString(sqlText,"#"+f.fieldName()+"#" , r.get_value4sql_statements(f));
			}

			if (  (!sqlText.toUpperCase().trim().startsWith("SELECT")) || sqlText.toUpperCase().contains("DELETE") || sqlText.toUpperCase().contains("UPDATE") ) {
				throw new myException(this, "Error NOT ALLOED KEYWORDS in SQL: <"+sqlText+">");
			}
			
			
			try {
				String ergebnis = bibDB.EinzelAbfrage(sqlText);
				if (S.isEmpty(ergebnis) || ergebnis.startsWith("@@@")) {
					throw new myException(this, "Error interpreting SQL: <"+sqlText+">");
				} else {
					
					DEBUG._print(ergebnis);
					
					cRet = "("+ergebnis+")";
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new myException(this, "Error interpreting SQL: <"+sqlText+">");
			}
		}
		
		return cRet;
	}
	
	
	
	public void saveUserSettings() throws myException {
		//jetzt die werte laden
		EXP_UserSetting_SaveDropDown 	saveDd = new EXP_UserSetting_SaveDropDown(this.field);
		EXP_UserSetting_SaveTextField 	saveText = new EXP_UserSetting_SaveTextField(this.field);
		
		saveDd.SAVE(this.selType.getActualDbVal());
		saveText.SAVE(this.getTextToInterpret());
	}
	
	
}
