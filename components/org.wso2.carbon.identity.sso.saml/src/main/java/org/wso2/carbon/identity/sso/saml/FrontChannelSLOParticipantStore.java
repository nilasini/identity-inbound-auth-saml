/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.identity.sso.saml;

import org.wso2.carbon.identity.application.authentication.framework.store.SessionDataStore;
import org.wso2.carbon.identity.application.common.cache.BaseCache;
import org.wso2.carbon.identity.core.util.IdentityUtil;

import java.util.concurrent.TimeUnit;

/**
 * This is to store information of front-channel enabled session participants in a single logout.
 * Although this is extended from BaseCache this does not act as a cache. This is implemented just to act as
 * an in-memory storage.
 */
public class FrontChannelSLOParticipantStore extends BaseCache<String, FrontChannelSLOParticipantInfo> {

    private static final String CACHE_NAME = "FrontChannelSLOParticipantStore";
    private static volatile FrontChannelSLOParticipantStore instance = new FrontChannelSLOParticipantStore();
    private boolean isTemporarySessionDataPersistEnabled = false;

    private FrontChannelSLOParticipantStore() {

        super(CACHE_NAME);
        if (IdentityUtil.getProperty("JDBCPersistenceManager.SessionDataPersist.Temporary") != null) {
            isTemporarySessionDataPersistEnabled = Boolean.parseBoolean(
                    IdentityUtil.getProperty("JDBCPersistenceManager.SessionDataPersist.Temporary"));
        }
    }

    /**
     * Return instance of FrontChannelSLOParticipantStore.
     *
     * @return FrontChannelSLOParticipantStore instance.
     */
    public static FrontChannelSLOParticipantStore getInstance() {

        return instance;
    }

    /**
     * Store FrontChannelSLOParticipantInfo against logout request id of the current SLO invoked session participant.
     *
     * @param key   Logout request id of the current SLO invoked session participant.
     * @param entry FrontChannelSLOParticipantInfo.
     */
    public void addToCache(String key, FrontChannelSLOParticipantInfo entry) {

        super.addToCache(key, entry);
        if (isTemporarySessionDataPersistEnabled) {
            long validityPeriod = TimeUnit.MINUTES.toNanos(IdentityUtil.getTempDataCleanUpTimeout());
            entry.setValidityPeriod(validityPeriod);
            SessionDataStore.getInstance().storeSessionData(key, CACHE_NAME, entry);
        }
    }

    /**
     * Retrieve FrontChannelSLOParticipantInfo from the store using logout request id of the current SLO invoked
     * session participant.
     *
     * @param key Logout request id of the current SLO invoked session participant.
     * @return FrontChannelSLOParticipantInfo.
     */
    public FrontChannelSLOParticipantInfo getValueFromCache(String key) {

        FrontChannelSLOParticipantInfo cacheEntry = super.getValueFromCache(key);
        if (cacheEntry == null && isTemporarySessionDataPersistEnabled) {
            cacheEntry = (FrontChannelSLOParticipantInfo) SessionDataStore.getInstance().
                    getSessionData(key, CACHE_NAME);
        }
        return cacheEntry;
    }

    /**
     * Remove FrontChannelSLOParticipantInfo from the store for the given logout request id.
     *
     * @param key Logout request id of the current SLO invoked session participant.
     */
    public void clearCacheEntry(String key) {

        super.clearCacheEntry(key);
        if (isTemporarySessionDataPersistEnabled) {
            SessionDataStore.getInstance().clearSessionData(key, CACHE_NAME);
        }
    }
}
