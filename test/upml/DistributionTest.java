package upml;


import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class DistributionTest extends TestCase {

	@Test
	public void testNormal() {
		assertEquals(test("a = c(0.5, 0.5)  a ~ ddirich(c(1,1))"), 8.881784197001252E-16);
		assertEquals(test("a = 1;  a ~ dnorm(0,1)"), -1.4189385332046727);
		assertEquals(test("a = 0.2  a ~ dbeta(2,3)"), 0.42918163472548);
		assertEquals(test("a = 1  a ~ dexp(1)"), -1.0);
		assertEquals(test("a = c(0.5, 0.5)  a ~ dlnorm(1, 1)"), -3.318330080327547);
		assertEquals(test("a = 0.5  a ~ dlnorm(1, 1)"), -3.318330080327547/2);
	}

	@Test
	public void testLoopWithDistr() {
		String cmd = "a0 = c(1,2,3) for (i in 1:3) { a0[i] ~ dnorm(0,1)}";
		assertEquals(test(cmd, "logP.a0[1]"), -1.4189385332046727);
		assertEquals(test(cmd, "logP.a0[2]"), -2.9189385332046727);
		assertEquals(test(cmd, "logP.a0[3]"), -5.418938533204672);
	}

	protected static double test(String cmd) {
		return test(cmd, "logP.a");
	}
	
	protected static double test(String cmd, String valueOfInterest) {
		try {
			UPMLListenerImpl parser = new UPMLListenerImpl(new StringBuilder(cmd));
			Object o = parser.parse("model{" + cmd + "}");
			if (o instanceof String) {
				System.out.println((String) o);
			}

			cmd = o.toString();
			String pythonCmd = "import numpy\n" +
					"import pymc3 as pm\n\n" +
					"with pm.Model() as model:\n" + 
					cmd + "\n\n" +
					"model\n";

			String PYTHON_FILE = "/tmp/python.test";
			FileWriter outfile = new FileWriter(PYTHON_FILE);
			outfile.write(pythonCmd);
			outfile.close();

			final ProcessBuilder pb = new ProcessBuilder("python", PYTHON_FILE);

			Map<String, String> environment = pb.environment();
			System.err.println(pb.command());

			// File log = new File("log");
			pb.redirectErrorStream(true);

			// Start the process and wait for it to finish.
			final Process process = pb.start();

			Thread inputThread = null;
			StringBuilder result = new StringBuilder();
			inputThread = new Thread() {
				public void run() {
					OutputStream out = process.getOutputStream();
					byte[] buf = new byte[2048];
					while (true) {
						try {
							int ni = System.in.available();
							if (ni > 0) {
								int c = System.in.read(buf, 0, Math.min(ni, buf.length));
								System.err.print((char) c);
								out.write(buf, 0, c);
								out.flush();
								result.append((char) c);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			inputThread.start();

			int c;
			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((c = input.read()) != -1) {
				System.out.print((char) c);
				result.append((char) c);
			}
			input.close();

			final int exitStatus = process.waitFor();
			if (exitStatus != 0) {
				System.err.println(process.getErrorStream());
			}
			inputThread.stop();

			System.err.println(result.toString());
			try {
				String str = result.toString();
				if (str.contains("[")) {
					str = str.substring(str.indexOf('[') + 1);
					String [] strs = str.trim().split("\\s+");
					str = strs[0];
				}
				return Double.parseDouble(str);
			} catch (NumberFormatException e) {
				double r = Boolean.parseBoolean(result.toString().trim()) ? 1 : 0;
				return r;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Double.NaN;
	}

}
