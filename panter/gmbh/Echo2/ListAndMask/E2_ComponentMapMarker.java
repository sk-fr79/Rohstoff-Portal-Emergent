/**
 * panter.gmbh.Echo2.ListAndMask
 * @author martin
 * @date 19.02.2019
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import java.util.Collection;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.layout.ColumnLayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 19.02.2019
 *
 */
public class E2_ComponentMapMarker {
	
	private E2_ComponentMAP  				map = 		null;
	private HMAP<Component,AttributeStore> 	storeMap = 	null;
	

	public E2_ComponentMapMarker(E2_ComponentMAP p_map) {
		super();
		this.map = p_map;
	}



	public void formatComponentMap()  {
		VEK<Component> v = this.getAllActiveComponents();
		this.updateStoreMap(v);
		//jetzt sollte fuer jede in v befindliche komponente ein AttributeStore in der storemap sein
		this.innerFormat(v);
	}
	

	/**
	 * kann von aussen benutzt werden, um formatierungen durchzufuehren
	 */
	public void resetAttributesInMap() {
		VEK<Component> v = this.getAllActiveComponents();
		this.updateStoreMap(v);
		for (Component c: v) {
			this.resetAttibute(c);
		}
	}

	
	
	/**
	 * kann in speziellen faellen UEBERSCHRIEBEN werden,
	 * der standard nutzt die in der navilist hinterlegten forecolor und font-attribute
	 * und ersetzt damit die standard-routinen
	 * @author martin
	 * @throws myException 
	 * @date 20.02.2019
	 *
	 */
	protected void innerFormat(Collection<Component> v)  {
		E2_NavigationList naviList = this.map.get_oNavigationList_This_Belongs_to();
		if (naviList != null) {
			Color highlight = 	naviList.get_oColorForeMarkSelectedRows();
			Font  font =  		naviList.get_oFontForeMarkSelectedRows();
			
			this.resetAttributesInMap(v);
			
			if (this.map.isChecked()) {
				this.setFontInMap(v, font);
				this.setTextColorInMap(v,highlight);
			}
			
		}
	}
	
	
	
