package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MAILPROFILE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_K_dummy;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class EMS__PopUpContainer extends RB_ModuleContainerMASK {

	private E2_NavigationList  NaviList = null;
	
	public EMS__PopUpContainer(E2_NavigationList  naviList) throws myException {
		super();
		
		this.NaviList = naviList;
		
		EMS_MaskContainer  maskContainer = new EMS_MaskContainer();
		
		this.registerComponent(new RB_K_dummy("-"), maskContainer);
		
		MyE2_Grid  gridHelp = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		E2_MaskFiller  filler = new E2_MaskFiller(maskContainer.get(new RB_KM(_TAB.email_send_schablone)), null, null);
		
		/**
 		this.rb_register(	new RB_KF(_DB.EMAIL_SEND_SCHABLONE$ID_EMAIL_SEND_SCHABLONE),
		this.rb_register(	new RB_KF(_DB.EMAIL_SEND_SCHABLONE$KENNUNG_MAILVERSAND),
		this.rb_register(	new RB_KF(_DB.EMAIL_SEND_SCHABLONE$ABSENDER),
		this.rb_register(	new RB_KF(_DB.EMAIL_SEND_SCHABLONE$BETREFF),
		this.rb_register(	new RB_KF(_DB.EMAIL_SEND_SCHABLONE$TEXT),
		 */
		
		
		filler.add_Line(gridHelp, new MyE2_String("ID"), 1, 		EMAIL_SEND_SCHABLONE.id_email_send_schablone.fn(), 1);
//		filler.add_Line(gridHelp, new MyE2_String("ID_Mandant"), 1, _DB.EMAIL_SEND_SCHABLONE$ID_MANDANT, 1);
		filler.add_Line(gridHelp, new MyE2_String("Kennung"), 1, 	EMAIL_SEND_SCHABLONE.kennung_mailversand.fn(), 1);
		filler.add_Line(gridHelp, new MyE2_String("Versandtyp"), 1, EMAIL_SEND_SCHABLONE.send_type.fn(), 1);
		filler.add_Line(gridHelp, new MyE2_String("Absender"), 1, 	EMAIL_SEND_SCHABLONE.absender.fn(), 1);
		filler.add_Line(gridHelp, new MyE2_String("Betreff"), 1, 	EMAIL_SEND_SCHABLONE.betreff.fn(), 1);
		filler.add_Line(gridHelp, new MyE2_String("Text"), 1, 		EMAIL_SEND_SCHABLONE.text.fn(), 1);
		
		this.rb_INIT(E2_MODULNAME_ENUM.MODUL.MASK_EMAIL_SCHABLONEN, gridHelp, true);
		
		this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(EMS__PopUpContainer.this) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				EMS__PopUpContainer.this.NaviList._REBUILD_ACTUAL_SITE("");
			}
		});
	}

}
