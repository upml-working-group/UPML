package upml;



import java.io.*;
import java.util.List;

/** A simple Read-Eval-Print-Loop for the JAGS language for BEAST **/ 
public class REPL {
	StringBuilder cmds;

	public REPL() {
		cmds = new StringBuilder();
	}
	
	public void doREPL() {
		while (true) {
			System.out.print(">");
			try {
				String cmd = (new BufferedReader(new InputStreamReader(System.in))).readLine();
				processCmd(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void processCmd(String cmd) {
		if (cmd == null) {
			return;
		}
		if (cmd.startsWith("quit") || cmd.startsWith("end") || cmd.startsWith("exit") || cmd == null) {
			System.exit(0);
		} else if (cmd.startsWith("?")) {
			cmd = cmd.trim().substring(1);
			UPMLListenerImpl.initNameMap();
			if (cmd.length() == 0) {
				StringBuilder b = new StringBuilder();
				b.append("Functions & Distributions: ");
				for (Object s : UPMLListenerImpl.mapNameToPython.keySet()) {
					b.append(s.toString());
					b.append(", ");
				}
				b.delete(b.length() - 2, b.length());
				System.out.println(b.toString())
				;
			} else {
				String _class = UPMLListenerImpl.mapNameToPython.getProperty(cmd);
				if (_class == null) {
					_class = UPMLListenerImpl.mapNameToPython.getProperty(cmd);
				}
				System.out.println(_class);
			}
		} else if (cmd.startsWith("save")) {
			save(cmd);
		} else {
			try {
				UPMLListenerImpl parser = new UPMLListenerImpl(cmds);
				Object o = parser.parse("model{" + cmd + "}");
				if (o instanceof String) {
					System.out.println((String) o);
				}
				//parser.parse(cmd);
			} catch (UPMLParsingException e) {
				System.out.println("model{" + cmd + "}");
				System.out.println(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Error: " + e.getMessage());
			}
		}
	}

	private void save(String cmd) {
		String [] strs = cmd.trim().split("\\s+");
		if (strs.length != 2) {
			System.out.println("Expected 'save <filename>' but got " + cmd);
			System.out.println("File is not saved");
			return;
		}
		try {
			String str = cmds.toString();

			FileWriter outfile = new FileWriter(new File(strs[1]));
	        outfile.write(str);
	        outfile.close();
	        System.err.println("Results in " + strs[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		System.out.println("A simple Read-Eval-Print-Loop for the JAGS language for UPML");
		REPL repl = new REPL();
		repl.doREPL();
	}


}
