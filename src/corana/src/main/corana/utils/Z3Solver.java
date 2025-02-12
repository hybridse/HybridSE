package main.corana.utils;

import main.corana.emulator.semantics.Environment;
import main.corana.emulator.semantics.Flags;
import main.corana.emulator.semantics.Register;
import main.corana.executor.Configs;
import main.corana.pojos.BitVec;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Z3Solver {
    private static String predefinedFuncs;
    public static void init() {
        predefinedFuncs = getPredefinedFunctions();
    }
    private static String getPredefinedFunctions() {
        MyStr smtDeclarations = new MyStr();
        try {
            List<String> files = FileUtils.getResourceFiles(Configs.smtFuncs);
            for (String f : files) {
                StringBuilder sb = new StringBuilder();
                FileUtils.readResource(Configs.smtFuncs + "/" + f, s -> sb.append(s).append("\n"));
                smtDeclarations.append(sb);
            }
            //smtDeclarations.append("(declare-fun SYM_STR_1 () (Array Int (_BitVec 32)))\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return smtDeclarations.value();
    }

    public static String solveBitVectorArithmeticByLibrary(String artm) {
        return "";
    }

    public static String solveBitVecArithmetic(String artm) {
        String z3Clause = "(simplify " + artm + ")";
        String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        if (artm.length() > 100) return "ERROR";
        try {
            File tempScript = File.createTempFile("z3_script_"+fileSuffix, ".smt");
            String tempZ3Script = tempScript.getAbsolutePath();
            FileUtils.write(tempZ3Script, z3Clause);

            String result = SysUtils.execCmd("z3 -smt2 " + tempZ3Script);

            FileUtils.delete(tempZ3Script);
            if (result != null) {
                if (result.contains("ERR0R") || result.contains("error")) {
                    return artm;
                } else {
                    return result.split("\n")[0];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
            //System.err.println("Command length: "+ tempZ3Script.length());
        }
        return "ERROR";
    }
    public static String simplify(String artm) {
        String simpStr = "(simplify " + artm + ")";

        try {
            ArrayList<String> bvVars = new ArrayList<>();
            ArrayList<String> allSym = new ArrayList<>(getAllSymVars(artm));

            ArrayList<String> boolVars = new ArrayList<>();
            Field[] fields = Flags.class.getFields();
            for (Field f : fields) {
                boolVars.add(f.getName().toLowerCase() + "_SYM");
            }
            bvVars.addAll(allSym.stream().filter(word -> !boolVars.contains(word)).collect(Collectors.toList()));

            String declaration = simplifyVars(bvVars, boolVars);
            String z3Clause = predefinedFuncs + declaration.replace("$mainAssert", simpStr);
            File tempScript = File.createTempFile("z3_script_", ".smt");
            String tempZ3Script = tempScript.getAbsolutePath();
            FileUtils.write(tempZ3Script, z3Clause);
            String result = SysUtils.execCmd("z3 -smt2 -T:1 " + tempZ3Script);
            FileUtils.delete(tempZ3Script);

            if (result != null) {
                if (result.contains("ERR0R") || result.contains("error") || result.contains("declare")) {
                    return artm;
                } else {
                    return result.split("\n")[0];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return artm;
    }

    private static List<String> getAllSymVars(Register registers) {
        List<String> result = new ArrayList<>();
        for (Character key : registers.regs.keySet()){
            String slot = registers.regs.get(key).toString();
            result.addAll(Arrays.asList(slot.split(" ")).stream().filter(word -> word.contains("SYM"))
                    .collect(Collectors.toList()));
        }
        List<String> resultWithoutDuplicates = new ArrayList<>(new HashSet<>(result));
        return resultWithoutDuplicates;
    }
    private static List<String> getAllMemVars(String pathConstraints) {
        List<String> result = new ArrayList<>();

        result.addAll(Arrays.asList(pathConstraints.split("\\s+|\\(|\\)|not")).stream().filter(word -> word.contains("MEM"))
                .collect(Collectors.toList()));

        List<String> resultWithoutDuplicates = new ArrayList<>(new HashSet<>(result));
        return resultWithoutDuplicates;
    }
    private static List<String> getAllSymVars(String pathConstraints) {
        List<String> result = new ArrayList<>();

            result.addAll(Arrays.asList(pathConstraints.split("\\s+|\\(|\\)|not")).stream().filter(word -> word.contains("SYM"))
                    .collect(Collectors.toList()));

        List<String> resultWithoutDuplicates = new ArrayList<>(new HashSet<>(result));
        return resultWithoutDuplicates;
    }

    /**
     * Declare needed variables and which value we need to obtain if SAT
     *
     * @param eval:   value need to evaluate if SAT
     * @param bvVars: input variables
     * @return
     */
    private static String declareEvalAndVars(String eval, ArrayList<String> bvVars, ArrayList<String> boolVars) {
        MyStr str = new MyStr();
        for (String v : bvVars) {
            str.append("(declare-const " + v + " (_ BitVec 32))\n");
        }
        for (String v : boolVars) {
            str.append("(declare-const " + v + " Bool)\n");
        }
        str.append("$mainAssert\n", "(check-sat)\n");
        str.append("(get-model)\n");
        if (eval != null) {
            str.append("(eval " + eval + ")", "\n");
        }
        return str.value();
    }
    private static String declareRegister(Environment env, String pc) {
        MyStr str = new MyStr();
//        for (String v : Mapping.regStrToChar.keySet()) {
//            str.append("(declare-const " + v + " (_ BitVec 32))\n");
//        }
        ArrayList<String> boolVars = new ArrayList<>();
        Field[] fields = Flags.class.getFields();
        for (Field f : fields) {
            boolVars.add(f.getName().toLowerCase() + "_SYM");
        }
        for (String v : boolVars) {
            str.append("(declare-const " + v + " Bool)\n");
        }
        ArrayList<String> allSym = new ArrayList<>(getAllMemVars(str.value()));
        for (String v : allSym) {
            str.append("(declare-const " + v + " Bool)\n");
        }
        for (Character v: env.register.regs.keySet()) {
            if (((BitVec) env.register.regs.get(v)).getSym().equals(Mapping.regCharToStr.get(v) + "_SYM")) {
                str.append("(declare-const " + Mapping.regCharToStr.get(v) + "_SYM " + " (_ BitVec 32))\n");
            } else {
                str.append("(declare-fun " + Mapping.regCharToStr.get(v) + "_SYM (_ BitVec 32) " + ((BitVec) env.register.regs.get(v)).getSym() + ")\n");
            }
        }

        str.append("$mainAssert\n", "(check-sat)\n");
        str.append("(get-model)\n");
        return str.value();
    }
    private static String simplifyVars(ArrayList<String> bvVars, ArrayList<String> boolVars) {
        MyStr str = new MyStr();
        for (String v : bvVars) {
            str.append("(declare-const " + v + " (_ BitVec 32))\n");
        }
        for (String v : boolVars) {
            str.append("(declare-const " + v + " Bool)\n");
        }
        str.append("$mainAssert\n");

        return str.value();
    }

    public static String getSMTFormula(String pathConstrain, Environment env) {
        String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Logs.info("\t-> Checking path constrains by Z3", "... ", Logs.shorten(pathConstrain), " ");
        //ArrayList<String> bvVars = new ArrayList<>();
        ArrayList<String> bvVars = new ArrayList<>(Mapping.regStrToChar.keySet());

        ArrayList<String> boolVars = new ArrayList<>();
        Field[] fields = Flags.class.getFields();
        for (Field f : fields) {
            boolVars.add(f.getName().toLowerCase() + "_SYM");
        }

        String declaration = declareRegister(env, pathConstrain);
        String finalConstraint = pathConstrain.equals("") ? "" : "(assert " + pathConstrain + ")";
        String z3Clause = predefinedFuncs + declaration.replace("$mainAssert", finalConstraint);

//        File tempScript = File.createTempFile("z3_script_"+fileSuffix, ".smt");
//        String tempZ3Script = tempScript.getAbsolutePath();
//        FileUtils.write(tempZ3Script, z3Clause);

        //String result = SysUtils.execCmd("z3 -smt2 -T:1 " + tempZ3Script);
        return z3Clause;
    }

    /**
     * Check the satisfiability of a path constraint.
     *
     * @param pathConstrain: path constraints
     * @param eval:          optional. null if do not need to evaluate anything
     * @return raw result returned by Z3 if SAT, null if UNSAT
     */
    public static String checkSAT(String pathConstrain, String eval) {
        try {
            String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            Logs.info("\t-> Checking path constrains by Z3", "... ", Logs.shorten(pathConstrain), " ");
            //ArrayList<String> bvVars = new ArrayList<>();
            ArrayList<String> bvVars = new ArrayList<>(Mapping.regStrToChar.keySet());
            ArrayList<String> allSym = new ArrayList<>(getAllSymVars(pathConstrain));

            ArrayList<String> boolVars = new ArrayList<>();
            Field[] fields = Flags.class.getFields();
            for (Field f : fields) {
                boolVars.add(f.getName().toLowerCase() + "_SYM");
            }
            //bvVars.addAll(allSym.stream().filter(word -> !boolVars.contains(word)).collect(Collectors.toList()));

            String declaration = declareEvalAndVars(eval, bvVars, boolVars);
            String finalConstraint = pathConstrain.equals("") ? "" : "(assert " + pathConstrain + ")";
            String z3Clause = predefinedFuncs + declaration.replace("$mainAssert", finalConstraint);

            File tempScript = File.createTempFile("z3_script_"+fileSuffix, ".smt");
            String tempZ3Script = tempScript.getAbsolutePath();
            FileUtils.write(tempZ3Script, z3Clause);

            String result = SysUtils.execCmd("z3 -smt2 -T:1 " + tempZ3Script);
            FileUtils.delete(tempZ3Script);

            if (result != null) {
                if (result.split("\n")[0].equalsIgnoreCase("sat")) {
                    Logs.infoLn("SAT");
                    return result;
                }
            }
            Logs.infoLn("UNSAT");
        } catch (Exception e) {
        } catch (Error e) {
        }
        //Logs.infoLn("UNSAT");
        return null;
    }

}

