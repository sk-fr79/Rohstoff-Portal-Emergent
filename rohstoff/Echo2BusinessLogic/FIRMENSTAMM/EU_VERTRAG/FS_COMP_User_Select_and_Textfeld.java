package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_SelectField;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class FS_COMP_User_Select_and_Textfeld extends MyE2_Grid{

	private boolean isId = false;

	private RB_SelectField selectField;

	private MyE2_TextField textField;


	public FS_COMP_User_Select_and_Textfeld() throws myException {
		super(2);

		SEL sel = new SEL(USER.name1+ "||' '||" +USER.vorname+ ","+ USER.id_user)
				.FROM(_TAB.user)
				.WHERE(new vgl(USER.id_mandant, bibALL.get_ID_MANDANT()))
				.AND(new vgl(USER.ist_verwaltung,"Y"))
				.AND(new vgl(USER.aktiv,"Y"))
				.ORDERUP(USER.name1);

		selectField = new RB_SelectField(USER.id_user,sel.s(), false, false, new Extent(20));
		selectField.add_oActionAgent(new rbFeldActionAgent());
		textField = new MyE2_TextField("", 250, 1000);

		this.add(textField);
		this.add(selectField);

	}
	
	@Override
	public void show_InputStatus(boolean bInputIsOK) {
		if(!bInputIsOK){
			textField.setStyle(MyE2_Grid.STYLE_GRID_BORDER(new E2_ColorHelpBackground()));
			textField.setBackground(new E2_ColorHelpBackground());
		}
	}

	public String getVerantwortlichePerson() throws myException{
		checkTextFeldValue();
		if(isId){	
			return "ID," + bibALL.convertID2UnformattedID(this.textField.EXT().get_C_MERKMAL());
		}else if(this.textField.getText().equals("-")){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Benutzerangabe !"));
			return "";
		}
		else{
			if(!(S.isEmpty(this.textField.getText()))){
				return "NAME,"+this.textField.getText();
			}else return "";
		}
	}

	public boolean checkTextFeldValue() throws myException{
		String textFeldValue = textField.getText();
		String selectFeldValue = selectField.get_ActualView();

		this.isId=false;

		if(!S.isEmpty(selectFeldValue) && !(selectFeldValue.equals("-"))){
			if(textFeldValue.equals(selectFeldValue)){
				isId = true;
			}
		}
		return isId;
	}

	private class rbFeldActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_SelectField orbsf = (RB_SelectField) bibE2.get_LAST_ACTIONEVENT().getSource();
			textField.setText(orbsf.get_ActualView());
			if(!S.isEmpty(orbsf.get_ActualWert())){
				textField.EXT().set_C_MERKMAL(orbsf.get_ActualWert());
				isId=true;
			}else isId = false;
		}

	}

}
