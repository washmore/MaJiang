package tech.washmore.majiang.demo.hongzhongmj.model;

/**
 * Created by Washmore on 2017/2/13.
 */
public enum PAI_XING {
    红中(0, 0),
    一饼(1, 1),
    二饼(1, 2),
    三饼(1, 3),
    四饼(1, 4),
    五饼(1, 5),
    六饼(1, 6),
    七饼(1, 7),
    八饼(1, 8),
    九饼(1, 9),
    一条(2, 1),
    二条(2, 2),
    三条(2, 3),
    四条(2, 4),
    五条(2, 5),
    六条(2, 6),
    七条(2, 7),
    八条(2, 8),
    九条(2, 9),
    一万(3, 1),
    二万(3, 2),
    三万(3, 3),
    四万(3, 4),
    五万(3, 5),
    六万(3, 6),
    七万(3, 7),
    八万(3, 8),
    九万(3, 9);

    private int se;
    private int shu;

    public int getSe() {
        return se;
    }

    public int getShu() {
        return shu;
    }

    PAI_XING(int se, int shu) {
        this.se = se;
        this.shu = shu;
    }

    public static PAI_XING getPai(int se, int shu) {
        for (PAI_XING pai : PAI_XING.values()) {
            if (pai.se == se && pai.shu == shu) {
                return pai;
            }
        }
        throw new IllegalArgumentException("没有这样的牌型");
    }
}
