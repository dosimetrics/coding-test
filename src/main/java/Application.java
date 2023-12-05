import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Application {

    public static void main(String[] args) throws IOException {
        new Application().run(Util.readFileFromClasspath("icd-codes.txt"));
    }

    private void run(String data) {
    	// You can ignore these lines for the task from here
    	// 
        List codes = new ArrayList();
        for (String line : data.split("\r?\n")) {
            String[] parts = line.split(";");
            String patient = parts[0];
            for (String code : parts[1].split(",")) {
                if (code.length() > 0) {
                    codes.add(code);
                }
            }
        }
        //
        // to here
        
        Map r = hugo(codes);
        System.out.println(StringUtils.join(r.keySet(), ", "));
        System.out.println(StringUtils.join(r.values(), ", "));
    }

    private static Integer M = 1;

    public static Map hugo(Collection c) {
        Iterator q = c.iterator();
        Map a = new HashMap();
        while (q.hasNext()) {
            Object x = q.next(); Integer b = (Integer) a.get(x);
            if (null == b) a.put(x, new Integer(M)); else a.put(x, new Integer(b.intValue() + M));
        }
        return a;
    }

}