package tech.washmore.majiang.demo.hongzhongmj.model;

/**
 * Created by Washmore on 2017/2/14.
 */
public class Majiang {
    public Majiang(int se, int shu) {
        this.se = se;
        this.shu = shu;
        this.pai = PAI_XING.getPai(se, shu);
    }

    public Majiang(PAI_XING paixing) {
        this(paixing.getSe(), paixing.getShu());
    }

    private int se;
    private int shu;
    private PAI_XING pai;

    public PAI_XING getPai() {
        return pai;
    }

    public void setPai(PAI_XING pai) {
        this.pai = pai;
    }

    public int getSe() {
        return se;
    }


    public void setSe(int se) {
        this.se = se;
    }

    public int getShu() {
        return shu;
    }

    public void setShu(int shu) {
        this.shu = shu;
    }
}
