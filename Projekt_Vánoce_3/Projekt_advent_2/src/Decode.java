import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main class for decoding the input text file.
 */
public class Decode {
    private int vi_result;
    private String vS_line;
    private Pattern pattern;
    private Matcher matcher;

    public Decode(){
        this.vi_result = 0;
        this.vS_line = "";
    }

    /***
     * Decodes text file input and creates result.
     * @return vi_result
     */
    public int mi_decode() {
        ArrayList<String> al_cards = new ArrayList<>();
        try {
            BufferedReader buffered_reader = new BufferedReader(new FileReader("advent.txt"));

            while (vS_line != null) {
                vS_line = buffered_reader.readLine();
                al_cards.add(vS_line);
            }

            al_cards.remove(null);

            int[] a_cards_quantity = new int[al_cards.size()];

            Arrays.fill(a_cards_quantity, 1);

            for (int i = 0; i < al_cards.size()-1; i++){
                int vi_next = 0; // serial number of a match


                ArrayList<String> al_winning_nums = mal_winning_numbers(al_cards.get(i));
                ArrayList<String> al_my_nums = mal_my_numbers(al_cards.get(i));


                for (int j = 0; j < al_my_nums.size(); j++){
                    for (int k = 0; k < al_winning_nums.size(); k++){

                        if (al_my_nums.get(j).equals(al_winning_nums.get(k))){


                            vi_next++;
                            if (i+vi_next <= al_cards.size()-1){
                                a_cards_quantity[i+vi_next] =a_cards_quantity[i+vi_next]+a_cards_quantity[i];
                            }
                        }
                    }
                }
            }

            for (int i = 0;  i  < a_cards_quantity.length; i++){
                vi_result+=a_cards_quantity[i];
            }
        }catch (IOException io_exception){
            System.out.println("File error mi_decode()");
        }

        return vi_result;
    }

    /**
     * Creates ArrayList of winning numbers of each card.
     * @param vS_input
     * @return
     */
    public ArrayList<String> mal_winning_numbers(String vS_input){

        ArrayList<String> al_winning_numbers = new ArrayList<>();
        String[] a_temp = vS_input.split("\\|");
        pattern = Pattern.compile("\\d+ ");
        matcher = pattern.matcher(a_temp[0]);
        while(matcher.find()){
            al_winning_numbers.add(matcher.group(0));
        }
        mv_remove_space(al_winning_numbers);
        return al_winning_numbers;
    }

    /**
     * Creates ArrayList of my numbers to compare with winning numbers.
     * @param vS_input
     * @return
     */
    public ArrayList<String> mal_my_numbers(String vS_input){

        ArrayList<String> al_my_numbers = new ArrayList<>();
        String[] a_temp = vS_input.split("\\|");
        pattern = Pattern.compile("\\d+");
        matcher = pattern.matcher(a_temp[1]);
        while(matcher.find()){
            al_my_numbers.add(matcher.group(0));
        }
        return al_my_numbers;
    }

    /**
     * Removes empty space from every number in al_input.
     * @param al_input
     */
    public void mv_remove_space(ArrayList<String> al_input){
        ArrayList<StringBuilder> al_temp = new ArrayList<>();

        for (int i = 0; i < al_input.size(); i++){
            al_temp.add(new StringBuilder(al_input.get(i)));
        }


        for (int i = 0; i < al_temp.size(); i++){
            al_temp.get(i).replace(al_temp.get(i).length()-1, al_temp.get(i).length(), "");
        }

        al_input.removeAll(al_input);

        for (int i = 0; i < al_temp.size(); i++){
            al_input.add(String.valueOf(al_temp.get(i)));
        }
    }
}
