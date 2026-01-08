package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;




import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


/*
 * das gleiche DefSpalteLayout_And_Else - objekt wird den Titel- und Listenbuttons uebergeben
 */
public class DefSpalteLayout_And_Else
{

	private int        iNummerDerSpalteInPopupListe = -1;

	private boolean    SpalteIsSortable = true;
	private MyString   TitelButton_Or_LabelText = null;
	private String     DBNameOfColumn = null;
	


	private Color      ColorBackgroundTitel = new E2_ColorDDark();
	private Color      ColorBackgroundList =  new E2_ColorBase();
	
	private Alignment  AlignTitel =  E2_ALIGN.LEFT_MID;
	private Alignment  AlignList =   E2_ALIGN.LEFT_MID;
	

	private Insets     InsetsTitel = new Insets(2,1,2,1);
	private Insets     InsetsButtons = new Insets(2,1,2,1);
	
	//ein uebersetzer-objekt zwischen buttontext und sorttext
	private XX_Translate_Buttontext_to_SortText  Translator = new TransSTD();

	

	private boolean    LineWrapTitel = false;
	private boolean    LineWrapList = true;





	/**
	 * 
	 * @param NummerDerSpalteInPopupListe
	 * @param spalteIsSortable
	 * @param titelButton_Or_LabelText
	 * @param dbNameDerSpalte
	 * @param colorBackgroundTitel    -- Ab hier sind die werte vorbesetzt und koennen null bleiben
	 * @param colorBackgroundList
	 * @param alignTitel
	 * @param alignList
	 * @param insetsTitel
	 * @param insetsButtons
	 * @param lineWrapTitel
	 * @param lineWrapList
	 * @param translator
	 * @throws myException
	 */
	public DefSpalteLayout_And_Else(	int                                     NummerDerSpalteInPopupListe,
										boolean 								spalteIsSortable,  
										MyString 								titelButton_Or_LabelText, 
										String 									dbNameDerSpalte,
										Color      								colorBackgroundTitel,
										Color   								colorBackgroundList,
										Alignment  								alignTitel,
										Alignment  								alignList,
										Insets     								insetsTitel,
										Insets     								insetsButtons,
										Boolean   								lineWrapTitel,
										Boolean   								lineWrapList,
										XX_Translate_Buttontext_to_SortText  	translator
										) throws myException
	{
		super();
		
		this.iNummerDerSpalteInPopupListe = NummerDerSpalteInPopupListe;
		this.SpalteIsSortable = spalteIsSortable;
		
		if (S.isEmpty(titelButton_Or_LabelText))
		{
			throw new myException(this,"Empty labeltext  is not allowd !");
		}
		
		if (S.isEmpty(dbNameDerSpalte))
		{
			throw new myException(this,"Empty DBNameOfColumn  is not allowd !");
		}
		this.TitelButton_Or_LabelText = 	titelButton_Or_LabelText;
		this.DBNameOfColumn = 				dbNameDerSpalte;
		
		
		//alle folgenden sind vorbesetzt und werden wenn null bei standard belassen
		if (colorBackgroundTitel!=null) 	{this.ColorBackgroundTitel=colorBackgroundTitel;}
		if (colorBackgroundList!=null) 		{this.ColorBackgroundList=colorBackgroundList;}
		if (alignTitel!=null) 				{this.AlignTitel=alignTitel;}
		if (alignList!=null) 				{this.AlignList=alignList;}
		if (insetsTitel!=null) 				{this.InsetsTitel=insetsTitel;}
		if (insetsButtons!=null) 			{this.InsetsButtons=insetsButtons;}

		if (lineWrapList!=null) 			{this.LineWrapList=lineWrapList;}
		if (lineWrapTitel!=null) 			{this.LineWrapTitel=lineWrapTitel;}

		if (translator!=null) 				{this.Translator=translator;}
		
		
	}
	
	
	/**
	 * @param NummerDerSpalteInPopupListe
	 * @param spalteIsSortable
	 * @param titelButton_Or_LabelText
	 * @param dbNameDerSpalte
	 * @throws myException
	 */
	public DefSpalteLayout_And_Else(	int                                     NummerDerSpalteInPopupListe,
										boolean 								spalteIsSortable,  
										MyString 								titelButton_Or_LabelText, 
										String 									dbNameDerSpalte
										) throws myException
	{
		super();
		
		this.iNummerDerSpalteInPopupListe = NummerDerSpalteInPopupListe;

		this.SpalteIsSortable = spalteIsSortable;
		
		if (S.isEmpty(titelButton_Or_LabelText))
		{
			throw new myException(this,"Empty labeltext  is not allowd !");
		}
		
		if (S.isEmpty(dbNameDerSpalte))
		{
			throw new myException(this,"Empty DBNameOfColumn  is not allowd !");
		}
		this.TitelButton_Or_LabelText = 	titelButton_Or_LabelText;
		this.DBNameOfColumn = 				dbNameDerSpalte;
		
		
		
	}

	
	
