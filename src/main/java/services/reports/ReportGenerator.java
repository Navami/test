package services.reports;

import services.InstructionSettlementDateCalculator;
import services.InstructionSettlementStatsCalculator;
import services.ranking.Rank;
import model.instruction.Instruction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class ReportGenerator implements IReportGenerator {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public String generateInstructionsReport(Set<Instruction> instructions) {
        InstructionSettlementDateCalculator.calculateSettlementDates(instructions);

        return generateDailyOutgoingRanking(instructions,
                generateDailyIncomingRanking(instructions,
                generateDailyIncomingAmount(instructions,
                generateDailyOutgoingAmount(instructions, stringBuilder))))
            .toString();
    }

    private static StringBuilder generateDailyOutgoingAmount(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                InstructionSettlementStatsCalculator.calculateDailyOutgoingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Daily Outgoing Amount          \n")
                .append("----------------------------------------\n")
                .append("       Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingAmount(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                InstructionSettlementStatsCalculator.calculateDailyIncomingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("        Daily Incoming Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                    .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyOutgoingRanking(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking =
                InstructionSettlementStatsCalculator.calculateDailyOutgoingRanking(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Daily Outgoing Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingRanking.keySet()) {
            for (Rank rank : dailyOutgoingRanking.get(date)) {
                stringBuilder
                    .append(date).append("   |      ")
                    .append(rank.getRank()).append("     |    ")
                    .append(rank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingRanking(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking =
                InstructionSettlementStatsCalculator.calculateDailyIncomingRanking(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Daily Incoming Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyIncomingRanking.keySet()) {
            for (Rank rank : dailyIncomingRanking.get(date)) {
                stringBuilder
                        .append(date).append("   |      ")
                        .append(rank.getRank()).append("     |    ")
                        .append(rank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }
}
