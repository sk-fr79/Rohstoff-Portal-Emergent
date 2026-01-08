/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde
 * @author manfred
 * @date 25.06.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;

import java.math.BigDecimal;

import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_GEBINDE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 25.06.2020
 *
 */
public class WK_RB_CHILD_ABZUG_GEB_MaskController extends RB_MaskController {

	public WK_RB_CHILD_ABZUG_GEB_MaskController(IF_RB_Component p_component) throws myException {
		super(p_component);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_DataobjectsCollector p_dataObjectsCollector) throws myException {
		super(p_dataObjectsCollector);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(IF_RB_Component p_component, MyE2_MessageVector mvForErrors) {
		super(p_component, mvForErrors);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_ComponentMap p_componentMap, MyE2_MessageVector mvForErrors) {
		super(p_componentMap, mvForErrors);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_ComponentMapCollector p_componentMapCollector,
			MyE2_MessageVector mvForErrors) {
		super(p_componentMapCollector, mvForErrors);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_DataobjectsCollector p_dataObjectsCollector,
			MyE2_MessageVector mvForErrors) {
		super(p_dataObjectsCollector, mvForErrors);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(IF_RB_Component p_component, boolean immediateBuild)
			throws myException {
		super(p_component, immediateBuild);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_ComponentMap p_componentMap, boolean immediateBuild)
			throws myException {
		super(p_componentMap, immediateBuild);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_ComponentMapCollector p_componentMapCollector,
			boolean immediateBuild) throws myException {
		super(p_componentMapCollector, immediateBuild);
	}

	public WK_RB_CHILD_ABZUG_GEB_MaskController(RB_DataobjectsCollector p_dataObjectsCollector, boolean immediateBuild)
			throws myException {
		super(p_dataObjectsCollector, immediateBuild);
	}

	
	public void __loadGebindeData() throws myException {
		RB_TextField tfGebinde 	= (RB_TextField) this.getRbComp(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingMaskKey(),WIEGEKARTE_ABZUG_GEB.gebinde, bibMSG.MV());
		RB_TextField tfGewicht 	= (RB_TextField) this.getRbComp(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingMaskKey(),WIEGEKARTE_ABZUG_GEB.gewicht_einzel, bibMSG.MV());
		RB_TextField tfMenge 	= (RB_TextField) this.getRbComp(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingMaskKey(),WIEGEKARTE_ABZUG_GEB.menge, bibMSG.MV());
		
		RB_selField selGebinde = (RB_selField)   this.getRbComp(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingMaskKey(),WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde, bibMSG.MV());
		
		Long idGebinde = new MyLong(selGebinde.getActualDbVal()).getLong();

		if(idGebinde != null) {
			Rec22  r = new Rec22(_TAB.wiegekarte_gebinde)._fill_id(idGebinde);
			tfGebinde.setText(r.getUfs(WIEGEKARTE_GEBINDE.bezeichnung));
			tfGewicht.setText( r.getFs(WIEGEKARTE_GEBINDE.gewicht,BigDecimal.ZERO.toString()) );
			tfMenge.setText("");
			tfGewicht._setFocus();
//			ApplicationInstance.getActive().setFocusedComponent((Component) tfGewicht);
			
		} else {
			
			tfGebinde.setText("");
			tfGewicht.setText("");
			tfMenge.setText("");
		}
	}
	
	
	public MyE2_MessageVector __checkMenge() throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		RB_selField selGebinde = (RB_selField) this.getRbComp(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingMaskKey(), WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde, bibMSG.MV() );
		if ( S.isEmpty(selGebinde.getActualDbVal()) ) {
			selGebinde.mark_MustField();
			mv._add(new MyE2_Alarm_Message(new MyE2_String("Ein Gebinde muss ausgewählt sein.")));
		}
				
		
		RB_TextField tfMenge 	= (RB_TextField) this.getRbComp(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingMaskKey(),WIEGEKARTE_ABZUG_GEB.menge, bibMSG.MV());
		if (S.isEmpty(tfMenge.getText())) {
			tfMenge._col_back_yellow();
			tfMenge._setFocus();
			mv._add(new MyE2_Alarm_Message(new MyE2_String("Menge darf nicht leer sein.")));
		} else {
			tfMenge._col_back_white();
		}
		
		RB_TextField tfGewicht 	= (RB_TextField) this.getRbComp(WK_RB_CHILD_ABZUG_GEB_CONST.getLeadingMaskKey(),WIEGEKARTE_ABZUG_GEB.gewicht_einzel, bibMSG.MV());
		if (S.isEmpty(tfGewicht.getText())) {
			tfGewicht._col_back_yellow();
			tfGewicht._setFocus();
			mv._add(new MyE2_Alarm_Message(new MyE2_String("Gewicht darf nicht leer sein.")));
		} else {
			tfGewicht._col_back_white();
		}
		
		return mv;
	}
	
}
