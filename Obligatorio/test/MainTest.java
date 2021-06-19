import TADs.arraylist.MyArrayList;
import TADs.arraylist.MyArrayListImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void causeOfDeathTest(){
        String row = ",cause1,, cause2 and, cause3";
        MyArrayList<String> causes = new MyArrayListImpl<>(5);
        for (String t : row.split(",")) {
            for ( String s : t.split("and")) {
                if (!s.equals(" ") && !s.equals("")) causes.add(s);
            }
        }
        String[] causesOfDeath = new String[causes.size()];
        for (int i=0; i<causes.size(); i++) {
            causesOfDeath[i]=causes.get(i);
        }
        for (String c : causesOfDeath) {
            System.out.println(c);
        }
        assertTrue(true);
    }



}