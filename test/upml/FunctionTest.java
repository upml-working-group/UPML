package upml;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.special.Gamma;
import org.junit.Test;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

import junit.framework.TestCase;

public class FunctionTest extends TestCase {

	@Test
	public void testMatrixFunctions() {
		String m1 = "m1=c(c(1,2),c(3,4))";
		String m2 = "m2=c(c(2,1),c(4,3))";
		String m3 = "m3=c(c(2,1,0),c(4,3,2))";

		assertEquals(test(m1 + " a=dim(m1)[1]"), 2.0);
		assertEquals(test(m1 + " a=dim(m1)[2]"), 2.0);
		assertEquals(test(m3 + " a=dim(m3)[1]"), 2.0);
		assertEquals(test(m3 + " a=dim(m3)[2]"), 3.0);

		assertEquals(test(m1 + " a=length(m1)"), 4.0);
		assertEquals(test(m3 + " a=length(m3)"), 6.0);

		assertEquals(test(m1 + " a=length(dim(m1))"), 2.0);
		assertEquals(test(m3 + " a=length(dim(m3))"), 2.0);

		assertEquals(test(m1 + " a= inverse(m1)[1]"), -2.0, 1e-12);
		assertEquals(test(m1 + " a= inverse(m1)[3]"), 1.5, 1e-12);
		assertEquals(test(m1 + " a= inverse(m1)[2]"), 1.0, 1e-12);
		assertEquals(test(m1 + " a= inverse(m1)[4]"), -0.5, 1e-12);

		assertEquals(test(m1 + " " + m2 + " a= inprod(m1,m2)[1]"), 2.0);
		assertEquals(test(m1 + " " + m2 + " a= inprod(m1,m2)[2]"), 2.0);
		assertEquals(test(m1 + " " + m2 + " a= inprod(m1,m2)[3]"), 12.0);
		assertEquals(test(m1 + " " + m2 + " a= inprod(m1,m2)[4]"), 12.0);

		assertEquals(test(m1 + " " + m2 + " a= prod(m1,m2)[1]"), 10.0);
		assertEquals(test(m1 + " " + m2 + " a= prod(m1,m2)[2]"), 7.0);
		assertEquals(test(m1 + " " + m2 + " a= prod(m1,m2)[3]"), 22.0);
		assertEquals(test(m1 + " " + m2 + " a= prod(m1,m2)[4]"), 15.0);

		assertEquals(test(m1 + " a=t(m1)[1]"), 1.0);
		assertEquals(test(m1 + " a=t(m1)[2]"), 3.0);
		assertEquals(test(m1 + " a=t(m1)[3]"), 2.0);
		assertEquals(test(m1 + " a=t(m1)[4]"), 4.0);

		assertEquals(test(m2 + " a= logdet(m2)"), log(2.0));
		// assertEquals(test(m1 + " " + m2 +" a= m1 %*% m2"), 1.0);
	}

	@Test
	public void testBivariableFunctions() {
		assertEquals(test("a=pow(2.0,4.0)"), 16.0);
		assertEquals(test("a=max(1.0,4.0)"), 4.0);
		assertEquals(test("a=max(4.0,1.0)"), 4.0);
		assertEquals(test("a=min(1.0,4.0)"), 1.0);
		assertEquals(test("a=min(4.0,1.0)"), 1.0);

		assertEquals(test("a=hypot(1.0,1.0)"), sqrt(2), EPSILON);
		assertEquals(test("a=atan2(1.0,1.0)"), PI / 4, EPSILON);

		assertEquals(test("a=equals(2.0,2.0)"), 1.0);
		assertEquals(test("a=equals(2.0,-2.0)"), 0.0);

		assertEquals(test("a=min(1.0,4.0)"), 1.0);
	}

	@Test
	public void testVectorFunctions() {
		String s = "c(2.0, 4.0, 1.0, 3.0)";

		assertEquals(test("a=max(" + s + ")"), 4.0);
		assertEquals(test("a=min(" + s + ")"), 1.0);
		assertEquals(test("a=mean(" + s + ")"), 2.5);
		assertEquals(test("a=sd(" + s + ")"), 1.1180339887499, EPSILON); // = 1.2909944487358056 with std(ddof=1);
		assertEquals(test("a=sum(" + s + ")"), 10.0);

//		assertEquals(test("a=order(" + s + ")"), 3.0);
		assertEquals(test("a=sort(" + s + ")"), 1.0);
		assertEquals(test("a=rank(" + s + ")"), 2.0);
	}

	@Test
	public void testUnaryFunctions() {
		testUnaryFunctions("0.0", 0.0);
		testUnaryFunctions("0.75", 0.75);
		testUnaryFunctions("-0.75", -0.75);
	}