	protected void resetAttributesInMap(Collection<Component> v) {
		for (Component c: v) {
			this.resetAttibute(c);
		}
	}
	

	
	protected void resetAttibute(Component c) {
		if (c!=null) {
			try {
				AttributeStore store = this.storeMap.get(c);
				if (store.getFont()!=null) {
					c.setFont(store.getFont()); 
				} else {
					c.setFont(new E2_FontPlain());
				}
				if (store.getTextColor()!=null) 	{
					c.setForeground(store.getTextColor()); 
				}
				c.setLayoutData(store.getlayoutData());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void setTextColorInMap(Collection<Component> v, Color textColor) {
		if (textColor!=null) {
			for (Component c: v) {
				c.setForeground(textColor);
			}
		}
	}

	
	public void setTextColorInMapDifferent(Collection<Component> v, int diffR, int diffG, int diffB) {
		for (Component c: v) {
			
			Color col = c.getForeground();
			if (col == null) {
				col = new Color(0,0,0);   //dann  schwarz als ausgang
			}
			int ir = col.getRed()+diffR;
			int ig = col.getGreen()+diffG;
			int ib = col.getBlue()+diffB;
			
			ir = ir<0?0:ir;
			ig = ig<0?0:ig;
			ib = ib<0?0:ib;

			ir = ir>254?254:ir;
			ig = ig>254?254:ig;
			ib = ib>254?254:ib;
			c.setForeground(new Color(ir,ig,ib));
		}
	}
	
	
	public void setFontInMap(Collection<Component>v, Font font) {
		if (font != null) {
			for (Component c: v) {
				c.setFont(font);
			}
		}
	}

	
	
	public void setLayoutBackgroundColorInMap(Collection<Component> v, Color col) {
		for (Component c: v) {
			LayoutData ld = c.getLayoutData();
			if (ld!=null) {
				if (ld instanceof GridLayoutData) {
					GridLayoutData gld = (GridLayoutData)ld;
					GridLayoutData gld2 = LayoutDataFactory.get_GL_Copy(gld, col);
					c.setLayoutData(gld2);
				} else if (ld instanceof ColumnLayoutData) {
					ColumnLayoutData cld = (ColumnLayoutData)ld;
					ColumnLayoutData cld2 = LayoutDataFactory.get_CL_Copy(cld, col);
					c.setLayoutData(cld2);
				}
			}
		}
	}


	

	/*
	 * sammelt alle momentan vorhandenen komponenten ein (sowohl alle direkten als auch die indirekten
	 */
	protected VEK<Component> getAllActiveComponents() {
		VEK<Component> v = new VEK<>();
		for (MyE2IF__Component comp: map.values()) {
			if (comp != null && comp.EXT()!=null && comp instanceof Component) {
				Component c =  (Component)comp;
				Component c2 = this.translateIndirect(c);
				this.findComponent(v, c);
				if (c2 != c) {
					this.findComponent(v, c);
				}
			}
		}
		return v;
	}
	
	
	/**
	 * bringt die aktuelle storemap auf den neuesten stand, loescht alte componenten-referenzen und legt fehlenden an
	 * (falls durch irgend einen vorgang die ComponentMap veraendert wurde
	 * @author martin
	 * @date 22.02.2019
	 *
	 * @param v
	 */
	protected void updateStoreMap(Collection<Component> v) {
		if (this.storeMap==null) {
			this.storeMap = new HMAP<>();
		}
		HMAP<Component,AttributeStore> zwischenspeicher = new HMAP<>();
		
		for (Component c: v) {
			if (this.storeMap.containsKey(c)) {
				zwischenspeicher.put(c, this.storeMap.get(c));
			} else {
				zwischenspeicher.put(c,  new AttributeStore(c));
			}
		}
		this.storeMap._clear();
		this.storeMap._putAll(zwischenspeicher);
		zwischenspeicher.clear();
	}
	
	
	
	
	
	public void setFontItalicInMap(VEK<Component> v, boolean italic) {
		for (Component c: v) {
			//falls kein font gesetzt ist, den standardfont setzen
			if (c.getFont()==null) {
				c.setFont(new E2_FontPlain());
			}
			c.setFont(bibFONT.getItalicFont(c.getFont(), italic));
		}
	}
	
	
	public void setFontBoldInMap(VEK<Component> v, boolean bold) {
		for (Component c: v) {
			//falls kein font gesetzt ist, den standardfont setzen
			if (c.getFont()==null) {
				c.setFont(new E2_FontPlain());
			}
			c.setFont(bibFONT.getBoldFont(c.getFont(), bold));
		}
	}
	
	
	
	public void setFontLineThroughInMap(VEK<Component> v, boolean lineThrough) {
		for (Component c: v) {
			//falls kein font gesetzt ist, den standardfont setzen
			if (c.getFont()==null) {
				c.setFont(new E2_FontPlain());
			}
			c.setFont(bibFONT.getLineThroughFont(c.getFont(), lineThrough));
		}
	}
	
	
	
	
	
	protected void findComponent(VEK<Component> sammler, Component c) {
		if (c==null) {
			return;
		}
		
		c = this.translateIndirect(c);
		
		if (c instanceof Grid) {
			sammler._addIfNotIn(c);
			for (Component ci : ((Grid)c).getComponents()) {
				findComponent(sammler,ci);
			}
		} else if (c instanceof Column) {
			sammler._addIfNotIn(c);
			for (Component ci : ((Column)c).getComponents()) {
				findComponent(sammler,ci);
			}

		} else if (c instanceof Row) {
			sammler._addIfNotIn(c);
			for (Component ci : ((Row)c).getComponents()) {
				findComponent(sammler,ci);
			}
		} else {
			sammler._addIfNotIn(c);
		}
	}

	
	
	
	/**
	 * object speichert beim ersten aufruf die aenderbaren attribute
	 * der komponenten, die in der liste vorkommen (rekursiv, alle gerenderten objekte)
	 * @author martin
	 * @date 20.02.2019
	 *
	 */
	public class AttributeStore extends Object {
		private Font font = null;
		private Color textColor = null;
		private LayoutData layoutData = null;
		
		public AttributeStore(Component c) {
			this.layoutData = 	c.getLayoutData();
			this.textColor =	c.getForeground();
			this.font = 		c.getFont();
			
			if (this.textColor==null) {
				this.textColor=Color.BLACK;
			}
		}
		
		
		public AttributeStore(Font p_font, Color p_textColor, LayoutData p_layoutData) {
			super();
			this.font = p_font;
			this.textColor = p_textColor;
			this.layoutData = p_layoutData;
		}

		public Font getFont() {
			return font;
		}

		public Color getTextColor() {
			return textColor;
		}

		public LayoutData getlayoutData() {
			return layoutData;
		}
	}

	
	
	private Component translateIndirect(Component c) {
		Component c_rueck = c;
		if (c instanceof MyE2IF__Component && ((MyE2IF__Component)c).EXT().get_real_rendered_component_in_list()!=null) {
			c_rueck = ((MyE2IF__Component)c).EXT().get_real_rendered_component_in_list();
		}
		return c_rueck;
	}
	


	public E2_ComponentMAP getMap() {
		return map;
	}



	public HMAP<Component, AttributeStore> getStoreMap() {
		return storeMap;
	}
	
	

	/**
	 * beispiel fuer einen actionAgentsAfterListGeneration, damit der formatierer immer angewendet wird   
	 * @author martin
	 * @date 25.02.2019
	 *
	 */
	public static class FormatingAgent extends XX_ActionAgent {
		
		private E2_NavigationList naviList = null;
		
		
		public FormatingAgent(E2_NavigationList p_naviList) {
			super();
			this.naviList = p_naviList;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			naviList._applyMarker();
		}
	}
	
	

}
