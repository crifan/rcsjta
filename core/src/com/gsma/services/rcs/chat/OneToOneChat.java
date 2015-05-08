/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010 France Telecom S.A.
 * Copyright (C) 2014 Sony Mobile Communications Inc.
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
 *
 * NOTE: This file has been modified by Sony Mobile Communications Inc.
 * Modifications are licensed under the License.
 ******************************************************************************/

package com.gsma.services.rcs.chat;

import com.gsma.services.rcs.Geoloc;
import com.gsma.services.rcs.RcsGenericException;
import com.gsma.services.rcs.RcsIllegalArgumentException;
import com.gsma.services.rcs.RcsPersistentStorageException;
import com.gsma.services.rcs.contact.ContactId;

/**
 * One-to-One Chat
 * 
 * @author Jean-Marc AUFFRET
 */
public class OneToOneChat {

    /**
     * Chat interface
     */
    private final IOneToOneChat mOneToOneChatInf;

    /**
     * Constructor
     * 
     * @param chatIntf Chat interface
     */
    /* package private */OneToOneChat(IOneToOneChat chatIntf) {
        mOneToOneChatInf = chatIntf;
    }

    /**
     * Returns the remote contact
     * 
     * @return ContactId
     * @throws RcsGenericException
     */
    public ContactId getRemoteContact() throws RcsGenericException {
        try {
            return mOneToOneChatInf.getRemoteContact();

        } catch (Exception e) {
            throw new RcsGenericException(e);
        }
    }

    /**
     * Returns true if it is possible to send messages in this one to one chat right now, else
     * return false.
     * 
     * @return boolean
     * @throws RcsPersistentStorageException
     * @throws RcsGenericException
     */
    public boolean isAllowedToSendMessage() throws RcsPersistentStorageException,
            RcsGenericException {
        try {
            return mOneToOneChatInf.isAllowedToSendMessage();

        } catch (Exception e) {
            RcsPersistentStorageException.assertException(e);
            throw new RcsGenericException(e);
        }
    }

    /**
     * Sends a chat message
     * 
     * @param message Message
     * @return ChatMessage
     * @throws RcsPersistentStorageException
     * @throws RcsGenericException
     */
    public ChatMessage sendMessage(String message) throws RcsPersistentStorageException,
            RcsGenericException {
        try {
            return new ChatMessage(mOneToOneChatInf.sendMessage(message));

        } catch (Exception e) {
            RcsIllegalArgumentException.assertException(e);
            RcsPersistentStorageException.assertException(e);
            throw new RcsGenericException(e);
        }
    }

    /**
     * Sends a geoloc message
     * 
     * @param geoloc Geoloc info
     * @return ChatMessage
     * @throws RcsPersistentStorageException
     * @throws RcsGenericException
     */
    public ChatMessage sendMessage(Geoloc geoloc) throws RcsPersistentStorageException,
            RcsGenericException {
        try {
            return new ChatMessage(mOneToOneChatInf.sendMessage2(geoloc));

        } catch (Exception e) {
            RcsIllegalArgumentException.assertException(e);
            RcsPersistentStorageException.assertException(e);
            throw new RcsGenericException(e);
        }
    }

    /**
     * Called when is composing a chat message
     * 
     * @param enabled It should be set to true if user is composing and set to false when the client
     *            application is leaving the chat UI
     * @throws RcsGenericException
     */
    public void onComposing(final boolean enabled) throws RcsGenericException {
        try {
            mOneToOneChatInf.onComposing(enabled);
        } catch (Exception e) {
            throw new RcsGenericException(e);
        }
    }

    /**
     * open the chat conversation.<br>
     * Note: if it is an incoming pending chat session and the parameter IM SESSION START is 0 then
     * the session is accepted now.
     * 
     * @throws RcsGenericException
     */
    public void openChat() throws RcsGenericException {
        try {
            mOneToOneChatInf.openChat();
        } catch (Exception e) {
            throw new RcsGenericException(e);
        }
    }

    /**
     * Resend a message which previously failed.
     * 
     * @param msgId
     * @throws RcsPersistentStorageException
     * @throws RcsGenericException
     */
    public void resendMessage(String msgId) throws RcsPersistentStorageException,
            RcsGenericException {
        try {
            mOneToOneChatInf.resendMessage(msgId);
        } catch (Exception e) {
            RcsIllegalArgumentException.assertException(e);
            RcsPersistentStorageException.assertException(e);
            throw new RcsGenericException(e);
        }
    }
}