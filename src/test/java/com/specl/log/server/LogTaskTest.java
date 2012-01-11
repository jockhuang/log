package com.specl.log.server;

import java.util.List;

import org.junit.Test;

public class LogTaskTest {

	@Test
	public void parseRcvPackage() {

		String[] options = new String[] { "test", " hi this is xiaolei good " };

		String s = getMesg(options);
		System.out.println(s);

		List<String> list = ThreadPoolTask.parseRcvPackage(s);

		System.out.println(list);
	}

	/**
	 * @param options
	 * @return
	 */
	public String getMesg(String[] options) {
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < options.length; i++) {
			options[i] = options[i] == null ? "" : options[i];
			data.append(String.format("%04x", options[i].getBytes().length)
					+ options[i]);
		}
		String s = String.valueOf(System.currentTimeMillis());
		data.append(String.format("%04x", s.getBytes().length) + s);
		byte[] sendData = (String.format("%04x",
				data.toString().getBytes().length) + data).getBytes();

		return new String(sendData);
	}
}
