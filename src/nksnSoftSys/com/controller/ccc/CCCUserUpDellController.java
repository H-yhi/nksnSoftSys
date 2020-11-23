package nksnSoftSys.com.controller.ccc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nksnSoftSys.com.bean.userInfo.UserBean;
import nksnSoftSys.com.dao.kjnGra.KjnGraDao;
import nksnSoftSys.com.dao.user.UserDao;

/**
 * Servlet implementation class CCCUserUpDellController
 */
@WebServlet("/CCCUserUpDellController")
public class CCCUserUpDellController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CCCUserUpDellController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String update = request.getParameter("update");
		String delete = request.getParameter("delete");

		if (update == null) {
			UserDao userDao = new UserDao();
			KjnGraDao kjnGraDao = new KjnGraDao();
			if(kjnGraDao.kjnGraDell(delete)) {
				userDao.userDell(delete);
				List<UserBean> list = userDao.findAll();
				request.setAttribute("list", list);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/applicationList.jsp");
				dispatcher.forward(request, response);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/regUser.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
