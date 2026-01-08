package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FUK__INFO_Grid_BemerkungenExtender extends MyE2_Grid_InLIST
{

	private static int[] iBreiteFull = {14,100};
	private static int[] iBreiteLeer = {14};
	
	private String       cBemerkung = "";
	
	private FUK__INFO_Grid   oInfoBlockThisBelongsTo = null;
	
	public FUK__INFO_Grid_BemerkungenExtender(RECORD_VPOS_TPA_FUHRE_KOSTEN recKosten, FUK__INFO_Grid InfoBlockThisBelongsTo) throws myException
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oInfoBlockThisBelongsTo = InfoBlockThisBelongsTo;
		
		this.set_Spalten(FUK__INFO_Grid_BemerkungenExtender.iBreiteLeer);
		if (recKosten!=null)
		{
			if (S.isFull(recKosten.get_INFO_KOSTEN_cUF()))
			{
				this.cBemerkung = recKosten.get_INFO_KOSTEN_cUF();
				this.add(new ownButtonOpen(),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_1_0_1_0));
			}
			else
			{
				this.cBemerkung="";
				this.add(new MyE2_Label(E2_ResourceIcon.get_RI("extender_right.png")),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_1_0_1_0));
			}
		}
		
	}

	
	public void set_open()
	{
		this.removeAll();
		this.set_Spalten(FUK__INFO_Grid_BemerkungenExtender.iBreiteFull);
		this.add(new ownButtonClose(),LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_1_0_1_0));
		//oThis.add(new MyE2_Label(oThis.cBemerkung, new E2_FontPlain(), true), E2_INSETS.I_1_0_1_0);
		this.add(new ownTextArea(this.cBemerkung), LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_1_0_1_0));
		
	}
	

	public void set_close()
	{
		this.removeAll();
		this.set_Spalten(FUK__INFO_Grid_BemerkungenExtender.iBreiteLeer);
		this.add(new ownButtonOpen(), E2_INSETS.I_1_0_1_0);
		
	}
	

	
	
	private class ownButtonOpen extends MyE2_Button
	{

		public ownButtonOpen()
		{
			super(E2_ResourceIcon.get_RI("extender_right.png"));
			
			
			
			if (S.isFull(FUK__INFO_Grid_BemerkungenExtender.this.cBemerkung))
			{
				this.setToolTipText(FUK__INFO_Grid_BemerkungenExtender.this.cBemerkung);
			}
			
			//alle aus diesem block oeffnen

			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					FUK__INFO_Grid InfoBlockThisBelongsTo = FUK__INFO_Grid_BemerkungenExtender.this.oInfoBlockThisBelongsTo;
					
					Component[] Comps = InfoBlockThisBelongsTo.getComponents();
					
					for (int i=0;i<Comps.length;i++)
					{
						if (Comps[i] instanceof FUK__INFO_Grid_BemerkungenExtender)
						{
							((FUK__INFO_Grid_BemerkungenExtender)Comps[i]).set_open();
						}
					}
					
					// FUK__INFO_Grid_BemerkungenExtender.this.set_open();
					
				}
			});
		}
		
	}

	
	private class ownButtonClose extends MyE2_Button
	{

		public ownButtonClose()
		{
			super(E2_ResourceIcon.get_RI("extender_left.png"));

			if (S.isFull(FUK__INFO_Grid_BemerkungenExtender.this.cBemerkung))
			{
				this.setToolTipText(FUK__INFO_Grid_BemerkungenExtender.this.cBemerkung);
			}

			
			this.add_oActionAgent(new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
//					FUK__INFO_Grid_BemerkungenExtender.this.set_close();
					
					FUK__INFO_Grid InfoBlockThisBelongsTo = FUK__INFO_Grid_BemerkungenExtender.this.oInfoBlockThisBelongsTo;
					
					Component[] Comps = InfoBlockThisBelongsTo.getComponents();
					
					for (int i=0;i<Comps.length;i++)
					{
						if (Comps[i] instanceof FUK__INFO_Grid_BemerkungenExtender)
						{
							((FUK__INFO_Grid_BemerkungenExtender)Comps[i]).set_close();
						}
					}

					
				}
			});
			
			
		}
		
	}

	
	private class ownTextArea extends MyE2_TextArea
	{

		public ownTextArea(String cText)
		{
			super(cText, 200, -1, 3);
			
			this.setBackground(new E2_ColorBase());
			this.setEnabled(false);
		}
		
		
	}
	
	
}
