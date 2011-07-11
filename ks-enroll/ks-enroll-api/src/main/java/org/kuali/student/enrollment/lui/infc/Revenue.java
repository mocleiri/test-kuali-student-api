/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.enrollment.lui.infc;

import java.util.List;
import org.kuali.student.r2.common.infc.HasAttributesAndMeta;
import org.kuali.student.r2.common.infc.HasId;
import org.kuali.student.r2.lum.course.infc.AffiliatedOrg;

/**
 * Detailed information about revenue collected from the course.
 *
 * @author nina
 */
public interface Revenue extends HasId, HasAttributesAndMeta {

    /**
     * A code that identifies the type of the fee. For example: Lab Fee or Tuition Fee or CMF for Course Materials Fee.
     * @name: Fee Type
     */
    public String getFeeType();

    /**
     * List of affiliated organizations.
     * @name Affiliated Organizations
     */
    public List<? extends AffiliatedOrg> getAffiliatedOrgs();
}