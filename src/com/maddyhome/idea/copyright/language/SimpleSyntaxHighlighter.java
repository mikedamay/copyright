/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.maddyhome.idea.copyright.language;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.maddyhome.idea.copyright.language.psi.SimpleTypes;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;
import static com.maddyhome.idea.copyright.language.SimpleSyntaxHighlightingColors.*;

/**
 * Created by mike on 9/8/15.
 */
public class SimpleSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey SEPARATOR
    = createTextAttributesKey("SIMPLE_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

  private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

  private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

  static {
    fillMap(ATTRIBUTES, PARENTHESIS, SimpleTypes.L_PAREN, SimpleTypes.R_PAREN);
    //fillMap(ATTRIBUTES, R_PAREN, SimpleTypes.R_PAREN);
    fillMap(ATTRIBUTES, VALUE, SimpleTypes.VALUE);
    fillMap(ATTRIBUTES, COMMENT, SimpleTypes.COMMENT);
    fillMap(ATTRIBUTES, MULTILINE_COMMENT, SimpleTypes.MULTILINE_COMMENT);
    fillMap(ATTRIBUTES, STR, SimpleTypes.STR);
    fillMap(ATTRIBUTES, SimpleParserDefinition.KEYWORDS, KEYWORD);
    fillMap(ATTRIBUTES, BAD_CHARACTER, TokenType.BAD_CHARACTER);
    /*
    fillMap(ATTRIBUTES, LINE_COMMENT, GoParserDefinition.LINE_COMMENT);
    fillMap(ATTRIBUTES, BLOCK_COMMENT, GoParserDefinition.MULTILINE_COMMENT);
    fillMap(ATTRIBUTES, PARENTHESES, GoTypes.LPAREN, GoTypes.RPAREN);
    fillMap(ATTRIBUTES, BRACES, GoTypes.LBRACE, GoTypes.RBRACE);
    fillMap(ATTRIBUTES, BRACKETS, GoTypes.LBRACK, GoTypes.RBRACK);
    fillMap(ATTRIBUTES, BAD_CHARACTER, TokenType.BAD_CHARACTER);
    fillMap(ATTRIBUTES, IDENTIFIER, GoTypes.IDENTIFIER);
    fillMap(ATTRIBUTES, DOT, GoTypes.DOT, GoTypes.TRIPLE_DOT);
    fillMap(ATTRIBUTES, COLON, GoTypes.COLON);
    fillMap(ATTRIBUTES, SEMICOLON, GoTypes.SEMICOLON);
    fillMap(ATTRIBUTES, COMMA, GoTypes.COMMA);
    fillMap(ATTRIBUTES, GoParserDefinition.OPERATORS, OPERATOR);
    fillMap(ATTRIBUTES, GoParserDefinition.KEYWORDS, KEYWORD);
    fillMap(ATTRIBUTES, GoParserDefinition.NUMBERS, NUMBER);
    fillMap(ATTRIBUTES, GoParserDefinition.STRING_LITERALS, STRING);
    */
  }




  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new FlexAdapter(new SimpleLexer((Reader) null));
  }

  @NotNull
  @Override
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(ATTRIBUTES.get(tokenType));
    /*
    if
      //(tokenType.equals(SimpleTypes.SEPARATOR)) {
      //return SEPARATOR_KEYS;
    //} else if (tokenType.equals(SimpleTypes.KEY)) {
    //  return KEY_KEYS;
    (tokenType.equals(SimpleTypes.VALUE)) {
      return pack(SimpleSyntaxHighlightingColors.VALUE);
    } else if (tokenType.equals(SimpleTypes.COMMENT)) {
      return pack(SimpleSyntaxHighlightingColors.COMMENT);
    } else if (tokenType.equals(SimpleTypes.MULTILINE_COMMENT)) {
      return pack(SimpleSyntaxHighlightingColors.MULTILINE_COMMENT);
    } else if (tokenType.equals(SimpleTypes.STR)) {
      return pack(SimpleSyntaxHighlightingColors.STR);
    } else if (tokenType.equals(SimpleTypes.L_PAREN)) {
      return pack(SimpleSyntaxHighlightingColors.L_PAREN);
    } else if (tokenType.equals(SimpleTypes.R_PAREN)) {
      return pack(SimpleSyntaxHighlightingColors.R_PAREN);
    } else if (tokenType.equals(SimpleTypes.IMPORT)) {
      return pack(SimpleSyntaxHighlightingColors.KEYWORD);
    } else if (tokenType.equals(TokenType.BAD_CHARACTER)){
      return pack(SimpleSyntaxHighlightingColors.BAD_CHARACTER);
    } else{
      return EMPTY_KEYS;
    }
    */
  }
}
