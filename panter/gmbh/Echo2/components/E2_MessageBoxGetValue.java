package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorE2String;


public class E2_MessageBoxGetValue extends E2_BasicModuleContainer implements IF_HasChangeListeners<E2_MessageBoxGetValue>
{

	// aktuell mögliche prüfungen, Erweiterungen hier einpflegen. String ist default und hat keine Einschränkung
	public enum TYPE_OF_INPUT {
		STRING,
		NUMBER,
		DATE
	};
	
	
	private MyE2_String  		cTextTitelZeile 	= new MyE2_String("Neuen Wert eingeben ...");
	private MyE2_String  		cTextOK 			= new MyE2_String("Ok");
	private MyE2_String  		cTextCancel 		= new MyE2_String("Abbrechen");
	private XX_ActionAgent      oActionAgentStart	= null;

	private Component			_componentExtra 	= null;
	private Vector<MyString>  	vInfos 				= new Vector<MyString>();

	private MyE2_String			cInputFieldDescription = new MyE2_String("Eingabewert");
	private MyE2_TextField      tfInput 			= null;
	private TYPE_OF_INPUT       _typeOfInput 		= TYPE_OF_INPUT.STRING;
	private int					_sizeOfInputField   = 100;
	
	
	private MyE2_Button         oButtonOK =        	null;
	private MyE2_Button         oButtonCancel =	    null;
	
	
	
	private boolean 			_ShowOK 		= true;
	private	boolean 			_ShowCancel		= true;
	private int 				_iWidth_base	= 400; 
	private int 				_iHeight_base	= 180;
	

	
	/**
	 * default-Messagebox mit Standard-Texten ohne Eingabe
	 * @author manfred
	 * @throws myException 
	 * @date 09.02.2021
	 *
	 */
	public E2_MessageBoxGetValue() throws myException {
		this(null,null,null,true,true,null,null,null,400,180);
	}
	

