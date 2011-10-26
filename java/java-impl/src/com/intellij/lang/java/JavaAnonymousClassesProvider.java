/*
 * Copyright 2000-2011 JetBrains s.r.o.
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
package com.intellij.lang.java;

import com.intellij.navigation.AnonymousElementProvider;
import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiAnonymousClass;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Konstantin Bulenkov
 */
public class JavaAnonymousClassesProvider implements AnonymousElementProvider {
  @Override
  public PsiElement[] getAnonymousElements(PsiElement parent) {
    if (parent instanceof PsiClass) {
      final List<PsiElement> elements = new ArrayList<PsiElement>();
      new JavaRecursiveElementWalkingVisitor() {
        @Override
        public void visitAnonymousClass(PsiAnonymousClass aClass) {
          elements.add(aClass);
        }
      }.visitElement(parent);

      if (! elements.isEmpty()) {
        return elements.toArray(new PsiElement[elements.size()]);
      }
    }
    return PsiElement.EMPTY_ARRAY;
  }
}
