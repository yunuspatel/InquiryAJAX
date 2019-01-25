package vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inquiry_master")
public class InquiryVo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="inquiry_id",length=5)
	int inquiryId;
	
	@Column(name="customer_name",length=20)
	String customerName;
	
	@Column(name="customer_mobile",length=12)
	Long customerMobile;
	
	@Column(name="customer_address",length=250)
	String customerAddress;
	
	@ManyToOne
	@JoinColumn(name="source_id",referencedColumnName="source_id")
	SourceVo sourceVo;
	
	@Column(name="inquiry_date",length=50)
	Date inquiryDate;
	
	public Date getInquiryDate() {
		return inquiryDate;
	}

	public void setInquiryDate(Date inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	@Column(name="inquiry_details",length=250)
	String inquiryDetails;
	
	@Column(name="reference_no",length=25)
	String referenceNumber;
	
	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public int getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(Long customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public SourceVo getSourceVo() {
		return sourceVo;
	}

	public void setSourceVo(SourceVo sourceVo) {
		this.sourceVo = sourceVo;
	}

	public String getInquiryDetails() {
		return inquiryDetails;
	}

	public void setInquiryDetails(String inquiryDetails) {
		this.inquiryDetails = inquiryDetails;
	}
}
