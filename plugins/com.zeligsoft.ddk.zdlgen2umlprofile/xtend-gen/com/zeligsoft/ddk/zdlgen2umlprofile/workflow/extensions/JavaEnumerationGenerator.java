/*******************************************************************************
 * Copyright (c) 2020 Northrop Grumman Systems Corporation.
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
 *******************************************************************************/
package com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions;

import com.google.inject.Inject;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainBlock;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainClassifier;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainEnum;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainEnumLiteral;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaNamingExtensions;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class JavaEnumerationGenerator {
  @Inject
  private JavaNamingExtensions _javaNamingExtensions;
  
  protected CharSequence _compileEnumeration(final GenDomainEnum element, final String pkg) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    GenDomainBlock _block = element.getBlock();
    String _interfaceJavaPackage = this._javaNamingExtensions.interfaceJavaPackage(_block);
    _builder.append(_interfaceJavaPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    CharSequence _generateEnumerationImports = this.generateEnumerationImports(element);
    _builder.append(_generateEnumerationImports, "");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* An enumeration for ");
    NamedElement _domainElement = element.getDomainElement();
    String _qualifiedName = _domainElement.getQualifiedName();
    _builder.append(_qualifiedName, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @author ZDL API Generator");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public enum ");
    String _name = element.getName();
    String _validJavaIdentifier = UML2Util.getValidJavaIdentifier(_name);
    _builder.append(_validJavaIdentifier, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    {
      EList<GenDomainEnumLiteral> _literals = element.getLiterals();
      boolean _hasElements = false;
      for(final GenDomainEnumLiteral literal : _literals) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(",\n", "    ");
        }
        _builder.append("    ");
        _builder.append("/**");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("* ");
        _builder.newLine();
        {
          NamedElement _domainElement_1 = literal.getDomainElement();
          EList<Comment> _ownedComments = _domainElement_1.getOwnedComments();
          for(final Comment comment : _ownedComments) {
            _builder.append("    ");
            _builder.append(" ");
            _builder.append("* ");
            String _body = comment.getBody();
            _builder.append(_body, "     ");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("*");
        _builder.newLine();
        _builder.append("    ");
        _builder.append(" ");
        _builder.append("*/");
        _builder.newLine();
        _builder.append("    ");
        String _name_1 = literal.getName();
        String _upperCase = _name_1.toUpperCase();
        _builder.append(_upperCase, "    ");
        _builder.append(" {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("\t");
        _builder.append("public EObject eObject(EObject context) {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("\t\t");
        _builder.append("return ZDLUtil.getZDLEnumLiteral(context,");
        _builder.newLine();
        _builder.append("\t\t\t\t\t");
        _builder.append("\"");
        NamedElement _domainElement_2 = element.getDomainElement();
        String _qualifiedName_1 = _domainElement_2.getQualifiedName();
        _builder.append(_qualifiedName_1, "					");
        _builder.append("\", \"");
        String _name_2 = literal.getName();
        _builder.append(_name_2, "					");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("\t");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("\t");
        _builder.append("public EObject eObject(com.zeligsoft.base.zdl.staticapi.core.ZObject context) {");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("\t\t");
        _builder.append("return eObject(context.eObject());");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
      }
    }
    {
      EList<GenDomainEnumLiteral> _literals_1 = element.getLiterals();
      boolean _isEmpty = _literals_1.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        _builder.append(",");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("* Literal for cases when the value is UNKNOWN");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("UNKNOWN {");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("public EObject eObject(EObject context) {");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    \t");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("public EObject eObject(com.zeligsoft.base.zdl.staticapi.core.ZObject context) {");
    _builder.newLine();
    _builder.append("    \t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("    \t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("};");
    _builder.newLine();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    CharSequence _generateCreateMethod = this.generateCreateMethod(element);
    _builder.append(_generateCreateMethod, "    ");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public abstract EObject eObject(EObject context);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("public abstract EObject eObject(com.zeligsoft.base.zdl.staticapi.core.ZObject context);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _compileEnumeration(final GenDomainClassifier element, final String pkg) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  private CharSequence generateEnumerationImports(final GenDomainEnum element) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.eclipse.emf.ecore.EObject;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import com.zeligsoft.base.zdl.util.ZDLUtil;");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence generateCreateMethod(final GenDomainEnum element) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @param literal");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*    the raw object to create the instance from");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* @return");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*    an instance of this enumeration based on the literal passed in and");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*    UNKNOWN if the literal is unrecognized");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public static ");
    String _name = element.getName();
    String _validJavaIdentifier = UML2Util.getValidJavaIdentifier(_name);
    _builder.append(_validJavaIdentifier, "");
    _builder.append(" create(EObject literal) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    {
      EList<GenDomainEnumLiteral> _literals = element.getLiterals();
      boolean _hasElements = false;
      for(final GenDomainEnumLiteral literal : _literals) {
        if (!_hasElements) {
          _hasElements = true;
          _builder.append("if ", "    ");
        } else {
          _builder.appendImmediate(" else if ", "    ");
        }
        _builder.append("(literal == ZDLUtil.getZDLEnumLiteral(literal, \"");
        NamedElement _domainElement = element.getDomainElement();
        String _qualifiedName = _domainElement.getQualifiedName();
        _builder.append(_qualifiedName, "    ");
        _builder.append("\", \"");
        String _name_1 = literal.getName();
        _builder.append(_name_1, "    ");
        _builder.append("\")) { //$NON-NLS-1$//$NON-NLS-2$");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("return ");
        String _name_2 = literal.getName();
        String _upperCase = _name_2.toUpperCase();
        _builder.append(_upperCase, "        ");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("}");
        _builder.newLine();
        _builder.append("    ");
      }
    }
    {
      EList<GenDomainEnumLiteral> _literals_1 = element.getLiterals();
      boolean _isEmpty = _literals_1.isEmpty();
      if (_isEmpty) {
        _builder.append("return UNKNOWN;");
      } else {
        _builder.append(" else {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("    ");
        _builder.append("return UNKNOWN;");
        _builder.newLine();
        _builder.append("    ");
        _builder.append("}");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence compileEnumeration(final GenDomainClassifier element, final String pkg) {
    if (element instanceof GenDomainEnum) {
      return _compileEnumeration((GenDomainEnum)element, pkg);
    } else if (element != null) {
      return _compileEnumeration(element, pkg);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(element, pkg).toString());
    }
  }
}
