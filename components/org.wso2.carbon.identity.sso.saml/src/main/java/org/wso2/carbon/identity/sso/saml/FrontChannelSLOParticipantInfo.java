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

import org.wso2.carbon.identity.sso.saml.cache.CacheEntry;

/**
 * Information of front-channel enabled session participants in a single logout.
 */
public class FrontChannelSLOParticipantInfo extends CacheEntry {

    private static final long serialVersionUID = -3909575392953155294L;

    private String originalIssuerLogoutRequestId;
    private String originalLogoutRequestIssuer;
    private String currentSLOInvokedParticipant;
    private String sessionIndex;
    private boolean isIdPInitSLO;
    private String relayState;
    private String returnToURL;

    public FrontChannelSLOParticipantInfo() {

    }

    public FrontChannelSLOParticipantInfo(String originalIssuerLogoutRequestId, String originalLogoutRequestIssuer,
                                          String currentSLOInvokedParticipant, String sessionIndex,
                                          boolean isIdPInitSLO, String relayState, String returnToURL) {

        this.originalIssuerLogoutRequestId = originalIssuerLogoutRequestId;
        this.originalLogoutRequestIssuer = originalLogoutRequestIssuer;
        this.currentSLOInvokedParticipant = currentSLOInvokedParticipant;
        this.sessionIndex = sessionIndex;
        this.isIdPInitSLO = isIdPInitSLO;
        this.relayState = relayState;
        this.returnToURL = returnToURL;
    }

    public String getOriginalIssuerLogoutRequestId() {

        return originalIssuerLogoutRequestId;
    }

    public void setOriginalIssuerLogoutRequestId(String originalIssuerLogoutRequestId) {

        this.originalIssuerLogoutRequestId = originalIssuerLogoutRequestId;
    }

    public String getCurrentSLOInvokedParticipant() {

        return currentSLOInvokedParticipant;
    }

    public void setCurrentSLOInvokedParticipant(String currentSLOInvokedParticipant) {

        this.currentSLOInvokedParticipant = currentSLOInvokedParticipant;
    }

    public String getSessionIndex() {

        return sessionIndex;
    }

    public void setSessionIndex(String sessionIndex) {

        this.sessionIndex = sessionIndex;
    }

    public String getOriginalLogoutRequestIssuer() {

        return originalLogoutRequestIssuer;
    }

    public void setOriginalLogoutRequestIssuer(String originalLogoutRequestIssuer) {

        this.originalLogoutRequestIssuer = originalLogoutRequestIssuer;
    }

    public boolean isIdPInitSLO() {

        return isIdPInitSLO;
    }

    public void setIdPInitSLO(boolean idPInitSLO) {

        isIdPInitSLO = idPInitSLO;
    }

    public String getRelayState() {

        return relayState;
    }

    public void setRelayState(String relayState) {

        this.relayState = relayState;
    }

    public String getReturnToURL() {

        return returnToURL;
    }

    public void setReturnToURL(String returnToURL) {

        this.returnToURL = returnToURL;
    }
}
