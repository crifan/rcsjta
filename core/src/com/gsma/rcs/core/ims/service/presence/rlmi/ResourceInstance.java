/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010-2016 Orange.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.gsma.rcs.core.ims.service.presence.rlmi;

public class ResourceInstance {

    private String uri;
    private String state = null;
    private String reason = null;

    public ResourceInstance(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public String getState() {
        return state;
    }

    public String getReason() {
        return reason;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
