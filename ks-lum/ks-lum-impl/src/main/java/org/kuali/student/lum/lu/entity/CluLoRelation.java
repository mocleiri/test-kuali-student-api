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

package org.kuali.student.lum.lu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.core.entity.AttributeOwner;
import org.kuali.student.core.entity.MetaEntity;

@Entity
@Table(name = "KSLU_CLU_LO_RELTN")
@NamedQueries({
	@NamedQuery(name="CluLoRelation.getCluLoRelation", query="SELECT rel FROM CluLoRelation rel WHERE rel.clu.id = :cluId and rel.loId = :loId"),
	@NamedQuery(name="CluLoRelation.getCluLoRelationByClu", query="SELECT rel FROM CluLoRelation rel WHERE rel.clu.id = :cluId"),
	@NamedQuery(name="CluLoRelation.getCluLoRelationByLo", query="SELECT rel FROM CluLoRelation rel WHERE rel.loId = :loId"),
	@NamedQuery(name="CluLoRelation.getCluLoRelationByCluIdAndType", query="SELECT rel.id FROM CluLoRelation rel WHERE rel.clu.id = :cluId AND rel.type = :cluLoRelationType")
})
public class CluLoRelation  extends MetaEntity implements
AttributeOwner<CluLoRelationAttribute> {

	@Id
	@Column(name="ID")
	private String id;

	@ManyToOne
	@JoinColumn(name="CLU_ID")
    private Clu clu;

	@Column(name="LO_ID")
    private String loId; 

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFF_DT")
    private Date effectiveDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIR_DT")
    private Date expirationDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<CluLoRelationAttribute> attributes;

	@Column(name="TYPE")
	private String type;

	@Column(name = "ST")
    private String state;
	
	@Override
	public List<CluLoRelationAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new ArrayList<CluLoRelationAttribute>();
		}
		return attributes;
	}

	@Override
	public void setAttributes(List<CluLoRelationAttribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public  void onPrePersist() {
		this.id = UUIDHelper.genStringUUID(this.id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Clu getClu() {
		return clu;
	}

	public void setClu(Clu clu) {
		this.clu = clu;
	}
	
	public String getLoId() {
		return loId;
	}

	public void setLoId(String loId) {
		this.loId = loId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}