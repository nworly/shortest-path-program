package io.deeplay;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static int height = 4;
    public static int width = 4;
    public static int fieldSize = height * width;

    public static int getResult(String fieldLayout, String race) throws Exception {

        if (fieldLayout == null || fieldLayout.length() != fieldSize) {
            throw new IllegalArgumentException(String.format("Field Layout must contain %d characters", fieldSize));
        }

        Pattern p = Pattern.compile("[^A-Z]");
        Matcher m = p.matcher(fieldLayout);

        if (m.find()) {
            throw new IllegalArgumentException("Field Layout must contain only capital A-Z characters");
        }

        List<String> rows = new ArrayList<>();

        int index = 0;
        while (index < fieldSize) {
            rows.add(fieldLayout.substring(index, index + width));
            index += width;
        }

//        this code works for barriers that are known in advance, for example S-W-T-P barriers only
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(new FileReader("barrier-values-for-each-race.json"));
//        JSONObject jsonObject = (JSONObject) obj;
//        JSONObject values = (JSONObject) jsonObject.get(race.toLowerCase());

        Map<String, Map<String, Integer>> barriersForRaces = new ObjectMapper().readValue(
                new File("barrier-values-for-each-race.json"),
                new TypeReference<Map<String, Map<String, Integer>>>() {
                }
        );

        Map<String, Integer> currentRaceValues = barriersForRaces.get(race.toLowerCase());

        int[][] field = new int[height][width];

        List<String> barriers = new ArrayList<>(currentRaceValues.size());
        List<Integer> barrierValues = new ArrayList<>(currentRaceValues.size());

        for (Map.Entry<String, Integer> entry : currentRaceValues.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            barriers.add(key);
            barrierValues.add(value);
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                String cell = String.valueOf(rows.get(i).charAt(j));

                if (barriers.contains(cell)) {
                    field[i][j] = barrierValues.get(barriers.indexOf(cell));
                } else {
                    throw new IllegalArgumentException(
                            String.format("Field Layout must contain only %s characters", barriers)
                    );
                }
            }
        }

//        this code works for barriers that are known in advance, for example S-W-T-P barriers only
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                char c = rows.get(i).charAt(j);
//                switch (c) {
//                    case 'S':
//                        field[i][j] = (int) (long) values.get("S");
//                        break;
//                    case 'W':
//                        field[i][j] = (int) (long) values.get("W");
//                        break;
//                    case 'T':
//                        field[i][j] = (int) (long) values.get("T");
//                        break;
//                    case 'P':
//                        field[i][j] = (int) (long) values.get("P");
//                        break;
//                }
//            }
//        }

        return Algorithm.getShortestPath(field, height, width);
    }
}
