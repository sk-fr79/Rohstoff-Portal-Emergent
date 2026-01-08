package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;

/**
 * button, um eine neu maske zu oeffnen, in der die werte eines ausgewaehlten satzes drinstehen
 * @author martin
 *
 */
public class WK_RB_bt_Print extends E2_Button{

	
	public WK_RB_bt_Print(RB_ComponentMap compMap) {
		super();
		_setShapeStandardTextButton()._image("printer.png", true);
		_txt_trans(new MyE2_String("Drucken").CTrans());
		
		this.setBreak4PopupController(new E2_Break4PopupController()._registerBreak(new Break4Print(compMap)));
	}
	
	
	private class Break4Print extends E2_Break4Popup{

		private RB_ComponentMap _compMap = null;
		private MyE2_String   		s_windowTitle = 	S.ms("Wiegekarte");
		private MyE2_String   		s_title = 			S.ms("Wiegekarte drucken");

		
		private VEK<MyE2_String>	v_meldungsblock = 	new VEK<MyE2_String>()._a(S.ms("Wiegekarte sicher abschließen? Danach ist keine Änderung mehr möglich!"));
		
		public Break4Print(RB_ComponentMap compMap) {
			_compMap = compMap;
			setTitle(s_windowTitle);
			this._setHeight(200);
			this._setWidth(500);
			getOwnSaveButton()._t(S.ms("Drucken und abschließen"))._s_BorderText();
			getOwnCloseButton()._t(S.ms("Abbrechen"))._s_BorderText();;
		}

		
		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup#generatePopUpContainer()
		 */
		@Override
		public E2_BasicModuleContainer generatePopUpContainer() throws myException {
			return this.fillContainer(new E2_BasicModuleContainer());
		}

		
		
		public E2_BasicModuleContainer fillContainer(E2_BasicModuleContainer container) {
			container.set_cADDON_TO_CLASSNAME(S.NN(this.getBreak4PopupController().getClass().getName()));  //damit die groesse individuell ist

			E2_Grid g = new E2_Grid()._setSize(this.getPopupWidth()-10)._a(new RB_lab()._t(this.s_title)._b()._fsa(2), new RB_gld()._ins(2,4,4,2));
			for (MyE2_String s: this.v_meldungsblock) {
				g._a(new RB_lab()._t(s), new RB_gld()._ins(2,2,2,2));
			}
			
			E2_Grid gButtons = new E2_Grid()._setSize(this.getPopupWidth()/2,this.getPopupWidth()/2);
			gButtons._a(this.getOwnSaveButton(),new RB_gld()._center_mid()._ins(2,2,5,2))._a(this.getOwnCloseButton(),new RB_gld()._center_mid()._ins(5,2,2,2));
			
			g._a(gButtons, new RB_gld()._ins(2, 10, 2, 2));
			
			container.add(g,E2_INSETS.I(10,5,10,5));
			return container;
		}
		
		
		
		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup#check4break(panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
		 */
		@Override
		protected boolean check4break(MyE2_MessageVector mv) throws myException {
			MyE2_MessageVector _mv = new MyE2_MessageVector();
			boolean popupIsLast =(this.getBreak4PopupController().getHmCounter().get(this)==0);
			
			// prüfen, ob die WK schon mal gedruckt wurde...
			RB_MaskController mc = new RB_MaskController(_compMap);
			String gedruckt = mc.get_dbVal(RecDOWiegekarte.key, WIEGEKARTE.gedruckt_am);
			return S.isEmpty(gedruckt) && popupIsLast ;
			
		}
		
	}
	
}
