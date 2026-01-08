package rohstoff.Echo2BusinessLogic._TAX;

import junit.framework.TestCase;

public class TaxIdTest extends TestCase {
	public void testGermanTax() {
		TaxId t = new TaxId("DE 123817SSSSS");
		System.out.println(t.toString());
		
	}
}
