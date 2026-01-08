/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_MessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.TOOLS.RB_labelStyle;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class PdServiceMessagePopup extends  MyE2_WindowPane {

	public static interface layoutDataProvider {
		public GridLayoutData getLayoutData();
	}
	
	public static interface labelStyleProvider  {
		public MutableStyle getLabelStyle();
	}
	
	private VEK<MyE2_MessageVector> sammlerMvs = new VEK<>();
	private E2_ContentPane    		contentPane = new E2_ContentPane(true);

	//einzeiliges grid fuer die subgrid der einzelnen bloecke
	private E2_Grid                 grid = new E2_Grid()._setSize(new Extent(100, Extent.PERCENT ));
	
	
	private layoutDataProvider  layoutProviderInfos = 	()->{ return new RB_gld()._col_back(Color.WHITE)._ins(1); };
	private layoutDataProvider  layoutProviderWarning = ()->{ return new RB_gld()._col_back(new E2_ColorHelpBackground())._ins(1); };
	private layoutDataProvider  layoutProviderAlarm = 	()->{ return new RB_gld()._col_back(new E2_ColorAlarm())._ins(1); };
	
	private labelStyleProvider  styleProviderInfos = 	()->{ return new RB_labelStyle(); };
	private labelStyleProvider  styleProviderWarning =  ()->{ return new RB_labelStyle();  };
	private labelStyleProvider  styleProviderAlarm = 	()->{ return new RB_labelStyle(); };
	
	public PdServiceMessagePopup() {
		super(true);
		this.add(contentPane);
		this.contentPane.add(this.grid);
	}


	
	/**
	 * adds messageVektor to showable mv and keeps its content
	 * @param mv
	 * @return itself
	 */
	public PdServiceMessagePopup assignMessages(MyE2_MessageVector mv) {
		this.sammlerMvs._a(mv);
		return this;
	}


	/**
	 * adds messageVektor to showable mv and clears it afterwards
	 * @param mv
	 * @return itself
	 */
	public PdServiceMessagePopup assignAndClearMessages(MyE2_MessageVector mv) {
		this.assignMessages(mv);
		mv.clear();
		return this;
	}

	
	public PdServiceMessagePopup showPopup() {
		this.grid._clear()._setSize(new Extent(100, Extent.PERCENT));
		
		for (MyE2_MessageVector mv: this.sammlerMvs) {
			this.grid._a(this.renderGrid(mv), new RB_gld()._ins(1));
		}
		
		bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(this);
		
		return this;
	}


	/**
	 * 
	 * @param mv
	 * @return s grid og one messageblock
	 */
	private E2_Grid renderGrid(MyE2_MessageVector mv) {
		E2_Grid g = new E2_Grid()._setSize(new Extent(100, Extent.PERCENT));
		
		for (MyE2_Message m: mv) {
			g._a(this.renderMessage(m), new RB_gld()._ins(1));
		}
		return g;
	}

	/**
	 * 
	 * @param m
	 * @return s grid of one message (1 row)
	 */
	private E2_Grid renderMessage(MyE2_Message m) {
		E2_Grid g = new E2_Grid();
		
		GridLayoutData glActive = this.layoutProviderInfos.getLayoutData();
		if (m.get_iType()==MyE2_Message.TYP_WARNING) {
			 glActive = this.layoutProviderWarning.getLayoutData();
		}
		if (m.get_iType()==MyE2_Message.TYP_ALARM) {
			 glActive = this.layoutProviderAlarm.getLayoutData();
		}
		
		MutableStyle  styleActive = this.styleProviderInfos.getLabelStyle();
		if (m.get_iType()==MyE2_Message.TYP_WARNING) {
			styleActive = this.styleProviderWarning.getLabelStyle();
		}
		if (m.get_iType()==MyE2_Message.TYP_ALARM) {
			styleActive = this.styleProviderAlarm.getLabelStyle();
		}
		
		
		if (m instanceof MyE2_BASIC_MessageWithAddonComponent) {
			g._setSize(new Extent(90, Extent.PERCENT), new Extent(10, Extent.PERCENT));
			g._a(new RB_lab()._t(m.get_cMessage().CTrans())._set_style(styleActive), glActive);
			g._a(((MyE2_BASIC_MessageWithAddonComponent)m).getAddonComponent(), glActive);
		} else {
			g._setSize(new Extent(100, Extent.PERCENT));
			g._a(new RB_lab()._t(m.get_cMessage().CTrans())._set_style(styleActive), glActive);
		}
		
		return g;
	}

	public layoutDataProvider getLayoutProviderInfos() {
		return layoutProviderInfos;
	}



	public layoutDataProvider getLayoutProviderWarning() {
		return layoutProviderWarning;
	}



	public layoutDataProvider getLayoutProviderAlarm() {
		return layoutProviderAlarm;
	}



	public labelStyleProvider getStyleProviderInfos() {
		return styleProviderInfos;
	}



	public labelStyleProvider getStyleProviderWarning() {
		return styleProviderWarning;
	}



	public labelStyleProvider getStyleProviderAlarm() {
		return styleProviderAlarm;
	}



	public void setLayoutProviderInfos(layoutDataProvider layoutProviderInfos) {
		this.layoutProviderInfos = layoutProviderInfos;
	}



	public void setLayoutProviderWarning(layoutDataProvider layoutProviderWarning) {
		this.layoutProviderWarning = layoutProviderWarning;
	}



	public void setLayoutProviderAlarm(layoutDataProvider layoutProviderAlarm) {
		this.layoutProviderAlarm = layoutProviderAlarm;
	}



	public void setStyleProviderInfos(labelStyleProvider styleProviderInfos) {
		this.styleProviderInfos = styleProviderInfos;
	}



	public void setStyleProviderWarning(labelStyleProvider styleProviderWarning) {
		this.styleProviderWarning = styleProviderWarning;
	}



	public void setStyleProviderAlarm(labelStyleProvider styleProviderAlarm) {
		this.styleProviderAlarm = styleProviderAlarm;
	}

	
	
	
	
	
}
