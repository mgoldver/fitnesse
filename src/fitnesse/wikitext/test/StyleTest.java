package fitnesse.wikitext.test;

import org.junit.Test;

public class StyleTest {
    @Test public void scansParenthesisStyle() throws Exception {
        ParserTest.assertScansTokenType("!style_x(my text)", "Style", true);
        ParserTest.assertScansTokenType("!style_style(my text)", "Style", true);
        ParserTest.assertScansTokenType("!style(Hi)", "Style", false);
        ParserTest.assertScansTokenType("!style_(Hi)", "Style", false);
        ParserTest.assertScansTokenType("!style_myStyle(hi))", "Style", true);
    }

    @Test public void scansBraceStyle() throws Exception {
        ParserTest.assertScansTokenType("!style_x{my text}", "Style", true);
        ParserTest.assertScansTokenType("!style_style{my text}", "Style", true);
        ParserTest.assertScansTokenType("!style{Hi}", "Style", false);
        ParserTest.assertScansTokenType("!style_{Hi}", "Style", false);
        ParserTest.assertScansTokenType("!style_myStyle{hi}}", "Style", true);
    }

    @Test public void scansBracketStyle() throws Exception {
        ParserTest.assertScansTokenType("!style_x[my text]", "Style", true);
        ParserTest.assertScansTokenType("!style_style[my text]", "Style", true);
        ParserTest.assertScansTokenType("!style[Hi]", "Style", false);
        ParserTest.assertScansTokenType("!style_[Hi]", "Style", false);
        ParserTest.assertScansTokenType("!style_myStyle[hi]]", "Style", true);
    }

    @Test public void translatesStyle() {
        ParserTest.assertTranslatesTo("!style_myStyle(wow zap)", "<span class=\"myStyle\">wow zap</span>");
        ParserTest.assertTranslatesTo("!style_myStyle[wow zap]", "<span class=\"myStyle\">wow zap</span>");
        ParserTest.assertTranslatesTo("!style_myStyle[)]", "<span class=\"myStyle\">)</span>");
        ParserTest.assertTranslatesTo("!style_myStyle{wow zap}", "<span class=\"myStyle\">wow zap</span>");
    }

    @Test public void ignoresMismatchedStyle() {
        ParserTest.assertTranslatesTo("!style_myStyle[stuff)", "!style_myStyle[stuff)");
    }

    @Test public void translatesNestedStyle() {
        ParserTest.assertTranslatesTo("!style_myStyle(!style_otherStyle(stuff))",
                "<span class=\"myStyle\"><span class=\"otherStyle\">stuff</span></span>");
    }

    @Test public void translatesOverlappedStyle() {
        ParserTest.assertTranslatesTo("!style_red(!style_blue{a)}",
                "!style_red(<span class=\"blue\">a)</span>");
    }
}
