package com.specl.log.util;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void t() {

		String s = StringUtil.encodeString("file");
		System.out.println(" encoded " + s);
		String d = StringUtil.decodingString(s);
		System.out.println(" decoded " + d);
	}
}
