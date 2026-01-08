package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;

public class FUK__MASK_KostenFullDaughterInlay extends XX_FULL_DAUGHTER
{
	private FUK__LIST_BasicModule_Inlay  oInlayAufgaben = null;
	
	public FUK__MASK_KostenFullDaughterInlay(SQLFieldForPrimaryKey osqlField, String MODULKENNER_MASK) throws myException 
	{
		super(osqlField);
		
		this.oInlayAufgaben = new FUK__LIST_BasicModule_Inlay(MODULKENNER_MASK,false);
		
	}

	@Override
	public Component build_content_for_Mask(String cValueFormated,String cValueUnformated, String cMASK_STATUS) throws myException 
	{
		this.set_buttons_IN_Status(cMASK_STATUS);   // die buttons passend einstellen
		
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			return new MyE2_Label(new MyE2_String("Positionen können erst erfasst werden, wenn der Vorgangskopf gespeichert wurde!"));
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			
			this.oInlayAufgaben.set_ID_From_Calling_Record(cValueUnformated);
			this.oInlayAufgaben.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");    
			return this.oInlayAufgaben;
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{

			this.oInlayAufgaben.set_ID_From_Calling_Record(cValueUnformated);
			this.oInlayAufgaben.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");    
			return this.oInlayAufgaben;
		}
		else
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
		
	}


	@Override
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException 
	{
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.oInlayAufgaben.set_ListButtonsEnabled(true, true);
		}
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.oInlayAufgaben.set_ListButtonsEnabled(true, true);
		}

	}

	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new MyE2_Label(new MyE2_String("Fuhren-Kostenpositionen können erst erfasst werden, wenn die Fuhre gespeichert wurde!")));

	}

	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}


}
