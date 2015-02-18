package ptp;

import ptp.AbstractTerm.TERM_TYPE;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by denislavrov on 2/17/15.
 */
public class Context {
    Context parentContext = null;
    int position;
    String rawContext;
    HashMap<TermDescriptor, Collection<ContextTerm>> terms = new HashMap<>();

    public Context(String rawContext) {
        this.rawContext = rawContext;
    }

    protected Context(Context parentContext, int position, String rawContext) {
        this.parentContext = parentContext;
        this.position = position;
        this.rawContext = rawContext;
    }

    public String getRawContext() {
        return rawContext;
    }

    public Collection<ContextTerm> tokenizeBy(TermDescriptor descriptor){
        return terms.computeIfAbsent(descriptor, desc -> desc.findAllTerms(this));
    }

    @Override
    public String toString() {
        return "Context{" +
                "parentContext=" + parentContext +
                ", position=" + position +
                ", rawContext='" + rawContext + '\'' +
                '}';
    }
}
