package panter.gmbh.Echo2.ListAndMask.List.Selektion.Filter;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionDataValueNotFittingToField;

public class SF_orLine {
	
	public boolean      							is_saved = false;
	
	public SF__Filter   							motherContainer = null;
	public SF_andBlock  							andBlock = null;
	
	private IF_Field_ext  							if_field = null;

	private SF_sel_Field 							selfield = 			null;
	private SF_sel_compares  						selComps = 			null;
	private SF_inputComponentContainer           	containerInput = 	null;

	private MyE2_Button                             bt_save_and_next = null;
	private MyE2_Button                             bt_openLine = null;
	private MyE2_Button                             bt_del = null;
	
	
	// wird festgelegt, wenn ein save-button gedückt wird
	private String   								actual_field_term = null;
	private COMP    								actual_compare = null;
	private String   								actual_db_value = null;
	
	
	public SF_orLine(SF__Filter  p_motherContainer, SF_andBlock p_andBlock) throws myException {
		super();
		this.if_field = null;
		this.is_saved=false;
		this.motherContainer = p_motherContainer;
		this.andBlock = p_andBlock;
		
		this.selfield = 			new SF_sel_Field(this.motherContainer.get_fields(),this);
		this.selComps = 			new SF_sel_compares();
		this.containerInput = 		new SF_inputComponentContainer(this);
 
		this.bt_save_and_next = 	new ownButtonSaveOrLine();
		this.bt_openLine = 			new ownButtonOpenOrLine();
		this.bt_del = 				new ownButtonDeleteOrLine(this);
		
	}

	public String get_wert() throws myException {
		return this.containerInput.get_value();
	}


	public SF_sel_Field get_selfield() {
		return selfield;
	}

	public SF_sel_compares get_selComps() {
		return selComps;
	}

	public SF_inputComponentContainer get_containerInput() {
		return containerInput;
	}

	
	public MyE2_MessageVector check_and_save_line() throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		
		if (S.isEmpty(this.selfield.get_ActualWert()) || S.isEmpty(this.selComps.get_ActualWert())) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte füllen Sie die Zeile aus !!")));
		} else {
			this.check_input_and_generate_where_parts(mv);
			if (mv.get_bIsOK()) {
				this.is_saved=true;
				this.andBlock.fill_grid_with_components();
			} else {
				bibMSG.add_MESSAGE(mv);
			}
		}
		
		return mv;
	}
	
	
	
	private class ownButtonDeleteOrLine extends MyE2_Button {

		private SF_orLine or_line = null;
		
		public ownButtonDeleteOrLine(SF_orLine p_or_line) {
			super(E2_ResourceIcon.get_RI("delete.png"));
			this.or_line = p_or_line;
			this.add_oActionAgent(new ownActionAgentDel());
			this.setToolTipText(new MyE2_String("Die Bedingungszeile löschen").CTrans());

		}
		
		private class ownActionAgentDel extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				SF_andBlock andblock = SF_orLine.this.andBlock;
				andblock.get_orLines().remove(ownButtonDeleteOrLine.this.or_line);
				
				//jetzt nachsehen, ob der and-block leer ist, wenn ja dann aus dem and-block-vektor loeschen
				if (andblock.get_orLines().size()==0) {
					if (SF_orLine.this.motherContainer.get_ands_4_popup().size()>=2){
						SF_orLine.this.motherContainer.get_ands_4_popup().remove(andblock);
					}
				}
				
				SF_orLine.this.motherContainer.fill_gridContainer4Popup();
			}
		}
		
	}
	

	private class ownButtonSaveOrLine extends MyE2_Button {

		public ownButtonSaveOrLine() {
			super(E2_ResourceIcon.get_RI("ok.png"));
			this.add_oActionAgent(new ownActionAgentSave());
			this.setToolTipText(new MyE2_String("Die Bedingungszeile schließen, speichern und eine leere Folgezeile anhängen").CTrans());
		}
		
		private class ownActionAgentSave extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				SF_orLine oThis = SF_orLine.this;
				bibMSG.add_MESSAGE(oThis.check_and_save_line());
			}
		}
		
	}

	
	private class ownButtonOpenOrLine extends MyE2_Button {

		public ownButtonOpenOrLine() {
			super(E2_ResourceIcon.get_RI("listlabel_green_border.png"));
			this.add_oActionAgent(new ownActionAgentSave());
			this.setToolTipText(new MyE2_String("Die Bedingungszeile zur Bearbeitung wieder öffnen").CTrans());
		}
		
		private class ownActionAgentSave extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				SF_orLine oThis = SF_orLine.this;
				oThis.is_saved=false;
				oThis.andBlock.fill_grid_with_components();
			}
		}
		
	}

	
	/**
	 * prueft die eingabe auf korrektheit
	 * @param mv
	 * @return
	 * @throws myException
	 */
	public void  check_input_and_generate_where_parts(MyE2_MessageVector mv) throws myException {
		
		MyMetaFieldDEF 	md = 			this.if_field.field.generate_MetaFieldDef();
		String 			c_db_string = 	null;
		
		this.actual_compare = this.selComps.get_actualCompare();

		try {
			
			//die NULL, not null-varianten hier zuerst
			if (this.actual_compare==COMP.ISNULL) {
				this.actual_field_term = if_field.tnfn();
				this.actual_db_value = "";
				return;
			}
			if (this.actual_compare==COMP.ISNOTNULL) {
				this.actual_field_term = if_field.tnfn();
				this.actual_db_value = "";
				return;
			}
			
			
//			try {
//				//zuerst den NULL-sonderfall pruefen
//				String test = S.NN(this.containerInput.get_value()).trim();
//				String db_wert = md.get_cStringForDataBase(test, true, false);
//				if (db_wert.equals("NULL")) {
//					this.actual_compare = COMP.ISNULL;
//					this.actual_field_term = if_field.tnfn();
//					this.actual_db_value = "";
//					return;
//				}
//			} catch (Exception e) {
//				//debug info nur in entwicklung
//				if (bibALL.get_bDebugMode()) {
//					e.printStackTrace();
//				}
//			}
			
			
			if (md.get_bIsTextType() && (!md.is_boolean_single_char()) && this.actual_compare == COMP.EQ) {  				//dann * - vorne oder hinten als like umdefinieren
				String inputVal = S.NN(this.containerInput.get_value()).trim().toUpperCase();
				boolean b_start_like = false;
				boolean b_end_like = false;
				
				if (inputVal.equals("*")) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Um eine Teilzeichenfolge zu suchen, bitte z.b. *beethoven* eingeben. Ein einzelner * kann nicht interpretiert werden!")));
				} else {
				
					if (inputVal.startsWith("*")) {
						b_start_like = true;
					}
					if (inputVal.endsWith("*")) {
						b_end_like = true;
					}
					
					if (b_start_like) {
						inputVal=inputVal.substring(1, inputVal.length());
					}
					if (b_end_like) {
						inputVal=inputVal.substring(0, inputVal.length()-1);
					}
					c_db_string = md.get_cStringForDataBase(inputVal, true, false);
					
					c_db_string = c_db_string.substring(1, c_db_string.length()-1);   //hochkommas weg
					if (b_start_like) { c_db_string = "%"+c_db_string; }
					if (b_end_like) { c_db_string = c_db_string+"%"; }
					c_db_string = "'"+c_db_string+"'";
					
					if (b_start_like||b_end_like) {
						this.actual_compare = COMP.LIKE;   //dann wird aus dem COMP.EQ das COMP.LIKE
					}
					
					this.actual_field_term = SF_enum_typ.field_term(if_field);
					this.actual_db_value = SF_enum_typ.value_term(if_field, c_db_string);
				}
				
			} else if (this.actual_compare == COMP.IN  || this.actual_compare == COMP.NOT_IN) {
				//dann zuerst die werte separieren und alle mit den werten vergleichen
				String inputVal = S.NN(this.containerInput.get_value()).trim().toUpperCase();
				if (S.isEmpty(inputVal)) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Der IN-Operator benötigt eine kommaseparierte Liste zum Vergleichen ...")));
				} else {
					Vector<String> v_vals = bibALL.TrenneZeile(inputVal, ",");
					
					//jetzt die "tauglichkeit" der eingegebenen werte pruefen
					Vector<String> v_4_db = new Vector<>();
					for (String val:  v_vals) {
						v_4_db.add(SF_enum_typ.value_term(if_field, md.get_cStringForDataBase(val, true, false)));
					}
					
					this.actual_field_term = SF_enum_typ.field_term(if_field);
					this.actual_db_value = "("+bibALL.Concatenate(v_4_db, ",", "")+")";
				}
				
			} else {
				String inputVal = S.NN(this.containerInput.get_value()).trim();
				c_db_string = md.get_cStringForDataBase(inputVal, true, false);
				this.actual_field_term = SF_enum_typ.field_term(if_field);
				this.actual_db_value = SF_enum_typ.value_term(if_field,c_db_string);
			}
			
			
		} catch (myExceptionDataValueNotFittingToField e) {
			mv.add_MESSAGE(e.get_Message(this.if_field.tnfn(), this.containerInput.get_value()));
		}
		
