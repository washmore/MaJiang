package tech.washmore.majiang.demo.changshamj;

import tech.washmore.majiang.demo.changshamj.model.Majiang;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Washmore on 2017/2/13.
 */
public class MjDemo {
    public static void main(String[] args) {
//        List<Majiang> myMajiang = new ArrayList<>();
//        MjHelper.build();
//        myMajiang.add(MjHelper.zuobi(1, 1));
//        myMajiang.add(MjHelper.zuobi(1, 1));
//        myMajiang.add(MjHelper.zuobi(1, 1));
//        myMajiang.add(MjHelper.zuobi(1, 2));
//        myMajiang.add(MjHelper.zuobi(1, 3));
//        myMajiang.add(MjHelper.zuobi(1, 4));
//        myMajiang.add(MjHelper.zuobi(1, 5));
//        myMajiang.add(MjHelper.zuobi(1, 6));
//        myMajiang.add(MjHelper.zuobi(1, 7));
//        myMajiang.add(MjHelper.zuobi(1, 8));
//        myMajiang.add(MjHelper.zuobi(1, 9));
//        myMajiang.add(MjHelper.zuobi(0, 0));
//        myMajiang.add(MjHelper.zuobi(1, 9));
//        myMajiang.add(MjHelper.zuobi(1, 9));
//
//        long t1 = System.currentTimeMillis();
//        List<MajiangZi> result = MjHelper.isHupai(myMajiang);
//        long t2 = System.currentTimeMillis();
//
//        System.out.println("\t耗時:" + (t2 - t1) + "\t是否和牌:" + (result != null) + "\t手牌牌型:" + myMajiang.stream().map(ma -> ma.getPai().name()).collect(Collectors.toList()) + "\t胡牌牌型:" + result.stream().map(ma -> ma.getPai().name()).collect(Collectors.toList()));

        int index = 0;
        do {
            List<Majiang> myMajiang = new ArrayList<>();
            MjHelper.开局();
            for (int i = 0; i < 14; i++) {
                myMajiang.add(MjHelper.发牌());
            }
            MjHelper.验证胡牌(myMajiang);
            System.out.print(++index + "\t手牌牌型:" + myMajiang.stream().map(ma -> ma.getPai().name()).collect(Collectors.toList()));
            long t1 = System.currentTimeMillis();
            List<Majiang> result = MjHelper.验证胡牌(myMajiang);
            long t2 = System.currentTimeMillis();
            if (result != null) {
                System.out.println("\t耗時:" + (t2 - t1) + "\t是否和牌:" + (result != null) + "\t胡牌牌型:" + result.stream().map(ma -> ma.getPai().name()).collect(Collectors.toList()));
                break;
            } else {
                System.out.println("\t耗時:" + (t2 - t1) + "\t是否和牌:" + (result != null));
            }
        } while (true);
    }
}
