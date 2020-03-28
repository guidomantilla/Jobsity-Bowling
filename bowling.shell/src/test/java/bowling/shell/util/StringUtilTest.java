package bowling.shell.util;

import static bowling.shell.util.StringUtil.validateStringDomainChars;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StringUtilTest {

	private final String NAME_CHARS_ACCEPTED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final String PIN_NUMBER_CHARS_ACCEPTED = "0123456789F";

	@Test
	public void whenBowlerNameIsOK_then_True() {

		assertTrue(validateStringDomainChars("Jeff", NAME_CHARS_ACCEPTED));
	}

	@Test
	public void whenBowlerNameIsBAD_then_False() {

		assertFalse(validateStringDomainChars("Jeff!", NAME_CHARS_ACCEPTED));
	}

	@Test
	public void whenBowlerScoreIs10_then_True() {

		assertTrue(validateStringDomainChars("10", PIN_NUMBER_CHARS_ACCEPTED));
	}

	@Test
	public void whenBowlerScoreIsNeg1_then_False() {

		assertFalse(validateStringDomainChars("-1", PIN_NUMBER_CHARS_ACCEPTED));
	}

	@Test
	public void whenBowlerScoreIs0_then_True() {

		assertTrue(validateStringDomainChars("0", PIN_NUMBER_CHARS_ACCEPTED));
	}

	@Test
	public void whenBowlerScoreIsF_then_True() {

		assertTrue(validateStringDomainChars("F", PIN_NUMBER_CHARS_ACCEPTED));
	}
}
