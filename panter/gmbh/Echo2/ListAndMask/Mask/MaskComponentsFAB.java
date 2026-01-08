package panter.gmbh.Echo2.ListAndMask.Mask;

import java.util.StringTokenizer;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.FieldValidator_FieldDisabled;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class MaskComponentsFAB
{

	
	/** 
	 * @param cLABEL_LIST = Liste aus SQLFieldMAP-Keys, die angefuegt werden sollen, getrennt mit | 
	 *        in den Label-tokens koennen Laenge und Hoehe mit <L100L> und <H100H> angehaengt werden
	 * @param oSQLFieldMap = Die SQLFieldMap , wo die SQLFields rausgenommen werden
	 * @param bForceLabel = wenn true, werden keine Eingabefelder, sondern Labels generiert
	 * @param bForceReadOnly 
	 * @param oTargetMap = E2_ComponentMAP, der die felder angefuegt werden
	 * @param iTextFieldMaxPixelSize 
	 * @return 
	 * @throws myException
	 */
	public static void  addStandardComponentsToMAP(  	String 			cLABEL_LIST,
														String 			cTitleForUserLIST,
														SQLFieldMAP 	oSQLFieldMap, 
														boolean 		bForceLabel, 
														boolean 		bForceReadOnly, 
														E2_ComponentMAP oTargetMap, 
														int iTextFieldMaxPixelSize) throws myException
	{
		
		StringTokenizer oTrennerKEYS = new StringTokenizer(cLABEL_LIST,"|");
		StringTokenizer oTrennerUserTitles = new StringTokenizer(cTitleForUserLIST,"|");
		
		if (oTrennerKEYS.countTokens() != oTrennerUserTitles.countTokens())
			throw new myException("MaskComponentsFAB:addStandardComponentsToMAP:Number of Keys and Number of User-Labels mus be the same !");
		
		while (oTrennerKEYS.hasMoreTokens() && oTrennerUserTitles.hasMoreTokens())
		{
			String cLabel = oTrennerKEYS.nextToken();
			
			//hier checken, ob ein Laengen oder hoehen-token vorhanden ist
			StringBuffer  sbLabel = new StringBuffer(cLabel);
			Integer  iWidth = S.get_InWertInStringCode(sbLabel, "L");
			Integer  iHeight = S.get_InWertInStringCode(sbLabel, "H");
			cLabel = sbLabel.toString();
			
			String cLabelForUser = oTrennerUserTitles.nextToken();
			boolean bVorsatz = false;
			if (cLabelForUser.startsWith("@"))
			{
				cLabelForUser=cLabelForUser.substring(1);
				bVorsatz=true;
			}
			SQLField oField =   oSQLFieldMap.get_SQLField(cLabel);
			MyE2IF__DB_Component oComp = null;
			oComp = oTargetMap.add_Component(MaskComponentsFAB.createStandardComponent(oField,bForceLabel, bForceReadOnly, iTextFieldMaxPixelSize, false,iWidth,iHeight),new MyE2_String(cLabelForUser));
			
			if (bVorsatz)
			{
				if (oComp instanceof MyE2_DB_CheckBox)
				{
					((MyE2_DB_CheckBox)oComp).set_DescriptionAsCheckboxText(true);
				}
			}
			
			
		}
		
	}
	

	
	
	/** 
	 * @param cLABEL_LIST = Liste aus SQLFieldMAP-Keys, die angefuegt werden sollen, getrennt mit |
	 * @param oSQLFieldMap = Die SQLFieldMap , wo die SQLFields rausgenommen werden
	 * @param bForceLabel = wenn true, werden keine Eingabefelder, sondern Labels generiert
	 * @param bForceReadOnly 
	 * @param oTargetMap = E2_ComponentMAP, der die felder angefuegt werden
	 * @param iTextFieldMaxPixelSize 
	 * @return 
	 * @throws myException
	 */
	public static void  addStandardComponentsToMAP_m_Zusatz(  	String 			cLABEL_LIST,
														String 			cTitleForUserLIST,
														SQLFieldMAP 	oSQLFieldMap, 
														boolean 		bForceLabel, 
														boolean 		bForceReadOnly, 
														E2_ComponentMAP oTargetMap, 
														int iTextFieldMaxPixelSize) throws myException
	{
		
		StringTokenizer oTrennerKEYS = new StringTokenizer(cLABEL_LIST,"|");
		StringTokenizer oTrennerUserTitles = new StringTokenizer(cTitleForUserLIST,"|");
		
		if (oTrennerKEYS.countTokens() != oTrennerUserTitles.countTokens())
			throw new myException("MaskComponentsFAB:addStandardComponentsToMAP:Number of Keys and Number of User-Labels mus be the same !");

		
		String[] cLABEL__LIST = new String[oTrennerKEYS.countTokens()];
		String[] cTitel__LIST = new String[oTrennerUserTitles.countTokens()];
		
		int i=0;
		while (oTrennerKEYS.hasMoreTokens() && oTrennerUserTitles.hasMoreTokens())
		{
			cLABEL__LIST[i]=oTrennerKEYS.nextToken();
			cTitel__LIST[i]=oTrennerUserTitles.nextToken();
			i++;
		}	
		
		
		MaskComponentsFAB.addStandardComponentsToMAP(cLABEL__LIST, cTitel__LIST, oSQLFieldMap, bForceLabel, bForceReadOnly, oTargetMap, iTextFieldMaxPixelSize);
		
		
	}

	
	
	
	
	
	
	public static void  addStandardComponentsToMAP(  	String[]		cLABEL_LIST,
														String[]		cTitleForUserLIST,
														SQLFieldMAP 	oSQLFieldMap, 
														boolean 		bForceLabel, 
														boolean 		bForceReadOnly, 
														E2_ComponentMAP oTargetMap, 
														int iTextFieldMaxPixelSize) throws myException
	{
		
		
		if (cLABEL_LIST == null || cLABEL_LIST.length==0 || cTitleForUserLIST == null || cTitleForUserLIST.length==0 ||cLABEL_LIST.length !=cTitleForUserLIST.length)
		throw new myException("MaskComponentsFAB:addStandardComponentsToMAP:Number of Keys and Number of User-Labels mus be the same  and MUST NOT BE EMPTY !!!");
		
		for (int i=0;i<cLABEL_LIST.length;i++)
		{
			String cLabel = 				cLABEL_LIST[i];
			String cLabelForUser = 			cTitleForUserLIST[i];

			boolean bLabelGleichLabel4User = cLabel.equals(cLabelForUser);
			
			int      iIndividuellLen = 		-1;
			int      iIndividuellHeight = 	-1;
			boolean  bIndividualDisabled = 	false;
			
			//den String untersuchen, ob ein Laengen-Beschreibungstext drinne ist
			StringBuffer sbLabel = new StringBuffer(cLabel);
			Integer iIndividLen = S.get_InWertInStringCode(sbLabel, "L");
			if (iIndividLen!=null)
			{
				iIndividuellLen = iIndividLen.intValue();
				cLabel = sbLabel.toString();
			}
			//den String untersuchen, ob ein Anzahl-Zeilen-Beschreibungstext drinne ist
			StringBuffer sbLabel2 = new StringBuffer(cLabel);
			Integer iIndividHeight = S.get_InWertInStringCode(sbLabel2, "H");
			if (iIndividHeight!=null)
			{
				iIndividuellHeight = iIndividHeight.intValue();
				cLabel = sbLabel2.toString();
			}

			//den String untersuchen, ob das diasbled-Flag gesetzt ist
			StringBuffer sbLabel3 = new StringBuffer(cLabel);
			Integer iTest = S.get_InWertInStringCode(sbLabel3, "DA");
			if (iTest!=null && iTest.intValue()==1)
			{
				bIndividualDisabled = true;
				cLabel = sbLabel3.toString();
			}
			
			//den String untersuchen, ob ein FontSize-Flag gesetzt ist
			StringBuffer sbLabel4 = new StringBuffer(cLabel);
			Integer iFontsize = S.get_InWertInStringCode(sbLabel4, "F");
			if (iFontsize!=null && iFontsize.intValue()!=0)
			{
				cLabel = sbLabel4.toString();
			}

			//den String untersuchen, ob ein Tooltip-Text gesetzt ist
			StringBuffer sbLabel5 = new StringBuffer(cLabel);
			String cToolTip = S.get_cWertInStringCode(sbLabel5, "TT");
			if (cToolTip!=null)
			{
				cLabel = sbLabel5.toString();
			}
			
			
			
			if (bLabelGleichLabel4User)
			{
				cLabelForUser = cLabel;                 //sonst tauchen formatierungen in den tooltips auf
			}
			
			
			boolean bVorsatz = false;
			if (cLabelForUser.startsWith("@"))
			{
				cLabelForUser=cLabelForUser.substring(1);
				bVorsatz=true;
			}
			SQLField oField =  	 	oSQLFieldMap.get_SQLField(cLabel);
			MyE2IF__DB_Component oComp = oTargetMap.add_Component(MaskComponentsFAB.createStandardComponent(oField,bForceLabel, (bIndividualDisabled||bForceReadOnly), iTextFieldMaxPixelSize, (iIndividuellHeight>0)),new MyE2_String(cLabelForUser));

			//falls die laenge mit einem <LxxxL> an den feldnamen angehaengt wurde, dann diesen hier zwingend eindefinieren
			if (iIndividuellLen>0)
			{
				if (oComp instanceof MyE2_DB_TextField)
				{
					((MyE2_DB_TextField)oComp).set_iWidthPixel(iIndividuellLen);
				}
				if (oComp instanceof MyE2_DB_TextArea)
				{
					((MyE2_DB_TextArea)oComp).set_iWidthPixel(iIndividuellLen);
				}
			}
			
			//falls die laenge mit einem <HxxxH> an den feldnamen angehaengt wurde, dann diesen hier zwingend eindefinieren
			if (iIndividuellHeight>0)
			{
				if (oComp instanceof MyE2_DB_TextArea)
				{
					((MyE2_DB_TextArea)oComp).set_iRows(iIndividuellHeight);
				}
			}
			
			
			//falls Font angegeben wurde
			if (iFontsize!=null)
			{
				if (oComp instanceof MyE2_DB_TextField)   	{	((MyE2_DB_TextField)oComp).setFont(new E2_FontPlain(iFontsize));					}
				if (oComp instanceof MyE2_DB_TextArea)		{	((MyE2_DB_TextArea)oComp).setFont(new E2_FontPlain(iFontsize));						}
				if (oComp instanceof MyE2_DB_CheckBox)		{	((MyE2_DB_CheckBox)oComp).setFont(new E2_FontPlain(iFontsize));						}
				if (oComp instanceof MyE2_DB_Label)			{	((MyE2_DB_Label)oComp).setFont(new E2_FontPlain(iFontsize));						}
			}
			
			
			//falls Tooltips angegeben wurden
			if (cToolTip!=null)
			{
				if (oComp instanceof MyE2_DB_TextField)   	{	((MyE2_DB_TextField)oComp).setToolTipText(new MyE2_String(cToolTip).CTrans());		}
				if (oComp instanceof MyE2_DB_TextArea)		{	((MyE2_DB_TextArea)oComp).setToolTipText(new MyE2_String(cToolTip).CTrans());		}
				if (oComp instanceof MyE2_DB_CheckBox)		{	((MyE2_DB_CheckBox)oComp).setToolTipText(new MyE2_String(cToolTip).CTrans());		}
				if (oComp instanceof MyE2_DB_Label)			{	((MyE2_DB_Label)oComp).setToolTipText(new MyE2_String(cToolTip).CTrans());			}
			}
			
			
			
			if (bVorsatz)
			{
				if (oComp instanceof MyE2_DB_CheckBox)
				{
					((MyE2_DB_CheckBox)oComp).set_DescriptionAsCheckboxText(true);
				}
			}
		}

	}

	
	
	

	
	
	
	/*
	 * methode versucht, fuer ein SQLFeld eine standard-komponente zu bauen.
	 */
	public static MyE2IF__DB_Component createStandardComponent(	SQLField 	oSQLField,
																boolean 	bForceLabel, 
																boolean 	bForceReadOnly, 
																int 		iTextFieldMaxPixelSize, 
																boolean 	bForceTextBlock) throws myException
	{
		
//		if (oSQLField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_TEXT))
//		{
//			if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()==1)
//			{
//				MyE2_DB_CheckBox oCheckBox = new MyE2_DB_CheckBox(oSQLField);
//				return oCheckBox;
//			}
//			else
//			{
//				if (!bForceLabel)
//				{
//					if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>1 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=10)
//					{
//						if (!bForceTextBlock)
//						{
//							MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
//							oTextField.set_iWidthPixel(MIN(100,iTextFieldMaxPixelSize));
//							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//							return oTextField;
//						}
//						else
//						{
//							MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
//							oTextField.set_iWidthPixel(MIN(100,iTextFieldMaxPixelSize));
//							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//							oTextField.set_iRows(5);
//							return oTextField;
//						}
//					}
//					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=11 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=29)
//					{
//						if (!bForceTextBlock)
//						{
//							MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
//							oTextField.set_iWidthPixel(MIN(200,iTextFieldMaxPixelSize));
//							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//							return oTextField;
//						}
//						else
//						{
//							MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
//							oTextField.set_iWidthPixel(MIN(200,iTextFieldMaxPixelSize));
//							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//							oTextField.set_iRows(5);
//							return oTextField;
//						}
//					}
//					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=30 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=79)
//					{
//						if (!bForceTextBlock)
//						{
//							MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
//							oTextField.set_iWidthPixel(MIN(350,iTextFieldMaxPixelSize));
//							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//							return oTextField;
//						}
//						else
//						{
//							MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
//							oTextField.set_iWidthPixel(MIN(350,iTextFieldMaxPixelSize));
//							oTextField.set_iRows(5);
//							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//							return oTextField;
//						}
//					}
//					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=80 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=119)
//					{
//						if (!bForceTextBlock)
//						{
//							MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
//							oTextField.set_iWidthPixel(MIN(500,iTextFieldMaxPixelSize));
//							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//							return oTextField;
//						}
//						else
//						{
//							MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
//							oTextField.set_iWidthPixel(MIN(500,iTextFieldMaxPixelSize));
//							oTextField.set_iRows(5);
//							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//							return oTextField;
//						}
//					}
//					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=120  && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=300)
//					{
//						MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
//						oTextField.set_iWidthPixel(MIN(500,iTextFieldMaxPixelSize));
//						oTextField.set_iRows(5);
//						if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//						return oTextField;
//					}
//					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>300)
//					{
//						MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
//						oTextField.set_iWidthPixel(MIN(500,iTextFieldMaxPixelSize));
//						oTextField.set_iRows(8);
//						if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//						return oTextField;
//					}
//				}
//				else
//				{
//					MyE2_DB_Label oLabel = new MyE2_DB_Label(oSQLField);
//					if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=30)
//					{
//						oLabel.setLineWrap(false);    // kleine labels ohne umbruch
//					}
//					else
//					{
//						oLabel.setLineWrap(true);     // grosse mit umbruch
//					}
//					return oLabel;
//				}
//				
//			}
//		}
//		else if (oSQLField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
//		{
//			if (!bForceLabel)
//			{
//				int iColumnLenght = (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()*11);
//				MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
//				oTextField.set_iWidthPixel(MIN(iColumnLenght,iTextFieldMaxPixelSize));
//				oTextField.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
//				if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//				return oTextField;
//
//			}
//			else
//			{
//				MyE2_DB_Label oLabel = new MyE2_DB_Label(oSQLField);
//				oLabel.setLineWrap(false);    // nummerische labels immer ohne umbruch
//				return oLabel;
//			}
//		}
//		else if (oSQLField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
//		{
//			if (!bForceLabel)
//			{
//				MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
//				oTextField.set_iWidthPixel(MIN(100,iTextFieldMaxPixelSize));
//				if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
//				return oTextField;
//			}
//			else
//			{
//				MyE2_DB_Label oLabel = new MyE2_DB_Label(oSQLField);
//				oLabel.setLineWrap(false);    // nummerische labels immer ohne umbruch
//				return oLabel;
//			}
//			
//		}
//		
//		/*
//		 * fuer den notfall immer einen label uebergeben
//		 */
//		return new  MyE2_DB_Label(oSQLField);
		
		return MaskComponentsFAB.createStandardComponent(oSQLField, bForceLabel, bForceReadOnly, iTextFieldMaxPixelSize, bForceTextBlock,null,null);
		
	}
	
	

	
	
	/*
	 * methode versucht, fuer ein SQLFeld eine standard-komponente zu bauen.
	 */
	public static MyE2IF__DB_Component createStandardComponent(	SQLField 	oSQLField,
																boolean 	bForceLabel, 
																boolean 	bForceReadOnly, 
																int 		iTextFieldMaxPixelSize, 
																boolean 	bForceTextBlock,
																Integer     iWidth,
																Integer     iHeight) throws myException
	{
		
		if (iWidth != null)
		{
			if (iHeight !=null && iHeight>1)
			{
				MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
				oTextField.set_iWidthPixel(iWidth);
				oTextField.set_iRows(iHeight);
				if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
				return oTextField;
			}
			else
			{
				MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
				oTextField.set_iWidthPixel(iWidth);
				if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
				return oTextField;
			}
		}
		
		
		
		if (oSQLField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_TEXT))
		{
			if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()==1)
			{
				MyE2_DB_CheckBox oCheckBox = new MyE2_DB_CheckBox(oSQLField);
				return oCheckBox;
			}
			else
			{
				if (!bForceLabel)
				{
					if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>1 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=10)
					{
						if (!bForceTextBlock)
						{
							MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
							oTextField.set_iWidthPixel(MIN(100,iTextFieldMaxPixelSize));
							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
							return oTextField;
						}
						else
						{
							MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
							oTextField.set_iWidthPixel(MIN(100,iTextFieldMaxPixelSize));
							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
							oTextField.set_iRows(5);
							return oTextField;
						}
					}
					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=11 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=29)
					{
						if (!bForceTextBlock)
						{
							MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
							oTextField.set_iWidthPixel(MIN(200,iTextFieldMaxPixelSize));
							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
							return oTextField;
						}
						else
						{
							MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
							oTextField.set_iWidthPixel(MIN(200,iTextFieldMaxPixelSize));
							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
							oTextField.set_iRows(5);
							return oTextField;
						}
					}
					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=30 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=79)
					{
						if (!bForceTextBlock)
						{
							MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
							oTextField.set_iWidthPixel(MIN(350,iTextFieldMaxPixelSize));
							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
							return oTextField;
						}
						else
						{
							MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
							oTextField.set_iWidthPixel(MIN(350,iTextFieldMaxPixelSize));
							oTextField.set_iRows(5);
							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
							return oTextField;
						}
					}
					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=80 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=119)
					{
						if (!bForceTextBlock)
						{
							MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
							oTextField.set_iWidthPixel(MIN(500,iTextFieldMaxPixelSize));
							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
							return oTextField;
						}
						else
						{
							MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
							oTextField.set_iWidthPixel(MIN(500,iTextFieldMaxPixelSize));
							oTextField.set_iRows(5);
							if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
							return oTextField;
						}
					}
					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=120  && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=300)
					{
						MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
						oTextField.set_iWidthPixel(MIN(500,iTextFieldMaxPixelSize));
						oTextField.set_iRows(5);
						if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
						return oTextField;
					}
					else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>300)
					{
						MyE2_DB_TextArea oTextField = new MyE2_DB_TextArea(oSQLField);
						oTextField.set_iWidthPixel(MIN(500,iTextFieldMaxPixelSize));
						oTextField.set_iRows(8);
						if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
						return oTextField;
					}
				}
				else
				{
					MyE2_DB_Label oLabel = new MyE2_DB_Label(oSQLField);
					if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=30)
					{
						oLabel.setLineWrap(false);    // kleine labels ohne umbruch
					}
					else
					{
						oLabel.setLineWrap(true);     // grosse mit umbruch
					}
					return oLabel;
				}
				
			}
		}
		else if (oSQLField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
		{
			if (!bForceLabel)
			{
				int iColumnLenght = (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()*11);
				MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
				oTextField.set_iWidthPixel(MIN(iColumnLenght,iTextFieldMaxPixelSize));
				oTextField.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
				if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
				return oTextField;

			}
			else
			{
				MyE2_DB_Label oLabel = new MyE2_DB_Label(oSQLField);
				oLabel.setLineWrap(false);    // nummerische labels immer ohne umbruch
				return oLabel;
			}
		}
		else if (oSQLField.get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
		{
			if (!bForceLabel)
			{
				MyE2_DB_TextField oTextField = new MyE2_DB_TextField(oSQLField);
				oTextField.set_iWidthPixel(MIN(100,iTextFieldMaxPixelSize));
				if (bForceReadOnly || !oSQLField.get_bFieldCanBeWrittenInMask()) oTextField.EXT().add_ValidatorsEnabledAllowd(new FieldValidator_FieldDisabled());
				return oTextField;
			}
			else
			{
				MyE2_DB_Label oLabel = new MyE2_DB_Label(oSQLField);
				oLabel.setLineWrap(false);    // nummerische labels immer ohne umbruch
				return oLabel;
			}
			
		}
		
		/*
		 * fuer den notfall immer einen label uebergeben
		 */
		return new  MyE2_DB_Label(oSQLField);
	}
	

	
	
		
	
	
	public static int MIN(int calcwert, int maxwert)
	{
		int iRueck = calcwert;
		if (maxwert>0)
			if (calcwert > maxwert)
			iRueck = maxwert;
		
		return iRueck;
	}
	
	
	public static int get_iStandardLabelLength_In_Pixel(SQLField oSQLField)
	{
		if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>1 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=10)
		{
			return 100;
		}
		else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=11 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=29)
		{
			return 200;
		}
		else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=30 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=79)
		{
			return 350;
		}
		else
		{
			return 500;
		}
	}
	
	public static int get_iStandardHeightInRow(SQLField oSQLField)
	{
		if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=1 && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=119)
		{
			return 1;
		}
		else if (oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()>=120  && oSQLField.get_oFieldMetaDef().get_FieldTextLENGTH()<=300)
		{
			return 5;
		}
		else
		{
			return 8;
		}
	
	}
}