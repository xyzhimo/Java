package com.xyz.groupby;

import com.xyz.groupby.dos.StudentDO;
import com.xyz.groupby.dos.UserDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Group {

    public static List<UserDO> init() {
        List<UserDO> result = new ArrayList<>();
        UserDO userDO = new UserDO(new StudentDO(0, "小明"), "男");
        UserDO userDO1 = new UserDO(new StudentDO(1, "小明10"), "男");
        UserDO userDO2 = new UserDO(new StudentDO(2, "小明20"), "男");
        UserDO userDO3 = new UserDO(new StudentDO(2, "小明21"), "男");
        UserDO userDO4 = new UserDO(new StudentDO(1, "小明11"), "男");
        UserDO userDO5 = new UserDO(new StudentDO(3, "小明3"), "女");
        result.add(userDO);
        result.add(userDO1);
        result.add(userDO2);
        result.add(userDO3);
        result.add(userDO4);
        result.add(userDO5);

        return result;
    }

    public static void main(String[] args) {
        List<UserDO> userDOS = init();
        Map<StudentDO, List<UserDO>> collect = userDOS.stream().collect(Collectors.groupingBy(UserDO::getStudentDO));
        for (Map.Entry<StudentDO, List<UserDO>> studentDOListEntry : collect.entrySet()) {
            System.out.println(studentDOListEntry.getKey() + " - " + studentDOListEntry.getValue());
        }

        List<UserDO> userDOS1 = collect.get(new StudentDO(1, ""));
        System.out.println(userDOS1);
    }
}
