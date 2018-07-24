package net.eutkin.redirect.service.blacklist;

import com.giladam.listmatch.PatternList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultBlackListChecker implements BlackListChecker {
    @Override
    public boolean isOnBlackList(String ip, List<String> blackList) {
        PatternList patternList = new PatternList(blackList, ".", false);
        return patternList.matches(ip);
    }
}
