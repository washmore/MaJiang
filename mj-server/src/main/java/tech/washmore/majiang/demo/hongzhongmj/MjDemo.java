package tech.washmore.majiang.demo.hongzhongmj;

import tech.washmore.majiang.demo.hongzhongmj.model.Majiang;
import tech.washmore.majiang.demo.hongzhongmj.model.PAI_XING;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Washmore on 2017/2/13.
 */
public class MjDemo {
    public static void main(String[] args) {
//        List<Majiang> myMajiang = new ArrayList<>();
//        MjHelper.开局();
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.一万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.一万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.一万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.二万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.三万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.四万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.五万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.六万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.七万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.八万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.九万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.九万));
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.九万));
//
//        myMajiang.add(MjHelper.发指定牌(PAI_XING.红中));
//
//        MjHelper.顺牌(myMajiang);
//        System.out.print("\t\t手牌牌型:" + myMajiang.stream().map(ma -> ma.getPai().name()).collect(Collectors.toList()));
//        long t1 = System.currentTimeMillis();
//        List<Majiang> result = MjHelper.验证胡牌(myMajiang);
//        long t2 = System.currentTimeMillis();
//        if (result != null) {
//            System.out.println("\t\t耗時:" + (t2 - t1) + "\t\t是否和牌:" + (result != null) + "\t\t胡牌牌型:" + result.stream().map(ma -> ma.getPai().name()).collect(Collectors.toList()));
//        } else {
//            System.out.println("\t\t耗時:" + (t2 - t1) + "\t\t是否和牌:" + (result != null));
//        }

        int index = 0;
        do {
            List<Majiang> myMajiang = new ArrayList<>();
            MjHelper.开局();
            for (int i = 0; i < 14; i++) {
                myMajiang.add(MjHelper.发牌());
            }
            MjHelper.顺牌(myMajiang);
            System.out.print(++index + "\t\t手牌牌型:" + myMajiang.stream().map(ma -> ma.getPai().name()).collect(Collectors.toList()));
            long t1 = System.currentTimeMillis();
            List<Majiang> result = MjHelper.验证胡牌(myMajiang);
            long t2 = System.currentTimeMillis();
            if (result != null) {
                System.out.println("\t\t耗時:" + (t2 - t1) + "\t\t是否和牌:" + (result != null) + "\t\t胡牌牌型:" + result.stream().map(ma -> ma.getPai().name()).collect(Collectors.toList()));
                break;
            } else {
                System.out.println("\t\t耗時:" + (t2 - t1) + "\t\t是否和牌:" + (result != null));
            }
        } while (true);
    }
}
