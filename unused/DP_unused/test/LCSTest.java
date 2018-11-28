import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LCSTest {
    @Test
    public void testLCS1() {
        assertEquals(LongestCommonSubsequence.longestCommonSubsequence("katie", "hater"), 3);
    }

    @Test
    public void testLCS2() {
        assertEquals(LongestCommonSubsequence.longestCommonSubsequence("hieroglyphology", "michaelangelo"), 5);
    }

    @Test
    public void testLCS3() {
        assertEquals(LongestCommonSubsequence.longestCommonSubsequence("822746576", "802294393"), 4);
    }

    @Test
    public void testLCS4() {
        assertEquals(LongestCommonSubsequence.longestCommonSubsequence("2035098756706740437408037396949194623134934787291398849050295457953374965820342463412248725428168866", "5359895045467841055572249860671751729903240505412224738102366766463089285662600016200835448181504939"), 45);
    }

}
