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
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainBlock;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainClassifier;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainEnum;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainModel;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.ZDLGenExtensions;
import java.util.Arrays;
import org.eclipse.uml2.common.util.UML2Util;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class JavaNamingExtensions {
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
  private ZDLGenExtensions _zDLGenExtensions;
  
  public String interfaceJavaPackage(final GenDomainBlock block) {
    String _xblockexpression = null;
    {
      String _name = block.getName();
      final String blockName = UML2Util.getValidJavaIdentifier(_name);
      String _interfaceJavaPackage = this.interfaceJavaPackage(this.rootPackage, blockName);
      _xblockexpression = (_interfaceJavaPackage);
    }
    return _xblockexpression;
  }
  
  private String interfaceJavaPackage(final String theRootPackage, final String theBlockName) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(theRootPackage, "");
    _builder.append(".");
    _builder.append(theBlockName, "");
    String _string = _builder.toString();
    return _string;
  }
  
  public String qualifiedName(final GenDomainConcept concept) {
    String _xblockexpression = null;
    {
      final GenDomainBlock block = concept.getBlock();
      final GenDomainModel domain = this._zDLGenExtensions.domainModel(block);
      String _xifexpression = null;
      boolean _equals = Objects.equal(domain, null);
      if (_equals) {
        _xifexpression = "";
      } else {
        String _rootPackage = domain.getRootPackage();
        String _name = block.getName();
        String _name_1 = concept.getName();
        String _qualifiedName = this.qualifiedName(_rootPackage, _name, _name_1);
        _xifexpression = _qualifiedName;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public String qualifiedName(final GenDomainEnum concept) {
    String _xblockexpression = null;
    {
      final GenDomainBlock block = concept.getBlock();
      final GenDomainModel domain = this._zDLGenExtensions.domainModel(block);
      String _xifexpression = null;
      boolean _equals = Objects.equal(domain, null);
      if (_equals) {
        _xifexpression = "";
      } else {
        String _rootPackage = domain.getRootPackage();
        String _name = block.getName();
        String _name_1 = concept.getName();
        String _qualifiedName = this.qualifiedName(_rootPackage, _name, _name_1);
        _xifexpression = _qualifiedName;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  private String qualifiedName(final String theRootPackage, final String blockName, final String conceptName) {
    StringConcatenation _builder = new StringConcatenation();
    String _interfaceJavaPackage = this.interfaceJavaPackage(theRootPackage, blockName);
    _builder.append(_interfaceJavaPackage, "");
    _builder.append(".");
    _builder.append(conceptName, "");
    String _string = _builder.toString();
    return _string;
  }
  
  public CharSequence implementationQualifiedName(final GenDomainConcept concept) {
    CharSequence _xblockexpression = null;
    {
      final GenDomainBlock block = concept.getBlock();
      final GenDomainModel domain = this._zDLGenExtensions.domainModel(block);
      CharSequence _xifexpression = null;
      boolean _equals = Objects.equal(domain, null);
      if (_equals) {
        _xifexpression = "";
      } else {
        String _rootPackage = domain.getRootPackage();
        String _name = block.getName();
        String _name_1 = concept.getName();
        String _implementationSubPackage = domain.getImplementationSubPackage();
        String _implSuffix = domain.getImplSuffix();
        CharSequence _qualifiedName = this.qualifiedName(_rootPackage, _name, _name_1, _implementationSubPackage, _implSuffix);
        _xifexpression = _qualifiedName;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  private CharSequence qualifiedName(final String theRootPackage, final String blockName, final String conceptName, final String theImplSubPackage, final String theImplSuffix) {
    StringConcatenation _builder = new StringConcatenation();
    String _implementationJavaPackage = this.implementationJavaPackage(theRootPackage, blockName, theImplSubPackage);
    _builder.append(_implementationJavaPackage, "");
    _builder.append(".");
    _builder.append(conceptName, "");
    _builder.append(theImplSuffix, "");
    return _builder;
  }
  
  public String implementationJavaPackage(final GenDomainBlock block) {
    String _xblockexpression = null;
    {
      String _name = block.getName();
      final String blockName = UML2Util.getValidJavaIdentifier(_name);
      String _implementationJavaPackage = this.implementationJavaPackage(this.rootPackage, blockName, this.implSubPackage);
      _xblockexpression = (_implementationJavaPackage);
    }
    return _xblockexpression;
  }
  
  private String implementationJavaPackage(final String theRootPackage, final String theBlockName, final String theImplSubPackage) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(theRootPackage, "");
    _builder.append(".");
    _builder.append(theBlockName, "");
    _builder.append(".");
    _builder.append(theImplSubPackage, "");
    String _string = _builder.toString();
    return _string;
  }
  
  protected String _javaInterfaceName(final GenDomainConcept concept) {
    String _name = concept.getName();
    String _validJavaIdentifier = UML2Util.getValidJavaIdentifier(_name);
    return _validJavaIdentifier;
  }
  
  protected String _javaInterfaceName(final GenDomainEnum genum) {
    String _name = genum.getName();
    String _validJavaIdentifier = UML2Util.getValidJavaIdentifier(_name);
    return _validJavaIdentifier;
  }
  
  protected String _javaImplementationName(final GenDomainEnum genum) {
    String _xblockexpression = null;
    {
      final GenDomainBlock block = genum.getBlock();
      final GenDomainModel domain = this._zDLGenExtensions.domainModel(block);
      String _xifexpression = null;
      boolean _equals = Objects.equal(domain, null);
      if (_equals) {
        _xifexpression = "";
      } else {
        String _xblockexpression_1 = null;
        {
          final String implSuffix = domain.getImplSuffix();
          StringConcatenation _builder = new StringConcatenation();
          String _name = genum.getName();
          String _validJavaIdentifier = UML2Util.getValidJavaIdentifier(_name);
          _builder.append(_validJavaIdentifier, "");
          _builder.append(implSuffix, "");
          String _string = _builder.toString();
          _xblockexpression_1 = (_string);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  protected String _javaImplementationName(final GenDomainConcept concept) {
    String _xblockexpression = null;
    {
      final GenDomainBlock block = concept.getBlock();
      final GenDomainModel domain = this._zDLGenExtensions.domainModel(block);
      String _xifexpression = null;
      boolean _equals = Objects.equal(domain, null);
      if (_equals) {
        _xifexpression = "";
      } else {
        String _xblockexpression_1 = null;
        {
          final String implSuffix = domain.getImplSuffix();
          StringConcatenation _builder = new StringConcatenation();
          String _name = concept.getName();
          String _validJavaIdentifier = UML2Util.getValidJavaIdentifier(_name);
          _builder.append(_validJavaIdentifier, "");
          _builder.append(implSuffix, "");
          String _string = _builder.toString();
          _xblockexpression_1 = (_string);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = (_xifexpression);
    }
    return _xblockexpression;
  }
  
  public String javaInterfaceName(final GenDomainClassifier genum) {
    if (genum instanceof GenDomainEnum) {
      return _javaInterfaceName((GenDomainEnum)genum);
    } else if (genum instanceof GenDomainConcept) {
      return _javaInterfaceName((GenDomainConcept)genum);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(genum).toString());
    }
  }
  
  public String javaImplementationName(final GenDomainClassifier genum) {
    if (genum instanceof GenDomainEnum) {
      return _javaImplementationName((GenDomainEnum)genum);
    } else if (genum instanceof GenDomainConcept) {
      return _javaImplementationName((GenDomainConcept)genum);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(genum).toString());
    }
  }
}
