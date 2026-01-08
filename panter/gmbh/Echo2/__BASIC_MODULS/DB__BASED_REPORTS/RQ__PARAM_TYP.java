/**
 * panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS
 * @author martin
 * @date 30.10.2018
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP_CreateDropDownForParameters;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 30.10.2018
 *
 */
public enum RQ__PARAM_TYP implements IF_enum_4_db_specified<RQ__PARAM_TYP>{
	
	YES_NO("Schalter")
	,TEXT("Textfeld")
	,TEXTAREA("Textfeld (mehrzeilig")
	,LONG("Ganzzahl")
	,DECIMAL("Komma-Zahl")
	,DATE("Datum")
	,DROPDOWN("Dropdown-Feld")
	
	;

	private String m_type = null;
	
	
	
	
	private RQ__PARAM_TYP(String type) {
		this.m_type = type;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#db_val()
	 */
	@Override
	public String db_val() {
		return this.name();
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#user_text()
	 */
	@Override
	public String user_text() {
		return this.m_type;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db_specified#get_Values()
	 */
	@Override
	public RQ__PARAM_TYP[] get_Values() {
		return RQ__PARAM_TYP.values();
	}

	
	public IF_RB_Component getComponent(Rec21 recParam) throws myException {
		
		IF_RB_Component c = null;
		
		switch (this) {
		
			case DATE:
				c = new RB_date_selektor();
				break;
			
			case DECIMAL:
				c = new RB_TextField()._w(200);
				break;
				
			case LONG:
				c = new RB_TextField()._w(200);
			
				break;
				
			case TEXT:
				c = new RB_TextField()._w(200);
				
				break;
				
			case TEXTAREA:
				c = new RB_TextArea()._sizeWH(200, 50);
				break;
				
			case YES_NO:
				c = new RB_cb();
			 	break;
			 	
			case DROPDOWN:
				c = new RB_selField();
				//jetzt die definition interpretieren
				String[][] selVals = REP_CreateDropDownForParameters.get_DropDownArray(recParam.getUfs(REPORTING_QUERY_PARAM.paramdef));
				if (selVals==null) {
					String name = "<???>";
					if (recParam != null && S.isFull(recParam.getUfs(REPORTING_QUERY_PARAM.paramkey))) {
						name = recParam.getUfs(REPORTING_QUERY_PARAM.paramkey);
					}
					throw new myException(this,"Parameter "+name+" has no correct DropDown-Definition !");
				} else {
					((RB_selField)c)._populate(selVals);
				}
				
				break;

		}
		
		return c;
	}

	
	
	public boolean isValid(String input) {
		boolean ret = false;
		
		switch (this) {
		
			case DATE:
				if (new MyDate(input).isOK()) {
					ret = true;
				}
				break;
			
			case DECIMAL:
				if (new MyBigDecimal(input).isOK()) {
					ret = true;
				}
				break;
				
			case LONG:
				if (!(input.contains(".") || input.contains(","))) {
					if (new MyLong(input).isOK()) {
						ret = true;
					}
				}
				break;
				
			case TEXT:
				ret = S.isFull(input);
				break;
				
			case TEXTAREA:
				ret = S.isFull(input);
				break;
				
			case YES_NO:
				ret = true;
			 	break;

			case DROPDOWN:
				ret = true;
				break;
		}

		
		return ret;
	}
	
	
	
	/**
	 * liefert den string fuer das sql-statement
	 * @author martin
	 * @date 30.10.2018
	 *
	 * @param input (bei Y-N-Feldern muss Y oder N oder leer  uebergeben werden
	 * @return
	 */
	public String getSqlPart(String input) {
		
		String ret  = "";
		
		switch (this) {
		
			case DATE:
				ret = new MyDate(input).get_cDBFormatErgebnis_4_SQLString();
				break;
			
			case DECIMAL:
				ret = new MyBigDecimal(input).getUfRounded(3);
				break;
				
			case LONG:
				ret = new MyLong(input).get_oLong().toString();
				break;
				
			case TEXT:
				ret = bibALL.MakeSql(input);
				break;
				
			case TEXTAREA:
				ret = bibALL.MakeSql(input);
				break;
				
			case YES_NO:
				ret = bibALL.MakeSql(S.NN(input,"N"));
			 	break;
			case DROPDOWN:
				ret = input;
				break;
		}

		
		return ret;
	}
	
	
	//gibt bei einem validierungsfehler eine passende meldung zur eingabe
	public MyE2_String getValidMsgParam(String input) {
		
		MyE2_String ret  = new MyE2_String("");
		
		switch (this) {
		
			case DATE:
				ret = S.ms("Der Wert: "+input+" ist kein korrektes Datum !");
				break;
			
			case DECIMAL:
				ret = S.ms("Der Wert: "+input+" ist keine korrekte Fliesskomma-Zahl!");
				break;
				
			case LONG:
				ret = S.ms("Der Wert: "+input+" ist keine korrekte Ganz-Zahl!");
				break;
				
			case TEXT:
				break;
				
			case TEXTAREA:
				break;
				
			case YES_NO:
				ret = S.ms("Der Wert: "+input+" ist keine korrekter Y-N-Wer!");
			 	break;
			 	
			case DROPDOWN:
				
				break;
		}

		
		return ret;
	}
	
	
	
	
	
	
}
