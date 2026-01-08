package panter.gmbh.Echo2.Messaging;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;

public class MessagePopupImmediate extends MyE2_WindowPane {

	
	//ausserer grid-Rahmen, wird zur positionierung benutzt
	private E2_Grid   gridBase4Window = new E2_Grid()._setSize(600);
			
	private Extent			oExtLeftPos = null;					//vorgabe = zentriert
	private Extent			oExtTopPos = null;					//vorgabe = zentriert
	private Extent			oExtWidth = new Extent(1000);
	private Extent			oExtHeight = new Extent(700);

	
	
	public MessagePopupImmediate() {
		super(true);   //immer modal
		this.setStyle(MyE2_WindowPane.STYLE_WINDOW_4_POPUP_MESSAGES());
	}
	
	public MessagePopupImmediate _renderMessages(MyE2_MessageVector mv) {
		this.removeAll();
		this.gridBase4Window.removeAll();
		E2_ContentPane oContentPane = new E2_ContentPane(true);
		this.add(oContentPane);
		oContentPane.add(gridBase4Window);
		gridBase4Window._a(new MyE2_MessageGridWithAllMessages(mv), new RB_gld()._ins(5));
		return this;
	}
	
	
	public MessagePopupImmediate _showPopup() {
		bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(this);
		this.setWidth(this.oExtWidth);
		this.setWidth(this.oExtHeight);
		this.setPositionX(this.oExtLeftPos);
		this.setPositionY(this.oExtTopPos);
		return this;
	}

	public MessagePopupImmediate _setLeftPos(int iLeftPos) {
		oExtLeftPos = new Extent(iLeftPos);
		return this;
	}
	public MessagePopupImmediate _setTopPos(int iTopPos)	  {
		oExtTopPos = new Extent(iTopPos);				
		return this;
	}
	public MessagePopupImmediate _setWidth(int iWidth)	 {	
		oExtWidth = new Extent(iWidth);				
		return this;
	}

	public MessagePopupImmediate _setHeight(int iHeight)	 {	
		oExtHeight = new Extent(iHeight);				
		return this;
	}

}
