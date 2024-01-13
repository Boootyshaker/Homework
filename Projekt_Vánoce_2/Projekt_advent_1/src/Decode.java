import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main class for decoding the input text file.
 */
public class Decode {
    private int vi_result;
    private String vS_line;
    private HashMap<String, Integer> cubes;

    public Decode() {
        this.vi_result = 0;
        this.vS_line = "";
        this.cubes = new HashMap<>();
    }

    /***
     * Decodes text file input and creates result.
     * @return vi_result
     */
    public int mi_decode(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("advent.txt"));
            cubes.put("red", 12);
            cubes.put("green", 13);
            cubes.put("blue", 14);

            do {

                if (vS_line == null){
                    break;
                }

                vS_line = bufferedReader.readLine();
                Pattern pattern = Pattern.compile("Game (\\d+):(.*)$");
                Matcher matcher;
                try {
                    matcher = pattern.matcher(vS_line);
                } catch (NullPointerException null_pointer_exception){
                    break;
                }
                System.out.println("line: " + vS_line);
                mv_add(matcher);

            } while (vS_line != null);
        }catch (IOException io_exception){
            System.out.println("There is nothing we can do.");
        }

        return vi_result;
    }


    /**
     * Divides line into 1.game id 2.numbers 3.colours.
     * If every condition is met, it will add a number to the vi_result variable.
     * @param matcher
     */
    public void mv_add(Matcher matcher){
        boolean vb_red = true;
        boolean vb_blue = true;
        boolean vb_green = true;

        matcher.find();
        int vi_game_id = Integer.parseInt(matcher.group(1));
        String[] a_sets = matcher.group(2).split(";");

        int vi_quantity;

        for (int i = 0; i < a_sets.length; i++){
            String[] a_cubes = a_sets[i].split(",");

            for (int j = 0; j < a_cubes.length; j++){
                String[] a_numbers_colors = a_cubes[j].split(" ");

                vi_quantity = Integer.parseInt(a_numbers_colors[1]);

                if (vi_quantity > cubes.get(a_numbers_colors[2])){
                    switch (a_numbers_colors[2]){
                        case "red" -> {
                            vb_red = false;
                        }
                        case "blue" -> {
                            vb_blue = false;
                        }
                        case "green" -> {
                            vb_green = false;
                        }
                    }
                }

            }
        }

        if (vb_red && vb_blue && vb_green){
            vi_result += vi_game_id;
        }
    }
}
