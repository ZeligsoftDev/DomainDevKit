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
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainAttribute;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainReference;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainStructuralFeature;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainStructuralFeatureExtensions;
import java.util.Arrays;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class JavaMethodSignaturesExtensions {
  @Inject
  private GenDomainStructuralFeatureExtensions _genDomainStructuralFeatureExtensions;
  
  protected String _compositeMultivaluedAddExistingSignature(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  protected String _compositeMultivaluedAddExistingSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void add");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    return _builder.toString();
  }
  
  protected String _compositeMultivaluedAddExistingSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void add");
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
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    return _builder.toString();
  }
  
  protected String _compositeMultivaluedAddParemeterizedSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<T extends ");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append("> T add");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(Class<T> typeToCreate, String concept)");
    return _builder.toString();
  }
  
  protected String _compositeMultivaluedAddParemeterizedSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<T extends ");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append("> T add");
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
    _builder.append("(Class<T> typeToCreate, String concept)");
    return _builder.toString();
  }
  
  protected String _compositeMultivalueAddSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" add");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("()");
    return _builder.toString();
  }
  
  protected String _compositeMultivalueAddSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" add");
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
    _builder.append("()");
    return _builder.toString();
  }
  
  protected String _compositeAddExistingSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void set");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _compositeAddExistingSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void set");
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
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _compositeAddParemeterizedSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<T extends ");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append("> T create");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(Class<T> typeToCreate, String concept)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _compositeAddParemeterizedSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<T extends ");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append("> T create");
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
    _builder.append("(Class<T> typeToCreate, String concept)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _compositeAddSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" create");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("()");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _compositeAddSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" create");
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
    _builder.append("()");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _sharedMultivaluedAddSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void add");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _sharedMultivaluedAddSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void add");
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
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _sharedAddSignature(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  protected String _sharedAddSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void set");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _sharedAddSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void set");
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
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _noneMultivaluedAddSignature(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  protected String _noneMultivaluedAddSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void add");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _noneMultivaluedAddSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void add");
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
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _noneAddSignature(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  protected String _noneAddSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void set");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _noneAddSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("void set");
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
    _builder.append("(");
    String _featureModifierType = this._genDomainStructuralFeatureExtensions.featureModifierType(feature);
    _builder.append(_featureModifierType, "");
    _builder.append(" val)");
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  protected String _accessorSignature(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  protected String _accessorSignature(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _featureAccessorReturnType = this._genDomainStructuralFeatureExtensions.featureAccessorReturnType(feature);
    _builder.append(_featureAccessorReturnType, "");
    _builder.append(" ");
    String _accessorName = this.accessorName(feature);
    _builder.append(_accessorName, "");
    _builder.append("()");
    return _builder.toString();
  }
  
  protected String _accessorSignature(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _featureAccessorReturnType = this._genDomainStructuralFeatureExtensions.featureAccessorReturnType(feature);
    _builder.append(_featureAccessorReturnType, "");
    _builder.append(" ");
    String _accessorName = this.accessorName(feature);
    _builder.append(_accessorName, "");
    _builder.append("()");
    return _builder.toString();
  }
  
  protected String _accessorName(final GenDomainStructuralFeature feature) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder.toString();
  }
  
  protected String _accessorName(final GenDomainAttribute feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("get");
    NamedElement _domainElement = feature.getDomainElement();
    String _name = _domainElement.getName();
    String _firstUpper = StringExtensions.toFirstUpper(_name);
    _builder.append(_firstUpper, "");
    {
      boolean _isInconsistentOverride = this._genDomainStructuralFeatureExtensions.isInconsistentOverride(feature);
      if (_isInconsistentOverride) {
        String _inconsistentOverrideString = this._genDomainStructuralFeatureExtensions.getInconsistentOverrideString(feature);
        _builder.append(_inconsistentOverrideString, "");
      }
    }
    return _builder.toString();
  }
  
  protected String _accessorName(final GenDomainReference feature) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("get");
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
    return _builder.toString();
  }
  
  public String compositeMultivaluedAddExistingSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _compositeMultivaluedAddExistingSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _compositeMultivaluedAddExistingSignature((GenDomainReference)feature);
    } else if (feature != null) {
      return _compositeMultivaluedAddExistingSignature(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String compositeMultivaluedAddParemeterizedSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _compositeMultivaluedAddParemeterizedSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _compositeMultivaluedAddParemeterizedSignature((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String compositeMultivalueAddSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _compositeMultivalueAddSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _compositeMultivalueAddSignature((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String compositeAddExistingSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _compositeAddExistingSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _compositeAddExistingSignature((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String compositeAddParemeterizedSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _compositeAddParemeterizedSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _compositeAddParemeterizedSignature((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String compositeAddSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _compositeAddSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _compositeAddSignature((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String sharedMultivaluedAddSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _sharedMultivaluedAddSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _sharedMultivaluedAddSignature((GenDomainReference)feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String sharedAddSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _sharedAddSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _sharedAddSignature((GenDomainReference)feature);
    } else if (feature != null) {
      return _sharedAddSignature(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String noneMultivaluedAddSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _noneMultivaluedAddSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _noneMultivaluedAddSignature((GenDomainReference)feature);
    } else if (feature != null) {
      return _noneMultivaluedAddSignature(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String noneAddSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _noneAddSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _noneAddSignature((GenDomainReference)feature);
    } else if (feature != null) {
      return _noneAddSignature(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String accessorSignature(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _accessorSignature((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _accessorSignature((GenDomainReference)feature);
    } else if (feature != null) {
      return _accessorSignature(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
  
  public String accessorName(final GenDomainStructuralFeature feature) {
    if (feature instanceof GenDomainAttribute) {
      return _accessorName((GenDomainAttribute)feature);
    } else if (feature instanceof GenDomainReference) {
      return _accessorName((GenDomainReference)feature);
    } else if (feature != null) {
      return _accessorName(feature);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(feature).toString());
    }
  }
}
