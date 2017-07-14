package goldfish;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StringTest.class);
	
	@Test
	public void test() {
		String a = "I";
		Assert.assertFalse(a.equals('I'));
	}
	
	@Test
	public void test2() {
		String a = null;
		if ("U".equals(a)) {
			logger.debug("success");
		}
	}
	
	
}
