package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.indep.exceptions.myException;


/*
 * panel, das es erlaubt, bedienelemente zu gruppieren
 */
public class E2_ComponentGroupHorizontal extends MyE2_Row  implements MyE2IF__Component
{
	public static RowLayoutData ownRowLayout=null;;
	static
	{
		RowLayoutData oRL = new RowLayoutData();
		oRL.setInsets(new Insets(0,2,4,2));
		oRL.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		ownRowLayout = oRL;
	}
	
	
	private MyE2EXT__Component 		oEXT = 				new MyE2EXT__Component(this);
	private RowLayoutData			ownLayoutData = 	null;

	public E2_ComponentGroupHorizontal(Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(1));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
	}

	public E2_ComponentGroupHorizontal(int iBorderSize, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
	}

	/**
	 * fügt eine dynamische Anzahl von Komponenten zur Gruppe
	 * @author manfred
	 * @date 28.09.2016
	 *
	 * @param iBorderSize
	 * @param oInsets
	 * @param components
	 */
	public E2_ComponentGroupHorizontal (int iBorderSize, Insets oInsets, Component... components){
		super();
		this.setStyle(new ownStyle(iBorderSize));

		if (oInsets != null){
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		}

		for (Component c : components){
			if (c != null) 	this.add(c);
		}
	}
	
	
	
	public E2_ComponentGroupHorizontal(int iBorderSize,Component oComp1, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);

		if (oComp1 != null) 	this.add(oComp1);
	}

	public E2_ComponentGroupHorizontal(int iBorderSize,Component oComp1,Component oComp2, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));

		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
	}
	public E2_ComponentGroupHorizontal(int iBorderSize,Component oComp1,Component oComp2,Component oComp3, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
	}
	public E2_ComponentGroupHorizontal(int iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
	}

	
	
	public E2_ComponentGroupHorizontal(int iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
	}

	public E2_ComponentGroupHorizontal(int iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,Component oComp6, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
	}


	
	public E2_ComponentGroupHorizontal(int iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,Component oComp6,Component oComp7, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
	}


	public E2_ComponentGroupHorizontal(int iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,Component oComp6,Component oComp7,Component oComp8, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
	}


	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
														Component oComp6,Component oComp7,Component oComp8,Component oComp9, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
	}

	

	
	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
										Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
	}


	

	
	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
										Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
	}


	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
			Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11,Component oComp12, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
		if (oComp12 != null) 	this.add(oComp12);
	}

	
	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
			Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11,Component oComp12,
			Component oComp13, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
		if (oComp12 != null) 	this.add(oComp12);
		if (oComp13 != null) 	this.add(oComp13);

	}

	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
			Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11,Component oComp12,
			Component oComp13,Component oComp14, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
		if (oComp12 != null) 	this.add(oComp12);
		if (oComp13 != null) 	this.add(oComp13);
		if (oComp14 != null) 	this.add(oComp14);
	}

	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
			Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11,Component oComp12,
			Component oComp13,Component oComp14,Component oComp15, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
		if (oComp12 != null) 	this.add(oComp12);
		if (oComp13 != null) 	this.add(oComp13);
		if (oComp14 != null) 	this.add(oComp14);
		if (oComp15 != null) 	this.add(oComp15);
	}

	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
			Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11,Component oComp12,
			Component oComp13,Component oComp14,Component oComp15,Component oComp16,Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
		if (oComp12 != null) 	this.add(oComp12);
		if (oComp13 != null) 	this.add(oComp13);
		if (oComp14 != null) 	this.add(oComp14);
		if (oComp15 != null) 	this.add(oComp15);
		if (oComp16 != null) 	this.add(oComp16);
	}

	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
			Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11,Component oComp12,
			Component oComp13,Component oComp14,Component oComp15,Component oComp16,Component oComp17, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
		if (oComp12 != null) 	this.add(oComp12);
		if (oComp13 != null) 	this.add(oComp13);
		if (oComp14 != null) 	this.add(oComp14);
		if (oComp15 != null) 	this.add(oComp15);
		if (oComp16 != null) 	this.add(oComp16);
		if (oComp17 != null) 	this.add(oComp17);

	}

	
	

	
	
	public E2_ComponentGroupHorizontal(int iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
			Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11,Component oComp12,
			Component oComp13,Component oComp14,Component oComp15,Component oComp16,Component oComp17,Component oComp18, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
		if (oComp12 != null) 	this.add(oComp12);
		if (oComp13 != null) 	this.add(oComp13);
		if (oComp14 != null) 	this.add(oComp14);
		if (oComp15 != null) 	this.add(oComp15);
		if (oComp16 != null) 	this.add(oComp16);
		if (oComp17 != null) 	this.add(oComp17);
		if (oComp18 != null) 	this.add(oComp18);
	}


	
	
	
	public E2_ComponentGroupHorizontal(Border iBorderSize, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
	}


	public E2_ComponentGroupHorizontal(Border iBorderSize,Component oComp1, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);

		if (oComp1 != null) 	this.add(oComp1);
	}

	public E2_ComponentGroupHorizontal(Border iBorderSize,Component oComp1,Component oComp2, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));

		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
	}
	public E2_ComponentGroupHorizontal(Border iBorderSize,Component oComp1,Component oComp2,Component oComp3, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
	}
	public E2_ComponentGroupHorizontal(Border iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
	}

	
	
	public E2_ComponentGroupHorizontal(Border iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
	}

	public E2_ComponentGroupHorizontal(Border iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,Component oComp6, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
	}


	
	public E2_ComponentGroupHorizontal(Border iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,Component oComp6,Component oComp7, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
	}


	public E2_ComponentGroupHorizontal(Border iBorderSize,Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,Component oComp6,Component oComp7,Component oComp8, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
	}


	public E2_ComponentGroupHorizontal(Border iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
														Component oComp6,Component oComp7,Component oComp8,Component oComp9, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
	}

	
	public E2_ComponentGroupHorizontal(Border iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
										Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
	}

	
	public E2_ComponentGroupHorizontal(Border iBorderSize,	Component oComp1,Component oComp2,Component oComp3,Component oComp4,Component oComp5,
										Component oComp6,Component oComp7,Component oComp8,Component oComp9, Component oComp10,Component oComp11, Insets oInsets)
	{
		super();
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		
		if (oComp1 != null) 	this.add(oComp1);
		if (oComp2 != null) 	this.add(oComp2);
		if (oComp3 != null) 	this.add(oComp3);
		if (oComp4 != null) 	this.add(oComp4);
		if (oComp5 != null) 	this.add(oComp5);
		if (oComp6 != null) 	this.add(oComp6);
		if (oComp7 != null) 	this.add(oComp7);
		if (oComp8 != null) 	this.add(oComp8);
		if (oComp9 != null) 	this.add(oComp9);
		if (oComp10 != null) 	this.add(oComp10);
		if (oComp11 != null) 	this.add(oComp11);
	}

	
	
	public E2_ComponentGroupHorizontal(int iBorderSize,  Vector<Component>  vComponents, Insets oInsets)
	{
		this.setStyle(new ownStyle(iBorderSize));
		if (oInsets != null)
		{
			this.ownLayoutData = new ownLayoutDATA(oInsets);
		}
		for (Component oComp: vComponents)
		{
			if (oComp != null) 	this.add(oComp);
		}
		
	}
	
	
	public void add(Component oComp)
	{
		super.add(oComp);
		oComp.setLayoutData(ownRowLayout);
		if (this.ownLayoutData != null)
			oComp.setLayoutData(this.ownLayoutData);
	}
	

	public void add(Component oComp, Insets oInsets)
	{
		super.add(oComp,oInsets);
	}

	
	
	private class ownLayoutDATA extends RowLayoutData
	{
		public ownLayoutDATA(Insets oInsets)
		{
			super();
			this.setInsets(oInsets);
		}
		
	}
	

	private class ownStyle extends MutableStyle
	{

		public ownStyle(int iBorder)
		{
			super();
			
			if (iBorder>0) this.setProperty( Row.PROPERTY_BORDER, new Border(iBorder, new E2_ColorBase(-20), Border.STYLE_SOLID));
			this.setProperty( Row.PROPERTY_INSETS, new Insets(0,2,2,2)); 
		}

		public ownStyle(Border oBorder)
		{
			super();
			
			if (oBorder != null) this.setProperty( Row.PROPERTY_BORDER, oBorder); 
			this.setProperty( Row.PROPERTY_INSETS, new Insets(0,2,2,2)); 
		}

		
	}

	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		for (int i=0;i<this.getComponentCount();i++)
		{
			if (this.getComponent(i) instanceof MyE2IF__Component)
				((MyE2IF__Component)this.getComponent(i)).set_bEnabled_For_Edit(bEnabled && this.EXT().is_ValidEnableAlowed());
		}
		
	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}
	
	
}
