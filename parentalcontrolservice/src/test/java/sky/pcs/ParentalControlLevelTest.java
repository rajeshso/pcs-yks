package sky.pcs;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParentalControlLevelTest {

    @Test
    public void parentalControlShouldBeConstructedAndShouldReturnDesiredFormat() {
        ParentalControlLevel dateFormat = ParentalControlLevel.EIGHTEEN;
        assertThat(dateFormat.getId(), is("18"));
        assertThat(dateFormat.getVal(), is(4));
    }

    @Test
    public void stringIdShouldBeConvertedToEnum() {
        String givenId = "18";
        ParentalControlLevel eighteen = ParentalControlLevel.EIGHTEEN;
        Optional<ParentalControlLevel> level = ParentalControlLevel.of(givenId);
        assertThat(level.isPresent(), is(true));
        assertThat(level.get(), is(ParentalControlLevel.EIGHTEEN));
    }

    @Test
    public void givenComparisionOfHigherHighThanLowerShouldBeTrue() {
        ParentalControlLevel eighteenLevel = ParentalControlLevel.EIGHTEEN;
        ParentalControlLevel uLevel = ParentalControlLevel.U;
        assertTrue(eighteenLevel.isHigherThanOrEqualTo(uLevel));
    }

    @Test
    public void givenComparisionOfLowerHighThanHigherShouldBeFalse() {
        ParentalControlLevel uLevel = ParentalControlLevel.U;
        ParentalControlLevel eighteenLevel = ParentalControlLevel.EIGHTEEN;
        assertFalse(uLevel.isHigherThanOrEqualTo(eighteenLevel));
    }
}