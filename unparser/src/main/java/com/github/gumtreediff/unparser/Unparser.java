package com.github.gumtreediff.unparser;

import com.github.gumtreediff.tree.ITree;

import java.util.List;

/**
 * Created by @author @pillravi on 11/17/16.
 *
 * Originally created 3/8/16 by @pillravi as part of PANDA (Patterns of Arbitrary Nature Difference Applier).
 *
 * This class traverses a GumTree ITree and turns it back into code. It is not
 * a pretty printer, and therefore requires the IntelliJ code formatter (or
 * other code formatter) to be run on its output.
 */
public final class Unparser {

    public static String unparse(ITree treeToModify) {
        String result;
        StringBuilder sb = new StringBuilder();

        prettyPrintNode(treeToModify, sb);

        result = sb.toString();

        return result;
    } /* end unparse */

    private static void prettyPrintNode(ITree node, StringBuilder sb) {
        int typeInteger = node.getType();

        NodeTypeEnum whichType = NodeTypeEnum.values()[typeInteger];

        switch (whichType) {
            case ANONYMOUS_CLASS_DECLARATION:
                unparseAnonymousClassDeclaration(node, sb);
                break;
            case ARRAY_ACCESS:
                unparseArrayAccess(node, sb);
                break;
            case ARRAY_CREATION:
                unparseArrayCreation(node, sb);
                break;
            case ARRAY_INITIALIZER:
                unparseArrayInitializer(node, sb);
                break;
            case ARRAY_TYPE:
                unparseArrayType(node, sb);
                break;
            case ASSERT_STATEMENT:
                unparseAssertStatement(node, sb);
                break;
            case ASSIGNMENT:
                unparseAssignment(node, sb);
                break;
            case BLOCK:
                unparseBlock(node, sb);
                break;
            case BOOLEAN_LITERAL:
                unparseBooleanLiteral(node, sb);
                break;
            case BREAK_STATEMENT:
                unparseBreakStatement(node, sb);
                break;
            case CAST_EXPRESSION:
                unparseCastExpression(node, sb);
                break;
            case CATCH_CLAUSE:
                unparseCatchClause(node, sb);
                break;
            case CHARACTER_LITERAL:
                unparseCharacterLiteral(node, sb);
                break;
            case CLASS_INSTANCE_CREATION:
                unparseClassInstanceCreation(node, sb);
                break;
            case COMPILATION_UNIT:
                unparseCompilationUnit(node, sb);
                break;
            case CONDITIONAL_EXPRESSION:
                unparseConditionalExpression(node, sb);
                break;
            case CONSTRUCTOR_INVOCATION:
                unparseConstructorInvocation(node, sb);
                break;
            case CONTINUE_STATEMENT:
                unparseContinueStatement(node, sb);
                break;
            case DO_STATEMENT:
                unparseDoStatement(node, sb);
                break;
            case EMPTY_STATEMENT:
                unparseEmptyStatement(node, sb);
                break;
            case EXPRESSION_STATEMENT:
                unparseExpressionStatement(node, sb);
                break;
            case FIELD_ACCESS:
                unparseFieldAccess(node, sb);
                break;
            case FIELD_DECLARATION:
                unparseFieldDeclaration(node, sb);
                break;
            case FOR_STATEMENT:
                unparseForStatement(node, sb);
                break;
            case IF_STATEMENT:
                unparseIfStatement(node, sb);
                break;
            case IMPORT_DECLARATION:
                unparseImportDeclaration(node, sb);
                break;
            case INFIX_EXPRESSION:
                unparseInfixExpression(node, sb);
                break;
            case INITIALIZER:
                unparseInitializer(node, sb);
                break;
            case JAVADOC:
                unparseJavadoc(node, sb);
                break;
            case LABELED_STATEMENT:
                unparseLabeledStatement(node, sb);
                break;
            case METHOD_DECLARATION:
                unparseMethodDeclaration(node, sb);
                break;
            case METHOD_INVOCATION:
                unparseMethodInvocation(node, sb);
                break;
            case NULL_LITERAL:
                unparseNullLiteral(node, sb);
                break;
            case NUMBER_LITERAL:
                unparseNumberLiteral(node, sb);
                break;
            case PACKAGE_DECLARATION:
                unparsePackageDeclaration(node, sb);
                break;
            case PARENTHESIZED_EXPRESSION:
                unparseParenthesizedExpression(node, sb);
                break;
            case POSTFIX_EXPRESSION:
                unparsePostfixExpression(node, sb);
                break;
            case PREFIX_EXPRESSION:
                unparsePrefixExpression(node, sb);
                break;
            case PRIMITIVE_TYPE:
                unparsePrimitiveType(node, sb);
                break;
            case QUALIFIED_NAME:
                unparseQualifiedName(node, sb);
                break;
            case RETURN_STATEMENT:
                unparseReturnStatement(node, sb);
                break;
            case SIMPLE_NAME:
                unparseSimpleName(node, sb);
                break;
            case SIMPLE_TYPE:
                unparseSimpleType(node, sb);
                break;
            case SINGLE_VARIABLE_DECLARATION:
                unparseSingleVariableDeclaration(node, sb);
                break;
            case STRING_LITERAL:
                unparseStringLiteral(node, sb);
                break;
            case SUPER_CONSTRUCTOR_INVOCATION:
                unparseSuperConstructorInvocation(node, sb);
                break;
            case SUPER_FIELD_ACCESS:
                unparseSuperFieldAccess(node, sb);
                break;
            case SUPER_METHOD_INVOCATION:
                unparseSuperMethodInvocation(node, sb);
                break;
            case SWITCH_CASE:
                unparseSwitchCase(node, sb);
                break;
            case SWITCH_STATEMENT:
                unparseSwitchStatement(node, sb);
                break;
            case SYNCHRONIZED_STATEMENT:
                unparseSynchronizedStatement(node, sb);
                break;
            case THIS_EXPRESSION:
                unparseThisExpression(node, sb);
                break;
            case THROW_STATEMENT:
                unparseThrowStatement(node, sb);
                break;
            case TRY_STATEMENT:
                unparseTryStatement(node, sb);
                break;
            case TYPE_DECLARATION:
                unparseTypeDeclaration(node, sb);
                break;
            case TYPE_DECLARATION_STATEMENT:
                unparseTypeDeclarationStatement(node, sb);
                break;
            case TYPE_LITERAL:
                unparseTypeLiteral(node, sb);
                break;
            case VARIABLE_DECLARATION_EXPRESSION:
                unparseVariableDeclarationExpression(node, sb);
                break;
            case VARIABLE_DECLARATION_FRAGMENT:
                unparseVariableDeclarationFragment(node, sb);
                break;
            case VARIABLE_DECLARATION_STATEMENT:
                unparseVariableDeclarationStatement(node, sb);
                break;
            case WHILE_STATEMENT:
                unparseWhileStatement(node, sb);
                break;
            case INSTANCEOF_EXPRESSION:
                unparseInstanceofExpression(node, sb);
                break;
            case LINE_COMMENT:
                unparseLineComment(node, sb);
                break;
            case BLOCK_COMMENT:
                unparseBlockComment(node, sb);
                break;
            case TAG_ELEMENT:
                unparseTagElement(node, sb);
                break;
            case TEXT_ELEMENT:
                unparseTextElement(node, sb);
                break;
            case MEMBER_REF:
                unparseMemberRef(node, sb);
                break;
            case METHOD_REF:
                unparseMethodRef(node, sb);
                break;
            case METHOD_REF_PARAMETER:
                unparseMethodRefParameter(node, sb);
                break;
            case ENHANCED_FOR_STATEMENT:
                unparseEnhancedForStatement(node, sb);
                break;
            case ENUM_DECLARATION:
                unparseEnumDeclaration(node, sb);
                break;
            case ENUM_CONSTANT_DECLARATION:
                unparseEnumConstantDeclaration(node, sb);
                break;
            case TYPE_PARAMETER:
                unparseTypeParameter(node, sb);
                break;
            case PARAMETERIZED_TYPE:
                unparseParameterizedType(node, sb);
                break;
            case QUALIFIED_TYPE:
                unparseQualifiedType(node, sb);
                break;
            case WILDCARD_TYPE:
                unparseWildcardType(node, sb);
                break;
            case NORMAL_ANNOTATION:
                unparseNormalAnnotation(node, sb);
                break;
            case MARKER_ANNOTATION:
                unparseMarkerAnnotation(node, sb);
                break;
            case SINGLE_MEMBER_ANNOTATION:
                unparseSingleMemberAnnotation(node, sb);
                break;
            case MEMBER_VALUE_PAIR:
                unparseMemberValuePair(node, sb);
                break;
            case ANNOTATION_TYPE_DECLARATION:
                unparseAnnotationTypeDeclaration(node, sb);
                break;
            case ANNOTATION_TYPE_MEMBER_DECLARATION:
                unparseAnnotationTypeMemberDeclaration(node, sb);
                break;
            case MODIFIER:
                unparseModifier(node, sb);
                break;
            case UNION_TYPE:
                unparseUnionType(node, sb);
                break;
            case DIMENSION:
                unparseDimension(node, sb);
                break;
            case LAMBDA_EXPRESSION:
                unparseLambdaExpression(node, sb);
                break;
            case INTERSECTION_TYPE:
                unparseIntersectionType(node, sb);
                break;
            case NAME_QUALIFIED_TYPE:
                unparseNameQualifiedType(node, sb);
                break;
            case CREATION_REFERENCE:
                unparseCreationReference(node, sb);
                break;
            case EXPRESSION_METHOD_REFERENCE:
                unparseExpressionMethodReference(node, sb);
                break;
            case SUPER_METHOD_REFERENCE:
                unparseSuperMethodReference(node, sb);
                break;
            case TYPE_METHOD_REFERENCE:
                unparseTypeMethodReference(node, sb);
                break;
            default:
                /* Other than if somehow a type 0 gets through (which would be
                 * a "dummy type," this will never happen; it would crash on the
                 * array access above before this ever happened. */
                sb.append("UNKNOWN_TYPE_ENCOUNTERED");
        }
    } /* end prettyPrintNode */

