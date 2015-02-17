package ptp;

import java.util.Collection;

/**
 * Created by denislavrov on 2/17/15.
 */
public class ResultContext extends Context {
    boolean orderReverse = false;

    private ResultContext(String rawContext) {
        super(rawContext);
    }


    private ResultContext(Context parentContext, int position, String rawContext, boolean orderReverse) {
        super(parentContext, position, rawContext);
        this.orderReverse = orderReverse;
    }

    public static ResultContext from(Context context, int begin, int end, boolean orderReverse){
        return new ResultContext(context, begin, context.rawContext.substring(begin, end), orderReverse);
    }

    public static ResultContext from(Context context, int begin, boolean orderReverse){
        return from(context, begin, context.rawContext.length(), orderReverse);
    }

    boolean contains(AbstractTerm term){
        return false;
    }
    ContextTerm firstByDescriptor(TermDescriptor descriptor){return null;}
    Collection<ContextTerm> closest(int distance){return null;}
}
