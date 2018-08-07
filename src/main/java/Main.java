import services.reports.IReportGenerator;
import services.reports.ReportGenerator;
import model.instruction.Instruction;
import utils.InstructionsGeneratorData;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final Set<Instruction> instructions = InstructionsGeneratorData.getInstructions();
        final IReportGenerator reportGenerator = new ReportGenerator();

        System.out.println(reportGenerator.generateInstructionsReport(instructions));
    }
}
