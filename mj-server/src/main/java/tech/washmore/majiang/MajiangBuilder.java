//package tech.washmore.majiang;
//
//import com.alibaba.fastjson.JSON;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * Created by Washmore on 2017/2/13.
// */
//public class MajiangBuilder {
//    public static List<MajiangZi> allMajiang = new ArrayList<>();
//
//    public static void build() {
//        allMajiang = new ArrayList<>();
//        for (int se = 1; se <= 3; se++) {
//            for (int shu = 1; shu <= 9; shu++) {
//                for (int temp = 1; temp <= 4; temp++) {
//                    allMajiang.add(new MajiangZi(se, shu));
//                }
//            }
//        }
//        allMajiang.add(new MajiangZi(0, 0));
//        allMajiang.add(new MajiangZi(0, 0));
//        allMajiang.add(new MajiangZi(0, 0));
//        allMajiang.add(new MajiangZi(0, 0));
//        //  System.out.println(JSON.toJSONString(allMajiang, false));
//    }
//
//    public static MajiangZi fapai() {
//        if (allMajiang.size() == 0) {
//            return null;
//        }
//        return allMajiang.remove(new Random().nextInt(allMajiang.size()));
//    }
//
//    public static MajiangZi zuobi(int se, int shu) {
//        for (int i = 0; i < allMajiang.size(); i++) {
//            if (allMajiang.get(i).getPai().equals(PAIXING.getPai(se, shu))) {
//                return allMajiang.remove(i);
//            }
//        }
//        return null;
//    }
//
//    public static void shunpai(List<MajiangZi> majiang) {
//        Collections.sort(majiang, new Comparator<MajiangZi>() {
//            @Override
//            public int compare(MajiangZi o1, MajiangZi o2) {
//                if (o1.getSe() == o2.getSe()) {
//                    return o1.getShu() - o2.getShu();
//                }
//                return o1.getSe() - o2.getSe();
//            }
//        });
//    }
//
//    public static List<MajiangZi> isHupai(List<MajiangZi> majiangZiList) {
//        shunpai(majiangZiList);
//        List<MajiangZi> hongzhong = new ArrayList<>();
//        List<MajiangZi> other = new ArrayList<>();
//        List<List<MajiangZi>> list = new ArrayList<>();
//        for (int i = 0; i < majiangZiList.size(); i++) {
//            MajiangZi z1 = majiangZiList.get(i);
//            if (z1.getPai().equals(PAIXING.红中)) {
//                hongzhong.add(z1);
//                continue;
//            } else {
//                if (hongzhong.size() > 0) {
//                    break;
//                }
//            }
//            List<MajiangZi> t = new ArrayList<>();
//            t.add(z1);
//            for (int j = i + 1; j < majiangZiList.size(); j++) {
//                MajiangZi z2 = majiangZiList.get(j);
//                if (z1.getPai().equals(z2.getPai())) {
//                    t.add(z2);
//                    i++;
//                }
//            }
//            if (t.size() > 4) {
//                return null;
//            }
//            list.add(t);
//        }
//        if (hongzhong.size() > 0) {
//            other = majiangZiList.subList(hongzhong.size(), majiangZiList.size());
//            List<List<MajiangZi>> newH = replaceHongzhong(hongzhong.size(), new ArrayList<>());
//            for (List<MajiangZi> nm : newH) {
//                List<MajiangZi> temp = new ArrayList<>();
//                temp.addAll(nm);
//                temp.addAll(other);
//                if (isHupai(temp) != null) {
//                    return temp;
//                }
//            }
//        } else {
//            if (try2N(list)) {
//                return majiangZiList;
//            }
//        }
//
//        return null;
//    }
//
//    private static List<List<MajiangZi>> replaceHongzhong(int hzNum, List<List<MajiangZi>> source) {
//        List<List<MajiangZi>> target = new ArrayList<>();
//        if (source == null || source.size() == 0) {
//            for (PAIXING pai : PAIXING.values()) {
//                if (pai.equals(PAIXING.红中)) {
//                    continue;
//                }
//                List<MajiangZi> newM = new ArrayList<>();
//                newM.add(new MajiangZi(pai.getSe(), pai.getShu()));
//                target.add(newM);
//            }
//        } else {
//            for (List<MajiangZi> m : source) {
//                for (PAIXING pai : PAIXING.values()) {
//                    if (pai.equals(PAIXING.红中)) {
//                        continue;
//                    }
//                    List<MajiangZi> newM = new ArrayList<>();
//                    newM.add(new MajiangZi(pai.getSe(), pai.getShu()));
//                    newM.addAll(m);
//                    target.add(newM);
//                }
//            }
//        }
//
//        if (--hzNum > 0) {
//            return replaceHongzhong(hzNum, target);
//        } else {
//            return target;
//        }
//    }
//
//    private static boolean try2N(List<List<MajiangZi>> list) {
//        //    System.out.println(JSON.toJSONString(list, true));
//        for (List<MajiangZi> l : list) {
//            if (l.size() >= 2) {
//                MajiangZi r1 = l.remove(0);
//                MajiangZi r2 = l.remove(0);
//                if (is3N(myCopy(list))) {
//                    return true;
//                }
//                l.add(0, r2);
//                l.add(0, r1);
//            }
//        }
//
//        return false;
//
//    }
//
//    private static boolean is3N(List<List<MajiangZi>> list) {
//        //System.out.println(JSON.toJSONString(list));
//        List<List<MajiangZi>> copy = list.stream().filter(l -> l.size() > 0).collect(Collectors.toList());
//        if (copy == null || copy.size() == 0) {
//            return true;
//        }
//        List<MajiangZi> check = copy.get(0);
//        if (check.size() == 4) {
//            if (copy.size() < 3) {
//                return false;
//            }
//            MajiangZi first = check.get(0);
//            MajiangZi second = copy.get(1).get(0);
//            MajiangZi third = copy.get(2).get(0);
//            if (!(first.getSe() == second.getSe() && first.getSe() == third.getSe()//
//                    && first.getShu() == second.getShu() - 1 && first.getShu() == third.getShu() - 2)) {
//                return false;
//            }
//            copy.get(0).removeAll(check);
//            copy.get(1).remove(0);
//            copy.get(2).remove(0);
//        } else if (check.size() == 1 || check.size() == 2) {
//            if (copy.size() < 3) {
//                return false;
//            }
//            MajiangZi first = check.get(0);
//            MajiangZi second = copy.get(1).get(0);
//            MajiangZi third = copy.get(2).get(0);
//            if (!(first.getSe() == second.getSe() && first.getSe() == third.getSe()//
//                    && first.getShu() == second.getShu() - 1 && first.getShu() == third.getShu() - 2)) {
//                return false;
//            }
//            copy.get(0).remove(0);
//            copy.get(1).remove(0);
//            copy.get(2).remove(0);
//        } else if (check.size() == 3) {
//            copy.get(0).removeAll(check);
//        }
//        return is3N(copy);
//    }
//
//    public static List<List<MajiangZi>> myCopy(List<List<MajiangZi>> source) {
//        List<List<MajiangZi>> target = new ArrayList<>();
//        if (source == null || source.size() == 0) {
//            return target;
//        }
//        for (List<MajiangZi> l : source) {
//            if (l == null || l.size() == 0) {
//                continue;
//            }
//            List<MajiangZi> l2 = new ArrayList<>();
//            for (MajiangZi m : l) {
//                l2.add(new MajiangZi(m.getSe(), m.getShu()));
//            }
//            target.add(l2);
//        }
//        return target;
//    }
//}
