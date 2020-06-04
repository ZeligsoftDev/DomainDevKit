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
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainAttribute;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainBlock;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainClassifier;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainDataType;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainEnum;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainReference;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainStructuralFeature;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainConceptExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainStructuralFeatureExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaImportExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaMethodSignaturesExtensions;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaNamingExtensions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class JavaImplementationGenerator {
  @Inject
  private GenDomainConceptExtensions _genDomainConceptExtensions;
  
  @Inject
  private GenDomainStructuralFeatureExtensions _genDomainStructuralFeatureExtensions;
  
  @Inject
  private JavaMethodSignaturesExtensions _javaMethodSignaturesExtensions;
  
  @Inject
  private JavaNamingExtensions _javaNamingExtensions;
  
  @Inject
  private JavaImportExtensions _javaImportExtensions;
  
  @Inject
  @Named(value = "Root Package")
  private String rootPackage;
  
  @Inject
  @Named(value = "Implementation SubPackage")
  private String implSubPackage;
  
  @Inject
  @Named(value = "Implementation Suffix")
  private String implSuffix;
  
  protected CharSequence _compileImplementation(final GenDomainClassifier concept, final String pkg) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  protected CharSequence _compileImplementation(final GenDomainConcept concept, final String pkg) {
    CharSequence _xblockexpression = null;
    {
      final EList<GenDomainConcept> baseConcepts = concept.getGenerals();
      final GenDomainConcept firstBaseConcept = IterableExtensions.<GenDomainConcept>head(baseConcepts);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      GenDomainBlock _block = concept.getBlock();
      String _implementationJavaPackage = this._javaNamingExtensions.implementationJavaPackage(_block);
      _builder.append(_implementationJavaPackage, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      CharSequence _implementationImports = this.implementationImports(concept);
      _builder.append(_implementationImports, "");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.newLine();
      _builder.append("public ");
      {
        org.eclipse.uml2.uml.Class _domainConcept = concept.getDomainConcept();
        boolean _isAbstract = _domainConcept.isAbstract();
        if (_isAbstract) {
          _builder.append("abstract ");
        }
      }
      _builder.append("class ");
      String _javaImplementationName = this._javaNamingExtensions.javaImplementationName(concept);
      _builder.append(_javaImplementationName, "");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      {
        boolean _notEquals = (!Objects.equal(firstBaseConcept, null));
        if (_notEquals) {
          _builder.append("extends ");
          String _javaImplementationName_1 = this._javaNamingExtensions.javaImplementationName(firstBaseConcept);
          _builder.append(_javaImplementationName_1, "    ");
          _builder.append(" ");
        } else {
          _builder.append("extends ZObjectImpl ");
        }
      }
      _builder.append("implements ");
      String _javaInterfaceName = this._javaNamingExtensions.javaInterfaceName(concept);
      _builder.append(_javaInterfaceName, "    ");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      CharSequence _implementationStandardFields = this.implementationStandardFields(concept);
      _builder.append(_implementationStandardFields, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      CharSequence _implementationFields = this.implementationFields(concept);
      _builder.append(_implementationFields, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      CharSequence _implementationConstructor = this.implementationConstructor(concept);
      _builder.append(_implementationConstructor, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      CharSequence _implementationStandardAccessors = this.implementationStandardAccessors(concept);
      _builder.append(_implementationStandardAccessors, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("    ");
      _builder.newLine();
      {
        List<GenDomainStructuralFeature> _allFeaturesToImplement = this._genDomainConceptExtensions.allFeaturesToImplement(concept);
        for(final GenDomainStructuralFeature feature : _allFeaturesToImplement) {
          _builder.append("    ");
          CharSequence _compileImplementation = this.compileImplementation(feature);
          _builder.append(_compileImplementation, "    ");
          _builder.append(" ");
          _builder.newLineIfNotEmpty();
          {
            boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
            if (_isInconsistentOverride) {
              {
                Set<GenDomainStructuralFeature> _overridenGenFeatures = this._genDomainStructuralFeatureExtensions.overridenGenFeatures(feature);
                for(final GenDomainStructuralFeature overriden : _overridenGenFeatures) {
                  _builder.append("    ");
                  CharSequence _compileOverridenImplementation = this.compileOverridenImplementation(overriden);
                  _builder.append(_compileOverridenImplementation, "    ");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
        }
      }
      _builder.append("    ");
      _builder.newLine();
      _builder.append("    ");
      CharSequence _umlMappingImplementationMethods = this.umlMappingImplementationMethods(concept);
      _builder.append(_umlMappingImplementationMethods, "    ");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence compileImplementation(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _accessorImplementation = this.accessorImplementation(feature);
    _builder.append(_accessorImplementation, "");
    _builder.newLineIfNotEmpty();
    CharSequence _modifierImplementation = this.modifierImplementation(feature);
    _builder.append(_modifierImplementation, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence _accessorImplementation(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  private CharSequence _accessorImplementation(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    CharSequence _featureAccessorReturnType = this._genDomainStructuralFeatureExtensions.featureAccessorReturnType(feature);
    _builder.append(_featureAccessorReturnType, "");
    _builder.append(" get");
    Property _domainAttribute = feature.getDomainAttribute();
    String _name = _domainAttribute.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    {
      boolean _and = false;
      Property _domainAttribute_1 = feature.getDomainAttribute();
      boolean _isMultivalued = _domainAttribute_1.isMultivalued();
      boolean _not = (!_isMultivalued);
      if (!_not) {
        _and = false;
      } else {
        boolean _hasPrimitiveType = this._genDomainStructuralFeatureExtensions.hasPrimitiveType(feature);
        _and = (_not && _hasPrimitiveType);
      }
      if (_and) {
        _builder.append("        ");
        _builder.append("final Object rawValue =");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("com.zeligsoft.base.zdl.util.ZDLUtil.getValue(eObject(), \"");
        String _conceptQualifiedName = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
        _builder.append(_conceptQualifiedName, "            ");
        _builder.append("\", \"");
        String _name_1 = feature.getName();
        _builder.append(_name_1, "            ");
        _builder.append("\");");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("return (");
        String _typeAsString = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
        _builder.append(_typeAsString, "        ");
        _builder.append(") rawValue;");
        _builder.newLineIfNotEmpty();
      } else {
        boolean _and_1 = false;
        Property _domainAttribute_2 = feature.getDomainAttribute();
        boolean _isMultivalued_1 = _domainAttribute_2.isMultivalued();
        boolean _not_1 = (!_isMultivalued_1);
        if (!_not_1) {
          _and_1 = false;
        } else {
          boolean _hasUMLType = this._genDomainStructuralFeatureExtensions.hasUMLType(feature);
          _and_1 = (_not_1 && _hasUMLType);
        }
        if (_and_1) {
          _builder.append("        ");
          _builder.append("final Object rawValue =");
          _builder.newLine();
          _builder.append("        ");
          _builder.append("    ");
          _builder.append("com.zeligsoft.base.zdl.util.ZDLUtil.getValue(eObject(), \"");
          String _conceptQualifiedName_1 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
          _builder.append(_conceptQualifiedName_1, "            ");
          _builder.append("\", \"");
          String _name_2 = feature.getName();
          _builder.append(_name_2, "            ");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("        ");
          _builder.append("return (");
          String _typeAsString_1 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
          _builder.append(_typeAsString_1, "        ");
          _builder.append(") rawValue;");
          _builder.newLineIfNotEmpty();
        } else {
          _builder.append("        ");
          _builder.append("final Object rawValue =");
          _builder.newLine();
          _builder.append("        ");
          _builder.append("    ");
          _builder.append("com.zeligsoft.base.zdl.util.ZDLUtil.getValue(eObject(), \"");
          String _conceptQualifiedName_2 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
          _builder.append(_conceptQualifiedName_2, "            ");
          _builder.append("\", \"");
          String _name_3 = feature.getName();
          _builder.append(_name_3, "            ");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("        ");
          _builder.newLine();
          _builder.append("        ");
          _builder.append("if(");
          String _featureFieldName = this.featureFieldName(feature);
          _builder.append(_featureFieldName, "        ");
          _builder.append(" == null) {");
          _builder.newLineIfNotEmpty();
          {
            Property _domainAttribute_3 = feature.getDomainAttribute();
            boolean _isMultivalued_2 = _domainAttribute_3.isMultivalued();
            if (_isMultivalued_2) {
              _builder.append("        ");
              String _featureFieldName_1 = this.featureFieldName(feature);
              _builder.append(_featureFieldName_1, "        ");
              _builder.append(" = new java.util.ArrayList<");
              String _typeAsString_2 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
              _builder.append(_typeAsString_2, "        ");
              _builder.append(">();");
              _builder.newLineIfNotEmpty();
              _builder.append("        ");
              _builder.append("@SuppressWarnings(\"unchecked\")");
              _builder.newLine();
              _builder.append("        ");
              _builder.append("final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;");
              _builder.newLine();
              _builder.append("        ");
              _builder.append("for(Object next : rawList) {");
              _builder.newLine();
              {
                boolean _hasPrimitiveType_1 = this._genDomainStructuralFeatureExtensions.hasPrimitiveType(feature);
                if (_hasPrimitiveType_1) {
                  _builder.append("        ");
                  _builder.append("    ");
                  String _featureFieldName_2 = this.featureFieldName(feature);
                  _builder.append(_featureFieldName_2, "            ");
                  _builder.append(".add((");
                  String _typeAsString_3 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
                  _builder.append(_typeAsString_3, "            ");
                  _builder.append(") next);");
                  _builder.newLineIfNotEmpty();
                } else {
                  GenDomainDataType _type = feature.getType();
                  if ((_type instanceof GenDomainEnum)) {
                    _builder.append("        ");
                    _builder.append("    ");
                    _builder.append("if(next instanceof org.eclipse.emf.ecore.EObject) {");
                    _builder.newLine();
                    _builder.append("        ");
                    _builder.append("    ");
                    _builder.append("    ");
                    String _typeAsString_4 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
                    _builder.append(_typeAsString_4, "                ");
                    _builder.append(" nextWrapper = ");
                    _builder.newLineIfNotEmpty();
                    _builder.append("        ");
                    _builder.append("    ");
                    _builder.append("         ");
                    String _typeAsString_5 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
                    _builder.append(_typeAsString_5, "                     ");
                    _builder.append(".create((org.eclipse.emf.ecore.EObject) next);");
                    _builder.newLineIfNotEmpty();
                    _builder.append("        ");
                    _builder.append("    ");
                    _builder.append("    ");
                    String _featureFieldName_3 = this.featureFieldName(feature);
                    _builder.append(_featureFieldName_3, "                ");
                    _builder.append(".add(nextWrapper);");
                    _builder.newLineIfNotEmpty();
                    _builder.append("        ");
                    _builder.append("    ");
                    _builder.append("}");
                    _builder.newLine();
                  } else {
                    boolean _hasZDLType = this._genDomainStructuralFeatureExtensions.hasZDLType(feature);
                    if (_hasZDLType) {
                      _builder.append("        ");
                      _builder.append("    ");
                      _builder.append("if(next instanceof org.eclipse.emf.ecore.EObject) {");
                      _builder.newLine();
                      _builder.append("        ");
                      _builder.append("    ");
                      _builder.append("    ");
                      String _typeAsString_6 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
                      _builder.append(_typeAsString_6, "                ");
                      _builder.append(" nextWrapper = ");
                      _builder.newLineIfNotEmpty();
                      _builder.append("        ");
                      _builder.append("    ");
                      _builder.append("         ");
                      _builder.append("ZDLFactoryRegistry.INSTANCE.create((org.eclipse.emf.ecore.EObject) next, ");
                      String _typeAsString_7 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
                      _builder.append(_typeAsString_7, "                     ");
                      _builder.append(".class);");
                      _builder.newLineIfNotEmpty();
                      _builder.append("        ");
                      _builder.append("    ");
                      _builder.append("    ");
                      String _featureFieldName_4 = this.featureFieldName(feature);
                      _builder.append(_featureFieldName_4, "                ");
                      _builder.append(".add(nextWrapper);");
                      _builder.newLineIfNotEmpty();
                      _builder.append("        ");
                      _builder.append("    ");
                      _builder.append("}");
                      _builder.newLine();
                    } else {
                      boolean _hasUMLType_1 = this._genDomainStructuralFeatureExtensions.hasUMLType(feature);
                      if (_hasUMLType_1) {
                        _builder.append("        ");
                        _builder.append("    ");
                        String _featureFieldName_5 = this.featureFieldName(feature);
                        _builder.append(_featureFieldName_5, "            ");
                        _builder.append(".add((");
                        String _typeAsString_8 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
                        _builder.append(_typeAsString_8, "            ");
                        _builder.append(") next);");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                  }
                }
              }
              _builder.append("        ");
              _builder.append("     ");
              _builder.newLine();
              _builder.append("        ");
              _builder.append("}");
              _builder.newLine();
            } else {
              {
                GenDomainDataType _type_1 = feature.getType();
                if ((_type_1 instanceof GenDomainEnum)) {
                  _builder.append("        ");
                  _builder.append("if(rawValue instanceof org.eclipse.emf.ecore.EObject) {");
                  _builder.newLine();
                  _builder.append("        ");
                  _builder.append("    ");
                  String _featureFieldName_6 = this.featureFieldName(feature);
                  _builder.append(_featureFieldName_6, "            ");
                  _builder.append(" = ");
                  String _typeAsString_9 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
                  _builder.append(_typeAsString_9, "            ");
                  _builder.append(".create((org.eclipse.emf.ecore.EObject) rawValue);");
                  _builder.newLineIfNotEmpty();
                  _builder.append("        ");
                  _builder.append("}");
                  _builder.newLine();
                } else {
                  boolean _hasZDLType_1 = this._genDomainStructuralFeatureExtensions.hasZDLType(feature);
                  if (_hasZDLType_1) {
                    _builder.append("        ");
                    _builder.append("if(rawValue instanceof org.eclipse.emf.ecore.EObject) {");
                    _builder.newLine();
                    _builder.append("        ");
                    _builder.append("  ");
                    String _featureFieldName_7 = this.featureFieldName(feature);
                    _builder.append(_featureFieldName_7, "          ");
                    _builder.append(" = ZDLFactoryRegistry.INSTANCE.create((org.eclipse.emf.ecore.EObject)rawValue, ");
                    String _typeAsString_10 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
                    _builder.append(_typeAsString_10, "          ");
                    _builder.append(".class);");
                    _builder.newLineIfNotEmpty();
                    _builder.append("        ");
                    _builder.append("}");
                    _builder.newLine();
                  }
                }
              }
            }
          }
          _builder.append("        ");
          _builder.append("}");
          _builder.newLine();
          _builder.append("        ");
          _builder.append("return ");
          String _featureFieldName_8 = this.featureFieldName(feature);
          _builder.append(_featureFieldName_8, "        ");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
      }
    }
    _builder.append("}");
    return _builder;
  }
  
  private CharSequence _accessorImplementation(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    CharSequence _featureAccessorReturnType = this._genDomainStructuralFeatureExtensions.featureAccessorReturnType(feature);
    _builder.append(_featureAccessorReturnType, "");
    _builder.append(" get");
    Property _domainAttribute = feature.getDomainAttribute();
    String _name = _domainAttribute.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("if(");
    String _featureFieldName = this.featureFieldName(feature);
    _builder.append(_featureFieldName, "    ");
    _builder.append(" == null) {");
    _builder.newLineIfNotEmpty();
    _builder.append("        ");
    _builder.append("final Object rawValue = ");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("com.zeligsoft.base.zdl.util.ZDLUtil.getValue(eObject(), \"");
    String _conceptQualifiedName = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
    _builder.append(_conceptQualifiedName, "            ");
    _builder.append("\", \"");
    Property _domainAttribute_1 = feature.getDomainAttribute();
    String _name_1 = _domainAttribute_1.getName();
    _builder.append(_name_1, "            ");
    _builder.append("\");");
    _builder.newLineIfNotEmpty();
    {
      Property _domainAttribute_2 = feature.getDomainAttribute();
      boolean _isMultivalued = _domainAttribute_2.isMultivalued();
      if (_isMultivalued) {
        _builder.append("        ");
        String _featureFieldName_1 = this.featureFieldName(feature);
        _builder.append(_featureFieldName_1, "        ");
        _builder.append(" = new java.util.ArrayList<");
        String _typeAsString = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
        _builder.append(_typeAsString, "        ");
        _builder.append(">();");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("@SuppressWarnings(\"unchecked\")");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("for(Object next : rawList) {");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("if(next instanceof org.eclipse.emf.ecore.EObject) {");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("        ");
        String _typeAsString_1 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
        _builder.append(_typeAsString_1, "                ");
        _builder.append(" nextWrapper = ");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("             ");
        _builder.append("ZDLFactoryRegistry.INSTANCE.create((org.eclipse.emf.ecore.EObject) next, ");
        String _typeAsString_2 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
        _builder.append(_typeAsString_2, "                     ");
        _builder.append(".class);");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("        ");
        String _featureFieldName_2 = this.featureFieldName(feature);
        _builder.append(_featureFieldName_2, "                ");
        _builder.append(".add(nextWrapper);");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("    ");
        _builder.append("}   ");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("}");
        _builder.newLine();
      } else {
        _builder.append("        ");
        _builder.append("if(rawValue instanceof org.eclipse.emf.ecore.EObject) {");
        _builder.newLine();
        _builder.append("        ");
        _builder.append("  ");
        String _featureFieldName_3 = this.featureFieldName(feature);
        _builder.append(_featureFieldName_3, "          ");
        _builder.append(" = ZDLFactoryRegistry.INSTANCE.create((org.eclipse.emf.ecore.EObject)rawValue, ");
        String _typeAsString_3 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
        _builder.append(_typeAsString_3, "          ");
        _builder.append(".class);");
        _builder.newLineIfNotEmpty();
        _builder.append("        ");
        _builder.append("}");
        _builder.newLine();
      }
    }
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("return ");
    String _featureFieldName_4 = this.featureFieldName(feature);
    _builder.append(_featureFieldName_4, "    ");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    return _builder;
  }
  
  public CharSequence compileOverridenImplementation(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    CharSequence _featureAccessorReturnType = this._genDomainStructuralFeatureExtensions.featureAccessorReturnType(feature);
    _builder.append(_featureAccessorReturnType, "");
    _builder.append(" get");
    Property _domainAttribute = feature.getDomainAttribute();
    String _name = _domainAttribute.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
    _builder.append(_inconsistentOverrideString, "");
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("throw new UnsupportedOperationException();");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    CharSequence _modifierImplementationOverriden = this.modifierImplementationOverriden(feature);
    _builder.append(_modifierImplementationOverriden, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  private CharSequence _modifierImplementation(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  private CharSequence _modifierImplementationOverriden(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  private CharSequence _modifierImplementation(final GenDomainAttribute feature) {
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
          String _compositeModifierImplementation = this.compositeModifierImplementation(feature);
          _switchResult = _compositeModifierImplementation;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.SHARED)) {
          _matched=true;
          String _sharedModifierImplementation = this.sharedModifierImplementation(feature);
          _switchResult = _sharedModifierImplementation;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.NONE)) {
          _matched=true;
          String _noneModifierImplementation = this.noneModifierImplementation(feature);
          _switchResult = _noneModifierImplementation;
        }
      }
      if (!_matched) {
        StringConcatenation _builder = new StringConcatenation();
        String _string = _builder.toString();
        _switchResult = _string;
      }
      _xifexpression = _switchResult;
    }
    return _xifexpression;
  }
  
  private CharSequence _modifierImplementationOverriden(final GenDomainAttribute feature) {
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
          String _compositeModifierImplementationOverriden = this.compositeModifierImplementationOverriden(feature);
          _switchResult = _compositeModifierImplementationOverriden;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.SHARED)) {
          _matched=true;
          String _sharedModifierImplementationOverriden = this.sharedModifierImplementationOverriden(feature);
          _switchResult = _sharedModifierImplementationOverriden;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.NONE)) {
          _matched=true;
          String _noneModifierImplementationOverriden = this.noneModifierImplementationOverriden(feature);
          _switchResult = _noneModifierImplementationOverriden;
        }
      }
      if (!_matched) {
        StringConcatenation _builder = new StringConcatenation();
        String _string = _builder.toString();
        _switchResult = _string;
      }
      _xifexpression = _switchResult;
    }
    return _xifexpression;
  }
  
  private CharSequence _modifierImplementation(final GenDomainReference feature) {
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
          String _compositeModifierImplementation = this.compositeModifierImplementation(feature);
          _switchResult = _compositeModifierImplementation;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.SHARED)) {
          _matched=true;
          String _sharedModifierImplementation = this.sharedModifierImplementation(feature);
          _switchResult = _sharedModifierImplementation;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.NONE)) {
          _matched=true;
          String _noneModifierImplementation = this.noneModifierImplementation(feature);
          _switchResult = _noneModifierImplementation;
        }
      }
      if (!_matched) {
        StringConcatenation _builder = new StringConcatenation();
        String _string = _builder.toString();
        _switchResult = _string;
      }
      _xifexpression = _switchResult;
    }
    return _xifexpression;
  }
  
  private CharSequence _modifierImplementationOverriden(final GenDomainReference feature) {
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
          String _compositeModifierImplementationOverriden = this.compositeModifierImplementationOverriden(feature);
          _switchResult = _compositeModifierImplementationOverriden;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.SHARED)) {
          _matched=true;
          String _sharedModifierImplementationOverriden = this.sharedModifierImplementationOverriden(feature);
          _switchResult = _sharedModifierImplementationOverriden;
        }
      }
      if (!_matched) {
        if (Objects.equal(_switchValue,AggregationKind.NONE)) {
          _matched=true;
          String _noneModifierImplementationOverriden = this.noneModifierImplementationOverriden(feature);
          _switchResult = _noneModifierImplementationOverriden;
        }
      }
      if (!_matched) {
        StringConcatenation _builder = new StringConcatenation();
        String _string = _builder.toString();
        _switchResult = _string;
      }
      _xifexpression = _switchResult;
    }
    return _xifexpression;
  }
  
  private String compositeModifierImplementation(final GenDomainStructuralFeature feature) {
    String _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isMultivalued = _domainAttribute.isMultivalued();
    if (_isMultivalued) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public ");
      String _compositeMultivaluedAddExistingSignature = this._javaMethodSignaturesExtensions.compositeMultivaluedAddExistingSignature(feature);
      _builder.append(_compositeMultivaluedAddExistingSignature, "");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("// make sure the ");
      String _featureName = this._genDomainStructuralFeatureExtensions.featureName(feature);
      _builder.append(_featureName, "	");
      _builder.append(" list is created");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _accessorName = this._javaMethodSignaturesExtensions.accessorName(feature);
      _builder.append(_accessorName, "	");
      _builder.append("();");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final Object rawValue = ZDLUtil.getValue(element, \"");
      String _conceptQualifiedName = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
      _builder.append(_conceptQualifiedName, "	");
      _builder.append("\", \"");
      String _featureName_1 = this._genDomainStructuralFeatureExtensions.featureName(feature);
      _builder.append(_featureName_1, "	");
      _builder.append("\");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("@SuppressWarnings(\"unchecked\")");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("rawList.add(val.eObject());");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if(");
      String _featureFieldName = this.featureFieldName(feature);
      _builder.append(_featureFieldName, "	");
      _builder.append(" != null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      String _featureFieldName_1 = this.featureFieldName(feature);
      _builder.append(_featureFieldName_1, "		");
      _builder.append(".add(val);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("public ");
      String _compositeMultivaluedAddParemeterizedSignature = this._javaMethodSignaturesExtensions.compositeMultivaluedAddParemeterizedSignature(feature);
      _builder.append(_compositeMultivaluedAddParemeterizedSignature, "");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("// make sure the ");
      Property _domainAttribute_1 = feature.getDomainAttribute();
      String _name = _domainAttribute_1.getName();
      _builder.append(_name, "	");
      _builder.append(" list is created");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      String _accessorName_1 = this._javaMethodSignaturesExtensions.accessorName(feature);
      _builder.append(_accessorName_1, "	");
      _builder.append("();");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("org.eclipse.emf.ecore.EObject newConcept = ");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("ZDLUtil.createZDLConcept(element, \"");
      String _conceptQualifiedName_1 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
      _builder.append(_conceptQualifiedName_1, "		");
      _builder.append("\", \"");
      String _featureName_2 = this._genDomainStructuralFeatureExtensions.featureName(feature);
      _builder.append(_featureName_2, "		");
      _builder.append("\", concept);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("T element = ZDLFactoryRegistry.INSTANCE");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append(".create((org.eclipse.emf.ecore.EObject) newConcept,");
      _builder.newLine();
      _builder.append("\t\t\t\t\t");
      _builder.append("typeToCreate);");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("if(");
      String _featureFieldName_2 = this.featureFieldName(feature);
      _builder.append(_featureFieldName_2, "	");
      _builder.append(" != null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t");
      String _featureFieldName_3 = this.featureFieldName(feature);
      _builder.append(_featureFieldName_3, "		");
      _builder.append(".add(element);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("return element;");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      {
        boolean _typeIsAbstract = this._genDomainStructuralFeatureExtensions.typeIsAbstract(feature);
        boolean _not = (!_typeIsAbstract);
        if (_not) {
          _builder.append("public ");
          String _compositeMultivalueAddSignature = this._javaMethodSignaturesExtensions.compositeMultivalueAddSignature(feature);
          _builder.append(_compositeMultivalueAddSignature, "");
          _builder.append("{");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("// make sure the ");
          String _featureName_3 = this._genDomainStructuralFeatureExtensions.featureName(feature);
          _builder.append(_featureName_3, "	");
          _builder.append(" list is created");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _accessorName_2 = this._javaMethodSignaturesExtensions.accessorName(feature);
          _builder.append(_accessorName_2, "	");
          _builder.append("();");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("org.eclipse.emf.ecore.EObject newConcept = ");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("ZDLUtil.createZDLConcept(element, \"");
          String _conceptQualifiedName_2 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
          _builder.append(_conceptQualifiedName_2, "		");
          _builder.append("\", \"");
          String _featureName_4 = this._genDomainStructuralFeatureExtensions.featureName(feature);
          _builder.append(_featureName_4, "		");
          _builder.append("\", \"");
          String _featureTypeQualifiedName = this._genDomainStructuralFeatureExtensions.featureTypeQualifiedName(feature);
          _builder.append(_featureTypeQualifiedName, "		");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
          _builder.append(_featureModifierType, "	");
          _builder.append(" element = ZDLFactoryRegistry.INSTANCE");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t\t");
          _builder.append(".create((org.eclipse.emf.ecore.EObject) newConcept,");
          _builder.newLine();
          _builder.append("\t\t\t\t\t");
          String _featureModifierType_1 = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
          _builder.append(_featureModifierType_1, "					");
          _builder.append(".class);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("if(");
          String _featureFieldName_4 = this.featureFieldName(feature);
          _builder.append(_featureFieldName_4, "	");
          _builder.append(" != null) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          String _featureFieldName_5 = this.featureFieldName(feature);
          _builder.append(_featureFieldName_5, "		");
          _builder.append(".add(element);");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("return element;");
          _builder.newLine();
          _builder.append("}");
        }
      }
      _builder.newLineIfNotEmpty();
      String _string = _builder.toString();
      _xifexpression = _string;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("public ");
      String _compositeAddExistingSignature = this._javaMethodSignaturesExtensions.compositeAddExistingSignature(feature);
      _builder_1.append(_compositeAddExistingSignature, "");
      _builder_1.append("{");
      _builder_1.newLineIfNotEmpty();
      {
        boolean _or = false;
        boolean _or_1 = false;
        boolean _hasPrimitiveType = this._genDomainStructuralFeatureExtensions.hasPrimitiveType(feature);
        if (_hasPrimitiveType) {
          _or_1 = true;
        } else {
          boolean _hasEnumerationType = this._genDomainStructuralFeatureExtensions.hasEnumerationType(feature);
          _or_1 = (_hasPrimitiveType || _hasEnumerationType);
        }
        if (_or_1) {
          _or = true;
        } else {
          boolean _hasUMLType = this._genDomainStructuralFeatureExtensions.hasUMLType(feature);
          _or = (_or_1 || _hasUMLType);
        }
        if (_or) {
          _builder_1.append("\t");
          _builder_1.append("ZDLUtil.setValue(element, \"");
          String _conceptQualifiedName_3 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
          _builder_1.append(_conceptQualifiedName_3, "	");
          _builder_1.append("\", \"");
          String _featureName_5 = this._genDomainStructuralFeatureExtensions.featureName(feature);
          _builder_1.append(_featureName_5, "	");
          _builder_1.append("\", val);");
          _builder_1.newLineIfNotEmpty();
        } else {
          _builder_1.append("\t");
          _builder_1.append("ZDLUtil.setValue(element, \"");
          String _conceptQualifiedName_4 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
          _builder_1.append(_conceptQualifiedName_4, "	");
          _builder_1.append("\", \"");
          String _featureName_6 = this._genDomainStructuralFeatureExtensions.featureName(feature);
          _builder_1.append(_featureName_6, "	");
          _builder_1.append("\", val.eObject());");
          _builder_1.newLineIfNotEmpty();
        }
      }
      _builder_1.append("}");
      _builder_1.newLine();
      _builder_1.append("public ");
      String _compositeAddParemeterizedSignature = this._javaMethodSignaturesExtensions.compositeAddParemeterizedSignature(feature);
      _builder_1.append(_compositeAddParemeterizedSignature, "");
      _builder_1.append("{");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t");
      _builder_1.append("org.eclipse.emf.ecore.EObject newConcept = ");
      _builder_1.newLine();
      _builder_1.append("\t\t");
      _builder_1.append("ZDLUtil.createZDLConcept(element, \"");
      String _conceptQualifiedName_5 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
      _builder_1.append(_conceptQualifiedName_5, "		");
      _builder_1.append("\", \"");
      String _featureName_7 = this._genDomainStructuralFeatureExtensions.featureName(feature);
      _builder_1.append(_featureName_7, "		");
      _builder_1.append("\", concept);");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t");
      _builder_1.append("T element = ZDLFactoryRegistry.INSTANCE");
      _builder_1.newLine();
      _builder_1.append("\t\t\t");
      _builder_1.append(".create((org.eclipse.emf.ecore.EObject) newConcept,");
      _builder_1.newLine();
      _builder_1.append("\t\t\t\t\t");
      _builder_1.append("typeToCreate);");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("return element;");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      {
        boolean _typeIsAbstract_1 = this._genDomainStructuralFeatureExtensions.typeIsAbstract(feature);
        boolean _not_1 = (!_typeIsAbstract_1);
        if (_not_1) {
          _builder_1.append("public ");
          String _compositeAddSignature = this._javaMethodSignaturesExtensions.compositeAddSignature(feature);
          _builder_1.append(_compositeAddSignature, "");
          _builder_1.append("{");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("org.eclipse.emf.ecore.EObject newConcept = ");
          _builder_1.newLine();
          _builder_1.append("\t\t");
          _builder_1.append("ZDLUtil.createZDLConcept(element, \"");
          String _conceptQualifiedName_6 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
          _builder_1.append(_conceptQualifiedName_6, "		");
          _builder_1.append("\", \"");
          String _featureName_8 = this._genDomainStructuralFeatureExtensions.featureName(feature);
          _builder_1.append(_featureName_8, "		");
          _builder_1.append("\", \"");
          String _featureTypeQualifiedName_1 = this._genDomainStructuralFeatureExtensions.featureTypeQualifiedName(feature);
          _builder_1.append(_featureTypeQualifiedName_1, "		");
          _builder_1.append("\");");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          String _featureModifierType_2 = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
          _builder_1.append(_featureModifierType_2, "	");
          _builder_1.append(" element = ZDLFactoryRegistry.INSTANCE");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t\t\t");
          _builder_1.append(".create((org.eclipse.emf.ecore.EObject) newConcept,");
          _builder_1.newLine();
          _builder_1.append("\t\t\t\t\t");
          String _featureModifierType_3 = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
          _builder_1.append(_featureModifierType_3, "					");
          _builder_1.append(".class);");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("return element;");
          _builder_1.newLine();
          _builder_1.append("}");
        }
      }
      _builder_1.newLineIfNotEmpty();
      String _string_1 = _builder_1.toString();
      _xifexpression = _string_1;
    }
    return _xifexpression;
  }
  
  private String compositeModifierImplementationOverriden(final GenDomainStructuralFeature feature) {
    String _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isMultivalued = _domainAttribute.isMultivalued();
    if (_isMultivalued) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public ");
      String _compositeMultivaluedAddExistingSignature = this._javaMethodSignaturesExtensions.compositeMultivaluedAddExistingSignature(feature);
      _builder.append(_compositeMultivaluedAddExistingSignature, "");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("throw new UnsupportedOperationException();");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("public ");
      String _compositeMultivaluedAddParemeterizedSignature = this._javaMethodSignaturesExtensions.compositeMultivaluedAddParemeterizedSignature(feature);
      _builder.append(_compositeMultivaluedAddParemeterizedSignature, "");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("throw new UnsupportedOperationException();");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      {
        boolean _typeIsAbstract = this._genDomainStructuralFeatureExtensions.typeIsAbstract(feature);
        boolean _not = (!_typeIsAbstract);
        if (_not) {
          _builder.append("public ");
          String _compositeMultivalueAddSignature = this._javaMethodSignaturesExtensions.compositeMultivalueAddSignature(feature);
          _builder.append(_compositeMultivalueAddSignature, "");
          _builder.append("{");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("throw new UnsupportedOperationException();");
          _builder.newLine();
          _builder.append("}");
        }
      }
      _builder.newLineIfNotEmpty();
      String _string = _builder.toString();
      _xifexpression = _string;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("public ");
      String _compositeAddExistingSignature = this._javaMethodSignaturesExtensions.compositeAddExistingSignature(feature);
      _builder_1.append(_compositeAddExistingSignature, "");
      _builder_1.append("{");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t");
      _builder_1.append("throw new UnsupportedOperationException();");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      _builder_1.append("public ");
      String _compositeAddParemeterizedSignature = this._javaMethodSignaturesExtensions.compositeAddParemeterizedSignature(feature);
      _builder_1.append(_compositeAddParemeterizedSignature, "");
      _builder_1.append("{");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t");
      _builder_1.append("throw new UnsupportedOperationException();");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      {
        boolean _typeIsAbstract_1 = this._genDomainStructuralFeatureExtensions.typeIsAbstract(feature);
        boolean _not_1 = (!_typeIsAbstract_1);
        if (_not_1) {
          _builder_1.append("public ");
          String _compositeAddSignature = this._javaMethodSignaturesExtensions.compositeAddSignature(feature);
          _builder_1.append(_compositeAddSignature, "");
          _builder_1.append("{");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("\t");
          _builder_1.append("throw new UnsupportedOperationException();");
          _builder_1.newLine();
          _builder_1.append("}");
        }
      }
      _builder_1.newLineIfNotEmpty();
      String _string_1 = _builder_1.toString();
      _xifexpression = _string_1;
    }
    return _xifexpression;
  }
  
  private String sharedModifierImplementation(final GenDomainStructuralFeature feature) {
    String _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isMultivalued = _domainAttribute.isMultivalued();
    if (_isMultivalued) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public ");
      String _sharedMultivaluedAddSignature = this._javaMethodSignaturesExtensions.sharedMultivaluedAddSignature(feature);
      _builder.append(_sharedMultivaluedAddSignature, "");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("// make sure the ");
      String _featureName = this._genDomainStructuralFeatureExtensions.featureName(feature);
      _builder.append(_featureName, "				");
      _builder.append(" list is created");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      String _accessorName = this._javaMethodSignaturesExtensions.accessorName(feature);
      _builder.append(_accessorName, "				");
      _builder.append("();");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("final Object rawValue = ZDLUtil.getValue(element, \"");
      String _conceptQualifiedName = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
      _builder.append(_conceptQualifiedName, "				");
      _builder.append("\", \"");
      String _featureName_1 = this._genDomainStructuralFeatureExtensions.featureName(feature);
      _builder.append(_featureName_1, "				");
      _builder.append("\");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("@SuppressWarnings(\"unchecked\")");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("rawList.add(val.eObject());");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("if(");
      String _featureFieldName = this.featureFieldName(feature);
      _builder.append(_featureFieldName, "				");
      _builder.append(" != null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t\t");
      String _featureFieldName_1 = this.featureFieldName(feature);
      _builder.append(_featureFieldName_1, "					");
      _builder.append(".add(val);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      String _string = _builder.toString();
      _xifexpression = _string;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("public ");
      String _sharedAddSignature = this._javaMethodSignaturesExtensions.sharedAddSignature(feature);
      _builder_1.append(_sharedAddSignature, "");
      _builder_1.append("{");
      _builder_1.newLineIfNotEmpty();
      {
        boolean _or = false;
        boolean _hasPrimitiveType = this._genDomainStructuralFeatureExtensions.hasPrimitiveType(feature);
        if (_hasPrimitiveType) {
          _or = true;
        } else {
          boolean _hasUMLType = this._genDomainStructuralFeatureExtensions.hasUMLType(feature);
          _or = (_hasPrimitiveType || _hasUMLType);
        }
        if (_or) {
          _builder_1.append("\t\t\t\t");
          _builder_1.append("ZDLUtil.setValue(element, \"");
          String _conceptQualifiedName_1 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
          _builder_1.append(_conceptQualifiedName_1, "				");
          _builder_1.append("\", \"");
          String _featureName_2 = this._genDomainStructuralFeatureExtensions.featureName(feature);
          _builder_1.append(_featureName_2, "				");
          _builder_1.append("\", val);");
          _builder_1.newLineIfNotEmpty();
        } else {
          boolean _hasEnumerationType = this._genDomainStructuralFeatureExtensions.hasEnumerationType(feature);
          if (_hasEnumerationType) {
            _builder_1.append("\t\t\t\t");
            _builder_1.append("ZDLUtil.setValue(element, \"");
            String _conceptQualifiedName_2 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
            _builder_1.append(_conceptQualifiedName_2, "				");
            _builder_1.append("\", \"");
            String _featureName_3 = this._genDomainStructuralFeatureExtensions.featureName(feature);
            _builder_1.append(_featureName_3, "				");
            _builder_1.append("\", val.eObject(element));");
            _builder_1.newLineIfNotEmpty();
          } else {
            _builder_1.append("\t\t\t\t");
            _builder_1.append("ZDLUtil.setValue(element, \"");
            String _conceptQualifiedName_3 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
            _builder_1.append(_conceptQualifiedName_3, "				");
            _builder_1.append("\", \"");
            String _featureName_4 = this._genDomainStructuralFeatureExtensions.featureName(feature);
            _builder_1.append(_featureName_4, "				");
            _builder_1.append("\", val.eObject());");
            _builder_1.newLineIfNotEmpty();
          }
        }
      }
      _builder_1.append("\t\t\t");
      _builder_1.append("}");
      String _string_1 = _builder_1.toString();
      _xifexpression = _string_1;
    }
    return _xifexpression;
  }
  
  private String sharedModifierImplementationOverriden(final GenDomainStructuralFeature feature) {
    String _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isMultivalued = _domainAttribute.isMultivalued();
    if (_isMultivalued) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public ");
      String _sharedMultivaluedAddSignature = this._javaMethodSignaturesExtensions.sharedMultivaluedAddSignature(feature);
      _builder.append(_sharedMultivaluedAddSignature, "");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("throw new UnsupportedOperationException();");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      String _string = _builder.toString();
      _xifexpression = _string;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("public ");
      String _sharedAddSignature = this._javaMethodSignaturesExtensions.sharedAddSignature(feature);
      _builder_1.append(_sharedAddSignature, "");
      _builder_1.append("{");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t\t\t\t");
      _builder_1.append("throw new UnsupportedOperationException();");
      _builder_1.newLine();
      _builder_1.append("\t\t\t");
      _builder_1.append("}");
      String _string_1 = _builder_1.toString();
      _xifexpression = _string_1;
    }
    return _xifexpression;
  }
  
  private String noneModifierImplementation(final GenDomainStructuralFeature feature) {
    String _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isMultivalued = _domainAttribute.isMultivalued();
    if (_isMultivalued) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public ");
      String _noneMultivaluedAddSignature = this._javaMethodSignaturesExtensions.noneMultivaluedAddSignature(feature);
      _builder.append(_noneMultivaluedAddSignature, "");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("// make sure the ");
      String _featureName = this._genDomainStructuralFeatureExtensions.featureName(feature);
      _builder.append(_featureName, "				");
      _builder.append(" list is created");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      String _accessorName = this._javaMethodSignaturesExtensions.accessorName(feature);
      _builder.append(_accessorName, "				");
      _builder.append("();");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("final Object rawValue = ZDLUtil.getValue(element, \"");
      String _conceptQualifiedName = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
      _builder.append(_conceptQualifiedName, "				");
      _builder.append("\", \"");
      String _featureName_1 = this._genDomainStructuralFeatureExtensions.featureName(feature);
      _builder.append(_featureName_1, "				");
      _builder.append("\");");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("@SuppressWarnings(\"unchecked\")");
      _builder.newLine();
      _builder.append("\t\t\t\t");
      _builder.append("final java.util.List<Object> rawList = (java.util.List<Object>) rawValue;");
      _builder.newLine();
      {
        boolean _hasPrimitiveType = this._genDomainStructuralFeatureExtensions.hasPrimitiveType(feature);
        if (_hasPrimitiveType) {
          _builder.append("\t\t\t\t");
          _builder.append("rawList.add(val);");
          _builder.newLine();
        } else {
          boolean _hasEnumerationType = this._genDomainStructuralFeatureExtensions.hasEnumerationType(feature);
          if (_hasEnumerationType) {
            _builder.append("\t\t\t\t");
            _builder.append("rawList.add(val.eObject(element));");
            _builder.newLine();
          } else {
            _builder.append("\t\t\t\t");
            _builder.append("rawList.add(val.eObject());");
            _builder.newLine();
          }
        }
      }
      _builder.append("\t\t\t\t");
      _builder.append("if(");
      String _featureFieldName = this.featureFieldName(feature);
      _builder.append(_featureFieldName, "				");
      _builder.append(" != null) {");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t\t");
      String _featureFieldName_1 = this.featureFieldName(feature);
      _builder.append(_featureFieldName_1, "					");
      _builder.append(".add(val);");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      String _string = _builder.toString();
      _xifexpression = _string;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("public ");
      String _noneAddSignature = this._javaMethodSignaturesExtensions.noneAddSignature(feature);
      _builder_1.append(_noneAddSignature, "");
      _builder_1.append("{");
      _builder_1.newLineIfNotEmpty();
      {
        boolean _or = false;
        boolean _hasPrimitiveType_1 = this._genDomainStructuralFeatureExtensions.hasPrimitiveType(feature);
        if (_hasPrimitiveType_1) {
          _or = true;
        } else {
          boolean _hasUMLType = this._genDomainStructuralFeatureExtensions.hasUMLType(feature);
          _or = (_hasPrimitiveType_1 || _hasUMLType);
        }
        if (_or) {
          _builder_1.append("\t\t\t\t");
          _builder_1.append("ZDLUtil.setValue(element, \"");
          String _conceptQualifiedName_1 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
          _builder_1.append(_conceptQualifiedName_1, "				");
          _builder_1.append("\", \"");
          String _featureName_2 = this._genDomainStructuralFeatureExtensions.featureName(feature);
          _builder_1.append(_featureName_2, "				");
          _builder_1.append("\", val);");
          _builder_1.newLineIfNotEmpty();
        } else {
          boolean _hasEnumerationType_1 = this._genDomainStructuralFeatureExtensions.hasEnumerationType(feature);
          if (_hasEnumerationType_1) {
            _builder_1.append("\t\t\t\t");
            _builder_1.append("ZDLUtil.setValue(element, \"");
            String _conceptQualifiedName_2 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
            _builder_1.append(_conceptQualifiedName_2, "				");
            _builder_1.append("\", \"");
            String _featureName_3 = this._genDomainStructuralFeatureExtensions.featureName(feature);
            _builder_1.append(_featureName_3, "				");
            _builder_1.append("\", val.eObject(element));");
            _builder_1.newLineIfNotEmpty();
          } else {
            _builder_1.append("\t\t\t\t");
            _builder_1.append("ZDLUtil.setValue(element, \"");
            String _conceptQualifiedName_3 = this._genDomainStructuralFeatureExtensions.conceptQualifiedName(feature);
            _builder_1.append(_conceptQualifiedName_3, "				");
            _builder_1.append("\", \"");
            String _featureName_4 = this._genDomainStructuralFeatureExtensions.featureName(feature);
            _builder_1.append(_featureName_4, "				");
            _builder_1.append("\", val.eObject());");
            _builder_1.newLineIfNotEmpty();
          }
        }
      }
      _builder_1.append("\t\t\t");
      _builder_1.append("}");
      String _string_1 = _builder_1.toString();
      _xifexpression = _string_1;
    }
    return _xifexpression;
  }
  
  private String noneModifierImplementationOverriden(final GenDomainStructuralFeature feature) {
    String _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isMultivalued = _domainAttribute.isMultivalued();
    if (_isMultivalued) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("public ");
      String _noneMultivaluedAddSignature = this._javaMethodSignaturesExtensions.noneMultivaluedAddSignature(feature);
      _builder.append(_noneMultivaluedAddSignature, "");
      _builder.append("{");
      _builder.newLineIfNotEmpty();
      _builder.append("\t\t\t\t");
      _builder.append("throw new UnsupportedOperationException();");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("}");
      String _string = _builder.toString();
      _xifexpression = _string;
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("public ");
      String _noneAddSignature = this._javaMethodSignaturesExtensions.noneAddSignature(feature);
      _builder_1.append(_noneAddSignature, "");
      _builder_1.append("{");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t\t\t\t");
      _builder_1.append("throw new UnsupportedOperationException();");
      _builder_1.newLine();
      _builder_1.append("\t\t\t");
      _builder_1.append("}");
      String _string_1 = _builder_1.toString();
      _xifexpression = _string_1;
    }
    return _xifexpression;
  }
  
  public CharSequence implementationImports(final GenDomainConcept concept) {
    CharSequence _xblockexpression = null;
    {
      EList<GenDomainConcept> _generals = concept.getGenerals();
      final GenDomainConcept firstBaseConcept = IterableExtensions.<GenDomainConcept>head(_generals);
      final ArrayList<GenDomainConcept> baseConceptsToImplement = this._genDomainConceptExtensions.baseDomainConceptsToImplement(concept);
      EList<GenDomainStructuralFeature> _features = concept.getFeatures();
      final Function1<GenDomainStructuralFeature,GenDomainClassifier> _function = new Function1<GenDomainStructuralFeature,GenDomainClassifier>() {
          public GenDomainClassifier apply(final GenDomainStructuralFeature feature) {
            GenDomainClassifier _featureType = JavaImplementationGenerator.this._genDomainStructuralFeatureExtensions.featureType(feature);
            return _featureType;
          }
        };
      final List<GenDomainClassifier> featureTypes = ListExtensions.<GenDomainStructuralFeature, GenDomainClassifier>map(_features, _function);
      EList<GenDomainStructuralFeature> _features_1 = concept.getFeatures();
      final Function1<GenDomainStructuralFeature,Boolean> _function_1 = new Function1<GenDomainStructuralFeature,Boolean>() {
          public Boolean apply(final GenDomainStructuralFeature feature) {
            boolean _isInconsistentOverride = JavaImplementationGenerator.this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
            return Boolean.valueOf(_isInconsistentOverride);
          }
        };
      Iterable<GenDomainStructuralFeature> _filter = IterableExtensions.<GenDomainStructuralFeature>filter(_features_1, _function_1);
      final Function1<GenDomainStructuralFeature,Set<GenDomainStructuralFeature>> _function_2 = new Function1<GenDomainStructuralFeature,Set<GenDomainStructuralFeature>>() {
          public Set<GenDomainStructuralFeature> apply(final GenDomainStructuralFeature overriden) {
            Set<GenDomainStructuralFeature> _overridenGenFeatures = JavaImplementationGenerator.this._genDomainStructuralFeatureExtensions.overridenGenFeatures(overriden);
            return _overridenGenFeatures;
          }
        };
      Iterable<Set<GenDomainStructuralFeature>> _map = IterableExtensions.<GenDomainStructuralFeature, Set<GenDomainStructuralFeature>>map(_filter, _function_2);
      Iterable<GenDomainStructuralFeature> _flatten = Iterables.<GenDomainStructuralFeature>concat(_map);
      final Function1<GenDomainStructuralFeature,GenDomainClassifier> _function_3 = new Function1<GenDomainStructuralFeature,GenDomainClassifier>() {
          public GenDomainClassifier apply(final GenDomainStructuralFeature baseFeature) {
            GenDomainClassifier _featureType = JavaImplementationGenerator.this._genDomainStructuralFeatureExtensions.featureType(baseFeature);
            return _featureType;
          }
        };
      final Iterable<GenDomainClassifier> inconsistentOverrideInterfaces = IterableExtensions.<GenDomainStructuralFeature, GenDomainClassifier>map(_flatten, _function_3);
      final Function1<GenDomainConcept,List<GenDomainClassifier>> _function_4 = new Function1<GenDomainConcept,List<GenDomainClassifier>>() {
          public List<GenDomainClassifier> apply(final GenDomainConcept baseConcept) {
            EList<GenDomainStructuralFeature> _features = baseConcept.getFeatures();
            final Function1<GenDomainStructuralFeature,GenDomainClassifier> _function = new Function1<GenDomainStructuralFeature,GenDomainClassifier>() {
                public GenDomainClassifier apply(final GenDomainStructuralFeature feature) {
                  GenDomainClassifier _featureType = JavaImplementationGenerator.this._genDomainStructuralFeatureExtensions.featureType(feature);
                  return _featureType;
                }
              };
            List<GenDomainClassifier> _map = ListExtensions.<GenDomainStructuralFeature, GenDomainClassifier>map(_features, _function);
            return _map;
          }
        };
      List<List<GenDomainClassifier>> _map_1 = ListExtensions.<GenDomainConcept, List<GenDomainClassifier>>map(baseConceptsToImplement, _function_4);
      final Iterable<GenDomainClassifier> baseFeatureTypes = Iterables.<GenDomainClassifier>concat(_map_1);
      HashSet<GenDomainClassifier> _hashSet = new HashSet<GenDomainClassifier>();
      Set<GenDomainClassifier> allInclusions = _hashSet;
      allInclusions.addAll(featureTypes);
      Iterables.<GenDomainClassifier>addAll(allInclusions, baseFeatureTypes);
      Iterables.<GenDomainClassifier>addAll(allInclusions, inconsistentOverrideInterfaces);
      allInclusions.remove(concept);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("import com.zeligsoft.base.zdl.staticapi.util.ZDLFactoryRegistry;");
      _builder.newLine();
      {
        boolean _equals = Objects.equal(firstBaseConcept, null);
        if (_equals) {
          _builder.append("import com.zeligsoft.base.zdl.staticapi.internal.core.ZObjectImpl;");
          _builder.newLine();
        }
      }
      _builder.newLine();
      _builder.append("import ");
      String _qualifiedName = this._javaNamingExtensions.qualifiedName(concept);
      _builder.append(_qualifiedName, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      {
        boolean _notEquals = (!Objects.equal(firstBaseConcept, null));
        if (_notEquals) {
          _builder.append("import ");
          CharSequence _implementationQualifiedName = this._javaNamingExtensions.implementationQualifiedName(firstBaseConcept);
          _builder.append(_implementationQualifiedName, "");
          _builder.append(";");
        }
      }
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      {
        Iterable<GenDomainClassifier> _filterNull = IterableExtensions.<GenDomainClassifier>filterNull(allInclusions);
        for(final GenDomainClassifier inclusion : _filterNull) {
          CharSequence _inclusionHelper = this.inclusionHelper(inclusion);
          _builder.append(_inclusionHelper, "");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      _builder.append("import com.zeligsoft.base.zdl.util.ZDLUtil;");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence inclusionHelper(final GenDomainClassifier type) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _generateImport = this._javaImportExtensions.generateImport(type);
    _builder.append(_generateImport, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence umlMappingImplementationMethods(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Set<org.eclipse.uml2.uml.Class> _allMetaclassesToImplementAccessorsFor = this._genDomainConceptExtensions.allMetaclassesToImplementAccessorsFor(concept);
      for(final org.eclipse.uml2.uml.Class umlClass : _allMetaclassesToImplementAccessorsFor) {
        _builder.newLineIfNotEmpty();
        _builder.append("@Override");
        _builder.newLine();
        _builder.append("public org.eclipse.uml2.uml.");
        String _name = umlClass.getName();
        _builder.append(_name, "");
        _builder.append(" as");
        String _name_1 = umlClass.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_1);
        _builder.append(_firstUpper, "");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("    ");
        _builder.append("return (org.eclipse.uml2.uml.");
        String _name_2 = umlClass.getName();
        _builder.append(_name_2, "    ");
        _builder.append(") eObject();");
        _builder.newLineIfNotEmpty();
        _builder.append("}");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence implementationStandardFields(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence implementationStandardAccessors(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence implementationConstructor(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public ");
    String _javaImplementationName = this._javaNamingExtensions.javaImplementationName(concept);
    _builder.append(_javaImplementationName, "");
    _builder.append("(org.eclipse.emf.ecore.EObject element) {");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("super(element);");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence implementationFields(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    {
      List<GenDomainStructuralFeature> _allFeaturesToImplement = this._genDomainConceptExtensions.allFeaturesToImplement(concept);
      for(final GenDomainStructuralFeature feature : _allFeaturesToImplement) {
        CharSequence _implementationField = this.implementationField(feature);
        _builder.append(_implementationField, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  private CharSequence implementationField(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Property _domainAttribute = feature.getDomainAttribute();
      boolean _isMultivalued = _domainAttribute.isMultivalued();
      if (_isMultivalued) {
        _builder.append("protected java.util.List<");
        String _typeAsString = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
        _builder.append(_typeAsString, "");
        _builder.append("> ");
        String _featureFieldName = this.featureFieldName(feature);
        _builder.append(_featureFieldName, "");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      } else {
        boolean _hasPrimitiveType = this._genDomainStructuralFeatureExtensions.hasPrimitiveType(feature);
        boolean _not = (!_hasPrimitiveType);
        if (_not) {
          _builder.append("protected ");
          String _typeAsString_1 = this._genDomainStructuralFeatureExtensions.typeAsString(feature);
          _builder.append(_typeAsString_1, "");
          _builder.append(" ");
          String _featureFieldName_1 = this.featureFieldName(feature);
          _builder.append(_featureFieldName_1, "");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
      }
    }
    return _builder;
  }
  
  private String featureFieldName(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("_");
    Property _domainAttribute = feature.getDomainAttribute();
    String _name = _domainAttribute.getName();
    String _validJavaIdentifier = UML2Util.getValidJavaIdentifier(_name);
    _builder.append(_validJavaIdentifier, "");
    String _string = _builder.toString();
    return _string;
  }
  
  public CharSequence compileImplementation(final GenDomainClassifier concept, final String pkg) {
    if (concept instanceof GenDomainConcept) {
      return _compileImplementation((GenDomainConcept)concept, pkg);
    } else if (concept != null) {
      return _compileImplementation(concept, pkg);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(concept, pkg).toString());
    }
  }
  
  private CharSequence accessorImplementation(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _accessorImplementation((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _accessorImplementation((GenDomainReference)feature);
    } else if (feature != null) {
      return _accessorImplementation(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  private CharSequence modifierImplementation(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _modifierImplementation((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _modifierImplementation((GenDomainReference)feature);
    } else if (feature != null) {
      return _modifierImplementation(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  private CharSequence modifierImplementationOverriden(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _modifierImplementationOverriden((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _modifierImplementationOverriden((GenDomainReference)feature);
    } else if (feature != null) {
      return _modifierImplementationOverriden(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
}
