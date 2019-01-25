package controller;

import java.io.IOException;
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
		}
	}

}
