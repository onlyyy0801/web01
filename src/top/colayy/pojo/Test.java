package top.colayy.pojo;

public class Test {
    private String tId;
    private int tType;
    private String tClassify;
    private String tTopic;
    private String createTime;
    private String tAnswer;
    private int tScore;

    private String oA;
    private String oB;
    private String oC;
    private String oD;


    public Test() {
    }

//    public Test(String tType, String tClassify, String tTopic, String createTime, String tAnswer, int tScore) {
//        this.tType = tType;
//        this.tClassify = tClassify;
//        this.tTopic = tTopic;
//        this.createTime = createTime;
//        this.tAnswer = tAnswer;
//        this.tScore = tScore;
//    }
//
//    public Test(String oA, String oB, String oC, String oD) {
//        this.oA = oA;
//        this.oB = oB;
//        this.oC = oC;
//        this.oD = oD;
//    }


    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public int gettType() {
        return tType;
    }

    public void settType(int tType) {
        this.tType = tType;
    }

    public String gettClassify() {
        return tClassify;
    }

    public void settClassify(String tClassify) {
        this.tClassify = tClassify;
    }

    public String gettTopic() {
        return tTopic;
    }

    public void settTopic(String tTopic) {
        this.tTopic = tTopic;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String gettAnswer() {
        return tAnswer;
    }

    public void settAnswer(String tAnswer) {
        this.tAnswer = tAnswer;
    }

    public int gettScore() {
        return tScore;
    }

    public void settScore(int tScore) {
        this.tScore = tScore;
    }

    public String getoA() {
        return oA;
    }

    public void setoA(String oA) {
        this.oA = oA;
    }

    public String getoB() {
        return oB;
    }

    public void setoB(String oB) {
        this.oB = oB;
    }

    public String getoC() {
        return oC;
    }

    public void setoC(String oC) {
        this.oC = oC;
    }

    public String getoD() {
        return oD;
    }

    public void setoD(String oD) {
        this.oD = oD;
    }
}