	public E2_MessageBoxGetValue(String 		TextTitelZeile,	
								String 			TextOk, 
								String 			TextCancel, 
								boolean 		bShowOK,
								boolean 		bShowCancel,
								String 			TextInputDescription,
								VectorE2String 	Infos,
								XX_ActionAgent  ActionAgentStart  ,
								int 			iWidth,
								int 			iHeight) throws myException 
	{
		super();
		
		if (TextTitelZeile!=null) 	this.cTextTitelZeile 				= new MyE2_String(TextTitelZeile);
		if (TextOk!=null) 			this.cTextOK 						= new MyE2_String(TextOk);
		if (TextCancel!=null) 		this.cTextCancel 					= new MyE2_String(TextCancel);
		if (TextInputDescription != null) this.cInputFieldDescription 	= new MyE2_String(TextInputDescription);
		
		if (Infos!=null) 			this.vInfos.addAll(Infos);
		
		_ShowOK 		= bShowOK;
		_ShowCancel 	= bShowCancel;
		_iWidth_base 	= iWidth;
		_iHeight_base 	= iHeight;
		
		
		this.oActionAgentStart = ActionAgentStart;
		
	}

	
	/**
	 * baut den Dialog auf und zeigt ihn an.
	 * @author manfred
	 *
	 * @return
	 * @throws myException
	 */
	public E2_MessageBoxGetValue _show() throws myException {
		this.removeAll();
		
		E2_Grid  oGridHelp 	= new E2_Grid()._w100()._bo_no()._s(1);
		E2_Grid  oGridInputField = new E2_Grid()._bo_no()._s(2);
		E2_Grid  oGridHelpButtons = new E2_Grid()._w100()._bo_no()._s(2);
		
		for (MyString oText: this.vInfos) 		{
			oGridHelp._a(new MyE2_Label(oText), new RB_gld()._ins(0)._left_mid());
		}
		
		if(_componentExtra != null){
			oGridHelp._a(_componentExtra, new RB_gld()._ins(0));
		}
		
		
		if (cInputFieldDescription != null) {
			oGridInputField._a(new MyE2_Label(this.cInputFieldDescription), new RB_gld()._ins(0)._left_mid());
		}
		
		this.tfInput = create_tfInput();
		
		oGridInputField._a(this.tfInput, new RB_gld()._ins( 10,0,0,0)._left_mid());
		
		
		this.oButtonOK 		= new buttonOk();
		this.oButtonCancel  = new buttonCancel();
		boolean bBeideAusblenden = (!_ShowOK) && (!_ShowCancel);
		
		E2_ComponentGroupHorizontal oCompGroup = new E2_ComponentGroupHorizontal(0,_ShowOK?this.oButtonOK:null,_ShowCancel?this.oButtonCancel:null,(bBeideAusblenden?new MyE2_Label(""):null),new Insets(0, 0, 20, 0));
		oGridHelpButtons._a(oCompGroup,new RB_gld()._left_top()._ins(2, 10, 2, 0)); 
		
		this.add(oGridHelp, E2_INSETS.I(10, 10, 10, 5));
		this.add(oGridInputField, E2_INSETS.I_10_0_10_0);
		this.add(oGridHelpButtons, E2_INSETS.I_10_0_10_0);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(_iWidth_base), new Extent(_iHeight_base), this.cTextTitelZeile);
		return this;
	}
	
	
	
	/**
	 * Setzt den Typ des Eingabefeldes für die Validierung
	 * @author manfred
	 * @date 21.01.2021
	 *
	 * @param type
	 * @return
	 */
	public E2_MessageBoxGetValue _setTypeOfInput (TYPE_OF_INPUT type) {
		this._typeOfInput = type;
		return this;
	}
	
	/**
	 * Setzt die Länge des Eingabefelds in Pixel
	 * @author manfred
	 * @date 22.01.2021
	 *
	 * @param size
	 * @return
	 */
	public E2_MessageBoxGetValue _setSizeOfInputField( int size) {
		this._sizeOfInputField = size;
		return this;
	}
	
	
	public E2_MessageBoxGetValue _setText_TitelZeile(String Titel) {
		this.cTextTitelZeile = new MyE2_String(Titel);
		return this;
	}
	
	public E2_MessageBoxGetValue _setText_OK(String TextOk) {
		this.cTextOK = new MyE2_String(TextOk);
		return this;
	}
	
	
	public E2_MessageBoxGetValue _setText_Cancel(String TextCancel) {
		this.cTextCancel = new MyE2_String(TextCancel);
		return this;
	}
	
	public E2_MessageBoxGetValue _setShowOK(boolean bShow) {
		this._ShowOK= bShow;
		return this;
	}
	
	public E2_MessageBoxGetValue _setShowCancel(boolean bShow) {
		this._ShowCancel = bShow;
		return this;
	}

	public E2_MessageBoxGetValue _setText_InputDescription(String Text) {
		this.cInputFieldDescription = new MyE2_String(Text);
		return this;
	}
	
	public E2_MessageBoxGetValue _addInfos (VectorE2String vInfos) {
		this.vInfos.addAll(vInfos);
		return this;
	}

	public E2_MessageBoxGetValue _setActionAgentStart(XX_ActionAgent agentStart) {
		this.oActionAgentStart = agentStart;
		return this;
	}

	public E2_MessageBoxGetValue _setWidth(int iWidth) {
		_iWidth_base 	= iWidth;
		return this;
	}
	
	public E2_MessageBoxGetValue _setHeight(int iHeight) {
		_iHeight_base 	= iHeight;
		return this;
	}

	
	
	
	
	
	
	/**
	 * Erzeugt das Input-Textfield mit der richtigen Size und angepasst an den Typ
	 * @author manfred
	 * @date 22.01.2021
	 *
	 * @return
	 */
	private MyE2_TextField create_tfInput() {
		
		Alignment m_alignRight = new Alignment(Alignment.RIGHT,	Alignment.CENTER);
		Alignment m_alignLeft = new Alignment(Alignment.LEFT,	Alignment.CENTER);
		
		MyE2_TextField tf = new MyE2_TextField();
		switch (_typeOfInput) {
		case STRING:
		case DATE:	
			tf.setAlignment(m_alignLeft);
			break;
			
		case NUMBER:
			tf.setAlignment(m_alignRight);
			
		default:
			break;
		}
		
		tf.set_iWidthPixel(_sizeOfInputField);
		
		return tf;
	}
	
	
	private MyE2_MessageVector validateInputValue(String value) {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		switch (this._typeOfInput) {
		case  STRING:
			// default, no Checks
			break;
		
		case NUMBER:
			MyBigDecimal bd = new MyBigDecimal(value);
			if (bd.isNotOK()) {
				mv._addAlarm(bd.get_cErrorCODE());
				tfInput.setBackground(Color.YELLOW);
				tfInput._setFocus();
				mv._add(new MyE2_Alarm_Message(new MyE2_String("Menge darf nicht leer sein.")));
			} else {
				tfInput.setBackground(Color.WHITE);
			}
			
			break;

		case DATE:
			MyDate dt = new MyDate(value);
			if (dt.isNotOK()) {
				mv._addAlarm(dt.get_cErrorCODE());
				tfInput.setBackground(Color.YELLOW);
				tfInput._setFocus();
				mv._add(new MyE2_Alarm_Message(new MyE2_String("Menge darf nicht leer sein.")));
			} else {
				tfInput.setBackground(Color.WHITE);
			}
			
			break;
		default:
			
			break;
		}
		return mv; 
	}
	
	
	
	
	
	
	private class buttonCancel extends MyE2_Button
	{

		public buttonCancel() 
		{
			super(E2_MessageBoxGetValue.this.cTextCancel);
			
			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					E2_MessageBoxGetValue.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			});
		}
	}
	

	
	private class buttonOk extends MyE2_Button
	{

		public buttonOk() 
		{
			super(E2_MessageBoxGetValue.this.cTextOK);
			
			if (E2_MessageBoxGetValue.this.oActionAgentStart!=null) {
				this.add_oActionAgent(E2_MessageBoxGetValue.this.oActionAgentStart);
			}
			
			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					bibMSG.MV()._add(validateInputValue(tfInput.getText()));
					if (bibMSG.get_bIsOK()) {

						MyE2_MessageVector mv = new MyE2_MessageVector();
						for (IF_ExecuterOnComponentV2<E2_MessageBoxGetValue> executer: changeListeners) {
							mv._add(executer.execute(E2_MessageBoxGetValue.this));
						}
						bibMSG.MV()._add(mv);

						E2_MessageBoxGetValue.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					} 
				}
			});
		}
	}


	/**
	 * @return the oButtonYES
	 */
	public MyE2_Button getButtonOK() {
		return oButtonOK;
	}


	/**
	 * @return the oButtonNO
	 */
	public MyE2_Button getButtonCancel() {
		return oButtonCancel;
	}
	
	
	/**
	 * gibt den Eingabe-String zurück
	 * @author manfred
	 *
	 * @return
	 */
	public String getValue() {
		return tfInput.getText();
	}
	
	
	/**
	 * Zusatzkomponente, die über dem Eingabefeld angezeigt wird
	 * @author manfred
	 *
	 * @param component
	 * @return
	 */
	public E2_MessageBoxGetValue _setComponentExtra(Component component) {
		_componentExtra = component;		
		return this;
	}

	
	/**
	 * gibt die Zusatzkomponente zurück
	 * @author manfred
	 * @date 25.03.2021
	 *
	 * @return
	 */
	public Component _getComponentExtra() {
		return _componentExtra;
	}
	
	
	
	// ChangeListener
	private VEK<IF_ExecuterOnComponentV2<E2_MessageBoxGetValue>>    changeListeners = new   VEK<>();       
	
	public E2_MessageBoxGetValue _clearChangeListener() {
		this.changeListeners.clear();
		return this;
	}
	
	public E2_MessageBoxGetValue _addChangeListener(IF_ExecuterOnComponentV2<E2_MessageBoxGetValue> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}
	
	
}
