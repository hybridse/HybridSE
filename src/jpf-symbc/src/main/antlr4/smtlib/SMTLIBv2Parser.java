package antlr4.smtlib;

// Generated from SMTLIBv2.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SMTLIBv2Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Comment=1, ParOpen=2, ParClose=3, Semicolon=4, String=5, QuotedSymbol=6, 
		PS_Not=7, PS_Bool=8, PS_ContinuedExecution=9, PS_Error=10, PS_False=11, 
		PS_ImmediateExit=12, PS_Incomplete=13, PS_Logic=14, PS_Memout=15, PS_Sat=16, 
		PS_Success=17, PS_Theory=18, PS_True=19, PS_Unknown=20, PS_Unsupported=21, 
		PS_Unsat=22, CMD_Assert=23, CMD_CheckSat=24, CMD_CheckSatAssuming=25, 
		CMD_DeclareConst=26, CMD_DeclareDatatype=27, CMD_DeclareDatatypes=28, 
		CMD_DeclareFun=29, CMD_DeclareSort=30, CMD_DefineFun=31, CMD_DefineFunRec=32, 
		CMD_DefineFunsRec=33, CMD_DefineSort=34, CMD_Echo=35, CMD_Exit=36, CMD_GetAssertions=37, 
		CMD_GetAssignment=38, CMD_GetInfo=39, CMD_GetModel=40, CMD_GetOption=41, 
		CMD_GetProof=42, CMD_GetUnsatAssumptions=43, CMD_GetUnsatCore=44, CMD_GetValue=45, 
		CMD_Pop=46, CMD_Push=47, CMD_Reset=48, CMD_ResetAssertions=49, CMD_SetInfo=50, 
		CMD_SetLogic=51, CMD_SetOption=52, GRW_Exclamation=53, GRW_Underscore=54, 
		GRW_As=55, GRW_Binary=56, GRW_Decimal=57, GRW_Exists=58, GRW_Hexadecimal=59, 
		GRW_Forall=60, GRW_Let=61, GRW_Match=62, GRW_Numeral=63, GRW_Par=64, GRW_String=65, 
		Numeral=66, Binary=67, HexDecimal=68, Decimal=69, Colon=70, PK_AllStatistics=71, 
		PK_AssertionStackLevels=72, PK_Authors=73, PK_Category=74, PK_Chainable=75, 
		PK_Definition=76, PK_DiagnosticOutputChannel=77, PK_ErrorBehaviour=78, 
		PK_Extension=79, PK_Funs=80, PK_FunsDescription=81, PK_GlobalDeclarations=82, 
		PK_InteractiveMode=83, PK_Language=84, PK_LeftAssoc=85, PK_License=86, 
		PK_Named=87, PK_Name=88, PK_Notes=89, PK_Pattern=90, PK_PrintSuccess=91, 
		PK_ProduceAssertions=92, PK_ProduceAssignments=93, PK_ProduceModels=94, 
		PK_ProduceProofs=95, PK_ProduceUnsatAssumptions=96, PK_ProduceUnsatCores=97, 
		PK_RandomSeed=98, PK_ReasonUnknown=99, PK_RegularOutputChannel=100, PK_ReproducibleResourceLimit=101, 
		PK_RightAssoc=102, PK_SmtLibVersion=103, PK_Sorts=104, PK_SortsDescription=105, 
		PK_Source=106, PK_Status=107, PK_Theories=108, PK_Values=109, PK_Verbosity=110, 
		PK_Version=111, RS_Model=112, UndefinedSymbol=113, WS=114;
	public static final int
		RULE_start = 0, RULE_generalReservedWord = 1, RULE_simpleSymbol = 2, RULE_quotedSymbol = 3, 
		RULE_predefSymbol = 4, RULE_predefKeyword = 5, RULE_symbol = 6, RULE_numeral = 7, 
		RULE_decimal = 8, RULE_hexadecimal = 9, RULE_binary = 10, RULE_string = 11, 
		RULE_keyword = 12, RULE_spec_constant = 13, RULE_s_expr = 14, RULE_index = 15, 
		RULE_identifier = 16, RULE_attribute_value = 17, RULE_attribute = 18, 
		RULE_sort = 19, RULE_qual_identifer = 20, RULE_var_binding = 21, RULE_sorted_var = 22, 
		RULE_pattern = 23, RULE_match_case = 24, RULE_term = 25, RULE_sort_symbol_decl = 26, 
		RULE_meta_spec_constant = 27, RULE_fun_symbol_decl = 28, RULE_par_fun_symbol_decl = 29, 
		RULE_theory_attribute = 30, RULE_theory_decl = 31, RULE_logic_attribue = 32, 
		RULE_logic = 33, RULE_sort_dec = 34, RULE_selector_dec = 35, RULE_constructor_dec = 36, 
		RULE_datatype_dec = 37, RULE_function_dec = 38, RULE_function_def = 39, 
		RULE_prop_literal = 40, RULE_script = 41, RULE_cmd_assert = 42, RULE_cmd_checkSat = 43, 
		RULE_cmd_checkSatAssuming = 44, RULE_cmd_declareConst = 45, RULE_cmd_declareDatatype = 46, 
		RULE_cmd_declareDatatypes = 47, RULE_cmd_declareFun = 48, RULE_cmd_declareSort = 49, 
		RULE_cmd_defineFun = 50, RULE_cmd_defineFunRec = 51, RULE_cmd_defineFunsRec = 52, 
		RULE_cmd_defineSort = 53, RULE_cmd_echo = 54, RULE_cmd_exit = 55, RULE_cmd_getAssertions = 56, 
		RULE_cmd_getAssignment = 57, RULE_cmd_getInfo = 58, RULE_cmd_getModel = 59, 
		RULE_cmd_getOption = 60, RULE_cmd_getProof = 61, RULE_cmd_getUnsatAssumptions = 62, 
		RULE_cmd_getUnsatCore = 63, RULE_cmd_getValue = 64, RULE_cmd_pop = 65, 
		RULE_cmd_push = 66, RULE_cmd_reset = 67, RULE_cmd_resetAssertions = 68, 
		RULE_cmd_setInfo = 69, RULE_cmd_setLogic = 70, RULE_cmd_setOption = 71, 
		RULE_command = 72, RULE_b_value = 73, RULE_option = 74, RULE_info_flag = 75, 
		RULE_error_behaviour = 76, RULE_reason_unknown = 77, RULE_model_response = 78, 
		RULE_info_response = 79, RULE_valuation_pair = 80, RULE_t_valuation_pair = 81, 
		RULE_check_sat_response = 82, RULE_echo_response = 83, RULE_get_assertions_response = 84, 
		RULE_get_assignment_response = 85, RULE_get_info_response = 86, RULE_get_model_response = 87, 
		RULE_get_option_response = 88, RULE_get_proof_response = 89, RULE_get_unsat_assump_response = 90, 
		RULE_get_unsat_core_response = 91, RULE_get_value_response = 92, RULE_specific_success_response = 93, 
		RULE_general_response = 94;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "generalReservedWord", "simpleSymbol", "quotedSymbol", "predefSymbol", 
			"predefKeyword", "symbol", "numeral", "decimal", "hexadecimal", "binary", 
			"string", "keyword", "spec_constant", "s_expr", "index", "identifier", 
			"attribute_value", "attribute", "sort", "qual_identifer", "var_binding", 
			"sorted_var", "pattern", "match_case", "term", "sort_symbol_decl", "meta_spec_constant", 
			"fun_symbol_decl", "par_fun_symbol_decl", "theory_attribute", "theory_decl", 
			"logic_attribue", "logic", "sort_dec", "selector_dec", "constructor_dec", 
			"datatype_dec", "function_dec", "function_def", "prop_literal", "script", 
			"cmd_assert", "cmd_checkSat", "cmd_checkSatAssuming", "cmd_declareConst", 
			"cmd_declareDatatype", "cmd_declareDatatypes", "cmd_declareFun", "cmd_declareSort", 
			"cmd_defineFun", "cmd_defineFunRec", "cmd_defineFunsRec", "cmd_defineSort", 
			"cmd_echo", "cmd_exit", "cmd_getAssertions", "cmd_getAssignment", "cmd_getInfo", 
			"cmd_getModel", "cmd_getOption", "cmd_getProof", "cmd_getUnsatAssumptions", 
			"cmd_getUnsatCore", "cmd_getValue", "cmd_pop", "cmd_push", "cmd_reset", 
			"cmd_resetAssertions", "cmd_setInfo", "cmd_setLogic", "cmd_setOption", 
			"command", "b_value", "option", "info_flag", "error_behaviour", "reason_unknown", 
			"model_response", "info_response", "valuation_pair", "t_valuation_pair", 
			"check_sat_response", "echo_response", "get_assertions_response", "get_assignment_response", 
			"get_info_response", "get_model_response", "get_option_response", "get_proof_response", 
			"get_unsat_assump_response", "get_unsat_core_response", "get_value_response", 
			"specific_success_response", "general_response"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'('", "')'", "';'", null, null, "'not'", "'Bool'", "'continued-execution'", 
			"'error'", "'false'", "'immediate-exit'", "'incomplete'", "'logic'", 
			"'memout'", "'sat'", "'success'", "'theory'", "'true'", "'unknown'", 
			"'unsupported'", "'unsat'", "'assert'", "'check-sat'", "'check-sat-assuming'", 
			"'declare-const'", "'declare-datatype'", "'declare-datatypes'", "'declare-fun'", 
			"'declare-sort'", "'define-fun'", "'define-fun-rec'", "'define-funs-rec'", 
			"'define-sort'", "'echo'", "'exit'", "'get-assertions'", "'get-assignment'", 
			"'get-info'", "'get-model'", "'get-option'", "'get-proof'", "'get-unsat-assumptions'", 
			"'get-unsat-core'", "'get-value'", "'pop'", "'push'", "'reset'", "'reset-assertions'", 
			"'set-info'", "'set-logic'", "'set-option'", "'!'", "'_'", "'as'", "'BINARY'", 
			"'DECIMAL'", "'exists'", "'HEXADECIMAL'", "'forall'", "'let'", "'match'", 
			"'NUMERAL'", "'par'", "'string'", null, null, null, null, "':'", "':all-statistics'", 
			"':assertion-stack-levels'", "':authors'", "':category'", "':chainable'", 
			"':definition'", "':diagnostic-output-channel'", "':error-behavior'", 
			"':extensions'", "':funs'", "':funs-description'", "':global-declarations'", 
			"':interactive-mode'", "':language'", "':left-assoc'", "':license'", 
			"':named'", "':name'", "':notes'", "':pattern'", "':print-success'", 
			"':produce-assertions'", "':produce-assignments'", "':produce-models'", 
			"':produce-proofs'", "':produce-unsat-assumptions'", "':produce-unsat-cores'", 
			"':random-seed'", "':reason-unknown'", "':regular-output-channel'", "':reproducible-resource-limit'", 
			"':right-assoc'", "':smt-lib-version'", "':sorts'", "':sorts-description'", 
			"':source'", "':status'", "':theories'", "':values'", "':verbosity'", 
			"':version'", "'model'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Comment", "ParOpen", "ParClose", "Semicolon", "String", "QuotedSymbol", 
			"PS_Not", "PS_Bool", "PS_ContinuedExecution", "PS_Error", "PS_False", 
			"PS_ImmediateExit", "PS_Incomplete", "PS_Logic", "PS_Memout", "PS_Sat", 
			"PS_Success", "PS_Theory", "PS_True", "PS_Unknown", "PS_Unsupported", 
			"PS_Unsat", "CMD_Assert", "CMD_CheckSat", "CMD_CheckSatAssuming", "CMD_DeclareConst", 
			"CMD_DeclareDatatype", "CMD_DeclareDatatypes", "CMD_DeclareFun", "CMD_DeclareSort", 
			"CMD_DefineFun", "CMD_DefineFunRec", "CMD_DefineFunsRec", "CMD_DefineSort", 
			"CMD_Echo", "CMD_Exit", "CMD_GetAssertions", "CMD_GetAssignment", "CMD_GetInfo", 
			"CMD_GetModel", "CMD_GetOption", "CMD_GetProof", "CMD_GetUnsatAssumptions", 
			"CMD_GetUnsatCore", "CMD_GetValue", "CMD_Pop", "CMD_Push", "CMD_Reset", 
			"CMD_ResetAssertions", "CMD_SetInfo", "CMD_SetLogic", "CMD_SetOption", 
			"GRW_Exclamation", "GRW_Underscore", "GRW_As", "GRW_Binary", "GRW_Decimal", 
			"GRW_Exists", "GRW_Hexadecimal", "GRW_Forall", "GRW_Let", "GRW_Match", 
			"GRW_Numeral", "GRW_Par", "GRW_String", "Numeral", "Binary", "HexDecimal", 
			"Decimal", "Colon", "PK_AllStatistics", "PK_AssertionStackLevels", "PK_Authors", 
			"PK_Category", "PK_Chainable", "PK_Definition", "PK_DiagnosticOutputChannel", 
			"PK_ErrorBehaviour", "PK_Extension", "PK_Funs", "PK_FunsDescription", 
			"PK_GlobalDeclarations", "PK_InteractiveMode", "PK_Language", "PK_LeftAssoc", 
			"PK_License", "PK_Named", "PK_Name", "PK_Notes", "PK_Pattern", "PK_PrintSuccess", 
			"PK_ProduceAssertions", "PK_ProduceAssignments", "PK_ProduceModels", 
			"PK_ProduceProofs", "PK_ProduceUnsatAssumptions", "PK_ProduceUnsatCores", 
			"PK_RandomSeed", "PK_ReasonUnknown", "PK_RegularOutputChannel", "PK_ReproducibleResourceLimit", 
			"PK_RightAssoc", "PK_SmtLibVersion", "PK_Sorts", "PK_SortsDescription", 
			"PK_Source", "PK_Status", "PK_Theories", "PK_Values", "PK_Verbosity", 
			"PK_Version", "RS_Model", "UndefinedSymbol", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SMTLIBv2.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SMTLIBv2Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public LogicContext logic() {
			return getRuleContext(LogicContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SMTLIBv2Parser.EOF, 0); }
		public Theory_declContext theory_decl() {
			return getRuleContext(Theory_declContext.class,0);
		}
		public ScriptContext script() {
			return getRuleContext(ScriptContext.class,0);
		}
		public General_responseContext general_response() {
			return getRuleContext(General_responseContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				logic();
				setState(191);
				match(EOF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(193);
				theory_decl();
				setState(194);
				match(EOF);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(196);
				script();
				setState(197);
				match(EOF);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(199);
				general_response();
				setState(200);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GeneralReservedWordContext extends ParserRuleContext {
		public TerminalNode GRW_Exclamation() { return getToken(SMTLIBv2Parser.GRW_Exclamation, 0); }
		public TerminalNode GRW_Underscore() { return getToken(SMTLIBv2Parser.GRW_Underscore, 0); }
		public TerminalNode GRW_As() { return getToken(SMTLIBv2Parser.GRW_As, 0); }
		public TerminalNode GRW_Binary() { return getToken(SMTLIBv2Parser.GRW_Binary, 0); }
		public TerminalNode GRW_Decimal() { return getToken(SMTLIBv2Parser.GRW_Decimal, 0); }
		public TerminalNode GRW_Exists() { return getToken(SMTLIBv2Parser.GRW_Exists, 0); }
		public TerminalNode GRW_Hexadecimal() { return getToken(SMTLIBv2Parser.GRW_Hexadecimal, 0); }
		public TerminalNode GRW_Forall() { return getToken(SMTLIBv2Parser.GRW_Forall, 0); }
		public TerminalNode GRW_Let() { return getToken(SMTLIBv2Parser.GRW_Let, 0); }
		public TerminalNode GRW_Match() { return getToken(SMTLIBv2Parser.GRW_Match, 0); }
		public TerminalNode GRW_Numeral() { return getToken(SMTLIBv2Parser.GRW_Numeral, 0); }
		public TerminalNode GRW_Par() { return getToken(SMTLIBv2Parser.GRW_Par, 0); }
		public TerminalNode GRW_String() { return getToken(SMTLIBv2Parser.GRW_String, 0); }
		public TerminalNode RS_Model() { return getToken(SMTLIBv2Parser.RS_Model, 0); }
		public GeneralReservedWordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generalReservedWord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGeneralReservedWord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGeneralReservedWord(this);
		}
	}

	public final GeneralReservedWordContext generalReservedWord() throws RecognitionException {
		GeneralReservedWordContext _localctx = new GeneralReservedWordContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_generalReservedWord);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_la = _input.LA(1);
			if ( !(((((_la - 53)) & ~0x3f) == 0 && ((1L << (_la - 53)) & ((1L << (GRW_Exclamation - 53)) | (1L << (GRW_Underscore - 53)) | (1L << (GRW_As - 53)) | (1L << (GRW_Binary - 53)) | (1L << (GRW_Decimal - 53)) | (1L << (GRW_Exists - 53)) | (1L << (GRW_Hexadecimal - 53)) | (1L << (GRW_Forall - 53)) | (1L << (GRW_Let - 53)) | (1L << (GRW_Match - 53)) | (1L << (GRW_Numeral - 53)) | (1L << (GRW_Par - 53)) | (1L << (GRW_String - 53)) | (1L << (RS_Model - 53)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SimpleSymbolContext extends ParserRuleContext {
		public PredefSymbolContext predefSymbol() {
			return getRuleContext(PredefSymbolContext.class,0);
		}
		public TerminalNode UndefinedSymbol() { return getToken(SMTLIBv2Parser.UndefinedSymbol, 0); }
		public SimpleSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleSymbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSimpleSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSimpleSymbol(this);
		}
	}

	public final SimpleSymbolContext simpleSymbol() throws RecognitionException {
		SimpleSymbolContext _localctx = new SimpleSymbolContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_simpleSymbol);
		try {
			setState(208);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PS_Not:
			case PS_Bool:
			case PS_ContinuedExecution:
			case PS_Error:
			case PS_False:
			case PS_ImmediateExit:
			case PS_Incomplete:
			case PS_Logic:
			case PS_Memout:
			case PS_Sat:
			case PS_Success:
			case PS_Theory:
			case PS_True:
			case PS_Unknown:
			case PS_Unsupported:
			case PS_Unsat:
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				predefSymbol();
				}
				break;
			case UndefinedSymbol:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				match(UndefinedSymbol);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuotedSymbolContext extends ParserRuleContext {
		public TerminalNode QuotedSymbol() { return getToken(SMTLIBv2Parser.QuotedSymbol, 0); }
		public QuotedSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quotedSymbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterQuotedSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitQuotedSymbol(this);
		}
	}

	public final QuotedSymbolContext quotedSymbol() throws RecognitionException {
		QuotedSymbolContext _localctx = new QuotedSymbolContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_quotedSymbol);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(QuotedSymbol);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredefSymbolContext extends ParserRuleContext {
		public TerminalNode PS_Not() { return getToken(SMTLIBv2Parser.PS_Not, 0); }
		public TerminalNode PS_Bool() { return getToken(SMTLIBv2Parser.PS_Bool, 0); }
		public TerminalNode PS_ContinuedExecution() { return getToken(SMTLIBv2Parser.PS_ContinuedExecution, 0); }
		public TerminalNode PS_Error() { return getToken(SMTLIBv2Parser.PS_Error, 0); }
		public TerminalNode PS_False() { return getToken(SMTLIBv2Parser.PS_False, 0); }
		public TerminalNode PS_ImmediateExit() { return getToken(SMTLIBv2Parser.PS_ImmediateExit, 0); }
		public TerminalNode PS_Incomplete() { return getToken(SMTLIBv2Parser.PS_Incomplete, 0); }
		public TerminalNode PS_Logic() { return getToken(SMTLIBv2Parser.PS_Logic, 0); }
		public TerminalNode PS_Memout() { return getToken(SMTLIBv2Parser.PS_Memout, 0); }
		public TerminalNode PS_Sat() { return getToken(SMTLIBv2Parser.PS_Sat, 0); }
		public TerminalNode PS_Success() { return getToken(SMTLIBv2Parser.PS_Success, 0); }
		public TerminalNode PS_Theory() { return getToken(SMTLIBv2Parser.PS_Theory, 0); }
		public TerminalNode PS_True() { return getToken(SMTLIBv2Parser.PS_True, 0); }
		public TerminalNode PS_Unknown() { return getToken(SMTLIBv2Parser.PS_Unknown, 0); }
		public TerminalNode PS_Unsupported() { return getToken(SMTLIBv2Parser.PS_Unsupported, 0); }
		public TerminalNode PS_Unsat() { return getToken(SMTLIBv2Parser.PS_Unsat, 0); }
		public PredefSymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predefSymbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterPredefSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitPredefSymbol(this);
		}
	}

	public final PredefSymbolContext predefSymbol() throws RecognitionException {
		PredefSymbolContext _localctx = new PredefSymbolContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_predefSymbol);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredefKeywordContext extends ParserRuleContext {
		public TerminalNode PK_AllStatistics() { return getToken(SMTLIBv2Parser.PK_AllStatistics, 0); }
		public TerminalNode PK_AssertionStackLevels() { return getToken(SMTLIBv2Parser.PK_AssertionStackLevels, 0); }
		public TerminalNode PK_Authors() { return getToken(SMTLIBv2Parser.PK_Authors, 0); }
		public TerminalNode PK_Category() { return getToken(SMTLIBv2Parser.PK_Category, 0); }
		public TerminalNode PK_Chainable() { return getToken(SMTLIBv2Parser.PK_Chainable, 0); }
		public TerminalNode PK_Definition() { return getToken(SMTLIBv2Parser.PK_Definition, 0); }
		public TerminalNode PK_DiagnosticOutputChannel() { return getToken(SMTLIBv2Parser.PK_DiagnosticOutputChannel, 0); }
		public TerminalNode PK_ErrorBehaviour() { return getToken(SMTLIBv2Parser.PK_ErrorBehaviour, 0); }
		public TerminalNode PK_Extension() { return getToken(SMTLIBv2Parser.PK_Extension, 0); }
		public TerminalNode PK_Funs() { return getToken(SMTLIBv2Parser.PK_Funs, 0); }
		public TerminalNode PK_FunsDescription() { return getToken(SMTLIBv2Parser.PK_FunsDescription, 0); }
		public TerminalNode PK_GlobalDeclarations() { return getToken(SMTLIBv2Parser.PK_GlobalDeclarations, 0); }
		public TerminalNode PK_InteractiveMode() { return getToken(SMTLIBv2Parser.PK_InteractiveMode, 0); }
		public TerminalNode PK_Language() { return getToken(SMTLIBv2Parser.PK_Language, 0); }
		public TerminalNode PK_LeftAssoc() { return getToken(SMTLIBv2Parser.PK_LeftAssoc, 0); }
		public TerminalNode PK_License() { return getToken(SMTLIBv2Parser.PK_License, 0); }
		public TerminalNode PK_Named() { return getToken(SMTLIBv2Parser.PK_Named, 0); }
		public TerminalNode PK_Name() { return getToken(SMTLIBv2Parser.PK_Name, 0); }
		public TerminalNode PK_Notes() { return getToken(SMTLIBv2Parser.PK_Notes, 0); }
		public TerminalNode PK_Pattern() { return getToken(SMTLIBv2Parser.PK_Pattern, 0); }
		public TerminalNode PK_PrintSuccess() { return getToken(SMTLIBv2Parser.PK_PrintSuccess, 0); }
		public TerminalNode PK_ProduceAssertions() { return getToken(SMTLIBv2Parser.PK_ProduceAssertions, 0); }
		public TerminalNode PK_ProduceAssignments() { return getToken(SMTLIBv2Parser.PK_ProduceAssignments, 0); }
		public TerminalNode PK_ProduceModels() { return getToken(SMTLIBv2Parser.PK_ProduceModels, 0); }
		public TerminalNode PK_ProduceProofs() { return getToken(SMTLIBv2Parser.PK_ProduceProofs, 0); }
		public TerminalNode PK_ProduceUnsatAssumptions() { return getToken(SMTLIBv2Parser.PK_ProduceUnsatAssumptions, 0); }
		public TerminalNode PK_ProduceUnsatCores() { return getToken(SMTLIBv2Parser.PK_ProduceUnsatCores, 0); }
		public TerminalNode PK_RandomSeed() { return getToken(SMTLIBv2Parser.PK_RandomSeed, 0); }
		public TerminalNode PK_ReasonUnknown() { return getToken(SMTLIBv2Parser.PK_ReasonUnknown, 0); }
		public TerminalNode PK_RegularOutputChannel() { return getToken(SMTLIBv2Parser.PK_RegularOutputChannel, 0); }
		public TerminalNode PK_ReproducibleResourceLimit() { return getToken(SMTLIBv2Parser.PK_ReproducibleResourceLimit, 0); }
		public TerminalNode PK_RightAssoc() { return getToken(SMTLIBv2Parser.PK_RightAssoc, 0); }
		public TerminalNode PK_SmtLibVersion() { return getToken(SMTLIBv2Parser.PK_SmtLibVersion, 0); }
		public TerminalNode PK_Sorts() { return getToken(SMTLIBv2Parser.PK_Sorts, 0); }
		public TerminalNode PK_SortsDescription() { return getToken(SMTLIBv2Parser.PK_SortsDescription, 0); }
		public TerminalNode PK_Source() { return getToken(SMTLIBv2Parser.PK_Source, 0); }
		public TerminalNode PK_Status() { return getToken(SMTLIBv2Parser.PK_Status, 0); }
		public TerminalNode PK_Theories() { return getToken(SMTLIBv2Parser.PK_Theories, 0); }
		public TerminalNode PK_Values() { return getToken(SMTLIBv2Parser.PK_Values, 0); }
		public TerminalNode PK_Verbosity() { return getToken(SMTLIBv2Parser.PK_Verbosity, 0); }
		public TerminalNode PK_Version() { return getToken(SMTLIBv2Parser.PK_Version, 0); }
		public PredefKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predefKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterPredefKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitPredefKeyword(this);
		}
	}

	public final PredefKeywordContext predefKeyword() throws RecognitionException {
		PredefKeywordContext _localctx = new PredefKeywordContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_predefKeyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			_la = _input.LA(1);
			if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (PK_AllStatistics - 71)) | (1L << (PK_AssertionStackLevels - 71)) | (1L << (PK_Authors - 71)) | (1L << (PK_Category - 71)) | (1L << (PK_Chainable - 71)) | (1L << (PK_Definition - 71)) | (1L << (PK_DiagnosticOutputChannel - 71)) | (1L << (PK_ErrorBehaviour - 71)) | (1L << (PK_Extension - 71)) | (1L << (PK_Funs - 71)) | (1L << (PK_FunsDescription - 71)) | (1L << (PK_GlobalDeclarations - 71)) | (1L << (PK_InteractiveMode - 71)) | (1L << (PK_Language - 71)) | (1L << (PK_LeftAssoc - 71)) | (1L << (PK_License - 71)) | (1L << (PK_Named - 71)) | (1L << (PK_Name - 71)) | (1L << (PK_Notes - 71)) | (1L << (PK_Pattern - 71)) | (1L << (PK_PrintSuccess - 71)) | (1L << (PK_ProduceAssertions - 71)) | (1L << (PK_ProduceAssignments - 71)) | (1L << (PK_ProduceModels - 71)) | (1L << (PK_ProduceProofs - 71)) | (1L << (PK_ProduceUnsatAssumptions - 71)) | (1L << (PK_ProduceUnsatCores - 71)) | (1L << (PK_RandomSeed - 71)) | (1L << (PK_ReasonUnknown - 71)) | (1L << (PK_RegularOutputChannel - 71)) | (1L << (PK_ReproducibleResourceLimit - 71)) | (1L << (PK_RightAssoc - 71)) | (1L << (PK_SmtLibVersion - 71)) | (1L << (PK_Sorts - 71)) | (1L << (PK_SortsDescription - 71)) | (1L << (PK_Source - 71)) | (1L << (PK_Status - 71)) | (1L << (PK_Theories - 71)) | (1L << (PK_Values - 71)) | (1L << (PK_Verbosity - 71)) | (1L << (PK_Version - 71)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolContext extends ParserRuleContext {
		public SimpleSymbolContext simpleSymbol() {
			return getRuleContext(SimpleSymbolContext.class,0);
		}
		public QuotedSymbolContext quotedSymbol() {
			return getRuleContext(QuotedSymbolContext.class,0);
		}
		public SymbolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSymbol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSymbol(this);
		}
	}

	public final SymbolContext symbol() throws RecognitionException {
		SymbolContext _localctx = new SymbolContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_symbol);
		try {
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PS_Not:
			case PS_Bool:
			case PS_ContinuedExecution:
			case PS_Error:
			case PS_False:
			case PS_ImmediateExit:
			case PS_Incomplete:
			case PS_Logic:
			case PS_Memout:
			case PS_Sat:
			case PS_Success:
			case PS_Theory:
			case PS_True:
			case PS_Unknown:
			case PS_Unsupported:
			case PS_Unsat:
			case UndefinedSymbol:
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				simpleSymbol();
				}
				break;
			case QuotedSymbol:
				enterOuterAlt(_localctx, 2);
				{
				setState(217);
				quotedSymbol();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumeralContext extends ParserRuleContext {
		public TerminalNode Numeral() { return getToken(SMTLIBv2Parser.Numeral, 0); }
		public NumeralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterNumeral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitNumeral(this);
		}
	}

	public final NumeralContext numeral() throws RecognitionException {
		NumeralContext _localctx = new NumeralContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numeral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(Numeral);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecimalContext extends ParserRuleContext {
		public TerminalNode Decimal() { return getToken(SMTLIBv2Parser.Decimal, 0); }
		public DecimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitDecimal(this);
		}
	}

	public final DecimalContext decimal() throws RecognitionException {
		DecimalContext _localctx = new DecimalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_decimal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			match(Decimal);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HexadecimalContext extends ParserRuleContext {
		public TerminalNode HexDecimal() { return getToken(SMTLIBv2Parser.HexDecimal, 0); }
		public HexadecimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexadecimal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterHexadecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitHexadecimal(this);
		}
	}

	public final HexadecimalContext hexadecimal() throws RecognitionException {
		HexadecimalContext _localctx = new HexadecimalContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_hexadecimal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(HexDecimal);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryContext extends ParserRuleContext {
		public TerminalNode Binary() { return getToken(SMTLIBv2Parser.Binary, 0); }
		public BinaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitBinary(this);
		}
	}

	public final BinaryContext binary() throws RecognitionException {
		BinaryContext _localctx = new BinaryContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_binary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(Binary);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public TerminalNode String() { return getToken(SMTLIBv2Parser.String, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(String);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public PredefKeywordContext predefKeyword() {
			return getRuleContext(PredefKeywordContext.class,0);
		}
		public TerminalNode Colon() { return getToken(SMTLIBv2Parser.Colon, 0); }
		public SimpleSymbolContext simpleSymbol() {
			return getRuleContext(SimpleSymbolContext.class,0);
		}
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitKeyword(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_keyword);
		try {
			setState(233);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PK_AllStatistics:
			case PK_AssertionStackLevels:
			case PK_Authors:
			case PK_Category:
			case PK_Chainable:
			case PK_Definition:
			case PK_DiagnosticOutputChannel:
			case PK_ErrorBehaviour:
			case PK_Extension:
			case PK_Funs:
			case PK_FunsDescription:
			case PK_GlobalDeclarations:
			case PK_InteractiveMode:
			case PK_Language:
			case PK_LeftAssoc:
			case PK_License:
			case PK_Named:
			case PK_Name:
			case PK_Notes:
			case PK_Pattern:
			case PK_PrintSuccess:
			case PK_ProduceAssertions:
			case PK_ProduceAssignments:
			case PK_ProduceModels:
			case PK_ProduceProofs:
			case PK_ProduceUnsatAssumptions:
			case PK_ProduceUnsatCores:
			case PK_RandomSeed:
			case PK_ReasonUnknown:
			case PK_RegularOutputChannel:
			case PK_ReproducibleResourceLimit:
			case PK_RightAssoc:
			case PK_SmtLibVersion:
			case PK_Sorts:
			case PK_SortsDescription:
			case PK_Source:
			case PK_Status:
			case PK_Theories:
			case PK_Values:
			case PK_Verbosity:
			case PK_Version:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				predefKeyword();
				}
				break;
			case Colon:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				match(Colon);
				setState(232);
				simpleSymbol();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Spec_constantContext extends ParserRuleContext {
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public DecimalContext decimal() {
			return getRuleContext(DecimalContext.class,0);
		}
		public HexadecimalContext hexadecimal() {
			return getRuleContext(HexadecimalContext.class,0);
		}
		public BinaryContext binary() {
			return getRuleContext(BinaryContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public Spec_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spec_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSpec_constant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSpec_constant(this);
		}
	}

	public final Spec_constantContext spec_constant() throws RecognitionException {
		Spec_constantContext _localctx = new Spec_constantContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_spec_constant);
		try {
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Numeral:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				numeral();
				}
				break;
			case Decimal:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				decimal();
				}
				break;
			case HexDecimal:
				enterOuterAlt(_localctx, 3);
				{
				setState(237);
				hexadecimal();
				}
				break;
			case Binary:
				enterOuterAlt(_localctx, 4);
				{
				setState(238);
				binary();
				}
				break;
			case String:
				enterOuterAlt(_localctx, 5);
				{
				setState(239);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class S_exprContext extends ParserRuleContext {
		public Spec_constantContext spec_constant() {
			return getRuleContext(Spec_constantContext.class,0);
		}
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<S_exprContext> s_expr() {
			return getRuleContexts(S_exprContext.class);
		}
		public S_exprContext s_expr(int i) {
			return getRuleContext(S_exprContext.class,i);
		}
		public S_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterS_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitS_expr(this);
		}
	}

	public final S_exprContext s_expr() throws RecognitionException {
		S_exprContext _localctx = new S_exprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_s_expr);
		int _la;
		try {
			setState(253);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
			case Numeral:
			case Binary:
			case HexDecimal:
			case Decimal:
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				spec_constant();
				}
				break;
			case QuotedSymbol:
			case PS_Not:
			case PS_Bool:
			case PS_ContinuedExecution:
			case PS_Error:
			case PS_False:
			case PS_ImmediateExit:
			case PS_Incomplete:
			case PS_Logic:
			case PS_Memout:
			case PS_Sat:
			case PS_Success:
			case PS_Theory:
			case PS_True:
			case PS_Unknown:
			case PS_Unsupported:
			case PS_Unsat:
			case UndefinedSymbol:
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				symbol();
				}
				break;
			case Colon:
			case PK_AllStatistics:
			case PK_AssertionStackLevels:
			case PK_Authors:
			case PK_Category:
			case PK_Chainable:
			case PK_Definition:
			case PK_DiagnosticOutputChannel:
			case PK_ErrorBehaviour:
			case PK_Extension:
			case PK_Funs:
			case PK_FunsDescription:
			case PK_GlobalDeclarations:
			case PK_InteractiveMode:
			case PK_Language:
			case PK_LeftAssoc:
			case PK_License:
			case PK_Named:
			case PK_Name:
			case PK_Notes:
			case PK_Pattern:
			case PK_PrintSuccess:
			case PK_ProduceAssertions:
			case PK_ProduceAssignments:
			case PK_ProduceModels:
			case PK_ProduceProofs:
			case PK_ProduceUnsatAssumptions:
			case PK_ProduceUnsatCores:
			case PK_RandomSeed:
			case PK_ReasonUnknown:
			case PK_RegularOutputChannel:
			case PK_ReproducibleResourceLimit:
			case PK_RightAssoc:
			case PK_SmtLibVersion:
			case PK_Sorts:
			case PK_SortsDescription:
			case PK_Source:
			case PK_Status:
			case PK_Theories:
			case PK_Values:
			case PK_Verbosity:
			case PK_Version:
				enterOuterAlt(_localctx, 3);
				{
				setState(244);
				keyword();
				}
				break;
			case ParOpen:
				enterOuterAlt(_localctx, 4);
				{
				setState(245);
				match(ParOpen);
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << String) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (Numeral - 66)) | (1L << (Binary - 66)) | (1L << (HexDecimal - 66)) | (1L << (Decimal - 66)) | (1L << (Colon - 66)) | (1L << (PK_AllStatistics - 66)) | (1L << (PK_AssertionStackLevels - 66)) | (1L << (PK_Authors - 66)) | (1L << (PK_Category - 66)) | (1L << (PK_Chainable - 66)) | (1L << (PK_Definition - 66)) | (1L << (PK_DiagnosticOutputChannel - 66)) | (1L << (PK_ErrorBehaviour - 66)) | (1L << (PK_Extension - 66)) | (1L << (PK_Funs - 66)) | (1L << (PK_FunsDescription - 66)) | (1L << (PK_GlobalDeclarations - 66)) | (1L << (PK_InteractiveMode - 66)) | (1L << (PK_Language - 66)) | (1L << (PK_LeftAssoc - 66)) | (1L << (PK_License - 66)) | (1L << (PK_Named - 66)) | (1L << (PK_Name - 66)) | (1L << (PK_Notes - 66)) | (1L << (PK_Pattern - 66)) | (1L << (PK_PrintSuccess - 66)) | (1L << (PK_ProduceAssertions - 66)) | (1L << (PK_ProduceAssignments - 66)) | (1L << (PK_ProduceModels - 66)) | (1L << (PK_ProduceProofs - 66)) | (1L << (PK_ProduceUnsatAssumptions - 66)) | (1L << (PK_ProduceUnsatCores - 66)) | (1L << (PK_RandomSeed - 66)) | (1L << (PK_ReasonUnknown - 66)) | (1L << (PK_RegularOutputChannel - 66)) | (1L << (PK_ReproducibleResourceLimit - 66)) | (1L << (PK_RightAssoc - 66)) | (1L << (PK_SmtLibVersion - 66)) | (1L << (PK_Sorts - 66)) | (1L << (PK_SortsDescription - 66)) | (1L << (PK_Source - 66)) | (1L << (PK_Status - 66)) | (1L << (PK_Theories - 66)) | (1L << (PK_Values - 66)) | (1L << (PK_Verbosity - 66)) | (1L << (PK_Version - 66)) | (1L << (UndefinedSymbol - 66)))) != 0)) {
					{
					{
					setState(246);
					s_expr();
					}
					}
					setState(251);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(252);
				match(ParClose);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndexContext extends ParserRuleContext {
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitIndex(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_index);
		try {
			setState(257);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Numeral:
				enterOuterAlt(_localctx, 1);
				{
				setState(255);
				numeral();
				}
				break;
			case QuotedSymbol:
			case PS_Not:
			case PS_Bool:
			case PS_ContinuedExecution:
			case PS_Error:
			case PS_False:
			case PS_ImmediateExit:
			case PS_Incomplete:
			case PS_Logic:
			case PS_Memout:
			case PS_Sat:
			case PS_Success:
			case PS_Theory:
			case PS_True:
			case PS_Unknown:
			case PS_Unsupported:
			case PS_Unsat:
			case UndefinedSymbol:
				enterOuterAlt(_localctx, 2);
				{
				setState(256);
				symbol();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode GRW_Underscore() { return getToken(SMTLIBv2Parser.GRW_Underscore, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_identifier);
		int _la;
		try {
			setState(270);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QuotedSymbol:
			case PS_Not:
			case PS_Bool:
			case PS_ContinuedExecution:
			case PS_Error:
			case PS_False:
			case PS_ImmediateExit:
			case PS_Incomplete:
			case PS_Logic:
			case PS_Memout:
			case PS_Sat:
			case PS_Success:
			case PS_Theory:
			case PS_True:
			case PS_Unknown:
			case PS_Unsupported:
			case PS_Unsat:
			case UndefinedSymbol:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				symbol();
				}
				break;
			case ParOpen:
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				match(ParOpen);
				setState(261);
				match(GRW_Underscore);
				setState(262);
				symbol();
				setState(264); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(263);
					index();
					}
					}
					setState(266); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==Numeral || _la==UndefinedSymbol );
				setState(268);
				match(ParClose);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attribute_valueContext extends ParserRuleContext {
		public Spec_constantContext spec_constant() {
			return getRuleContext(Spec_constantContext.class,0);
		}
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<S_exprContext> s_expr() {
			return getRuleContexts(S_exprContext.class);
		}
		public S_exprContext s_expr(int i) {
			return getRuleContext(S_exprContext.class,i);
		}
		public Attribute_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterAttribute_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitAttribute_value(this);
		}
	}

	public final Attribute_valueContext attribute_value() throws RecognitionException {
		Attribute_valueContext _localctx = new Attribute_valueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_attribute_value);
		int _la;
		try {
			setState(282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case String:
			case Numeral:
			case Binary:
			case HexDecimal:
			case Decimal:
				enterOuterAlt(_localctx, 1);
				{
				setState(272);
				spec_constant();
				}
				break;
			case QuotedSymbol:
			case PS_Not:
			case PS_Bool:
			case PS_ContinuedExecution:
			case PS_Error:
			case PS_False:
			case PS_ImmediateExit:
			case PS_Incomplete:
			case PS_Logic:
			case PS_Memout:
			case PS_Sat:
			case PS_Success:
			case PS_Theory:
			case PS_True:
			case PS_Unknown:
			case PS_Unsupported:
			case PS_Unsat:
			case UndefinedSymbol:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				symbol();
				}
				break;
			case ParOpen:
				enterOuterAlt(_localctx, 3);
				{
				setState(274);
				match(ParOpen);
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << String) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (Numeral - 66)) | (1L << (Binary - 66)) | (1L << (HexDecimal - 66)) | (1L << (Decimal - 66)) | (1L << (Colon - 66)) | (1L << (PK_AllStatistics - 66)) | (1L << (PK_AssertionStackLevels - 66)) | (1L << (PK_Authors - 66)) | (1L << (PK_Category - 66)) | (1L << (PK_Chainable - 66)) | (1L << (PK_Definition - 66)) | (1L << (PK_DiagnosticOutputChannel - 66)) | (1L << (PK_ErrorBehaviour - 66)) | (1L << (PK_Extension - 66)) | (1L << (PK_Funs - 66)) | (1L << (PK_FunsDescription - 66)) | (1L << (PK_GlobalDeclarations - 66)) | (1L << (PK_InteractiveMode - 66)) | (1L << (PK_Language - 66)) | (1L << (PK_LeftAssoc - 66)) | (1L << (PK_License - 66)) | (1L << (PK_Named - 66)) | (1L << (PK_Name - 66)) | (1L << (PK_Notes - 66)) | (1L << (PK_Pattern - 66)) | (1L << (PK_PrintSuccess - 66)) | (1L << (PK_ProduceAssertions - 66)) | (1L << (PK_ProduceAssignments - 66)) | (1L << (PK_ProduceModels - 66)) | (1L << (PK_ProduceProofs - 66)) | (1L << (PK_ProduceUnsatAssumptions - 66)) | (1L << (PK_ProduceUnsatCores - 66)) | (1L << (PK_RandomSeed - 66)) | (1L << (PK_ReasonUnknown - 66)) | (1L << (PK_RegularOutputChannel - 66)) | (1L << (PK_ReproducibleResourceLimit - 66)) | (1L << (PK_RightAssoc - 66)) | (1L << (PK_SmtLibVersion - 66)) | (1L << (PK_Sorts - 66)) | (1L << (PK_SortsDescription - 66)) | (1L << (PK_Source - 66)) | (1L << (PK_Status - 66)) | (1L << (PK_Theories - 66)) | (1L << (PK_Values - 66)) | (1L << (PK_Verbosity - 66)) | (1L << (PK_Version - 66)) | (1L << (UndefinedSymbol - 66)))) != 0)) {
					{
					{
					setState(275);
					s_expr();
					}
					}
					setState(280);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(281);
				match(ParClose);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public Attribute_valueContext attribute_value() {
			return getRuleContext(Attribute_valueContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_attribute);
		try {
			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(284);
				keyword();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				keyword();
				setState(286);
				attribute_value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<SortContext> sort() {
			return getRuleContexts(SortContext.class);
		}
		public SortContext sort(int i) {
			return getRuleContext(SortContext.class,i);
		}
		public SortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sort; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSort(this);
		}
	}

	public final SortContext sort() throws RecognitionException {
		SortContext _localctx = new SortContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_sort);
		int _la;
		try {
			setState(300);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(291);
				match(ParOpen);
				setState(292);
				identifier();
				setState(294); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(293);
					sort();
					}
					}
					setState(296); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol );
				setState(298);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Qual_identiferContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode GRW_As() { return getToken(SMTLIBv2Parser.GRW_As, 0); }
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Qual_identiferContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qual_identifer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterQual_identifer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitQual_identifer(this);
		}
	}

	public final Qual_identiferContext qual_identifer() throws RecognitionException {
		Qual_identiferContext _localctx = new Qual_identiferContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_qual_identifer);
		try {
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(302);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				match(ParOpen);
				setState(304);
				match(GRW_As);
				setState(305);
				identifier();
				setState(306);
				sort();
				setState(307);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_bindingContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Var_bindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_binding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterVar_binding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitVar_binding(this);
		}
	}

	public final Var_bindingContext var_binding() throws RecognitionException {
		Var_bindingContext _localctx = new Var_bindingContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_var_binding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(ParOpen);
			setState(312);
			symbol();
			setState(313);
			term();
			setState(314);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sorted_varContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Sorted_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sorted_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSorted_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSorted_var(this);
		}
	}

	public final Sorted_varContext sorted_var() throws RecognitionException {
		Sorted_varContext _localctx = new Sorted_varContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_sorted_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(ParOpen);
			setState(317);
			symbol();
			setState(318);
			sort();
			setState(319);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternContext extends ParserRuleContext {
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitPattern(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_pattern);
		int _la;
		try {
			setState(331);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QuotedSymbol:
			case PS_Not:
			case PS_Bool:
			case PS_ContinuedExecution:
			case PS_Error:
			case PS_False:
			case PS_ImmediateExit:
			case PS_Incomplete:
			case PS_Logic:
			case PS_Memout:
			case PS_Sat:
			case PS_Success:
			case PS_Theory:
			case PS_True:
			case PS_Unknown:
			case PS_Unsupported:
			case PS_Unsat:
			case UndefinedSymbol:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				symbol();
				}
				break;
			case ParOpen:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				match(ParOpen);
				setState(323);
				symbol();
				setState(325); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(324);
					symbol();
					}
					}
					setState(327); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol );
				setState(329);
				match(ParClose);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Match_caseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Match_caseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match_case; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterMatch_case(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitMatch_case(this);
		}
	}

	public final Match_caseContext match_case() throws RecognitionException {
		Match_caseContext _localctx = new Match_caseContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_match_case);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(ParOpen);
			setState(334);
			pattern();
			setState(335);
			term();
			setState(336);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Spec_constantContext spec_constant() {
			return getRuleContext(Spec_constantContext.class,0);
		}
		public Qual_identiferContext qual_identifer() {
			return getRuleContext(Qual_identiferContext.class,0);
		}
		public List<TerminalNode> ParOpen() { return getTokens(SMTLIBv2Parser.ParOpen); }
		public TerminalNode ParOpen(int i) {
			return getToken(SMTLIBv2Parser.ParOpen, i);
		}
		public List<TerminalNode> ParClose() { return getTokens(SMTLIBv2Parser.ParClose); }
		public TerminalNode ParClose(int i) {
			return getToken(SMTLIBv2Parser.ParClose, i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode GRW_Let() { return getToken(SMTLIBv2Parser.GRW_Let, 0); }
		public List<Var_bindingContext> var_binding() {
			return getRuleContexts(Var_bindingContext.class);
		}
		public Var_bindingContext var_binding(int i) {
			return getRuleContext(Var_bindingContext.class,i);
		}
		public TerminalNode GRW_Forall() { return getToken(SMTLIBv2Parser.GRW_Forall, 0); }
		public List<Sorted_varContext> sorted_var() {
			return getRuleContexts(Sorted_varContext.class);
		}
		public Sorted_varContext sorted_var(int i) {
			return getRuleContext(Sorted_varContext.class,i);
		}
		public TerminalNode GRW_Exists() { return getToken(SMTLIBv2Parser.GRW_Exists, 0); }
		public TerminalNode GRW_Match() { return getToken(SMTLIBv2Parser.GRW_Match, 0); }
		public List<Match_caseContext> match_case() {
			return getRuleContexts(Match_caseContext.class);
		}
		public Match_caseContext match_case(int i) {
			return getRuleContext(Match_caseContext.class,i);
		}
		public TerminalNode GRW_Exclamation() { return getToken(SMTLIBv2Parser.GRW_Exclamation, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_term);
		int _la;
		try {
			setState(407);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(338);
				spec_constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				qual_identifer();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(340);
				match(ParOpen);
				setState(341);
				qual_identifer();
				setState(343); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(342);
					term();
					}
					}
					setState(345); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << String) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (Numeral - 66)) | (1L << (Binary - 66)) | (1L << (HexDecimal - 66)) | (1L << (Decimal - 66)) | (1L << (UndefinedSymbol - 66)))) != 0) );
				setState(347);
				match(ParClose);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(349);
				match(ParOpen);
				setState(350);
				match(GRW_Let);
				setState(351);
				match(ParOpen);
				setState(353); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(352);
					var_binding();
					}
					}
					setState(355); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ParOpen );
				setState(357);
				match(ParClose);
				setState(358);
				term();
				setState(359);
				match(ParClose);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(361);
				match(ParOpen);
				setState(362);
				match(GRW_Forall);
				setState(363);
				match(ParOpen);
				setState(365); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(364);
					sorted_var();
					}
					}
					setState(367); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ParOpen );
				setState(369);
				match(ParClose);
				setState(370);
				term();
				setState(371);
				match(ParClose);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(373);
				match(ParOpen);
				setState(374);
				match(GRW_Exists);
				setState(375);
				match(ParOpen);
				setState(377); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(376);
					sorted_var();
					}
					}
					setState(379); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ParOpen );
				setState(381);
				match(ParClose);
				setState(382);
				term();
				setState(383);
				match(ParClose);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(385);
				match(ParOpen);
				setState(386);
				match(GRW_Match);
				setState(387);
				term();
				setState(388);
				match(ParOpen);
				setState(390); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(389);
					match_case();
					}
					}
					setState(392); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ParOpen );
				setState(394);
				match(ParClose);
				setState(395);
				match(ParClose);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(397);
				match(ParOpen);
				setState(398);
				match(GRW_Exclamation);
				setState(399);
				term();
				setState(401); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(400);
					attribute();
					}
					}
					setState(403); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0) );
				setState(405);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sort_symbol_declContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public Sort_symbol_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sort_symbol_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSort_symbol_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSort_symbol_decl(this);
		}
	}

	public final Sort_symbol_declContext sort_symbol_decl() throws RecognitionException {
		Sort_symbol_declContext _localctx = new Sort_symbol_declContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_sort_symbol_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			match(ParOpen);
			setState(410);
			identifier();
			setState(411);
			numeral();
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0)) {
				{
				{
				setState(412);
				attribute();
				}
				}
				setState(417);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(418);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Meta_spec_constantContext extends ParserRuleContext {
		public TerminalNode GRW_Numeral() { return getToken(SMTLIBv2Parser.GRW_Numeral, 0); }
		public TerminalNode GRW_Decimal() { return getToken(SMTLIBv2Parser.GRW_Decimal, 0); }
		public TerminalNode GRW_String() { return getToken(SMTLIBv2Parser.GRW_String, 0); }
		public Meta_spec_constantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_meta_spec_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterMeta_spec_constant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitMeta_spec_constant(this);
		}
	}

	public final Meta_spec_constantContext meta_spec_constant() throws RecognitionException {
		Meta_spec_constantContext _localctx = new Meta_spec_constantContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_meta_spec_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			_la = _input.LA(1);
			if ( !(((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (GRW_Decimal - 57)) | (1L << (GRW_Numeral - 57)) | (1L << (GRW_String - 57)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fun_symbol_declContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public Spec_constantContext spec_constant() {
			return getRuleContext(Spec_constantContext.class,0);
		}
		public List<SortContext> sort() {
			return getRuleContexts(SortContext.class);
		}
		public SortContext sort(int i) {
			return getRuleContext(SortContext.class,i);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public Meta_spec_constantContext meta_spec_constant() {
			return getRuleContext(Meta_spec_constantContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Fun_symbol_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun_symbol_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterFun_symbol_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitFun_symbol_decl(this);
		}
	}

	public final Fun_symbol_declContext fun_symbol_decl() throws RecognitionException {
		Fun_symbol_declContext _localctx = new Fun_symbol_declContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_fun_symbol_decl);
		int _la;
		try {
			setState(459);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(422);
				match(ParOpen);
				setState(423);
				spec_constant();
				setState(424);
				sort();
				setState(428);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0)) {
					{
					{
					setState(425);
					attribute();
					}
					}
					setState(430);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(431);
				match(ParClose);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(433);
				match(ParOpen);
				setState(434);
				meta_spec_constant();
				setState(435);
				sort();
				setState(439);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0)) {
					{
					{
					setState(436);
					attribute();
					}
					}
					setState(441);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(442);
				match(ParClose);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(444);
				match(ParOpen);
				setState(445);
				identifier();
				setState(447); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(446);
					sort();
					}
					}
					setState(449); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol );
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0)) {
					{
					{
					setState(451);
					attribute();
					}
					}
					setState(456);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(457);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Par_fun_symbol_declContext extends ParserRuleContext {
		public Fun_symbol_declContext fun_symbol_decl() {
			return getRuleContext(Fun_symbol_declContext.class,0);
		}
		public List<TerminalNode> ParOpen() { return getTokens(SMTLIBv2Parser.ParOpen); }
		public TerminalNode ParOpen(int i) {
			return getToken(SMTLIBv2Parser.ParOpen, i);
		}
		public TerminalNode GRW_Par() { return getToken(SMTLIBv2Parser.GRW_Par, 0); }
		public List<TerminalNode> ParClose() { return getTokens(SMTLIBv2Parser.ParClose); }
		public TerminalNode ParClose(int i) {
			return getToken(SMTLIBv2Parser.ParClose, i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public List<SortContext> sort() {
			return getRuleContexts(SortContext.class);
		}
		public SortContext sort(int i) {
			return getRuleContext(SortContext.class,i);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public Par_fun_symbol_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_par_fun_symbol_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterPar_fun_symbol_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitPar_fun_symbol_decl(this);
		}
	}

	public final Par_fun_symbol_declContext par_fun_symbol_decl() throws RecognitionException {
		Par_fun_symbol_declContext _localctx = new Par_fun_symbol_declContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_par_fun_symbol_decl);
		int _la;
		try {
			setState(487);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(461);
				fun_symbol_decl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(462);
				match(ParOpen);
				setState(463);
				match(GRW_Par);
				setState(464);
				match(ParOpen);
				setState(466); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(465);
					symbol();
					}
					}
					setState(468); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol );
				setState(470);
				match(ParClose);
				setState(471);
				match(ParOpen);
				setState(472);
				identifier();
				setState(474); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(473);
					sort();
					}
					}
					setState(476); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol );
				setState(481);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0)) {
					{
					{
					setState(478);
					attribute();
					}
					}
					setState(483);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(484);
				match(ParClose);
				setState(485);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Theory_attributeContext extends ParserRuleContext {
		public TerminalNode PK_Sorts() { return getToken(SMTLIBv2Parser.PK_Sorts, 0); }
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<Sort_symbol_declContext> sort_symbol_decl() {
			return getRuleContexts(Sort_symbol_declContext.class);
		}
		public Sort_symbol_declContext sort_symbol_decl(int i) {
			return getRuleContext(Sort_symbol_declContext.class,i);
		}
		public TerminalNode PK_Funs() { return getToken(SMTLIBv2Parser.PK_Funs, 0); }
		public List<Par_fun_symbol_declContext> par_fun_symbol_decl() {
			return getRuleContexts(Par_fun_symbol_declContext.class);
		}
		public Par_fun_symbol_declContext par_fun_symbol_decl(int i) {
			return getRuleContext(Par_fun_symbol_declContext.class,i);
		}
		public TerminalNode PK_SortsDescription() { return getToken(SMTLIBv2Parser.PK_SortsDescription, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode PK_FunsDescription() { return getToken(SMTLIBv2Parser.PK_FunsDescription, 0); }
		public TerminalNode PK_Definition() { return getToken(SMTLIBv2Parser.PK_Definition, 0); }
		public TerminalNode PK_Values() { return getToken(SMTLIBv2Parser.PK_Values, 0); }
		public TerminalNode PK_Notes() { return getToken(SMTLIBv2Parser.PK_Notes, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public Theory_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_theory_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterTheory_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitTheory_attribute(this);
		}
	}

	public final Theory_attributeContext theory_attribute() throws RecognitionException {
		Theory_attributeContext _localctx = new Theory_attributeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_theory_attribute);
		int _la;
		try {
			setState(518);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(489);
				match(PK_Sorts);
				setState(490);
				match(ParOpen);
				setState(492); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(491);
					sort_symbol_decl();
					}
					}
					setState(494); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ParOpen );
				setState(496);
				match(ParClose);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(498);
				match(PK_Funs);
				setState(499);
				match(ParOpen);
				setState(501); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(500);
					par_fun_symbol_decl();
					}
					}
					setState(503); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ParOpen );
				setState(505);
				match(ParClose);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(507);
				match(PK_SortsDescription);
				setState(508);
				string();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(509);
				match(PK_FunsDescription);
				setState(510);
				string();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(511);
				match(PK_Definition);
				setState(512);
				string();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(513);
				match(PK_Values);
				setState(514);
				string();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(515);
				match(PK_Notes);
				setState(516);
				string();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(517);
				attribute();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Theory_declContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode PS_Theory() { return getToken(SMTLIBv2Parser.PS_Theory, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<Theory_attributeContext> theory_attribute() {
			return getRuleContexts(Theory_attributeContext.class);
		}
		public Theory_attributeContext theory_attribute(int i) {
			return getRuleContext(Theory_attributeContext.class,i);
		}
		public Theory_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_theory_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterTheory_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitTheory_decl(this);
		}
	}

	public final Theory_declContext theory_decl() throws RecognitionException {
		Theory_declContext _localctx = new Theory_declContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_theory_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
			match(ParOpen);
			setState(521);
			match(PS_Theory);
			setState(522);
			symbol();
			setState(524); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(523);
				theory_attribute();
				}
				}
				setState(526); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0) );
			setState(528);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_attribueContext extends ParserRuleContext {
		public TerminalNode PK_Theories() { return getToken(SMTLIBv2Parser.PK_Theories, 0); }
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public TerminalNode PK_Language() { return getToken(SMTLIBv2Parser.PK_Language, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode PK_Extension() { return getToken(SMTLIBv2Parser.PK_Extension, 0); }
		public TerminalNode PK_Values() { return getToken(SMTLIBv2Parser.PK_Values, 0); }
		public TerminalNode PK_Notes() { return getToken(SMTLIBv2Parser.PK_Notes, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public Logic_attribueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_attribue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterLogic_attribue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitLogic_attribue(this);
		}
	}

	public final Logic_attribueContext logic_attribue() throws RecognitionException {
		Logic_attribueContext _localctx = new Logic_attribueContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_logic_attribue);
		int _la;
		try {
			setState(548);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(530);
				match(PK_Theories);
				setState(531);
				match(ParOpen);
				setState(533); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(532);
					symbol();
					}
					}
					setState(535); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol );
				setState(537);
				match(ParClose);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(539);
				match(PK_Language);
				setState(540);
				string();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(541);
				match(PK_Extension);
				setState(542);
				string();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(543);
				match(PK_Values);
				setState(544);
				string();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(545);
				match(PK_Notes);
				setState(546);
				string();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(547);
				attribute();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode PS_Logic() { return getToken(SMTLIBv2Parser.PS_Logic, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<Logic_attribueContext> logic_attribue() {
			return getRuleContexts(Logic_attribueContext.class);
		}
		public Logic_attribueContext logic_attribue(int i) {
			return getRuleContext(Logic_attribueContext.class,i);
		}
		public LogicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitLogic(this);
		}
	}

	public final LogicContext logic() throws RecognitionException {
		LogicContext _localctx = new LogicContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_logic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			match(ParOpen);
			setState(551);
			match(PS_Logic);
			setState(552);
			symbol();
			setState(554); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(553);
				logic_attribue();
				}
				}
				setState(556); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0) );
			setState(558);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sort_decContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Sort_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sort_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSort_dec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSort_dec(this);
		}
	}

	public final Sort_decContext sort_dec() throws RecognitionException {
		Sort_decContext _localctx = new Sort_decContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_sort_dec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			match(ParOpen);
			setState(561);
			symbol();
			setState(562);
			numeral();
			setState(563);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Selector_decContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Selector_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSelector_dec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSelector_dec(this);
		}
	}

	public final Selector_decContext selector_dec() throws RecognitionException {
		Selector_decContext _localctx = new Selector_decContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_selector_dec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
			match(ParOpen);
			setState(566);
			symbol();
			setState(567);
			sort();
			setState(568);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constructor_decContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<Selector_decContext> selector_dec() {
			return getRuleContexts(Selector_decContext.class);
		}
		public Selector_decContext selector_dec(int i) {
			return getRuleContext(Selector_decContext.class,i);
		}
		public Constructor_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterConstructor_dec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitConstructor_dec(this);
		}
	}

	public final Constructor_decContext constructor_dec() throws RecognitionException {
		Constructor_decContext _localctx = new Constructor_decContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_constructor_dec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(570);
			match(ParOpen);
			setState(571);
			symbol();
			setState(575);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ParOpen) {
				{
				{
				setState(572);
				selector_dec();
				}
				}
				setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(578);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Datatype_decContext extends ParserRuleContext {
		public List<TerminalNode> ParOpen() { return getTokens(SMTLIBv2Parser.ParOpen); }
		public TerminalNode ParOpen(int i) {
			return getToken(SMTLIBv2Parser.ParOpen, i);
		}
		public List<TerminalNode> ParClose() { return getTokens(SMTLIBv2Parser.ParClose); }
		public TerminalNode ParClose(int i) {
			return getToken(SMTLIBv2Parser.ParClose, i);
		}
		public List<Constructor_decContext> constructor_dec() {
			return getRuleContexts(Constructor_decContext.class);
		}
		public Constructor_decContext constructor_dec(int i) {
			return getRuleContext(Constructor_decContext.class,i);
		}
		public TerminalNode GRW_Par() { return getToken(SMTLIBv2Parser.GRW_Par, 0); }
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public Datatype_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_datatype_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterDatatype_dec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitDatatype_dec(this);
		}
	}

	public final Datatype_decContext datatype_dec() throws RecognitionException {
		Datatype_decContext _localctx = new Datatype_decContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_datatype_dec);
		int _la;
		try {
			setState(606);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(580);
				match(ParOpen);
				setState(582); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(581);
					constructor_dec();
					}
					}
					setState(584); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ParOpen );
				setState(586);
				match(ParClose);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(588);
				match(ParOpen);
				setState(589);
				match(GRW_Par);
				setState(590);
				match(ParOpen);
				setState(592); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(591);
					symbol();
					}
					}
					setState(594); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol );
				setState(596);
				match(ParClose);
				setState(597);
				match(ParOpen);
				setState(599); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(598);
					constructor_dec();
					}
					}
					setState(601); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ParOpen );
				setState(603);
				match(ParClose);
				setState(604);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_decContext extends ParserRuleContext {
		public List<TerminalNode> ParOpen() { return getTokens(SMTLIBv2Parser.ParOpen); }
		public TerminalNode ParOpen(int i) {
			return getToken(SMTLIBv2Parser.ParOpen, i);
		}
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public List<TerminalNode> ParClose() { return getTokens(SMTLIBv2Parser.ParClose); }
		public TerminalNode ParClose(int i) {
			return getToken(SMTLIBv2Parser.ParClose, i);
		}
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public List<Sorted_varContext> sorted_var() {
			return getRuleContexts(Sorted_varContext.class);
		}
		public Sorted_varContext sorted_var(int i) {
			return getRuleContext(Sorted_varContext.class,i);
		}
		public Function_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterFunction_dec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitFunction_dec(this);
		}
	}

	public final Function_decContext function_dec() throws RecognitionException {
		Function_decContext _localctx = new Function_decContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_function_dec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(608);
			match(ParOpen);
			setState(609);
			symbol();
			setState(610);
			match(ParOpen);
			setState(614);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ParOpen) {
				{
				{
				setState(611);
				sorted_var();
				}
				}
				setState(616);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(617);
			match(ParClose);
			setState(618);
			sort();
			setState(619);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_defContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public List<Sorted_varContext> sorted_var() {
			return getRuleContexts(Sorted_varContext.class);
		}
		public Sorted_varContext sorted_var(int i) {
			return getRuleContext(Sorted_varContext.class,i);
		}
		public Function_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterFunction_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitFunction_def(this);
		}
	}

	public final Function_defContext function_def() throws RecognitionException {
		Function_defContext _localctx = new Function_defContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_function_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
			symbol();
			setState(622);
			match(ParOpen);
			setState(626);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ParOpen) {
				{
				{
				setState(623);
				sorted_var();
				}
				}
				setState(628);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(629);
			match(ParClose);
			setState(630);
			sort();
			setState(631);
			term();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Prop_literalContext extends ParserRuleContext {
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode PS_Not() { return getToken(SMTLIBv2Parser.PS_Not, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Prop_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prop_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterProp_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitProp_literal(this);
		}
	}

	public final Prop_literalContext prop_literal() throws RecognitionException {
		Prop_literalContext _localctx = new Prop_literalContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_prop_literal);
		try {
			setState(639);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QuotedSymbol:
			case PS_Not:
			case PS_Bool:
			case PS_ContinuedExecution:
			case PS_Error:
			case PS_False:
			case PS_ImmediateExit:
			case PS_Incomplete:
			case PS_Logic:
			case PS_Memout:
			case PS_Sat:
			case PS_Success:
			case PS_Theory:
			case PS_True:
			case PS_Unknown:
			case PS_Unsupported:
			case PS_Unsat:
			case UndefinedSymbol:
				enterOuterAlt(_localctx, 1);
				{
				setState(633);
				symbol();
				}
				break;
			case ParOpen:
				enterOuterAlt(_localctx, 2);
				{
				setState(634);
				match(ParOpen);
				setState(635);
				match(PS_Not);
				setState(636);
				symbol();
				setState(637);
				match(ParClose);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScriptContext extends ParserRuleContext {
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitScript(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(644);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ParOpen) {
				{
				{
				setState(641);
				command();
				}
				}
				setState(646);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_assertContext extends ParserRuleContext {
		public TerminalNode CMD_Assert() { return getToken(SMTLIBv2Parser.CMD_Assert, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Cmd_assertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_assert; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_assert(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_assert(this);
		}
	}

	public final Cmd_assertContext cmd_assert() throws RecognitionException {
		Cmd_assertContext _localctx = new Cmd_assertContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_cmd_assert);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(647);
			match(CMD_Assert);
			setState(648);
			term();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_checkSatContext extends ParserRuleContext {
		public TerminalNode CMD_CheckSat() { return getToken(SMTLIBv2Parser.CMD_CheckSat, 0); }
		public Cmd_checkSatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_checkSat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_checkSat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_checkSat(this);
		}
	}

	public final Cmd_checkSatContext cmd_checkSat() throws RecognitionException {
		Cmd_checkSatContext _localctx = new Cmd_checkSatContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_cmd_checkSat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(650);
			match(CMD_CheckSat);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_checkSatAssumingContext extends ParserRuleContext {
		public TerminalNode CMD_CheckSatAssuming() { return getToken(SMTLIBv2Parser.CMD_CheckSatAssuming, 0); }
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<Prop_literalContext> prop_literal() {
			return getRuleContexts(Prop_literalContext.class);
		}
		public Prop_literalContext prop_literal(int i) {
			return getRuleContext(Prop_literalContext.class,i);
		}
		public Cmd_checkSatAssumingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_checkSatAssuming; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_checkSatAssuming(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_checkSatAssuming(this);
		}
	}

	public final Cmd_checkSatAssumingContext cmd_checkSatAssuming() throws RecognitionException {
		Cmd_checkSatAssumingContext _localctx = new Cmd_checkSatAssumingContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_cmd_checkSatAssuming);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652);
			match(CMD_CheckSatAssuming);
			setState(653);
			match(ParOpen);
			setState(657);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol) {
				{
				{
				setState(654);
				prop_literal();
				}
				}
				setState(659);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(660);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_declareConstContext extends ParserRuleContext {
		public TerminalNode CMD_DeclareConst() { return getToken(SMTLIBv2Parser.CMD_DeclareConst, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public Cmd_declareConstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_declareConst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_declareConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_declareConst(this);
		}
	}

	public final Cmd_declareConstContext cmd_declareConst() throws RecognitionException {
		Cmd_declareConstContext _localctx = new Cmd_declareConstContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_cmd_declareConst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(662);
			match(CMD_DeclareConst);
			setState(663);
			symbol();
			setState(664);
			sort();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_declareDatatypeContext extends ParserRuleContext {
		public TerminalNode CMD_DeclareDatatype() { return getToken(SMTLIBv2Parser.CMD_DeclareDatatype, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public Datatype_decContext datatype_dec() {
			return getRuleContext(Datatype_decContext.class,0);
		}
		public Cmd_declareDatatypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_declareDatatype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_declareDatatype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_declareDatatype(this);
		}
	}

	public final Cmd_declareDatatypeContext cmd_declareDatatype() throws RecognitionException {
		Cmd_declareDatatypeContext _localctx = new Cmd_declareDatatypeContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_cmd_declareDatatype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			match(CMD_DeclareDatatype);
			setState(667);
			symbol();
			setState(668);
			datatype_dec();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_declareDatatypesContext extends ParserRuleContext {
		public TerminalNode CMD_DeclareDatatypes() { return getToken(SMTLIBv2Parser.CMD_DeclareDatatypes, 0); }
		public List<TerminalNode> ParOpen() { return getTokens(SMTLIBv2Parser.ParOpen); }
		public TerminalNode ParOpen(int i) {
			return getToken(SMTLIBv2Parser.ParOpen, i);
		}
		public List<TerminalNode> ParClose() { return getTokens(SMTLIBv2Parser.ParClose); }
		public TerminalNode ParClose(int i) {
			return getToken(SMTLIBv2Parser.ParClose, i);
		}
		public List<Sort_decContext> sort_dec() {
			return getRuleContexts(Sort_decContext.class);
		}
		public Sort_decContext sort_dec(int i) {
			return getRuleContext(Sort_decContext.class,i);
		}
		public List<Datatype_decContext> datatype_dec() {
			return getRuleContexts(Datatype_decContext.class);
		}
		public Datatype_decContext datatype_dec(int i) {
			return getRuleContext(Datatype_decContext.class,i);
		}
		public Cmd_declareDatatypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_declareDatatypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_declareDatatypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_declareDatatypes(this);
		}
	}

	public final Cmd_declareDatatypesContext cmd_declareDatatypes() throws RecognitionException {
		Cmd_declareDatatypesContext _localctx = new Cmd_declareDatatypesContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_cmd_declareDatatypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(670);
			match(CMD_DeclareDatatypes);
			setState(671);
			match(ParOpen);
			setState(673); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(672);
				sort_dec();
				}
				}
				setState(675); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ParOpen );
			setState(677);
			match(ParClose);
			setState(678);
			match(ParOpen);
			setState(680); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(679);
				datatype_dec();
				}
				}
				setState(682); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ParOpen );
			setState(684);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_declareFunContext extends ParserRuleContext {
		public TerminalNode CMD_DeclareFun() { return getToken(SMTLIBv2Parser.CMD_DeclareFun, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<SortContext> sort() {
			return getRuleContexts(SortContext.class);
		}
		public SortContext sort(int i) {
			return getRuleContext(SortContext.class,i);
		}
		public Cmd_declareFunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_declareFun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_declareFun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_declareFun(this);
		}
	}

	public final Cmd_declareFunContext cmd_declareFun() throws RecognitionException {
		Cmd_declareFunContext _localctx = new Cmd_declareFunContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_cmd_declareFun);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(686);
			match(CMD_DeclareFun);
			setState(687);
			symbol();
			setState(688);
			match(ParOpen);
			setState(692);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol) {
				{
				{
				setState(689);
				sort();
				}
				}
				setState(694);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(695);
			match(ParClose);
			setState(696);
			sort();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_declareSortContext extends ParserRuleContext {
		public TerminalNode CMD_DeclareSort() { return getToken(SMTLIBv2Parser.CMD_DeclareSort, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public Cmd_declareSortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_declareSort; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_declareSort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_declareSort(this);
		}
	}

	public final Cmd_declareSortContext cmd_declareSort() throws RecognitionException {
		Cmd_declareSortContext _localctx = new Cmd_declareSortContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_cmd_declareSort);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(698);
			match(CMD_DeclareSort);
			setState(699);
			symbol();
			setState(700);
			numeral();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_defineFunContext extends ParserRuleContext {
		public TerminalNode CMD_DefineFun() { return getToken(SMTLIBv2Parser.CMD_DefineFun, 0); }
		public Function_defContext function_def() {
			return getRuleContext(Function_defContext.class,0);
		}
		public Cmd_defineFunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_defineFun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_defineFun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_defineFun(this);
		}
	}

	public final Cmd_defineFunContext cmd_defineFun() throws RecognitionException {
		Cmd_defineFunContext _localctx = new Cmd_defineFunContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_cmd_defineFun);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(702);
			match(CMD_DefineFun);
			setState(703);
			function_def();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_defineFunRecContext extends ParserRuleContext {
		public TerminalNode CMD_DefineFunRec() { return getToken(SMTLIBv2Parser.CMD_DefineFunRec, 0); }
		public Function_defContext function_def() {
			return getRuleContext(Function_defContext.class,0);
		}
		public Cmd_defineFunRecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_defineFunRec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_defineFunRec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_defineFunRec(this);
		}
	}

	public final Cmd_defineFunRecContext cmd_defineFunRec() throws RecognitionException {
		Cmd_defineFunRecContext _localctx = new Cmd_defineFunRecContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_cmd_defineFunRec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(705);
			match(CMD_DefineFunRec);
			setState(706);
			function_def();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_defineFunsRecContext extends ParserRuleContext {
		public TerminalNode CMD_DefineFunsRec() { return getToken(SMTLIBv2Parser.CMD_DefineFunsRec, 0); }
		public List<TerminalNode> ParOpen() { return getTokens(SMTLIBv2Parser.ParOpen); }
		public TerminalNode ParOpen(int i) {
			return getToken(SMTLIBv2Parser.ParOpen, i);
		}
		public List<TerminalNode> ParClose() { return getTokens(SMTLIBv2Parser.ParClose); }
		public TerminalNode ParClose(int i) {
			return getToken(SMTLIBv2Parser.ParClose, i);
		}
		public List<Function_decContext> function_dec() {
			return getRuleContexts(Function_decContext.class);
		}
		public Function_decContext function_dec(int i) {
			return getRuleContext(Function_decContext.class,i);
		}
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Cmd_defineFunsRecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_defineFunsRec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_defineFunsRec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_defineFunsRec(this);
		}
	}

	public final Cmd_defineFunsRecContext cmd_defineFunsRec() throws RecognitionException {
		Cmd_defineFunsRecContext _localctx = new Cmd_defineFunsRecContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_cmd_defineFunsRec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(708);
			match(CMD_DefineFunsRec);
			setState(709);
			match(ParOpen);
			setState(711); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(710);
				function_dec();
				}
				}
				setState(713); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ParOpen );
			setState(715);
			match(ParClose);
			setState(716);
			match(ParOpen);
			setState(718); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(717);
				term();
				}
				}
				setState(720); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << String) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (Numeral - 66)) | (1L << (Binary - 66)) | (1L << (HexDecimal - 66)) | (1L << (Decimal - 66)) | (1L << (UndefinedSymbol - 66)))) != 0) );
			setState(722);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_defineSortContext extends ParserRuleContext {
		public TerminalNode CMD_DefineSort() { return getToken(SMTLIBv2Parser.CMD_DefineSort, 0); }
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public Cmd_defineSortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_defineSort; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_defineSort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_defineSort(this);
		}
	}

	public final Cmd_defineSortContext cmd_defineSort() throws RecognitionException {
		Cmd_defineSortContext _localctx = new Cmd_defineSortContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_cmd_defineSort);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(724);
			match(CMD_DefineSort);
			setState(725);
			symbol();
			setState(726);
			match(ParOpen);
			setState(730);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol) {
				{
				{
				setState(727);
				symbol();
				}
				}
				setState(732);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(733);
			match(ParClose);
			setState(734);
			sort();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_echoContext extends ParserRuleContext {
		public TerminalNode CMD_Echo() { return getToken(SMTLIBv2Parser.CMD_Echo, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public Cmd_echoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_echo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_echo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_echo(this);
		}
	}

	public final Cmd_echoContext cmd_echo() throws RecognitionException {
		Cmd_echoContext _localctx = new Cmd_echoContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_cmd_echo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(736);
			match(CMD_Echo);
			setState(737);
			string();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_exitContext extends ParserRuleContext {
		public TerminalNode CMD_Exit() { return getToken(SMTLIBv2Parser.CMD_Exit, 0); }
		public Cmd_exitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_exit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_exit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_exit(this);
		}
	}

	public final Cmd_exitContext cmd_exit() throws RecognitionException {
		Cmd_exitContext _localctx = new Cmd_exitContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_cmd_exit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(739);
			match(CMD_Exit);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getAssertionsContext extends ParserRuleContext {
		public TerminalNode CMD_GetAssertions() { return getToken(SMTLIBv2Parser.CMD_GetAssertions, 0); }
		public Cmd_getAssertionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getAssertions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getAssertions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getAssertions(this);
		}
	}

	public final Cmd_getAssertionsContext cmd_getAssertions() throws RecognitionException {
		Cmd_getAssertionsContext _localctx = new Cmd_getAssertionsContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_cmd_getAssertions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(741);
			match(CMD_GetAssertions);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getAssignmentContext extends ParserRuleContext {
		public TerminalNode CMD_GetAssignment() { return getToken(SMTLIBv2Parser.CMD_GetAssignment, 0); }
		public Cmd_getAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getAssignment(this);
		}
	}

	public final Cmd_getAssignmentContext cmd_getAssignment() throws RecognitionException {
		Cmd_getAssignmentContext _localctx = new Cmd_getAssignmentContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_cmd_getAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743);
			match(CMD_GetAssignment);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getInfoContext extends ParserRuleContext {
		public TerminalNode CMD_GetInfo() { return getToken(SMTLIBv2Parser.CMD_GetInfo, 0); }
		public Info_flagContext info_flag() {
			return getRuleContext(Info_flagContext.class,0);
		}
		public Cmd_getInfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getInfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getInfo(this);
		}
	}

	public final Cmd_getInfoContext cmd_getInfo() throws RecognitionException {
		Cmd_getInfoContext _localctx = new Cmd_getInfoContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_cmd_getInfo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(745);
			match(CMD_GetInfo);
			setState(746);
			info_flag();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getModelContext extends ParserRuleContext {
		public TerminalNode CMD_GetModel() { return getToken(SMTLIBv2Parser.CMD_GetModel, 0); }
		public Cmd_getModelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getModel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getModel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getModel(this);
		}
	}

	public final Cmd_getModelContext cmd_getModel() throws RecognitionException {
		Cmd_getModelContext _localctx = new Cmd_getModelContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_cmd_getModel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(748);
			match(CMD_GetModel);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getOptionContext extends ParserRuleContext {
		public TerminalNode CMD_GetOption() { return getToken(SMTLIBv2Parser.CMD_GetOption, 0); }
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public Cmd_getOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getOption(this);
		}
	}

	public final Cmd_getOptionContext cmd_getOption() throws RecognitionException {
		Cmd_getOptionContext _localctx = new Cmd_getOptionContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_cmd_getOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(750);
			match(CMD_GetOption);
			setState(751);
			keyword();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getProofContext extends ParserRuleContext {
		public TerminalNode CMD_GetProof() { return getToken(SMTLIBv2Parser.CMD_GetProof, 0); }
		public Cmd_getProofContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getProof; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getProof(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getProof(this);
		}
	}

	public final Cmd_getProofContext cmd_getProof() throws RecognitionException {
		Cmd_getProofContext _localctx = new Cmd_getProofContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_cmd_getProof);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(753);
			match(CMD_GetProof);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getUnsatAssumptionsContext extends ParserRuleContext {
		public TerminalNode CMD_GetUnsatAssumptions() { return getToken(SMTLIBv2Parser.CMD_GetUnsatAssumptions, 0); }
		public Cmd_getUnsatAssumptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getUnsatAssumptions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getUnsatAssumptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getUnsatAssumptions(this);
		}
	}

	public final Cmd_getUnsatAssumptionsContext cmd_getUnsatAssumptions() throws RecognitionException {
		Cmd_getUnsatAssumptionsContext _localctx = new Cmd_getUnsatAssumptionsContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_cmd_getUnsatAssumptions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(755);
			match(CMD_GetUnsatAssumptions);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getUnsatCoreContext extends ParserRuleContext {
		public TerminalNode CMD_GetUnsatCore() { return getToken(SMTLIBv2Parser.CMD_GetUnsatCore, 0); }
		public Cmd_getUnsatCoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getUnsatCore; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getUnsatCore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getUnsatCore(this);
		}
	}

	public final Cmd_getUnsatCoreContext cmd_getUnsatCore() throws RecognitionException {
		Cmd_getUnsatCoreContext _localctx = new Cmd_getUnsatCoreContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_cmd_getUnsatCore);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(757);
			match(CMD_GetUnsatCore);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_getValueContext extends ParserRuleContext {
		public TerminalNode CMD_GetValue() { return getToken(SMTLIBv2Parser.CMD_GetValue, 0); }
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Cmd_getValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_getValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_getValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_getValue(this);
		}
	}

	public final Cmd_getValueContext cmd_getValue() throws RecognitionException {
		Cmd_getValueContext _localctx = new Cmd_getValueContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_cmd_getValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(759);
			match(CMD_GetValue);
			setState(760);
			match(ParOpen);
			setState(762); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(761);
				term();
				}
				}
				setState(764); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << String) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (Numeral - 66)) | (1L << (Binary - 66)) | (1L << (HexDecimal - 66)) | (1L << (Decimal - 66)) | (1L << (UndefinedSymbol - 66)))) != 0) );
			setState(766);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_popContext extends ParserRuleContext {
		public TerminalNode CMD_Pop() { return getToken(SMTLIBv2Parser.CMD_Pop, 0); }
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public Cmd_popContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_pop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_pop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_pop(this);
		}
	}

	public final Cmd_popContext cmd_pop() throws RecognitionException {
		Cmd_popContext _localctx = new Cmd_popContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_cmd_pop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			match(CMD_Pop);
			setState(769);
			numeral();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_pushContext extends ParserRuleContext {
		public TerminalNode CMD_Push() { return getToken(SMTLIBv2Parser.CMD_Push, 0); }
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public Cmd_pushContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_push; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_push(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_push(this);
		}
	}

	public final Cmd_pushContext cmd_push() throws RecognitionException {
		Cmd_pushContext _localctx = new Cmd_pushContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_cmd_push);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(771);
			match(CMD_Push);
			setState(772);
			numeral();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_resetContext extends ParserRuleContext {
		public TerminalNode CMD_Reset() { return getToken(SMTLIBv2Parser.CMD_Reset, 0); }
		public Cmd_resetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_reset; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_reset(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_reset(this);
		}
	}

	public final Cmd_resetContext cmd_reset() throws RecognitionException {
		Cmd_resetContext _localctx = new Cmd_resetContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_cmd_reset);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(774);
			match(CMD_Reset);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_resetAssertionsContext extends ParserRuleContext {
		public TerminalNode CMD_ResetAssertions() { return getToken(SMTLIBv2Parser.CMD_ResetAssertions, 0); }
		public Cmd_resetAssertionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_resetAssertions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_resetAssertions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_resetAssertions(this);
		}
	}

	public final Cmd_resetAssertionsContext cmd_resetAssertions() throws RecognitionException {
		Cmd_resetAssertionsContext _localctx = new Cmd_resetAssertionsContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_cmd_resetAssertions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(776);
			match(CMD_ResetAssertions);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_setInfoContext extends ParserRuleContext {
		public TerminalNode CMD_SetInfo() { return getToken(SMTLIBv2Parser.CMD_SetInfo, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public Cmd_setInfoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_setInfo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_setInfo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_setInfo(this);
		}
	}

	public final Cmd_setInfoContext cmd_setInfo() throws RecognitionException {
		Cmd_setInfoContext _localctx = new Cmd_setInfoContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_cmd_setInfo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(778);
			match(CMD_SetInfo);
			setState(779);
			attribute();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_setLogicContext extends ParserRuleContext {
		public TerminalNode CMD_SetLogic() { return getToken(SMTLIBv2Parser.CMD_SetLogic, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public Cmd_setLogicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_setLogic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_setLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_setLogic(this);
		}
	}

	public final Cmd_setLogicContext cmd_setLogic() throws RecognitionException {
		Cmd_setLogicContext _localctx = new Cmd_setLogicContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_cmd_setLogic);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(781);
			match(CMD_SetLogic);
			setState(782);
			symbol();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cmd_setOptionContext extends ParserRuleContext {
		public TerminalNode CMD_SetOption() { return getToken(SMTLIBv2Parser.CMD_SetOption, 0); }
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public Cmd_setOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd_setOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCmd_setOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCmd_setOption(this);
		}
	}

	public final Cmd_setOptionContext cmd_setOption() throws RecognitionException {
		Cmd_setOptionContext _localctx = new Cmd_setOptionContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_cmd_setOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(784);
			match(CMD_SetOption);
			setState(785);
			option();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public Cmd_assertContext cmd_assert() {
			return getRuleContext(Cmd_assertContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Cmd_checkSatContext cmd_checkSat() {
			return getRuleContext(Cmd_checkSatContext.class,0);
		}
		public Cmd_checkSatAssumingContext cmd_checkSatAssuming() {
			return getRuleContext(Cmd_checkSatAssumingContext.class,0);
		}
		public Cmd_declareConstContext cmd_declareConst() {
			return getRuleContext(Cmd_declareConstContext.class,0);
		}
		public Cmd_declareDatatypeContext cmd_declareDatatype() {
			return getRuleContext(Cmd_declareDatatypeContext.class,0);
		}
		public Cmd_declareDatatypesContext cmd_declareDatatypes() {
			return getRuleContext(Cmd_declareDatatypesContext.class,0);
		}
		public Cmd_declareFunContext cmd_declareFun() {
			return getRuleContext(Cmd_declareFunContext.class,0);
		}
		public Cmd_declareSortContext cmd_declareSort() {
			return getRuleContext(Cmd_declareSortContext.class,0);
		}
		public Cmd_defineFunContext cmd_defineFun() {
			return getRuleContext(Cmd_defineFunContext.class,0);
		}
		public Cmd_defineFunRecContext cmd_defineFunRec() {
			return getRuleContext(Cmd_defineFunRecContext.class,0);
		}
		public Cmd_defineFunsRecContext cmd_defineFunsRec() {
			return getRuleContext(Cmd_defineFunsRecContext.class,0);
		}
		public Cmd_defineSortContext cmd_defineSort() {
			return getRuleContext(Cmd_defineSortContext.class,0);
		}
		public Cmd_echoContext cmd_echo() {
			return getRuleContext(Cmd_echoContext.class,0);
		}
		public Cmd_exitContext cmd_exit() {
			return getRuleContext(Cmd_exitContext.class,0);
		}
		public Cmd_getAssertionsContext cmd_getAssertions() {
			return getRuleContext(Cmd_getAssertionsContext.class,0);
		}
		public Cmd_getAssignmentContext cmd_getAssignment() {
			return getRuleContext(Cmd_getAssignmentContext.class,0);
		}
		public Cmd_getInfoContext cmd_getInfo() {
			return getRuleContext(Cmd_getInfoContext.class,0);
		}
		public Cmd_getModelContext cmd_getModel() {
			return getRuleContext(Cmd_getModelContext.class,0);
		}
		public Cmd_getOptionContext cmd_getOption() {
			return getRuleContext(Cmd_getOptionContext.class,0);
		}
		public Cmd_getProofContext cmd_getProof() {
			return getRuleContext(Cmd_getProofContext.class,0);
		}
		public Cmd_getUnsatAssumptionsContext cmd_getUnsatAssumptions() {
			return getRuleContext(Cmd_getUnsatAssumptionsContext.class,0);
		}
		public Cmd_getUnsatCoreContext cmd_getUnsatCore() {
			return getRuleContext(Cmd_getUnsatCoreContext.class,0);
		}
		public Cmd_getValueContext cmd_getValue() {
			return getRuleContext(Cmd_getValueContext.class,0);
		}
		public Cmd_popContext cmd_pop() {
			return getRuleContext(Cmd_popContext.class,0);
		}
		public Cmd_pushContext cmd_push() {
			return getRuleContext(Cmd_pushContext.class,0);
		}
		public Cmd_resetContext cmd_reset() {
			return getRuleContext(Cmd_resetContext.class,0);
		}
		public Cmd_resetAssertionsContext cmd_resetAssertions() {
			return getRuleContext(Cmd_resetAssertionsContext.class,0);
		}
		public Cmd_setInfoContext cmd_setInfo() {
			return getRuleContext(Cmd_setInfoContext.class,0);
		}
		public Cmd_setLogicContext cmd_setLogic() {
			return getRuleContext(Cmd_setLogicContext.class,0);
		}
		public Cmd_setOptionContext cmd_setOption() {
			return getRuleContext(Cmd_setOptionContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_command);
		try {
			setState(907);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(787);
				match(ParOpen);
				setState(788);
				cmd_assert();
				setState(789);
				match(ParClose);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(791);
				match(ParOpen);
				setState(792);
				cmd_checkSat();
				setState(793);
				match(ParClose);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(795);
				match(ParOpen);
				setState(796);
				cmd_checkSatAssuming();
				setState(797);
				match(ParClose);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(799);
				match(ParOpen);
				setState(800);
				cmd_declareConst();
				setState(801);
				match(ParClose);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(803);
				match(ParOpen);
				setState(804);
				cmd_declareDatatype();
				setState(805);
				match(ParClose);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(807);
				match(ParOpen);
				setState(808);
				cmd_declareDatatypes();
				setState(809);
				match(ParClose);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(811);
				match(ParOpen);
				setState(812);
				cmd_declareFun();
				setState(813);
				match(ParClose);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(815);
				match(ParOpen);
				setState(816);
				cmd_declareSort();
				setState(817);
				match(ParClose);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(819);
				match(ParOpen);
				setState(820);
				cmd_defineFun();
				setState(821);
				match(ParClose);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(823);
				match(ParOpen);
				setState(824);
				cmd_defineFunRec();
				setState(825);
				match(ParClose);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(827);
				match(ParOpen);
				setState(828);
				cmd_defineFunsRec();
				setState(829);
				match(ParClose);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(831);
				match(ParOpen);
				setState(832);
				cmd_defineSort();
				setState(833);
				match(ParClose);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(835);
				match(ParOpen);
				setState(836);
				cmd_echo();
				setState(837);
				match(ParClose);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(839);
				match(ParOpen);
				setState(840);
				cmd_exit();
				setState(841);
				match(ParClose);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(843);
				match(ParOpen);
				setState(844);
				cmd_getAssertions();
				setState(845);
				match(ParClose);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(847);
				match(ParOpen);
				setState(848);
				cmd_getAssignment();
				setState(849);
				match(ParClose);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(851);
				match(ParOpen);
				setState(852);
				cmd_getInfo();
				setState(853);
				match(ParClose);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(855);
				match(ParOpen);
				setState(856);
				cmd_getModel();
				setState(857);
				match(ParClose);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(859);
				match(ParOpen);
				setState(860);
				cmd_getOption();
				setState(861);
				match(ParClose);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(863);
				match(ParOpen);
				setState(864);
				cmd_getProof();
				setState(865);
				match(ParClose);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(867);
				match(ParOpen);
				setState(868);
				cmd_getUnsatAssumptions();
				setState(869);
				match(ParClose);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(871);
				match(ParOpen);
				setState(872);
				cmd_getUnsatCore();
				setState(873);
				match(ParClose);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(875);
				match(ParOpen);
				setState(876);
				cmd_getValue();
				setState(877);
				match(ParClose);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(879);
				match(ParOpen);
				setState(880);
				cmd_pop();
				setState(881);
				match(ParClose);
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(883);
				match(ParOpen);
				setState(884);
				cmd_push();
				setState(885);
				match(ParClose);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(887);
				match(ParOpen);
				setState(888);
				cmd_reset();
				setState(889);
				match(ParClose);
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(891);
				match(ParOpen);
				setState(892);
				cmd_resetAssertions();
				setState(893);
				match(ParClose);
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(895);
				match(ParOpen);
				setState(896);
				cmd_setInfo();
				setState(897);
				match(ParClose);
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(899);
				match(ParOpen);
				setState(900);
				cmd_setLogic();
				setState(901);
				match(ParClose);
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(903);
				match(ParOpen);
				setState(904);
				cmd_setOption();
				setState(905);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class B_valueContext extends ParserRuleContext {
		public TerminalNode PS_True() { return getToken(SMTLIBv2Parser.PS_True, 0); }
		public TerminalNode PS_False() { return getToken(SMTLIBv2Parser.PS_False, 0); }
		public B_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_b_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterB_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitB_value(this);
		}
	}

	public final B_valueContext b_value() throws RecognitionException {
		B_valueContext _localctx = new B_valueContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_b_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(909);
			_la = _input.LA(1);
			if ( !(_la==PS_False || _la==PS_True) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public TerminalNode PK_DiagnosticOutputChannel() { return getToken(SMTLIBv2Parser.PK_DiagnosticOutputChannel, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode PK_GlobalDeclarations() { return getToken(SMTLIBv2Parser.PK_GlobalDeclarations, 0); }
		public B_valueContext b_value() {
			return getRuleContext(B_valueContext.class,0);
		}
		public TerminalNode PK_InteractiveMode() { return getToken(SMTLIBv2Parser.PK_InteractiveMode, 0); }
		public TerminalNode PK_PrintSuccess() { return getToken(SMTLIBv2Parser.PK_PrintSuccess, 0); }
		public TerminalNode PK_ProduceAssertions() { return getToken(SMTLIBv2Parser.PK_ProduceAssertions, 0); }
		public TerminalNode PK_ProduceAssignments() { return getToken(SMTLIBv2Parser.PK_ProduceAssignments, 0); }
		public TerminalNode PK_ProduceModels() { return getToken(SMTLIBv2Parser.PK_ProduceModels, 0); }
		public TerminalNode PK_ProduceProofs() { return getToken(SMTLIBv2Parser.PK_ProduceProofs, 0); }
		public TerminalNode PK_ProduceUnsatAssumptions() { return getToken(SMTLIBv2Parser.PK_ProduceUnsatAssumptions, 0); }
		public TerminalNode PK_ProduceUnsatCores() { return getToken(SMTLIBv2Parser.PK_ProduceUnsatCores, 0); }
		public TerminalNode PK_RandomSeed() { return getToken(SMTLIBv2Parser.PK_RandomSeed, 0); }
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public TerminalNode PK_RegularOutputChannel() { return getToken(SMTLIBv2Parser.PK_RegularOutputChannel, 0); }
		public TerminalNode PK_ReproducibleResourceLimit() { return getToken(SMTLIBv2Parser.PK_ReproducibleResourceLimit, 0); }
		public TerminalNode PK_Verbosity() { return getToken(SMTLIBv2Parser.PK_Verbosity, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitOption(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_option);
		try {
			setState(940);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(911);
				match(PK_DiagnosticOutputChannel);
				setState(912);
				string();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(913);
				match(PK_GlobalDeclarations);
				setState(914);
				b_value();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(915);
				match(PK_InteractiveMode);
				setState(916);
				b_value();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(917);
				match(PK_PrintSuccess);
				setState(918);
				b_value();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(919);
				match(PK_ProduceAssertions);
				setState(920);
				b_value();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(921);
				match(PK_ProduceAssignments);
				setState(922);
				b_value();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(923);
				match(PK_ProduceModels);
				setState(924);
				b_value();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(925);
				match(PK_ProduceProofs);
				setState(926);
				b_value();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(927);
				match(PK_ProduceUnsatAssumptions);
				setState(928);
				b_value();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(929);
				match(PK_ProduceUnsatCores);
				setState(930);
				b_value();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(931);
				match(PK_RandomSeed);
				setState(932);
				numeral();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(933);
				match(PK_RegularOutputChannel);
				setState(934);
				string();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(935);
				match(PK_ReproducibleResourceLimit);
				setState(936);
				numeral();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(937);
				match(PK_Verbosity);
				setState(938);
				numeral();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(939);
				attribute();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Info_flagContext extends ParserRuleContext {
		public TerminalNode PK_AllStatistics() { return getToken(SMTLIBv2Parser.PK_AllStatistics, 0); }
		public TerminalNode PK_AssertionStackLevels() { return getToken(SMTLIBv2Parser.PK_AssertionStackLevels, 0); }
		public TerminalNode PK_Authors() { return getToken(SMTLIBv2Parser.PK_Authors, 0); }
		public TerminalNode PK_ErrorBehaviour() { return getToken(SMTLIBv2Parser.PK_ErrorBehaviour, 0); }
		public TerminalNode PK_Name() { return getToken(SMTLIBv2Parser.PK_Name, 0); }
		public TerminalNode PK_ReasonUnknown() { return getToken(SMTLIBv2Parser.PK_ReasonUnknown, 0); }
		public TerminalNode PK_Version() { return getToken(SMTLIBv2Parser.PK_Version, 0); }
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public Info_flagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_info_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterInfo_flag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitInfo_flag(this);
		}
	}

	public final Info_flagContext info_flag() throws RecognitionException {
		Info_flagContext _localctx = new Info_flagContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_info_flag);
		try {
			setState(950);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(942);
				match(PK_AllStatistics);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(943);
				match(PK_AssertionStackLevels);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(944);
				match(PK_Authors);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(945);
				match(PK_ErrorBehaviour);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(946);
				match(PK_Name);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(947);
				match(PK_ReasonUnknown);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(948);
				match(PK_Version);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(949);
				keyword();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Error_behaviourContext extends ParserRuleContext {
		public TerminalNode PS_ImmediateExit() { return getToken(SMTLIBv2Parser.PS_ImmediateExit, 0); }
		public TerminalNode PS_ContinuedExecution() { return getToken(SMTLIBv2Parser.PS_ContinuedExecution, 0); }
		public Error_behaviourContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error_behaviour; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterError_behaviour(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitError_behaviour(this);
		}
	}

	public final Error_behaviourContext error_behaviour() throws RecognitionException {
		Error_behaviourContext _localctx = new Error_behaviourContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_error_behaviour);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(952);
			_la = _input.LA(1);
			if ( !(_la==PS_ContinuedExecution || _la==PS_ImmediateExit) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reason_unknownContext extends ParserRuleContext {
		public TerminalNode PS_Memout() { return getToken(SMTLIBv2Parser.PS_Memout, 0); }
		public TerminalNode PS_Incomplete() { return getToken(SMTLIBv2Parser.PS_Incomplete, 0); }
		public S_exprContext s_expr() {
			return getRuleContext(S_exprContext.class,0);
		}
		public Reason_unknownContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reason_unknown; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterReason_unknown(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitReason_unknown(this);
		}
	}

	public final Reason_unknownContext reason_unknown() throws RecognitionException {
		Reason_unknownContext _localctx = new Reason_unknownContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_reason_unknown);
		try {
			setState(957);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(954);
				match(PS_Memout);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(955);
				match(PS_Incomplete);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(956);
				s_expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Model_responseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public Cmd_defineFunContext cmd_defineFun() {
			return getRuleContext(Cmd_defineFunContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Cmd_defineFunRecContext cmd_defineFunRec() {
			return getRuleContext(Cmd_defineFunRecContext.class,0);
		}
		public Cmd_defineFunsRecContext cmd_defineFunsRec() {
			return getRuleContext(Cmd_defineFunsRecContext.class,0);
		}
		public Model_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_model_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterModel_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitModel_response(this);
		}
	}

	public final Model_responseContext model_response() throws RecognitionException {
		Model_responseContext _localctx = new Model_responseContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_model_response);
		try {
			setState(971);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(959);
				match(ParOpen);
				setState(960);
				cmd_defineFun();
				setState(961);
				match(ParClose);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(963);
				match(ParOpen);
				setState(964);
				cmd_defineFunRec();
				setState(965);
				match(ParClose);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(967);
				match(ParOpen);
				setState(968);
				cmd_defineFunsRec();
				setState(969);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Info_responseContext extends ParserRuleContext {
		public TerminalNode PK_AssertionStackLevels() { return getToken(SMTLIBv2Parser.PK_AssertionStackLevels, 0); }
		public NumeralContext numeral() {
			return getRuleContext(NumeralContext.class,0);
		}
		public TerminalNode PK_Authors() { return getToken(SMTLIBv2Parser.PK_Authors, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode PK_ErrorBehaviour() { return getToken(SMTLIBv2Parser.PK_ErrorBehaviour, 0); }
		public Error_behaviourContext error_behaviour() {
			return getRuleContext(Error_behaviourContext.class,0);
		}
		public TerminalNode PK_Name() { return getToken(SMTLIBv2Parser.PK_Name, 0); }
		public TerminalNode PK_ReasonUnknown() { return getToken(SMTLIBv2Parser.PK_ReasonUnknown, 0); }
		public Reason_unknownContext reason_unknown() {
			return getRuleContext(Reason_unknownContext.class,0);
		}
		public TerminalNode PK_Version() { return getToken(SMTLIBv2Parser.PK_Version, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public Info_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_info_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterInfo_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitInfo_response(this);
		}
	}

	public final Info_responseContext info_response() throws RecognitionException {
		Info_responseContext _localctx = new Info_responseContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_info_response);
		try {
			setState(986);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(973);
				match(PK_AssertionStackLevels);
				setState(974);
				numeral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(975);
				match(PK_Authors);
				setState(976);
				string();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(977);
				match(PK_ErrorBehaviour);
				setState(978);
				error_behaviour();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(979);
				match(PK_Name);
				setState(980);
				string();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(981);
				match(PK_ReasonUnknown);
				setState(982);
				reason_unknown();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(983);
				match(PK_Version);
				setState(984);
				string();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(985);
				attribute();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Valuation_pairContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public Valuation_pairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valuation_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterValuation_pair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitValuation_pair(this);
		}
	}

	public final Valuation_pairContext valuation_pair() throws RecognitionException {
		Valuation_pairContext _localctx = new Valuation_pairContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_valuation_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(988);
			match(ParOpen);
			setState(989);
			term();
			setState(990);
			term();
			setState(991);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class T_valuation_pairContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public SymbolContext symbol() {
			return getRuleContext(SymbolContext.class,0);
		}
		public B_valueContext b_value() {
			return getRuleContext(B_valueContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public T_valuation_pairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_t_valuation_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterT_valuation_pair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitT_valuation_pair(this);
		}
	}

	public final T_valuation_pairContext t_valuation_pair() throws RecognitionException {
		T_valuation_pairContext _localctx = new T_valuation_pairContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_t_valuation_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(993);
			match(ParOpen);
			setState(994);
			symbol();
			setState(995);
			b_value();
			setState(996);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Check_sat_responseContext extends ParserRuleContext {
		public TerminalNode PS_Sat() { return getToken(SMTLIBv2Parser.PS_Sat, 0); }
		public TerminalNode PS_Unsat() { return getToken(SMTLIBv2Parser.PS_Unsat, 0); }
		public TerminalNode PS_Unknown() { return getToken(SMTLIBv2Parser.PS_Unknown, 0); }
		public Check_sat_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_check_sat_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterCheck_sat_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitCheck_sat_response(this);
		}
	}

	public final Check_sat_responseContext check_sat_response() throws RecognitionException {
		Check_sat_responseContext _localctx = new Check_sat_responseContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_check_sat_response);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(998);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PS_Sat) | (1L << PS_Unknown) | (1L << PS_Unsat))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Echo_responseContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public Echo_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_echo_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterEcho_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitEcho_response(this);
		}
	}

	public final Echo_responseContext echo_response() throws RecognitionException {
		Echo_responseContext _localctx = new Echo_responseContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_echo_response);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1000);
			string();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_assertions_responseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public Get_assertions_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_assertions_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_assertions_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_assertions_response(this);
		}
	}

	public final Get_assertions_responseContext get_assertions_response() throws RecognitionException {
		Get_assertions_responseContext _localctx = new Get_assertions_responseContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_get_assertions_response);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1002);
			match(ParOpen);
			setState(1006);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ParOpen) | (1L << String) | (1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (Numeral - 66)) | (1L << (Binary - 66)) | (1L << (HexDecimal - 66)) | (1L << (Decimal - 66)) | (1L << (UndefinedSymbol - 66)))) != 0)) {
				{
				{
				setState(1003);
				term();
				}
				}
				setState(1008);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1009);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_assignment_responseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<T_valuation_pairContext> t_valuation_pair() {
			return getRuleContexts(T_valuation_pairContext.class);
		}
		public T_valuation_pairContext t_valuation_pair(int i) {
			return getRuleContext(T_valuation_pairContext.class,i);
		}
		public Get_assignment_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_assignment_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_assignment_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_assignment_response(this);
		}
	}

	public final Get_assignment_responseContext get_assignment_response() throws RecognitionException {
		Get_assignment_responseContext _localctx = new Get_assignment_responseContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_get_assignment_response);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1011);
			match(ParOpen);
			setState(1015);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ParOpen) {
				{
				{
				setState(1012);
				t_valuation_pair();
				}
				}
				setState(1017);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1018);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_info_responseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<Info_responseContext> info_response() {
			return getRuleContexts(Info_responseContext.class);
		}
		public Info_responseContext info_response(int i) {
			return getRuleContext(Info_responseContext.class,i);
		}
		public Get_info_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_info_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_info_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_info_response(this);
		}
	}

	public final Get_info_responseContext get_info_response() throws RecognitionException {
		Get_info_responseContext _localctx = new Get_info_responseContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_get_info_response);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1020);
			match(ParOpen);
			setState(1022); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1021);
				info_response();
				}
				}
				setState(1024); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 70)) & ~0x3f) == 0 && ((1L << (_la - 70)) & ((1L << (Colon - 70)) | (1L << (PK_AllStatistics - 70)) | (1L << (PK_AssertionStackLevels - 70)) | (1L << (PK_Authors - 70)) | (1L << (PK_Category - 70)) | (1L << (PK_Chainable - 70)) | (1L << (PK_Definition - 70)) | (1L << (PK_DiagnosticOutputChannel - 70)) | (1L << (PK_ErrorBehaviour - 70)) | (1L << (PK_Extension - 70)) | (1L << (PK_Funs - 70)) | (1L << (PK_FunsDescription - 70)) | (1L << (PK_GlobalDeclarations - 70)) | (1L << (PK_InteractiveMode - 70)) | (1L << (PK_Language - 70)) | (1L << (PK_LeftAssoc - 70)) | (1L << (PK_License - 70)) | (1L << (PK_Named - 70)) | (1L << (PK_Name - 70)) | (1L << (PK_Notes - 70)) | (1L << (PK_Pattern - 70)) | (1L << (PK_PrintSuccess - 70)) | (1L << (PK_ProduceAssertions - 70)) | (1L << (PK_ProduceAssignments - 70)) | (1L << (PK_ProduceModels - 70)) | (1L << (PK_ProduceProofs - 70)) | (1L << (PK_ProduceUnsatAssumptions - 70)) | (1L << (PK_ProduceUnsatCores - 70)) | (1L << (PK_RandomSeed - 70)) | (1L << (PK_ReasonUnknown - 70)) | (1L << (PK_RegularOutputChannel - 70)) | (1L << (PK_ReproducibleResourceLimit - 70)) | (1L << (PK_RightAssoc - 70)) | (1L << (PK_SmtLibVersion - 70)) | (1L << (PK_Sorts - 70)) | (1L << (PK_SortsDescription - 70)) | (1L << (PK_Source - 70)) | (1L << (PK_Status - 70)) | (1L << (PK_Theories - 70)) | (1L << (PK_Values - 70)) | (1L << (PK_Verbosity - 70)) | (1L << (PK_Version - 70)))) != 0) );
			setState(1026);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_model_responseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode RS_Model() { return getToken(SMTLIBv2Parser.RS_Model, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<Model_responseContext> model_response() {
			return getRuleContexts(Model_responseContext.class);
		}
		public Model_responseContext model_response(int i) {
			return getRuleContext(Model_responseContext.class,i);
		}
		public Get_model_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_model_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_model_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_model_response(this);
		}
	}

	public final Get_model_responseContext get_model_response() throws RecognitionException {
		Get_model_responseContext _localctx = new Get_model_responseContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_get_model_response);
		int _la;
		try {
			setState(1045);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1028);
				match(ParOpen);
				setState(1029);
				match(RS_Model);
				setState(1033);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ParOpen) {
					{
					{
					setState(1030);
					model_response();
					}
					}
					setState(1035);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1036);
				match(ParClose);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1037);
				match(ParOpen);
				setState(1041);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ParOpen) {
					{
					{
					setState(1038);
					model_response();
					}
					}
					setState(1043);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1044);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_option_responseContext extends ParserRuleContext {
		public Attribute_valueContext attribute_value() {
			return getRuleContext(Attribute_valueContext.class,0);
		}
		public Get_option_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_option_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_option_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_option_response(this);
		}
	}

	public final Get_option_responseContext get_option_response() throws RecognitionException {
		Get_option_responseContext _localctx = new Get_option_responseContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_get_option_response);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1047);
			attribute_value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_proof_responseContext extends ParserRuleContext {
		public S_exprContext s_expr() {
			return getRuleContext(S_exprContext.class,0);
		}
		public Get_proof_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_proof_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_proof_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_proof_response(this);
		}
	}

	public final Get_proof_responseContext get_proof_response() throws RecognitionException {
		Get_proof_responseContext _localctx = new Get_proof_responseContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_get_proof_response);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1049);
			s_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_unsat_assump_responseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public Get_unsat_assump_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_unsat_assump_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_unsat_assump_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_unsat_assump_response(this);
		}
	}

	public final Get_unsat_assump_responseContext get_unsat_assump_response() throws RecognitionException {
		Get_unsat_assump_responseContext _localctx = new Get_unsat_assump_responseContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_get_unsat_assump_response);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1051);
			match(ParOpen);
			setState(1055);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol) {
				{
				{
				setState(1052);
				symbol();
				}
				}
				setState(1057);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1058);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_unsat_core_responseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<SymbolContext> symbol() {
			return getRuleContexts(SymbolContext.class);
		}
		public SymbolContext symbol(int i) {
			return getRuleContext(SymbolContext.class,i);
		}
		public Get_unsat_core_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_unsat_core_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_unsat_core_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_unsat_core_response(this);
		}
	}

	public final Get_unsat_core_responseContext get_unsat_core_response() throws RecognitionException {
		Get_unsat_core_responseContext _localctx = new Get_unsat_core_responseContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_get_unsat_core_response);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1060);
			match(ParOpen);
			setState(1064);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << QuotedSymbol) | (1L << PS_Not) | (1L << PS_Bool) | (1L << PS_ContinuedExecution) | (1L << PS_Error) | (1L << PS_False) | (1L << PS_ImmediateExit) | (1L << PS_Incomplete) | (1L << PS_Logic) | (1L << PS_Memout) | (1L << PS_Sat) | (1L << PS_Success) | (1L << PS_Theory) | (1L << PS_True) | (1L << PS_Unknown) | (1L << PS_Unsupported) | (1L << PS_Unsat))) != 0) || _la==UndefinedSymbol) {
				{
				{
				setState(1061);
				symbol();
				}
				}
				setState(1066);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1067);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_value_responseContext extends ParserRuleContext {
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public List<Valuation_pairContext> valuation_pair() {
			return getRuleContexts(Valuation_pairContext.class);
		}
		public Valuation_pairContext valuation_pair(int i) {
			return getRuleContext(Valuation_pairContext.class,i);
		}
		public Get_value_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_value_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGet_value_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGet_value_response(this);
		}
	}

	public final Get_value_responseContext get_value_response() throws RecognitionException {
		Get_value_responseContext _localctx = new Get_value_responseContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_get_value_response);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1069);
			match(ParOpen);
			setState(1071); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1070);
				valuation_pair();
				}
				}
				setState(1073); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ParOpen );
			setState(1075);
			match(ParClose);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Specific_success_responseContext extends ParserRuleContext {
		public Check_sat_responseContext check_sat_response() {
			return getRuleContext(Check_sat_responseContext.class,0);
		}
		public Echo_responseContext echo_response() {
			return getRuleContext(Echo_responseContext.class,0);
		}
		public Get_assertions_responseContext get_assertions_response() {
			return getRuleContext(Get_assertions_responseContext.class,0);
		}
		public Get_assignment_responseContext get_assignment_response() {
			return getRuleContext(Get_assignment_responseContext.class,0);
		}
		public Get_info_responseContext get_info_response() {
			return getRuleContext(Get_info_responseContext.class,0);
		}
		public Get_model_responseContext get_model_response() {
			return getRuleContext(Get_model_responseContext.class,0);
		}
		public Get_option_responseContext get_option_response() {
			return getRuleContext(Get_option_responseContext.class,0);
		}
		public Get_proof_responseContext get_proof_response() {
			return getRuleContext(Get_proof_responseContext.class,0);
		}
		public Get_unsat_assump_responseContext get_unsat_assump_response() {
			return getRuleContext(Get_unsat_assump_responseContext.class,0);
		}
		public Get_unsat_core_responseContext get_unsat_core_response() {
			return getRuleContext(Get_unsat_core_responseContext.class,0);
		}
		public Get_value_responseContext get_value_response() {
			return getRuleContext(Get_value_responseContext.class,0);
		}
		public Specific_success_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specific_success_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterSpecific_success_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitSpecific_success_response(this);
		}
	}

	public final Specific_success_responseContext specific_success_response() throws RecognitionException {
		Specific_success_responseContext _localctx = new Specific_success_responseContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_specific_success_response);
		try {
			setState(1088);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1077);
				check_sat_response();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1078);
				echo_response();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1079);
				get_assertions_response();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1080);
				get_assignment_response();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1081);
				get_info_response();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1082);
				get_model_response();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1083);
				get_option_response();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1084);
				get_proof_response();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1085);
				get_unsat_assump_response();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1086);
				get_unsat_core_response();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1087);
				get_value_response();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class General_responseContext extends ParserRuleContext {
		public TerminalNode PS_Success() { return getToken(SMTLIBv2Parser.PS_Success, 0); }
		public Specific_success_responseContext specific_success_response() {
			return getRuleContext(Specific_success_responseContext.class,0);
		}
		public TerminalNode PS_Unsupported() { return getToken(SMTLIBv2Parser.PS_Unsupported, 0); }
		public TerminalNode ParOpen() { return getToken(SMTLIBv2Parser.ParOpen, 0); }
		public TerminalNode PS_Error() { return getToken(SMTLIBv2Parser.PS_Error, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode ParClose() { return getToken(SMTLIBv2Parser.ParClose, 0); }
		public General_responseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_general_response; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).enterGeneral_response(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SMTLIBv2Listener ) ((SMTLIBv2Listener)listener).exitGeneral_response(this);
		}
	}

	public final General_responseContext general_response() throws RecognitionException {
		General_responseContext _localctx = new General_responseContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_general_response);
		try {
			setState(1098);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1090);
				match(PS_Success);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1091);
				specific_success_response();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1092);
				match(PS_Unsupported);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1093);
				match(ParOpen);
				setState(1094);
				match(PS_Error);
				setState(1095);
				string();
				setState(1096);
				match(ParClose);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3t\u044f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\u00cd\n\2\3\3"+
		"\3\3\3\4\3\4\5\4\u00d3\n\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\5\b\u00dd\n"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\5\16\u00ec"+
		"\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u00f3\n\17\3\20\3\20\3\20\3\20\3\20"+
		"\7\20\u00fa\n\20\f\20\16\20\u00fd\13\20\3\20\5\20\u0100\n\20\3\21\3\21"+
		"\5\21\u0104\n\21\3\22\3\22\3\22\3\22\3\22\6\22\u010b\n\22\r\22\16\22\u010c"+
		"\3\22\3\22\5\22\u0111\n\22\3\23\3\23\3\23\3\23\7\23\u0117\n\23\f\23\16"+
		"\23\u011a\13\23\3\23\5\23\u011d\n\23\3\24\3\24\3\24\3\24\5\24\u0123\n"+
		"\24\3\25\3\25\3\25\3\25\6\25\u0129\n\25\r\25\16\25\u012a\3\25\3\25\5\25"+
		"\u012f\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0138\n\26\3\27\3"+
		"\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\6\31\u0148"+
		"\n\31\r\31\16\31\u0149\3\31\3\31\5\31\u014e\n\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\6\33\u015a\n\33\r\33\16\33\u015b\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\6\33\u0164\n\33\r\33\16\33\u0165\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\6\33\u0170\n\33\r\33\16\33\u0171\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\6\33\u017c\n\33\r\33\16\33\u017d\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\6\33\u0189\n\33\r\33\16\33\u018a\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\6\33\u0194\n\33\r\33\16\33\u0195\3\33\3"+
		"\33\5\33\u019a\n\33\3\34\3\34\3\34\3\34\7\34\u01a0\n\34\f\34\16\34\u01a3"+
		"\13\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\7\36\u01ad\n\36\f\36\16"+
		"\36\u01b0\13\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u01b8\n\36\f\36\16"+
		"\36\u01bb\13\36\3\36\3\36\3\36\3\36\3\36\6\36\u01c2\n\36\r\36\16\36\u01c3"+
		"\3\36\7\36\u01c7\n\36\f\36\16\36\u01ca\13\36\3\36\3\36\5\36\u01ce\n\36"+
		"\3\37\3\37\3\37\3\37\3\37\6\37\u01d5\n\37\r\37\16\37\u01d6\3\37\3\37\3"+
		"\37\3\37\6\37\u01dd\n\37\r\37\16\37\u01de\3\37\7\37\u01e2\n\37\f\37\16"+
		"\37\u01e5\13\37\3\37\3\37\3\37\5\37\u01ea\n\37\3 \3 \3 \6 \u01ef\n \r"+
		" \16 \u01f0\3 \3 \3 \3 \3 \6 \u01f8\n \r \16 \u01f9\3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \5 \u0209\n \3!\3!\3!\3!\6!\u020f\n!\r!\16!\u0210"+
		"\3!\3!\3\"\3\"\3\"\6\"\u0218\n\"\r\"\16\"\u0219\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\5\"\u0227\n\"\3#\3#\3#\3#\6#\u022d\n#\r#\16#\u022e"+
		"\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3&\3&\3&\7&\u0240\n&\f&\16&\u0243"+
		"\13&\3&\3&\3\'\3\'\6\'\u0249\n\'\r\'\16\'\u024a\3\'\3\'\3\'\3\'\3\'\3"+
		"\'\6\'\u0253\n\'\r\'\16\'\u0254\3\'\3\'\3\'\6\'\u025a\n\'\r\'\16\'\u025b"+
		"\3\'\3\'\3\'\5\'\u0261\n\'\3(\3(\3(\3(\7(\u0267\n(\f(\16(\u026a\13(\3"+
		"(\3(\3(\3(\3)\3)\3)\7)\u0273\n)\f)\16)\u0276\13)\3)\3)\3)\3)\3*\3*\3*"+
		"\3*\3*\3*\5*\u0282\n*\3+\7+\u0285\n+\f+\16+\u0288\13+\3,\3,\3,\3-\3-\3"+
		".\3.\3.\7.\u0292\n.\f.\16.\u0295\13.\3.\3.\3/\3/\3/\3/\3\60\3\60\3\60"+
		"\3\60\3\61\3\61\3\61\6\61\u02a4\n\61\r\61\16\61\u02a5\3\61\3\61\3\61\6"+
		"\61\u02ab\n\61\r\61\16\61\u02ac\3\61\3\61\3\62\3\62\3\62\3\62\7\62\u02b5"+
		"\n\62\f\62\16\62\u02b8\13\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3"+
		"\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\6\66\u02ca\n\66\r\66\16\66\u02cb"+
		"\3\66\3\66\3\66\6\66\u02d1\n\66\r\66\16\66\u02d2\3\66\3\66\3\67\3\67\3"+
		"\67\3\67\7\67\u02db\n\67\f\67\16\67\u02de\13\67\3\67\3\67\3\67\38\38\3"+
		"8\39\39\3:\3:\3;\3;\3<\3<\3<\3=\3=\3>\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3"+
		"B\6B\u02fd\nB\rB\16B\u02fe\3B\3B\3C\3C\3C\3D\3D\3D\3E\3E\3F\3F\3G\3G\3"+
		"G\3H\3H\3H\3I\3I\3I\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3"+
		"J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3"+
		"J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3"+
		"J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3"+
		"J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3"+
		"J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\3J\5J\u038e\nJ\3K\3K\3L\3L\3L\3L\3L\3"+
		"L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3"+
		"L\5L\u03af\nL\3M\3M\3M\3M\3M\3M\3M\3M\5M\u03b9\nM\3N\3N\3O\3O\3O\5O\u03c0"+
		"\nO\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\5P\u03ce\nP\3Q\3Q\3Q\3Q\3Q\3Q"+
		"\3Q\3Q\3Q\3Q\3Q\3Q\3Q\5Q\u03dd\nQ\3R\3R\3R\3R\3R\3S\3S\3S\3S\3S\3T\3T"+
		"\3U\3U\3V\3V\7V\u03ef\nV\fV\16V\u03f2\13V\3V\3V\3W\3W\7W\u03f8\nW\fW\16"+
		"W\u03fb\13W\3W\3W\3X\3X\6X\u0401\nX\rX\16X\u0402\3X\3X\3Y\3Y\3Y\7Y\u040a"+
		"\nY\fY\16Y\u040d\13Y\3Y\3Y\3Y\7Y\u0412\nY\fY\16Y\u0415\13Y\3Y\5Y\u0418"+
		"\nY\3Z\3Z\3[\3[\3\\\3\\\7\\\u0420\n\\\f\\\16\\\u0423\13\\\3\\\3\\\3]\3"+
		"]\7]\u0429\n]\f]\16]\u042c\13]\3]\3]\3^\3^\6^\u0432\n^\r^\16^\u0433\3"+
		"^\3^\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\5_\u0443\n_\3`\3`\3`\3`\3`\3`\3"+
		"`\3`\5`\u044d\n`\3`\2\2a\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&("+
		"*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084"+
		"\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c"+
		"\u009e\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4"+
		"\u00b6\u00b8\u00ba\u00bc\u00be\2\t\4\2\67Crr\3\2\t\30\3\2Iq\5\2;;AACC"+
		"\4\2\r\r\25\25\4\2\13\13\16\16\5\2\22\22\26\26\30\30\2\u0495\2\u00cc\3"+
		"\2\2\2\4\u00ce\3\2\2\2\6\u00d2\3\2\2\2\b\u00d4\3\2\2\2\n\u00d6\3\2\2\2"+
		"\f\u00d8\3\2\2\2\16\u00dc\3\2\2\2\20\u00de\3\2\2\2\22\u00e0\3\2\2\2\24"+
		"\u00e2\3\2\2\2\26\u00e4\3\2\2\2\30\u00e6\3\2\2\2\32\u00eb\3\2\2\2\34\u00f2"+
		"\3\2\2\2\36\u00ff\3\2\2\2 \u0103\3\2\2\2\"\u0110\3\2\2\2$\u011c\3\2\2"+
		"\2&\u0122\3\2\2\2(\u012e\3\2\2\2*\u0137\3\2\2\2,\u0139\3\2\2\2.\u013e"+
		"\3\2\2\2\60\u014d\3\2\2\2\62\u014f\3\2\2\2\64\u0199\3\2\2\2\66\u019b\3"+
		"\2\2\28\u01a6\3\2\2\2:\u01cd\3\2\2\2<\u01e9\3\2\2\2>\u0208\3\2\2\2@\u020a"+
		"\3\2\2\2B\u0226\3\2\2\2D\u0228\3\2\2\2F\u0232\3\2\2\2H\u0237\3\2\2\2J"+
		"\u023c\3\2\2\2L\u0260\3\2\2\2N\u0262\3\2\2\2P\u026f\3\2\2\2R\u0281\3\2"+
		"\2\2T\u0286\3\2\2\2V\u0289\3\2\2\2X\u028c\3\2\2\2Z\u028e\3\2\2\2\\\u0298"+
		"\3\2\2\2^\u029c\3\2\2\2`\u02a0\3\2\2\2b\u02b0\3\2\2\2d\u02bc\3\2\2\2f"+
		"\u02c0\3\2\2\2h\u02c3\3\2\2\2j\u02c6\3\2\2\2l\u02d6\3\2\2\2n\u02e2\3\2"+
		"\2\2p\u02e5\3\2\2\2r\u02e7\3\2\2\2t\u02e9\3\2\2\2v\u02eb\3\2\2\2x\u02ee"+
		"\3\2\2\2z\u02f0\3\2\2\2|\u02f3\3\2\2\2~\u02f5\3\2\2\2\u0080\u02f7\3\2"+
		"\2\2\u0082\u02f9\3\2\2\2\u0084\u0302\3\2\2\2\u0086\u0305\3\2\2\2\u0088"+
		"\u0308\3\2\2\2\u008a\u030a\3\2\2\2\u008c\u030c\3\2\2\2\u008e\u030f\3\2"+
		"\2\2\u0090\u0312\3\2\2\2\u0092\u038d\3\2\2\2\u0094\u038f\3\2\2\2\u0096"+
		"\u03ae\3\2\2\2\u0098\u03b8\3\2\2\2\u009a\u03ba\3\2\2\2\u009c\u03bf\3\2"+
		"\2\2\u009e\u03cd\3\2\2\2\u00a0\u03dc\3\2\2\2\u00a2\u03de\3\2\2\2\u00a4"+
		"\u03e3\3\2\2\2\u00a6\u03e8\3\2\2\2\u00a8\u03ea\3\2\2\2\u00aa\u03ec\3\2"+
		"\2\2\u00ac\u03f5\3\2\2\2\u00ae\u03fe\3\2\2\2\u00b0\u0417\3\2\2\2\u00b2"+
		"\u0419\3\2\2\2\u00b4\u041b\3\2\2\2\u00b6\u041d\3\2\2\2\u00b8\u0426\3\2"+
		"\2\2\u00ba\u042f\3\2\2\2\u00bc\u0442\3\2\2\2\u00be\u044c\3\2\2\2\u00c0"+
		"\u00c1\5D#\2\u00c1\u00c2\7\2\2\3\u00c2\u00cd\3\2\2\2\u00c3\u00c4\5@!\2"+
		"\u00c4\u00c5\7\2\2\3\u00c5\u00cd\3\2\2\2\u00c6\u00c7\5T+\2\u00c7\u00c8"+
		"\7\2\2\3\u00c8\u00cd\3\2\2\2\u00c9\u00ca\5\u00be`\2\u00ca\u00cb\7\2\2"+
		"\3\u00cb\u00cd\3\2\2\2\u00cc\u00c0\3\2\2\2\u00cc\u00c3\3\2\2\2\u00cc\u00c6"+
		"\3\2\2\2\u00cc\u00c9\3\2\2\2\u00cd\3\3\2\2\2\u00ce\u00cf\t\2\2\2\u00cf"+
		"\5\3\2\2\2\u00d0\u00d3\5\n\6\2\u00d1\u00d3\7s\2\2\u00d2\u00d0\3\2\2\2"+
		"\u00d2\u00d1\3\2\2\2\u00d3\7\3\2\2\2\u00d4\u00d5\7\b\2\2\u00d5\t\3\2\2"+
		"\2\u00d6\u00d7\t\3\2\2\u00d7\13\3\2\2\2\u00d8\u00d9\t\4\2\2\u00d9\r\3"+
		"\2\2\2\u00da\u00dd\5\6\4\2\u00db\u00dd\5\b\5\2\u00dc\u00da\3\2\2\2\u00dc"+
		"\u00db\3\2\2\2\u00dd\17\3\2\2\2\u00de\u00df\7D\2\2\u00df\21\3\2\2\2\u00e0"+
		"\u00e1\7G\2\2\u00e1\23\3\2\2\2\u00e2\u00e3\7F\2\2\u00e3\25\3\2\2\2\u00e4"+
		"\u00e5\7E\2\2\u00e5\27\3\2\2\2\u00e6\u00e7\7\7\2\2\u00e7\31\3\2\2\2\u00e8"+
		"\u00ec\5\f\7\2\u00e9\u00ea\7H\2\2\u00ea\u00ec\5\6\4\2\u00eb\u00e8\3\2"+
		"\2\2\u00eb\u00e9\3\2\2\2\u00ec\33\3\2\2\2\u00ed\u00f3\5\20\t\2\u00ee\u00f3"+
		"\5\22\n\2\u00ef\u00f3\5\24\13\2\u00f0\u00f3\5\26\f\2\u00f1\u00f3\5\30"+
		"\r\2\u00f2\u00ed\3\2\2\2\u00f2\u00ee\3\2\2\2\u00f2\u00ef\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f2\u00f1\3\2\2\2\u00f3\35\3\2\2\2\u00f4\u0100\5\34\17"+
		"\2\u00f5\u0100\5\16\b\2\u00f6\u0100\5\32\16\2\u00f7\u00fb\7\4\2\2\u00f8"+
		"\u00fa\5\36\20\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3"+
		"\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe"+
		"\u0100\7\5\2\2\u00ff\u00f4\3\2\2\2\u00ff\u00f5\3\2\2\2\u00ff\u00f6\3\2"+
		"\2\2\u00ff\u00f7\3\2\2\2\u0100\37\3\2\2\2\u0101\u0104\5\20\t\2\u0102\u0104"+
		"\5\16\b\2\u0103\u0101\3\2\2\2\u0103\u0102\3\2\2\2\u0104!\3\2\2\2\u0105"+
		"\u0111\5\16\b\2\u0106\u0107\7\4\2\2\u0107\u0108\78\2\2\u0108\u010a\5\16"+
		"\b\2\u0109\u010b\5 \21\2\u010a\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c"+
		"\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\7\5"+
		"\2\2\u010f\u0111\3\2\2\2\u0110\u0105\3\2\2\2\u0110\u0106\3\2\2\2\u0111"+
		"#\3\2\2\2\u0112\u011d\5\34\17\2\u0113\u011d\5\16\b\2\u0114\u0118\7\4\2"+
		"\2\u0115\u0117\5\36\20\2\u0116\u0115\3\2\2\2\u0117\u011a\3\2\2\2\u0118"+
		"\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2\2\2\u011a\u0118\3\2"+
		"\2\2\u011b\u011d\7\5\2\2\u011c\u0112\3\2\2\2\u011c\u0113\3\2\2\2\u011c"+
		"\u0114\3\2\2\2\u011d%\3\2\2\2\u011e\u0123\5\32\16\2\u011f\u0120\5\32\16"+
		"\2\u0120\u0121\5$\23\2\u0121\u0123\3\2\2\2\u0122\u011e\3\2\2\2\u0122\u011f"+
		"\3\2\2\2\u0123\'\3\2\2\2\u0124\u012f\5\"\22\2\u0125\u0126\7\4\2\2\u0126"+
		"\u0128\5\"\22\2\u0127\u0129\5(\25\2\u0128\u0127\3\2\2\2\u0129\u012a\3"+
		"\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u012d\7\5\2\2\u012d\u012f\3\2\2\2\u012e\u0124\3\2\2\2\u012e\u0125\3\2"+
		"\2\2\u012f)\3\2\2\2\u0130\u0138\5\"\22\2\u0131\u0132\7\4\2\2\u0132\u0133"+
		"\79\2\2\u0133\u0134\5\"\22\2\u0134\u0135\5(\25\2\u0135\u0136\7\5\2\2\u0136"+
		"\u0138\3\2\2\2\u0137\u0130\3\2\2\2\u0137\u0131\3\2\2\2\u0138+\3\2\2\2"+
		"\u0139\u013a\7\4\2\2\u013a\u013b\5\16\b\2\u013b\u013c\5\64\33\2\u013c"+
		"\u013d\7\5\2\2\u013d-\3\2\2\2\u013e\u013f\7\4\2\2\u013f\u0140\5\16\b\2"+
		"\u0140\u0141\5(\25\2\u0141\u0142\7\5\2\2\u0142/\3\2\2\2\u0143\u014e\5"+
		"\16\b\2\u0144\u0145\7\4\2\2\u0145\u0147\5\16\b\2\u0146\u0148\5\16\b\2"+
		"\u0147\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a"+
		"\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014c\7\5\2\2\u014c\u014e\3\2\2\2\u014d"+
		"\u0143\3\2\2\2\u014d\u0144\3\2\2\2\u014e\61\3\2\2\2\u014f\u0150\7\4\2"+
		"\2\u0150\u0151\5\60\31\2\u0151\u0152\5\64\33\2\u0152\u0153\7\5\2\2\u0153"+
		"\63\3\2\2\2\u0154\u019a\5\34\17\2\u0155\u019a\5*\26\2\u0156\u0157\7\4"+
		"\2\2\u0157\u0159\5*\26\2\u0158\u015a\5\64\33\2\u0159\u0158\3\2\2\2\u015a"+
		"\u015b\3\2\2\2\u015b\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015d\3\2"+
		"\2\2\u015d\u015e\7\5\2\2\u015e\u019a\3\2\2\2\u015f\u0160\7\4\2\2\u0160"+
		"\u0161\7?\2\2\u0161\u0163\7\4\2\2\u0162\u0164\5,\27\2\u0163\u0162\3\2"+
		"\2\2\u0164\u0165\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0168\7\5\2\2\u0168\u0169\5\64\33\2\u0169\u016a\7"+
		"\5\2\2\u016a\u019a\3\2\2\2\u016b\u016c\7\4\2\2\u016c\u016d\7>\2\2\u016d"+
		"\u016f\7\4\2\2\u016e\u0170\5.\30\2\u016f\u016e\3\2\2\2\u0170\u0171\3\2"+
		"\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0173\3\2\2\2\u0173"+
		"\u0174\7\5\2\2\u0174\u0175\5\64\33\2\u0175\u0176\7\5\2\2\u0176\u019a\3"+
		"\2\2\2\u0177\u0178\7\4\2\2\u0178\u0179\7<\2\2\u0179\u017b\7\4\2\2\u017a"+
		"\u017c\5.\30\2\u017b\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017b\3\2"+
		"\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\7\5\2\2\u0180"+
		"\u0181\5\64\33\2\u0181\u0182\7\5\2\2\u0182\u019a\3\2\2\2\u0183\u0184\7"+
		"\4\2\2\u0184\u0185\7@\2\2\u0185\u0186\5\64\33\2\u0186\u0188\7\4\2\2\u0187"+
		"\u0189\5\62\32\2\u0188\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u0188\3"+
		"\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d\7\5\2\2\u018d"+
		"\u018e\7\5\2\2\u018e\u019a\3\2\2\2\u018f\u0190\7\4\2\2\u0190\u0191\7\67"+
		"\2\2\u0191\u0193\5\64\33\2\u0192\u0194\5&\24\2\u0193\u0192\3\2\2\2\u0194"+
		"\u0195\3\2\2\2\u0195\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0197\3\2"+
		"\2\2\u0197\u0198\7\5\2\2\u0198\u019a\3\2\2\2\u0199\u0154\3\2\2\2\u0199"+
		"\u0155\3\2\2\2\u0199\u0156\3\2\2\2\u0199\u015f\3\2\2\2\u0199\u016b\3\2"+
		"\2\2\u0199\u0177\3\2\2\2\u0199\u0183\3\2\2\2\u0199\u018f\3\2\2\2\u019a"+
		"\65\3\2\2\2\u019b\u019c\7\4\2\2\u019c\u019d\5\"\22\2\u019d\u01a1\5\20"+
		"\t\2\u019e\u01a0\5&\24\2\u019f\u019e\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1"+
		"\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a4\3\2\2\2\u01a3\u01a1\3\2"+
		"\2\2\u01a4\u01a5\7\5\2\2\u01a5\67\3\2\2\2\u01a6\u01a7\t\5\2\2\u01a79\3"+
		"\2\2\2\u01a8\u01a9\7\4\2\2\u01a9\u01aa\5\34\17\2\u01aa\u01ae\5(\25\2\u01ab"+
		"\u01ad\5&\24\2\u01ac\u01ab\3\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01ac\3\2"+
		"\2\2\u01ae\u01af\3\2\2\2\u01af\u01b1\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1"+
		"\u01b2\7\5\2\2\u01b2\u01ce\3\2\2\2\u01b3\u01b4\7\4\2\2\u01b4\u01b5\58"+
		"\35\2\u01b5\u01b9\5(\25\2\u01b6\u01b8\5&\24\2\u01b7\u01b6\3\2\2\2\u01b8"+
		"\u01bb\3\2\2\2\u01b9\u01b7\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bc\3\2"+
		"\2\2\u01bb\u01b9\3\2\2\2\u01bc\u01bd\7\5\2\2\u01bd\u01ce\3\2\2\2\u01be"+
		"\u01bf\7\4\2\2\u01bf\u01c1\5\"\22\2\u01c0\u01c2\5(\25\2\u01c1\u01c0\3"+
		"\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c1\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4"+
		"\u01c8\3\2\2\2\u01c5\u01c7\5&\24\2\u01c6\u01c5\3\2\2\2\u01c7\u01ca\3\2"+
		"\2\2\u01c8\u01c6\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01cb\3\2\2\2\u01ca"+
		"\u01c8\3\2\2\2\u01cb\u01cc\7\5\2\2\u01cc\u01ce\3\2\2\2\u01cd\u01a8\3\2"+
		"\2\2\u01cd\u01b3\3\2\2\2\u01cd\u01be\3\2\2\2\u01ce;\3\2\2\2\u01cf\u01ea"+
		"\5:\36\2\u01d0\u01d1\7\4\2\2\u01d1\u01d2\7B\2\2\u01d2\u01d4\7\4\2\2\u01d3"+
		"\u01d5\5\16\b\2\u01d4\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d4\3"+
		"\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01d9\7\5\2\2\u01d9"+
		"\u01da\7\4\2\2\u01da\u01dc\5\"\22\2\u01db\u01dd\5(\25\2\u01dc\u01db\3"+
		"\2\2\2\u01dd\u01de\3\2\2\2\u01de\u01dc\3\2\2\2\u01de\u01df\3\2\2\2\u01df"+
		"\u01e3\3\2\2\2\u01e0\u01e2\5&\24\2\u01e1\u01e0\3\2\2\2\u01e2\u01e5\3\2"+
		"\2\2\u01e3\u01e1\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4\u01e6\3\2\2\2\u01e5"+
		"\u01e3\3\2\2\2\u01e6\u01e7\7\5\2\2\u01e7\u01e8\7\5\2\2\u01e8\u01ea\3\2"+
		"\2\2\u01e9\u01cf\3\2\2\2\u01e9\u01d0\3\2\2\2\u01ea=\3\2\2\2\u01eb\u01ec"+
		"\7j\2\2\u01ec\u01ee\7\4\2\2\u01ed\u01ef\5\66\34\2\u01ee\u01ed\3\2\2\2"+
		"\u01ef\u01f0\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f2"+
		"\3\2\2\2\u01f2\u01f3\7\5\2\2\u01f3\u0209\3\2\2\2\u01f4\u01f5\7R\2\2\u01f5"+
		"\u01f7\7\4\2\2\u01f6\u01f8\5<\37\2\u01f7\u01f6\3\2\2\2\u01f8\u01f9\3\2"+
		"\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb"+
		"\u01fc\7\5\2\2\u01fc\u0209\3\2\2\2\u01fd\u01fe\7k\2\2\u01fe\u0209\5\30"+
		"\r\2\u01ff\u0200\7S\2\2\u0200\u0209\5\30\r\2\u0201\u0202\7N\2\2\u0202"+
		"\u0209\5\30\r\2\u0203\u0204\7o\2\2\u0204\u0209\5\30\r\2\u0205\u0206\7"+
		"[\2\2\u0206\u0209\5\30\r\2\u0207\u0209\5&\24\2\u0208\u01eb\3\2\2\2\u0208"+
		"\u01f4\3\2\2\2\u0208\u01fd\3\2\2\2\u0208\u01ff\3\2\2\2\u0208\u0201\3\2"+
		"\2\2\u0208\u0203\3\2\2\2\u0208\u0205\3\2\2\2\u0208\u0207\3\2\2\2\u0209"+
		"?\3\2\2\2\u020a\u020b\7\4\2\2\u020b\u020c\7\24\2\2\u020c\u020e\5\16\b"+
		"\2\u020d\u020f\5> \2\u020e\u020d\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u020e"+
		"\3\2\2\2\u0210\u0211\3\2\2\2\u0211\u0212\3\2\2\2\u0212\u0213\7\5\2\2\u0213"+
		"A\3\2\2\2\u0214\u0215\7n\2\2\u0215\u0217\7\4\2\2\u0216\u0218\5\16\b\2"+
		"\u0217\u0216\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u0217\3\2\2\2\u0219\u021a"+
		"\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021c\7\5\2\2\u021c\u0227\3\2\2\2\u021d"+
		"\u021e\7V\2\2\u021e\u0227\5\30\r\2\u021f\u0220\7Q\2\2\u0220\u0227\5\30"+
		"\r\2\u0221\u0222\7o\2\2\u0222\u0227\5\30\r\2\u0223\u0224\7[\2\2\u0224"+
		"\u0227\5\30\r\2\u0225\u0227\5&\24\2\u0226\u0214\3\2\2\2\u0226\u021d\3"+
		"\2\2\2\u0226\u021f\3\2\2\2\u0226\u0221\3\2\2\2\u0226\u0223\3\2\2\2\u0226"+
		"\u0225\3\2\2\2\u0227C\3\2\2\2\u0228\u0229\7\4\2\2\u0229\u022a\7\20\2\2"+
		"\u022a\u022c\5\16\b\2\u022b\u022d\5B\"\2\u022c\u022b\3\2\2\2\u022d\u022e"+
		"\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0230\3\2\2\2\u0230"+
		"\u0231\7\5\2\2\u0231E\3\2\2\2\u0232\u0233\7\4\2\2\u0233\u0234\5\16\b\2"+
		"\u0234\u0235\5\20\t\2\u0235\u0236\7\5\2\2\u0236G\3\2\2\2\u0237\u0238\7"+
		"\4\2\2\u0238\u0239\5\16\b\2\u0239\u023a\5(\25\2\u023a\u023b\7\5\2\2\u023b"+
		"I\3\2\2\2\u023c\u023d\7\4\2\2\u023d\u0241\5\16\b\2\u023e\u0240\5H%\2\u023f"+
		"\u023e\3\2\2\2\u0240\u0243\3\2\2\2\u0241\u023f\3\2\2\2\u0241\u0242\3\2"+
		"\2\2\u0242\u0244\3\2\2\2\u0243\u0241\3\2\2\2\u0244\u0245\7\5\2\2\u0245"+
		"K\3\2\2\2\u0246\u0248\7\4\2\2\u0247\u0249\5J&\2\u0248\u0247\3\2\2\2\u0249"+
		"\u024a\3\2\2\2\u024a\u0248\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u024c\3\2"+
		"\2\2\u024c\u024d\7\5\2\2\u024d\u0261\3\2\2\2\u024e\u024f\7\4\2\2\u024f"+
		"\u0250\7B\2\2\u0250\u0252\7\4\2\2\u0251\u0253\5\16\b\2\u0252\u0251\3\2"+
		"\2\2\u0253\u0254\3\2\2\2\u0254\u0252\3\2\2\2\u0254\u0255\3\2\2\2\u0255"+
		"\u0256\3\2\2\2\u0256\u0257\7\5\2\2\u0257\u0259\7\4\2\2\u0258\u025a\5J"+
		"&\2\u0259\u0258\3\2\2\2\u025a\u025b\3\2\2\2\u025b\u0259\3\2\2\2\u025b"+
		"\u025c\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u025e\7\5\2\2\u025e\u025f\7\5"+
		"\2\2\u025f\u0261\3\2\2\2\u0260\u0246\3\2\2\2\u0260\u024e\3\2\2\2\u0261"+
		"M\3\2\2\2\u0262\u0263\7\4\2\2\u0263\u0264\5\16\b\2\u0264\u0268\7\4\2\2"+
		"\u0265\u0267\5.\30\2\u0266\u0265\3\2\2\2\u0267\u026a\3\2\2\2\u0268\u0266"+
		"\3\2\2\2\u0268\u0269\3\2\2\2\u0269\u026b\3\2\2\2\u026a\u0268\3\2\2\2\u026b"+
		"\u026c\7\5\2\2\u026c\u026d\5(\25\2\u026d\u026e\7\5\2\2\u026eO\3\2\2\2"+
		"\u026f\u0270\5\16\b\2\u0270\u0274\7\4\2\2\u0271\u0273\5.\30\2\u0272\u0271"+
		"\3\2\2\2\u0273\u0276\3\2\2\2\u0274\u0272\3\2\2\2\u0274\u0275\3\2\2\2\u0275"+
		"\u0277\3\2\2\2\u0276\u0274\3\2\2\2\u0277\u0278\7\5\2\2\u0278\u0279\5("+
		"\25\2\u0279\u027a\5\64\33\2\u027aQ\3\2\2\2\u027b\u0282\5\16\b\2\u027c"+
		"\u027d\7\4\2\2\u027d\u027e\7\t\2\2\u027e\u027f\5\16\b\2\u027f\u0280\7"+
		"\5\2\2\u0280\u0282\3\2\2\2\u0281\u027b\3\2\2\2\u0281\u027c\3\2\2\2\u0282"+
		"S\3\2\2\2\u0283\u0285\5\u0092J\2\u0284\u0283\3\2\2\2\u0285\u0288\3\2\2"+
		"\2\u0286\u0284\3\2\2\2\u0286\u0287\3\2\2\2\u0287U\3\2\2\2\u0288\u0286"+
		"\3\2\2\2\u0289\u028a\7\31\2\2\u028a\u028b\5\64\33\2\u028bW\3\2\2\2\u028c"+
		"\u028d\7\32\2\2\u028dY\3\2\2\2\u028e\u028f\7\33\2\2\u028f\u0293\7\4\2"+
		"\2\u0290\u0292\5R*\2\u0291\u0290\3\2\2\2\u0292\u0295\3\2\2\2\u0293\u0291"+
		"\3\2\2\2\u0293\u0294\3\2\2\2\u0294\u0296\3\2\2\2\u0295\u0293\3\2\2\2\u0296"+
		"\u0297\7\5\2\2\u0297[\3\2\2\2\u0298\u0299\7\34\2\2\u0299\u029a\5\16\b"+
		"\2\u029a\u029b\5(\25\2\u029b]\3\2\2\2\u029c\u029d\7\35\2\2\u029d\u029e"+
		"\5\16\b\2\u029e\u029f\5L\'\2\u029f_\3\2\2\2\u02a0\u02a1\7\36\2\2\u02a1"+
		"\u02a3\7\4\2\2\u02a2\u02a4\5F$\2\u02a3\u02a2\3\2\2\2\u02a4\u02a5\3\2\2"+
		"\2\u02a5\u02a3\3\2\2\2\u02a5\u02a6\3\2\2\2\u02a6\u02a7\3\2\2\2\u02a7\u02a8"+
		"\7\5\2\2\u02a8\u02aa\7\4\2\2\u02a9\u02ab\5L\'\2\u02aa\u02a9\3\2\2\2\u02ab"+
		"\u02ac\3\2\2\2\u02ac\u02aa\3\2\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02ae\3\2"+
		"\2\2\u02ae\u02af\7\5\2\2\u02afa\3\2\2\2\u02b0\u02b1\7\37\2\2\u02b1\u02b2"+
		"\5\16\b\2\u02b2\u02b6\7\4\2\2\u02b3\u02b5\5(\25\2\u02b4\u02b3\3\2\2\2"+
		"\u02b5\u02b8\3\2\2\2\u02b6\u02b4\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7\u02b9"+
		"\3\2\2\2\u02b8\u02b6\3\2\2\2\u02b9\u02ba\7\5\2\2\u02ba\u02bb\5(\25\2\u02bb"+
		"c\3\2\2\2\u02bc\u02bd\7 \2\2\u02bd\u02be\5\16\b\2\u02be\u02bf\5\20\t\2"+
		"\u02bfe\3\2\2\2\u02c0\u02c1\7!\2\2\u02c1\u02c2\5P)\2\u02c2g\3\2\2\2\u02c3"+
		"\u02c4\7\"\2\2\u02c4\u02c5\5P)\2\u02c5i\3\2\2\2\u02c6\u02c7\7#\2\2\u02c7"+
		"\u02c9\7\4\2\2\u02c8\u02ca\5N(\2\u02c9\u02c8\3\2\2\2\u02ca\u02cb\3\2\2"+
		"\2\u02cb\u02c9\3\2\2\2\u02cb\u02cc\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cd\u02ce"+
		"\7\5\2\2\u02ce\u02d0\7\4\2\2\u02cf\u02d1\5\64\33\2\u02d0\u02cf\3\2\2\2"+
		"\u02d1\u02d2\3\2\2\2\u02d2\u02d0\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3\u02d4"+
		"\3\2\2\2\u02d4\u02d5\7\5\2\2\u02d5k\3\2\2\2\u02d6\u02d7\7$\2\2\u02d7\u02d8"+
		"\5\16\b\2\u02d8\u02dc\7\4\2\2\u02d9\u02db\5\16\b\2\u02da\u02d9\3\2\2\2"+
		"\u02db\u02de\3\2\2\2\u02dc\u02da\3\2\2\2\u02dc\u02dd\3\2\2\2\u02dd\u02df"+
		"\3\2\2\2\u02de\u02dc\3\2\2\2\u02df\u02e0\7\5\2\2\u02e0\u02e1\5(\25\2\u02e1"+
		"m\3\2\2\2\u02e2\u02e3\7%\2\2\u02e3\u02e4\5\30\r\2\u02e4o\3\2\2\2\u02e5"+
		"\u02e6\7&\2\2\u02e6q\3\2\2\2\u02e7\u02e8\7\'\2\2\u02e8s\3\2\2\2\u02e9"+
		"\u02ea\7(\2\2\u02eau\3\2\2\2\u02eb\u02ec\7)\2\2\u02ec\u02ed\5\u0098M\2"+
		"\u02edw\3\2\2\2\u02ee\u02ef\7*\2\2\u02efy\3\2\2\2\u02f0\u02f1\7+\2\2\u02f1"+
		"\u02f2\5\32\16\2\u02f2{\3\2\2\2\u02f3\u02f4\7,\2\2\u02f4}\3\2\2\2\u02f5"+
		"\u02f6\7-\2\2\u02f6\177\3\2\2\2\u02f7\u02f8\7.\2\2\u02f8\u0081\3\2\2\2"+
		"\u02f9\u02fa\7/\2\2\u02fa\u02fc\7\4\2\2\u02fb\u02fd\5\64\33\2\u02fc\u02fb"+
		"\3\2\2\2\u02fd\u02fe\3\2\2\2\u02fe\u02fc\3\2\2\2\u02fe\u02ff\3\2\2\2\u02ff"+
		"\u0300\3\2\2\2\u0300\u0301\7\5\2\2\u0301\u0083\3\2\2\2\u0302\u0303\7\60"+
		"\2\2\u0303\u0304\5\20\t\2\u0304\u0085\3\2\2\2\u0305\u0306\7\61\2\2\u0306"+
		"\u0307\5\20\t\2\u0307\u0087\3\2\2\2\u0308\u0309\7\62\2\2\u0309\u0089\3"+
		"\2\2\2\u030a\u030b\7\63\2\2\u030b\u008b\3\2\2\2\u030c\u030d\7\64\2\2\u030d"+
		"\u030e\5&\24\2\u030e\u008d\3\2\2\2\u030f\u0310\7\65\2\2\u0310\u0311\5"+
		"\16\b\2\u0311\u008f\3\2\2\2\u0312\u0313\7\66\2\2\u0313\u0314\5\u0096L"+
		"\2\u0314\u0091\3\2\2\2\u0315\u0316\7\4\2\2\u0316\u0317\5V,\2\u0317\u0318"+
		"\7\5\2\2\u0318\u038e\3\2\2\2\u0319\u031a\7\4\2\2\u031a\u031b\5X-\2\u031b"+
		"\u031c\7\5\2\2\u031c\u038e\3\2\2\2\u031d\u031e\7\4\2\2\u031e\u031f\5Z"+
		".\2\u031f\u0320\7\5\2\2\u0320\u038e\3\2\2\2\u0321\u0322\7\4\2\2\u0322"+
		"\u0323\5\\/\2\u0323\u0324\7\5\2\2\u0324\u038e\3\2\2\2\u0325\u0326\7\4"+
		"\2\2\u0326\u0327\5^\60\2\u0327\u0328\7\5\2\2\u0328\u038e\3\2\2\2\u0329"+
		"\u032a\7\4\2\2\u032a\u032b\5`\61\2\u032b\u032c\7\5\2\2\u032c\u038e\3\2"+
		"\2\2\u032d\u032e\7\4\2\2\u032e\u032f\5b\62\2\u032f\u0330\7\5\2\2\u0330"+
		"\u038e\3\2\2\2\u0331\u0332\7\4\2\2\u0332\u0333\5d\63\2\u0333\u0334\7\5"+
		"\2\2\u0334\u038e\3\2\2\2\u0335\u0336\7\4\2\2\u0336\u0337\5f\64\2\u0337"+
		"\u0338\7\5\2\2\u0338\u038e\3\2\2\2\u0339\u033a\7\4\2\2\u033a\u033b\5h"+
		"\65\2\u033b\u033c\7\5\2\2\u033c\u038e\3\2\2\2\u033d\u033e\7\4\2\2\u033e"+
		"\u033f\5j\66\2\u033f\u0340\7\5\2\2\u0340\u038e\3\2\2\2\u0341\u0342\7\4"+
		"\2\2\u0342\u0343\5l\67\2\u0343\u0344\7\5\2\2\u0344\u038e\3\2\2\2\u0345"+
		"\u0346\7\4\2\2\u0346\u0347\5n8\2\u0347\u0348\7\5\2\2\u0348\u038e\3\2\2"+
		"\2\u0349\u034a\7\4\2\2\u034a\u034b\5p9\2\u034b\u034c\7\5\2\2\u034c\u038e"+
		"\3\2\2\2\u034d\u034e\7\4\2\2\u034e\u034f\5r:\2\u034f\u0350\7\5\2\2\u0350"+
		"\u038e\3\2\2\2\u0351\u0352\7\4\2\2\u0352\u0353\5t;\2\u0353\u0354\7\5\2"+
		"\2\u0354\u038e\3\2\2\2\u0355\u0356\7\4\2\2\u0356\u0357\5v<\2\u0357\u0358"+
		"\7\5\2\2\u0358\u038e\3\2\2\2\u0359\u035a\7\4\2\2\u035a\u035b\5x=\2\u035b"+
		"\u035c\7\5\2\2\u035c\u038e\3\2\2\2\u035d\u035e\7\4\2\2\u035e\u035f\5z"+
		">\2\u035f\u0360\7\5\2\2\u0360\u038e\3\2\2\2\u0361\u0362\7\4\2\2\u0362"+
		"\u0363\5|?\2\u0363\u0364\7\5\2\2\u0364\u038e\3\2\2\2\u0365\u0366\7\4\2"+
		"\2\u0366\u0367\5~@\2\u0367\u0368\7\5\2\2\u0368\u038e\3\2\2\2\u0369\u036a"+
		"\7\4\2\2\u036a\u036b\5\u0080A\2\u036b\u036c\7\5\2\2\u036c\u038e\3\2\2"+
		"\2\u036d\u036e\7\4\2\2\u036e\u036f\5\u0082B\2\u036f\u0370\7\5\2\2\u0370"+
		"\u038e\3\2\2\2\u0371\u0372\7\4\2\2\u0372\u0373\5\u0084C\2\u0373\u0374"+
		"\7\5\2\2\u0374\u038e\3\2\2\2\u0375\u0376\7\4\2\2\u0376\u0377\5\u0086D"+
		"\2\u0377\u0378\7\5\2\2\u0378\u038e\3\2\2\2\u0379\u037a\7\4\2\2\u037a\u037b"+
		"\5\u0088E\2\u037b\u037c\7\5\2\2\u037c\u038e\3\2\2\2\u037d\u037e\7\4\2"+
		"\2\u037e\u037f\5\u008aF\2\u037f\u0380\7\5\2\2\u0380\u038e\3\2\2\2\u0381"+
		"\u0382\7\4\2\2\u0382\u0383\5\u008cG\2\u0383\u0384\7\5\2\2\u0384\u038e"+
		"\3\2\2\2\u0385\u0386\7\4\2\2\u0386\u0387\5\u008eH\2\u0387\u0388\7\5\2"+
		"\2\u0388\u038e\3\2\2\2\u0389\u038a\7\4\2\2\u038a\u038b\5\u0090I\2\u038b"+
		"\u038c\7\5\2\2\u038c\u038e\3\2\2\2\u038d\u0315\3\2\2\2\u038d\u0319\3\2"+
		"\2\2\u038d\u031d\3\2\2\2\u038d\u0321\3\2\2\2\u038d\u0325\3\2\2\2\u038d"+
		"\u0329\3\2\2\2\u038d\u032d\3\2\2\2\u038d\u0331\3\2\2\2\u038d\u0335\3\2"+
		"\2\2\u038d\u0339\3\2\2\2\u038d\u033d\3\2\2\2\u038d\u0341\3\2\2\2\u038d"+
		"\u0345\3\2\2\2\u038d\u0349\3\2\2\2\u038d\u034d\3\2\2\2\u038d\u0351\3\2"+
		"\2\2\u038d\u0355\3\2\2\2\u038d\u0359\3\2\2\2\u038d\u035d\3\2\2\2\u038d"+
		"\u0361\3\2\2\2\u038d\u0365\3\2\2\2\u038d\u0369\3\2\2\2\u038d\u036d\3\2"+
		"\2\2\u038d\u0371\3\2\2\2\u038d\u0375\3\2\2\2\u038d\u0379\3\2\2\2\u038d"+
		"\u037d\3\2\2\2\u038d\u0381\3\2\2\2\u038d\u0385\3\2\2\2\u038d\u0389\3\2"+
		"\2\2\u038e\u0093\3\2\2\2\u038f\u0390\t\6\2\2\u0390\u0095\3\2\2\2\u0391"+
		"\u0392\7O\2\2\u0392\u03af\5\30\r\2\u0393\u0394\7T\2\2\u0394\u03af\5\u0094"+
		"K\2\u0395\u0396\7U\2\2\u0396\u03af\5\u0094K\2\u0397\u0398\7]\2\2\u0398"+
		"\u03af\5\u0094K\2\u0399\u039a\7^\2\2\u039a\u03af\5\u0094K\2\u039b\u039c"+
		"\7_\2\2\u039c\u03af\5\u0094K\2\u039d\u039e\7`\2\2\u039e\u03af\5\u0094"+
		"K\2\u039f\u03a0\7a\2\2\u03a0\u03af\5\u0094K\2\u03a1\u03a2\7b\2\2\u03a2"+
		"\u03af\5\u0094K\2\u03a3\u03a4\7c\2\2\u03a4\u03af\5\u0094K\2\u03a5\u03a6"+
		"\7d\2\2\u03a6\u03af\5\20\t\2\u03a7\u03a8\7f\2\2\u03a8\u03af\5\30\r\2\u03a9"+
		"\u03aa\7g\2\2\u03aa\u03af\5\20\t\2\u03ab\u03ac\7p\2\2\u03ac\u03af\5\20"+
		"\t\2\u03ad\u03af\5&\24\2\u03ae\u0391\3\2\2\2\u03ae\u0393\3\2\2\2\u03ae"+
		"\u0395\3\2\2\2\u03ae\u0397\3\2\2\2\u03ae\u0399\3\2\2\2\u03ae\u039b\3\2"+
		"\2\2\u03ae\u039d\3\2\2\2\u03ae\u039f\3\2\2\2\u03ae\u03a1\3\2\2\2\u03ae"+
		"\u03a3\3\2\2\2\u03ae\u03a5\3\2\2\2\u03ae\u03a7\3\2\2\2\u03ae\u03a9\3\2"+
		"\2\2\u03ae\u03ab\3\2\2\2\u03ae\u03ad\3\2\2\2\u03af\u0097\3\2\2\2\u03b0"+
		"\u03b9\7I\2\2\u03b1\u03b9\7J\2\2\u03b2\u03b9\7K\2\2\u03b3\u03b9\7P\2\2"+
		"\u03b4\u03b9\7Z\2\2\u03b5\u03b9\7e\2\2\u03b6\u03b9\7q\2\2\u03b7\u03b9"+
		"\5\32\16\2\u03b8\u03b0\3\2\2\2\u03b8\u03b1\3\2\2\2\u03b8\u03b2\3\2\2\2"+
		"\u03b8\u03b3\3\2\2\2\u03b8\u03b4\3\2\2\2\u03b8\u03b5\3\2\2\2\u03b8\u03b6"+
		"\3\2\2\2\u03b8\u03b7\3\2\2\2\u03b9\u0099\3\2\2\2\u03ba\u03bb\t\7\2\2\u03bb"+
		"\u009b\3\2\2\2\u03bc\u03c0\7\21\2\2\u03bd\u03c0\7\17\2\2\u03be\u03c0\5"+
		"\36\20\2\u03bf\u03bc\3\2\2\2\u03bf\u03bd\3\2\2\2\u03bf\u03be\3\2\2\2\u03c0"+
		"\u009d\3\2\2\2\u03c1\u03c2\7\4\2\2\u03c2\u03c3\5f\64\2\u03c3\u03c4\7\5"+
		"\2\2\u03c4\u03ce\3\2\2\2\u03c5\u03c6\7\4\2\2\u03c6\u03c7\5h\65\2\u03c7"+
		"\u03c8\7\5\2\2\u03c8\u03ce\3\2\2\2\u03c9\u03ca\7\4\2\2\u03ca\u03cb\5j"+
		"\66\2\u03cb\u03cc\7\5\2\2\u03cc\u03ce\3\2\2\2\u03cd\u03c1\3\2\2\2\u03cd"+
		"\u03c5\3\2\2\2\u03cd\u03c9\3\2\2\2\u03ce\u009f\3\2\2\2\u03cf\u03d0\7J"+
		"\2\2\u03d0\u03dd\5\20\t\2\u03d1\u03d2\7K\2\2\u03d2\u03dd\5\30\r\2\u03d3"+
		"\u03d4\7P\2\2\u03d4\u03dd\5\u009aN\2\u03d5\u03d6\7Z\2\2\u03d6\u03dd\5"+
		"\30\r\2\u03d7\u03d8\7e\2\2\u03d8\u03dd\5\u009cO\2\u03d9\u03da\7q\2\2\u03da"+
		"\u03dd\5\30\r\2\u03db\u03dd\5&\24\2\u03dc\u03cf\3\2\2\2\u03dc\u03d1\3"+
		"\2\2\2\u03dc\u03d3\3\2\2\2\u03dc\u03d5\3\2\2\2\u03dc\u03d7\3\2\2\2\u03dc"+
		"\u03d9\3\2\2\2\u03dc\u03db\3\2\2\2\u03dd\u00a1\3\2\2\2\u03de\u03df\7\4"+
		"\2\2\u03df\u03e0\5\64\33\2\u03e0\u03e1\5\64\33\2\u03e1\u03e2\7\5\2\2\u03e2"+
		"\u00a3\3\2\2\2\u03e3\u03e4\7\4\2\2\u03e4\u03e5\5\16\b\2\u03e5\u03e6\5"+
		"\u0094K\2\u03e6\u03e7\7\5\2\2\u03e7\u00a5\3\2\2\2\u03e8\u03e9\t\b\2\2"+
		"\u03e9\u00a7\3\2\2\2\u03ea\u03eb\5\30\r\2\u03eb\u00a9\3\2\2\2\u03ec\u03f0"+
		"\7\4\2\2\u03ed\u03ef\5\64\33\2\u03ee\u03ed\3\2\2\2\u03ef\u03f2\3\2\2\2"+
		"\u03f0\u03ee\3\2\2\2\u03f0\u03f1\3\2\2\2\u03f1\u03f3\3\2\2\2\u03f2\u03f0"+
		"\3\2\2\2\u03f3\u03f4\7\5\2\2\u03f4\u00ab\3\2\2\2\u03f5\u03f9\7\4\2\2\u03f6"+
		"\u03f8\5\u00a4S\2\u03f7\u03f6\3\2\2\2\u03f8\u03fb\3\2\2\2\u03f9\u03f7"+
		"\3\2\2\2\u03f9\u03fa\3\2\2\2\u03fa\u03fc\3\2\2\2\u03fb\u03f9\3\2\2\2\u03fc"+
		"\u03fd\7\5\2\2\u03fd\u00ad\3\2\2\2\u03fe\u0400\7\4\2\2\u03ff\u0401\5\u00a0"+
		"Q\2\u0400\u03ff\3\2\2\2\u0401\u0402\3\2\2\2\u0402\u0400\3\2\2\2\u0402"+
		"\u0403\3\2\2\2\u0403\u0404\3\2\2\2\u0404\u0405\7\5\2\2\u0405\u00af\3\2"+
		"\2\2\u0406\u0407\7\4\2\2\u0407\u040b\7r\2\2\u0408\u040a\5\u009eP\2\u0409"+
		"\u0408\3\2\2\2\u040a\u040d\3\2\2\2\u040b\u0409\3\2\2\2\u040b\u040c\3\2"+
		"\2\2\u040c\u040e\3\2\2\2\u040d\u040b\3\2\2\2\u040e\u0418\7\5\2\2\u040f"+
		"\u0413\7\4\2\2\u0410\u0412\5\u009eP\2\u0411\u0410\3\2\2\2\u0412\u0415"+
		"\3\2\2\2\u0413\u0411\3\2\2\2\u0413\u0414\3\2\2\2\u0414\u0416\3\2\2\2\u0415"+
		"\u0413\3\2\2\2\u0416\u0418\7\5\2\2\u0417\u0406\3\2\2\2\u0417\u040f\3\2"+
		"\2\2\u0418\u00b1\3\2\2\2\u0419\u041a\5$\23\2\u041a\u00b3\3\2\2\2\u041b"+
		"\u041c\5\36\20\2\u041c\u00b5\3\2\2\2\u041d\u0421\7\4\2\2\u041e\u0420\5"+
		"\16\b\2\u041f\u041e\3\2\2\2\u0420\u0423\3\2\2\2\u0421\u041f\3\2\2\2\u0421"+
		"\u0422\3\2\2\2\u0422\u0424\3\2\2\2\u0423\u0421\3\2\2\2\u0424\u0425\7\5"+
		"\2\2\u0425\u00b7\3\2\2\2\u0426\u042a\7\4\2\2\u0427\u0429\5\16\b\2\u0428"+
		"\u0427\3\2\2\2\u0429\u042c\3\2\2\2\u042a\u0428\3\2\2\2\u042a\u042b\3\2"+
		"\2\2\u042b\u042d\3\2\2\2\u042c\u042a\3\2\2\2\u042d\u042e\7\5\2\2\u042e"+
		"\u00b9\3\2\2\2\u042f\u0431\7\4\2\2\u0430\u0432\5\u00a2R\2\u0431\u0430"+
		"\3\2\2\2\u0432\u0433\3\2\2\2\u0433\u0431\3\2\2\2\u0433\u0434\3\2\2\2\u0434"+
		"\u0435\3\2\2\2\u0435\u0436\7\5\2\2\u0436\u00bb\3\2\2\2\u0437\u0443\5\u00a6"+
		"T\2\u0438\u0443\5\u00a8U\2\u0439\u0443\5\u00aaV\2\u043a\u0443\5\u00ac"+
		"W\2\u043b\u0443\5\u00aeX\2\u043c\u0443\5\u00b0Y\2\u043d\u0443\5\u00b2"+
		"Z\2\u043e\u0443\5\u00b4[\2\u043f\u0443\5\u00b6\\\2\u0440\u0443\5\u00b8"+
		"]\2\u0441\u0443\5\u00ba^\2\u0442\u0437\3\2\2\2\u0442\u0438\3\2\2\2\u0442"+
		"\u0439\3\2\2\2\u0442\u043a\3\2\2\2\u0442\u043b\3\2\2\2\u0442\u043c\3\2"+
		"\2\2\u0442\u043d\3\2\2\2\u0442\u043e\3\2\2\2\u0442\u043f\3\2\2\2\u0442"+
		"\u0440\3\2\2\2\u0442\u0441\3\2\2\2\u0443\u00bd\3\2\2\2\u0444\u044d\7\23"+
		"\2\2\u0445\u044d\5\u00bc_\2\u0446\u044d\7\27\2\2\u0447\u0448\7\4\2\2\u0448"+
		"\u0449\7\f\2\2\u0449\u044a\5\30\r\2\u044a\u044b\7\5\2\2\u044b\u044d\3"+
		"\2\2\2\u044c\u0444\3\2\2\2\u044c\u0445\3\2\2\2\u044c\u0446\3\2\2\2\u044c"+
		"\u0447\3\2\2\2\u044d\u00bf\3\2\2\2N\u00cc\u00d2\u00dc\u00eb\u00f2\u00fb"+
		"\u00ff\u0103\u010c\u0110\u0118\u011c\u0122\u012a\u012e\u0137\u0149\u014d"+
		"\u015b\u0165\u0171\u017d\u018a\u0195\u0199\u01a1\u01ae\u01b9\u01c3\u01c8"+
		"\u01cd\u01d6\u01de\u01e3\u01e9\u01f0\u01f9\u0208\u0210\u0219\u0226\u022e"+
		"\u0241\u024a\u0254\u025b\u0260\u0268\u0274\u0281\u0286\u0293\u02a5\u02ac"+
		"\u02b6\u02cb\u02d2\u02dc\u02fe\u038d\u03ae\u03b8\u03bf\u03cd\u03dc\u03f0"+
		"\u03f9\u0402\u040b\u0413\u0417\u0421\u042a\u0433\u0442\u044c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}