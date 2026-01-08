package panter.gmbh.Echo2.RB.IF;

import java.lang.reflect.Method;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public interface IF_Formatter  {
	public void mark_Neutral() throws myException;
	
//	public void mark_MustField() throws myException;
//  public void mark_Disabled() throws myException;
//	public void mark_FalseInput() throws myException;
	public void set_Alignment(Alignment align)  throws myException;
	
	public void setFocusTraversalParticipant(boolean bGetsFocus);
	
	
	public default void mark_NotMustField() throws myException {
		if (this instanceof IF__Reflections) {
			IF__Reflections ref = (IF__Reflections)this;
			Method m =ref.CheckMethod(this.getClass().getMethods(), "setBorder");
			if (m!=null) {
				try {
					m.invoke(this, (Border)null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	public default void mark_MustField() throws myException {
		if (this instanceof IF__Reflections) {
			IF__Reflections ref = (IF__Reflections)this;
			Method m =ref.CheckMethod(this.getClass().getMethods(), "setBorder");
			if (m!=null) {
				try {
					m.invoke(this, new Border(1, Color.RED, Border.STYLE_SOLID));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	
	public default void mark_Enabled() throws myException {
		if (this instanceof IF__Reflections) {
			IF__Reflections ref = (IF__Reflections)this;
			Method m =ref.CheckMethod(this.getClass().getMethods(), "setBackground");
			Method m2 =ref.CheckMethod(this.getClass().getMethods(), "setForeground");
			if (m!=null && m2!=null) {
				try {
					m.invoke(this, new E2_ColorEditBackground());
					m2.invoke(this, Color.BLACK);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public default void mark_Disabled() throws myException {
		if (this instanceof IF__Reflections) {
			IF__Reflections ref = (IF__Reflections)this;
			Method m =ref.CheckMethod(this.getClass().getMethods(), "setBackground");
			Method m2 =ref.CheckMethod(this.getClass().getMethods(), "setForeground");
			if (m!=null && m2!=null) {
				try {
					m.invoke(this, new E2_ColorGray(230));
					m2.invoke(this, new E2_ColorGray(100));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	public default void mark_CorrectInput() throws myException {
		if (this instanceof IF__Reflections) {
			IF__Reflections ref = (IF__Reflections)this;
			Method m =ref.CheckMethod(this.getClass().getMethods(), "setBackground");
			if (m!=null) {
				try {
					m.invoke(this, new E2_ColorEditBackground());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public default void mark_FalseInput() throws myException {
		if (this instanceof IF__Reflections) {
			IF__Reflections ref = (IF__Reflections)this;
			Method m =ref.CheckMethod(this.getClass().getMethods(), "setBackground");
			if (m!=null) {
				try {
					m.invoke(this, new E2_ColorHelpBackground());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
	
	public default void setHighlightColorInGrid(Color background,Class containerClass) {
		
		if (this instanceof Component&& this instanceof MyE2IF__Component) {
			
			Component componentToHighlight = findMotherInContainerClass((Component)this,containerClass);
			
			LayoutData layout = componentToHighlight.getLayoutData();
			if (layout!=null && layout instanceof GridLayoutData) {
				MyE2EXT__Component ext = ((MyE2IF__Component)componentToHighlight).EXT();
				GridLayoutData gld = (GridLayoutData)layout;
				
				if (ext != null) {
					GridLayoutData gldCopy = LayoutDataFactory.get_GL_Copy(gld);
					gldCopy.setBackground(background);
					
					ext.setColorBackOfLayoutDataBeforeHighlight(gld.getBackground());
					ext.setHighlightActive(true);
					componentToHighlight.setLayoutData(gldCopy);
					
				}
			}
		}
		
	}
	
	
	public default void setNormalColorInGrid(Class containerClass) {
		
		if (this instanceof Component&& this instanceof MyE2IF__Component) {
			
			Component componentToHighlight = findMotherInContainerClass((Component)this,containerClass);
			
			LayoutData layout = componentToHighlight.getLayoutData();
			if (layout!=null && layout instanceof GridLayoutData) {
				MyE2EXT__Component ext = ((MyE2IF__Component)componentToHighlight).EXT();
				GridLayoutData gld = (GridLayoutData)layout;
				
				if (ext != null && ext.isHighlightActive()) {
					GridLayoutData gldCopy = LayoutDataFactory.get_GL_Copy(gld);
					Color oldColor = ext.getColorBackOfLayoutDataBeforeHighlight();
					gldCopy.setBackground(oldColor);

					ext.setColorBackOfLayoutDataBeforeHighlight(null);
					ext.setHighlightActive(false);
					componentToHighlight.setLayoutData(gldCopy);

				}
			}
		}
		
	}
	
	
	public default Component findMotherInContainerClass(Component starter, Class containerClass) {
		
		Component compToHighlight = starter; 
		
		if (containerClass!=null) {
			//bis zu vier ebenen zurueck
			VEK<Component> vek = new VEK<>();
			
			Component motherOne = starter.getParent();
			Component motherTwo = motherOne != null ? motherOne.getParent(): null;
			Component motherThree = motherTwo != null ? motherTwo.getParent(): null;
			Component motherFour = motherThree != null ? motherThree.getParent(): null;
	
			vek._addValidated((e)->{return e!=null;}, motherOne);
			vek._addValidated((e)->{return e!=null;}, motherTwo);
			vek._addValidated((e)->{return e!=null;}, motherThree);
			vek._addValidated((e)->{return e!=null;}, motherFour);
			
			//dann ist die komponente vor der containerclass-type die gesuchte, zu highlightende component
			for (int i=0;i<vek.size();i++) {
				if (containerClass.isInstance(vek.get(i))) {
					if (i>0) {     //sonst ist der erste gefundene bereits der container und das originalobjekt zu highlighten
						compToHighlight = vek.get(i-1);
					}
					break;
				}
			}
		}
		return compToHighlight;
	}
	
	
	
}
