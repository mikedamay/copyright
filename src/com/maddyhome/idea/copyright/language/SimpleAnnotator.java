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

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.maddyhome.idea.copyright.language.psi.SimpleImportStatement;
import com.maddyhome.idea.copyright.language.psi.SimpleProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by mike on 9/20/15.
 */
public class SimpleAnnotator implements Annotator {

  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
     if (element instanceof SimpleImportStatement) {
       TextRange range = element.getTextRange();
       Annotation annotation = holder.createInfoAnnotation(range, null);
       annotation.setTextAttributes(SyntaxHighlighterColors.LINE_COMMENT);
     }

    else if (element.toString().equals("PsiKeyword:public")) {
        Project project = element.getProject();
        List<SimpleProperty> properties = SimpleUtil.findProperties(project, "case");
        if (properties.size() == 1) {
          TextRange range = new TextRange(element.getTextRange().getStartOffset() + 1,
            element.getTextRange().getStartOffset() + 1);
          Annotation annotation = holder.createInfoAnnotation(range, null);
          annotation.setTextAttributes(SyntaxHighlighterColors.LINE_COMMENT);
        }
        else if (properties.size() == 0) {
          TextRange range = new TextRange(element.getTextRange().getStartOffset() + 1,
            element.getTextRange().getEndOffset());
          holder.createErrorAnnotation(range, "unresolved issue");
        }
      }
  }
}
