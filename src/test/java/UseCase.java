import ptp.AbstractTerm.TERM_TYPE;
import ptp.Context;
import ptp.ContextTerm;
import ptp.descriptors.RegexDescriptor;

/**
 * Created by denislavrov on 2/17/15.
 */
public class UseCase {
    public static void main(String[] args) {
        String rawContext = "Apples grow on trees.\n" +
                "Oranges grow somewhere.\n" +
                "Cows are not fruit.";
        Context context = new Context(rawContext);
        RegexDescriptor descriptor = new RegexDescriptor("Oranges", TERM_TYPE.WORD);
        for (ContextTerm contextTerm : descriptor.findAllTerms(context)) {
            System.out.println(contextTerm.before());
        }
    }
}
