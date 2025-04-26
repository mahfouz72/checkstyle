package com.puppycrawl.tools.checkstyle.grammar.javadoc;

import com.puppycrawl.tools.checkstyle.AbstractTreeTestSupport;
import org.junit.jupiter.api.Test;

public class JavadocAstRegressionTest extends AbstractTreeTestSupport {

    @Override
    protected String getPackageLocation() {
        return "com/puppycrawl/tools/checkstyle/grammar/javadoc/";
    }

    @Test
    public void testEmptyJavadoc() throws Exception {
        verifyJavadocTree(getPath("expectedEmptyJavadoc.txt"),
                getPath("InputEmptyJavadoc.javadoc"));
    }

    @Test
    public void testSimpleJavadocWithText() throws Exception {
        verifyJavadocTree(getPath("expectedJavadocWithText.txt"),
                getPath("InputJavadocWithText.javadoc"));
    }

    @Test
    public void testJavadocWithSpecialSymbolsInText() throws Exception {
        verifyJavadocTree(getPath("expectedJavadocWithSpecialSymbolsInText.txt"),
                getPath("InputJavadocWithSpecialSymbolsInText.javadoc"));
    }
}
