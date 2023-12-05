import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {
	
    public static String readFileFromClasspath(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(filePath);
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
