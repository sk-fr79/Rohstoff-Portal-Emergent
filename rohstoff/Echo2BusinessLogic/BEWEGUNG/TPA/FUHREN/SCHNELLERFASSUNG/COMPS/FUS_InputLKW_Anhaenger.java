package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopMiddleMenue;
import panter.gmbh.Echo2.components.MyE2_TextFieldWithEraser;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.__FUS_STANDARD_Element;

public class FUS_InputLKW_Anhaenger extends MyE2_TextFieldWithEraser implements __FUS_STANDARD_Element
{

	public FUS_InputLKW_Anhaenger()
	{
		super(true);
		this.set_iMaxInputSize(80);
		this.add_ZusatzComponent(new ownPopupMenueAnhaenger());
	}

	
	@Override
	public void mark_MUST_BE_Filled(boolean bMarked) throws myException
	{
		this.setBackground(new E2_ColorEditBackground());

		if (bMarked)
		{
			this.setBackground(new E2_ColorHelpBackground());
		}
	}

	@Override
	public MyE2_String get_InfoMessage() throws myException
	{
		return new MyE2_String("Bitte die LKW-Nummer ausfüllen");
	}

	@Override
	public boolean get_bIsCorrectFilled(boolean bMarkWhenFalse) throws myException
	{
		this.mark_MUST_BE_Filled(false);

		boolean bIsOK = S.isFull(this.getText());
		
		if (bMarkWhenFalse) this.mark_MUST_BE_Filled( !bIsOK);

		return bIsOK;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGefuelltSeinMuessen() throws myException
	{

		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();
		return vRueck;
	}

	@Override
	public Vector<__FUS_STANDARD_Element> get_KomponentenDieGeleertWerden() throws myException
	{
		Vector<__FUS_STANDARD_Element> vRueck = new Vector<__FUS_STANDARD_Element>();
		return vRueck;
	}

	@Override
	public void clean__Field() throws myException
	{
		this.setText("");
	}

	public MyBigDecimal  get_bd_Preis()
	{
		
		return new MyBigDecimal(this.getText());
		
	}


	@Override
	public Boolean get_IS_EK() throws myException
	{
		return Boolean.TRUE;
	}


	@Override
	public void do_afterFieldWasFilled(String cFillValue) throws myException
	{
		
	}

	
	private class ownPopupMenueAnhaenger extends MyE2_PopMiddleMenue
	{
		public ownPopupMenueAnhaenger()
		{
			super(E2_ResourceIcon.get_RI("lager_xxs.png"),E2_ResourceIcon.get_RI("lager_xxs__.png"));
			
			this.setToolTipText(new MyE2_String("Eigene Anhänger anzeigen").CTrans());
			
			String cQuery = 
				  "SELECT JT_MASCHINEN.KFZKENNZEICHEN FROM "+bibE2.cTO()+".JT_MASCHINEN" +
				  								" INNER JOIN "+bibE2.cTO()+".JT_MASCHINENTYP ON (JT_MASCHINEN.ID_MASCHINENTYP =JT_MASCHINENTYP.ID_MASCHINENTYP) " +
				  								" WHERE NVL(JT_MASCHINENTYP.IST_ANHAENGER,'N')='Y' AND " +
				  								" JT_MASCHINEN.KFZKENNZEICHEN IS NOT NULL ORDER BY JT_MASCHINEN.KFZKENNZEICHEN";

			String[][] arrKennzeichen = bibDB.EinzelAbfrageInArray(cQuery, "");
			
			if (arrKennzeichen!=null && arrKennzeichen.length>0)
			{
				for (int i=0;i<arrKennzeichen.length;i++)
				{
					this.addButton(new ownButton(arrKennzeichen[i][0]), true);
				}
			}
		}
		
		
		private class ownButton extends MyE2_Button
		{
			public ownButton(String cText)
			{
				super(cText);
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						FUS_InputLKW_Anhaenger.this.setText(ownButton.this.getText());
					}
				});
			}
		}
	}
	
	
}
