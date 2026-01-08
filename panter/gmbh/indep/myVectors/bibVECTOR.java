package panter.gmbh.indep.myVectors;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.indep.MyString;

public class bibVECTOR
{
	public static boolean VectorsContainsSameStrings(Vector<String> V1, Vector<String> V2)
	{
		boolean bRueck = true;
		
		if (V1.size()!=V2.size())
		{
			return false;
		}
		for (String cTest: V1)
		{
			if (!V2.contains(cTest))
			{
				bRueck = false;
				break;
			}
		}
		return bRueck;	
	}
	
	
	
	public static Vector<String> get_VectorFromArray(String[][] cArray)
	{
		if (cArray == null)
			return null;
		
		
		Vector<String> vRueck = new Vector<String>();
		
		for (int i=0;i<cArray.length;i++)
		{
			for (int k=0;k<cArray[i].length;k++)
			{
				if (cArray[i][k]!=null)
				{
					vRueck.add(cArray[i][k]);
				}
			}
		}
		
		return vRueck;
	}
	
	
	
	public static Vector<String> get_VectorFromArray(String[] cArray)
	{
		if (cArray == null)
			return null;
		
		
		Vector<String> vRueck = new Vector<String>();
		
		for (int i=0;i<cArray.length;i++)
		{
			if (cArray[i]!=null)
			{
				vRueck.add(cArray[i]);
			}
		}
		
		return vRueck;
	}
	

	
	
	
	public static Vector<String> get_VectorFromArray(String[][] cArray, Vector<String> retVectorWhenArrayNULL)
	{
		if (cArray == null)
			return retVectorWhenArrayNULL;
		
		
		Vector<String> vRueck = new Vector<String>();
		
		for (int i=0;i<cArray.length;i++)
		{
			for (int k=0;k<cArray[i].length;k++)
			{
				if (cArray[i][k]!=null)
				{
					vRueck.add(cArray[i][k]);
				}
			}
		}
		
		return vRueck;
	}
	
	
	
	public static Vector<String> get_VectorFromArray(String[] cArray, Vector<String> retVectorWhenArrayNULL)
	{
		if (cArray == null)
			return retVectorWhenArrayNULL;
		
		
		Vector<String> vRueck = new Vector<String>();
		
		for (int i=0;i<cArray.length;i++)
		{
			if (cArray[i]!=null)
			{
				vRueck.add(cArray[i]);
			}
		}
		
		return vRueck;
	}
	


	
	
	
	public static Vector<String> get_VectorFromSet(Set<String> sSet)
	{
		if (sSet == null)
			return null;
		

		Iterator<String>  oIter = sSet.iterator();
		Vector<String> vRueck = new Vector<String>();
		
		while (oIter.hasNext())
		{
			vRueck.add(oIter.next());
		}
		
		return vRueck;
	}
	
	
	
	public static Vector<Integer> get_VI(Integer i1)
	{
		Vector<Integer> vRueck = new Vector<Integer>();
		
		vRueck.add(i1);
		
		return vRueck;
	}
	
	
	public static Vector<Integer> get_VI(Integer i1, Integer i2)
	{
		Vector<Integer> vRueck = new Vector<Integer>();
		
		vRueck.add(i1);
		vRueck.add(i2);
		
		return vRueck;
	}
	
	public static Vector<Integer> get_VI(Integer i1, Integer i2, Integer i3)
	{
		Vector<Integer> vRueck = new Vector<Integer>();
		
		vRueck.add(i1);
		vRueck.add(i2);
		vRueck.add(i3);
		
		return vRueck;
	}
	
	public static Vector<Integer> get_VI(Integer i1, Integer i2, Integer i3, Integer i4)
	{
		Vector<Integer> vRueck = new Vector<Integer>();
		
		vRueck.add(i1);
		vRueck.add(i2);
		vRueck.add(i3);
		vRueck.add(i4);
		
		return vRueck;
	}
	
	
	public static Vector<Integer> get_VI(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5)
	{
		Vector<Integer> vRueck = new Vector<Integer>();
		
		vRueck.add(i1);
		vRueck.add(i2);
		vRueck.add(i3);
		vRueck.add(i4);
		vRueck.add(i5);
		
		return vRueck;
	}
	
	
	public static Vector<Integer> get_VI(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5, Integer i6)
	{
		Vector<Integer> vRueck = new Vector<Integer>();
		
		vRueck.add(i1);
		vRueck.add(i2);
		vRueck.add(i3);
		vRueck.add(i4);
		vRueck.add(i5);
		vRueck.add(i6);
		
		return vRueck;
	}

	
	public static Vector<Integer> get_VI(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5, Integer i6, Integer i7)
	{
		Vector<Integer> vRueck = new Vector<Integer>();
		
		vRueck.add(i1);
		vRueck.add(i2);
		vRueck.add(i3);
		vRueck.add(i4);
		vRueck.add(i5);
		vRueck.add(i6);
		vRueck.add(i7);
		
		return vRueck;
	}
	
