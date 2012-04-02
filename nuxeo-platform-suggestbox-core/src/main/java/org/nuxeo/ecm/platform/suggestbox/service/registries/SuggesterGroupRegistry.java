package org.nuxeo.ecm.platform.suggestbox.service.registries;

import java.util.HashMap;
import java.util.Map;

import org.nuxeo.ecm.platform.suggestbox.service.descriptors.SuggesterGroupDescriptor;
import org.nuxeo.runtime.model.ContributionFragmentRegistry;

public class SuggesterGroupRegistry extends
        ContributionFragmentRegistry<SuggesterGroupDescriptor> {

    protected Map<String, SuggesterGroupDescriptor> suggesterGroupDescriptors = new HashMap<String, SuggesterGroupDescriptor>();

    public SuggesterGroupDescriptor getSuggesterGroupDescriptor(String name) {
        return suggesterGroupDescriptors.get(name);
    }

    @Override
    public SuggesterGroupDescriptor clone(SuggesterGroupDescriptor suggestGroup) {
        try {
            return (SuggesterGroupDescriptor) suggestGroup.clone();
        } catch (CloneNotSupportedException e) {
            // this should never occur since clone implements Cloneable
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contributionRemoved(String id, SuggesterGroupDescriptor arg1) {
        suggesterGroupDescriptors.remove(id);
    }

    @Override
    public void contributionUpdated(String id,
            SuggesterGroupDescriptor contrib,
            SuggesterGroupDescriptor newOrigContrib) {
        suggesterGroupDescriptors.put(id, contrib);
    }

    @Override
    public String getContributionId(SuggesterGroupDescriptor suggestGroup) {
        return suggestGroup.getName();
    }

    @Override
    public void merge(SuggesterGroupDescriptor src, SuggesterGroupDescriptor dst) {
        try {
            dst.mergeFrom(src);
        } catch (Exception e) {
            // it should be possible to throw a non runtime exception here
            throw new RuntimeException(e);
        }
    }
}
