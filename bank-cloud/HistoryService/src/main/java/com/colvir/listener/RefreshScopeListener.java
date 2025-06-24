package com.colvir.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RefreshScopeListener implements ApplicationListener<RefreshScopeRefreshedEvent> {

    @Override
    public void onApplicationEvent(RefreshScopeRefreshedEvent event) {
        log.info("Refresh scope triggered");
    }
}
