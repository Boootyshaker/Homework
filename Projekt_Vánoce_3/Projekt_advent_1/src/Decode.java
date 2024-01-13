import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main class for decoding the input text file.
 */
public class Decode {
    private int vi_result;
    private StringBuilder vSB_line;
    private Pattern pattern;
    private Matcher matcher;

    public Decode(){
        this.vi_result = 0;
        this.vSB_line = new StringBuilder("");
    }

    /***
     * Decodes text file input and creates result.
     * @return vi_result
     */
    public int mi_result(){
        try{
            BufferedReader buffered_reader = new BufferedReader(new FileReader("advent.txt"));
            while (vSB_line != null){

                vSB_line = new StringBuilder(buffered_reader.readLine());
                ArrayList<StringBuilder> al_win_nums = mal_winning_numbers(String.valueOf(vSB_line));
                ArrayList<StringBuilder> al_my_nums = mal_my_numbers(String.valueOf(vSB_line));
                mv_remove_space(al_win_nums);

                int vi_points = 0;
                int vi_power = 0;

                for (int i = 0; i < al_my_nums.size(); i++){
                    for (int j = 0; j < al_win_nums.size(); j++){
                        if (Integer.parseInt(String.valueOf(al_my_nums.get(i))) == Integer.parseInt(String.valueOf(al_win_nums.get(j)))){
                            vi_points = (int) Math.pow(2,vi_power);
                            vi_power++;
                        }
                    }
                }
                vi_result += vi_points;
            }

        } catch (IOException io_exception){
            System.out.println("file error mi_result");
        } catch (NullPointerException null_pointer_exception){

        }
        return vi_result;
    }

    /**
     * Creates ArrayList of winning numbers of each card.
     * @param vS_input
     * @return
     */
    public ArrayList<StringBuilder> mal_winning_numbers(String vS_input){

        ArrayList<StringBuilder> al_winning_numbers = new ArrayList<>();
        String[] a_temp = vS_input.split("\\|");
        pattern = Pattern.compile("\\d+ ");
        matcher = pattern.matcher(a_temp[0]);
        while(matcher.find()){
            al_winning_numbers.add(new StringBuilder(matcher.group(0)));
        }
        return al_winning_numbers;
    }

    /**
     * Creates ArrayList of my numbers to compare with winning numbers.
     * @param vS_input
     * @return
     */
    public ArrayList<StringBuilder> mal_my_numbers(String vS_input){

        ArrayList<StringBuilder> al_my_numbers = new ArrayList<>();
        String[] a_temp = vS_input.split("\\|");
        pattern = Pattern.compile("\\d+");
        matcher = pattern.matcher(a_temp[1]);
        while(matcher.find()){
            al_my_numbers.add(new StringBuilder(matcher.group(0)));
        }
        return al_my_numbers;
    }

    /**
     * Removes empty space in each al_input element.
     * @param al_input
     */
    public void mv_remove_space(ArrayList<StringBuilder> al_input){
        for (int i = 0; i < al_input.size(); i++){
            al_input.get(i).replace(al_input.get(i).length()-1, al_input.get(i).length(), "");
        }
    }

}
