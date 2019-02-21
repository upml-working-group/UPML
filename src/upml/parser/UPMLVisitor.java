// Generated from UPML.g4 by ANTLR 4.7
package upml.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link UPMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface UPMLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link UPMLParser#input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(UPMLParser.InputContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#var_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_stmt(UPMLParser.Var_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#dec_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec_list(UPMLParser.Dec_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#node_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode_dec(UPMLParser.Node_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#dim_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDim_list(UPMLParser.Dim_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#data_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitData_stmt(UPMLParser.Data_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#model_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModel_stmt(UPMLParser.Model_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#relations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelations(UPMLParser.RelationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#relation_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation_list(UPMLParser.Relation_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(UPMLParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#for_loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_loop(UPMLParser.For_loopContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#counter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCounter(UPMLParser.CounterContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(UPMLParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#determ_relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeterm_relation(UPMLParser.Determ_relationContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#stoch_relation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStoch_relation(UPMLParser.Stoch_relationContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#truncated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruncated(UPMLParser.TruncatedContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(UPMLParser.IntervalContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(UPMLParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#range_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange_list(UPMLParser.Range_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#range_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange_element(UPMLParser.Range_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(UPMLParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#expression_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list(UPMLParser.Expression_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(UPMLParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#distribution}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistribution(UPMLParser.DistributionContext ctx);
	/**
	 * Visit a parse tree produced by {@link UPMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(UPMLParser.ExpressionContext ctx);
}