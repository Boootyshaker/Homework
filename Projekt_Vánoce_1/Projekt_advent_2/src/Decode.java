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
    private String vS_line;

    public Decode() {
        this.vi_result = 0;
        this.vS_line = "";
    }

    /**
     * Decodes text file input and creates result.
     * @return vi_result
     */
    public int mi_decode(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("advent.txt"));
            while (true){
                vS_line = bufferedReader.readLine();
                if (vS_line == null){
                    break;
                }
                ArrayList<String> al_matches = mS_regex(vS_line);
                String vS_digits = mS_digits(al_matches.get(0));
                vS_digits += mS_digits(al_matches.get(al_matches.size()-1));
                vi_result += Integer.parseInt(vS_digits);
            }
        } catch (IOException io_exception) {
            System.out.println("File error mi_decode()");
        }

        return vi_result;
    }

    /**
     * Returns Arraylist of every match from regex tester.
     * @param vS_input
     * @return
     */
    public ArrayList<String> mS_regex(String vS_input){
        ArrayList<String> al_matches = new ArrayList<>();
        Pattern pattern = Pattern.compile("[0-9]|\\Qone\\E|\\Qtwo\\E|\\Qthree\\E|\\Qfour\\E|\\Qfive\\E|\\Qsix\\E|\\Qseven\\E|\\Qeight\\E|\\Qnine\\E|\\Qzero\\E", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(vS_input);
        if (matcher.find()){
            do {
                al_matches.add(matcher.group());
            }while(matcher.find(matcher.start()+1));
        }

        return al_matches;
    }

    /**
     * Returns word as a digit.
     * @param vS_input
     * @return
     */
    public String mS_digits(String vS_input){
        String vS_digit = "";
        switch (vS_input){
            case "zero" -> vS_digit = "0";
            case "one" -> vS_digit = "1";
            case "two" -> vS_digit = "2";
            case "three" -> vS_digit = "3";
            case "four" -> vS_digit = "4";
            case "five" -> vS_digit = "5";
            case "six" -> vS_digit = "6";
            case "seven" -> vS_digit = "7";
            case "eight" -> vS_digit = "8";
            case "nine" -> vS_digit = "9";
            default -> vS_digit = vS_input;
        }

        return vS_digit;
    }
}
