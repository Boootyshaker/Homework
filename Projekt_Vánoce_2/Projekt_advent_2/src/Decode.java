import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main class for decoding the input text file.
 */
public class Decode {
    private int vi_result;
    private String vS_line;
    private HashMap<String, Integer> hm_cubes;

    public Decode() {
        this.vi_result = 0;
        this.vS_line = "";
        hm_cubes = new HashMap<>();
    }

    /***
     * Decodes text file input and creates result.
     * @return vi_result
     */
    public int mi_decode(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("advent.txt"));

            do {
                vS_line = bufferedReader.readLine();
                Pattern pattern = Pattern.compile("\\d+ (red|blue|green)");
                Matcher matcher = pattern.matcher(vS_line);
                ArrayList<String> al_matches = new ArrayList<>();
                while(matcher.find()){
                    al_matches.add(matcher.group());
                }

                String[] a_colours = new String[]{"red", "green", "blue"};

                for (int i = 0; i < 3; i++){
                    int vi_highest = 0;

                    for (int j = 0; j < al_matches.size(); j++){
                        if ((mi_nubmer(al_matches.get(j)) > vi_highest) && mS_colour(al_matches.get(j)).equals(a_colours[i])){
                            vi_highest = mi_nubmer(al_matches.get(j));
                        }
                    }

                    hm_cubes.put(a_colours[i], vi_highest);
                }

                vi_result += ((hm_cubes.get("red") * hm_cubes.get("blue") * hm_cubes.get("green")));
            }while (vS_line != null);

        }catch (IOException io_exception){
            System.out.println("IO error in mi_decode");
        }catch (NullPointerException null_pointer_exception){
        }

        return vi_result;
    }

    /**
     * Returns number from vS_input as Integer.
     * @param vS_input
     * @return
     */
    public int mi_nubmer(String vS_input){
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(vS_input);
        matcher.find();
        return Integer.parseInt(matcher.group());
    }

    /**
     * Returns colour of a cube.
     * @param vS_input
     * @return
     */
    public String mS_colour(String vS_input){
        Pattern pattern = Pattern.compile("red|blue|green");
        Matcher matcher = pattern.matcher(vS_input);
        matcher.find();

        return matcher.group();
    }
}
