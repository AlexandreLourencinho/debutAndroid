package pt.loual.premiereappandroid.tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static pt.loual.premiereappandroid.tools.Constantes.ALPHABET;

public class GenClef
{

    public String randomKey()
    {
        List<Character> charList = Arrays.asList(ALPHABET);

        Collections.shuffle(charList);

        StringBuilder chaineCode = new StringBuilder();
        for (Character car : charList)
        {
            chaineCode.append(car);
        }
        return String.valueOf(chaineCode);
    }

}
