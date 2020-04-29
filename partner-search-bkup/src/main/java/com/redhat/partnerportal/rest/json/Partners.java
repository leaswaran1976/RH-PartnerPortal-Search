package com.redhat.partnerportal.rest.json;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "partners")
public class Partners extends PanacheEntity {
	public String account_owner;
	public String website;
	public String billing_state_province;
	public String billing_address;
	public String company_email_address;
	public String phone;
	public String last_activity;
	public String description_of_business;
	public int total_partner_delivery_accreditation;
	public int total_partner_sales_accreditation;
	public int total_partner_technical_accreditation;
	public String partner_skills;
	public String industry_vertical_expertise;
	public String subregion;
	public String partner_status_visible;
	public String partner_programs;
	public String account_name;
	public String finder_partner_tier;
	public int partner_center_id;
	public int account_number;
	public String program_group;

	/**
	 * Empty Constructor as required by Quarkus
	 */
	public Partners() {
	}

	/**
	 * Constructor to create Partners entity
	 * 
	 * @param ao
	 * @param web_site
	 * @param bsp
	 * @param baddr
	 * @param cemail
	 * @param phone
	 * @param last_activity
	 * @param desc
	 * @param da
	 * @param sa
	 * @param pta
	 * @param pskills
	 * @param ive
	 * @param subregion
	 * @param psv
	 * @param pp
	 * @param aname
	 * @param fpt
	 * @param pcid
	 * @param acctnum
	 */
	public Partners(String ao, String web_site, String bsp, String baddr, String cemail, String phone,
			String last_activity, String desc, int da, int sa, int pta, String pskills, String ive, String subregion,
			String psv, String pp, String aname, String fpt, int pcid, int acctnum, String pgmGroup) {
		this.account_owner = ao;
		this.website = web_site;
		this.billing_state_province = bsp;
		this.billing_address = baddr;
		this.company_email_address = cemail;
		this.phone = phone;
		this.last_activity = last_activity;
		this.description_of_business = desc;
		this.total_partner_delivery_accreditation = da;
		this.total_partner_sales_accreditation = sa;
		this.total_partner_technical_accreditation = pta;
		this.partner_skills = pskills;
		this.industry_vertical_expertise = ive;
		this.subregion = subregion;
		this.partner_status_visible = psv;
		this.partner_programs = pp;
		this.account_name = aname;
		this.finder_partner_tier = fpt;
		this.partner_center_id = pcid;
		this.account_number = acctnum;
		this.program_group = pgmGroup;
	}

	/**
	 * Method to find Partners by name
	 * 
	 * @param name - name of the partner
	 * @return
	 */
	public static List<Partners> findByName(String name) {
		return find("account_name", name).list();
	}

	/**
	 * Method to find special Partners like APEX, GSI etc
	 * 
	 * @return
	 */
	public static List<Partners> findSpecialPartners(String keyword) {
		return find("program_group like ?1", '%' + keyword.toUpperCase() + '%').list();
	}
}