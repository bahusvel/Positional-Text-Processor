package ptp.descriptors;

import ptp.AbstractTerm.TERM_TYPE;
import ptp.Context;
import ptp.ContextTerm;
import ptp.TermDescriptor;

import java.util.Collection;

/**
 * Created by denislavrov on 2/17/15.
 */
public class RawDescriptor extends TermDescriptor {
    String rawTerm;

    public RawDescriptor(TERM_TYPE descriptorType, String rawTerm) {
        super(descriptorType);
        this.rawTerm = rawTerm;
    }

    @Override
    public boolean validateTerm(String term) {
        return term.equals(rawTerm);
    }

    @Override
    public Collection<ContextTerm> findAllTerms(Context context) {
        return null;
    }
}
