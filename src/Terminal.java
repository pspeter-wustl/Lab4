import java.util.*;
import java.io.*;
/**
 * Class to facilitate input and output from the command line.
 */

public class Terminal {
    static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader((System.in)));
    static PrintStream outFile = null;
    static String fileName;
    static int callDepth = 0;
    static String prefix = "";
    static boolean fromFile = false;

    /**
     * Start a transcript file that logs all input and output
     * <br>The file is saved to the same directory as the project file
     * <br>Under Windows 95, use Cafe/Word/WordPad to view the text file
     * @param fn string to call transcript file
     * @return none
     */
     
    public static void recognizeAsLetter(char c) {
    	tokenizer.wordChars(c,c);
    }
    public static void startTranscript(String fn) {
        fileName = fn;
        try {
            outFile = new PrintStream(new FileOutputStream(fileName));
            outFile.println("----- Transcript file " + fileName + " started "
                            + new Date());
            System.out.println("----- Writing output to the file: " + fn + ". ");
        }
        catch (IOException e) {
            System.out.println("IOException in startTranscript opening " + fn + "\n" + e);
        }
    }
    
    public static void readInputFromFile(String fn) {
    	try {
    		tokenizer = new StreamTokenizer(new InputStreamReader(new FileInputStream(fn)));
		println("----- Reading input from the file: " + fn + ". ");
		fromFile = true;
        }
        catch (IOException e) {
            System.out.println("IOException in readInputFromFile opening " + fn + "\n" + e);
        }
    }


    /**
     * Stop the transcript file from logging input and output
     * @return none
     */
    public static void stopTranscript() {
        outFile.println("----- Transcript file " + fileName + " closed "
                        + new Date());
        outFile.close();
        outFile = null;
    }

    static String getPrefix(int i) {
        if (i > 0)
           return("| " + getPrefix(i-1));
        else
           return("");
    }
    /**
     * Print a string to the command line with a carriage return.
     * <br>Indentation added if printCall and printReturn are used.
     * @param s string to output
     * @return none
     */
    public static void println(String s) {
        if (outFile != null) outFile.println(prefix + s);
        System.out.println(prefix + s);
    }

    /**
     * Print a string to the command line.  No indentation is added.
     * @param s string to output
     * @return none
     */
    public static void print(String s) {
        if (outFile != null) outFile.print(s);
	System.out.print(s);
	System.out.flush();
    }

    /**
     * Prints the current indentation to the command line.
     * @return none
     */
    public static void indent() {
        System.out.print(prefix);
        System.out.flush();
        if (outFile != null) outFile.print(prefix);
    }

    /**
     * Print procedure call information to the command line with a
     * <br>carriage return.  Indentation is increased for future println
     * <br>and indent calls.
     * @param s procedure call information to output
     * @return none
     */
    public static void printCall(String s) {
        println(s);
        callDepth++;
        prefix = getPrefix(callDepth);
    }

    /**
     * Print a return value to the command line with a
     * <br>carriage return.  Indentation is decreased for future println
     * <br>and indent calls.
     * @param s string (the formatted return value) to output
     * @return none
     */
    public static void printReturn(String s) {
        callDepth--;
        prefix = getPrefix(callDepth);
        println(s);
    }

    /**
     * Read a double from the command line
     * @return a double
     */
    public static double readDouble() {
        scanTo(tokenizer.TT_NUMBER);
	if (outFile != null && !fromFile) outFile.println("" + tokenizer.nval);
        return (tokenizer.nval);
    }

    /**
     * Read a integer from the command line
     * @return a integer
     */
    public static int readInt() {
        scanTo(tokenizer.TT_NUMBER);
        if (outFile != null && !fromFile) outFile.println("" + (int) tokenizer.nval);
        return ((int) tokenizer.nval); //use Math.trunc??
    }

    /**
     * Read a string from the command line
     * @return a string
     */
    public static String readWord() {
        scanTo(tokenizer.TT_WORD);
        if (tokenizer.sval != null) {
            if (outFile != null && !fromFile) outFile.println("" + tokenizer.sval);
            return(tokenizer.sval);
        } else {
            if (outFile != null && !fromFile) outFile.println("" + ((char) tokenizer.ttype));
            return("" + ((char) tokenizer.ttype));
        }
    }

    static void scanTo(int tt) {
      // scans to a given token type:
      // TT_NUMBER or TT_WORD
        boolean found = false;
        try {
            while (!found) {
                int ttype = tokenizer.nextToken();
                if (ttype == tt) {
                    found = true;
                }
                else if (ttype == tokenizer.TT_EOF) {
                    println("End of File reached while scanning for input.");
                    found = true;
                }
                else if (ttype == tokenizer.TT_EOL) {
                    // skip over end of line
                }
                else if ((tt == tokenizer.TT_WORD)
                        && (ttype != tokenizer.TT_NUMBER)) {
                        found = true;
                }
            }
        }
        catch (IOException e) {
            println("IOException while scanning for input.");
        }
    }
}
