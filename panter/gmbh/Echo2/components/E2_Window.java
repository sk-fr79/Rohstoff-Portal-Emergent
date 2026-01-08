/**
 * 
 */
package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.ContentPane;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.FillImageBorder;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.WindowPane;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_FillImageBorder;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorWindowPaneContent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorWindowPaneTitel;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class E2_Window extends WindowPane {

	private MyE2_String 				m_title 	= null;
	private E2_BasicModuleContainer  	m_container = null;
	private E2_Button                   m_closeButton = null;
	
	
	private int   						m_titleHight = 20;
	private SplitPane 					m_splitPane = new SplitPane(SplitPane.ORIENTATION_VERTICAL);
	private ContentPane 				m_paneTitle = new ContentPane();
	private ContentPane 				m_paneContent = new ContentPane();
	
	private Color 						m_colorTitle = new E2_ColorWindowPaneTitel();
	
	private E2_Grid    					g_title = new E2_Grid()._setSize(new Extent(100, Extent.PERCENT), new Extent(20));
	
	public E2_Window() throws myException {
		super();
		
		this.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		this.renderTitle();
		this.m_paneTitle.add(this.g_title);
		
		this.add(this.m_splitPane);
		this.m_paneTitle.setBackground(this.m_colorTitle);
		this.m_splitPane.setResizable(false);
		this.m_splitPane.add(this.m_paneTitle);
		this.m_splitPane.add(this.m_paneContent);
		this.m_splitPane.setSeparatorPosition(new Extent(this.m_titleHight));
	}

	private void renderTitle() {
		this.g_title._clear();
		this.g_title._a(new RB_lab()._t(S.NN(this.m_title, S.ms(""))),  new RB_gld()._ins(1)._left_mid());
		
//		if (this.m_closeButton!=null) {
//			this.g_title._a(this.m_closeButton, 			new RB_gld()._ins(1)._right_mid());
//		}
//		
		this.setStyle(E2_Window.STYLE(this.m_colorTitle) );
	}
	
	public E2_Window _show() {
		bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(this);
		return this;
	}
	
	public MyE2_String getTitleText() {
		return m_title;
	}

	public E2_BasicModuleContainer getCcontainer() {
		return m_container;
	}

	public E2_Button getCloseButton() {
		return m_closeButton;
	}

	public int getTitleHight() {
		return m_titleHight;
	}

	public SplitPane getSplitPane() {
		return m_splitPane;
	}

	public ContentPane getPaneTitle() {
		return m_paneTitle;
	}

	public ContentPane getPaneContent() {
		return m_paneContent;
	}

	public E2_Window _setTitleText(MyE2_String title) {
		this.m_title = title;
		this.renderTitle();
		return this;
	}

	public E2_Window _setContainer(E2_BasicModuleContainer container) {
		this.m_container = container;
		return this;
	}

	public E2_Window _setCloseButton(E2_Button closeButton) {
		this.m_closeButton = closeButton;
		this.renderTitle();
		return this;
	}

	public E2_Window _setTitleHight(int titleHight) {
		this.m_titleHight = titleHight;
		this.m_splitPane.setSeparatorPosition(new Extent(this.m_titleHight));
		return this;
	}


	public Color getColorTitle() {
		return m_colorTitle;
	}


	public E2_Window setColorTitle(Color colorTitle) {
		this.m_colorTitle = colorTitle;
		this.m_paneTitle.setBackground(this.m_colorTitle);
		return this;
	}


	private class ownCloseAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			bibMSG.MV()._addInfo("Test");
			forceClose();
		}
	}
	
	
	
	public static MutableStyle STYLE(Color colTitle) {
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, new E2_ColorWindowPaneContent());
		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, E2_ResourceIcon.get_RI("windowpaneclose_small.png"));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT, new Extent(20));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, colTitle);
		oStyle.setProperty( WindowPane.PROPERTY_CLOSABLE, new Boolean(true));
		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1),new Insets(1)));
		oStyle.setProperty( WindowPane.PROPERTY_MOVABLE, new Boolean(true));
		
		try {
			if (bibALL.get_RECORD_USER().is_FENSTER_MIT_SCHATTEN_YES())	{
				FillImageBorder border = new FillImageBorder();
				
				border.setBorderInsets(new Insets(20));
				border.setContentInsets(new Insets(4,4,10,10));
				border.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(E2_ResourceIcon.get_RI("bordertopleft.png")));
				border.setFillImage(FillImageBorder.TOP, new FillImage(E2_ResourceIcon.get_RI("bordertop.png")));
				border.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(E2_ResourceIcon.get_RI("bordertopright.png")));
				border.setFillImage(FillImageBorder.LEFT, new FillImage(E2_ResourceIcon.get_RI("borderleft.png")));
				border.setFillImage(FillImageBorder.RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderright.png")));
				border.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(E2_ResourceIcon.get_RI("borderbottomleft.png")));
				border.setFillImage(FillImageBorder.BOTTOM, new FillImage(E2_ResourceIcon.get_RI("borderbottom.png")));
				border.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderbottomright.png")));
				
				oStyle.setProperty( WindowPane.PROPERTY_BORDER,border);
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		return oStyle;
	}

	
//	
//	public void addActionListener(ActionListener l) {
//        getEventListenerList().addListener(ActionListener.class, l);
//	}
//	
//    /**
//     * Notifies all listeners that have registered for this event type.
//     * 
//     * @param e the <code>ActionEvent</code> to send
//     */
//    public void fireActionPerformed(ActionEvent e) {
//        if (!hasEventListenerList()) {
//            return;
//        }
//        EventListener[] listeners = getEventListenerList().getListeners(ActionListener.class);
//        for (int index = 0; index < listeners.length; ++index) {
//            ((ActionListener) listeners[index]).actionPerformed(e);
//        }
//    }

//	private boolean closeViaEvent = false;
//    
     
    private void handleOwnClose() {
   		this.m_closeButton.doAction();
    }
    
    
    public void forceClose() {
    	super.userClose();
    }
    
    
    public void userClose() {
    	if (this.m_closeButton==null) {
    		this.forceClose();
    	} else {
    		this.handleOwnClose();
    	}
    
    }

    
}
