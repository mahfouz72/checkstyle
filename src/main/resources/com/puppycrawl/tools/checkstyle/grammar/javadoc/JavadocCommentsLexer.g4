lexer grammar JavadocCommentsLexer;

tokens {
    JAVADOC, LEADING_ASTERISK, NEWLINE, AUTHOR_LITERAL, SEE_LITERAL, PARAM_LITERAL,
    CUSTOM_NAME, TEXT, JAVADOC_INLINE_TAG_START,
    CODE_LITERAL, LINK_LITERAL, LINKPLAIN_LITERAL, JAVADOC_INLINE_TAG_END
}


@lexer::members {
    private int previousTokenType = 0;
    private Token previousToken = null;
    private boolean afterNewline = true;
    private boolean isJavadocTag = true;

    public boolean isAfterNewline() {
        return afterNewline;
    }

    public void setAfterNewline() {
        afterNewline = true;
    }

    /*
        Each block tag must appear at the beginning of a line, ignoring leading asterisks,
        whitespace characters. This means you can use the @ character elsewhere in the text
        and it will not be interpreted as the start of a tag.
    */
    private boolean isJavadocTag() {
        if (previousToken == null) {
            return true;
        }
        return previousTokenType == LEADING_ASTERISK || previousToken.getText().isBlank();
    }

    @Override
    public void emit(Token token) {
        super.emit(token);
        previousTokenType = token.getType();
        previousToken = token;

        if (previousTokenType != NEWLINE) {
            afterNewline = false;
        }
    }
}

LEADING_ASTERISK
    : [ \t]* '*' {isAfterNewline()}?
    ;

NEWLINE
    : '\r'? '\n' { setAfterNewline(); }
    ;

AUTHOR_LITERAL: '@author' {isJavadocTag()}?;
SEE_LITERAL: '@see' {isJavadocTag()}?;
PARAM_LITERAL: '@param' {isJavadocTag()}?;

CUSTOM_NAME: '@' [a-zA-Z0-9:._-]+ {isJavadocTag()}?;

JAVADOC_INLINE_TAG_START: '{' -> pushMode(JavadocInlineTag);

ASTERISK_IN_TEXT
    : '*' -> type(TEXT)
    ;

AT_IN_TEXT
    : '@' -> type(TEXT)
    ;

TEXT
    : ~[@*{}\r\n]+
    ;

mode JavadocInlineTag;

CODE_LITERAL: '@code';
LINK_LITERAL : '@link';
LINKPLAIN_LITERAL: '@linkplain';
JavadocInlineTag_TEXT: ~[@*{}\r\n]+ -> type(TEXT);
JavadocInlineTag_AT_IN_TEXT: '@' -> type(TEXT);
JavadocInlineTag_ASTERISK_IN_TEXT: '*' -> type(TEXT);
JAVADOC_INLINE_TAG_END: '}' -> popMode;
