/*
 * Created on 16.10.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.components;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.TextField;
import nextapp.echo2.app.layout.GridLayoutData;
import nextapp.echo2.extras.app.CalendarSelect;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;




public abstract class XX_PopUpCalendar extends MyE2_Button  implements MyE2IF__Component, E2_IF_Copy
{
	
    private CalendarSelect				oDateChoose = 	null; 
    
    private Vector<TextField> 	        vTF_to_FillWithDate = new Vector<TextField>();

    private MyE2_Button  				oButtonOK = new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"));
    private MyE2_Button  				oButtonCancel = new MyE2_Button(E2_ResourceIcon.get_RI("cancel.png"));
  
    private E2_BasicModuleContainer     oContainerWithCalendar = null;
    

    /**
     * 
     * @param oDate
     * @param oTF
     * @param oContainer
     */
    public XX_PopUpCalendar(Calendar oDate, TextField oTF, E2_BasicModuleContainer oContainer)
    {
        super(E2_ResourceIcon.get_RI("calendar.png"),true);
    	
        this.oContainerWithCalendar = oContainer;
        
    	// falls null uebergeben wird, dann das aktuelle datum nehmen
    	if (oDate != null)
    		this.oDateChoose = new CalendarSelect(oDate.getTime());
    	else
    		this.oDateChoose = new CalendarSelect(new GregorianCalendar().getTime());
    	
    	this.oDateChoose.setLocale(Locale.GERMANY);
  
		this.vTF_to_FillWithDate.add(oTF);
        this.add_oActionAgent(new ownActionAgentOpenSelector());
        
        this.oContainerWithCalendar.add(this.oDateChoose, E2_INSETS.I_5_5_5_5);
        this.oContainerWithCalendar.add(new E2_ComponentGroupHorizontal(0,this.oButtonOK,this.oButtonCancel,E2_INSETS.I_0_5_0_0), E2_INSETS.I_5_5_5_5);
        
        this.oButtonOK.add_oActionAgent(new ownActionAgentOK());
        this.oButtonCancel.add_oActionAgent(new ownActionAgentCancel());
    }

    
    
    /**
     * 
     * @param oDate
     * @param oTF
     * @param oContainer
     */
    public XX_PopUpCalendar(Calendar oDate, Vector<TextField> vTF, E2_BasicModuleContainer oContainer)
    {
        super(E2_ResourceIcon.get_RI("calendar.png"),true);
    	
        this.oContainerWithCalendar = oContainer;
        
    	// falls null uebergeben wird, dann das aktuelle datum nehmen
    	if (oDate != null)
    		this.oDateChoose = new CalendarSelect(oDate.getTime());
    	else
    		this.oDateChoose = new CalendarSelect(new GregorianCalendar().getTime());
    	
    	this.oDateChoose.setLocale(Locale.GERMANY);
  
		this.vTF_to_FillWithDate.addAll(vTF);
        this.add_oActionAgent(new ownActionAgentOpenSelector());
        
        this.oContainerWithCalendar.add(this.oDateChoose, E2_INSETS.I_5_5_5_5);
        this.oContainerWithCalendar.add(new E2_ComponentGroupHorizontal(0,this.oButtonOK,this.oButtonCancel,E2_INSETS.I_0_5_0_0), E2_INSETS.I_5_5_5_5);
        
        this.oButtonOK.add_oActionAgent(new ownActionAgentOK());
        this.oButtonCancel.add_oActionAgent(new ownActionAgentCancel());
    }

    

    
    private class ownActionAgentOpenSelector extends XX_ActionAgent
    {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			XX_PopUpCalendar oThis = XX_PopUpCalendar.this;
		
			if (oThis.vTF_to_FillWithDate.size()>0)
			{
				String cDatum = S.NN(oThis.vTF_to_FillWithDate.get(0).getText());    //das erste textfeld ist ausschlaggebend
				if (myDateHelper.isDate(cDatum))
				{
					oThis.oDateChoose.setDate(new myDateHelper(cDatum).get_oCalDate().getTime());
				}
			}

			oThis.oContainerWithCalendar.CREATE_AND_SHOW_POPUPWINDOW(new Extent(250), new Extent(300), new MyE2_String("Datum wählen ..."));
			
		}
    }
    
    
    private class ownActionAgentOK extends XX_ActionAgent
    {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			XX_PopUpCalendar oThis = XX_PopUpCalendar.this;
		
			if (oThis.vTF_to_FillWithDate.size()>0)
			{
				for (TextField oTF:oThis.vTF_to_FillWithDate)
				{
					String cDatum = new myDateHelper(oThis.oDateChoose.getDate()).get_cDateFormatForMask();
					oTF.setText(cDatum);
				}
			}

			oThis.Do_SomethingOnOK_Button();
			oThis.oContainerWithCalendar.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
    }
 
    private class ownActionAgentCancel extends XX_ActionAgent
    {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			XX_PopUpCalendar.this.oContainerWithCalendar.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
    }

    
	public CalendarSelect get_oDateChooser() 
	{
		return oDateChoose;
	}



	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return null;
	}


	public abstract void Do_SomethingOnOK_Button() throws myException;
	
	
	
	
	//2014-09-08: methoden um das objekt in ein grid einzuwickeln
	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;
	@Override
	public MyE2IF__Component ME() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.setSize(1+this.vADDONS_IN_WRAP.size());
		//die alte layoutdata sichern und an das aussengrid uebergeben
		LayoutData oLayoutThis = this.getLayoutData();
		GridLayoutData 	oLayoutInnen1 = new GridLayoutData();
		GridLayoutData 	oLayoutInnen2 = new GridLayoutData();
		oLayoutInnen1.setInsets(E2_INSETS.I(0,0,this.i_SpaceInPixel,0));
		oLayoutInnen1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		oLayoutInnen2.setInsets(E2_INSETS.I(0,0,0,0));
		oLayoutInnen2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		if (this.b_OnComponentInFront) {
			this.setLayoutData(oLayoutInnen1);
			oGridRueck.add_raw(this);
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			((Component)this.vADDONS_IN_WRAP.get(this.vADDONS_IN_WRAP.size()-1)).setLayoutData(oLayoutInnen2);
		} else {
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			this.setLayoutData(oLayoutInnen2);
			oGridRueck.add_raw(this);
		}
		
		oGridRueck.setLayoutData(oLayoutThis);
		return oGridRueck;
	}

	@Override
	public Component C_ME() throws myException {
		return (Component) this.ME();
	}

	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
		this.b_OnComponentInFront = ME_InFront;
	}

	@Override
	public void set_SpaceInPX(int iSpace) {
		this.i_SpaceInPixel=iSpace;
	}

	
}
