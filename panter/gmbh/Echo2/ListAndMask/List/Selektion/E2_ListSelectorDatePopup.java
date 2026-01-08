package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;


/**
 * selector mit EINEM datumsfeld, nur popup erlaubt, keine freie datumseingabe (wegen selektionsaktion)
 * @author martin
 *
 */
public abstract class E2_ListSelectorDatePopup extends XX_ListSelektor
{
	//selectionskomponente ist ein Grid
	private MyE2_TextField_DatePOPUP_OWN   		oDatumsFeld =  null;
	
	private Extent    							oExtSpaltenBreiteBeschriftung = null;
	private Extent    							oExtSpalteSelektor = 			null;
	private MyE2_String    						cBeschriftung = null;
	
	//falls es weitere aktionen geben soll, z.B. beeinflussung des uebrigen selektors nach click
	private Vector<XX_ActionAgent>              vWeitereAktionenInFront = new Vector<XX_ActionAgent>();
	private Vector<XX_ActionAgent>              vWeitereAktionenBehind = new Vector<XX_ActionAgent>();
	
	public E2_ListSelectorDatePopup(	boolean 	bShowEraser, 
										boolean 	bMiniIcon, 
										Extent 		extBreiteTextFeldInDatumsObjekt, 
										MyE2_String Beschriftung, 
										Extent 		extSpaltenBreiteBeschriftung,
										Extent 		extSpalteSelektor) throws myException
	{
		super();
		
		this.cBeschriftung = Beschriftung;
		
		
		this.oExtSpaltenBreiteBeschriftung = 	extSpaltenBreiteBeschriftung;
		this.oExtSpalteSelektor = 				extSpalteSelektor;
		
		oDatumsFeld = new MyE2_TextField_DatePOPUP_OWN("", 100, bMiniIcon, bShowEraser);
		
		if (extBreiteTextFeldInDatumsObjekt!=null) oDatumsFeld.get_oTextField().setWidth(extBreiteTextFeldInDatumsObjekt);
		
		this.oDatumsFeld.get_oTextField().set_bEnabled_For_Edit(false);
	}


	@Override
	public Component get_oComponentForSelection()
	{
		MyE2_Grid  oGrid4Selector = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		if (this.cBeschriftung!=null)
		{
			oGrid4Selector.setSize(2);
			oGrid4Selector.add(new MyE2_Label(this.cBeschriftung), E2_INSETS.I_0_0_0_0);
			if (this.oExtSpaltenBreiteBeschriftung!=null) oGrid4Selector.setColumnWidth(0, this.oExtSpaltenBreiteBeschriftung);
		}
		
		oGrid4Selector.add(this.oDatumsFeld, E2_INSETS.I_0_0_0_0);
		if (this.oExtSpalteSelektor!=null) oGrid4Selector.setColumnWidth(1, this.oExtSpalteSelektor);
		
		return oGrid4Selector;
	}

	@Override
	public Component get_oComponentWithoutText()
	{
		MyE2_Grid  oGrid4Selector = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGrid4Selector.add(this.oDatumsFeld, E2_INSETS.I_0_0_0_0);
		if (this.oExtSpalteSelektor!=null) oGrid4Selector.setColumnWidth(0, this.oExtSpalteSelektor);
		return oGrid4Selector;
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) 
	{
		this.oDatumsFeld.get_vActionAgentsZusatz().removeAllElements();
		this.oDatumsFeld.get_vActionAgentsZusatz().add(oAgent);
		this.oDatumsFeld.get_oButtonEraser().add_oActionAgent(oAgent);
		
		this.oDatumsFeld.get_vActionAgentsZusatz().addAll(0,this.vWeitereAktionenInFront);
		this.oDatumsFeld.get_oButtonEraser().add_oActionAgent(this.vWeitereAktionenInFront,true);
		
		this.oDatumsFeld.get_vActionAgentsZusatz().addAll(this.vWeitereAktionenBehind);
		this.oDatumsFeld.get_oButtonEraser().add_oActionAgent(this.vWeitereAktionenBehind,false);

		
	}
	
	
	public void setWidthInPixel_InputFields(int iPixel)
	{
		this.oDatumsFeld.get_oTextField().set_iWidthPixel(iPixel);
	}



	public MyE2_TextField_DatePOPUP_OWN get_oTFDatum()
	{
		return this.oDatumsFeld;
	}

	/*
	 * der eigene actionlistener loescht die eingaben, wenn ein datum nicht korrekt war 
	 */
	public void doInternalCheck()
	{
		this._do_check(this.oDatumsFeld.get_oTextField());
	}
	
	private void _do_check(MyE2_TextField oTF)
	{
		if (!bibALL.isEmpty(oTF.getText()))
		{
			TestingDate oDate = new TestingDate(oTF.getText());
			if (! oDate.testing())
			{
				oTF.setText("");
			}
			else
			{
				oTF.setText(oDate.get_FormatedDateString("dd.mm.yyyy"));
			}
		}
	}


	public MyE2_TextField_DatePOPUP_OWN get_oDatumsFeld()
	{
		return oDatumsFeld;
	}


	public Extent get_oExtSpaltenBreiteBeschriftung()
	{
		return oExtSpaltenBreiteBeschriftung;
	}


	public Extent get_oExtSpalteSelektor()
	{
		return oExtSpalteSelektor;
	}


	public MyE2_String get_cBeschriftung()
	{
		return cBeschriftung;
	}


	public Vector<XX_ActionAgent> get_vWeitereAktionenInFront()
	{
		return vWeitereAktionenInFront;
	}


	public Vector<XX_ActionAgent> get_vWeitereAktionenBehind()
	{
		return vWeitereAktionenBehind;
	}

}
