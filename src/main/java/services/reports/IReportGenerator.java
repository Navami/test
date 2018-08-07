package services.reports;

import model.instruction.Instruction;

import java.util.Set;

public interface IReportGenerator {
    String generateInstructionsReport(Set<Instruction> instructions);
}
