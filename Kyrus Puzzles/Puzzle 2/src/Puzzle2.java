import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Puzzle2 {

    static ArrayList<Character> valid = new ArrayList<>();
    static char[] chars = {'q','w','e','r','t','y','u','i','o','p',
            'a','s','d','f','g','h','j','k','l',
            'z','x','c','v','b','n','m',' '};
    static HashMap<Character, Integer> counts;

    static int marker = 0;

    public static char toChar(String hex){
        counts.replace(hex.charAt(0), counts.get(hex.charAt(0)) + 1);
        counts.replace(hex.charAt(1), counts.get(hex.charAt(1)) + 1);
        return (char)Integer.parseInt(hex, 16);
    }

    public static void main(String[] args) {
        counts = new HashMap<>();
        for(char c = 'a'; c < 'g'; ++c) counts.put(c, 0);
        for(int i = 0; i < 10; ++i) counts.put(Integer.toString(i).charAt(0), 0);

        for(char c : chars) {valid.add(c); valid.add(Character.toUpperCase(c));}

        if(args.length != 1) System.out.println("Usage: <filename>");
        else {
            String filename = args[0];
            try (BufferedReader in = new BufferedReader(new FileReader(filename));
                 BufferedWriter out = new BufferedWriter((new FileWriter("solution.txt")))) {

                String line;
                char[] translated = new char[16];

                while(true){
                    line = in.readLine();

                    if(line != null) {
                        // *****************************************************************
                        // Translates all characters and prints only A-z

                        for(int j = 0; j < 32; j += 2) {
                            char temp = toChar(line.substring(j, j + 2));
                            if(valid.contains(temp)){
                                translated[marker] = temp;
                                if(marker == 15) {
                                    marker = 0;
                                    out.write(translated);
                                    translated[15] = ']';
                                    out.write('\n');
                                }
                                else marker++;
                            }
                        }


                        //for(char c : translated) if(valid.contains(c)) out.write(c);

                        // Prints note text in DWORDs instead of 4 DWORDs
                        // for (int i = 0; i < 4; ++i) out.write(line.substring(8 * i, 8 * (i + 1)) + '\n');
                    }
                    else break;
                }
                System.out.println("\n" + counts);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
}