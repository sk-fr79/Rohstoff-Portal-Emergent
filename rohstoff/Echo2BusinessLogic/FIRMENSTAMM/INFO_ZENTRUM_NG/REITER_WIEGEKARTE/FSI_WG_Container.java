/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.MELDUNG_REITER
 * @author sebastien
 * @date 27.11.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_WIEGEKARTE;

import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Grid;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG.AI__SelectSettings_NG;

/**
 * ubersicht fur meldung
 * @author sebastien
 * @date 27.11.2018
 *
 */
public class FSI_WG_Container extends E2_Grid {
	
	private VEK<String> v_id_adressen = new VEK<String>();

	private AR_Grid  	infoGrid = new AR_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

	private E2_Grid 	gridSteuerbuttons= new E2_Grid();

	private static int[] iBreite = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};

	
	private TreeMap<String, IF_Saveable>  	hm_Steuerpult = new TreeMap<String, IF_Saveable>();

	private MyE2_ContainerEx 						container = null;

	public FSI_WG_Container(Vector<String> id_adresses) throws myException {
		super();
		this._s(1)._w100();
		
		this.v_id_adressen.clear();
		this.v_id_adressen._a(id_adresses);
		
		this.gridSteuerbuttons._s(2)._w100();
		this.gridSteuerbuttons.setOrientation(E2_Grid.ORIENTATION_VERTICAL);
	
		
		RB_gld gld = new RB_gld()._ins(2,0,2,0)._left_top();
		
		AI__SelectSettings_NG abstand_box = new AI__SelectSettings_NG(
				ENUM_Selectfield_WiegeKarte.SF_INSETS.get_lbl_4_user(),		
				new String[]  {"0","1","2","3","4","5","6","7","8"}, 
				"1", 
				new Extent(60),
				ENUM_Selectfield_WiegeKarte.SF_INSETS.getTooltiptext());
		this.hm_Steuerpult.put(ENUM_Selectfield_WiegeKarte.SF_INSETS.name(), 	abstand_box);
		this.gridSteuerbuttons._a(abstand_box.get_Comp(), gld);
		
		
		AI__SelectSettings_NG farbdiff_box = new AI__SelectSettings_NG(ENUM_Selectfield_WiegeKarte.SF_FARBDIFF.get_lbl_4_user(),
				new String[]{"0","5","10","15","20","25","30"}, 
				"1", 
				new Extent(60),
				ENUM_Selectfield_WiegeKarte.SF_FARBDIFF.getTooltiptext());		
		this.hm_Steuerpult.put(ENUM_Selectfield_WiegeKarte.SF_FARBDIFF.name(), 	farbdiff_box);
		this.gridSteuerbuttons._a(farbdiff_box.get_Comp(), gld);
		
		this.container = new MyE2_ContainerEx(infoGrid);

		//settings laden
      	new FSI_WG_SaveSettings(hm_Steuerpult).RESTORE();
      	
      	for (IF_Saveable sf: this.hm_Steuerpult.values()) {
			sf.add_action(new ownActionAgentRebuild());
			sf.add_action(new ownActionAgentSaveSettings());
		}
      	
      	
		this._a(gridSteuerbuttons,new RB_gld()._ins(2,2,2,10));
		this._a(container);
		
		this.build();
	}	

	public VEK<String> get_v_id_adressen() {
		return v_id_adressen;
	}
	
	public void build() throws myException {
		this.infoGrid.removeAll();

		FSI_WG_DataBlock fi  = new FSI_WG_DataBlock(this.v_id_adressen, this.hm_Steuerpult);
      	
		fi.fill(infoGrid);
	}
	
	public MyE2_ContainerEx get_Container_EX() {
		return this.container;
	}
	
	private class ownActionAgentRebuild extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FSI_WG_Container.this.build();
		}
	}
	
	private class ownActionAgentSaveSettings extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new FSI_WG_SaveSettings(FSI_WG_Container.this.hm_Steuerpult).SAVE();
		}
	}
}
