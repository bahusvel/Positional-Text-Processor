package ptp;

import ptp.descriptors.LineDescriptor;

import java.util.Collection;

/**
 * Created by denislavrov on 2/17/15.
 */
public class ContextTerm extends AbstractTerm{
    final Context context;
    int position = 0;

    public ContextTerm(TERM_TYPE termType, TermDescriptor descriptor, Context context, int position, String rawTerm) {
        super(termType, descriptor);
        this.context = context;
        this.position = position;
        super.rawTerm = rawTerm;
    }


    public ResultContext after(){
        return ResultContext.from(context, position + rawTerm.length(), false);
    }
    public ResultContext before(){
        return ResultContext.from(context, 0, position, true);
    }

    private ContextTerm getLineForTerm(){
        Collection<ContextTerm> lines = context.tokenizeBy(LineDescriptor.INSTANCE);
        ContextTerm lineContext = null;
        for (ContextTerm line : lines) {
            if (line.position + line.rawTerm.length() > position){
                lineContext = line;
                break;
            }
        }
        assert lineContext != null;
        return lineContext;
    }

    public ResultContext right(){
        ContextTerm lineContext = getLineForTerm();
        return ResultContext.from(context, position + rawTerm.length(), lineContext.position + lineContext.rawTerm.length(), false);
    }
    public ResultContext left(){
        ContextTerm lineContext = getLineForTerm();
        return ResultContext.from(context, lineContext.position, position, true);
    }
    public ResultContext above(){
        ContextTerm lineContext = getLineForTerm();
        return ResultContext.from(context, 0, lineContext.position, true);
    }
    public ResultContext below(){
        ContextTerm lineContext = getLineForTerm();
        return ResultContext.from(context, lineContext.position + lineContext.rawTerm.length(), false);
    }
}
