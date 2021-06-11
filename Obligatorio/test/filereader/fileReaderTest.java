package filereader;

import TADs.hash.MyClosedHashImpl;
import TADs.hash.MyHash;
import entities.CastMember;
import entities.CauseOfDeath;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class fileReaderTest {

    @Test
    public void testFlujoNormal(){
        fileReader.readCastMember();
    }

    @Test
    public void testCastMember(){
        MyHash<Integer, CastMember> castMemberHash = new MyClosedHashImpl<>();
        MyHash<Integer, CauseOfDeath> causeOfDeathHash = new MyClosedHashImpl<>();
        fileReader.readCastMember();
        System.out.println(castMemberHash.get(1).getImdbNameId());
        System.out.println(castMemberHash.get(1).getChildren());
        assertTrue(true);
    }
}
