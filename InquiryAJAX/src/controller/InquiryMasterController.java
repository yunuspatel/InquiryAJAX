package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
				response.getWriter().println("Not");
			}
		}else if(flag.equals("addInquiry")) {
			addInquiry(request,response);
		}else if(flag.equals("display")) {
			setInquiryData(request,response);
		}else if(flag.equals("loadData")) {
			loadData(request,response);
		}else if(flag.equals("delete")) {
			deleteInquiry(request,response);
		}
	}

	private void deleteInquiry(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		InquiryMasterDao inquiryMasterDao=new InquiryMasterDao();
		int id=Integer.parseInt(request.getParameter("id"));
		InquiryVo inquiryVo=new InquiryVo();
		inquiryVo.setInquiryId(id);
		inquiryMasterDao.delete(inquiryVo);
	}

	private void loadData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		InquiryMasterDao inquiryMasterDao=new InquiryMasterDao();
		int id=Integer.parseInt(request.getParameter("id"));
		List<InquiryVo> list=inquiryMasterDao.getInquiry(id);
		
		if(!list.isEmpty()) {
			PrintWriter out=response.getWriter();
			
			String data="<table align=center border=1>";
			for(InquiryVo inquiryVo : list) {
				data+="<tr>";
				data+="<td>"+inquiryVo.getInquiryId()+"</td>";
				data+="<td>"+inquiryVo.getCustomerName()+"</td>";
				data+="<td>"+inquiryVo.getCustomerAddress()+"</td>";
				data+="<td>"+inquiryVo.getCustomerMobile()+"</td>";
				data+="<td>"+inquiryVo.getReferenceNumber()+"</td>";
				data+="<td>"+inquiryVo.getInquiryDetails()+"</td>";
				data+="<td>"+inquiryVo.getInquiryDate()+"</td>";
				data+="<td>"+inquiryVo.getSourceVo().getSourceName()+"</td>";
				data+="</tr>";
			}
			data+="</table>";
			System.out.println(data);
			out.println(data);
			/*out.println("<inquiry>");
			for(InquiryVo inquiryVo : list) {
				out.println("<inquiryId>"+inquiryVo.getInquiryId()+"</inquiryId");
				out.println("<address>"+inquiryVo.getCustomerAddress()+"</address");
				out.println("<name>"+inquiryVo.getCustomerName()+"</name");
				out.println("<inquiryDetails>"+inquiryVo.getInquiryDetails()+"</inquiryDetails");
				out.println("<referenceNo>"+inquiryVo.getReferenceNumber()+"</referenceNo");
				out.println("<mobileno>"+inquiryVo.getCustomerMobile()+"</mobileno");
				out.println("<inquiryDate>"+inquiryVo.getInquiryDate()+"</inquiryDate");
				out.println("<referenceName>"+inquiryVo.getSourceVo().getSourceName()+"</referenceName");
			}
			out.println("</inquiry>");*/
		}
	}

	private void setInquiryData(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		InquiryMasterDao inquiryMasterDao=new InquiryMasterDao();
		List<InquiryVo> inquiryList=inquiryMasterDao.getAllInquiries();
		session.setAttribute("allInquiries", inquiryList);
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
		
		List<InquiryVo> inquiryList=inquiryMasterDao.getAllInquiries();
		HttpSession session=request.getSession();
		session.setAttribute("allInquiries",inquiryList);
		
		inquiryMasterDao.addInquiry(inquiryVo);
		response.getWriter().println("Inserted");
	}

}
