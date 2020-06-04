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
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainAttribute;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainClassifier;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainDataType;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainEnum;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainReference;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainStructuralFeature;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaNamingExtensions;
import java.util.Arrays;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Feature;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class GenDomainStructuralFeatureExtensions {
  @Inject
  private JavaNamingExtensions _javaNamingExtensions;
  
  protected String _typeAsString(final GenDomainAttribute feature) {
    String _xifexpression = null;
    GenDomainDataType _type = feature.getType();
    boolean _equals = Objects.equal(_type, null);
    if (_equals) {
      String _xifexpression_1 = null;
      Property _domainAttribute = feature.getDomainAttribute();
      Type _type_1 = _domainAttribute.getType();
      boolean _equals_1 = Objects.equal(_type_1, null);
      if (_equals_1) {
        String _xifexpression_2 = null;
        Property _umlMetaattribute = feature.getUmlMetaattribute();
        boolean _notEquals = (!Objects.equal(_umlMetaattribute, null));
        if (_notEquals) {
          Property _umlMetaattribute_1 = feature.getUmlMetaattribute();
          Type _type_2 = _umlMetaattribute_1.getType();
          String _name = _type_2.getName();
          String _plus = ("org.eclipse.uml2.uml." + _name);
          _xifexpression_2 = _plus;
        } else {
          _xifexpression_2 = "TYPE IS NULL";
        }
        _xifexpression_1 = _xifexpression_2;
      } else {
        String _xblockexpression = null;
        {
          Property _domainAttribute_1 = feature.getDomainAttribute();
          Type _type_3 = _domainAttribute_1.getType();
          final String type = _type_3.getName();
          String _xifexpression_3 = null;
          boolean _equals_2 = Objects.equal(type, "UnlimitedNatural");
          if (_equals_2) {
            return "Integer";
          } else {
            _xifexpression_3 = type;
          }
          _xblockexpression = (_xifexpression_3);
        }
        _xifexpression_1 = _xblockexpression;
      }
      _xifexpression = _xifexpression_1;
    } else {
      GenDomainDataType _type_3 = feature.getType();
      String _javaInterfaceName = this._javaNamingExtensions.javaInterfaceName(_type_3);
      _xifexpression = _javaInterfaceName;
    }
    return _xifexpression;
  }
  
  protected boolean _hasZDLType(final GenDomainReference feature) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      GenDomainConcept _target = feature.getTarget();
      boolean _equals = Objects.equal(_target, null);
      if (_equals) {
        Property _domainAttribute = feature.getDomainAttribute();
        Type _type = _domainAttribute.getType();
        boolean _equals_1 = Objects.equal(_type, null);
        if (_equals_1) {
          result = false;
        } else {
          Property _domainAttribute_1 = feature.getDomainAttribute();
          Type _type_1 = _domainAttribute_1.getType();
          String _name = _type_1.getName();
          final String _switchValue = _name;
          boolean _matched = false;
          if (!_matched) {
            if (Objects.equal(_switchValue,"Integer")) {
              _matched=true;
              result = false;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"UnlimitedNatural")) {
              _matched=true;
              result = false;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"String")) {
              _matched=true;
              result = false;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"Boolean")) {
              _matched=true;
              result = false;
            }
          }
          if (!_matched) {
            result = true;
          }
        }
      } else {
        result = true;
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  protected boolean _hasZDLType(final GenDomainAttribute feature) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      GenDomainDataType _type = feature.getType();
      boolean _equals = Objects.equal(_type, null);
      if (_equals) {
        Property _domainAttribute = feature.getDomainAttribute();
        Type _type_1 = _domainAttribute.getType();
        boolean _equals_1 = Objects.equal(_type_1, null);
        if (_equals_1) {
          result = false;
        } else {
          Property _domainAttribute_1 = feature.getDomainAttribute();
          Type _type_2 = _domainAttribute_1.getType();
          String _name = _type_2.getName();
          final String _switchValue = _name;
          boolean _matched = false;
          if (!_matched) {
            if (Objects.equal(_switchValue,"Integer")) {
              _matched=true;
              result = false;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"UnlimitedNatural")) {
              _matched=true;
              result = false;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"String")) {
              _matched=true;
              result = false;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"Boolean")) {
              _matched=true;
              result = false;
            }
          }
          if (!_matched) {
            result = true;
          }
        }
      } else {
        result = true;
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  protected boolean _hasUMLType(final GenDomainReference feature) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      GenDomainConcept _target = feature.getTarget();
      boolean _equals = Objects.equal(_target, null);
      if (_equals) {
        Property _domainAttribute = feature.getDomainAttribute();
        Type _type = _domainAttribute.getType();
        boolean _equals_1 = Objects.equal(_type, null);
        if (_equals_1) {
          Property _umlMetaattribute = feature.getUmlMetaattribute();
          boolean _notEquals = (!Objects.equal(_umlMetaattribute, null));
          if (_notEquals) {
            result = true;
          } else {
            result = false;
          }
        } else {
          result = false;
        }
      } else {
        result = false;
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  protected boolean _hasUMLType(final GenDomainAttribute feature) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      GenDomainDataType _type = feature.getType();
      boolean _equals = Objects.equal(_type, null);
      if (_equals) {
        Property _domainAttribute = feature.getDomainAttribute();
        Type _type_1 = _domainAttribute.getType();
        boolean _equals_1 = Objects.equal(_type_1, null);
        if (_equals_1) {
          Property _umlMetaattribute = feature.getUmlMetaattribute();
          boolean _notEquals = (!Objects.equal(_umlMetaattribute, null));
          if (_notEquals) {
            result = true;
          } else {
            result = false;
          }
        } else {
          result = false;
        }
      } else {
        result = false;
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  protected boolean _hasPrimitiveType(final GenDomainReference feature) {
    boolean result = false;
    GenDomainConcept _target = feature.getTarget();
    boolean _equals = Objects.equal(_target, null);
    if (_equals) {
      Property _domainAttribute = feature.getDomainAttribute();
      Type _type = _domainAttribute.getType();
      boolean _equals_1 = Objects.equal(_type, null);
      if (_equals_1) {
        result = false;
      } else {
        Property _domainAttribute_1 = feature.getDomainAttribute();
        Type _type_1 = _domainAttribute_1.getType();
        String _name = _type_1.getName();
        final String _switchValue = _name;
        boolean _matched = false;
        if (!_matched) {
          if (Objects.equal(_switchValue,"Integer")) {
            _matched=true;
            result = true;
          }
        }
        if (!_matched) {
          if (Objects.equal(_switchValue,"UnlimitedNatural")) {
            _matched=true;
            result = true;
          }
        }
        if (!_matched) {
          if (Objects.equal(_switchValue,"String")) {
            _matched=true;
            result = true;
          }
        }
        if (!_matched) {
          if (Objects.equal(_switchValue,"Boolean")) {
            _matched=true;
            result = true;
          }
        }
        if (!_matched) {
          result = false;
        }
      }
    }
    return result;
  }
  
  protected boolean _hasPrimitiveType(final GenDomainAttribute feature) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      GenDomainDataType _type = feature.getType();
      boolean _equals = Objects.equal(_type, null);
      if (_equals) {
        Property _domainAttribute = feature.getDomainAttribute();
        Type _type_1 = _domainAttribute.getType();
        boolean _equals_1 = Objects.equal(_type_1, null);
        if (_equals_1) {
          result = false;
        } else {
          Property _domainAttribute_1 = feature.getDomainAttribute();
          Type _type_2 = _domainAttribute_1.getType();
          String _name = _type_2.getName();
          final String _switchValue = _name;
          boolean _matched = false;
          if (!_matched) {
            if (Objects.equal(_switchValue,"Integer")) {
              _matched=true;
              result = true;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"UnlimitedNatural")) {
              _matched=true;
              result = true;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"String")) {
              _matched=true;
              result = true;
            }
          }
          if (!_matched) {
            if (Objects.equal(_switchValue,"Boolean")) {
              _matched=true;
              result = true;
            }
          }
          if (!_matched) {
            result = false;
          }
        }
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  protected String _typeAsString(final GenDomainReference feature) {
    String _xifexpression = null;
    GenDomainConcept _target = feature.getTarget();
    boolean _equals = Objects.equal(_target, null);
    if (_equals) {
      String _xifexpression_1 = null;
      Property _umlMetaattribute = feature.getUmlMetaattribute();
      boolean _notEquals = (!Objects.equal(_umlMetaattribute, null));
      if (_notEquals) {
        Property _umlMetaattribute_1 = feature.getUmlMetaattribute();
        Type _type = _umlMetaattribute_1.getType();
        String _name = _type.getName();
        String _plus = ("org.eclipse.uml2.uml." + _name);
        _xifexpression_1 = _plus;
      } else {
        _xifexpression_1 = "TYPE IS NULL";
      }
      _xifexpression = _xifexpression_1;
    } else {
      GenDomainConcept _target_1 = feature.getTarget();
      String _javaInterfaceName = this._javaNamingExtensions.javaInterfaceName(_target_1);
      _xifexpression = _javaInterfaceName;
    }
    return _xifexpression;
  }
  
  protected boolean _hasEnumerationType(final GenDomainAttribute feature) {
    GenDomainDataType _type = feature.getType();
    return (_type instanceof GenDomainEnum);
  }
  
  protected boolean _hasEnumerationType(final GenDomainReference feature) {
    GenDomainConcept _target = feature.getTarget();
    return (_target instanceof GenDomainEnum);
  }
  
  public String conceptQualifiedName(final GenDomainStructuralFeature feature) {
    GenDomainConcept _concept = feature.getConcept();
    NamedElement _domainElement = _concept.getDomainElement();
    String _qualifiedName = _domainElement.getQualifiedName();
    return _qualifiedName;
  }
  
  public CharSequence featureAccessorReturnType(final GenDomainStructuralFeature feature) {
    CharSequence _xifexpression = null;
    Property _domainAttribute = feature.getDomainAttribute();
    boolean _isMultivalued = _domainAttribute.isMultivalued();
    if (_isMultivalued) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("java.util.List<");
      String _typeAsString = this.typeAsString(feature);
      _builder.append(_typeAsString, "");
      _builder.append(">");
      _xifexpression = _builder;
    } else {
      String _typeAsString_1 = this.typeAsString(feature);
      _xifexpression = _typeAsString_1;
    }
    return _xifexpression;
  }
  
  public String featureModifierType(final GenDomainStructuralFeature feature) {
    String _typeAsString = this.typeAsString(feature);
    return _typeAsString;
  }
  
  public boolean isConsistentOverride(final GenDomainStructuralFeature feature, final GenDomainStructuralFeature overriden) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      Property _domainAttribute = feature.getDomainAttribute();
      String _name = _domainAttribute.getName();
      Property _domainAttribute_1 = overriden.getDomainAttribute();
      String _name_1 = _domainAttribute_1.getName();
      boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
      if (_equalsIgnoreCase) {
        Property _domainAttribute_2 = feature.getDomainAttribute();
        final Type featureType = _domainAttribute_2.getType();
        Property _domainAttribute_3 = overriden.getDomainAttribute();
        final Type overridenType = _domainAttribute_3.getType();
        boolean _conformsTo = featureType.conformsTo(overridenType);
        if (_conformsTo) {
          result = true;
        }
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public boolean isInconsistentOverride(final GenDomainStructuralFeature feature, final GenDomainStructuralFeature overriden) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      Property _domainAttribute = feature.getDomainAttribute();
      String _name = _domainAttribute.getName();
      Property _domainAttribute_1 = overriden.getDomainAttribute();
      String _name_1 = _domainAttribute_1.getName();
      boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
      if (_equalsIgnoreCase) {
        Property _domainAttribute_2 = feature.getDomainAttribute();
        final Type featureType = _domainAttribute_2.getType();
        Property _domainAttribute_3 = overriden.getDomainAttribute();
        final Type overridenType = _domainAttribute_3.getType();
        boolean _conformsTo = featureType.conformsTo(overridenType);
        boolean _not = (!_conformsTo);
        if (_not) {
          result = true;
        }
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public boolean isOverride(final GenDomainStructuralFeature feature) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      final Set<Property> overridenFeature = this.overridenFeatures(feature);
      boolean _isEmpty = overridenFeature.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        result = true;
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public boolean isInconsistentOverride(final GenDomainStructuralFeature feature) {
    boolean _xblockexpression = false;
    {
      boolean result = false;
      final Set<Property> overridenFeature = this.overridenFeatures(feature);
      for (final Property next : overridenFeature) {
        {
          Property _domainAttribute = feature.getDomainAttribute();
          final Type featureType = _domainAttribute.getType();
          final Type overridenType = next.getType();
          boolean _conformsTo = featureType.conformsTo(overridenType);
          boolean _not = (!_conformsTo);
          if (_not) {
            result = true;
          } else {
            Property _domainAttribute_1 = feature.getDomainAttribute();
            boolean _isMultivalued = _domainAttribute_1.isMultivalued();
            boolean _isMultivalued_1 = next.isMultivalued();
            boolean _notEquals = (_isMultivalued != _isMultivalued_1);
            if (_notEquals) {
              result = true;
            }
          }
        }
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public String getInconsistentOverrideString(final GenDomainStructuralFeature feature) {
    String _xblockexpression = null;
    {
      int count = 0;
      final Set<Property> overridenFeature = this.overridenFeatures(feature);
      for (final Property next : overridenFeature) {
        {
          Property _domainAttribute = feature.getDomainAttribute();
          final Type featureType = _domainAttribute.getType();
          final Type overridenType = next.getType();
          boolean _conformsTo = featureType.conformsTo(overridenType);
          boolean _not = (!_conformsTo);
          if (_not) {
            int _plus = (count + 1);
            count = _plus;
          } else {
            Property _domainAttribute_1 = feature.getDomainAttribute();
            boolean _isMultivalued = _domainAttribute_1.isMultivalued();
            boolean _isMultivalued_1 = next.isMultivalued();
            boolean _notEquals = (_isMultivalued != _isMultivalued_1);
            if (_notEquals) {
              int _plus_1 = (count + 1);
              count = _plus_1;
            }
          }
        }
      }
      String _xifexpression = null;
      boolean _equals = (count == 1);
      if (_equals) {
        _xifexpression = "Override";
      } else {
        String _xifexpression_1 = null;
        boolean _greaterThan = (count > 1);
        if (_greaterThan) {
          String _plus = ("Override" + Integer.valueOf(count));
          _xifexpression_1 = _plus;
        } else {
          _xifexpression_1 = "";
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public Set<Property> overridenFeatures(final GenDomainStructuralFeature feature) {
    Set<Property> _xblockexpression = null;
    {
      final GenDomainConcept concept = feature.getConcept();
      org.eclipse.uml2.uml.Class _domainConcept = concept.getDomainConcept();
      final EList<Classifier> generals = _domainConcept.allParents();
      final Set<Property> overridenFeatures = Sets.<Property>newHashSet();
      for (final Classifier c : generals) {
        EList<Property> _attributes = c.getAttributes();
        final Function1<Feature,Boolean> _function = new Function1<Feature,Boolean>() {
            public Boolean apply(final Feature f) {
              boolean _and = false;
              Property _domainAttribute = feature.getDomainAttribute();
              boolean _notEquals = (!Objects.equal(f, _domainAttribute));
              if (!_notEquals) {
                _and = false;
              } else {
                Property _domainAttribute_1 = feature.getDomainAttribute();
                String _name = _domainAttribute_1.getName();
                String _name_1 = f.getName();
                boolean _equalsIgnoreCase = _name.equalsIgnoreCase(_name_1);
                _and = (_notEquals && _equalsIgnoreCase);
              }
              return Boolean.valueOf(_and);
            }
          };
        Iterable<Property> _filter = IterableExtensions.<Property>filter(_attributes, _function);
        Iterables.<Property>addAll(overridenFeatures, _filter);
      }
      _xblockexpression = (overridenFeatures);
    }
    return _xblockexpression;
  }
  
  public Set<GenDomainStructuralFeature> overridenGenFeatures(final GenDomainStructuralFeature feature) {
    Set<GenDomainStructuralFeature> _xblockexpression = null;
    {
      final GenDomainConcept concept = feature.getConcept();
      final EList<GenDomainConcept> generals = concept.allGenerals();
      final Set<GenDomainStructuralFeature> overridenFeatures = Sets.<GenDomainStructuralFeature>newHashSet();
      for (final GenDomainConcept c : generals) {
        EList<GenDomainStructuralFeature> _features = c.getFeatures();
        final Function1<GenDomainStructuralFeature,Boolean> _function = new Function1<GenDomainStructuralFeature,Boolean>() {
            public Boolean apply(final GenDomainStructuralFeature f) {
              boolean _and = false;
              boolean _notEquals = (!Objects.equal(f, feature));
              if (!_notEquals) {
                _and = false;
              } else {
                String _featureName = GenDomainStructuralFeatureExtensions.this.featureName(feature);
                String _featureName_1 = GenDomainStructuralFeatureExtensions.this.featureName(f);
                boolean _equals = _featureName.equals(_featureName_1);
                _and = (_notEquals && _equals);
              }
              return Boolean.valueOf(_and);
            }
          };
        Iterable<GenDomainStructuralFeature> _filter = IterableExtensions.<GenDomainStructuralFeature>filter(_features, _function);
        Iterables.<GenDomainStructuralFeature>addAll(overridenFeatures, _filter);
      }
      _xblockexpression = (overridenFeatures);
    }
    return _xblockexpression;
  }
  
  protected String _featureName(final GenDomainAttribute attr) {
    String _name = attr.getName();
    return _name;
  }
  
  protected String _featureName(final GenDomainReference ref) {
    Property _domainAttribute = ref.getDomainAttribute();
    String _name = _domainAttribute.getName();
    return _name;
  }
  
  protected GenDomainClassifier _featureType(final GenDomainAttribute attr) {
    GenDomainDataType _type = attr.getType();
    return _type;
  }
  
  protected GenDomainClassifier _featureType(final GenDomainReference ref) {
    GenDomainConcept _target = ref.getTarget();
    return _target;
  }
  
  public String featureTypeQualifiedName(final GenDomainStructuralFeature feat) {
    String _xblockexpression = null;
    {
      final GenDomainClassifier featureType = this.featureType(feat);
      String _xifexpression = null;
      boolean _equals = Objects.equal(featureType, null);
      if (_equals) {
        _xifexpression = "";
      } else {
        NamedElement _domainElement = featureType.getDomainElement();
        String _qualifiedName = _domainElement.getQualifiedName();
        _xifexpression = _qualifiedName;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  protected boolean _typeIsAbstract(final GenDomainAttribute attr) {
    boolean _xblockexpression = false;
    {
      Property _domainAttribute = attr.getDomainAttribute();
      final Type attrType = _domainAttribute.getType();
      boolean _switchResult = false;
      boolean _matched = false;
      if (!_matched) {
        if (attrType instanceof Classifier) {
          final Classifier _classifier = (Classifier)attrType;
          _matched=true;
          boolean _isAbstract = _classifier.isAbstract();
          _switchResult = _isAbstract;
        }
      }
      if (!_matched) {
        _switchResult = false;
      }
      _xblockexpression = (_switchResult);
    }
    return _xblockexpression;
  }
  
  protected boolean _typeIsAbstract(final GenDomainReference attr) {
    boolean _xblockexpression = false;
    {
      Property _domainAttribute = attr.getDomainAttribute();
      final Type attrType = _domainAttribute.getType();
      boolean _switchResult = false;
      boolean _matched = false;
      if (!_matched) {
        if (attrType instanceof Classifier) {
          final Classifier _classifier = (Classifier)attrType;
          _matched=true;
          boolean _isAbstract = _classifier.isAbstract();
          _switchResult = _isAbstract;
        }
      }
      if (!_matched) {
        _switchResult = false;
      }
      _xblockexpression = (_switchResult);
    }
    return _xblockexpression;
  }
  
  public String typeAsString(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _typeAsString((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _typeAsString((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public boolean hasZDLType(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _hasZDLType((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _hasZDLType((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public boolean hasUMLType(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _hasUMLType((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _hasUMLType((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public boolean hasPrimitiveType(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _hasPrimitiveType((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _hasPrimitiveType((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public boolean hasEnumerationType(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _hasEnumerationType((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _hasEnumerationType((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String featureName(final GenDomainStructuralFeature attr) {
    if (attr instanceof GenDomainAttribute) {
      return _featureName((GenDomainAttribute)attr);
    } else if (attr instanceof GenDomainReference) {
      return _featureName((GenDomainReference)attr);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(attr).toString());
    }
  }
  
  public GenDomainClassifier featureType(final GenDomainStructuralFeature attr) {
    if (attr instanceof GenDomainAttribute) {
      return _featureType((GenDomainAttribute)attr);
    } else if (attr instanceof GenDomainReference) {
      return _featureType((GenDomainReference)attr);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(attr).toString());
    }
  }
  
  public boolean typeIsAbstract(final GenDomainStructuralFeature attr) {
    if (attr instanceof GenDomainAttribute) {
      return _typeIsAbstract((GenDomainAttribute)attr);
    } else if (attr instanceof GenDomainReference) {
      return _typeIsAbstract((GenDomainReference)attr);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(attr).toString());
    }
  }
}
