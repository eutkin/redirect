package net.eutkin.redirect.service.blacklist;

import java.util.List;

public interface BlackListChecker {

    /**
     * Проверяет, входит ли ip в черный список
     * @param ip адрес
     * @param blackList черный список
     * @return {@literal true} если входит, иначе {@literal false}
     */
    boolean isOnBlackList(String ip, List<String> blackList);
}
