package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_InputDatum2;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.SUCHER._SEARCH_SearchAdressFields;

public class FUS_InputDatum extends MyE2_TextField_DatePOPUP_OWN implements __FUS_STANDARD_Element
{

	public FUS_InputDatum() throws myException
	{
		super("",80,true,true);
		this.get_oButtonEraser().__setImages(E2_ResourceIcon.get_RI("eraser_xxs.png"), true);
		
		this.get_vActionAgentsZusatz().add(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				//falls ein 2. datumfeld da ist (bei fahrplan), dann den wert dorthin kopieren
				FUS_InputDatum2 oDatum2 = new _SEARCH_InputDatum2().get_Found_FUS_InputDatum2();
				if (oDatum2 != null && S.isEmpty(oDatum2.get_oTextField().getText()))
				{
					oDatum2.get_oTextField().setText(FUS_InputDatum.this.get_oTextField().getText());
				}
			}
		});
		
		
		this.get_vActionAgentsZusatz().add(new ownActionAgentPruefeHandelsvertraege());
	}
 
	//2014-08-04: problematik: wird eine Adresse in eine maske voreingefuellt, dann wird diese nicht auf handelsvertraege geprueft. Dies
	//            wird hier ueber den zusatzagenten gemacht
	private class ownActionAgentPruefeHandelsvertraege extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 	{
			FUS_SearchAdresse oEK_Adresse = new _SEARCH_SearchAdressFields().get_Found_EK_AdressField();
			FUS_SearchAdresse oVK_Adresse = new _SEARCH_SearchAdressFields().get_Found_VK_AdressField();
			
			oEK_Adresse.show_pruefung_fehlender_Handelsvertrag();
			oVK_Adresse.show_pruefung_fehlender_Handelsvertrag();
			

			
		}
	}
	
	
	
	
	@Override
	public void mark_MUST_BE_Filled(boolean bMarked) throws myException
	{
		this.get_oTextField().setBackground(new E2_ColorEditBackground());
		
		if (bMarked)
		{
			this.get_oTextField().setBackground(new E2_ColorHelpBackground());
		}
	}

	@Override
	public MyE2_String get_InfoMessage() throws myException
	{
		return new MyE2_String("Bitte das Wiege / Eingangs / Ausgangsdatum erfassen ...");
	}

	@Override
	public boolean get_bIsCorrectFilled(boolean bMarkWhenFalse) throws myException
	{
		this.mark_MUST_BE_Filled(false);
		
		MyDate  oDate = new MyDate(this.get_oTextField().getText());
		
		if (bMarkWhenFalse) this.mark_MUST_BE_Filled( !(oDate.get_cErrorCODE().equals(MyDate.ALL_OK)));

		
		return (oDate.get_cErrorCODE().equals(MyDate.ALL_OK));
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen()
	{
		return null;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGeleertWerden()
	{
		return null;
	}

	@Override
	public void clean__Field() throws myException
	{
		this.get_oTextField().setText("");
	}

	@Override
	public Boolean get_IS_EK() throws myException
	{
		return null;
	}

	public MyDate  get_oActualDate()
	{
		return new MyDate(this.get_oTextField().getText());
	}

	@Override
	public void do_afterFieldWasFilled(String cFillValue) throws myException
	{
		
	}

	
	
}
