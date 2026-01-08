package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.RULES;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED.FKR_FB_MASK_BasicModuleContainer;

public class FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND extends XX_FULL_DAUGHTER
{
	
	private FKR_FB_MASK_BasicModuleContainer 				maskContainerCalling = null;
	private FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay 	maskDaughterInlay = null;
	private MyE2_Grid  	    								oGridContent = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

	public FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND(SQLFieldForPrimaryKey osqlField, FKR_FB_MASK_BasicModuleContainer mask_Container) throws myException
	{
		super(osqlField);
		this.maskContainerCalling = mask_Container;
		this.maskDaughterInlay = new FKR_FB_MASK_FULL_DAUGHTER_FILTER_AND__Inlay(this.maskContainerCalling);
	}

	@Override
	public Component build_content_for_Mask(String idFilter_F, String idFilter_UF, String cMASK_STATUS) throws myException
	{
		this.maskDaughterInlay.set_ID_From_Calling_Record(idFilter_UF);
		this.maskDaughterInlay.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");
		this.oGridContent.add(this.maskDaughterInlay, E2_INSETS.I(0,0,0,0));
		return this.oGridContent;
	}

	@Override
	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("Bedingungen können erst nach dem ersten Speichervorgang erfaßt werden!"));
	}

	@Override
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException
	{
		this.maskDaughterInlay.set_ListButtonsEnabled(false, true);
		
		if (!cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)) {
			this.maskDaughterInlay.set_ListButtonsEnabled(true, true);
		}

	}

	@Override
	public void prepare_DaughterContentForNew() throws myException
	{
		this.build_content_for_Mask("-1", "-1", E2_ComponentMAP.STATUS_UNDEFINED);
		this.oGridContent.removeAll();
		this.oGridContent.add(new MyE2_Label(new MyE2_String("Bedingungen können erst nach dem ersten Speichervorgang erfaßt werden!")));
		
	}

}
