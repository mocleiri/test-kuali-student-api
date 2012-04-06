/*
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.r2.lum.lu.infc;

import org.kuali.student.r2.common.dto.RichTextInfo;
import org.kuali.student.r2.common.infc.Relationship;

/**
 * Information about the LU to document relation.
 *
 * @Version 2.0
 * @Author Sri komandur@uw.edu
 */
public interface LuDocRelation extends Relationship {
    /**
     * Unique identifier for a Canonical Learning Unit (CLU).
     *
     * @name Clu Id
     * @readOnly
     * @required
     */
    String getCluId();

    /**
     * Unique identifier for a document.
     *
     * @name Document Id
     * @readOnly
     * @required
     */
    String getDocumentId();

    /**
     * The title of the document usage in the context of the CLU.
     *
     * @name Title
     */
    String getTitle();

    /**
     * The description of the document usage in the context of the CLU.
     *
     * @name Descr
     */
    RichTextInfo getDescr();
}
