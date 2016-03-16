/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010-2016 Orange.
 * Copyright (C) 2014 Sony Mobile Communications AB.
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
 * NOTE: This file has been modified by Sony Mobile Communications AB.
 * Modifications are licensed under the License.
 ******************************************************************************/

package com.gsma.rcs.core.ims.service.im.filetransfer.http;

import com.gsma.rcs.utils.logger.Logger;

import android.net.Uri;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Parse the XML file describing file content without thumbnail to resume upload
 */
public class FileTransferHttpResumeInfoParser extends DefaultHandler {
    /*
     * File-Transfer HTTP SAMPLE: <?xml version="1.0" encoding=  ?UTF-8  ??> <file-resume-info>
     * <file-range start="[start-offset in bytes]" end="[end-offset in bytes]" / > <data
     * url="[HTTP upload URL for the file]"/> </file-resume-info>
     */

    /**
     * File transfer over HTTP info document
     */
    private FileTransferHttpResumeInfo mFtResumeInfo;

    private static final Logger sLogger = Logger.getLogger(FileTransferHttpResumeInfoParser.class
            .getName());

    /**
     * Constructor
     * 
     * @param ftHttpInput
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public FileTransferHttpResumeInfoParser(InputSource ftHttpInput)
            throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(ftHttpInput, this);
    }

    /**
     * Gets information to resume upload transfer
     * 
     * @return information
     */
    public FileTransferHttpResumeInfo getResumeInfo() {
        return mFtResumeInfo;
    }

    /**
     * Receive notification of the beginning of the document.
     */
    public void startDocument() {
        if (sLogger.isActivated()) {
            sLogger.debug("Start document");
        }
    }

    /**
     * Receive notification of the start of an element.
     * 
     * @param namespaceURL
     * @param localName
     * @param qname
     * @param attr
     */
    public void startElement(String namespaceURL, String localName, String qname, Attributes attr) {
        if ("file-resume-info".equals(localName)) {
            mFtResumeInfo = new FileTransferHttpResumeInfo();

        } else if ("file-range".equals(localName)) {
            if (mFtResumeInfo != null) {
                String start = attr.getValue("start").trim();
                mFtResumeInfo.setStart(Integer.parseInt(start));
                String end = attr.getValue("end").trim();
                mFtResumeInfo.setEnd(Integer.parseInt(end));
            }

        } else if ("data".equals(localName)) {
            if (mFtResumeInfo != null) {
                String url = attr.getValue("url").trim();
                mFtResumeInfo.setUri(Uri.parse(url));
            }
        }
    }

    /**
     * Receive notification of the end of the document.
     */
    public void endDocument() {
        if (sLogger.isActivated()) {
            sLogger.debug("End document");
        }
    }
}
