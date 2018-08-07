package services;

import services.workingDays.ArabiaWorkingDays;
import services.workingDays.DefaultWorkingDays;
import services.workingDays.IWorkingDays;
import model.instruction.Instruction;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

/**
 * A settlement date calculator
 */
public class InstructionSettlementDateCalculator {

    /**
     * Helper function to calculate settlement date for every given instruction
     * @param instructions the instructions of which the settlement date will be calculated
     */
    public static void calculateSettlementDates(Set<Instruction> instructions) {
        instructions.forEach(InstructionSettlementDateCalculator::calculateSettlementDate);
    }

    /**
     * Calculate the settlementDate Based on some logic
     * @param instruction the instruction of which the settlement date will be calculated
     */
    public static void calculateSettlementDate(Instruction instruction) {
        final IWorkingDays workingDaysMechanism = getWorkingDaysStrategy(instruction.getCurrency());

        final LocalDate newSettlementDate =
                workingDaysMechanism.findFirstWorkingDate(instruction.getSettlementDate());

        if (newSettlementDate != null) {
            instruction.setSettlementDate(newSettlementDate);
        }
    }

    /**
     * Select proper strategy based on the Currency
     * @param currency the currency to choose
     * @return the proper working days strategy
     */
    private static IWorkingDays getWorkingDaysStrategy(Currency currency) {
        if ((currency.getCurrencyCode().equals("AED")) ||
            (currency.getCurrencyCode().equals("SAR")))
        {
            return ArabiaWorkingDays.getInstance();
        }
        return DefaultWorkingDays.getInstance();
    }
}
