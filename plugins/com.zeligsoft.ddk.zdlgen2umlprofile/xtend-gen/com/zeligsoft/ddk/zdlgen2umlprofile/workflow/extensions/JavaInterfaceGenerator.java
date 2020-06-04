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

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainAttribute;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainBlock;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainClassifier;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainDataType;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainNamedElement;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainReference;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainStructuralFeature;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainConceptExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainStructuralFeatureExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaImportExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaMethodSignaturesExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaNamingExtensions;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class JavaInterfaceGenerator {
  @Inject
  private GenDomainConceptExtensions _genDomainConceptExtensions;
  
  @Inject
  private GenDomainStructuralFeatureExtensions _genDomainStructuralFeatureExtensions;
  
  @Inject
  private JavaNamingExtensions _javaNamingExtensions;
  
  @Inject
  private JavaImportExtensions _javaImportExtensions;
  
  @Inject
  private JavaMethodSignaturesExtensions _javaMethodSignaturesExtensions;
  
  @Inject
  @Named(value = "Root Package")
  private String rootPackage;
  
  @Inject
  @Named(value = "Implementation SubPackage")
  private String implSubPackage;
  
  @Inject
  @Named(value = "Implementation Suffix")
  private String implSuffix;
  
  protected CharSequence _compileInterface(final GenDomainClassifier concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    GenDomainBlock _block = concept.getBlock();
    String _interfaceJavaPackage = this._javaNamingExtensions.interfaceJavaPackage(_block);
    _builder.append(_interfaceJavaPackage, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("public interface ");
    String _javaInterfaceName = this._javaNamingExtensions.javaInterfaceName(concept);
    _builder.append(_javaInterfaceName, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  protected CharSequence _compileInterface(final GenDomainConcept concept) {
    CharSequence _xblockexpression = null;
    {
      final List<GenDomainClassifier> importedTypes = this.interfaceImports(concept);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      GenDomainBlock _block = concept.getBlock();
      String _interfaceJavaPackage = this._javaNamingExtensions.interfaceJavaPackage(_block);
      _builder.append(_interfaceJavaPackage, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      {
        EList<Classifier> _baseDomainConcepts = this._genDomainConceptExtensions.baseDomainConcepts(concept);
        boolean _isEmpty = _baseDomainConcepts.isEmpty();
        if (_isEmpty) {
          _builder.append(" ");
          _builder.append("import com.zeligsoft.base.zdl.staticapi.core.ZObject;");
          _builder.newLine();
        }
      }
      _builder.append("import com.zeligsoft.base.zdl.staticapi.functions.TypeSelectPredicate;");
      _builder.newLine();
      {
        Iterable<GenDomainClassifier> _filterNull = IterableExtensions.<GenDomainClassifier>filterNull(importedTypes);
        for(final GenDomainClassifier importedType : _filterNull) {
          {
            GenDomainBlock _block_1 = importedType.getBlock();
            GenDomainBlock _block_2 = concept.getBlock();
            boolean _notEquals = (!Objects.equal(_block_1, _block_2));
            if (_notEquals) {
              CharSequence _generateImport = this._javaImportExtensions.generateImport(importedType);
              _builder.append(_generateImport, "");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      _builder.newLine();
      _builder.append("public interface ");
      NamedElement _domainElement = concept.getDomainElement();
      String _name = _domainElement.getName();
      _builder.append(_name, "");
      {
        EList<Classifier> _baseDomainConcepts_1 = this._genDomainConceptExtensions.baseDomainConcepts(concept);
        boolean _isEmpty_1 = _baseDomainConcepts_1.isEmpty();
        if (_isEmpty_1) {
          _builder.append(" extends ZObject");
        }
      }
      {
        EList<Classifier> _baseDomainConcepts_2 = this._genDomainConceptExtensions.baseDomainConcepts(concept);
        boolean _hasElements = false;
        for(final Classifier general : _baseDomainConcepts_2) {
          if (!_hasElements) {
            _hasElements = true;
            _builder.append(" extends ", "");
          } else {
            _builder.appendImmediate(", ", "");
          }
          String _name_1 = general.getName();
          _builder.append(_name_1, "");
        }
      }
      _builder.append(" {");
      _builder.newLineIfNotEmpty();
      {
        EList<GenDomainStructuralFeature> _features = concept.getFeatures();
        for(final GenDomainStructuralFeature feature : _features) {
          CharSequence _compileInterface = this.compileInterface(feature);
          _builder.append(_compileInterface, "");
          _builder.newLineIfNotEmpty();
        }
      }
      CharSequence _umlMappingInterfaceMethods = this.umlMappingInterfaceMethods(concept);
      _builder.append(_umlMappingInterfaceMethods, "");
      _builder.newLineIfNotEmpty();
      CharSequence _typeSelectFields = this.typeSelectFields(concept);
      _builder.append(_typeSelectFields, "");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  protected CharSequence _compileInterface(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  protected CharSequence _compileInterface(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _attributeAccessor = this.attributeAccessor(feature);
    _builder.append(_attributeAccessor, "");
    _builder.newLineIfNotEmpty();
    String _attributeModifier = this.attributeModifier(feature);
    _builder.append(_attributeModifier, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence attributeAccessor(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    String _accessorSignature = this._javaMethodSignaturesExtensions.accessorSignature(feature);
    _builder.append(_accessorSignature, "");
    _builder.append(";");
    return _builder;
  }
  
  public String attributeModifier(final GenDomainAttribute feature) {
    String _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isReadOnly = _domainAttribute.isReadOnly();
    boolean _not = (!_isReadOnly);
    if (_not) {
      String _switchResult = null;
      Property _domainAttribute_1 = feature.getDomainAttribute();
      AggregationKind _aggregation = _domainAttribute_1.getAggregation();
      int _ordinal = _aggregation.ordinal();
      final int _switchValue = _ordinal;
      boolean _matched = false;
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.COMPOSITE)) {
          _matched=true;
          String _xifexpression_1 = null;
          Property _domainAttribute_2 = feature.getDomainAttribute();
          boolean _isMultivalued = _domainAttribute_2.isMultivalued();
          if (_isMultivalued) {
            StringConcatenation _builder = new StringConcatenation();
            String _compositeMultivaluedAddExistingSignature = this._javaMethodSignaturesExtensions.compositeMultivaluedAddExistingSignature(feature);
            _builder.append(_compositeMultivaluedAddExistingSignature, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            String _compositeMultivaluedAddParemeterizedSignature = this._javaMethodSignaturesExtensions.compositeMultivaluedAddParemeterizedSignature(feature);
            _builder.append(_compositeMultivaluedAddParemeterizedSignature, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            {
              boolean _typeIsAbstract = this._genDomainStructuralFeatureExtensions.typeIsAbstract(feature);
              boolean _not_1 = (!_typeIsAbstract);
              if (_not_1) {
                String _compositeMultivalueAddSignature = this._javaMethodSignaturesExtensions.compositeMultivalueAddSignature(feature);
                _builder.append(_compositeMultivalueAddSignature, "");
                _builder.append(";");
              }
            }
            _builder.newLineIfNotEmpty();
            String _string = _builder.toString();
            _xifexpression_1 = _string;
          } else {
            StringConcatenation _builder_1 = new StringConcatenation();
            String _compositeAddExistingSignature = this._javaMethodSignaturesExtensions.compositeAddExistingSignature(feature);
            _builder_1.append(_compositeAddExistingSignature, "");
            _builder_1.append(";");
            _builder_1.newLineIfNotEmpty();
            String _compositeAddParemeterizedSignature = this._javaMethodSignaturesExtensions.compositeAddParemeterizedSignature(feature);
            _builder_1.append(_compositeAddParemeterizedSignature, "");
            _builder_1.append(";");
            _builder_1.newLineIfNotEmpty();
            {
              boolean _typeIsAbstract_1 = this._genDomainStructuralFeatureExtensions.typeIsAbstract(feature);
              boolean _not_2 = (!_typeIsAbstract_1);
              if (_not_2) {
                String _compositeAddSignature = this._javaMethodSignaturesExtensions.compositeAddSignature(feature);
                _builder_1.append(_compositeAddSignature, "");
                _builder_1.append(";");
              }
            }
            _builder_1.newLineIfNotEmpty();
            String _string_1 = _builder_1.toString();
            _xifexpression_1 = _string_1;
          }
          _switchResult = _xifexpression_1;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.SHARED)) {
          _matched=true;
          String _xifexpression_2 = null;
          Property _domainAttribute_3 = feature.getDomainAttribute();
          boolean _isMultivalued_1 = _domainAttribute_3.isMultivalued();
          if (_isMultivalued_1) {
            StringConcatenation _builder_2 = new StringConcatenation();
            String _sharedMultivaluedAddSignature = this._javaMethodSignaturesExtensions.sharedMultivaluedAddSignature(feature);
            _builder_2.append(_sharedMultivaluedAddSignature, "");
            _builder_2.append(";");
            String _string_2 = _builder_2.toString();
            _xifexpression_2 = _string_2;
          } else {
            StringConcatenation _builder_3 = new StringConcatenation();
            String _sharedAddSignature = this._javaMethodSignaturesExtensions.sharedAddSignature(feature);
            _builder_3.append(_sharedAddSignature, "");
            _builder_3.append(";");
            String _string_3 = _builder_3.toString();
            _xifexpression_2 = _string_3;
          }
          _switchResult = _xifexpression_2;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.NONE)) {
          _matched=true;
          String _xifexpression_3 = null;
          Property _domainAttribute_4 = feature.getDomainAttribute();
          boolean _isMultivalued_2 = _domainAttribute_4.isMultivalued();
          if (_isMultivalued_2) {
            StringConcatenation _builder_4 = new StringConcatenation();
            String _noneMultivaluedAddSignature = this._javaMethodSignaturesExtensions.noneMultivaluedAddSignature(feature);
            _builder_4.append(_noneMultivaluedAddSignature, "");
            _builder_4.append(";");
            String _string_4 = _builder_4.toString();
            _xifexpression_3 = _string_4;
          } else {
            StringConcatenation _builder_5 = new StringConcatenation();
            String _noneAddSignature = this._javaMethodSignaturesExtensions.noneAddSignature(feature);
            _builder_5.append(_noneAddSignature, "");
            _builder_5.append(";");
            String _string_5 = _builder_5.toString();
            _xifexpression_3 = _string_5;
          }
          _switchResult = _xifexpression_3;
        }
      }
      if (!_matched) {
        StringConcatenation _builder_6 = new StringConcatenation();
        String _string_6 = _builder_6.toString();
        _switchResult = _string_6;
      }
      _xifexpression = _switchResult;
    }
    return _xifexpression;
  }
  
  public String referenceModifier(final GenDomainReference feature) {
    String _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isReadOnly = _domainAttribute.isReadOnly();
    boolean _not = (!_isReadOnly);
    if (_not) {
      String _switchResult = null;
      Property _domainAttribute_1 = feature.getDomainAttribute();
      AggregationKind _aggregation = _domainAttribute_1.getAggregation();
      int _ordinal = _aggregation.ordinal();
      final int _switchValue = _ordinal;
      boolean _matched = false;
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.COMPOSITE)) {
          _matched=true;
          String _xifexpression_1 = null;
          Property _domainAttribute_2 = feature.getDomainAttribute();
          boolean _isMultivalued = _domainAttribute_2.isMultivalued();
          if (_isMultivalued) {
            StringConcatenation _builder = new StringConcatenation();
            String _compositeMultivaluedAddExistingSignature = this._javaMethodSignaturesExtensions.compositeMultivaluedAddExistingSignature(feature);
            _builder.append(_compositeMultivaluedAddExistingSignature, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            String _compositeMultivaluedAddParemeterizedSignature = this._javaMethodSignaturesExtensions.compositeMultivaluedAddParemeterizedSignature(feature);
            _builder.append(_compositeMultivaluedAddParemeterizedSignature, "");
            _builder.append(";");
            _builder.newLineIfNotEmpty();
            {
              boolean _typeIsAbstract = this._genDomainStructuralFeatureExtensions.typeIsAbstract(feature);
              boolean _not_1 = (!_typeIsAbstract);
              if (_not_1) {
                String _compositeMultivalueAddSignature = this._javaMethodSignaturesExtensions.compositeMultivalueAddSignature(feature);
                _builder.append(_compositeMultivalueAddSignature, "");
                _builder.append(";");
              }
            }
            _builder.newLineIfNotEmpty();
            String _string = _builder.toString();
            _xifexpression_1 = _string;
          } else {
            StringConcatenation _builder_1 = new StringConcatenation();
            String _compositeAddExistingSignature = this._javaMethodSignaturesExtensions.compositeAddExistingSignature(feature);
            _builder_1.append(_compositeAddExistingSignature, "");
            _builder_1.append(";");
            _builder_1.newLineIfNotEmpty();
            String _compositeAddParemeterizedSignature = this._javaMethodSignaturesExtensions.compositeAddParemeterizedSignature(feature);
            _builder_1.append(_compositeAddParemeterizedSignature, "");
            _builder_1.append(";");
            _builder_1.newLineIfNotEmpty();
            {
              boolean _typeIsAbstract_1 = this._genDomainStructuralFeatureExtensions.typeIsAbstract(feature);
              boolean _not_2 = (!_typeIsAbstract_1);
              if (_not_2) {
                String _compositeAddSignature = this._javaMethodSignaturesExtensions.compositeAddSignature(feature);
                _builder_1.append(_compositeAddSignature, "");
                _builder_1.append(";");
              }
            }
            _builder_1.newLineIfNotEmpty();
            String _string_1 = _builder_1.toString();
            _xifexpression_1 = _string_1;
          }
          _switchResult = _xifexpression_1;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.SHARED)) {
          _matched=true;
          String _xifexpression_2 = null;
          Property _domainAttribute_3 = feature.getDomainAttribute();
          boolean _isMultivalued_1 = _domainAttribute_3.isMultivalued();
          if (_isMultivalued_1) {
            StringConcatenation _builder_2 = new StringConcatenation();
            String _sharedMultivaluedAddSignature = this._javaMethodSignaturesExtensions.sharedMultivaluedAddSignature(feature);
            _builder_2.append(_sharedMultivaluedAddSignature, "");
            _builder_2.append(";");
            String _string_2 = _builder_2.toString();
            _xifexpression_2 = _string_2;
          } else {
            StringConcatenation _builder_3 = new StringConcatenation();
            String _sharedAddSignature = this._javaMethodSignaturesExtensions.sharedAddSignature(feature);
            _builder_3.append(_sharedAddSignature, "");
            _builder_3.append(";");
            String _string_3 = _builder_3.toString();
            _xifexpression_2 = _string_3;
          }
          _switchResult = _xifexpression_2;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.NONE)) {
          _matched=true;
          String _xifexpression_3 = null;
          Property _domainAttribute_4 = feature.getDomainAttribute();
          boolean _isMultivalued_2 = _domainAttribute_4.isMultivalued();
          if (_isMultivalued_2) {
            StringConcatenation _builder_4 = new StringConcatenation();
            String _noneMultivaluedAddSignature = this._javaMethodSignaturesExtensions.noneMultivaluedAddSignature(feature);
            _builder_4.append(_noneMultivaluedAddSignature, "");
            _builder_4.append(";");
            String _string_4 = _builder_4.toString();
            _xifexpression_3 = _string_4;
          } else {
            StringConcatenation _builder_5 = new StringConcatenation();
            String _noneAddSignature = this._javaMethodSignaturesExtensions.noneAddSignature(feature);
            _builder_5.append(_noneAddSignature, "");
            _builder_5.append(";");
            String _string_5 = _builder_5.toString();
            _xifexpression_3 = _string_5;
          }
          _switchResult = _xifexpression_3;
        }
      }
      if (!_matched) {
        StringConcatenation _builder_6 = new StringConcatenation();
        String _string_6 = _builder_6.toString();
        _switchResult = _string_6;
      }
      _xifexpression = _switchResult;
    }
    return _xifexpression;
  }
  
  protected CharSequence _compileInterface(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _referenceAccessor = this.referenceAccessor(feature);
    _builder.append(_referenceAccessor, "");
    _builder.newLineIfNotEmpty();
    String _referenceModifier = this.referenceModifier(feature);
    _builder.append(_referenceModifier, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence referenceAccessor(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    String _accessorSignature = this._javaMethodSignaturesExtensions.accessorSignature(feature);
    _builder.append(_accessorSignature, "");
    _builder.append(";");
    return _builder;
  }
  
  public List<GenDomainClassifier> interfaceImports(final GenDomainConcept concept) {
    List<GenDomainClassifier> _xblockexpression = null;
    {
      final EList<GenDomainConcept> baseInterfaces = concept.getGenerals();
      EList<GenDomainAttribute> _attributes = concept.getAttributes();
      final Function1<GenDomainAttribute,GenDomainDataType> _function = new Function1<GenDomainAttribute,GenDomainDataType>() {
          public GenDomainDataType apply(final GenDomainAttribute attribute) {
            GenDomainDataType _type = attribute.getType();
            return _type;
          }
        };
      final List<GenDomainDataType> attributeInterfaces = ListExtensions.<GenDomainAttribute, GenDomainDataType>map(_attributes, _function);
      EList<GenDomainReference> _references = concept.getReferences();
      final Function1<GenDomainReference,GenDomainConcept> _function_1 = new Function1<GenDomainReference,GenDomainConcept>() {
          public GenDomainConcept apply(final GenDomainReference ref) {
            GenDomainConcept _target = ref.getTarget();
            return _target;
          }
        };
      final List<GenDomainConcept> referenceInterfaces = ListExtensions.<GenDomainReference, GenDomainConcept>map(_references, _function_1);
      HashSet<GenDomainClassifier> _hashSet = new HashSet<GenDomainClassifier>();
      Set<GenDomainClassifier> allInclusions = _hashSet;
      allInclusions.addAll(baseInterfaces);
      allInclusions.addAll(attributeInterfaces);
      allInclusions.addAll(referenceInterfaces);
      allInclusions.remove(concept);
      List<GenDomainClassifier> _list = IterableExtensions.<GenDomainClassifier>toList(allInclusions);
      _xblockexpression = (_list);
    }
    return _xblockexpression;
  }
  
  public CharSequence umlMappingInterfaceMethods(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<org.eclipse.uml2.uml.Class> _umlMetaclasses = concept.getUmlMetaclasses();
      for(final org.eclipse.uml2.uml.Class umlClass : _umlMetaclasses) {
        _builder.append("org.eclipse.uml2.uml.");
        String _name = umlClass.getName();
        _builder.append(_name, "");
        _builder.append(" as");
        String _name_1 = umlClass.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_1);
        _builder.append(_firstUpper, "");
        _builder.append("();");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence typeSelectFields(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* A predicate which returns true if the Object is an");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* instance of ");
    String _javaInterfaceName = this._javaNamingExtensions.javaInterfaceName(concept);
    _builder.append(_javaInterfaceName, " ");
    _builder.newLineIfNotEmpty();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("static final TypeSelectPredicate<");
    String _javaInterfaceName_1 = this._javaNamingExtensions.javaInterfaceName(concept);
    _builder.append(_javaInterfaceName_1, "");
    _builder.append("> type = ");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("new TypeSelectPredicate<");
    String _javaInterfaceName_2 = this._javaNamingExtensions.javaInterfaceName(concept);
    _builder.append(_javaInterfaceName_2, "    ");
    _builder.append(">(");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("\"");
    NamedElement _domainElement = concept.getDomainElement();
    String _qualifiedName = _domainElement.getQualifiedName();
    _builder.append(_qualifiedName, "        ");
    _builder.append("\", //$NON-NLS-1$");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    String _javaInterfaceName_3 = this._javaNamingExtensions.javaInterfaceName(concept);
    _builder.append(_javaInterfaceName_3, "        ");
    _builder.append(".class); ");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence compileInterface(final GenDomainNamedElement feature) {
    if (feature instanceof GenDomainAttribute) {
      return _compileInterface((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainConcept) {
      return _compileInterface((GenDomainConcept)feature);
    } else if (feature instanceof GenDomainReference) {
      return _compileInterface((GenDomainReference)feature);
    } else if (feature instanceof GenDomainClassifier) {
      return _compileInterface((GenDomainClassifier)feature);
    } else if (feature instanceof GenDomainStructuralFeature) {
      return _compileInterface((GenDomainStructuralFeature)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
}
