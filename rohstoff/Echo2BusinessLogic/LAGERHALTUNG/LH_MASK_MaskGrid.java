
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class LH_MASK_MaskGrid extends E2_Grid {

	private RB_TransportHashMap   m_tpHashMap = null;

	private MyE2_TabbedPane  ta  = new MyE2_TabbedPane(600);

	private VEK<E2_Grid>   fieldContainers = 	new VEK<E2_Grid>();
	private VEK<MyString>  tabText = 			new VEK<MyString>();

	public LH_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();

		int iWidthComplete = LH_CONST.LH_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
				LH_CONST.LH_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
		this._setSize(iWidthComplete)._bo_no();


		this._setSize(800)._bo_no();

		this.m_tpHashMap = p_tpHashMap;
		this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);

		LH_MASK_ComponentMap  map_box = (LH_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(_TAB.lager_box.rb_km());

		E2_Grid g2 = fieldContainers._ar(new E2_Grid());
		tabText._a(S.ms("Paletten"));
		g2._setSize(760)._bo_no()._w100();
		g2._a(map_box.getComp(new LH_KEY_ContainerInlay()), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

		E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( LH_CONST.LH_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
				LH_CONST.LH_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        

		tabText._a(S.ms("Lagerbox"));

		g1._a(new RB_lab("Box Id") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
		._a(map_box.getComp(LAGER_BOX.id_lager_box), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

		g1._a(new RB_lab("Box Nummer") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
		._a(map_box.getComp(LAGER_BOX.boxnummer), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

		g1._a(new RB_lab("Default Box") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
		._a(map_box.getComp(LAGER_BOX.is_default_box), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
		
		g1._a(new RB_lab("Box Beschreibung") ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
		._a(map_box.getComp(LAGER_BOX.beschreibung), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));


		this.renderMask();

		this.resize(LH_CONST.LH_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
				LH_CONST.LH_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
	}


	private void renderMask() throws myException {
		if (this.fieldContainers.size()==1) {
			this._a(this.fieldContainers.get(0));
		} else {
			
			if(this.m_tpHashMap.getLastMaskLoadStatus()!=MASK_STATUS.NEW) {
				this.ta.add_Tabb(S.ms("Paletten"), this.fieldContainers.get(0));
			}
			this.ta.add_Tabb(S.ms("Lagerbox"), this.fieldContainers.get(1));

			this._a(this.ta);
		}
	}

	public void resize(int width, int height) {
		this.ta.setWidth(new Extent(width-60));
		this.ta.setHeight(new Extent(height-170));
	}

}


