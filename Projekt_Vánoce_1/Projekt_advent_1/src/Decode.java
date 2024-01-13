import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    /***
     * Decodes text file input and creates result.
     * @return vi_result
     */
    public int mi_decode(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("advent.txt"));
            while(true){
                vS_line = bufferedReader.readLine();
                if (vS_line == null){
                    break;
                }
                String vS_digits = "";
                for (int i = 0; i < vS_line.length(); i++){
                    if (!(mS_digit(vS_line.charAt(i)).equals(""))){
                        vS_digits += vS_line.charAt(i);
                        break;
                    }
                }
                for (int i = vS_line.length()-1; i > -1; i--){
                    if (!(mS_digit(vS_line.charAt(i)).equals(""))){
                        vS_digits += vS_line.charAt(i);
                        break;
                    }
                }
                vi_result += Integer.parseInt(vS_digits);
            }

        } catch (IOException ioException) {
            System.out.println("file error in mi_decode()");
        }
        return vi_result;
    }

    /**
     * Returns vc_input digit as a String.
     * @param vc_input
     * @return
     */
    public String mS_digit(char vc_input){
        String vS_digit;
        switch (vc_input){
            case '0' -> vS_digit = "0";
            case '1' -> vS_digit = "1";
            case '2' -> vS_digit = "2";
            case '3' -> vS_digit = "3";
            case '4' -> vS_digit = "4";
            case '5' -> vS_digit = "5";
            case '6' -> vS_digit = "6";
            case '7' -> vS_digit = "7";
            case '8' -> vS_digit = "8";
            case '9' -> vS_digit = "9";
            default -> vS_digit = "";
        }

        return vS_digit;
    }
}
