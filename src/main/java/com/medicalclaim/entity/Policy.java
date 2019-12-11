package com.medicalclaim.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String policyNo;
	private String policyHolderName;
	private String policyDependentName;
	private LocalDate validFrom;
	private LocalDate validTo;
	private Double policyAmount;
	private Double maxClaimAmount;
	private String status;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "policyId")
	private Set<PolicyClaim> policyClaim;
}