    private static void unparseAnonymousClassDeclaration(ITree node,
                                                         StringBuilder sb) {

    }

    private static void unparseArrayAccess(ITree node, StringBuilder sb) {

    }

    private static void unparseArrayCreation(ITree node, StringBuilder sb) {

    }

    private static void unparseArrayInitializer(ITree node, StringBuilder sb) {

    }

    private static void unparseArrayType(ITree node, StringBuilder sb) {
//        List<ITree> children = node.getChildren();
//
//        for (ITree child : children) {
//            prettyPrintNode(child, sb);
//        }
        /* TODO: check if it is ever necessary to print each child */
        sb.append(node.getLabel());
    }

    private static void unparseAssertStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseAssignment(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();
        boolean equalsPrinted = false;

        for (ITree child : children) {
            prettyPrintNode(child, sb);

            /* After the first child, print an equals sign */
            if (!equalsPrinted) {
                sb.append("= ");
                equalsPrinted = true;
            }
        }

    }

    private static void unparseBlock(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append("{\n");
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append("}\n");
    }

    private static void unparseBooleanLiteral(ITree node, StringBuilder sb) {

    }

    private static void unparseBreakStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseCastExpression(ITree node, StringBuilder sb) {

    }

    private static void unparseCatchClause(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();
        boolean endCatchObjParenPrinted = false;

        sb.append("catch (");
        for (ITree child : children) {
            prettyPrintNode(child, sb);

            if (!endCatchObjParenPrinted) {
                sb.append(") ");
                endCatchObjParenPrinted = true;
            }
        }
    }

