package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Date;

import nextapp.echo2.app.Extent;
import nextapp.echo2.extras.app.CalendarSelect;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class FU_MASK_LoadDatum extends MyE2_Button
{

	private CalendarSelect oSelCal = new CalendarSelect();
	private MyE2_Button    oButtonAbholVon = new MyE2_Button(new MyE2_String("Abhol.von"));
	private MyE2_Button    oButtonAbholBis = new MyE2_Button(new MyE2_String("Abhol.bis"));
	private MyE2_Button    oButtonLieferVon = new MyE2_Button(new MyE2_String("Lieferung.von"));
	private MyE2_Button    oButtonLieferBis = new MyE2_Button(new MyE2_String("Lieferung.bis"));
	
	
	public FU_MASK_LoadDatum()
	{
		super(E2_ResourceIcon.get_RI("calendar.png"),true);
		
		this.add_oActionAgent(new ownAction());
		
		this.oButtonAbholVon.add_oActionAgent(new actionSetDate());
		this.oButtonAbholBis.add_oActionAgent(new actionSetDate());
		this.oButtonLieferVon.add_oActionAgent(new actionSetDate());
		this.oButtonLieferBis.add_oActionAgent(new actionSetDate());
	}

	
	
	private class ownAction extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			FU_MASK_LoadDatum oThis = FU_MASK_LoadDatum.this;
			
			E2_ComponentMAP oMap = FU_MASK_LoadDatum.this.EXT().get_oComponentMAP();
			
			MyE2_Row oRowBasic = new MyE2_Row();

			oRowBasic.add(oThis.oSelCal, E2_INSETS.I_1_1_1_1);

			MyE2_Column oColButtons = new MyE2_Column();
			oColButtons.add(oThis.oButtonAbholVon, E2_INSETS.I_1_1_1_1);
			oColButtons.add(oThis.oButtonAbholBis, E2_INSETS.I_1_1_1_1);
			oColButtons.add(oThis.oButtonLieferVon, E2_INSETS.I_1_1_1_1);
			oColButtons.add(oThis.oButtonLieferBis, E2_INSETS.I_1_1_1_1);

			oRowBasic.add(oColButtons,E2_INSETS.I_0_0_0_0);
			
			String cAktuellesDatum = oMap.get_cActualDBValueFormated("DATUM_ABHOLUNG");
			
			Date dActual = new Date();                                    //voreinstellung
			if (S.isFull(cAktuellesDatum))
			{
				TestingDate oDate = new TestingDate(cAktuellesDatum);
				if (oDate.testing())
				{
					dActual = oDate.get_Calendar().getTime();
				}
			}
			
			E2_ContainerDatePopup oPopUp = new E2_ContainerDatePopup();
			
			FU_MASK_LoadDatum.this.oSelCal.setDate(dActual);
			
			oPopUp.add(oRowBasic, E2_INSETS.I_10_10_10_10);
			
			oPopUp.CREATE_AND_SHOW_POPUPWINDOW(new Extent(300), new Extent(250), new MyE2_String("Bitte wählen Sie .."));
			
		}
		
	}
	
	
	
	private class actionSetDate extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			//zuerst gucken, von welchen button es kommt und dann das richtige feld setzen
			MyE2_Button oButtonSource = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
			FU_MASK_LoadDatum oThis = FU_MASK_LoadDatum.this;
			E2_ComponentMAP oMap = FU_MASK_LoadDatum.this.EXT().get_oComponentMAP();
			
			String cDatumFormated = myDateHelper.FormatDateNormal(oThis.oSelCal.getDate());
			
			if (oButtonSource==oThis.oButtonAbholVon)
			{
				((MyE2IF__DB_Component)oMap.get__Comp("DATUM_ABHOLUNG")).set_cActualMaskValue(cDatumFormated);
				if (S.isEmpty(((MyE2IF__DB_Component)oMap.get__Comp("DATUM_ABHOLUNG_ENDE")).get_cActualMaskValue()))
				{
					((MyE2IF__DB_Component)oMap.get__Comp("DATUM_ABHOLUNG_ENDE")).set_cActualMaskValue(cDatumFormated);
				}
			}
			if (oButtonSource==oThis.oButtonAbholBis)
			{
				((MyE2IF__DB_Component)oMap.get__Comp("DATUM_ABHOLUNG_ENDE")).set_cActualMaskValue(cDatumFormated);
			}
			
			if (oButtonSource==oThis.oButtonLieferVon)
			{
				((MyE2IF__DB_Component)oMap.get__Comp("DATUM_ANLIEFERUNG")).set_cActualMaskValue(cDatumFormated);
				if (S.isEmpty(((MyE2IF__DB_Component)oMap.get__Comp("DATUM_ANLIEFERUNG_ENDE")).get_cActualMaskValue()))
				{
					((MyE2IF__DB_Component)oMap.get__Comp("DATUM_ANLIEFERUNG_ENDE")).set_cActualMaskValue(cDatumFormated);
				}
			}
			if (oButtonSource==oThis.oButtonLieferBis)
			{
				((MyE2IF__DB_Component)oMap.get__Comp("DATUM_ANLIEFERUNG_ENDE")).set_cActualMaskValue(cDatumFormated);
			}
			
			
		}
		
	}
	
	
	
	
	private class E2_ContainerDatePopup extends E2_BasicModuleContainer
	{

		public E2_ContainerDatePopup()
		{
			super();
		}
		
	}

	
	
	
	
}