	public GridLayoutData  get_glTitelButtons()
	{
		GridLayoutData  oGLHead = new GridLayoutData();
		oGLHead.setAlignment(this.AlignTitel);
		oGLHead.setBackground(this.ColorBackgroundTitel);
		oGLHead.setInsets(this.InsetsTitel);
		return oGLHead;
	}
	

	public GridLayoutData  get_glListButtons()
	{
		GridLayoutData  oGLHead = new GridLayoutData();
		oGLHead.setAlignment(this.AlignList);
		oGLHead.setBackground(this.ColorBackgroundList);
		oGLHead.setInsets(this.InsetsButtons);
		return oGLHead;
	}

	
	public XX_Translate_Buttontext_to_SortText get_Translator()
	{
		return Translator;
	}


	public void set_Translator(XX_Translate_Buttontext_to_SortText translator)
	{
		Translator = translator;
	}

	
	public String get_DBNameOfColumn()
	{
		return DBNameOfColumn;
	}

	
	
	public boolean get_bSpalteIsSortable()
	{
		return SpalteIsSortable;
	}


	public MyString get_cSpalteButton_Or_LabelText()
	{
		return TitelButton_Or_LabelText;
	}


	public Color get_ColorBackgroundTitel()
	{
		return ColorBackgroundTitel;
	}


	public void set_ColorBackgroundTitel(Color colorBackgroundTitel)
	{
		ColorBackgroundTitel = colorBackgroundTitel;
	}


	public Color get_ColorBackgroundList()
	{
		return ColorBackgroundList;
	}


	public void set_ColorBackgroundList(Color colorBackgroundList)
	{
		ColorBackgroundList = colorBackgroundList;
	}



	public Alignment get_AlignTitel()
	{
		return AlignTitel;
	}


	public void set_AlignTitel(Alignment alignTitel)
	{
		AlignTitel = alignTitel;
	}


	public Alignment get_AlignList()
	{
		return AlignList;
	}


	public void set_AlignList(Alignment alignList)
	{
		AlignList = alignList;
	}

	public Insets get_InsetsTitel()
	{
		return InsetsTitel;
	}

	public void set_InsetsTitel(Insets insetsTitel)
	{
		InsetsTitel = insetsTitel;
	}

	public Insets get_InsetsButtons()
	{
		return InsetsButtons;
	}

	public void set_InsetsButtons(Insets insetsButtons)
	{
		InsetsButtons = insetsButtons;
	}

	public boolean get_bLineWrapTitel()
	{
		return LineWrapTitel;
	}

	public void set_bLineWrapTitel(boolean lineWrapTitel)
	{
		LineWrapTitel = lineWrapTitel;
	}

	public boolean get_bLineWrapList()
	{
		return LineWrapList;
	}

	public void set_bLineWrapList(boolean lineWrapList)
	{
		LineWrapList = lineWrapList;
	}

	public int get_iNummerDerSpalteInPopupListe()
	{
		return iNummerDerSpalteInPopupListe;
	}


	
}
