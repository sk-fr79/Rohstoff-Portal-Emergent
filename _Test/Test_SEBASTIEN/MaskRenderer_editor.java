package _Test.Test_SEBASTIEN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class MaskRenderer_editor extends E2_Grid {
	
	
	private E2_Button new_mask_bt 		= new E2_Button();
	
	private E2_Grid entwicklung_grid  	= new E2_Grid();
	
	private E2_Button add_new_line 		= new E2_Button();
	private E2_Button edit_new_line 	= new E2_Button();
	private E2_Button delete_line_bt 	= new E2_Button();
	
	private Vector<String> 			v_mask_definition;
	private Vector<Vector<String>>	v_cell_definition;
	
	public MaskRenderer_editor() throws myException {
		super();
		
		this._a(init_bedienPanel(), new RB_gld()._ins(2)._span(2));
	}
	
	public E2_Grid init_bedienPanel() throws myException {
		E2_Grid bedien_panel= new E2_Grid();
		
		this.new_mask_bt._image(E2_ResourceIcon.get_RI("new.png"))._aaa(new add_new_mask());
		this.new_mask_bt._ttt("Neuer Maske");
		
		bedien_panel._a(this.new_mask_bt, new RB_gld()._ins(2));
		
		return bedien_panel;
	}
	
	private class add_new_mask extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new create_new_mask_container().CREATE_AND_SHOW_POPUPWINDOW(new Extent(450), new Extent(300), new MyE2_String("Mask definition..."));
		}
	}
	
	private class create_new_mask_container extends E2_BasicModuleContainer{
		public create_new_mask_container() {
			super();
			
			E2_Grid grd = new E2_Grid()._setSize(125,200)._bo_no();
			grd._a("Tablename"			, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a(new RB_TextField(200)	, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a("Maskname"				, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a(new RB_TextField(200)	, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a("Maskname (lang)" 		, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a(new RB_TextField(200)	, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a("Spalte"				, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a(new RB_TextField(200)	, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a("Spalte breite"			, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a(new RB_TextField(200)	, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a("Links offset"			, new RB_gld()._ins(2,2,2,5)._left_mid())
			._a(new RB_TextField(200)	, new RB_gld()._ins(2,2,2,5)._left_mid())
					
			._a(new E2_Grid()
					._a(new E2_Button()._t("Speichern")._aaa(new mask_def_speichern_action())._style(E2_Button.StyleTextButton()), new RB_gld()._ins(0,10,5,2))
					._a(new E2_Button()._t("Abbrechen")._aaa(new mask_def_abbrechen_action())._style(E2_Button.StyleTextButton()), new RB_gld()._ins(5,10,0,2))
					, new RB_gld()._span(2)._ins(2)._right_bottom());
			
			this.add(grd);
		}
		
		private class mask_def_speichern_action extends XX_ActionAgent{

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
			}
			
		}
		
		private class mask_def_abbrechen_action extends XX_ActionAgent{

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Maske definition wurde abgebrochen ...")));
				create_new_mask_container.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
			
		}
	}
	
	
}
