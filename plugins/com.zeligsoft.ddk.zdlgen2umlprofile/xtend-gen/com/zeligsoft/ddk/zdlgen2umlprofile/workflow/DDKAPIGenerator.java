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
package com.zeligsoft.ddk.zdlgen2umlprofile.workflow;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainBlock;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainClassifier;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainEnum;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainModel;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainPackageableElement;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainSpecialization;
import com.zeligsoft.ddk.zdlgen2umlprofile.filesystem.IFileSystemAccess;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainConceptExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainModelExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainStructuralFeatureExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaEnumerationGenerator;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaImplementationGenerator;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaInterfaceGenerator;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaNamingExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaUMLPackageTypeSelectUtilGenerator;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.ZDLGenExtensions;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class DDKAPIGenerator {
  @Inject
  private GenDomainConceptExtensions _genDomainConceptExtensions;
  
  @Inject
  private GenDomainStructuralFeatureExtensions _genDomainStructuralFeatureExtensions;
  
  @Inject
  private JavaNamingExtensions _javaNamingExtensions;
  
  @Inject
  private JavaInterfaceGenerator _javaInterfaceGenerator;
  
  @Inject
  private JavaImplementationGenerator _javaImplementationGenerator;
  
  @Inject
  private JavaEnumerationGenerator _javaEnumerationGenerator;
  
  @Inject
  private JavaUMLPackageTypeSelectUtilGenerator _javaUMLPackageTypeSelectUtilGenerator;
  
  @Inject
  private ZDLGenExtensions _zDLGenExtensions;
  
  @Inject
  private GenDomainModelExtensions _genDomainModelExtensions;
  
  @Inject
  @Named(value = "Root Package")
  private String rootPackage;
  
  @Inject
  @Named(value = "Implementation SubPackage")
  private String implSubPackage;
  
  @Inject
  @Named(value = "Implementation Suffix")
  private String implSuffix;
  
  @Inject
  private IFileSystemAccess fileSystemAccess;
  
  public void doGenerate(final GenDomainModel model) {
    this.compileFactoryClass(model);
    this.compileTypeSelectClass(model);
    Iterable<GenDomainBlock> _domainBlocks = this._genDomainModelExtensions.domainBlocks(model);
    for (final GenDomainBlock block : _domainBlocks) {
      this.doGenerate(block);
    }
  }
  
  public void doGenerate(final GenDomainBlock block) {
    final String blockName = block.getName();
    String _interfaceJavaPackage = this._javaNamingExtensions.interfaceJavaPackage(block);
    final String rootDirectory = _interfaceJavaPackage.replace(".", "/");
    String _implementationJavaPackage = this._javaNamingExtensions.implementationJavaPackage(block);
    final String implDirectory = _implementationJavaPackage.replace(".", "/");
    EList<GenDomainClassifier> _classifiers = block.getClassifiers();
    Iterable<GenDomainConcept> _filter = Iterables.<GenDomainConcept>filter(_classifiers, GenDomainConcept.class);
    for (final GenDomainConcept concept : _filter) {
      {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(rootDirectory, "");
        _builder.append("/");
        String _javaInterfaceName = this._javaNamingExtensions.javaInterfaceName(concept);
        _builder.append(_javaInterfaceName, "");
        _builder.append(".java");
        String _string = _builder.toString();
        CharSequence _compileInterface = this._javaInterfaceGenerator.compileInterface(concept);
        this.fileSystemAccess.generateFile(_string, _compileInterface);
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(implDirectory, "");
        _builder_1.append("/");
        String _javaImplementationName = this._javaNamingExtensions.javaImplementationName(concept);
        _builder_1.append(_javaImplementationName, "");
        _builder_1.append(".java");
        String _string_1 = _builder_1.toString();
        CharSequence _compileImplementation = this._javaImplementationGenerator.compileImplementation(concept, blockName);
        this.fileSystemAccess.generateFile(_string_1, _compileImplementation);
      }
    }
    EList<GenDomainClassifier> _classifiers_1 = block.getClassifiers();
    Iterable<GenDomainEnum> _filter_1 = Iterables.<GenDomainEnum>filter(_classifiers_1, GenDomainEnum.class);
    for (final GenDomainEnum element : _filter_1) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(rootDirectory, "");
      _builder.append("/");
      String _javaInterfaceName = this._javaNamingExtensions.javaInterfaceName(element);
      String _validJavaIdentifier = UML2Util.getValidJavaIdentifier(_javaInterfaceName);
      _builder.append(_validJavaIdentifier, "");
      _builder.append(".java");
      String _string = _builder.toString();
      CharSequence _compileEnumeration = this._javaEnumerationGenerator.compileEnumeration(element, blockName);
      this.fileSystemAccess.generateFile(_string, _compileEnumeration);
    }
  }
  
  public void compileTypeSelectClass(final GenDomainModel model) {
    StringConcatenation _builder = new StringConcatenation();
    String _rootPackage = model.getRootPackage();
    String _replace = _rootPackage.replace(".", "/");
    _builder.append(_replace, "");
    _builder.append("/");
    String _name = model.getName();
    _builder.append(_name, "");
    _builder.append("/util/");
    String _name_1 = model.getName();
    _builder.append(_name_1, "");
    _builder.append("TypeSelectUtil.java");
    String _string = _builder.toString();
    CharSequence _generateJavaUMLPackageTypeSelectUtil = this._javaUMLPackageTypeSelectUtilGenerator.generateJavaUMLPackageTypeSelectUtil(model);
    this.fileSystemAccess.generateFile(_string, _generateJavaUMLPackageTypeSelectUtil);
  }
  
  public void compileFactoryClass(final GenDomainModel model) {
    EList<GenDomainPackageableElement> _elements = model.getElements();
    final Iterable<GenDomainSpecialization> domainSpecializations = Iterables.<GenDomainSpecialization>filter(_elements, GenDomainSpecialization.class);
    final Procedure1<GenDomainSpecialization> _function = new Procedure1<GenDomainSpecialization>() {
        public void apply(final GenDomainSpecialization spec) {
          DDKAPIGenerator.this.compileFactoryClass(spec);
        }
      };
    IterableExtensions.<GenDomainSpecialization>forEach(domainSpecializations, _function);
  }
  
  public void compileFactoryClass(final GenDomainSpecialization model) {
    EObject _eContainer = model.eContainer();
    final GenDomainModel domainModel = this._zDLGenExtensions.domainModel(_eContainer);
    StringConcatenation _builder = new StringConcatenation();
    String _rootPackage = domainModel.getRootPackage();
    String _replace = _rootPackage.replace(".", "/");
    _builder.append(_replace, "");
    _builder.append("/");
    String _name = domainModel.getName();
    _builder.append(_name, "");
    _builder.append("/util/");
    String _name_1 = model.getName();
    _builder.append(_name_1, "");
    _builder.append("FactoryImpl.java");
    String _string = _builder.toString();
    CharSequence _compileFactoryClassHelper = this.compileFactoryClassHelper(model);
    this.fileSystemAccess.generateFile(_string, _compileFactoryClassHelper);
  }
  
  private CharSequence compileFactoryClassHelper(final GenDomainSpecialization model) {
    CharSequence _xblockexpression = null;
    {
      EObject _eContainer = model.eContainer();
      final GenDomainModel domainModel = this._zDLGenExtensions.domainModel(_eContainer);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      String _rootPackage = domainModel.getRootPackage();
      _builder.append(_rootPackage, "");
      _builder.append(".");
      String _name = domainModel.getName();
      _builder.append(_name, "");
      _builder.append(".util;");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("import java.util.Map;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.google.common.collect.Maps;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("import com.zeligsoft.base.zdl.staticapi.util.AbstractBaseZDLFactory;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("public class ");
      String _name_1 = model.getName();
      _builder.append(_name_1, "");
      _builder.append("FactoryImpl extends AbstractBaseZDLFactory {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("protected java.util.Map<String, Class<?>> registry ");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("= Maps.newHashMap();");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public ");
      String _name_2 = model.getName();
      _builder.append(_name_2, "	");
      _builder.append("FactoryImpl() {");
      _builder.newLineIfNotEmpty();
      {
        EList<GenDomainConcept> _domainConcepts = model.getDomainConcepts();
        final Function1<GenDomainConcept,Boolean> _function = new Function1<GenDomainConcept,Boolean>() {
            public Boolean apply(final GenDomainConcept concept) {
              org.eclipse.uml2.uml.Class _domainConcept = concept.getDomainConcept();
              boolean _isAbstract = _domainConcept.isAbstract();
              boolean _not = (!_isAbstract);
              return Boolean.valueOf(_not);
            }
          };
        Iterable<GenDomainConcept> _filter = IterableExtensions.<GenDomainConcept>filter(_domainConcepts, _function);
        for(final GenDomainConcept concept : _filter) {
          _builder.append("\t");
          _builder.append("registry.put(\"");
          NamedElement _domainElement = concept.getDomainElement();
          String _qualifiedName = _domainElement.getQualifiedName();
          _builder.append(_qualifiedName, "	");
          _builder.append("\", ");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("\t");
          CharSequence _implementationQualifiedName = this._javaNamingExtensions.implementationQualifiedName(concept);
          _builder.append(_implementationQualifiedName, "		");
          _builder.append(".class);");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("protected Map<String, Class<?>> getRegistry() {");
      _builder.newLine();
      _builder.append("\t    ");
      _builder.append("return registry;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
}