    private static void unparseCharacterLiteral(ITree node, StringBuilder sb) {

    }

    private static void unparseClassInstanceCreation(ITree node,
                                                     StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append("new ");
        /* print the name of the new object's class */
        prettyPrintNode(children.get(0), sb);

        printArgList(1, children, sb);
    }

    private static void unparseCompilationUnit(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
    }

    private static void unparseConditionalExpression(ITree node,
                                                     StringBuilder sb) {

    }

    private static void unparseConstructorInvocation(ITree node,
                                                     StringBuilder sb) {

    }

    private static void unparseContinueStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseDoStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseEmptyStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseExpressionStatement(ITree node,
                                                   StringBuilder sb) {
        List<ITree> children = node.getChildren();

        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append(";\n");
    }

    private static void unparseFieldAccess(ITree node, StringBuilder sb) {

    }

    private static void unparseFieldDeclaration(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        for (ITree child : children) {
            prettyPrintNode(child, sb);
            sb.append(" ");
        }
        deleteTrailingSpace(sb);
        sb.append(";\n");
    }

    private static void unparseForStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseIfStatement(ITree node, StringBuilder sb) {
        // TODO: test what happens if for statement is only one line
        // could possibly test last child to see if it's a block
        List<ITree> children = node.getChildren();

        sb.append("if (");
        for (ITree child : children) {
            if (child.getType() == NodeTypeEnum.BLOCK.ordinal()) {
                /* the conditions have ended */
                sb.append(") ");
            }
            prettyPrintNode(child, sb);
        }
    }

