/**
 * Copyright 2012 The Kuali Foundation Licensed under the
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
 *
 * Created by bobhurt on 8/7/12
 */
package org.kuali.student.enrollment.kitchensink;

import org.hsqldb.lib.StringUtil;
import org.kuali.rice.krad.uif.field.LinkField;
import org.kuali.rice.krad.uif.service.impl.ViewHelperServiceImpl;

import java.text.MessageFormat;

/**
 * This class //TODO ...
 *
 * @author Kuali Student Team
 */
public class KitchenSinkHelper extends ViewHelperServiceImpl {

    public void setSourceLinkText(LinkField linkField, Object model) {
        linkField.setLinkText(linkField.getHref());
    }

    public void setDirectLinkUrl(LinkField linkField, Object model, String methodToCall) {
        if (StringUtil.isEmpty(methodToCall)) {
            methodToCall = "start";
        }

        String url = MessageFormat.format("{0}?viewId={1}&amp;methodToCall={2}",
                ((KitchenSinkForm) model).getFormPostUrl(),
                ((KitchenSinkForm) model).getViewId() + "-Bare", methodToCall);
        linkField.setLinkText(url);
        linkField.setHref(url);
    }
    public void setDirectLinkUrl(LinkField linkField, Object model) {
        setDirectLinkUrl(linkField, model, null);
    }

}
