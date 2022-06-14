package Model;

public class GeneSequenceModel {
    private String geneName;
    private String sequenceA;
    private String sequenceB;

    private String validate(String s) {
        StringBuilder strb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c >= 'a' && c <= 'z') c = (char)(c - 'a' + 'A');
            if(c == 'T') c = 'U';
            strb.append(c);
        }
        return strb.toString();
    }
    public GeneSequenceModel(String geneName, String sequenceA, String sequenceB) {
        setSequenceA(validate(sequenceA));
        setSequenceB(validate(sequenceB));
        setGeneName(geneName);
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName.toLowerCase();
    }

    public String getSequenceA() {
        return sequenceA;
    }

    public void setSequenceA(String sequenceA) {
        this.sequenceA = sequenceA.toLowerCase();
    }

    public String getSequenceB() {
        return sequenceB;
    }

    public void setSequenceB(String sequenceB) {
        this.sequenceB = sequenceB.toLowerCase();
    }

    @Override
    public String toString() {
        return this.geneName + " -> " + this.sequenceA + ", " + this.sequenceB;
    }
}
