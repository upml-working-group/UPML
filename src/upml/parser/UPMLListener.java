// Generated from UPML.g4 by ANTLR 4.7
package upml.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link UPMLParser}.
 */
public interface UPMLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link UPMLParser#input}.
	 * @param ctx the parse tree
	 */
	void enterInput(UPMLParser.InputContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#input}.
	 * @param ctx the parse tree
	 */
	void exitInput(UPMLParser.InputContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#var_stmt}.
	 * @param ctx the parse tree
	 */
	void enterVar_stmt(UPMLParser.Var_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#var_stmt}.
	 * @param ctx the parse tree
	 */
	void exitVar_stmt(UPMLParser.Var_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#dec_list}.
	 * @param ctx the parse tree
	 */
	void enterDec_list(UPMLParser.Dec_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#dec_list}.
	 * @param ctx the parse tree
	 */
	void exitDec_list(UPMLParser.Dec_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#node_dec}.
	 * @param ctx the parse tree
	 */
	void enterNode_dec(UPMLParser.Node_decContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#node_dec}.
	 * @param ctx the parse tree
	 */
	void exitNode_dec(UPMLParser.Node_decContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#dim_list}.
	 * @param ctx the parse tree
	 */
	void enterDim_list(UPMLParser.Dim_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#dim_list}.
	 * @param ctx the parse tree
	 */
	void exitDim_list(UPMLParser.Dim_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#data_stmt}.
	 * @param ctx the parse tree
	 */
	void enterData_stmt(UPMLParser.Data_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#data_stmt}.
	 * @param ctx the parse tree
	 */
	void exitData_stmt(UPMLParser.Data_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#model_stmt}.
	 * @param ctx the parse tree
	 */
	void enterModel_stmt(UPMLParser.Model_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#model_stmt}.
	 * @param ctx the parse tree
	 */
	void exitModel_stmt(UPMLParser.Model_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#relations}.
	 * @param ctx the parse tree
	 */
	void enterRelations(UPMLParser.RelationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#relations}.
	 * @param ctx the parse tree
	 */
	void exitRelations(UPMLParser.RelationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#relation_list}.
	 * @param ctx the parse tree
	 */
	void enterRelation_list(UPMLParser.Relation_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#relation_list}.
	 * @param ctx the parse tree
	 */
	void exitRelation_list(UPMLParser.Relation_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(UPMLParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(UPMLParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void enterFor_loop(UPMLParser.For_loopContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#for_loop}.
	 * @param ctx the parse tree
	 */
	void exitFor_loop(UPMLParser.For_loopContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#counter}.
	 * @param ctx the parse tree
	 */
	void enterCounter(UPMLParser.CounterContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#counter}.
	 * @param ctx the parse tree
	 */
	void exitCounter(UPMLParser.CounterContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(UPMLParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(UPMLParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#determ_relation}.
	 * @param ctx the parse tree
	 */
	void enterDeterm_relation(UPMLParser.Determ_relationContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#determ_relation}.
	 * @param ctx the parse tree
	 */
	void exitDeterm_relation(UPMLParser.Determ_relationContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#stoch_relation}.
	 * @param ctx the parse tree
	 */
	void enterStoch_relation(UPMLParser.Stoch_relationContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#stoch_relation}.
	 * @param ctx the parse tree
	 */
	void exitStoch_relation(UPMLParser.Stoch_relationContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#truncated}.
	 * @param ctx the parse tree
	 */
	void enterTruncated(UPMLParser.TruncatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#truncated}.
	 * @param ctx the parse tree
	 */
	void exitTruncated(UPMLParser.TruncatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#interval}.
	 * @param ctx the parse tree
	 */
	void enterInterval(UPMLParser.IntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#interval}.
	 * @param ctx the parse tree
	 */
	void exitInterval(UPMLParser.IntervalContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(UPMLParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(UPMLParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#range_list}.
	 * @param ctx the parse tree
	 */
	void enterRange_list(UPMLParser.Range_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#range_list}.
	 * @param ctx the parse tree
	 */
	void exitRange_list(UPMLParser.Range_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#range_element}.
	 * @param ctx the parse tree
	 */
	void enterRange_element(UPMLParser.Range_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#range_element}.
	 * @param ctx the parse tree
	 */
	void exitRange_element(UPMLParser.Range_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(UPMLParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(UPMLParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(UPMLParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(UPMLParser.Expression_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(UPMLParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(UPMLParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#distribution}.
	 * @param ctx the parse tree
	 */
	void enterDistribution(UPMLParser.DistributionContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#distribution}.
	 * @param ctx the parse tree
	 */
	void exitDistribution(UPMLParser.DistributionContext ctx);
	/**
	 * Enter a parse tree produced by {@link UPMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(UPMLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link UPMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(UPMLParser.ExpressionContext ctx);
}