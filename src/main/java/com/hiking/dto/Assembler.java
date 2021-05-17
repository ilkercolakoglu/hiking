package com.hiking.dto;

/**
 * @developer -- ilkercolakoglu
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Assembler {

    public static void main(String[] args) throws IOException {
        Defrag defrag = new Assembler().new Defrag();
        if (args.length > 0) {
            List<String> strList=new ArrayList<>();
            strList.add("afrfr");
            strList.add("agbg");
            strList.add("rfrfr");
            strList.stream().map(defrag::reassemble).forEach(System.out::println);
            return;
        }

        String cev = defrag.reassemble("O draconia;conian devil! Oh la;h lame sa;saint!");

        System.out.println(cev);

        // assert from language, replace with junit
        assert (defrag.reassemble("ABCDEF;DEFG").equals("ABCDEFG"));
        assert (defrag.reassemble("XYAB;PXY").equals("PXYAB"));
        assert (defrag.reassemble("XAB;ABC").equals("XABC"));
        assert (defrag.reassemble("ABCDEF;XYZABC").equals("XYZABCDEF"));
        assert (defrag.reassemble("ABCDEF;XCDEZ").equals("ABCDEF"));
        assert (defrag.reassemble("ABCDEF;BCDE").equals("ABCDEF"));
        assert (defrag
                .reassemble("O draconia;conian devil! Oh la;h lame sa;saint!")
                .equals("O draconian devil! Oh lame saint!"));

    }

    class Defrag {

        public String reassemble(String line) {

            List<String> list = getFragments(line);
            String text = list.get(0);
            list.remove(0);

            for (int loop = list.size() - 1; loop >= 0; loop--) {
                int max = 0, index = 0;
                int matchedFrag = 0;
                int textLength = 0, findMeLength = 0;
                boolean prefix = false;
                // lets go through each fragment
                for (int entryIndex = list.size() - 1; entryIndex >= 0; entryIndex--) {

                    String findMe = list.get(entryIndex);
                    textLength = text.length();
                    findMeLength = findMe.length();

                    // at the front
                    for (int k = findMe.length() - 1; k > 0; k--) {
                        int region = findMeLength - k;
                        if (text.regionMatches(0, findMe, k, region)) {
                            if (region > max) {
                                index = k;
                                max = region;
                                matchedFrag = entryIndex;
                                prefix = true;
                            }
                        }
                    }

                    // at the back
                    for (int k = 0; k < text.length(); k++) {
                        int region = (k + findMeLength <= textLength) ? findMeLength
                                : textLength - k;
                        if (text.regionMatches(k, findMe, 0, region)) {
                            if (region > max) {
                                index = k;
                                max = region;
                                matchedFrag = entryIndex;
                                prefix = false;
                            }
                        }
                    }
                }

                // stitch text together
                if (prefix) {
                    text = list.get(matchedFrag).substring(0, index) + text;
                } else if (index > textLength - list.get(matchedFrag).length()) { // Suffix
                    text = text
                            + list.get(matchedFrag).substring(
                            textLength - index);
                }
                list.remove(matchedFrag);
            }
            return text;
        }

    }

    // turn fragments into a list
    private List<String> getFragments(String line) {
        StringTokenizer st = new StringTokenizer(line, ";");
        List<String> fragments = new ArrayList<>();
        while (st.hasMoreTokens()) {
            fragments.add(st.nextToken());
        }
        return fragments;
    }

}
