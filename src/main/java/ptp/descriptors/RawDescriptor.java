package ptp.descriptors;

import ptp.AbstractTerm.TERM_TYPE;
import ptp.Context;
import ptp.ContextTerm;
import ptp.TermDescriptor;

import java.util.ArrayList;
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
        return false;
    }

    @Override
    public Collection<ContextTerm> findAllTerms(Context context) {
        ArrayList<ContextTerm> contextTerms = new ArrayList<>();
        String deref = context.getRawContext();
        int lastIndex = 0;
        while (true){
            lastIndex = deref.indexOf(rawTerm, lastIndex);
            if (lastIndex != -1) {
                contextTerms.add(new ContextTerm(descriptorType, this, context, lastIndex, rawTerm));
                lastIndex += rawTerm.length();
            } else break;
        }
        return contextTerms;
    }
}
