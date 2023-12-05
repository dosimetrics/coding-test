import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Application {

    public static void main(String[] args) throws IOException {
        new Application().run(readFileFromClasspath("icd-codes.txt"));
    }

    private void run(String data) {
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

    public static String readFileFromClasspath(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        InputStream inputStream = Application.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } else {
            throw new IOException("File not found: " + filePath);
        }
        return content.toString();
    }
}