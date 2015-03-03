package ptp.descriptors;

import ptp.AbstractTerm.TERM_TYPE;
import ptp.Context;
import ptp.ContextTerm;
import ptp.TermDescriptor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by denislavrov on 3/4/15.
 */
public class CharDescriptor extends TermDescriptor {
    public static CharDescriptor INSTANCE = new CharDescriptor();

    public CharDescriptor() {
        super(TERM_TYPE.CHAR);
    }

    @Override
    public boolean validateTerm(String term) {
        return false;
    }

    @Override
    public Collection<ContextTerm> findAllTerms(Context context) {
        ArrayList<ContextTerm> terms = new ArrayList<>();
        char[] chars = context.getRawContext().toCharArray();
        int count = 0;
        for (char aChar : chars) {
            terms.add(new ContextTerm(this, context, count++, Character.toString(aChar)));
        }
        return terms;
    }
}
