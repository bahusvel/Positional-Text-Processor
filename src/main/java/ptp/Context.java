package ptp;

import ptp.AbstractTerm.TERM_TYPE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

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

    public static Optional<Context> commonParent(Context a, Context b){
        ArrayList<Context> aParents = new ArrayList<>();
        ArrayList<Context> bParents = new ArrayList<>();
        Context aParent = a.parentContext;
        while (aParent != null){
            aParents.add(aParent);
            aParent = aParent.parentContext;
        }
        Context bParent = b.parentContext;
        while (bParent != null){
            bParents.add(bParent);
            bParent = bParent.parentContext;
        }
        int apos = -1;
        int bpos = -1;
        for (Context parent : aParents) {
            if (bParents.indexOf(parent) >= 0) {
                apos = bParents.indexOf(parent);
                break;
            }
        }
        for (Context parent : bParents) {
            if (aParents.indexOf(parent) >= 0) {
                bpos = aParents.indexOf(parent);
                break;
            }
        }
        if (apos <= bpos && apos >= 0)
            return Optional.of(aParents.get(apos));
        else if (bpos < apos && bpos >= 0)
            return Optional.of(bParents.get(bpos));
        return Optional.empty();
    }

    public static Optional<Context> fastLCA(Context p, Context q){
        int h1 = p.getHeight();
        int h2 = q.getHeight();
        // swap both nodes in case p is deeper than q.
        if (h1 > h2) {
            int h = h1;
            h1 = h2;
            h2 = h;
            Context a = p;
            p = q;
            q = a;
        }
        // invariant: h1 <= h2.
        int dh = h2 - h1;
        for (int h = 0; h < dh; h++)
            q = q.parentContext;
        while (p != null && q != null) {
            if (p == q) return Optional.of(p);
            p = p.parentContext;
            q = q.parentContext;
        }
        return Optional.empty();
    }

    private int getHeight(){
        int height = 0;
        Context p = this;
        while (p != null) {
            height++;
            p = p.parentContext;
        }
        return height;
    }

    @Override
    public String toString() {
        return "Context{" +
                "parentContext=" + parentContext +
                ", position=" + position +
                ", rawContext='" + rawContext + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Context test1 = new Context("bla bla");
        Context test2 = new Context("bla");
        Context parent1 = new Context("bla bla bla");
        Context parent2 = new Context("bla bla");
        test1.parentContext = parent1;
        test2.parentContext = parent2;
        parent2.parentContext = parent1;
        System.out.println(fastLCA(test1, test2));
    }
}