//		DEBUG.System_println("Wert: "+this.actual_db_value);
		
	}
	

	public MyE2_Button get_bt_save() {
		return bt_save_and_next;
	}
	public MyE2_Button get_bt_openLine() {
		return bt_openLine;
	}

	public MyE2_Button get_bt_del() {
		return bt_del;
	}

	public void set_IF_field(IF_Field_ext if_field) throws myException {
		this.if_field = if_field;
		this.containerInput.set_field(if_field);
		this.selComps.set_IF_field(if_field);
	}


	
	public String get_sql_where_block() {
		String c_rueck = "";
		
		if (this.is_saved) {
			c_rueck = this.actual_field_term+" "+this.actual_compare.ausdruck()+" "+this.actual_db_value;
		}
		return c_rueck;
	}

	
	public boolean is_forgotten_to_save() throws myException {
		boolean b_rueck = false;
		if (S.isFull(this.selfield.get_ActualWert()) || S.isFull(this.selComps.get_ActualWert())) {
			if (!this.is_saved) {
				b_rueck = true;
			}
		}
		return b_rueck;
	}
	
	
	public SF_orLine clear_this_line() throws myException {
		this.selComps.set_ActiveValue("");
		this.selfield.set_ActiveValue("");
		this.set_IF_field(null);
		
		return this;
	}

	public SF_orLine get_or_Copy(SF_andBlock and_b) throws myException {
		SF_orLine line = new SF_orLine(this.motherContainer,and_b);
		line.selComps.set_oDataToView(this.selComps.get_DataToView());
		line.selfield.set_oDataToView(this.selfield.get_DataToView());
		line.selComps.set_ActiveValue(this.selComps.get_ActualWert());
		line.selfield.set_ActiveValue(this.selfield.get_ActualWert());
		line.containerInput.set_field(this.containerInput.get_if_field());
		line.actual_field_term = this.actual_field_term;
		line.actual_db_value = this.actual_db_value;
		line.actual_compare = this.actual_compare;
		line.is_saved = this.is_saved;
		line.if_field = this.if_field;
		line.containerInput.set_field(this.if_field);
		line.containerInput.set_value(this.containerInput.get_value());
		
		return line;
	}

	
	
}
