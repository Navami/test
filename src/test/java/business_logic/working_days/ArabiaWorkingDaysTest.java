package business_logic.working_days;

import org.junit.Before;
import org.junit.Test;
import services.workingDays.ArabiaWorkingDays;
import services.workingDays.IWorkingDays;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ArabiaWorkingDaysTest {
    private IWorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = ArabiaWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2018, 3, 26);

        assertEquals(aSunday, workingDays.findFirstWorkingDate(aSunday));
    }

    @Test
    public void testFindFirstWorkingDate_Thursday() throws Exception {
        final LocalDate aThursday = LocalDate.of(2018, 3, 23);

        assertEquals(aThursday, workingDays.findFirstWorkingDate(aThursday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2018, 3, 24);

        assertEquals(LocalDate.of(2018, 3, 26), workingDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2018, 3, 25);

        assertEquals(LocalDate.of(2018, 3, 26), workingDays.findFirstWorkingDate(aSaturday));
    }

}
