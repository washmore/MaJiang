package tech.washmore.majiang.demo.hongzhongmj;

import tech.washmore.majiang.demo.hongzhongmj.model.Majiang;
import tech.washmore.majiang.demo.hongzhongmj.model.PAI_XING;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Washmore on 2017/2/14.
 */
public class MjHelper {
    public static List<Majiang> allMajiang;

    public static void 开局() {
        allMajiang = new ArrayList<>();
        for (int se = 1; se <= 3; se++) {
            for (int shu = 1; shu <= 9; shu++) {
                for (int temp = 1; temp <= 4; temp++) {
                    allMajiang.add(new Majiang(se, shu));
                }
            }
        }
        allMajiang.add(new Majiang(PAI_XING.红中));
        allMajiang.add(new Majiang(PAI_XING.红中));
        allMajiang.add(new Majiang(PAI_XING.红中));
        allMajiang.add(new Majiang(PAI_XING.红中));
    }

    public static Majiang 发牌() {
        if (allMajiang.size() == 0) {
            return null;
        }
        return allMajiang.remove(new Random().nextInt(allMajiang.size()));
    }

    public static Majiang 发指定牌(PAI_XING paixing) {
        for (int i = 0; i < allMajiang.size(); i++) {
            if (allMajiang.get(i).getPai().equals(paixing)) {
                return allMajiang.remove(i);
            }
        }
        throw new IllegalArgumentException("没有这种牌了");
    }

    public static void 顺牌(List<Majiang> majiang) {
        Collections.sort(majiang, new Comparator<Majiang>() {
            @Override
            public int compare(Majiang o1, Majiang o2) {
                if (o1.getSe() == o2.getSe()) {
                    return o1.getShu() - o2.getShu();
                }
                return o1.getSe() - o2.getSe();
            }
        });
    }


    public static List<Majiang> 验证胡牌(List<Majiang> majiangZiList) {
        顺牌(majiangZiList);
        List<Majiang> hongzhong = new ArrayList<>();
        List<List<Majiang>> list = new ArrayList<>();
        for (int i = 0; i < majiangZiList.size(); i++) {
            Majiang z1 = majiangZiList.get(i);
            if (z1.getPai().equals(PAI_XING.红中)) {
                hongzhong.add(z1);
                continue;
            } else {
                if (hongzhong.size() > 0) {
                    break;
                }
            }
            List<Majiang> t = new ArrayList<>();
            t.add(z1);
            for (int j = i + 1; j < majiangZiList.size(); j++) {
                Majiang z2 = majiangZiList.get(j);
                if (z1.getPai().equals(z2.getPai())) {
                    t.add(z2);
                    i++;
                }
            }
            if (t.size() > 4) {
                return null;
            }
            list.add(t);
        }
        if (hongzhong.size() > 0) {
            List<Majiang> other = majiangZiList.subList(hongzhong.size(), majiangZiList.size());
            List<List<Majiang>> newH = 替换红中赖子(hongzhong.size(), new ArrayList<>());
            for (List<Majiang> nm : newH) {
                List<Majiang> temp = new ArrayList<>();
                temp.addAll(nm);
                temp.addAll(other);
                if (验证胡牌(temp) != null) {
                    return temp;
                }
            }
        } else {
            if (验证2(list)) {
                return majiangZiList;
            }
        }

        return null;
    }

    private static List<List<Majiang>> 替换红中赖子(int hzNum, List<List<Majiang>> source) {
        List<List<Majiang>> target = new ArrayList<>();
        if (source == null || source.size() == 0) {
            for (PAI_XING pai : PAI_XING.values()) {
                if (pai.equals(PAI_XING.红中)) {
                    continue;
                }
                List<Majiang> newM = new ArrayList<>();
                newM.add(new Majiang(pai));
                target.add(newM);
            }
        } else {
            for (List<Majiang> m : source) {
                for (PAI_XING pai : PAI_XING.values()) {
                    if (pai.equals(PAI_XING.红中)) {
                        continue;
                    }
                    List<Majiang> newM = new ArrayList<>();
                    newM.add(new Majiang(pai));
                    newM.addAll(m);
                    target.add(newM);
                }
            }
        }

        if (--hzNum > 0) {
            return 替换红中赖子(hzNum, target);
        } else {
            return target;
        }
    }

    private static boolean 验证2(List<List<Majiang>> list) {
        //    System.out.println(JSON.toJSONString(list, true));
        for (List<Majiang> l : list) {
            if (l.size() >= 2) {
                Majiang r1 = l.remove(0);
                Majiang r2 = l.remove(0);
                if (验证3N(自制copy(list))) {
                    return true;
                }
                l.add(0, r2);
                l.add(0, r1);
            }
        }

        return false;

    }

    private static boolean 验证3N(List<List<Majiang>> list) {
        List<List<Majiang>> trimList = list.stream().filter(l -> l.size() > 0).collect(Collectors.toList());
        if (trimList == null || trimList.size() == 0) {
            return true;
        }
        List<Majiang> check = trimList.get(0);
        if (check.size() == 4) {
            if (trimList.size() < 3) {
                return false;
            }
            if (!处理顺子(trimList)) {
                return false;
            }
        } else if (check.size() == 1 || check.size() == 2) {
            if (trimList.size() < 3) {
                return false;
            }
            if (!处理顺子(trimList)) {
                return false;
            }
        } else if (check.size() == 3) {
            trimList.get(0).removeAll(check);
        }
        return 验证3N(trimList);
    }

    public static boolean 处理顺子(List<List<Majiang>> trimList) {
        Majiang first = trimList.get(0).get(0);
        Majiang second = trimList.get(1).get(0);
        Majiang third = trimList.get(2).get(0);
        if (!(first.getSe() == second.getSe() && first.getSe() == third.getSe()//
                && first.getShu() == second.getShu() - 1 && first.getShu() == third.getShu() - 2)) {
            return false;
        }
        trimList.get(0).remove(0);
        trimList.get(1).remove(0);
        trimList.get(2).remove(0);
        return true;
    }

    public static List<List<Majiang>> 自制copy(List<List<Majiang>> source) {
        List<List<Majiang>> target = new ArrayList<>();
        if (source == null || source.size() == 0) {
            return target;
        }
        for (List<Majiang> l : source) {
            if (l == null || l.size() == 0) {
                continue;
            }
            List<Majiang> l2 = new ArrayList<>();
            for (Majiang m : l) {
                l2.add(new Majiang(m.getSe(), m.getShu()));
            }
            target.add(l2);
        }
        return target;
    }
}
