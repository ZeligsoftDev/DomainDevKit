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

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainBlock;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainClassifier;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainModel;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainModelExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaImportExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaNamingExtensions;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class JavaUMLPackageTypeSelectUtilGenerator {
  @Inject
  private GenDomainModelExtensions _genDomainModelExtensions;
  
  @Inject
  private JavaNamingExtensions _javaNamingExtensions;
  
  @Inject
  private JavaImportExtensions _javaImportExtensions;
  
  public CharSequence generateJavaUMLPackageTypeSelectUtil(final GenDomainModel model) {
    CharSequence _xblockexpression = null;
    {
      final Iterable<GenDomainClassifier> packageableElementTypes = this.packageableElementTypes(model);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      String _rootPackage = model.getRootPackage();
      _builder.append(_rootPackage, "");
      _builder.append(".");
      String _name = model.getName();
      _builder.append(_name, "");
      _builder.append(".util;");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("import java.util.Collection;");
      _builder.newLine();
      _builder.append("import java.util.List;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import org.eclipse.uml2.uml.PackageableElement;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.google.common.collect.Collections2;");
      _builder.newLine();
      _builder.append("import com.google.common.collect.ImmutableList;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.zeligsoft.base.zdl.staticapi.predicate.IsZDLConcept;");
      _builder.newLine();
      _builder.append("import com.zeligsoft.base.zdl.staticapi.functions.CreateZDLWrapper;");
      _builder.newLine();
      _builder.newLine();
      {
        for(final GenDomainClassifier type : packageableElementTypes) {
          CharSequence _generateImport = this._javaImportExtensions.generateImport(type);
          _builder.append(_generateImport, "");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("public class ");
      String _name_1 = model.getName();
      _builder.append(_name_1, "");
      _builder.append("TypeSelectUtil {");
      _builder.newLineIfNotEmpty();
      {
        for(final GenDomainClassifier type_1 : packageableElementTypes) {
          _builder.append("    ");
          _builder.append("public List<");
          String _javaInterfaceName = this._javaNamingExtensions.javaInterfaceName(type_1);
          _builder.append(_javaInterfaceName, "    ");
          _builder.append("> select");
          String _name_2 = type_1.getName();
          String _firstUpper = StringExtensions.toFirstUpper(_name_2);
          _builder.append(_firstUpper, "    ");
          _builder.append("(org.eclipse.uml2.uml.Package pkg) {");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("    ");
          _builder.append("final Collection<PackageableElement> elements = ");
          _builder.newLine();
          _builder.append("    ");
          _builder.append("        ");
          _builder.append("Collections2.filter(pkg.getPackagedElements(),");
          _builder.newLine();
          _builder.append("    ");
          _builder.append("            ");
          _builder.append("new IsZDLConcept(\"");
          NamedElement _domainElement = type_1.getDomainElement();
          String _qualifiedName = _domainElement.getQualifiedName();
          _builder.append(_qualifiedName, "                ");
          _builder.append("\"));");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("    ");
          _builder.append("final Collection<");
          String _javaInterfaceName_1 = this._javaNamingExtensions.javaInterfaceName(type_1);
          _builder.append(_javaInterfaceName_1, "        ");
          _builder.append("> result = ");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("        ");
          _builder.append("Collections2.transform(elements, CreateZDLWrapper.create(");
          String _javaInterfaceName_2 = this._javaNamingExtensions.javaInterfaceName(type_1);
          _builder.append(_javaInterfaceName_2, "            ");
          _builder.append(".class));");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("    ");
          _builder.append("return new ImmutableList.Builder<");
          String _javaInterfaceName_3 = this._javaNamingExtensions.javaInterfaceName(type_1);
          _builder.append(_javaInterfaceName_3, "        ");
          _builder.append(">().addAll(result).build();");
          _builder.newLineIfNotEmpty();
          _builder.append("    ");
          _builder.append("    ");
          _builder.newLine();
          _builder.append("    ");
          _builder.append("}");
          _builder.newLine();
          _builder.append("    ");
          _builder.newLine();
        }
      }
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public Iterable<GenDomainClassifier> packageableElementTypes(final GenDomainModel model) {
    Iterable<GenDomainBlock> _domainBlocks = this._genDomainModelExtensions.domainBlocks(model);
    final Function1<GenDomainBlock,Iterable<GenDomainClassifier>> _function = new Function1<GenDomainBlock,Iterable<GenDomainClassifier>>() {
        public Iterable<GenDomainClassifier> apply(final GenDomainBlock block) {
          Iterable<GenDomainClassifier> _packageableElementTypes = JavaUMLPackageTypeSelectUtilGenerator.this.packageableElementTypes(block);
          return _packageableElementTypes;
        }
      };
    Iterable<Iterable<GenDomainClassifier>> _map = IterableExtensions.<GenDomainBlock, Iterable<GenDomainClassifier>>map(_domainBlocks, _function);
    Iterable<GenDomainClassifier> _flatten = Iterables.<GenDomainClassifier>concat(_map);
    return _flatten;
  }
  
  public Iterable<GenDomainClassifier> packageableElementTypes(final GenDomainBlock block) {
    EList<GenDomainClassifier> _classifiers = block.getClassifiers();
    final Function1<GenDomainClassifier,Boolean> _function = new Function1<GenDomainClassifier,Boolean>() {
        public Boolean apply(final GenDomainClassifier classifier) {
          boolean _mapsToPackageableElement = JavaUMLPackageTypeSelectUtilGenerator.this.mapsToPackageableElement(classifier);
          return Boolean.valueOf(_mapsToPackageableElement);
        }
      };
    Iterable<GenDomainClassifier> _filter = IterableExtensions.<GenDomainClassifier>filter(_classifiers, _function);
    return _filter;
  }
  
  private boolean _mapsToPackageableElement(final GenDomainClassifier classifier) {
    return false;
  }
  
  private boolean _mapsToPackageableElement(final GenDomainConcept classifier) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      Set<org.eclipse.uml2.uml.Class> _umlMetaclassMapping = this.umlMetaclassMapping(classifier);
      final Function1<org.eclipse.uml2.uml.Class,Boolean> _function = new Function1<org.eclipse.uml2.uml.Class,Boolean>() {
          public Boolean apply(final org.eclipse.uml2.uml.Class base) {
            boolean _or = false;
            String _name = base.getName();
            boolean _equals = _name.equals("PackageableElement");
            if (_equals) {
              _or = true;
            } else {
              EList<Classifier> _allParents = base.allParents();
              final Function1<Classifier,Boolean> _function = new Function1<Classifier,Boolean>() {
                  public Boolean apply(final Classifier parent) {
                    String _name = parent.getName();
                    boolean _equals = _name.equals("PackageableElement");
                    return Boolean.valueOf(_equals);
                  }
                };
              boolean _exists = IterableExtensions.<Classifier>exists(_allParents, _function);
              _or = (_equals || _exists);
            }
            return Boolean.valueOf(_or);
          }
        };
      boolean _exists = IterableExtensions.<org.eclipse.uml2.uml.Class>exists(_umlMetaclassMapping, _function);
      result = _exists;
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  private Set<org.eclipse.uml2.uml.Class> umlMetaclassMapping(final GenDomainConcept concept) {
    Set<org.eclipse.uml2.uml.Class> _xblockexpression = null;
    {
      final Set<org.eclipse.uml2.uml.Class> metaclasses = CollectionLiterals.<org.eclipse.uml2.uml.Class>newHashSet();
      EList<GenDomainConcept> _allGenerals = concept.allGenerals();
      final Function1<GenDomainConcept,EList<org.eclipse.uml2.uml.Class>> _function = new Function1<GenDomainConcept,EList<org.eclipse.uml2.uml.Class>>() {
          public EList<org.eclipse.uml2.uml.Class> apply(final GenDomainConcept base) {
            EList<org.eclipse.uml2.uml.Class> _umlMetaclasses = base.getUmlMetaclasses();
            return _umlMetaclasses;
          }
        };
      List<EList<org.eclipse.uml2.uml.Class>> _map = ListExtensions.<GenDomainConcept, EList<org.eclipse.uml2.uml.Class>>map(_allGenerals, _function);
      final Iterable<org.eclipse.uml2.uml.Class> baseMetaclasses = Iterables.<org.eclipse.uml2.uml.Class>concat(_map);
      EList<org.eclipse.uml2.uml.Class> _umlMetaclasses = concept.getUmlMetaclasses();
      metaclasses.addAll(_umlMetaclasses);
      Iterables.<org.eclipse.uml2.uml.Class>addAll(metaclasses, baseMetaclasses);
      _xblockexpression = (metaclasses);
    }
    return _xblockexpression;
  }
  
  private boolean mapsToPackageableElement(final GenDomainClassifier classifier) {
    if (classifier instanceof GenDomainConcept) {
      return _mapsToPackageableElement((GenDomainConcept)classifier);
    } else if (classifier != null) {
      return _mapsToPackageableElement(classifier);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(classifier).toString());
    }
  }
}
