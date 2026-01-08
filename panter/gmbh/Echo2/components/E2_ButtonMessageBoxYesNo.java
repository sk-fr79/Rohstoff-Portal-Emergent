package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_ButtonMessageBoxYesNo extends MyE2_Button 
{
	private MyE2_String  		cTextTitelZeile = 	new MyE2_String("Bitte wählen ...");
	private MyE2_String  		cTextYes = 			new MyE2_String("Ja");
	private MyE2_String  		cTextNo = 			new MyE2_String("Nein");
	private Vector<MyString>  	vInfos = 			new Vector<MyString>();

	
	

	/**
	 * 
	 * @param TextTitelZeile
	 * @param TextYes
	 * @param TextNo
	 * @param Infos
	 * @throws myException
	 */
	public E2_ButtonMessageBoxYesNo(
			MyE2_String 		TextTitelZeile,	
			MyE2_String 		TextYes, 
			MyE2_String 		TextNo, 
			Vector<MyString> 	Infos
			)  throws myException
	{
		super();
		this.__settings(TextTitelZeile,TextYes,TextNo,Infos);
	}

	
	
	/**
	 * 
	 * @param oImg
	 * @param bAutoDisabled
	 * @param TextTitelZeile
	 * @param TextYes
	 * @param TextNo
	 * @param Infos
	 * @throws myException
	 */
	public E2_ButtonMessageBoxYesNo(ImageReference 		oImg, 
									boolean 			bAutoDisabled,
									MyE2_String 		TextTitelZeile,	
									MyE2_String 		TextYes, 
									MyE2_String 		TextNo, 
									Vector<MyString> 	Infos
			)  throws myException
	{
		super(oImg, bAutoDisabled);
		this.__settings(TextTitelZeile,TextYes,TextNo,Infos);
	}

	
	
	/**
	 * 
	 * @param oImg
	 * @param oimgDisabled
	 * @param TextTitelZeile
	 * @param TextYes
	 * @param TextNo
	 * @param Infos
	 * @throws myException
	 */
	public E2_ButtonMessageBoxYesNo(
									ImageReference oImg, 
									ImageReference oimgDisabled,
									MyE2_String 		TextTitelZeile,	
									MyE2_String 		TextYes, 
									MyE2_String 		TextNo, 
									Vector<MyString> 	Infos
								)  throws myException
	{
		super(oImg, oimgDisabled);
		this.__settings(TextTitelZeile,TextYes,TextNo,Infos);
	}

	
	
	/**
	 * 
	 * @param oImg
	 * @param TextTitelZeile
	 * @param TextYes
	 * @param TextNo
	 * @param Infos
	 * @throws myException
	 */
	public E2_ButtonMessageBoxYesNo(
									ImageReference 		oImg,
									MyE2_String 		TextTitelZeile,	
									MyE2_String 		TextYes, 
									MyE2_String 		TextNo, 
									Vector<MyString> 	Infos
									)  throws myException
	{
		super(oImg);
		this.__settings(TextTitelZeile,TextYes,TextNo,Infos);
	}

	
	
	/**
	 * 
	 * @param cText
	 * @param TextTitelZeile
	 * @param TextYes
	 * @param TextNo
	 * @param Infos
	 * @throws myException
	 */
	public E2_ButtonMessageBoxYesNo(MyString cText,
									MyE2_String 		TextTitelZeile,	
									MyE2_String 		TextYes, 
									MyE2_String 		TextNo, 
									Vector<MyString> 	Infos
									)  throws myException
	{
		super(cText);
		this.__settings(TextTitelZeile,TextYes,TextNo,Infos);
	}

	
	
	/**
	 * 
	 * @param cText
	 * @param oImg
	 * @param oimgDisabled
	 * @param TextTitelZeile
	 * @param TextYes
	 * @param TextNo
	 * @param Infos
	 * @throws myException
	 */
	public E2_ButtonMessageBoxYesNo(	Object 				cText, 
										ImageReference 		oImg, 
										ImageReference 		oimgDisabled,
										MyE2_String 		TextTitelZeile,	
										MyE2_String 		TextYes, 
										MyE2_String 		TextNo, 
										Vector<MyString> 	Infos
			)  throws myException
	{
		super(cText, oImg, oimgDisabled);
		this.__settings(TextTitelZeile,TextYes,TextNo,Infos);
	}

	
	
	/**
	 * 
	 * @param cText
	 * @param oStyle
	 * @param TextTitelZeile
	 * @param TextYes
	 * @param TextNo
	 * @param Infos
	 * @throws myException
	 */
	public E2_ButtonMessageBoxYesNo(	String 			cText, 		
										E2_MutableStyle oStyle,
										MyE2_String 		TextTitelZeile,	
										MyE2_String 		TextYes, 
										MyE2_String 		TextNo, 
										Vector<MyString> 	Infos
										)  throws myException
	{
		super(cText, oStyle);
		this.__settings(TextTitelZeile,TextYes,TextNo,Infos);
	}


	
	
	
	/**
	 * 
	 * @param cText
	 * @param TextTitelZeile
	 * @param TextYes
	 * @param TextNo
	 * @param Infos
	 * @throws myException
	 */
	public E2_ButtonMessageBoxYesNo(	String cText,
										MyE2_String 		TextTitelZeile,	
										MyE2_String 		TextYes, 
										MyE2_String 		TextNo, 
										Vector<MyString> 	Infos
									)  throws myException
	{
		super(cText);
		this.__settings(TextTitelZeile,TextYes,TextNo,Infos);
	}
	

	
	private void __settings(		MyE2_String 		TextTitelZeile,	
									MyE2_String 		TextYes,	
									MyE2_String 		TextNo, 
									Vector<MyString> 	Infos
							) throws myException
	{
		if (TextTitelZeile!=null) 	this.cTextTitelZeile = 	TextTitelZeile;
		if (TextYes!=null) 			this.cTextYes = 		TextYes;
		if (TextNo!=null) 			this.cTextNo = 			TextNo;
		
		if (Infos!=null) 			this.vInfos.addAll(Infos);
		
		this.add_oActionAgent(new actionOpenPopupWindow());
		
	}
	
	
	
	private class actionOpenPopupWindow extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_MessageBoxYesNo oMessageBox = E2_ButtonMessageBoxYesNo.this.create_E2_MessageBoxYesNo(
															E2_ButtonMessageBoxYesNo.this.cTextTitelZeile,
															E2_ButtonMessageBoxYesNo.this.cTextYes,
															E2_ButtonMessageBoxYesNo.this.cTextNo,
															E2_ButtonMessageBoxYesNo.this.vInfos,null);
			
			oMessageBox.add_ActionAgents(E2_ButtonMessageBoxYesNo.this.create_actionAgents4yesButton());
			
		}
		
		
	}
	
	
	
	
	public abstract Vector<XX_ActionAgent>  create_actionAgents4yesButton() throws myException;
	
	public abstract E2_MessageBoxYesNo      create_E2_MessageBoxYesNo(
																	MyE2_String 		TextTitelZeile,	
																	MyE2_String 		TextYes, 
																	MyE2_String 		TextNo, 
																	Vector<MyString> 	Infos,
																	XX_ActionAgent      ActionAgentStart) throws myException;
	
}
