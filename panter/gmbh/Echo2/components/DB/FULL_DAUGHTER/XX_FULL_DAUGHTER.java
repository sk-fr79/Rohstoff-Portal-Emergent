package panter.gmbh.Echo2.components.DB.FULL_DAUGHTER;

import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.IF_GetInBorder;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.DAUGHTER_INTERFACE.IF_MaskDaughter_Activater;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

//2014-03-12: IF_MaskDaughter_Activater als interface zugefuegt

public abstract class XX_FULL_DAUGHTER extends MyE2_Column implements 	MyE2IF__DB_Component,
																		MyE2IF__Component, 
																		E2_IF_Copy,
																		IF_GetInBorder,
																		IF_MaskDaughter_Activater
{

//	private	E2_ContentPane					oContentPaneThisBelongsTo = null; 
	
	private MyE2EXT__DB_Component 			oEXTDB=new MyE2EXT__DB_Component(this);

	/*
	 * diese komponente merkt sich den status beim aufbau der maske, damit beim speichern
	 * unterschieden werden kann, ebenfalls werden diese fragen beim aktivieren gebraucht
	 */
	private String 							cActual_STATUS_MAP = null;
	private String 							cLastFormated_MaskValue = null;
	
	
	/*
	 * schalter, der das object aktiv macht, d.h. wenn innerhalb eines tabbed-panes mehrer komplexe toechter vorhanden sind,
	 * dann koennen diese die maske stark hindern, z.b. beim blaettern. dann kann das object beim klicken auf 
	 * den passenden tab aktiv gemacht werden und wird erst dann aufgebaut
	 */
	private boolean 						bIsActive = true;
	
	
	
	
	public XX_FULL_DAUGHTER(	SQLFieldForPrimaryKey 			osqlField)
	{
		super();
	        
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
	}


	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		throw new myExceptionCopy(myExceptionCopy.ERROR_COPY_NOT_IMPLEMENTED);
	}

	
	public void set_bIsComplexObject(boolean bisComplex)
	{
	}


	// das objekt ist immer complex
	public boolean get_bIsComplexObject()
	{
		return true;
	}

	public MyE2EXT__DB_Component EXT_DB()							{		return this.oEXTDB;		}
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)			{		this.oEXTDB = oEXT_DB;	}

	
	
	public void set_cActual_Formated_DBContent_To_Mask(String cValueFormated, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DBC_DaughterTable:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		this.cActual_STATUS_MAP = cMASK_STATUS;
		this.cLastFormated_MaskValue = cValueFormated;
		
		String cUnformated = this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_cStringForDataBase(cValueFormated, true, false);
		this.removeAll();
		
		if (this.bIsActive)
		{
			this.add(this.build_content_for_Mask(cValueFormated,cUnformated,cMASK_STATUS));
			
			// hier die von oben stammenden MyIntegrityWatcherVECTOR aus der mutter-maske uebernehmen
			// this.Transfer_Mother_IntegrityWatchers_To_Daughter_E2_ComponentMAPs();
			
			this.set_buttons_IN_Status(cMASK_STATUS);
		}
		else
		{
			this.add(this.build_non_active_placeholder());
		}
	}

	
	
	/**
	 * hier muss, je nach maskenzustand, die komponente in der maske gebaut werden
	 */
	public abstract Component build_content_for_Mask(String cValueFormated, String cValueUnformated, String cMASK_STATUS) throws myException;
	
	
	/*
	 * hier wird eine komponente gebaut, die anzeigt, dass das object im zustand "nicht active" ist
	 */
	public abstract Component build_non_active_placeholder() throws myException;
	
	
	/**
	 * hier wird der status der komponente definiert, d.h. welche knoepfe sind aktiv
	 */
	public abstract void set_buttons_IN_Status(String cMASK_STATUS) throws myException;

	
	
	public void 		prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		this.cActual_STATUS_MAP = E2_ComponentMAP.STATUS_NEW_EMPTY;
		this.cLastFormated_MaskValue = null;
		this.prepare_DaughterContentForNew();
		
		
	}
	
	public abstract void 	prepare_DaughterContentForNew() throws myException;
	
	
	public String 		get_cActualMaskValue() throws myException
	{
		return null;
	}
	
	
	public String 		get_cActualDBValueFormated() throws myException
	{
		return null;
	}
	
	public void 		set_cActualMaskValue(String cValue) throws myException
	{
	}
	
	public Vector<String>		get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return new Vector<String>();
	}
	
	public Vector<String>		get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return new Vector<String>();
	}
	

	/*
	 * wird hier im Standard soo eingestellt, dass die view-buttons noch offen sind
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT().get_bCanBeEnabled();
		
		
		if (!bVoraussetzung)
		{
			this.set_buttons_IN_Status(E2_ComponentMAP.STATUS_VIEW);
		}
		else
		{
			this.set_buttons_IN_Status(E2_ComponentMAP.STATUS_EDIT);
		}
	}

	
	/**
	 * aktiv / inaktiv schalten
	 */
	public void 	set_bIsActive(boolean isActive) throws myException
	{		
		this.bIsActive = isActive;
		if (this.cActual_STATUS_MAP != null && this.cLastFormated_MaskValue!=null)
		{
			this.set_cActual_Formated_DBContent_To_Mask(this.cLastFormated_MaskValue,this.cActual_STATUS_MAP, null);
		}
	}
	
	public String 				get_cActual_STATUS_MAP()			{		return cActual_STATUS_MAP;	}
	public boolean 				get_bIsActive()						{		return bIsActive;	}
	public String 				get_cLastFormated_MaskValue()		{		return cLastFormated_MaskValue;	}
	
	
	//2011-02-10: Grid in Rahmen
	public MyE2_Grid get_InBorderGrid(Border oBorder, Extent oExt, Insets oInsets)
	{
		
		MutableStyle  oStyle = new MutableStyle();
		oStyle.setProperty(Grid.PROPERTY_BORDER, oBorder!=null?oBorder:new Border(1,Color.BLACK,Border.STYLE_SOLID));
		oStyle.setProperty(Grid.PROPERTY_INSETS, oInsets!=null?oInsets:E2_INSETS.I_1_1_1_1);
		if (oExt!=null)
		{		
			oStyle.setProperty(Grid.PROPERTY_WIDTH, oExt);
		}
		
		MyE2_Grid_InLIST oGridRueck = new MyE2_Grid_InLIST(1,oStyle);

		oGridRueck.add(this);
		return oGridRueck;
	}

}
