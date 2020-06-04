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
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainConcept;
import com.zeligsoft.ddk.zdl.zdlgen.GenDomainStructuralFeature;
import com.zeligsoft.ddk.zdlgen2umlprofile.workflow.extensions.GenDomainStructuralFeatureExtensions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class GenDomainConceptExtensions {
  @Inject
  private GenDomainStructuralFeatureExtensions _genDomainStructuralFeatureExtensions;
  
  public EList<Classifier> baseDomainConcepts(final GenDomainConcept concept) {
    org.eclipse.uml2.uml.Class _domainConcept = concept.getDomainConcept();
    final EList<Classifier> result = _domainConcept.getGenerals();
    org.eclipse.uml2.uml.Class _domainConcept_1 = concept.getDomainConcept();
    result.remove(_domainConcept_1);
    return result;
  }
  
  public ArrayList<GenDomainConcept> baseDomainConceptsToImplement(final GenDomainConcept concept) {
    ArrayList<GenDomainConcept> _xblockexpression = null;
    {
      EList<GenDomainConcept> _generals = concept.getGenerals();
      final GenDomainConcept firstBaseConcept = IterableExtensions.<GenDomainConcept>head(_generals);
      EList<GenDomainConcept> _allGenerals = concept.allGenerals();
      ArrayList<GenDomainConcept> _arrayList = new ArrayList<GenDomainConcept>(_allGenerals);
      final ArrayList<GenDomainConcept> result = _arrayList;
      boolean _notEquals = (!Objects.equal(firstBaseConcept, null));
      if (_notEquals) {
        result.remove(firstBaseConcept);
        EList<GenDomainConcept> _allGenerals_1 = firstBaseConcept.allGenerals();
        result.removeAll(_allGenerals_1);
      }
      _xblockexpression = (result);
    }
    return _xblockexpression;
  }
  
  public List<GenDomainStructuralFeature> allFeaturesToImplement(final GenDomainConcept concept) {
    List<GenDomainStructuralFeature> _xblockexpression = null;
    {
      final Map<String,GenDomainStructuralFeature> featureNameMap = CollectionLiterals.<String, GenDomainStructuralFeature>newHashMap();
      EList<GenDomainStructuralFeature> _features = concept.getFeatures();
      for (final GenDomainStructuralFeature nextFeature : _features) {
        String _featureName = this._genDomainStructuralFeatureExtensions.featureName(nextFeature);
        featureNameMap.put(_featureName, nextFeature);
      }
      ArrayList<GenDomainConcept> _baseDomainConceptsToImplement = this.baseDomainConceptsToImplement(concept);
      for (final GenDomainConcept baseConcept : _baseDomainConceptsToImplement) {
        EList<GenDomainStructuralFeature> _features_1 = baseConcept.getFeatures();
        for (final GenDomainStructuralFeature nextFeature_1 : _features_1) {
          String _featureName_1 = this._genDomainStructuralFeatureExtensions.featureName(nextFeature_1);
          boolean _containsKey = featureNameMap.containsKey(_featureName_1);
          boolean _not = (!_containsKey);
          if (_not) {
            String _featureName_2 = this._genDomainStructuralFeatureExtensions.featureName(nextFeature_1);
            featureNameMap.put(_featureName_2, nextFeature_1);
          }
        }
      }
      Collection<GenDomainStructuralFeature> _values = featureNameMap.values();
      List<GenDomainStructuralFeature> _list = IterableExtensions.<GenDomainStructuralFeature>toList(_values);
      _xblockexpression = (_list);
    }
    return _xblockexpression;
  }
  
  public Set<org.eclipse.uml2.uml.Class> allMetaclassesToImplementAccessorsFor(final GenDomainConcept concept) {
    Set<org.eclipse.uml2.uml.Class> _xblockexpression = null;
    {
      final List<org.eclipse.uml2.uml.Class> result = CollectionLiterals.<org.eclipse.uml2.uml.Class>newArrayList();
      EList<org.eclipse.uml2.uml.Class> _umlMetaclasses = concept.getUmlMetaclasses();
      result.addAll(_umlMetaclasses);
      ArrayList<GenDomainConcept> _baseDomainConceptsToImplement = this.baseDomainConceptsToImplement(concept);
      for (final GenDomainConcept baseConcept : _baseDomainConceptsToImplement) {
        EList<org.eclipse.uml2.uml.Class> _umlMetaclasses_1 = baseConcept.getUmlMetaclasses();
        result.addAll(_umlMetaclasses_1);
      }
      Set<org.eclipse.uml2.uml.Class> _set = IterableExtensions.<org.eclipse.uml2.uml.Class>toSet(result);
      _xblockexpression = (_set);
    }
    return _xblockexpression;
  }
}
