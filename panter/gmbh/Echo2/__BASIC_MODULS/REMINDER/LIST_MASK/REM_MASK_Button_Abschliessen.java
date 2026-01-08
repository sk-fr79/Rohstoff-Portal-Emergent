/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK
 * @author manfred
 * @date 08.04.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import org.apache.batik.css.engine.value.svg.AlignmentBaselineManager;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldUser;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 08.04.2016
 *
 */
public class REM_MASK_Button_Abschliessen extends MyE2_Button{

	REM_MASK_ComponentMap _oComponentMap = null;
	
	private static final String sAbschluss = "Abschliessen";
	private static final String sAktivieren = "Abschluss aufheben";
	
	
	public REM_MASK_Button_Abschliessen(REM_MASK_ComponentMap oMap) {
		super(new MyString(sAbschluss),MyE2_Button.StyleImageButtonCenteredWithBlackBorder()) ;
		_oComponentMap = oMap;
		this.updateButtonText();
		this.add_oActionAgent(new actionAgentAbschliessen());
	}
	
	
	private class actionAgentAbschliessen extends XX_ActionAgent{
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent#executeAgentCode(panter.gmbh.Echo2.ActionEventTools.ExecINFO)
		 */
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			REM_MASK_Button_Abschliessen oThis = REM_MASK_Button_Abschliessen.this;
			
			boolean bIstAbgeschlossen = false;
			RB_HighLevel_SelectFieldUser oUser = (RB_HighLevel_SelectFieldUser)_oComponentMap.get__Comp(REMINDER_DEF.id_user_abgeschlossen);
			RB_TextField_old				oDatum = (RB_TextField_old) _oComponentMap.get__Comp(REMINDER_DEF.abgeschlossen_am);

			bIstAbgeschlossen = !bibALL.isEmpty(oUser.get_ActualWert());
			
			if (!bIstAbgeschlossen){
				oUser.set_ActiveValue(bibALL.get_ID_USER_FORMATTED());
				oDatum.setText(bibALL.get_cDateNOW());
			} else {
				oDatum.setText("");
				oUser.set_ActiveInhalt_or_FirstInhalt("");
			}
			
			oThis.updateButtonText();
		}
	}
	
	
	/**
	 * Setzt den Button-Text abhängig vom Status des Abschlusses
	 * @author manfred
	 * @date 12.04.2016
	 *
	 */
	public void updateButtonText() {
		RB_HighLevel_SelectFieldUser oUser;
		boolean bIstAbgeschlossen = false;
		try {
			oUser = (RB_HighLevel_SelectFieldUser)_oComponentMap.get__Comp(REMINDER_DEF.id_user_abgeschlossen);
			bIstAbgeschlossen = !bibALL.isEmpty(oUser.get_ActualWert());

			if (bIstAbgeschlossen){
				this.setText(new MyString(sAktivieren).CTrans());
			} else {
				this.setText(new MyString(sAbschluss).CTrans());
			}

		} catch (myException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
