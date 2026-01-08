package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort.test;

import static org.junit.Assert.*;

import org.junit.Test;

import panter.gmbh.Echo2.RB.COMP.RB_DoubleText_4_PW_complexity;

public class RB_PasswordComplexity_TEST {

	
	
	@Test
	public void test() {
		String pwdLow = "abcd";
		
		String pwdMedium1 = "aBcD";
		String pwdMedium2 = "1234";
		String pwdMedium3 = "a1bcd";
		String pwdMedium4 = "a1Bcd";
		
		String pwdStrong1 = "a1B2c3D4";
		String pwdStrong2 = "abcdefgh";
		String pwdStrong3 = "a1bcdefh";
		String pwdStrong4 = "12345678";
		
		String pwdVeryStrong1 = "a1B2c3D4";
		String pwdVeryStrong2 = "abcdefgh";
		String pwdVeryStrong3 = "a1bcdefh";
		String pwdVeryStrong4 = "12345678";
		String pwdVeryStrong5 = "12345678(";
		String pwdVeryStrong6 = "abcdefgh#";
		String pwdVeryStrong7 = "a1B2c3D4&#";
		
		String nullPwd = null;
		
		
		
		assertEquals(RB_DoubleText_4_PW_complexity.isNotSecure(pwdLow), true);
	
		assertEquals(RB_DoubleText_4_PW_complexity.isNotSecure(pwdMedium1), true);
		assertEquals(RB_DoubleText_4_PW_complexity.isMediumSecure(pwdLow), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isMediumSecure(pwdMedium1), true);
		assertEquals(RB_DoubleText_4_PW_complexity.isMediumSecure(pwdMedium2), true);
		assertEquals(RB_DoubleText_4_PW_complexity.isMediumSecure(pwdMedium3), true);
		assertEquals(RB_DoubleText_4_PW_complexity.isMediumSecure(pwdMedium4), true);
		assertEquals(RB_DoubleText_4_PW_complexity.isMediumSecure(nullPwd), false);
		
		assertEquals(RB_DoubleText_4_PW_complexity.isNotSecure(pwdStrong1), true);
		assertEquals(RB_DoubleText_4_PW_complexity.isMediumSecure(pwdStrong1), true);
		assertEquals(RB_DoubleText_4_PW_complexity.isStrongSecured(pwdStrong1), true);
		assertEquals(RB_DoubleText_4_PW_complexity.isStrongSecured(pwdStrong2), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isStrongSecured(pwdStrong3), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isStrongSecured(pwdStrong4), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isMediumSecure(nullPwd), false);
		
		assertEquals(RB_DoubleText_4_PW_complexity.isVeryStrongSecured(pwdVeryStrong1), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isVeryStrongSecured(pwdVeryStrong2), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isVeryStrongSecured(pwdVeryStrong3), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isVeryStrongSecured(pwdVeryStrong4), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isVeryStrongSecured(pwdVeryStrong5), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isVeryStrongSecured(pwdVeryStrong6), false);
		assertEquals(RB_DoubleText_4_PW_complexity.isVeryStrongSecured(pwdVeryStrong7), true);
	}

}
