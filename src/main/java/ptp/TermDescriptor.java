package ptp;

import ptp.AbstractTerm.TERM_TYPE;

import java.util.Collection;

/**
 * Created by denislavrov on 2/17/15.
 */
public abstract class TermDescriptor {
    protected final TERM_TYPE descriptorType;

    public TermDescriptor(TERM_TYPE descriptorType) {
        this.descriptorType = descriptorType;
    }

    public abstract boolean validateTerm(String term);
    public abstract Collection<ContextTerm> findAllTerms(Context context);
}
