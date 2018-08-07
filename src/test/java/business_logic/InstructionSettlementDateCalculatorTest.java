package business_logic;

import model.instruction.Instruction;
import model.instruction.InstructionDetails;
import model.instruction.TradeAction;
import org.junit.Test;
import services.InstructionSettlementDateCalculator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

public class InstructionSettlementDateCalculatorTest {
    @Test
    public void calculateSettlementDate_default_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 3, 24);

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 3, 10),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_default_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 3, 26);

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 3, 10),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(100.25)));

        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        assertEquals(LocalDate.of(2018, 3, 27), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 3, 24);

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 3, 10),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("AED"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        assertEquals(LocalDate.of(2018, 3, 26), fakeInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 3, 26);

        final Instruction fakeInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 3, 10),
                initialSettlementDate,
                new InstructionDetails(
                        Currency.getInstance("SAR"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        InstructionSettlementDateCalculator.calculateSettlementDate(fakeInstruction);

        assertEquals(initialSettlementDate, fakeInstruction.getSettlementDate());
    }
}
