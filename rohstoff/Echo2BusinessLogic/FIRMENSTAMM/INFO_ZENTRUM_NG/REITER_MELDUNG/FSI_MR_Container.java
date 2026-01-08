/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.MELDUNG_REITER
 * @author sebastien
 * @date 27.11.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_MELDUNG;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Grid;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG._INFO_NT_NG.AI__SelectSettings_NG;

/**
 * ubersicht fur meldung
 * @author sebastien
 * @date 27.11.2018
 *
 */
public class FSI_MR_Container extends E2_Grid {

	private VEK<String> v_id_adressen = new VEK<String>();

	private AR_Grid  	infoGrid = new AR_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

	private E2_Grid 	gridSteuerbuttons= new E2_Grid();

	private static int[] iBreite = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};

	public static final String SF_FARBDIFF 	= "SF_FARBDIFF";
	public static final String SF_INSETS 	= "SF_INSETS";
	
	private LinkedHashMap<String, IF_Saveable>  	hm_Steuerpult = new LinkedHashMap<String, IF_Saveable>();

	private MyE2_ContainerEx 						container = null;

	public FSI_MR_Container(Vector<String> id_adresses) throws myException {
		super();
		this._s(1)._bo_no()._w100();
		this.v_id_adressen.clear();
		this.v_id_adressen._a(id_adresses);

		this.gridSteuerbuttons._s(3)._bo_no()._w100();
		this.gridSteuerbuttons.setOrientation(E2_Grid.ORIENTATION_VERTICAL);

		TreeMap<String, String> hm_info_typ = new TreeMap<String, String>();
		hm_info_typ.putAll(myCONST.HM_ADRESS_INFO_TYP);
		
		RB_gld gld = new RB_gld()._ins(2,0,2,0)._left_top();
		
		for(Entry<String, String> ent: hm_info_typ.entrySet()) {
			FSI_MR_CheckBox chkbox = new FSI_MR_CheckBox(S.ms(ent.getValue()), S.ms(ent.getValue()), false, ent.getKey());

			this.hm_Steuerpult.put(ent.getKey(), chkbox);

			this.gridSteuerbuttons._a(chkbox, gld);
		}

		this.gridSteuerbuttons._a(new RB_lab());

		AI__SelectSettings_NG abstand_box = new AI__SelectSettings_NG(
				ENUM_Selectfield_Meldung.SF_INSETS.get_lbl_4_user(),		
				new String[]  {"0","1","2","3","4","5","6","7","8"}, 
				"1", 
				new Extent(60),
				ENUM_Selectfield_Meldung.SF_INSETS.getTooltiptext());
		this.hm_Steuerpult.put(ENUM_Selectfield_Meldung.SF_INSETS.name(), 	abstand_box);
		this.gridSteuerbuttons._a(abstand_box.get_Comp(), gld);
		
		
		AI__SelectSettings_NG farbdiff_box = new AI__SelectSettings_NG(ENUM_Selectfield_Meldung.SF_FARBDIFF.get_lbl_4_user(),
				new String[]{"0","5","10","15","20","25","30"}, 
				"1", 
				new Extent(60),
				ENUM_Selectfield_Meldung.SF_FARBDIFF.getTooltiptext());
		
		this.hm_Steuerpult.put(ENUM_Selectfield_Meldung.SF_FARBDIFF.name(), farbdiff_box);
		this.gridSteuerbuttons._a(farbdiff_box.get_Comp(), gld);

		this.container = new MyE2_ContainerEx(infoGrid);

		//settings laden
		new FSI_MR_SaveSettings(hm_Steuerpult).RESTORE();

		for (IF_Saveable cb: this.hm_Steuerpult.values()) {
			cb.add_action(new ownActionAgentRebuild());
			cb.add_action(new ownActionAgentSaveSettings());
		}

		this._a(gridSteuerbuttons,new RB_gld()._ins(2,2,2,15));
		this._a(container);

		this.build();
	}

	private class ownActionAgentRebuild extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FSI_MR_Container.this.build();
		}
	}

	private class ownActionAgentSaveSettings extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new FSI_MR_SaveSettings(FSI_MR_Container.this.hm_Steuerpult).SAVE();
		}
	}


	public VEK<String> get_v_id_adressen() {
		return v_id_adressen;
	}

	public void build() throws myException {
		this.infoGrid.removeAll();

		FSI_MR_HauptDataBlock fi  = new FSI_MR_HauptDataBlock(this.v_id_adressen, this.hm_Steuerpult);

		fi.fill(infoGrid);
	}

	public MyE2_ContainerEx get_Container_EX() {
		return this.container;
	}
}
