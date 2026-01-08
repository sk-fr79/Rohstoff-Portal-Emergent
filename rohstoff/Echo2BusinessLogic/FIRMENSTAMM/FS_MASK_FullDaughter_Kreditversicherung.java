package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH.KV_HEAD_LIST_BasicModule_Inlay;

public class FS_MASK_FullDaughter_Kreditversicherung extends XX_FULL_DAUGHTER
{
	
	private KV_HEAD_LIST_BasicModule_Inlay 	oInlay_VersicherungsKopf = null;;
	
	private String   						ActualMaskStatus = null;
	

	public FS_MASK_FullDaughter_Kreditversicherung(SQLFieldForPrimaryKey osqlField, KV_HEAD_LIST_BasicModule_Inlay Inlay_VersicherungsKopf)
	{
		super(osqlField);
		this.oInlay_VersicherungsKopf = Inlay_VersicherungsKopf;
	}

	@Override
	public Component build_content_for_Mask(String cValueFormated, String cValueUnformated, String cMASK_STATUS) throws myException
	{
		this.set_buttons_IN_Status(cMASK_STATUS);   // die buttons passend einstellen
		
		this.ActualMaskStatus = cMASK_STATUS;
		
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) || cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY))
		{
			return new MyE2_Label(new MyE2_String("Positionen können erst erfasst werden, wenn der Vorgangskopf gespeichert wurde!"));
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			
			this.oInlay_VersicherungsKopf.set_ID_From_Calling_Record(cValueUnformated);
			this.oInlay_VersicherungsKopf.get_oSelVector().doActionPassiv();
			return this.oInlay_VersicherungsKopf;
		}
		else if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{

			this.oInlay_VersicherungsKopf.set_ID_From_Calling_Record(cValueUnformated);
			this.oInlay_VersicherungsKopf.get_oSelVector().doActionPassiv();
			return this.oInlay_VersicherungsKopf;
		}
		else
			return new MyE2_Label(new MyE2_String("Fehler !!!"));
	}

	@Override
	public Component build_non_active_placeholder() throws myException
	{
		return new MyE2_Label(new MyE2_String("INAKTIV !!!"));
	}

	@Override
	public void set_buttons_IN_Status(String cMASK_STATUS) throws myException
	{
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			this.oInlay_VersicherungsKopf.set_ListButtonsEnabled(true, true);
		}
		if (cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW))
		{
			this.oInlay_VersicherungsKopf.set_ListButtonsEnabled(false, true);
		}

	}

	@Override
	public void prepare_DaughterContentForNew() throws myException
	{
		this.removeAll();
		this.add(new MyE2_Label(new MyE2_String("Kreditversicherungen können erst erfasst werden, wenn die Adresse gespeichert wurde!")));
	}

	
	public boolean get_bMaskStatus_IS_Edit()
	{
		return S.NN(this.ActualMaskStatus).equals(E2_ComponentMAP.STATUS_EDIT);
	}
	
}
