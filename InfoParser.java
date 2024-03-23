package prog_kr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoParser {

    public static Map<String, List<Program>> parse(List<String> list) {
        Map<String, List<Program>> map = new HashMap<>();
        String currentTime = null;
        Program currentProgram = null;
        List<Program> programs = new ArrayList<>();
        for (String line : list) {
            if (line.startsWith("#")) {
                if (currentTime != null) {
                    map.put(currentTime, programs);
                }
                currentTime = line;
            } else if (currentTime != null) {
                if (currentProgram == null) {
                    currentProgram = new Program(currentTime, new BroadcastsTime(line), null);
                } else {
                    currentProgram.setName(line);
                    programs.add(currentProgram);
                    currentProgram = null;
                }
            } else throw new RuntimeException();
        }
        return map;
    }
}
