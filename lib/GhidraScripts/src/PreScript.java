import ghidra.app.script.GhidraScript;
import ghidra.app.util.headless.HeadlessScript;

public class PreScript extends HeadlessScript {
    public void run() throws Exception {
        //TODO Add User Code Here
        enableHeadlessAnalysis(true);
        setAnalysisOption(currentProgram, "ARM Constant Reference Analyzer", "true");
        setAnalysisOption(currentProgram, "Reference", "true");
        analyzeAll(currentProgram);
    }
}
