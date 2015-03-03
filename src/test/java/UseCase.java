import ptp.AbstractTerm.TERM_TYPE;
import ptp.Context;
import ptp.ContextTerm;
import ptp.descriptors.LineDescriptor;
import ptp.descriptors.RawDescriptor;
import ptp.descriptors.RegexDescriptor;
import ptp.descriptors.WordDescriptor;

/**
 * Created by denislavrov on 2/17/15.
 */
public class UseCase {
    public static void main(String[] args) {
        String rawContext = "Special Session: MoN2-1 Concept Drift, Domain Adaptation & Learning in Dynamic Environments I,\n" +
                "Chair: Giacomo Boracchi and Manuel Roveri, Room: " +
                "                                           SUPERROOM308 ......................................................................... 165\n" +
                "4:00PM Trotting Gait Planning for a Quadruped Robot with High Payload Walking on Irregular Terrain Nan Hu, Shaoyuan Li, Dan Huang and Feng Gao\n" +
                "4:20PM Using HDDT to Avoid Instances Propagation in Unbalanced and Evolving Data Streams\n" +
                "Andrea Dal Pozzolo, Reid Johnson, Room Olivier Caelen, Serge Waterschoot, Nitesh V. Chawla and Gianluca Bontempi\n" +
                "4:40PM Domain Adaptation Bounds for Multiple Expert Systems Under Concept Room Drift Gregory Ditzler, Gail Rosen and Robi Polikar";
        Context context = new Context(rawContext);
        RawDescriptor descriptor = new RawDescriptor(TERM_TYPE.WORD, "Room");
        RawDescriptor water = new RawDescriptor(TERM_TYPE.WORD, "Waterschoot");
        for (ContextTerm contextTerm : descriptor.findAllTerms(context)) {
            System.out.println(contextTerm.between(water.findFirst(context)));
        }
    }
}
