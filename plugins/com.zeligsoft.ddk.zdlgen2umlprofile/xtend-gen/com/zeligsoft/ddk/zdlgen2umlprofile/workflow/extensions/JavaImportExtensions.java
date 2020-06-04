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
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainClassifier;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainEnum;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.JavaNamingExtensions;
import java.util.Arrays;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class JavaImportExtensions {
  @Inject
  private JavaNamingExtensions _javaNamingExtensions;
  
  protected CharSequence _generateImport(final GenDomainClassifier concept) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  protected CharSequence _generateImport(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import ");
    String _qualifiedName = this._javaNamingExtensions.qualifiedName(concept);
    _builder.append(_qualifiedName, "");
    _builder.append(";");
    return _builder;
  }
  
  protected CharSequence _generateImport(final GenDomainEnum denum) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import ");
    String _qualifiedName = this._javaNamingExtensions.qualifiedName(denum);
    _builder.append(_qualifiedName, "");
    _builder.append(";");
    return _builder;
  }
  
  protected CharSequence _generateImplementationImport(final GenDomainClassifier concept) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  protected CharSequence _generateImplementationImport(final GenDomainConcept concept) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import ");
    CharSequence _implementationQualifiedName = this._javaNamingExtensions.implementationQualifiedName(concept);
    _builder.append(_implementationQualifiedName, "");
    _builder.append(";");
    return _builder;
  }
  
  public CharSequence generateImport(final GenDomainClassifier denum) {
    if (denum instanceof GenDomainEnum) {
      return _generateImport((GenDomainEnum)denum);
    } else if (denum instanceof GenDomainConcept) {
      return _generateImport((GenDomainConcept)denum);
    } else if (denum != null) {
      return _generateImport(denum);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(denum).toString());
    }
  }
  
  public CharSequence generateImplementationImport(final GenDomainClassifier concept) {
    if (concept instanceof GenDomainConcept) {
      return _generateImplementationImport((GenDomainConcept)concept);
    } else if (concept != null) {
      return _generateImplementationImport(concept);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(concept).toString());
    }
  }
}
