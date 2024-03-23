package prog_kr;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

import static prog_kr.InfoParser.parse;

public class Main {
    public static void main(String [] args) throws IOException {
        Scanner s = new Scanner(new File("C:\\Users\\alsum\\IdeaProjects\\aisd\\src\\prog_kr\\schedule.txt"));
        List<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        List<String> newList = Files.readAllLines(new File("C:\\Users\\alsum\\IdeaProjects\\aisd\\src\\prog_kr\\schedule.txt").toPath(), Charset.defaultCharset());

        for (int i = 0; i<newList.size(); i++){
            System.out.println(newList.get(i));
        }
        Map<String, List<Program>> map1 = parse(newList);
        for (int i = 0; i<map1.size(); i++){
            System.out.print(map1.get(i));
            System.out.println(map1.equals(i));
        }
    }
}