	public static Vector<Integer> get_VI(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5, Integer i6, Integer i7, Integer i8)
	{
		Vector<Integer> vRueck = new Vector<Integer>();
		
		vRueck.add(i1);
		vRueck.add(i2);
		vRueck.add(i3);
		vRueck.add(i4);
		vRueck.add(i5);
		vRueck.add(i6);
		vRueck.add(i7);
		vRueck.add(i8);
		
		return vRueck;
	}
	
	
	
	public static Vector<String> get_Vector(String... s) {
		Vector<String> vRueck = new Vector<String>();
		for (String st: s) {
			vRueck.add(st);
		}
		return vRueck;
	}
	

	public static Vector<String> get_Vector(Collection<String> v_str) {
		Vector<String> vRueck = new Vector<String>();
		vRueck.addAll(v_str);
		return vRueck;
	}
	

	
	
	
	public static Vector<String> get_Vector(String cFirstElement)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cFirstElement);
		return vRueck;
	}
	
	public static Vector<String> get_Vector(String cElement1,String cElement2)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		return vRueck;
	}

	public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		return vRueck;
	}
	

	
	public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		if (cElement4 != null) vRueck.add(cElement4);
		return vRueck;
	}
	public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		if (cElement4 != null) vRueck.add(cElement4);
		if (cElement5 != null) vRueck.add(cElement5);
		return vRueck;
	}
	




	
	
	
	
	
	
	public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,String cElement6,String cElement7,String cElement8)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		if (cElement4 != null) vRueck.add(cElement4);
		if (cElement5 != null) vRueck.add(cElement5);
		if (cElement6 != null) vRueck.add(cElement6);
		if (cElement7 != null) vRueck.add(cElement7);
		if (cElement8 != null) vRueck.add(cElement8);
		return vRueck;
	}


//	public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,String cElement6,String cElement7,String cElement8,String cElement9)
//	{
//		Vector<String> vRueck = new Vector<String>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		if (cElement5 != null) vRueck.add(cElement5);
//		if (cElement6 != null) vRueck.add(cElement6);
//		if (cElement7 != null) vRueck.add(cElement7);
//		if (cElement8 != null) vRueck.add(cElement8);
//		if (cElement9 != null) vRueck.add(cElement9);
//		
//		return vRueck;
//	}



	
//	public static Vector<String> get_Vector(		String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,
//													String cElement6,String cElement7,	String cElement8,	String cElement9, String cElement10, String cElement11)
//	{
//		Vector<String> vRueck = new Vector<String>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		if (cElement5 != null) vRueck.add(cElement5);
//		if (cElement6 != null) vRueck.add(cElement6);
//		if (cElement7 != null) vRueck.add(cElement7);
//		if (cElement8 != null) vRueck.add(cElement8);
//		if (cElement9 != null) vRueck.add(cElement9);
//		if (cElement10 != null) vRueck.add(cElement10);
//		if (cElement11 != null) vRueck.add(cElement11);
//		
//		return vRueck;
//	}
	
	

	public static Vector<MyString> get_Vector(MyString... strings)
	{
		Vector<MyString> vRueck = new Vector<MyString>();
		for (MyString s: strings) {
			vRueck.add(s);
		}
		return vRueck;
	}
	
	
	
	
//	public static Vector<MyString> get_Vector(MyString cFirstElement)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cFirstElement);
//		return vRueck;
//	}
	
//	public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		return vRueck;
//	}

	public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3)
	{
		Vector<MyString> vRueck = new Vector<MyString>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		return vRueck;
	}
	

	
//	public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		return vRueck;
//	}
//	public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		if (cElement5 != null) vRueck.add(cElement5);
//		return vRueck;
//	}
	
	public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,MyString cElement6)
	{
		Vector<MyString> vRueck = new Vector<MyString>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		if (cElement4 != null) vRueck.add(cElement4);
		if (cElement5 != null) vRueck.add(cElement5);
		if (cElement6 != null) vRueck.add(cElement6);
		return vRueck;
	}


//	public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,MyString cElement6,MyString cElement7)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		if (cElement5 != null) vRueck.add(cElement5);
//		if (cElement6 != null) vRueck.add(cElement6);
//		if (cElement7 != null) vRueck.add(cElement7);
//		return vRueck;
//	}


	
	
	
	
	
	
//	public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,MyString cElement6,MyString cElement7,MyString cElement8)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		if (cElement5 != null) vRueck.add(cElement5);
//		if (cElement6 != null) vRueck.add(cElement6);
//		if (cElement7 != null) vRueck.add(cElement7);
//		if (cElement8 != null) vRueck.add(cElement8);
//		return vRueck;
//	}


//	public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,MyString cElement6,MyString cElement7,MyString cElement8,MyString cElement9)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		if (cElement5 != null) vRueck.add(cElement5);
//		if (cElement6 != null) vRueck.add(cElement6);
//		if (cElement7 != null) vRueck.add(cElement7);
//		if (cElement8 != null) vRueck.add(cElement8);
//		if (cElement9 != null) vRueck.add(cElement9);
//		
//		return vRueck;
//	}

