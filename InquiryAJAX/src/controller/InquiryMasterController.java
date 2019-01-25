package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.InquiryMasterDao;
import dao.SourceMasterDao;
import vo.InquiryVo;
import vo.SourceVo;

/**
 * Servlet implementation class InquiryMasterController
 */
@WebServlet("/InquiryMasterController")
public class InquiryMasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryMasterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String flag=request.getParameter("flag");
		
		if(flag.equals("displayAll"))
		{
			InquiryMasterDao inquiryMasterDao=new InquiryMasterDao();
			List<InquiryVo> list=inquiryMasterDao.getAllInquiries();
			
			SourceMasterDao sourceMasterDao=new SourceMasterDao();
			List<SourceVo> sourceList=sourceMasterDao.getSourceList();
			
			HttpSession httpSession=request.getSession();
			httpSession.setAttribute("source", sourceList);
			
			if(list.isEmpty())
			{
				response.getWriter().println("Empty");
			}else {
				response.getWriter().println("NotEmpty");
			}
		}else if(flag.equals("addInquiry")) {
			addInquiry(request,response);
		}
	}

	private void addInquiry(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		InquiryMasterDao inquiryMasterDao=new InquiryMasterDao();
		
		BigInteger referenceNo=inquiryMasterDao.getRefernceNo("select case count(reference_no) when 0 then 1 else max(inquiry_id)+1 end from inquiry_master");
		
		String customerName=request.getParameter("customerName");
		String customerMobile=request.getParameter("customerMobile");
		String referenceSource=request.getParameter("referenceSource");
		String inquiryDetails=request.getParameter("inquiryDetails");
		String sourceId=request.getParameter("sourceId");
		String customerAddress=request.getParameter("customerAddress");
		
		InquiryVo inquiryVo=new InquiryVo();
		inquiryVo.setCustomerAddress(customerAddress);
		inquiryVo.setCustomerMobile(Long.parseLong(customerMobile));
		inquiryVo.setCustomerName(customerName);
		inquiryVo.setInquiryDate(new Date());
		inquiryVo.setInquiryDetails(inquiryDetails);
		SourceVo sourceVo=new SourceVo();
		sourceVo.setSourceId(Integer.parseInt(sourceId));
		inquiryVo.setSourceVo(sourceVo);
		
		int ids=referenceNo.intValue();
		String referenceNum="INQ"+ids;
		inquiryVo.setReferenceNumber(referenceNum);
		
		inquiryMasterDao.addInquiry(inquiryVo);
		response.getWriter().println("Inserted");
	}

}
