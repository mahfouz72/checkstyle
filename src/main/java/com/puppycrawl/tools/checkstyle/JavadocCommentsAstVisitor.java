package com.puppycrawl.tools.checkstyle;

import com.puppycrawl.tools.checkstyle.api.JavadocCommentsTokenTypes;
import com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocNodeImpl;
import com.puppycrawl.tools.checkstyle.grammar.javadoc.JavadocCommentsParser;
import com.puppycrawl.tools.checkstyle.grammar.javadoc.JavadocCommentsParserBaseVisitor;
import com.puppycrawl.tools.checkstyle.utils.JavadocUtil;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;


public class JavadocCommentsAstVisitor extends JavadocCommentsParserBaseVisitor<JavadocNodeImpl> {

    @Override
    public JavadocNodeImpl visitJavadoc(JavadocCommentsParser.JavadocContext ctx) {
        final JavadocNodeImpl javadocNode = createImaginary(JavadocCommentsTokenTypes.JAVADOC);
        processChildren(javadocNode, ctx.children);
        return javadocNode;
    }

    @Override
    public JavadocNodeImpl visitMainDescription(JavadocCommentsParser.MainDescriptionContext ctx) {
        return flattenedTree(ctx);
    }

    @Override
    public JavadocNodeImpl visitJavadocLine(JavadocCommentsParser.JavadocLineContext ctx) {
        return flattenedTree(ctx);
    }

    private JavadocNodeImpl flattenedTree(ParserRuleContext ctx) {
        final JavadocNodeImpl dummyNode = new JavadocNodeImpl();
        processChildren(dummyNode, ctx.children);
        return (JavadocNodeImpl) JavadocUtil.getFirstChild(dummyNode);
    }


    private void processChildren(JavadocNodeImpl parent, List<? extends ParseTree> children) {
        TextAccumulator textAccumulator = new TextAccumulator();

        children.forEach(child -> {
            if (child instanceof TerminalNode) {
                TerminalNode terminalNode = (TerminalNode) child;
                Token token = (Token) terminalNode.getPayload();

                if (isTextToken(token)) {
                    textAccumulator.append(terminalNode);
                }
                else {
                    textAccumulator.flush(parent);
                    parent.addChild(create(terminalNode));
                }
            }
            else {
                textAccumulator.flush(parent);
                parent.addChild(visit(child));
            }
        });
        textAccumulator.flush(parent);
    }

    private boolean isTextToken(Token token) {
        return token.getType() == JavadocCommentsTokenTypes.TEXT;
    }

    private JavadocNodeImpl create(TerminalNode terminalNode) {
        Token token = (Token) terminalNode.getPayload();
        final JavadocNodeImpl javadocNode = new JavadocNodeImpl();
        javadocNode.initialize(token);
        return javadocNode;
    }

    private JavadocNodeImpl createImaginary(int tokenType) {
        final JavadocNodeImpl node = new JavadocNodeImpl();
        node.setType(tokenType);
        node.setText(JavadocUtil.getTokenName(tokenType));
        return node;
    }


    private class TextAccumulator {
        TerminalNode firstTextToken = null;
        StringBuilder combinedText = new StringBuilder();


        public void append(TerminalNode terminalNode) {
            if (firstTextToken == null) {
                firstTextToken = terminalNode;
            }
            Token token = (Token) terminalNode.getPayload();
            combinedText.append(token.getText());
        }

        public void flush(JavadocNodeImpl parent) {
            if (!isEmpty()) {
                JavadocNodeImpl textNode = create(firstTextToken);
                textNode.setText(combinedText.toString());
                parent.addChild(textNode);
                clear();
            }
        }

        private boolean isEmpty() {
            return firstTextToken == null;
        }

        private void clear() {
            firstTextToken = null;
            combinedText.setLength(0);
        }
    }
}
