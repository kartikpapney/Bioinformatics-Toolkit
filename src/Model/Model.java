package Model;

import java.text.DecimalFormat;

public class Model {
    private static final DecimalFormat four = new DecimalFormat("#0.00");
    private int sno;
    private String geneName;
    private int geneSequenceSize, noOfNonSynonymousChange, noOfSynonymousChange;
    private String avgOfSynonymousChange, avgOfNonSynonymousChange;
    private String dn, ds, dnbyds;

    public Model() {

    }
    public Model(int sno, String geneName, int geneSequenceSize, int noOfSynonymousChange, int noOfNonSynonymousChange, Double avgOfSynonymousChange, Double avgOfNonSynonymousChange) {
        this.sno = sno;
        this.geneName = geneName.substring(1);
        this.geneSequenceSize = geneSequenceSize;
        this.noOfNonSynonymousChange = noOfNonSynonymousChange;
        this.noOfSynonymousChange = noOfSynonymousChange;
        this.avgOfSynonymousChange = four.format(avgOfSynonymousChange);
        this.avgOfNonSynonymousChange = four.format(avgOfNonSynonymousChange);
        double ps = noOfSynonymousChange/avgOfSynonymousChange;
        double pn = noOfNonSynonymousChange/avgOfNonSynonymousChange;
        double ds = -(3.0/4)*Math.log(1-(4.0/3)*ps);
        double dn = -(3.0/4)*Math.log(1-(4.0/3)*pn);
        this.dn =   four.format(dn);
        this.ds =  four.format(ds);
        if(ds == 0) {
            this.dnbyds = "NA";
        } else {
            this.dnbyds = four.format(dn/ds);
        }
    }

    @Override
    public String toString() {
        return geneName + ", " + geneSequenceSize + ", " + noOfSynonymousChange + ", " + noOfNonSynonymousChange + ", " + avgOfSynonymousChange + ", " + avgOfNonSynonymousChange;
    }

    public Object[] getModel() {
        return new Object[]{sno, geneName, geneSequenceSize, noOfSynonymousChange, noOfNonSynonymousChange, avgOfSynonymousChange, avgOfNonSynonymousChange, dn, ds, dnbyds};
    }
}