//	public static Vector<MyString> get_Vector(		MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,
//													MyString cElement6,MyString cElement7,	MyString cElement8,	MyString cElement9, MyString cElement10)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		if (cElement5 != null) vRueck.add(cElement5);
//		if (cElement6 != null) vRueck.add(cElement6);
//		if (cElement7 != null) vRueck.add(cElement7);
//		if (cElement8 != null) vRueck.add(cElement8);
//		if (cElement9 != null) vRueck.add(cElement9);
//		if (cElement10 != null) vRueck.add(cElement10);
//		
//		return vRueck;
//	}


	
//	public static Vector<MyString> get_Vector(		MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,
//													MyString cElement6,MyString cElement7,	MyString cElement8,	MyString cElement9, MyString cElement10, MyString cElement11)
//	{
//		Vector<MyString> vRueck = new Vector<MyString>();
//		vRueck.add(cElement1);
//		if (cElement2 != null) vRueck.add(cElement2);
//		if (cElement3 != null) vRueck.add(cElement3);
//		if (cElement4 != null) vRueck.add(cElement4);
//		if (cElement5 != null) vRueck.add(cElement5);
//		if (cElement6 != null) vRueck.add(cElement6);
//		if (cElement7 != null) vRueck.add(cElement7);
//		if (cElement8 != null) vRueck.add(cElement8);
//		if (cElement9 != null) vRueck.add(cElement9);
//		if (cElement10 != null) vRueck.add(cElement10);
//		if (cElement11 != null) vRueck.add(cElement11);
//		
//		return vRueck;
//	}
	

	
	
	public static Vector<Component> get_vCompVector(Component v1)
	{
		Vector<Component> vRueck = new Vector<Component>();
		vRueck.add(v1);
		return vRueck;
	}

	public static Vector<Component> get_vCompVector(Component v1, Component v2)
	{
		Vector<Component> vRueck = new Vector<Component>();
		vRueck.add(v1);
		if (v2!=null) vRueck.add(v2);
		return vRueck;
	}

	public static Vector<Component> get_vCompVector(Component v1, Component v2, Component v3)
	{
		Vector<Component> vRueck = new Vector<Component>();
		vRueck.add(v1);
		if (v2!=null) vRueck.add(v2);
		if (v3!=null) vRueck.add(v3);
		return vRueck;
	}

	public static Vector<Component> get_vCompVector(Component v1, Component v2, Component v3,Component v4)
	{
		Vector<Component> vRueck = new Vector<Component>();
		vRueck.add(v1);
		if (v2!=null) vRueck.add(v2);
		if (v3!=null) vRueck.add(v3);
		if (v4!=null) vRueck.add(v4);
		return vRueck;
	}

	public static Vector<Component> get_vCompVector(Component v1, Component v2, Component v3, Component v4, Component v5)
	{
		Vector<Component> vRueck = new Vector<Component>();
		vRueck.add(v1);
		if (v2!=null) vRueck.add(v2);
		if (v3!=null) vRueck.add(v3);
		if (v4!=null) vRueck.add(v4);
		if (v5!=null) vRueck.add(v5);
		return vRueck;
	}

	public static Vector<Component> get_vCompVector(Component v1, Component v2, Component v3, Component v4, Component v5, Component v6)
	{
		Vector<Component> vRueck = new Vector<Component>();
		vRueck.add(v1);
		if (v2!=null) 		vRueck.add(v2);
		if (v3!=null) vRueck.add(v3);
		if (v4!=null) vRueck.add(v4);
		if (v5!=null) vRueck.add(v5);
		if (v6!=null) vRueck.add(v6);
		return vRueck;
	}
	
	
	
	public static Vector<String> MakeUpperStrings(Vector<String> vQuelle)
	{
		if (vQuelle==null)
		{
			return null;
		}
		
		
		Vector<String> vRueck = new Vector<String>();
		
		for (String cWert: vQuelle)
		{
			vRueck.add(cWert.toUpperCase());
		}
		
		return vRueck;
	}

	public static Vector<String> MakeLowerStrings(Vector<String> vQuelle)
	{
		if (vQuelle==null)
		{
			return null;
		}

		
		Vector<String> vRueck = new Vector<String>();
		
		for (String cWert: vQuelle)
		{
			vRueck.add(cWert.toLowerCase());
		}
		
		return vRueck;
	}

	
	
	public static Vector<String> sort_reverse(Vector<String> v_orig) {
		
		Vector<String> v_rueck = new Vector<>();
		
		for (int i=v_orig.size()-1;i>=0;i--) {
			v_rueck.add(v_orig.get(i));
		}
		return v_rueck;
	}
	
	
	public static Vector<String> get_vector_from_keyset(AbstractMap<String, String> hm) 	{

		Vector<String> v_r = new Vector<String>();
		
		if (hm!=null) {
			v_r.addAll(hm.keySet());
		}
		return v_r;	
	}

	
	public static Vector<String> get_vector_from_values(AbstractMap<String, String> hm) 	{

		Vector<String> v_r = new Vector<String>();
		
		if (hm!=null) {
			v_r.addAll(hm.values());
		}
		return v_r;	
	}

	
}
