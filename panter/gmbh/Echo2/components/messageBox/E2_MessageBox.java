package panter.gmbh.Echo2.components.messageBox;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorE2String;

public abstract class E2_MessageBox extends E2_BasicModuleContainer {

	private MyE2_Button 		buttonOK = 		new MyE2_Button();
	private MyE2_Button 		buttonCancel = 	new MyE2_Button();
	
	private MyE2_String 		window_title = new MyE2_String("Bestätigung ?");
	private MyE2_String 		titleInBox =   null;;
	
	private VectorE2String 	    v_infos = new VectorE2String();

	public abstract void render_message_box() throws myException;

	public E2_MessageBox() {
		super();
	}

	public E2_MessageBox set_titel(MyE2_String windowtitle) {
		this.window_title=windowtitle;
		return this;
	}

	public E2_MessageBox t(String s) {
		this.v_infos.t(s);
		return this;
	}
	
	public E2_MessageBox a(MyE2_String s) {
		this.v_infos.a(s);
		return this;
	}


	public MyE2_String get_titleInBox() {
		return titleInBox;
	}

	public E2_MessageBox set_titleInBox(MyE2_String titleInBox) {
		this.titleInBox = titleInBox;
		return this;
	}

	public MyE2_Button get_buttonOK() {
		return buttonOK;
	}

	public MyE2_Button get_buttonCancel() {
		return buttonCancel;
	}

	public MyE2_String get_window_title() {
		return window_title;
	}

	public VectorE2String get_infos() {
		return v_infos;
	}
	
}
