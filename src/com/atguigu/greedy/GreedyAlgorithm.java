package com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Leo
 * @date 2020/8/14 - 9:50
 */

public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建广播电台，放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();

        //设置电台覆盖范围
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //电台及其覆盖范围加入到HashMap中
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //存放所有地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.addAll(hashSet1);
        allAreas.addAll(hashSet2);
        allAreas.addAll(hashSet3);
        allAreas.addAll(hashSet4);
        allAreas.addAll(hashSet5);

        //创建ArrayList存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();

        //定义一个临时的集合，在遍历的过程中存放电台覆盖范围和当前还没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义一个maxKey，保存在遍历过程中，能够覆盖最大未覆盖地区对应的电台的key，即K1,K2,K3...
        //如果maxKey不为null，则加入到selects
        String maxKey = null;
        while (allAreas.size() != 0) {//还有没有覆盖到的地区
            maxKey = null;//maxKey在每一轮循环开始都要重新寻找，需要置空
            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集，交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前集合包含的未覆盖地区的数量比maxKey指向的集合要多，就重新赋值给maxKey
                //tempSet.size() > broadcasts.get(maxKey).size()体现出贪心算法的特点，每次都选择最优的
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区从allAreas中去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("选择结果为：" + selects);
    }

}