	public void testUnaryFunctions(String v, double d) {
		assertEquals(test("a=abs(" + v + ")"), abs(d));

		assertEquals(test("a=cbrt(" + v + ")"), cbrt(d), EPSILON);
//		assertEquals(test("a=cloglog(" + v + ")"), log(-log(1 - d)), EPSILON);
		assertEquals(test("a=exp(" + v + ")"), exp(d), EPSILON);
		assertEquals(test("a=expm1(" + v + ")"), expm1(d), EPSILON);
		if (d > 0) {
			assertEquals(test("a=sqrt(" + v + ")"), sqrt(d), EPSILON);
			assertEquals(test("a=log(" + v + ")"), log(d), EPSILON);
			assertEquals(test("a=log10(" + v + ")"), log10(d), EPSILON);
			assertEquals(test("a=log1p(" + v + ")"), log1p(d), EPSILON);
//			assertEquals(test("a=loggamm(" + v + ")"), Gamma.logGamma(d), EPSILON);
//			assertEquals(test("a=logit(" + v + ")"), log(d) - log(1 - d), EPSILON);
//			assertEquals(test("a=logfact(" + v + ")"), logfact(d), EPSILON);
		}
//		try {
//			if (d >= 0 && d <= 1) {
//				assertEquals(test("a=probit(" + v + ")"),
//						sqrt(2) * org.apache.commons.math3.special.Erf.erf(2 * d - 1), EPSILON);
//			}
//		} catch (MaxCountExceededException e) {
//			// ignore invalid input
//		}
		assertEquals(test("a=ceil(" + v + ")"), ceil(d), EPSILON);
		assertEquals(test("a=trunc(" + v + ")"), floor(d), EPSILON);
		assertEquals(test("a=floor(" + v + ")"), floor(d), EPSILON);
		assertEquals(test("a=round(" + v + ")"), (double) round(d), EPSILON);
		assertEquals(test("a=signum(" + v + ")"), signum(d), EPSILON);
//		assertEquals(test("a=step(" + v + ")"), (double) (d > 0 ? 1 : 0), EPSILON);
	}

	private double logfact(double d) {
		double logFactorial = 0;
		for (int j = 2; j <= d; j++) {
			logFactorial += log(j);
		}
		return logFactorial;
	}

	@Test
	public void testTrigeomatryFunctions() {
//		testTrigeomatryFunctions("0.0", 0.0);
//		testTrigeomatryFunctions("0.5", 0.5);
//		testTrigeomatryFunctions("-0.5", -0.5);
//		testTrigeomatryFunctions("0.25", 0.25);
//		testTrigeomatryFunctions("-0.25", -0.25);
	}

	final static double EPSILON = 1e-11;
	
	public void testTrigeomatryFunctions(String v, double d) {
		assertEquals(test("a=sin(" + v + ")"), sin(d), EPSILON);
		assertEquals(test("a=cos(" + v + ")"), cos(d), EPSILON);
		assertEquals(test("a=tan(" + v + ")"), tan(d), EPSILON);

		assertEquals(test("a=asin(" + v + ")"), asin(d), EPSILON);
		assertEquals(test("a=acos(" + v + ")"), acos(d), EPSILON);
		assertEquals(test("a=atan(" + v + ")"), atan(d), EPSILON);

		assertEquals(test("a=arcsin(" + v + ")"), asin(d), EPSILON);
		assertEquals(test("a=arccos(" + v + ")"), acos(d), EPSILON);
		assertEquals(test("a=arctan(" + v + ")"), atan(d), EPSILON);

		assertEquals(test("a=sinh(" + v + ")"), sinh(d), EPSILON);
		assertEquals(test("a=cosh(" + v + ")"), cosh(d), EPSILON);
		assertEquals(test("a=tanh(" + v + ")"), tanh(d), EPSILON);

		assertEquals(test("a=asinh(" + v + ")"), asinh(d), EPSILON);
		assertEquals(test("a=acosh(" + v + ")"), acosh(d), EPSILON);
		assertEquals(test("a=atanh(" + v + ")"), atanh(d), EPSILON);

		assertEquals(test("a=arcsinh(" + v + ")"), asinh(d), EPSILON);
		assertEquals(test("a=arccosh(" + v + ")"), acosh(d), EPSILON);
		assertEquals(test("a=arctanh(" + v + ")"), atanh(d), EPSILON);
	}

	double asinh(double val) {
		return (Math.log(val + Math.sqrt(val * val + 1)));
	}

	double acosh(double val) {
		return (Math.log(val + Math.sqrt(val + 1) * Math.sqrt(val - 1)));
	}

	double atanh(double val) {
		return (0.5 * Math.log((1 + val) / (1 - val)));
	}

	protected static double test(String cmd) {
		try {
			UPMLListenerImpl parser = new UPMLListenerImpl(new StringBuilder(cmd));
			Object o = parser.parse("model{" + cmd + "}");
			if (o instanceof String) {
				System.out.println((String) o);
			}

			String pythonCmd = "import numpy;\n" + ((String) o).trim() + "\nprint(a)\n";

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
