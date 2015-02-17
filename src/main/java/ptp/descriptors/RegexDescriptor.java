package ptp.descriptors;

import ptp.AbstractTerm.TERM_TYPE;
import ptp.Context;
import ptp.ContextTerm;
import ptp.TermDescriptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by denislavrov on 2/17/15.
 */
public class RegexDescriptor extends TermDescriptor {
    final String rawRegex;
    final Pattern pattern;

    public RegexDescriptor(String regex, TERM_TYPE descriptorType) {
        super(descriptorType);
        rawRegex = regex;
        pattern = Pattern.compile(rawRegex);
    }

    public RegexDescriptor(String regex) {
        super(TERM_TYPE.PHRASE);
        rawRegex = regex;
        pattern = Pattern.compile(rawRegex);
    }

    @Override
    public boolean validateTerm(String term) {
        return term.matches(rawRegex);
    }

    @Override
    public Collection<ContextTerm> findAllTerms(Context context) {
        ArrayList<ContextTerm> contextTerms = new ArrayList<>();
        Matcher matcher = pattern.matcher(context.getRawContext());
        while (matcher.find()){
            contextTerms.add(new ContextTerm(descriptorType, this, context, matcher.start(), matcher.group()));
        }
        return contextTerms;
    }
}
