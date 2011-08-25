/**
 * Copyright 2010 The Kuali Foundation Licensed under the
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

package org.kuali.student.core.personsearch.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.Person;

import org.kuali.student.common.search.dto.SearchParam;
import org.kuali.student.common.search.dto.SearchRequest;
import org.kuali.student.common.search.dto.SearchResult;
import org.kuali.student.common.search.dto.SearchResultCell;
import org.kuali.student.common.search.dto.SearchResultRow;
import org.kuali.student.common.search.dto.SearchTypeInfo;
import org.kuali.student.common.search.dto.SortDirection;


public final class QuickViewByGivenName extends PersonSearch implements SearchOperation {
    public static final String SEARCH_TYPE = "person.search.personQuickViewByGivenName";
    public static final String CRITERIA_TYPE = "person.search.personByGivenName";
    public static final String RESULT_TYPE = "person.search.personQuickView";
    final static public String NAME_PARAM = "person.queryParam.personGivenName";
    final static public String ID_PARAM = "person.queryParam.personId";
    final static public String AFFILIATION_PARAM = "person.queryParam.personAffiliation";

    final static public String PERSON_ID_RESULT = "person.resultColumn.PersonId";
    final static public String ENTITY_ID_RESULT = "person.resultColumn.EntityId";
    final static public String DISPLAY_NAME_RESULT = "person.resultColumn.DisplayName";// Smith, John (jsmith)
    final static public String GIVEN_NAME_RESULT = "person.resultColumn.GivenName";// Smith, John
    final static public String PRINCIPAL_NAME_RESULT = "person.resultColumn.PrincipalName";

    final static private String KIM_PERSON_AFFILIATION_TYPE_CODE = "affiliationTypeCode";

    final static private String KIM_PRINCIPALS_PRINCIPALNAME = "principals.principalName";
    final static private String KIM_PRINCIPALS_PRINCIPALID = "principals.principalId";
    final static private String KIM_PERSON_FIRST_NAME = "names.firstName";
    final static private String KIM_PERSON_MIDDLE_NAME = "names.middleName";
    final static private String KIM_PERSON_LAST_NAME = "names.lastName";
    
    
    private List<Person> findPersons(final IdentityService identityService, final SearchRequest searchRequest) {
        String nameSearch = null;
        String affilSearch = null;
        String idSearch = null;
        for (SearchParam param : searchRequest.getParams()) {
            String value = (String) param.getValue();
            if (!value.isEmpty()) {
                if (value.indexOf("%") == -1 && value.indexOf("*") == -1) {
                    value = "*" + value + "*";
                }
                if (NAME_PARAM.equals(param.getKey())) {
                    if (nameSearch != null) {
                        nameSearch += "|" + value;
                    } else {
                        nameSearch = value;
                    }
                } else if (AFFILIATION_PARAM.equals(param.getKey())) {
                    if (affilSearch != null) {
                        affilSearch += "|" + value;
                    } else {
                        affilSearch = value;
                    }
                } else if (ID_PARAM.equals(param.getKey())){
                	if(idSearch!=null){
                		idSearch += "|" + param.getValue();
                	}else{
                		idSearch = param.getValue().toString();
                	}
                }
            }
        }
        final List<Map<String, String>> searches = new ArrayList<Map<String, String>>();
        if(idSearch!=null){
        	Map<String, String> criteria = new HashMap<String, String>();
        	criteria.put(KIM_PRINCIPALS_PRINCIPALID, idSearch);
	        searches.add(criteria);
        }else if (nameSearch != null) {
        	Map<String, String> principalNameCriteria = new HashMap<String, String>();
        	principalNameCriteria.put(KIM_PRINCIPALS_PRINCIPALNAME, nameSearch);
	        searches.add(principalNameCriteria);
	        
            Map<String, String> firstNameCriteria = new HashMap<String, String>();
            firstNameCriteria.put(KIM_PERSON_FIRST_NAME, nameSearch);
            searches.add(firstNameCriteria);

            Map<String, String> middleNameCriteria = new HashMap<String, String>();
            middleNameCriteria.put(KIM_PERSON_MIDDLE_NAME, nameSearch);
            searches.add(middleNameCriteria);

            Map<String, String> lastNameCriteria = new HashMap<String, String>();
            lastNameCriteria.put(KIM_PERSON_LAST_NAME, nameSearch);
            searches.add(lastNameCriteria);
        }

        if (affilSearch != null) {//TODO what is this search for?
            if (searches.isEmpty()) {
                searches.add(new HashMap<String, String>());
            }
            for (Map<String, String> search : searches) {
                search.put(KIM_PERSON_AFFILIATION_TYPE_CODE, affilSearch);
            }
        }

        if (searches.isEmpty()) {
            searches.add(new HashMap<String, String>());
        }

        final Map<String,Person> persons = new HashMap<String,Person>();
        for (Map<String, String> criteria : searches) {
            criteria.putAll(PersonSearchServiceImpl.PERSON_CRITERIA);
            final List<? extends Person> foundPeople = findPeopleInternal(identityService, criteria, false);
            for (Person person : foundPeople) {
                persons.put(person.getEntityId(), person);
            }
        }
        return new ArrayList<Person>(persons.values());
    }

    @Override
    public SearchResult search(final IdentityService identityService, final SearchRequest searchRequest) {
        final SearchResult result = new SearchResult();

        List<Person> persons = findPersons(identityService, searchRequest);
        //TODO finish sorting
        if (searchRequest.getSortDirection() != null) {
            final int direction = (searchRequest.getSortDirection().equals(SortDirection.ASC) ? 1 : -1);
            Collections.sort(persons, new Comparator<Person>() {

                @Override
                public int compare(Person o1, Person o2) {
                    if (DISPLAY_NAME_RESULT.equals(searchRequest.getSortColumn())) {
                        return o1.getName().compareToIgnoreCase(o2.getName()) * direction;
                    } else if (ENTITY_ID_RESULT.equals(searchRequest.getSortColumn())) {
                        return o1.getPrincipalId().compareToIgnoreCase(o2.getPrincipalId()) * direction;
                    } else {
                        // TODO Recognize alternate sort columns
                        return 0;
                    }
                }
            });
        }

        for (Person person : persons) {
            final SearchResultRow resultRow = new SearchResultRow();
            resultRow.setCells(new ArrayList<SearchResultCell>());
            
            SearchResultCell cell = new SearchResultCell();
            cell.setKey(ENTITY_ID_RESULT);
            cell.setValue(person.getEntityId());
            resultRow.getCells().add(cell);
            
            cell = new SearchResultCell();
            cell.setKey(PERSON_ID_RESULT);
            cell.setValue(person.getPrincipalId());
            resultRow.getCells().add(cell);
            
            cell = new SearchResultCell();
            cell.setKey(PRINCIPAL_NAME_RESULT);
            cell.setValue(person.getPrincipalName());
            resultRow.getCells().add(cell);

            cell = new SearchResultCell();
            cell.setKey(GIVEN_NAME_RESULT);
            cell.setValue(person.getName());
            resultRow.getCells().add(cell);

            cell = new SearchResultCell();
            cell.setKey(DISPLAY_NAME_RESULT);
            cell.setValue(person.getName()+"("+person.getPrincipalName()+")");
            resultRow.getCells().add(cell);
            
            result.getRows().add(resultRow);
            
        }
        
        result.setStartAt(searchRequest.getStartAt());
        result.setTotalResults(result.getRows().size()); // TODO fix this
        return result;
    }

 @Override
 public SearchTypeInfo getType ()
 {
  return new QuickViewByGivenNameSearchTypeCreator ().get ();
 }


}