    private static void unparseImportDeclaration(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append("import ");
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append(";\n");
    }

    private static void unparseInfixExpression(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        for (ITree child : children) {
            prettyPrintNode(child, sb);
            sb.append(node.getLabel() + " ");
        }
        /* remove the last infix label and space so that they will only be in
         * between each child */
        sb.delete(sb.length() - 2, sb.length());
    }

    private static void unparseInitializer(ITree node, StringBuilder sb) {

    }

    private static void unparseJavadoc(ITree node, StringBuilder sb) {
        sb.append("/**\n*/\n");
    }

    private static void unparseLabeledStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseMethodDeclaration(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();
        boolean methodHasBeenNamed = false;

        for (ITree child : children) {
            /* The last child of a method decl should be a block, before which
             * the parameter list should end with a closing ")" */
            if (child.getType() == NodeTypeEnum.BLOCK.ordinal()) {
                sb.append(") ");
            }

            prettyPrintNode(child, sb);
            sb.append(" ");

            /* The first simple name child of a method decl should be the name
             * of the method. This should be followed by the parameter list
             * surrounded in parentheses */
            if (child.getType() == NodeTypeEnum.SIMPLE_NAME.ordinal() &&
                    !methodHasBeenNamed) {
                methodHasBeenNamed = true;
                deleteTrailingSpace(sb);
                sb.append("(");
            }
        }
    }

    private static void unparseMethodInvocation(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        /* Print the receiver (always the 1st child) */
        prettyPrintNode(children.get(0), sb);
        /* After the receiver of the message send, add a "." */
        sb.append(".");
        /* Print the name of the method (always the 2nd child) */
        prettyPrintNode(children.get(1), sb);
        /* After the name, start the parameter list */
        printArgList(2, children, sb);
    }

    private static void printArgList(int startIdx, List<ITree> children,
                                     StringBuilder sb) {
        sb.append("(");

        List<ITree> arguments = children.subList(startIdx, children.size());
        for (ITree child : arguments) {
            prettyPrintNode(child, sb);
            sb.append(", ");
        }
        if (!arguments.isEmpty()) {
            /* remove the last comma and space so that they will only be in
             * between each parameter */
            sb.delete(sb.length() - 2, sb.length());
        }

        /* end the parameter list */
        sb.append(")");
    }

    private static void unparseNullLiteral(ITree node, StringBuilder sb) {
        sb.append("null");
    }

    private static void unparseNumberLiteral(ITree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparsePackageDeclaration(ITree node,
                                                  StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append("package ");
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }

        /* after a package decl, there's more often than not a blank line */
        sb.append(";\n\n");
    }

    private static void unparseParenthesizedExpression(ITree node,
                                                       StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append("(");
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append(")");
    }

    private static void unparsePostfixExpression(ITree node, StringBuilder sb) {

    }

