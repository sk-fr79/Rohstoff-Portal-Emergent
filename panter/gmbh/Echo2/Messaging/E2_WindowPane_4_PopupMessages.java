package panter.gmbh.Echo2.Messaging;

import nextapp.echo2.app.event.WindowPaneListener;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;

public class E2_WindowPane_4_PopupMessages extends MyE2_WindowPane {

	//annahme: das fenster wird mit dem Fensterschlieﬂ-knopf geschlossen.
	//muss von den anderen aktionen auf false gesetzt werden
	private boolean b_CloseEventIsFromWindowButtonRightCorner = true;
	
	
	//ausserer grid-Rahmen, wird zur positionierung benutzt
	private MyE2_Grid   gridBase4Window = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100_H100());
			
	private GridLayoutData   gl_4_MessageGrid = MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I(5,5,5,5), new E2_ColorBase(), 1);
	
	
	public E2_WindowPane_4_PopupMessages() {
		super(true);   //immer modal
		this.setStyle(MyE2_WindowPane.STYLE_WINDOW_4_POPUP_MESSAGES());
	}
	
	
	
	public void showActualMessages() {
		this.removeAll();
		this.gridBase4Window.removeAll();
		E2_ContentPane oContentPane = new E2_ContentPane(true);
		this.add(oContentPane);
		oContentPane.add(gridBase4Window);
		gridBase4Window.add(new MyE2_MessageGridWithAllMessages(bibMSG.MV()), this.gl_4_MessageGrid);
	}
	
	
	public void showMessages(MyE2_MessageVector mv) {
		this.removeAll();
		this.gridBase4Window.removeAll();
		E2_ContentPane oContentPane = new E2_ContentPane(true);
		this.add(oContentPane);
		oContentPane.add(gridBase4Window);
		gridBase4Window.add(new MyE2_MessageGridWithAllMessages(mv), this.gl_4_MessageGrid);
	}
	
	
	
	public E2_WindowPane_4_PopupMessages _showMessages(MyE2_MessageVector mv) {
		this.showMessages(mv);
		return this;
	}
	
	
	public E2_WindowPane_4_PopupMessages _showActualMessages() {
		this.showActualMessages();
		return this;
	}

	
	public void showPopup(WindowPaneListener oListener) {
		bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(this);
		
		if (oListener!=null) {
			this.addWindowPaneListener(oListener);
			this.b_CloseEventIsFromWindowButtonRightCorner = true;
		}
		
	}


	public boolean get_bCloseEventIsFromWindowButtonRightCorner() {
		return this.b_CloseEventIsFromWindowButtonRightCorner;
	}



	public void set_bCloseEventIsFromWindowButtonRightCorner(boolean bCloseEventIsFromWindowButtonRightCorner) {
		this.b_CloseEventIsFromWindowButtonRightCorner = bCloseEventIsFromWindowButtonRightCorner;
	}



	public GridLayoutData get_gl_4_MessageGrid() {
		return this.gl_4_MessageGrid;
	}



	public void set_gl_4_MessageGrid(GridLayoutData gl4MessageGrid) {
		this.gl_4_MessageGrid = gl4MessageGrid;
	}
	
	
	public E2_WindowPane_4_PopupMessages _showPopup() {
		bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(this);
		return this;
	}
	
}
