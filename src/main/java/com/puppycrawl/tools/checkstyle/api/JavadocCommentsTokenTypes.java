package com.puppycrawl.tools.checkstyle.api;

import com.puppycrawl.tools.checkstyle.grammar.javadoc.JavadocCommentsLexer;

public final class JavadocCommentsTokenTypes {

    public static final int JAVADOC = JavadocCommentsLexer.JAVADOC;
    public static final int LEADING_ASTERISK = JavadocCommentsLexer.LEADING_ASTERISK;
    public static final int NEWLINE = JavadocCommentsLexer.NEWLINE;
    public static final int AUTHOR_LITERAL = JavadocCommentsLexer.AUTHOR_LITERAL;
    public static final int SEE_LITERAL = JavadocCommentsLexer.SEE_LITERAL;
    public static final int PARAM_LITERAL = JavadocCommentsLexer.PARAM_LITERAL;
    public static final int CUSTOM_NAME = JavadocCommentsLexer.CUSTOM_NAME;
    public static final int TEXT = JavadocCommentsLexer.TEXT;
    public static final int JAVADOC_INLINE_TAG_START = JavadocCommentsLexer.JAVADOC_INLINE_TAG_START;
    public static final int CODE_LITERAL = JavadocCommentsLexer.CODE_LITERAL;
    public static final int LINK_LITERAL = JavadocCommentsLexer.LINK_LITERAL;
    public static final int LINKPLAIN_LITERAL = JavadocCommentsLexer.LINKPLAIN_LITERAL;
    public static final int JAVADOC_INLINE_TAG_END = JavadocCommentsLexer.JAVADOC_INLINE_TAG_END;

}
