package net.eutkin.redirect.service.blacklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class CompositeBlackListChecker implements BlackListChecker {

    private List<BlackListChecker> blackListCheckers = new ArrayList<>();


    @Override
    public boolean isOnBlackList(String ip, List<String> blackList) {
        if (blackListCheckers.isEmpty()) {
            return false;
        }
        return blackListCheckers.stream().anyMatch(blackListChecker -> blackListChecker.isOnBlackList(ip, blackList));
    }

    @Autowired(required = false)
    public void setBlackListCheckers(List<BlackListChecker> blackListCheckers) {
        this.blackListCheckers = blackListCheckers;
    }
}
