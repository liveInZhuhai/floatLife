import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hadoop on 16-8-1.
 */
public class mybatisGenerate {
    public static void main(String[] args) throws IOException {

        String[] cmd = {"/bin/bash","-c","sh "+System.getProperty("user.dir")+"/src/mybatis_generate/mybatisGenerate.sh"};
        Process pb = Runtime.getRuntime().exec(cmd);

        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        input.close();
    }
    }
