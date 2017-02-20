package at.crimsonbit.quizchell.test;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class TestEnv {
	
	@Test
	public void test() {
		Map<String, String> envVars = System.getenv();
		for (Entry<String, String> entry : envVars.entrySet()) {
			if (entry.getKey().contains("HOME"))
				System.out.println(entry);
		}
	}
	
}
