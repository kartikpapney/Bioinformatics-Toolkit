package Model;

import Interface.OutputInterface;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class InputModel implements OutputInterface {

    private static final Object[][] ob = new Object[][]{{"UUU",0},{"UCU",1},{"UAU",2},{"UGU",3},{"UUC",4},{"UCC",5},{"UAC",6},{"UGC",7},{"UUA",8},{"UCA",9},{"UAA",10},{"UGA",11},{"UUG",12},{"UCG",13},{"UAG",14},{"UGG",15},{"CUU",16},{"CCU",17},{"CAU",18},{"CGU",19},{"CUC",20},{"CCC",21},{"CAC",22},{"CGC",23},{"CUA",24},{"CCA",25},{"CAA",26},{"CGA",27},{"CUG",28},{"CCG",29},{"CAG",30},{"CGG",31},{"AUU",32},{"ACU",33},{"AAU",34},{"AGU",35},{"AUC",36},{"ACC",37},{"AAC",38},{"AGC",39},{"AUA",40},{"ACA",41},{"AAA",42},{"AGA",43},{"AUG",44},{"ACG",45},{"AAG",46},{"AGG",47},{"GUU",48},{"GCU",49},{"GAU",50},{"GGU",51},{"GUC",52},{"GCC",53},{"GAC",54},{"GGC",55},{"GUA",56},{"GCA",57},{"GAA",58},{"GGA",59},{"GUG",60},{"GCG",61},{"GAG",62},{"GGG",63}};
    private static final Map<String, Integer> mapping = new HashMap<>();
    private static final double SynSite[]={0.333333333,1.000000000,0.333333333,0.333333333,0.333333333,1.000000000,0.333333333,0.333333333,0.666666667,1.000000000,-1.0,-1.0,0.666666667,1.000000000,-1.0,0.000000000,1.000000000,1.000000000,0.333333333,1.000000000,1.000000000,1.000000000,0.333333333,1.000000000,1.333333333,1.000000000,0.333333333,1.333333333,1.333333333,1.000000000,0.333333333,1.333333333,0.666666667,1.000000000,0.333333333,0.333333333,0.666666667,1.000000000,0.333333333,0.333333333,0.666666667,1.000000000,0.333333333,0.666666667,0.000000000,1.000000000,0.333333333,0.666666667,1.000000000,1.000000000,0.333333333,1.000000000,1.000000000,1.000000000,0.333333333,1.000000000,1.000000000,1.000000000,0.333333333,1.000000000,1.000000000,1.000000000,0.333333333,1.000000000};
    private static final String[] AA3 = {"Phe","Ser","Tyr","Cys","Phe","Ser","Tyr","Cys","Leu","Ser","TER","TER","Leu","Ser","TER","Trp","Leu","Pro","His","Arg","Leu","Pro","His","Arg","Leu","Pro","Gln","Arg","Leu","Pro","Gln","Arg","Ile","Thr","Asn","Ser","Ile","Thr","Asn","Ser","Ile","Thr","Lys","Arg","Met","Thr","Lys","Arg","Val","Ala","Asp","Gly","Val","Ala","Asp","Gly","Val","Ala","Glu","Gly","Val","Ala","Glu","Gly"};
    private static final char AA1[]={'F','S','Y','C','F','S','Y','C','L','S','X','X','L','S','X','W','L','P','H','R','L','P','H','R','L','P','Q','R','L','P','Q','R','I','T','N','S','I','T','N','S','I','T','K','R','M','T','K','R','V','A','D','G','V','A','D','G','V','A','E','G','V','A','E','G'};
    public List<GeneSequenceModel> sequences = new ArrayList<>();
    public List<Model> result = new ArrayList<Model>();
    public InputModel() {
//        HashSet<Character> a = new HashSet<>(), b = new HashSet<>(), c = new HashSet<>(), d = new HashSet<>();

        for(Object[] o : ob) {
//            double val = SynSite[(Integer)o[1]];
            mapping.put(((String) o[0]).toLowerCase(), (Integer) o[1]);
//            if(val == 0)  {
//                a.add(AA1[(Integer)o[1]]);
//            }
//            if(val == 0.333333333)  {
//                b.add(AA1[(Integer)o[1]]);
//            }
//            if(val == 0.666666667)  {
//                c.add(AA1[(Integer)o[1]]);
//            } if(val == 1) {
//                d.add(AA1[(Integer) o[1]]);
//            }
//            if(AA1[(Integer)o[1]] == 'L' || AA1[(Integer)o[1]] == 'R' || AA1[(Integer)o[1]] == 'S') {
//                System.out.println(AA1[(Integer)o[1]] + " " + ((String) o[0]).toUpperCase(Locale.ROOT) + " " + SynSite[(Integer)o[1]]);
//            }
        }
//        System.out.println(a);
//        System.out.println(b);System.out.println(c);System.out.println(d);
    }
    public void calculate() {
        int sno = 1;
        for(int i=0; i<sequences.size(); i++) {
            GeneSequenceModel s = sequences.get(i);
            if(checkIfInputIsValid(s)) {
                double[] change = findAverageValueOfSynonymousAndNonSynonymousChange(s.getSequenceA(), s.getSequenceB());
                int[] count = countSynonymousAndNonSynonymousChange(s.getSequenceA(), s.getSequenceB());
                result.add(new Model(sno, s.getGeneName(),
                        s.getSequenceA().length(),
                        count[0], count[1], change[0], change[1]));
                sno++;
            }
        }
    }
    public void input(File a, File b) throws IOException {
        BufferedReader areader = new BufferedReader(new FileReader(a));
        BufferedReader breader = new BufferedReader(new FileReader(b));
        String geneName, sequenceA, sequenceB;
        while ((geneName = areader.readLine())!=null) {
            breader.readLine();
            sequenceA = areader.readLine();
            sequenceB = breader.readLine();
            setSequences(geneName, sequenceA, sequenceB);
        }
    }
    public void setSequences(String geneName, String sequenceA, String sequenceB) {
        sequences.add(new GeneSequenceModel(geneName, sequenceA, sequenceB));
    }

    public static boolean checkIfInputIsValid(GeneSequenceModel model) {
        if(model == null || model.getSequenceB().length() != model.getSequenceA().length() || model.getSequenceA().length()%3 != 0) return false;
        int n=model.getSequenceA().length();
        for(int i=0; i<n-2; i+=3) {
            StringBuilder codonA = new StringBuilder();
            StringBuilder codonB = new StringBuilder();
            for(int j=i; j<i+3; j++) {
                codonA.append(model.getSequenceA().charAt(j));
                codonB.append(model.getSequenceB().charAt(j));
            }
            if(!mapping.containsKey(codonA.toString()) || !mapping.containsKey(codonB.toString())) return false;
        }
        return true;
    }

    public String getAminoAcidSequence(String sequence) {
        StringBuilder aminoAcidSequence = new StringBuilder();
        for(int i=0; i<sequence.length()-2; i+=3) {
            StringBuilder codon = new StringBuilder();
            for(int j=i; j<i+3; j++) {
                codon.append(sequence.charAt(j));
            }
            aminoAcidSequence.append(mapping.get(codon.toString()));
        }
        return aminoAcidSequence.toString();
    }

    @Override
    public double[] findSynonymousAndNonSynonymousChange(String sequence) {
        double[] result = new double[]{0, 0};
        for(int i=0; i<sequence.length()-2; i+=3) {
            StringBuilder codon = new StringBuilder();
            for(int j=i; j<i+3; j++) {
                codon.append(sequence.charAt(j));
            }
            result[0] += SynSite[mapping.get(codon.toString())];
            result[1] += 3 - SynSite[mapping.get(codon.toString())];
        }
        return result;
    }

    @Override
    public int[] countSynonymousAndNonSynonymousChange(String a, String b) {
        int[] result = new int[]{0, 0};
        for(int i=0; i<a.length()-2; i+=3) {
            StringBuilder acodon = new StringBuilder(), bcodon = new StringBuilder();
            for(int j=i; j<i+3; j++) {
                acodon.append(a.charAt(j));
                bcodon.append(b.charAt(j));
            }
            if(acodon.toString().equals(bcodon.toString())) {

            } else {
                String ac = acodon.toString();
                String bc = bcodon.toString();
                if(ac.charAt(2) != bc.charAt(2)) result[0]++;
                if(ac.charAt(1) != bc.charAt(1)) result[1]++;
                if(ac.charAt(0) != bc.charAt(0)) result[1]++;
            }
        }
        return result;
    }

    @Override
    public double[] findAverageValueOfSynonymousAndNonSynonymousChange(String first, String second) {
        double[] a = findSynonymousAndNonSynonymousChange(first);
        double[] b = findSynonymousAndNonSynonymousChange(second);
        return new double[]{(a[0]+b[0])/2, (a[1]+b[1])/2};
    }
}