    private static void unparsePrefixExpression(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append(node.getLabel());
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
    }

    private static void unparsePrimitiveType(ITree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseQualifiedName(ITree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseReturnStatement(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append("return ");
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append(";\n");
    }

    private static void unparseSimpleName(ITree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseSimpleType(ITree node, StringBuilder sb) {
        if (node.getParent().getType() ==
                NodeTypeEnum.TYPE_DECLARATION.ordinal()) {
            /* then it is a type being extended or implemented */
            /* TODO: figure out how to differentiate b/w extends/implements */
            sb.append("extends ");
        }

        List<ITree> children = node.getChildren();
        /* should only have one child, a simple name */
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
    }

    private static void unparseSingleVariableDeclaration(ITree node,
                                                         StringBuilder sb) {
        List<ITree> children = node.getChildren();

        for (ITree child : children) {
            prettyPrintNode(child, sb);
            sb.append(" ");
        }
        deleteTrailingSpace(sb);
    }

    private static void unparseStringLiteral(ITree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseSuperConstructorInvocation(ITree node,
                                                          StringBuilder sb) {

    }

    private static void unparseSuperFieldAccess(ITree node, StringBuilder sb) {

    }

    private static void unparseSuperMethodInvocation(ITree node,
                                                     StringBuilder sb) {

    }

    private static void unparseSwitchCase(ITree node, StringBuilder sb) {

    }

    private static void unparseSwitchStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseSynchronizedStatement(ITree node,
                                                     StringBuilder sb) {

    }

    private static void unparseThisExpression(ITree node, StringBuilder sb) {
        sb.append("this");
    }

    private static void unparseThrowStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseTryStatement(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append("try ");
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
    }

    private static void unparseTypeDeclaration(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();
        boolean classKeywordPrinted = false;
        boolean openingBracePrinted = false;

        for (ITree child : children) {
            /* if there are no more modifiers like public, static, final */
            if (child.getType() != NodeTypeEnum.JAVADOC.ordinal() &&
                    child.getType() != NodeTypeEnum.MODIFIER.ordinal() &&
                    !classKeywordPrinted) {
                sb.append("class ");
                classKeywordPrinted = true;
            }

            /* If this next node a simple type or name, we've started the
             * class */
            boolean isClassNameOrExtendedType =
                    (child.getType() == NodeTypeEnum.SIMPLE_NAME.ordinal() ||
                            child.getType() == NodeTypeEnum.SIMPLE_TYPE.ordinal());

            if (classKeywordPrinted &&
                    !isClassNameOrExtendedType &&
                    !openingBracePrinted) {
                sb.append("{\n\t");
                openingBracePrinted = true;
            }

            prettyPrintNode(child, sb);
            sb.append(" ");

        }

        /* at the end of a class, there should be a closing brace */
        sb.append("}\n");
    }

    private static void unparseTypeDeclarationStatement(ITree node,
                                                        StringBuilder sb) {

    }

    private static void unparseTypeLiteral(ITree node, StringBuilder sb) {

    }

    private static void unparseVariableDeclarationExpression(ITree node,
                                                             StringBuilder sb) {

    }

    private static void unparseVariableDeclarationFragment(ITree node,
                                                           StringBuilder sb) {
        List<ITree> children = node.getChildren();

        for (ITree child : children) {
            prettyPrintNode(child, sb);
            sb.append(" ");

            /* If the Variable Declaration Fragment has more than just one child
             * it means that after the variable has been named, it will be
             * initialized so it needs an = sign */
            if (child.getType() == NodeTypeEnum.SIMPLE_NAME.ordinal()
                    && nextSiblingExistsFor(child)) {
                sb.append("= ");
            }
        }
        deleteTrailingSpace(sb);
    }

    private static void unparseVariableDeclarationStatement(ITree node,
                                                            StringBuilder sb) {
        List<ITree> children = node.getChildren();

        for (ITree child : children) {
            prettyPrintNode(child, sb);
            sb.append(" ");
        }
        deleteTrailingSpace(sb);
        sb.append(";\n");
    }

    private static void unparseWhileStatement(ITree node, StringBuilder sb) {

    }

    private static void unparseInstanceofExpression(ITree node,
                                                    StringBuilder sb) {

    }

    private static void unparseLineComment(ITree node, StringBuilder sb) {

    }

    private static void unparseBlockComment(ITree node, StringBuilder sb) {

    }

    private static void unparseTagElement(ITree node, StringBuilder sb) {

    }

    private static void unparseTextElement(ITree node, StringBuilder sb) {

    }

    private static void unparseMemberRef(ITree node, StringBuilder sb) {

    }

    private static void unparseMethodRef(ITree node, StringBuilder sb) {

    }

    private static void unparseMethodRefParameter(ITree node,
                                                  StringBuilder sb) {

    }

    private static void unparseEnhancedForStatement(ITree node,
                                                    StringBuilder sb) {
        List<ITree> children = node.getChildren();
        sb.append("for (");
        /* Unparse loop control variable */
        prettyPrintNode(children.get(0), sb);
        sb.append(" : ");
        /* Unparse collection being looped through */
        prettyPrintNode(children.get(1), sb);
        sb.append(") ");
        /* Unparse block */
        // todo: what if an enhanced for only has one statement inside
        // and therefore doesn't have a block?
        prettyPrintNode(children.get(2), sb);

    }

    private static void unparseEnumDeclaration(ITree node, StringBuilder sb) {

    }

    private static void unparseEnumConstantDeclaration(ITree node,
                                                       StringBuilder sb) {

    }

    private static void unparseTypeParameter(ITree node, StringBuilder sb) {

    }

    private static void unparseParameterizedType(ITree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseQualifiedType(ITree node, StringBuilder sb) {

    }

    private static void unparseWildcardType(ITree node, StringBuilder sb) {

    }

    private static void unparseNormalAnnotation(ITree node, StringBuilder sb) {

    }

    private static void unparseMarkerAnnotation(ITree node, StringBuilder sb) {
        List<ITree> children = node.getChildren();

        sb.append("@");
        for (ITree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append("\n");
    }

    private static void unparseSingleMemberAnnotation(ITree node,
                                                      StringBuilder sb) {

    }

    private static void unparseMemberValuePair(ITree node, StringBuilder sb) {

    }

    private static void unparseAnnotationTypeDeclaration(ITree node,
                                                         StringBuilder sb) {

    }

    private static void unparseAnnotationTypeMemberDeclaration(ITree node,
                                                               StringBuilder sb) {

    }

    private static void unparseModifier(ITree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseUnionType(ITree node, StringBuilder sb) {

    }

    private static void unparseDimension(ITree node, StringBuilder sb) {

    }

    private static void unparseLambdaExpression(ITree node, StringBuilder sb) {

    }

    private static void unparseIntersectionType(ITree node, StringBuilder sb) {

    }

    private static void unparseNameQualifiedType(ITree node, StringBuilder sb) {

    }

    private static void unparseCreationReference(ITree node, StringBuilder sb) {

    }

    private static void unparseExpressionMethodReference(ITree node,
                                                         StringBuilder sb) {

    }

    private static void unparseSuperMethodReference(ITree node,
                                                    StringBuilder sb) {

    }

    private static void unparseTypeMethodReference(ITree node,
                                                   StringBuilder sb) {

    }

    /* Util methods used by case methods */
    private static void deleteTrailingSpace(StringBuilder sb) {
        sb.delete(sb.length()-1, sb.length());
    }

    private static ITree getPreviousSibling(ITree child) {
        int pos = child.positionInParent();
        int siblingPos = pos - 1;
        if (pos < 0) {
            throw new IndexOutOfBoundsException("no previous sibling");
        }
        return child.getParent().getChild(siblingPos);
    }


    private static boolean nextSiblingExistsFor(ITree child) {
        int pos = child.positionInParent();
        int siblingPos = pos + 1;
        ITree parent = child.getParent();
        try {
            parent.getChild(siblingPos);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

} /* end class Unparser */
